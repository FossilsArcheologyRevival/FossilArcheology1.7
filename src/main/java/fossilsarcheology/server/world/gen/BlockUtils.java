package fossilsarcheology.server.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockUtils {

	@Deprecated
	public static void setBlock(World world, int x, int y, int z, Block block, int meta, int flags) {
		BlockPos pos = new BlockPos(x, y, z);
		world.setBlockState(pos, block.getStateFromMeta(meta), flags);
	}

	@Deprecated
	public static Block getBlock(World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		return world.getBlockState(pos).getBlock();
	}

	@Deprecated
	public static int getBlockMeta(World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		return world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
	}

	@Deprecated
	public static void setChest(World world, int x, int y, int z, Block block, int meta, int flags, ResourceLocation loot) {
		BlockPos pos = new BlockPos(x, y, z);
		world.setBlockState(pos, block.getStateFromMeta(meta), 3);
		if (world.getBlockState(pos).getBlock() instanceof BlockChest) {
			TileEntity tileentity1 = world.getTileEntity(pos);
			if (tileentity1 instanceof TileEntityChest && !((TileEntityChest) tileentity1).isInvalid()) {
				((TileEntityChest) tileentity1).setLootTable(loot, new Random().nextLong());
			}
		}
	}

}
