package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.Revival;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTyrannosaurus extends EntityPrehistoric {

	public static Animation ROAR_ANIMATION = Animation.create(100);

	public EntityTyrannosaurus(World world) {
		super(world, PrehistoricEntityType.TYRANNOSAURUS, 2, 14, 15, 82, 0.25, 0.45);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new DinoAIRiding(this, 1.0F));
		this.tasks.addTask(4, new DinoAIAttackOnCollide(this, 1.5D, false));
		this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new DinoAIEatFeeders(this, 1));
		this.tasks.addTask(6, new DinoAIEatItems(this, 1));
		this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
		this.tasks.addTask(8, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new DinoAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, new Predicate<Entity>() {
			@Override
			public boolean apply(@Nullable Entity entity) {
				return entity instanceof EntityLivingBase;
			}
		}));
		this.setActualSize(1.8F, 1.25F);
		this.pediaScale = 40F;
		this.hasFeatherToggle = true;
		this.featherToggle = Revival.CONFIG.featheredTRex;
		minSize = 0.4F;
		maxSize = 4.3F;
		teenAge = 5;
		developsResistance = true;
		breaksBlocks = true;
		this.nearByMobsAllowed = 2;
		this.ridingY = 1.45F;
		this.ridingXZ = -0.05F;
	}

	@Override
	public int getAttackLength() {
		return 30;
	}

	@Override
	public int getSpeakLength() {
		return 40;
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
		if (getAttackBounds().intersects(entity.getEntityBoundingBox())) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ATTACK_ANIMATION);
				return false;
			}
			if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 12) {
				IAttributeInstance attackDamage = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				boolean hurt = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) attackDamage.getAttributeValue());
				if (entity.getRidingEntity() != null) {
					if (entity.getRidingEntity() == this) {
						entity.startRiding(null);
					}
				}
				entity.motionY += (0.4000000059604645D / 2);
				knockbackEntity(entity, 1F, 0.1F);
				return hurt;
			}
		}
		return false;
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

		return PrehistoricEntityTypeAI.Attacking.DROP;
	}

	@Override
	public PrehistoricEntityTypeAI.Climbing aiClimbType() {

		return PrehistoricEntityTypeAI.Climbing.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Following aiFollowType() {

		return PrehistoricEntityTypeAI.Following.AGRESSIVE;
	}

	@Override
	public PrehistoricEntityTypeAI.Jumping aiJumpType() {

		return PrehistoricEntityTypeAI.Jumping.BASIC;
	}

	@Override
	public PrehistoricEntityTypeAI.Response aiResponseType() {

		return isChild() ? PrehistoricEntityTypeAI.Response.SCARED : PrehistoricEntityTypeAI.Response.AGRESSIVE;
	}

	@Override
	public PrehistoricEntityTypeAI.Stalking aiStalkType() {

		return PrehistoricEntityTypeAI.Stalking.STEALTH;
	}

	@Override
	public PrehistoricEntityTypeAI.Taming aiTameType() {

		return PrehistoricEntityTypeAI.Taming.GEM;
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

		return FAItemRegistry.SKULL_STICK;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.isSleeping() && this.rand.nextInt(1500) == 0 && !world.isRemote && !this.isSkeleton() && !this.isActuallyWeak() && !this.isSitting() && this.getAttackTarget() == null) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ROAR_ANIMATION);
			}
		}
		if (getAnimation() == EntityTyrannosaurus.ROAR_ANIMATION && getAnimationTick() == 10) {
			this.playSound(FASoundRegistry.TYRANNOSAURUS_ROAR, this.getSoundPitch(), this.getSoundVolume());
		}
	}

	private void triggerTamingAcheivement(EntityPlayer player) {
		// player.addStat(FossilAchievements.THE_KING);

	}

	@Override
	public int getAdultAge() {
		return 12;
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{SPEAK_ANIMATION, ATTACK_ANIMATION, ROAR_ANIMATION};
	}

	@Override
	public int getTailSegments() {
		return 3;
	}

	public int getMaxHunger() {
		return 150;
	}

	public void onDeath(DamageSource source) {
		if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer) {
			//((EntityPlayer) source.getTrueSource()).addStat(FossilAchievements.TREX_KILL);
		}
		super.onDeath(source);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isWeak() ? FASoundRegistry.TYRANNOSAURUS_WEAK : FASoundRegistry.TYRANNOSAURUS_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.TYRANNOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.TYRANNOSAURUS_DEATH;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}
}
