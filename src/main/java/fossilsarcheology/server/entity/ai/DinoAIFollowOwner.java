package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.OrderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DinoAIFollowOwner extends EntityAIBase {
    private EntityPrehistoric prehistoric;
    private EntityLivingBase theOwner;
    World theWorld;
    private double speed;
    private PathNavigate petPathfinder;
    private int counter;
    float maxDist;
    float minDist;
    private boolean avoidsWater;

    public DinoAIFollowOwner(EntityPrehistoric prehistoric, double speed, float minDist, float maxDist) {
        this.prehistoric = prehistoric;
        this.theWorld = prehistoric.world;
        this.speed = speed;
        this.petPathfinder = prehistoric.getNavigator();
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.prehistoric.getOwner();

        if (entitylivingbase == null) {
            return false;
        } else if (this.prehistoric.currentOrder != OrderType.FOLLOW) {
            return false;
        } else if (this.prehistoric.getDistanceSq(entitylivingbase) < (double) (this.minDist * this.minDist)) {
            return false;
        } else {
            this.theOwner = entitylivingbase;
            return true;
        }
    }

    public boolean continueExecuting() {
        return !this.petPathfinder.noPath() && this.prehistoric.getDistanceSq(this.theOwner) > (double) (this.maxDist * this.maxDist) && !this.prehistoric.isSitting();
    }

    public void startExecuting() {
        this.counter = 0;
        this.avoidsWater = this.prehistoric.getPathPriority(PathNodeType.WATER) == 0;
        this.prehistoric.setPathPriority(PathNodeType.WATER, 0.0F);
        if (this.prehistoric.isSitting()) {
            this.prehistoric.setSitting(false);
        }
        if (this.prehistoric.isSleeping()) {
            this.prehistoric.setSleeping(false);
        }
    }

    public void resetTask() {
        this.theOwner = null;
        this.petPathfinder.clearPath();
        this.prehistoric.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    private boolean isEmptyBlock(BlockPos pos) {
        IBlockState iblockstate = this.theWorld.getBlockState(pos);
        return iblockstate.getMaterial() == Material.AIR ? true : !iblockstate.isFullCube();
    }

    public void updateTask() {
        this.prehistoric.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float) this.prehistoric.getVerticalFaceSpeed());

        if (!this.prehistoric.isSitting()) {
            if (--this.counter <= 0) {
                this.counter = 10;

                if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.speed)) {
                    if (!this.prehistoric.getLeashed()) {
                        if (this.prehistoric.getDistanceSq(this.theOwner) >= 144.0D) {
                            int i = MathHelper.floor(this.theOwner.posX) - 2;
                            int j = MathHelper.floor(this.theOwner.posZ) - 2;
                            int k = MathHelper.floor(this.theOwner.getEntityBoundingBox().minY);

                            for (int l = 0; l <= 4; ++l) {
                                for (int i1 = 0; i1 <= 4; ++i1) {
                                    if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.theWorld.getBlockState(new BlockPos(i + l, k - 1, j + i1)).isOpaqueCube() && this.isEmptyBlock(new BlockPos(i + l, k, j + i1)) && this.isEmptyBlock(new BlockPos(i + l, k + 1, j + i1))) {
                                        this.prehistoric.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.prehistoric.rotationYaw, this.prehistoric.rotationPitch);
                                        this.petPathfinder.clearPath();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
