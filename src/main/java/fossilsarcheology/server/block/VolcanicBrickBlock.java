package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class VolcanicBrickBlock extends Block implements DefaultRenderedItem {
	public VolcanicBrickBlock() {
		super(Material.ROCK);
		setHardness(5.0F);
		setSoundType(SoundType.GROUND);
		this.setUnlocalizedName("volcanic_brick");
		setCreativeTab(FATabRegistry.BLOCKS);
	}
}