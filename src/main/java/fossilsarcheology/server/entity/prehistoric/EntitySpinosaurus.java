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

public class EntitySpinosaurus extends EntityPrehistoricSwimming {
    private static final DataParameter<Boolean> SWIMMING = EntityDataManager.<Boolean>createKey(EntitySpinosaurus.class, DataSerializers.BOOLEAN);

    public Animation SHAKE_ANIMATION = Animation.create(65);
    private boolean isSwimming;
    public float swimProgress;

    public EntitySpinosaurus(World world) {
        super(world, PrehistoricEntityType.SPINOSAURUS, 2, 14, 15, 86, 0.25, 0.3);
        this.setActualSize(1.5F, 1.0F);
        isAmphibious = true;
        FISH_ANIMATION = Animation.create(40);
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(3, new DinoAIRiding(this, 1.5D));
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
        minSize = 0.6F;
        maxSize = 4.75F;
        teenAge = 5;
        developsResistance = true;
        breaksBlocks = true;
        this.hasBabyTexture = true;
        this.ridingY = 1.21F;
        this.ridingXZ = -0.3F;
        this.pediaScale = 26F;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SWIMMING, false);
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

    @Override
    public int getTailSegments() {
        return 3;
    }

    public int getMaxHunger() {
        return 175;
    }

    @Override
    public double swimSpeed() {
        return 1;
    }

    @Override
    public void updateRidden() {
        if(this.getRidingPlayer() != null && this.func_152114_e(this.getRidingPlayer())){
            super.updateRidden();
            return;
        }
        if (this.getPassengers().get(0) != null && this.getPassengers().get(0) instanceof EntityLivingBase) {
            Entity riddenByEntity = this.getPassengers().get(0);

            if((this.getAnimationTick() > 55 || this.getAnimation() == NO_ANIMATION) && riddenByEntity != null){
                if(riddenByEntity instanceof EntityToyBase){
                    ((EntityToyBase) riddenByEntity).dismountEntity(this);
                    this.setAttackTarget(null);
                    this.doPlayBonus(((EntityToyBase) riddenByEntity).toyBonus);
                    riddenByEntity.setPosition(posX, posY, posZ);
                    riddenByEntity = null;
                    return;
                }else{
                    riddenByEntity.attackEntityFrom(DamageSource.causeMobDamage(this), Math.max(((EntityLivingBase) riddenByEntity).getMaxHealth(), 100));
                    this.onKillEntity((EntityLivingBase) riddenByEntity);
                }
            }
            riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + riddenByEntity.getYOffset(), this.posZ);
            float modTick_0 = this.getAnimationTick() - 15;
            float modTick_1 = this.getAnimationTick() > 15 ? 6 * MathHelper.sin((float) (Math.PI + (modTick_0 * 0.275F))) : 0;
            float modTick_2 = this.getAnimationTick() > 15 ? 15 : this.getAnimationTick();
            this.rotationYaw *= 0;
            riddenByEntity.rotationYaw = this.rotationYaw + this.rotationYawHead + 180;
            rotationYaw = renderYawOffset;
            float radius = 0.6F * (0.7F * getAgeScale()) * -3;
            float angle = (0.01745329251F * this.renderYawOffset) + 3.15F + (modTick_1 * 1.75F) * 0.05F;
            double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
            double extraZ = (double) (radius * MathHelper.cos(angle));
            double extraY = 0.8F * (getAgeScale() + (modTick_1 * 0.05) + (modTick_2 * 0.15) - 2);
            super.updateRidden();
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
        if(this.isSwimming() && (this.isSitting() || this.isSleeping())){
            this.setSitting(false);
            this.setSleeping(false);
        }
        if (this.getAttackTarget() != null) {
            if (getAttackBounds().intersects(this.getAttackTarget().getEntityBoundingBox())) {
                this.attackEntityAsMob(this.getAttackTarget());
                if (!this.isEntitySmallerThan(this.getAttackTarget(), 2F * (this.getAgeScale() / this.maxSize))) {
                    if (this.getAnimation() != ATTACK_ANIMATION) {
                        this.setAnimation(ATTACK_ANIMATION);
                    }
                    if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() > 10) {
                        this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue());
                    }
                } else {
                    this.getAttackTarget().startRiding(this);
                    if (this.getAnimation() != SHAKE_ANIMATION) {
                        this.setAnimation(SHAKE_ANIMATION);
                    }
                }
            }
        }
        if (this.isInWaterMaterial()) {
            this.setSwimming(true);
        }else{
            this.setSwimming(false);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if(this.isInWater()){
            return false;
        }

        return true;
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

    public int getAttackLength(){
        return 25;
    }


    @Override
    public Animation[] getAnimations() {
        return new Animation[] { SPEAK_ANIMATION, ATTACK_ANIMATION, SHAKE_ANIMATION, FISH_ANIMATION };
    }

    @Override
    public boolean canBeRidden() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FASoundRegistry.SPINOSAURUS_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.SPINOSAURUS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.SPINOSAURUS_DEATH;
    }
}
