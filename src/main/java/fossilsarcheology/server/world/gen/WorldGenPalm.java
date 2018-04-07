package fossilsarcheology.server.world.gen;

import fossilsarcheology.server.block.FABlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPalm extends WorldGenerator {
	private static final IBlockState LOG = FABlockRegistry.PALM_LOG.getDefaultState();
	private static final IBlockState LEAF = FABlockRegistry.PALM_LEAVES.getDefaultState();


	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		Block j1 = world.getBlockState(pos.down()).getBlock();

		if (j1 != Blocks.GRASS && j1 != Blocks.DIRT || pos.getY() >= 128 - 12 - 1) {
			return false;
		}

		// int deltaY = 5;
		int deltaY = rand.nextInt(10);

		for (int y = (pos.getY() - 1) + 1; y <= pos.getY() + 10 + deltaY; y++) {
			world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ()), LOG);
		}

		int var4 = pos.getY() - 5 + deltaY;
		world.setBlockState(new BlockPos(pos.getX(), var4 + 16, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 1, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 2, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 3, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 4, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 5, var4 + 14, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() + 1), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() + 2), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() + 3), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() + 4), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 14, pos.getZ() + 5), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 1, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 2, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 3, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 4, var4 + 15, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 5, var4 + 14, pos.getZ()), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() - 1), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() - 2), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() - 3), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 15, pos.getZ() - 4), LEAF);
		world.setBlockState(new BlockPos(pos.getX(), var4 + 14, pos.getZ() - 5), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 1, var4 + 15, pos.getZ() + 1), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 1, var4 + 15, pos.getZ() - 1), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 1, var4 + 15, pos.getZ() + 1), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 1, var4 + 15, pos.getZ() - 1), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 2, var4 + 15, pos.getZ() + 2), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 2, var4 + 15, pos.getZ() - 2), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 2, var4 + 15, pos.getZ() + 2), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 2, var4 + 15, pos.getZ() - 2), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 3, var4 + 14, pos.getZ() + 3), LEAF);
		world.setBlockState(new BlockPos(pos.getX() + 3, var4 + 14, pos.getZ() - 3), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 3, var4 + 14, pos.getZ() + 3), LEAF);
		world.setBlockState(new BlockPos(pos.getX() - 3, var4 + 14, pos.getZ() - 3), LEAF);
		return true;
	}
}
