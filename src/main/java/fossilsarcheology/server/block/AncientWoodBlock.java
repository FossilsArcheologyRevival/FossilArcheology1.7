package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AncientWoodBlock extends Block implements DefaultRenderedItem {
	public AncientWoodBlock() {
		super(Material.WOOD);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		setHardness(2.0F);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName("ancient_wood");
	}
}
