package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AncientStoneBlock extends Block implements DefaultRenderedItem {
	public AncientStoneBlock() {
		super(Material.ROCK);
		this.setHardness(1.5F);
		this.setUnlocalizedName("ancient_stone");
		this.setCreativeTab(FATabRegistry.BLOCKS);
	}
}

