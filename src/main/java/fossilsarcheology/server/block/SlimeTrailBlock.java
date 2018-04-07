package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockRail;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class SlimeTrailBlock extends BlockRail implements DefaultRenderedItem {
	public SlimeTrailBlock() {
		this.setHardness(0.3F);
		this.setUnlocalizedName("slimetrail");
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setSoundType(SoundType.SLIME);
		this.setHarvestLevel("shovel", 0);
		this.slipperiness = 1.12F;
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return rand.nextInt(3) == 0 ? Items.SLIME_BALL : null;
	}

}

