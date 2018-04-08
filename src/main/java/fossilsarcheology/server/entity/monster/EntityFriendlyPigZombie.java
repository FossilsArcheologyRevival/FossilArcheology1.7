package fossilsarcheology.server.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityFriendlyPigZombie extends EntityTameable {

    public EntityFriendlyPigZombie(World var1) {
        super(var1);
        this.setSize(0.5F, 1.9F);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.setTamed(false);
    }

    @Override
    public void onKillEntity(EntityLivingBase entity) {
        super.onKillEntity(entity);
        sendMessageToOwner("pigman.kill");
    }

    @Override
    public void setDead() {
        if (!this.world.isRemote && isTamed() && getHealth() > 0) {
            return;
        }
        super.setDead();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damage, float f) {
        if (this.isEntityInvulnerable(damage)) {
            return false;
        } else {
            Entity entity = damage.getTrueSource();
            this.aiSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                f = (f + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(damage, f);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5);

        if (flag) {
            int i = this.world.getDifficulty().getDifficultyId();

            if (this.getHeldItemMainhand() == null && this.isBurning() && this.rand.nextFloat() < (float) i * 0.3F) {
                entity.setFire(2 * i);
            }
        }

        return flag;
    }

    protected void playStepSound(BlockPos pos, Block blockIn){
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);

        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        }
    }



    public void sendMessageToOwner(String words) {
        if (this.getOwner() instanceof EntityPlayer) {
            ((EntityPlayer) this.getOwner()).sendStatusMessage(new TextComponentString(I18n.format(words)), false);
        }
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase entity, EntityLivingBase thisMobsOwner) {

        if (entity instanceof EntityWolf) {
            EntityWolf entitywolf = (EntityWolf) entity;
            if (entitywolf.isTamed() && entitywolf.getOwner() == thisMobsOwner) {
                return false;
            }
        }
        if (entity instanceof EntityFriendlyPigZombie) {
            EntityFriendlyPigZombie entitywfpz = (EntityFriendlyPigZombie) entity;

            if (entitywfpz.isTamed() && entitywfpz.getOwner() == thisMobsOwner) {
                return false;
            }
        }

        return !(entity instanceof EntityPlayer && thisMobsOwner instanceof EntityPlayer && !((EntityPlayer) thisMobsOwner).canAttackPlayer((EntityPlayer) entity)) && (!(entity instanceof EntityHorse) || !((EntityHorse) entity).isTame());

    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_PIG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource soucre)
    {
        return SoundEvents.ENTITY_ZOMBIE_PIG_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_PIG_DEATH;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand){
        if (this.isOwner(player) && !this.world.isRemote) {
            this.aiSit.setSitting(!this.isSitting());
            this.isJumping = false;
            this.getNavigator().clearPath();
            this.setAttackTarget(null);
            this.setAttackTarget(null);
            sendMessageToOwner("pigman.sit");
            return true;
        }
        return false;
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageble) {
        return null;
    }
}
