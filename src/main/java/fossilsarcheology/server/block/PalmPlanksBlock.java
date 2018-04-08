package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PalmPlanksBlock extends Block implements DefaultRenderedItem {

	public PalmPlanksBlock() {
		super(Material.WOOD);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName("palm_planks");
		this.setCreativeTab(FATabRegistry.BLOCKS);
	}

}

