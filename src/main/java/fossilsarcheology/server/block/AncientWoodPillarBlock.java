package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AncientWoodPillarBlock extends BlockRotatedPillar implements DefaultRenderedItem {
	protected AncientWoodPillarBlock() {
		super(Material.WOOD);
		this.setCreativeTab(FATabRegistry.BLOCKS);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.4F);
		this.setResistance(1.0F);

		this.setUnlocalizedName("ancient_wood_pillar");
	}
}
