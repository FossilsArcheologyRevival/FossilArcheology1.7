package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class VolcanicRockBlock extends Block implements DefaultRenderedItem {
	public VolcanicRockBlock() {
		super(Material.ROCK);
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.GROUND);
		setUnlocalizedName("volcanic_brick");
		setCreativeTab(FATabRegistry.BLOCKS);
	}
}
