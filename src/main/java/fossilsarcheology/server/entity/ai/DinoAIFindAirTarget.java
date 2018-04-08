package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoricFlying;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;


public class DinoAIFindAirTarget extends EntityAIBase {
    private EntityPrehistoricFlying prehistoric;
    private BlockPos target;
    private World theWorld;

    public DinoAIFindAirTarget(EntityPrehistoricFlying prehistoric) {
        this.prehistoric = prehistoric;
        this.theWorld = prehistoric.world;
    }

    public boolean shouldExecute() {
        if (!prehistoric.isAdult()) {
            return false;
        }
        if (target!= null && !prehistoric.isDirectPathBetweenPoints(new Vec3d(prehistoric.getPosition().getX(), prehistoric.getPosition().getY(), prehistoric.getPosition().getZ()), new Vec3d(target.getX(), target.getY(), target.getZ()))) {
            prehistoric.currentTarget = null;
        }

        if (!prehistoric.isFlying() && !prehistoric.onGround) {
            prehistoric.currentTarget = null;
            return false;
        }

        if (prehistoric.currentTarget != null) {
            return false;
        } else {
            BlockPos pos = this.findAirTarget();
            if (pos == null) {
                return false;
            } else {
                this.target = pos;
                return true;
            }
        }
    }

    public boolean continueExecuting() {
        return prehistoric.currentTarget != null;
    }

    public void startExecuting() {
        this.prehistoric.currentTarget = target;
    }

    public BlockPos findAirTarget() {
        Random random = this.prehistoric.getRNG();

        if (prehistoric.getAttackTarget() == null) {
            for (int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = new BlockPos((int) this.prehistoric.posX + ((6 + random.nextInt(10)) * (random.nextBoolean() ? -1 : 1)), (int) this.prehistoric.posY + 5 + ((random.nextInt(6)) * (random.nextBoolean() ? -1 : 1)), (int) this.prehistoric.posZ + ((6 + random.nextInt(10)) * (random.nextBoolean() ? -1 : 1)));
                BlockPos blockpos1ground = new BlockPos((int) this.prehistoric.posX + ((3 + random.nextInt(4)) * (random.nextBoolean() ? -1 : 1)), (int) this.prehistoric.posY + 3, (int) this.prehistoric.posZ + ((3 + random.nextInt(4)) * (random.nextBoolean() ? -1 : 1)));
                if (prehistoric.getBlockUnder() != Blocks.AIR) {
                    return blockpos1ground;
                } else {
                    if (prehistoric.world.getBlockState(blockpos1).getMaterial() == Material.AIR) {
                        return blockpos1;
                    }
                }
            }
        } else {
            BlockPos blockpos1 = new BlockPos((int) prehistoric.getAttackTarget().posX, (int) prehistoric.getAttackTarget().posY, (int) prehistoric.getAttackTarget().posZ);
            if (prehistoric.world.getBlockState(blockpos1).getMaterial() == Material.AIR) {
                return blockpos1;
            }
        }

        return null;
    }

}