package fossilsarcheology.server.entity.prehistoric;


import com.google.common.base.Predicate;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityIcthyosaurus extends EntityPrehistoricSwimming {

	public EntityIcthyosaurus(World world) {
		super(world, PrehistoricEntityType.ICTHYOSAURUS, 1, 4, 10, 30, 0.1, 0.1);
		FISH_ANIMATION = Animation.create(40);
		this.tasks.addTask(1, this.aiSit);
		this.tasks.addTask(3, new DinoAIEatFeeders(this, 1));
		this.tasks.addTask(3, new DinoAIEatItems(this, 1));
		this.tasks.addTask(4, new DinoAIMakeFish(this));
		this.tasks.addTask(5, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new DinoAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, new Predicate<Entity>() {
			@Override
			public boolean apply(@Nullable Entity entity) {
				return entity instanceof EntityLivingBase;
			}
		}));
		this.setActualSize(1.2F, 1.0F);
		minSize = 0.3F;
		maxSize = 0.8F;
		teenAge = 3;
		developsResistance = true;
		pediaScale = 40;
	}

	@Override
	public void setSpawnValues() {
	}

	@Override
	public PrehistoricEntityTypeAI.Activity aiActivityType() {
		return PrehistoricEntityTypeAI.Activity.BOTH;
	}

	@Override
	public PrehistoricEntityTypeAI.Attacking aiAttackType() {
		return PrehistoricEntityTypeAI.Attacking.DROWN;
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
		return PrehistoricEntityTypeAI.Response.WATERCALM;
	}

	@Override
	public PrehistoricEntityTypeAI.Stalking aiStalkType() {
		return PrehistoricEntityTypeAI.Stalking.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Taming aiTameType() {
		return PrehistoricEntityTypeAI.Taming.IMPRINTING;
	}

	@Override
	public PrehistoricEntityTypeAI.Untaming aiUntameType() {
		return PrehistoricEntityTypeAI.Untaming.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Moving aiMovingType() {
		return PrehistoricEntityTypeAI.Moving.AQUATIC;
	}

	@Override
	public PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType() {
		return PrehistoricEntityTypeAI.WaterAbility.ATTACK;
	}

	@Override
	public boolean doesFlock() {
		return false;
	}

	@Override
	public Item getOrderItem() {
		return FAItemRegistry.SHELL;
	}

	@Override
	public int getAdultAge() {
		return 7;
	}

	public String getTexture() {
		if (isSkeleton()) {
			return "fossil:textures/model/icthyosaurus_0/icthyosaurus_skeleton.png";
		}
		String gender = this.isChild() ? "_baby" : (this.getGender() == 0 ? "_female" : "_male");
		return "fossil:textures/model/icthyosaurus_0/icthyosaurus" + gender + ".png";
	}

	@Override
	public double swimSpeed() {
		return 2;
	}

	public int getMaxHunger() {
		return 125;
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{SPEAK_ANIMATION, ATTACK_ANIMATION, FISH_ANIMATION};
	}

	@Override
	public boolean canBeRidden() {
		return false;
	}
}
