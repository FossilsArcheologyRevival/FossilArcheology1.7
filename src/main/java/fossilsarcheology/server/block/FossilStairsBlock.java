package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class FossilStairsBlock extends BlockStairs implements DefaultRenderedItem {

	protected FossilStairsBlock(IBlockState modelState, String name) {
		super(modelState);
		this.setLightOpacity(0);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
