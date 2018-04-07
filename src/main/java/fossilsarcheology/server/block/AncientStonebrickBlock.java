package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AncientStonebrickBlock extends Block implements DefaultRenderedItem {
	public AncientStonebrickBlock() {
		super(Material.ROCK);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setHardness(1.5F);
		this.setUnlocalizedName("ancient_stone_brick");
	}
}
