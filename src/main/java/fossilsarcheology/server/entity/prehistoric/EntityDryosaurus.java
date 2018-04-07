package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityDryosaurus extends EntityPrehistoric {

	public EntityDryosaurus(World world) {
		super(world, PrehistoricEntityType.DRYOSAURUS, 1, 1, 6, 16, 0.2, 0.35);
		this.teenAge = 3;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new DinoAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(6, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(7, new DinoAIEatBlocks(this, 1));
		this.tasks.addTask(7, new DinoAIEatFeeders(this, 1));
		this.tasks.addTask(7, new DinoAIEatItems(this, 1));
		this.tasks.addTask(8, new DinoAIWander(this, 1.0D));
		this.tasks.addTask(9, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new DinoAILookIdle(this));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, new Predicate<Entity>() {
			@Override
			public boolean apply(@Nullable Entity entity) {
				return entity instanceof EntityLivingBase;
			}
		}));
		this.setActualSize(0.8F, 0.7F);
		this.nearByMobsAllowed = 10;
		minSize = 0.5F;
		maxSize = 1F;
		teenAge = 2;
		developsResistance = false;
		breaksBlocks = false;
		hasTeenTexture = false;
		pediaScale = 40;
	}

	@Override
	public int getAttackLength() {
		return 25;
	}

	@Override
	public void setSpawnValues() {
	}

	@Override
	public PrehistoricEntityTypeAI.Activity aiActivityType() {

		return PrehistoricEntityTypeAI.Activity.DIURINAL;
	}

	@Override
	public PrehistoricEntityTypeAI.Attacking aiAttackType() {

		return PrehistoricEntityTypeAI.Attacking.BASIC;
	}

	@Override
	public PrehistoricEntityTypeAI.Climbing aiClimbType() {

		return PrehistoricEntityTypeAI.Climbing.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Following aiFollowType() {

		return PrehistoricEntityTypeAI.Following.NORMAL;
	}

	@Override
	public PrehistoricEntityTypeAI.Jumping aiJumpType() {

		return PrehistoricEntityTypeAI.Jumping.BASIC;
	}

	@Override
	public PrehistoricEntityTypeAI.Response aiResponseType() {

		return PrehistoricEntityTypeAI.Response.SCARED;
	}

	@Override
	public PrehistoricEntityTypeAI.Stalking aiStalkType() {

		return PrehistoricEntityTypeAI.Stalking.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Taming aiTameType() {

		return PrehistoricEntityTypeAI.Taming.FEEDING;
	}

	@Override
	public PrehistoricEntityTypeAI.Untaming aiUntameType() {

		return PrehistoricEntityTypeAI.Untaming.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Moving aiMovingType() {

		return PrehistoricEntityTypeAI.Moving.WALK;
	}

	@Override
	public PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType() {

		return PrehistoricEntityTypeAI.WaterAbility.NONE;
	}

	@Override
	public boolean doesFlock() {
		return false;
	}

	@Override
	public Item getOrderItem() {
		return Items.STICK;
	}

	@Override
	public int getAdultAge() {
		return 5;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 12 && this.getAttackTarget() != null) {
			this.attackEntityAsMob(this.getAttackTarget());
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAttackBounds().intersects(entity.getEntityBoundingBox())) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ATTACK_ANIMATION);
				return false;
			}

			if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 12) {
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
				if (entity.getRidingEntity() != null) {
					if (entity.getRidingEntity() == this) {
						entity.startRiding(null);
					}
				}
				return flag;
			}
		}
		return false;
	}

	public int getMaxHunger() {
		return 50;
	}

	@Override
	public boolean canBeRidden() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return FASoundRegistry.DRYOSAURUS_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.DRYOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.DRYOSAURUS_DEATH;
	}
}
