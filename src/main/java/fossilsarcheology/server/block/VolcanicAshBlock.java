package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class VolcanicAshBlock extends Block implements DefaultRenderedItem {
	public VolcanicAshBlock(String type) {
		super(Material.CLOTH);
		this.blockParticleGravity = -0.15F;
		setHardness(0.2F);
		setSoundType(SoundType.GROUND);
		this.setUnlocalizedName("volcanic_" + type);
		setCreativeTab(FATabRegistry.BLOCKS);
	}
}