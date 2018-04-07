package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class EntityParasaurolophus extends EntityPrehistoric {

	private static final DataParameter<Boolean> STANDING = EntityDataManager.<Boolean>createKey(EntityParasaurolophus.class, DataSerializers.BOOLEAN);
	public float standProgress;
	private boolean isStanding;
	private int standTicks;

	public EntityParasaurolophus(World world) {
		super(world, PrehistoricEntityType.PARASAUROLOPHUS, 2, 17, 25, 70, 0.25, 0.5);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new DinoAIRiding(this, 1.5F));
		this.tasks.addTask(3, new DinoAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(4, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(5, new DinoAIEatBlocks(this, 1));
		this.tasks.addTask(5, new DinoAIEatFeeders(this, 1));
		this.tasks.addTask(5, new DinoAIEatItems(this, 1));
		this.tasks.addTask(6, new DinoAIWander(this, 1.0D));
		this.tasks.addTask(7, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new DinoAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, new Predicate<Entity>() {
			@Override
			public boolean apply(@Nullable Entity entity) {
				return entity instanceof EntityLivingBase;
			}
		}));
		this.setActualSize(1.6F, 1.2F);
		this.nearByMobsAllowed = 6;
		minSize = 0.6F;
		maxSize = 2.5F;
		teenAge = 5;
		developsResistance = true;
		breaksBlocks = true;
		this.pediaScale = 30F;
		this.ridingY = 2;
	}

	public int getSpeakLength() {
		return 40;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(STANDING, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Standing", this.isStanding);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setStanding(compound.getBoolean("Standing"));

	}

	public boolean isStanding() {
		if (world.isRemote) {
			boolean isStanding = this.dataManager.get(STANDING);
			this.isStanding = isStanding;
			return isStanding;
		}

		return isStanding;
	}

	public void setStanding(boolean standing) {
		this.dataManager.set(STANDING, standing);
		if (!world.isRemote) {
			this.isStanding = standing;
		}
	}

	@Override
	public int getAttackLength() {
		return 30;
	}

	@Override
	public int getAdultAge() {
		return 13;
	}

	@Override
	public void setSpawnValues() {
	}

	@Override
	public Activity aiActivityType() {

		return Activity.DIURINAL;
	}

	@Override
	public Attacking aiAttackType() {

		return Attacking.CHARGE;
	}

	@Override
	public Climbing aiClimbType() {

		return Climbing.NONE;
	}

	@Override
	public Following aiFollowType() {

		return Following.NONE;
	}

	@Override
	public Jumping aiJumpType() {

		return Jumping.BASIC;
	}

	@Override
	public Response aiResponseType() {

		return Response.TERITORIAL;
	}

	@Override
	public Stalking aiStalkType() {

		return Stalking.NONE;
	}

	@Override
	public Taming aiTameType() {

		return Taming.IMPRINTING;
	}

	@Override
	public Untaming aiUntameType() {

		return Untaming.STARVE;
	}

	@Override
	public Moving aiMovingType() {

		return Moving.WALK;
	}

	@Override
	public WaterAbility aiWaterAbilityType() {

		return WaterAbility.NONE;
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
	public int getTailSegments() {
		return 3;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAttackBounds().intersects(entity.getEntityBoundingBox())) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ATTACK_ANIMATION);
				return false;
			}

			if (this.getAnimation() == ATTACK_ANIMATION && (this.getAnimationTick() >= 12 && this.getAnimationTick() <= 14)) {
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
				if (entity.getRidingEntity() != null) {
					if (entity.getRidingEntity() == this) {
						entity.startRiding(null);
					}
				}
				entity.motionY += 0.1000000059604645D;
				return flag;
			}
		}
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		boolean standing = this.isStanding();
		if (standing && standProgress < 20.0F) {
			standProgress += 0.5F;
		} else if (!standing && standProgress > 0.0F) {
			standProgress -= 0.5F;
		}
		if (standing) {
			standTicks++;
		} else if (!standing && standTicks != 0) {
			standTicks = 0;
		}
		if (this.getAnimation() == ATTACK_ANIMATION && (this.getAnimationTick() >= 12 && this.getAnimationTick() <= 14) && this.getAttackTarget() != null) {
			this.attackEntityAsMob(this.getAttackTarget());
		}
		if (!this.world.isRemote && !this.isMovementBlocked() && !this.isStanding() && this.getRNG().nextInt(100) == 0 && standTicks == 0) {
			this.setStanding(true);
		}
		if (!this.world.isRemote && this.isStanding() && this.getRNG().nextInt(800) == 0 && standTicks > 800) {
			this.setStanding(false);
		}
	}

	public void setSleeping(boolean sleeping) {
		if (sleeping) {
			this.setStanding(false);
		}
		super.setSleeping(sleeping);
	}

	public void setSitting(boolean sitting) {
		if (sitting) {
			this.setStanding(false);
		}
		super.setSitting(sitting);
	}


	public int getMaxHunger() {
		return 175;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return FASoundRegistry.PARASAUROLOPHUS_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.PARASAUROLOPHUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.PARASAUROLOPHUS_DEATH;
	}
}
