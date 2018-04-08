package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityLiopleurodon extends EntityPrehistoricSwimming {

    public static Animation SHAKE_ANIMATION = Animation.create(50);

    public EntityLiopleurodon(World world) {
        super(world, PrehistoricEntityType.LIOPLEURODON, 2, 12, 10, 45, 0.3, 0.4);
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(3, new DinoAIRiding(this, 1.5));
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
        this.setActualSize(2.25F, 0.7F);
        minSize = 0.8F;
        maxSize = 1.8F;
        teenAge = 6;
        developsResistance = true;
        breaksBlocks = false;
        hasBabyTexture = false;
        pediaScale = 30;
        this.ridingY = 1.3F;
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

        return Items.STICK;
    }

    @Override
    public int getAdultAge() {
        return 13;
    }

    @Override
    public double swimSpeed() {
        return 2.5D;
    }

    public int getMaxHunger() {
        return 125;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getAttackTarget() != null) {
            if (getAttackBounds().intersects(this.getAttackTarget().getEntityBoundingBox())) {
                if (!this.isEntitySmallerThan(this.getAttackTarget(), 1.6F * (this.getAgeScale() / this.maxSize))) {
                    if (this.getAnimation() != ATTACK_ANIMATION) {
                        this.setAnimation(ATTACK_ANIMATION);
                    }
                    if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() > 5) {
                        this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue());
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
                    riddenByEntity.attackEntityFrom(DamageSource.causeMobDamage(this), Math.max(((EntityLivingBase) riddenByEntity).getMaxHealth(), 100));
                    this.onKillEntity((EntityLivingBase) riddenByEntity);
                }
            }

            float modTick_1 = 5 * MathHelper.sin((float) (Math.PI + (this.getAnimationTick() * 0.275F)));
            rotationYaw = renderYawOffset;
            float radius = 0.5F * (0.7F * getAgeScale()) * -3;
            float angle = (0.01745329251F * this.renderYawOffset) + 3.15F + (modTick_1 * 1.75F) * 0.05F;
            double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
            double extraZ = (double) (radius * MathHelper.cos(angle));
            double extraY = 0.15F * (getAgeScale());
            super.updateRidden();
            riddenByEntity.setPosition(this.posX + extraX, this.posY + extraY, this.posZ + extraZ);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    @Override
    public boolean canBeRidden() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? FASoundRegistry.LIOPLEURODON_LIVING_INSIDE : FASoundRegistry.LIOPLEURODON_LIVING_OUTSIDE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.LIOPLEURODON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.LIOPLEURODON_DEATH;
    }
}