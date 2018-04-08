package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySarcosuchus extends EntityPrehistoricSwimming {

    private static final DataParameter<Boolean> SWIMMING = EntityDataManager.<Boolean>createKey(EntitySarcosuchus.class, DataSerializers.BOOLEAN);

    public static Animation ROLL_ANIMATION = Animation.create(115);
    private boolean isSwimming;
    public float swimProgress;

    public EntitySarcosuchus(World world) {
        super(world, PrehistoricEntityType.SARCOSUCHUS, 1, 3, 15, 70, 0.25, 0.25);
        this.setActualSize(2.0F, 1.0F);
        isAmphibious = true;
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(3, new DinoAIRiding(this, 1.0F));
        this.tasks.addTask(3, new DinoAIAttackOnCollide(this, 1.5D, false));
        this.tasks.addTask(4, new DinoAIEatFeeders(this, 1));
        this.tasks.addTask(4, new DinoAIEatItems(this, 1));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
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
        minSize = 0.2F;
        maxSize = 2.3F;
        teenAge = 5;
        developsResistance = true;
        breaksBlocks = true;
        this.hasBabyTexture = true;
        this.ridingY = 1.2F;
        this.pediaScale = 30;
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

        return PrehistoricEntityTypeAI.Following.AGRESSIVE;
    }

    @Override
    public PrehistoricEntityTypeAI.Jumping aiJumpType() {

        return PrehistoricEntityTypeAI.Jumping.BASIC;
    }

    @Override
    public PrehistoricEntityTypeAI.Response aiResponseType() {

        return PrehistoricEntityTypeAI.Response.AGRESSIVE;
    }

    @Override
    public PrehistoricEntityTypeAI.Stalking aiStalkType() {

        return PrehistoricEntityTypeAI.Stalking.NONE;
    }

    @Override
    public PrehistoricEntityTypeAI.Taming aiTameType() {

        return PrehistoricEntityTypeAI.Taming.BLUEGEM;
    }

    @Override
    public PrehistoricEntityTypeAI.Untaming aiUntameType() {

        return PrehistoricEntityTypeAI.Untaming.NONE;
    }

    @Override
    public PrehistoricEntityTypeAI.Moving aiMovingType() {

        return PrehistoricEntityTypeAI.Moving.SEMIAQUATIC;
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
        return FAItemRegistry.SKULL_STICK;
    }

    @Override
    public int getAdultAge() {
        return 12;
    }

    public int getMaxHunger() {
        return 150;
    }

    @Override
    public double swimSpeed() {
        return 2;
    }

    @Override
    public void updateRidden() {
        if (this.getRidingPlayer() != null && this.func_152114_e(this.getRidingPlayer())) {
            super.updateRidden();
            return;
        }
        if (this.getPassengers().get(0) != null && this.getPassengers().get(0) instanceof EntityLivingBase) {
            Entity riddenByEntity = this.getPassengers().get(0);
            if (this.getAnimationTick() % 20 == 0 && riddenByEntity != null) {
                riddenByEntity.attackEntityFrom(DamageSource.DROWN, 10);

                if (riddenByEntity instanceof EntityToyBase) {
                    ((EntityToyBase) riddenByEntity).dismountEntity(this);
                }
                if (riddenByEntity.isDead) {
                    this.onKillEntity((EntityLivingBase) riddenByEntity);
                }
            }
            riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + riddenByEntity.getYOffset(), this.posZ);
            this.rotationYaw *= 0;
            riddenByEntity.rotationYaw = this.rotationYaw + this.rotationYawHead + 180;
            rotationYaw = renderYawOffset;
            float radius = -0.7F * (0.7F * getAgeScale()) * -3;
            float angle = (0.01745329251F * this.renderYawOffset) + 3.15F * 0.05F;
            double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
            double extraZ = (double) (radius * MathHelper.cos(angle));
            double extraY = 0.5F * getAgeScale();
            riddenByEntity.setPosition(this.posX + extraX, this.posY + extraY, this.posZ + extraZ);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        boolean swimming = this.isSwimming();
        if (swimming && swimProgress < 20.0F) {
            swimProgress += 0.5F;
            if (sitProgress != 0)
                sitProgress = sleepProgress = 0F;
        } else if (!swimming && swimProgress > 0.0F) {
            swimProgress -= 0.5F;
            if (sitProgress != 0)
                sitProgress = sleepProgress = 0F;
        }
        if (this.isSwimming() && (this.isSitting() || this.isSleeping())) {
            this.setSitting(false);
            this.setSleeping(false);
        }
        if (this.getAttackTarget() != null) {
            if (getAttackBounds().intersects(this.getAttackTarget().getEntityBoundingBox())) {
                if (!this.isInWater()) {
                    if (this.getAnimation() != ATTACK_ANIMATION) {
                        this.setAnimation(ATTACK_ANIMATION);
                    }
                    if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() > 5) {
                        this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                    }
                    this.attackEntityAsMob(this.getAttackTarget());
                } else {
                    this.getAttackTarget().startRiding(this);
                    if (this.getAnimation() != ROLL_ANIMATION) {
                        this.setAnimation(ROLL_ANIMATION);
                    }
                }
            }
        }
        if (this.isInWaterMaterial()) {
            this.setSwimming(true);
        } else {
            this.setSwimming(false);
        }
    }

    public int getAttackLength() {
        return 15;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (this.isInWater()) {
            return false;
        }
        return true;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[] { SPEAK_ANIMATION, ATTACK_ANIMATION, ROLL_ANIMATION };
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SWIMMING, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Swimming", this.isSwimming);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSwimming(compound.getBoolean("Swimming"));

    }

    public boolean isSwimming() {
        if (world.isRemote) {
            boolean isSwimming = this.dataManager.get(SWIMMING);
            this.isSwimming = isSwimming;
            return isSwimming;
        }

        return isSwimming;
    }

    public void setSwimming(boolean swimming) {
        this.dataManager.set(SWIMMING, swimming);
        if (!world.isRemote) {
            this.isSwimming = swimming;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isChild() ? FASoundRegistry.SARCOSUCHUS_BABY_LIVING : FASoundRegistry.SARCOSUCHUS_LIVING;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.SARCOSUCHUS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.SARCOSUCHUS_DEATH;
    }
    @Override
    public boolean canBeRidden() {
        return true;
    }
}
