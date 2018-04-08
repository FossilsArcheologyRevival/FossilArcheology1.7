package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.*;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMosasaurus extends EntityPrehistoricSwimming {

    public static Animation SHAKE_ANIMATION = Animation.create(50);

    public EntityMosasaurus(World world) {
        super(world, PrehistoricEntityType.MOSASAURUS, 2, 9, 12, 70, 0.3, 0.35);
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(3, new DinoAIRiding(this, 1.0F));
        this.tasks.addTask(4, new DinoAIEatFeeders(this, 1));
        this.tasks.addTask(4, new DinoAIEatItems(this, 1));
        this.tasks.addTask(5, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new DinoAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, new Predicate<Entity>() {
            @Override
            public boolean apply(@Nullable Entity entity) {
                return entity instanceof EntityLivingBase;
            }
        }));
        this.hasBabyTexture = false;
        this.setActualSize(1.5F, 0.6F);
        minSize = 0.6F;
        maxSize = 4.1F;
        teenAge = 5;
        developsResistance = true;
        breaksBlocks = true;
        hasBabyTexture = false;
        pediaScale = 30;
        this.ridingY = 0.8F;
    }

    @Override
    public void setSpawnValues() {}

    @Override
    public Activity aiActivityType() {

        return Activity.DIURINAL;
    }

    @Override
    public Attacking aiAttackType() {

        return Attacking.DROWN;
    }

    @Override
    public Climbing aiClimbType() {

        return Climbing.NONE;
    }

    @Override
    public Following aiFollowType() {

        return Following.AGRESSIVE;
    }

    @Override
    public Jumping aiJumpType() {

        return Jumping.BASIC;
    }

    @Override
    public Response aiResponseType() {

        return Response.AGRESSIVE;
    }

    @Override
    public Stalking aiStalkType() {

        return Stalking.NONE;
    }

    @Override
    public Taming aiTameType() {

        return Taming.BLUEGEM;
    }

    @Override
    public Untaming aiUntameType() {

        return Untaming.NONE;
    }

    @Override
    public Moving aiMovingType() {

        return Moving.AQUATIC;
    }

    @Override
    public WaterAbility aiWaterAbilityType() {

        return WaterAbility.ATTACK;
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
        return 11;
    }

    @Override
    public double swimSpeed() {
        return 2.5D;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.getAttackTarget() != null) {
            if (getAttackBounds().intersects(this.getAttackTarget().getEntityBoundingBox())) {
                if (!this.isEntitySmallerThan(this.getAttackTarget(), 2F * (this.getAgeScale() / this.maxSize))) {
                    if (this.getAnimation() != ATTACK_ANIMATION) {
                        this.setAnimation(ATTACK_ANIMATION);
                    }
                    if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() > 5) {
                        this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue());
                    }
                    this.attackEntityAsMob(this.getAttackTarget());
                } else {
                    this.getAttackTarget().startRiding(this);
                    if (this.getAnimation() != SHAKE_ANIMATION) {
                        this.setAnimation(SHAKE_ANIMATION);
                    }
                }
            }
        }

    }

    @Override
    public void updateRidden() {
        if(this.getRidingPlayer() != null && this.func_152114_e(this.getRidingPlayer())){
            super.updateRidden();
            return;
        }
        if (this.getPassengers().get(0) != null && this.getPassengers().get(0) instanceof EntityLivingBase) {
            Entity riddenByEntity = this.getPassengers().get(0);
            if((this.getAnimationTick() > 55 || this.getAnimation() == NO_ANIMATION)){
                if(riddenByEntity instanceof EntityToyBase){
                    ((EntityToyBase) riddenByEntity).dismountEntity(this);
                    this.setAttackTarget(null);
                    this.doPlayBonus(((EntityToyBase) riddenByEntity).toyBonus);
                    riddenByEntity.setPosition(posX, posY, posZ);
                    riddenByEntity = null;
                    return;
                }else{
                    if(this.getPassengers().get(0) instanceof EntityLivingBase){
                        ((EntityLivingBase)riddenByEntity).attackEntityFrom(DamageSource.causeMobDamage(this), Math.max(((EntityLivingBase) riddenByEntity).getMaxHealth(), 100));

                    }
                    this.onKillEntity((EntityLivingBase) riddenByEntity);
                }
            }

            float modTick_1 = 5 * MathHelper.sin((float) (Math.PI + (this.getAnimationTick() * 0.275F)));
            rotationYaw = renderYawOffset;
            float radius = 0.5F * (0.7F * getAgeScale()) * -3;
            float angle = (0.01745329251F * this.renderYawOffset) + 3.15F + (modTick_1 * 1.75F) * 0.05F;
            double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
            double extraZ = (double) (radius * MathHelper.cos(angle));
            double extraY = 0.05F * (getAgeScale());
            riddenByEntity.setPosition(this.posX + extraX, this.posY + extraY, this.posZ + extraZ);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    @Override
    public int getMaxHunger() {
        return 125;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[] { SPEAK_ANIMATION, ATTACK_ANIMATION, SHAKE_ANIMATION };
    }

    @Override
    public boolean canBeRidden() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? FASoundRegistry.MOSASAURUS_LIVING_INSIDE : FASoundRegistry.MOSASAURUS_LIVING_OUTSIDE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.MOSASAURUS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.MOSASAURUS_DEATH;
    }
}
