package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.util.FoodMappings;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DinoAIEatBlocks extends EntityAIBase {
	private BlockPos targetBlock;
	private EntityPrehistoric prehistoric;
	private double speed;
	private BlockSorter targetSorter;

	public DinoAIEatBlocks(EntityPrehistoric prehistoric, double speed) {
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
					if (FoodMappings.INSTANCE.getBlockFoodAmount(prehistoric.world.getBlockState(new BlockPos(x, y, z)).getBlock(), prehistoric.type.diet) > 0) {
						allBlocks.add(new BlockPos(x, y, z));
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
		return true;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
	}

	@Override
	public void updateTask() {
		if (targetBlock != null) {
			Block block = prehistoric.world.getBlockState(this.targetBlock).getBlock();
			if (FoodMappings.INSTANCE.getBlockFoodAmount(block, prehistoric.type.diet) > 0) {
				double d0 = this.getDistance(this.targetBlock.getX() + 0.5D, this.targetBlock.getY(), this.targetBlock.getZ() + 0.5D);
				if (d0 * d0 < 6) {
					prehistoric.setHunger(Math.min(prehistoric.getMaxHunger(), prehistoric.getHunger() + FoodMappings.INSTANCE.getBlockFoodAmount(block, prehistoric.type.diet)));
					prehistoric.setHealth(Math.min(prehistoric.getMaxHealth(), (int) (prehistoric.getHealth() + FoodMappings.INSTANCE.getBlockFoodAmount(block, prehistoric.type.diet) / 10)));
					// prehistoric.doFoodEffect(Item.getItemFromBlock(block));
					prehistoric.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1, 1);
					prehistoric.world.destroyBlock(this.targetBlock, false);
					targetBlock = null;
					resetTask();
					return;
				} else {
					if (prehistoric.getNavigator().noPath()) {
						this.prehistoric.getNavigator().tryMoveToXYZ(this.targetBlock.getX() + 0.5D, this.targetBlock.getY(), this.targetBlock.getZ() + 0.5D, 1D);
					}
				}
			}
		}
	}

	public double getDistance(double x, double y, double z) {
		double d3 = prehistoric.posX - x;
		double d4 = prehistoric.posY + prehistoric.getEyeHeight() - y;
		double d5 = prehistoric.posZ - z;
		return (double) MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
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
			double d1 = entity.posY + entity.getEyeHeight() - vec3.getY();
			double d2 = entity.posZ - vec3.getZ();
			return d0 * d0 + d1 * d1 + d2 * d2;
		}
	}
}
