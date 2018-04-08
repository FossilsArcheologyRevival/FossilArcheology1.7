package fossilsarcheology.server.entity.prehistoric;

import fossilsarcheology.server.entity.EntityFishBase;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class EntityNautilus extends EntityFishBase {

    public float shellProgress;
    public boolean isInShell;
    public int ticksToShell;
    private static final DataParameter<Boolean> ISINSHELL = EntityDataManager.<Boolean>createKey(EntityNautilus.class, DataSerializers.BOOLEAN);
    public EntityNautilus(World world) {
        super(world, PrehistoricEntityType.NAUTILUS);
        this.setSize(0.8F, 1F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ISINSHELL, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("InShell", this.isInShell);
        compound.setInteger("ShellTick", this.ticksToShell);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        this.setInShell(compound.getBoolean("InShell"));
        this.ticksToShell = compound.getInteger("ShellTick");
    }

    public void setInShell(boolean inShell) {
        this.dataManager.set(ISINSHELL, inShell);
        if (!world.isRemote) {
            this.isInShell = inShell;
        }
    }

    public boolean isInShell() {
        if (world.isRemote) {
            boolean isSleeping = this.dataManager.get(ISINSHELL);
            this.isInShell = isSleeping;
            return isSleeping;
        }
        return isInShell;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        boolean inshell = isInShell();
        if (inshell && shellProgress < 20.0F) {
            shellProgress += 0.5F;
        } else if (!inshell && shellProgress > 0.0F) {
            shellProgress -= 0.5F;
        }
        if (ticksToShell > 0) {
            ticksToShell--;
        }
        if (!this.world.isRemote) {
            if (isThereNearbyMobs() && this.ticksToShell == 0 || !this.isInWater() && this.onGround && this.ticksToShell == 0) {
                if (!this.isInShell()) {
                    this.setInShell(true);
                //    Revival.NETWORK_WRAPPER.sendToAll(new MessageUpdateNautilus(this.getEntityId(), true));
                }
            } else {
                if (this.isInShell()) {
                    this.setInShell(false);
                //    Revival.NETWORK_WRAPPER.sendToAll(new MessageUpdateNautilus(this.getEntityId(), false));
                }
            }
        }
    }

    @Override
    protected void dropFewItems(boolean burning, int i) {
        this.dropItem(FAItemRegistry.SHELL, 1);
    }

    public boolean isThereNearbyMobs() {
        Entity targetEntity;
        EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(this);
        List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D), null);
        Collections.sort(list, theNearestAttackableTargetSorter);

        if (list.isEmpty()) {
            return false;
        } else {
            for (Entity entity : list) {
                if (isAScaryAnimal(entity)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isAScaryAnimal(Entity entity) {
        if (entity instanceof EntityPlayer) {
            return true;
        }
        /*if (entity instanceof EntityPrehistoric) {
            return ((EntityPrehistoric) entity).type.diet.getFearIndex() >= 2;
        }*/
        return entity.width >= 1.2;
    }

    @Override
    public boolean attackEntityFrom(DamageSource dmg, float f) {
        if (f > 0 && this.isInShell() && dmg.getTrueSource() != null) {
            this.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1, this.getRNG().nextFloat() + 0.8F);
            if(this.getRidingEntity() != null){
                return super.attackEntityFrom(dmg, f);
            }
            return false;
        }
        if (!this.isInShell()) {
            this.setInShell(true);
           // Revival.NETWORK_WRAPPER.sendToAll(new MessageUpdateNautilus(this.getEntityId(), true));
        }
        return super.attackEntityFrom(dmg, f);
    }

    @Override
    public String getTexture() {
        return "fossil:textures/model/fish/nautilus.png";
    }

    @Override
    protected double getSwimSpeed() {
        return 0.25;
    }
}
