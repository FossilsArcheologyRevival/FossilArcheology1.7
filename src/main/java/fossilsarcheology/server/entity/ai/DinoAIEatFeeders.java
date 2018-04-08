package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.block.FeederBlock;
import fossilsarcheology.server.block.entity.TileEntityFeeder;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DinoAIEatFeeders extends EntityAIBase {
    private BlockPos targetBlock;
    private EntityPrehistoric prehistoric;
    private double speed;
    private BlockSorter targetSorter;
    private int feedingTicks;

    public DinoAIEatFeeders(EntityPrehistoric prehistoric, double speed) {
        super();
        this.prehistoric = prehistoric;
        this.speed = speed;
        this.targetSorter = new BlockSorter(this, prehistoric);
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {

        if (prehistoric.getHunger() >= prehistoric.getMaxHunger()) {
            return false;
        }
        if (prehistoric.isMovementBlocked()) {
            return false;
        }
        if (prehistoric.getRNG().nextInt(1) != 0) {
            return false;
        }
        int radius = 16;
        List<BlockPos> allBlocks = new ArrayList<BlockPos>();
        for (int x = (int) (prehistoric.posX) - (radius / 2); x < (int) (prehistoric.posX) + (radius / 2); x++) {
            for (int y = (int) (prehistoric.posY) - (radius / 2); y < (int) (prehistoric.posY) + (radius / 2); y++) {
                for (int z = (int) (prehistoric.posZ) - (radius / 2); z < (int) (prehistoric.posZ) + (radius / 2); z++) {
                    if (prehistoric.world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof FeederBlock && prehistoric.world.getTileEntity(new BlockPos(x, y, z)) != null && prehistoric.world.getTileEntity(new BlockPos(x, y, z)) instanceof TileEntityFeeder) {
                        TileEntityFeeder feeder = (TileEntityFeeder) prehistoric.world.getTileEntity(new BlockPos(x, y, z));
                        if (!feeder.isEmpty(prehistoric.type)) {
                            allBlocks.add(new BlockPos(x, y, z));
                        }
                    }
                }
            }
        }
        if (allBlocks.isEmpty()) {
            return false;
        }
        Collections.sort(allBlocks, this.targetSorter);
        this.targetBlock = allBlocks.get(0);
        return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (targetBlock == null) {
            return false;
        }
        if (prehistoric.getHunger() >= prehistoric.getMaxHunger()) {

            return false;
        }

        if (prehistoric.isMovementBlocked()) {
            return false;
        }
        if (prehistoric.world.getBlockState(targetBlock) instanceof FeederBlock && prehistoric.world.getTileEntity(targetBlock) != null && prehistoric.world.getTileEntity(targetBlock) instanceof TileEntityFeeder) {
            return true;
        }
        return false;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void updateTask() {
        if (targetBlock != null) {
            if (prehistoric.world.getBlockState(targetBlock) instanceof FeederBlock && prehistoric.world.getTileEntity(targetBlock) != null && prehistoric.world.getTileEntity(targetBlock) instanceof TileEntityFeeder) {
                TileEntityFeeder feeder = (TileEntityFeeder) prehistoric.world.getTileEntity(targetBlock);
                double d0 = prehistoric.getDistance(this.targetBlock.getX(), this.targetBlock.getY(), this.targetBlock.getZ());
                if (d0 * d0 < 6) {
                    if (feedingTicks < 30 && !feeder.isEmpty(prehistoric.type)) {
                        feedingTicks++;
                        feeder.feedDinosaur(prehistoric);
                        prehistoric.setHealth(Math.min(prehistoric.getMaxHealth(), (int) (prehistoric.getHealth() + feedingTicks / 4)));
                        prehistoric.doFoodEffect();
                    } else {
                        feedingTicks = 0;
                        targetBlock = null;
                        resetTask();
                    }
                    return;
                } else {
                    if (prehistoric.getNavigator().noPath()) {
                        this.prehistoric.getNavigator().tryMoveToXYZ(this.targetBlock.getX() + 0.5D, this.targetBlock.getY(), this.targetBlock.getZ() + 0.5D, 1D);
                    }
                }
            }
        }
    }

    public class BlockSorter implements Comparator {
        final EntityAIBase ai;
        private Entity entity;

        public BlockSorter(EntityAIBase ai, Entity entity) {
            this.ai = ai;
            this.entity = entity;
        }

        public int compareBlocks(BlockPos var1, BlockPos var2) {
            double var3 = this.getDistanceSqToVec(var1);
            double var5 = this.getDistanceSqToVec(var2);
            return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
        }

        @Override
        public int compare(Object var1, Object var2) {
            return this.compareBlocks((BlockPos) var1, (BlockPos) var2);
        }

        public double getDistanceSqToVec(BlockPos vec3) {
            double d0 = entity.posX - vec3.getX();
            double d1 = entity.posY - vec3.getY();
            double d2 = entity.posZ - vec3.getZ();
            return d0 * d0 + d1 * d1 + d2 * d2;
        }
    }
}
