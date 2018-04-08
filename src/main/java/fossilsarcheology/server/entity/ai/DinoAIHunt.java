package fossilsarcheology.server.entity.ai;

import com.google.common.base.Predicate;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.PrehistoricMoodType;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import fossilsarcheology.server.util.FoodMappings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;

public class DinoAIHunt<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
	private EntityPrehistoric dragon;

	public DinoAIHunt(EntityPrehistoric entityIn, Class<T> classTarget, boolean checkSight, Predicate<? super T> targetSelector) {
		super(entityIn, classTarget, 0, checkSight, false, targetSelector);
		this.dragon = entityIn;
	}

	@Override
	public boolean shouldExecute() {
		if (super.shouldExecute() && this.targetEntity != null && !this.targetEntity.getClass().equals(this.dragon.getClass())) {
			if (this.dragon.width >= this.targetEntity.width) {
				if (this.taskOwner instanceof EntityPrehistoric) {
					EntityPrehistoric prehistoric = (EntityPrehistoric) this.taskOwner;
					if (targetEntity instanceof EntityPlayer && ((EntityPlayer) targetEntity).capabilities.isCreativeMode) {
						return false;
					}
					if (targetEntity instanceof EntityPlayer) {
						if (taskOwner.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
							return false;
						}
						if (prehistoric.getMood() < 0 && prehistoric.getMoodFace() != PrehistoricMoodType.CALM) {
							return !((EntityPlayer) targetEntity).capabilities.isCreativeMode;
						} else if (prehistoric.getMood() > 25 && prehistoric.getMoodFace() != PrehistoricMoodType.CALM) {
							return false;
						} else if (prehistoric.getMoodFace() == PrehistoricMoodType.CALM) {
							return !prehistoric.func_152114_e(targetEntity) && prehistoric.canDinoHunt(targetEntity, true);
						}
					}
					if (FoodMappings.INSTANCE.getEntityFoodAmount(this.targetEntity.getClass(), prehistoric.type.diet) > 0) {
						return !prehistoric.func_152114_e(targetEntity) && prehistoric.canDinoHunt(targetEntity, true);
					}
					if (targetEntity instanceof EntityToyBase && prehistoric.ticksTillPlay == 0) {
						return true;
					}
					if (prehistoric.isMovementBlocked() || !prehistoric.canDinoHunt(targetEntity, true)) {
						return false;
					}

				}
				return true;
			}
		}
		return false;
	}
}