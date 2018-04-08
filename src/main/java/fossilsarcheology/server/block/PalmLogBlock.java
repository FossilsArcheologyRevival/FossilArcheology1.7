package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PalmLogBlock extends BlockRotatedPillar implements DefaultRenderedItem {
	protected PalmLogBlock() {
		super(Material.WOOD);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.4F);
		this.setResistance(1.0F);
		this.setUnlocalizedName("palm_log");
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		int i = 4;
		int j = 5;
		if (world.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
			for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
				IBlockState iblockstate = world.getBlockState(blockpos);
				if (iblockstate.getBlock().isLeaves(iblockstate, world, blockpos)) {
					iblockstate.getBlock().beginLeavesDecay(iblockstate, world, blockpos);
				}
			}
		}
	}
}
