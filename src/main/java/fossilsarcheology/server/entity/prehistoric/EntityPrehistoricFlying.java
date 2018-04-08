package fossilsarcheology.server.entity.prehistoric;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityPrehistoricFlying extends EntityPrehistoric {

    public BlockPos currentTarget;
    public static final int FLYING_INDEX = 29;
    private boolean isFlying;
    public float flyProgress;
    private int ticksFlying;
    private static final DataParameter<Boolean> FLYING = EntityDataManager.<Boolean>createKey(EntityPrehistoricFlying.class, DataSerializers.BOOLEAN);

    public EntityPrehistoricFlying(World world, PrehistoricEntityType type, double baseDamage, double maxDamage, double baseHealth, double maxHealth, double baseSpeed, double maxSpeed) {
        super(world, type, baseDamage, maxDamage, baseHealth, maxHealth, baseSpeed, maxSpeed);
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) this.height * 0.5D, vec2.z), false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FLYING, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Flying", this.isFlying);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setFlying(compound.getBoolean("Flying"));

    }

    public boolean isFlying() {
        if (world.isRemote) {
            boolean isFlying = this.dataManager.get(FLYING);
            this.isFlying = isFlying;
            return isFlying;
        }

        return isFlying;
    }

    @Override
    public void jump() {
        super.jump();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        boolean flying = isFlying();
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
        if (flying && flyProgress < 20.0F) {
            flyProgress += 0.5F;
            if (sitProgress != 0)
                sitProgress = sleepProgress = 0F;
        } else if (!flying && flyProgress > 0.0F) {
            flyProgress -= 0.5F;
            if (sitProgress != 0)
                sitProgress = sleepProgress = 0F;
        }
        if (!this.isFlying() && !this.isMovementBlocked() && rand.nextInt(400) == 0 && !this.world.isRemote && this.isAdult() && this.getControllingPassenger() == null && this.onGround) {
            this.setFlying(true);
        }
        if(ticksFlying > 80 && this.onGround){
            this.setFlying(false);
        }
        if(!this.isFlying() && this.currentTarget != null && !this.onGround){
            this.setFlying(true);
        }
        if (this.isFlying() && getAttackTarget() == null) {
            flyAround();
            ticksFlying++;
        } else if (getAttackTarget() != null) {
            flyTowardsTarget();
        }
    }

    public boolean canSleep() {
        if(super.canSleep() && this.isFlying() && this.onGround){
            this.setFlying(false);
            return super.canSleep();
        }
        return super.canSleep();
    }

    public void setFlying(boolean flying) {
        this.dataManager.set(FLYING, flying);
        if (!world.isRemote) {
            this.isSleeping = flying;
        }
    }

    public void flyAround() {
        if (currentTarget != null) {
            if (!isDirectPathBetweenPoints(new Vec3d(this.posY, this.posY, this.posZ), new Vec3d(currentTarget.getX(), currentTarget.getY(), currentTarget.getZ()))) {
                currentTarget = null;
            }
            if (!isTargetInAir() || this.getDistance(currentTarget.getX(), currentTarget.getY(), currentTarget.getZ()) < 3F || ticksFlying > 6000) {
                currentTarget = null;
            }
            flyTowardsTarget();
        }
    }

    public void flyTowardsTarget() {
        if (currentTarget != null && isTargetInAir() && this.isFlying() && this.getDistanceSquared(new Vec3d(currentTarget.getX(), this.posY, currentTarget.getY())) > 3) {
            double targetX = currentTarget.getX() + 0.5D - posX;
            double targetY = currentTarget.getY() + 0.5D - posY;
            double targetZ = currentTarget.getZ() + 0.5D - posZ;
            motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.100000000372529 * getFlySpeed();
            motionY += (Math.signum(targetY) * 0.5D - motionY) * 0.100000000372529 * getFlySpeed();
            motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.100000000372529 * getFlySpeed();
            float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
            float rotation = MathHelper.wrapDegrees(angle - rotationYaw);
            moveForward = 0.5F;
            rotationYaw += rotation;
        } else {
            this.currentTarget = null;
        }
    }

    protected boolean isTargetInAir() {
        return currentTarget != null && (world.getBlockState(currentTarget).getMaterial() == Material.AIR && world.getBlockState(currentTarget.up()).getMaterial() == Material.AIR);
    }

    public float getDistanceSquared(Vec3d vec) {
        float f = (float) (this.posX - vec.x);
        float f1 = (float) (this.posY - vec.y);
        float f2 = (float) (this.posZ - vec.z);
        return f * f + f1 * f1 + f2 * f2;
    }

    protected abstract double getFlySpeed();
}