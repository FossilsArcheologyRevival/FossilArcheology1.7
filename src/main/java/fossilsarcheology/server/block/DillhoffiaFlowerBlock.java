package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;

public class DillhoffiaFlowerBlock extends BlockBush implements DefaultRenderedItem {

	public DillhoffiaFlowerBlock() {
		setCreativeTab(FATabRegistry.BLOCKS);
		setUnlocalizedName("dillhoffia_flower");
		setSoundType(SoundType.PLANT);
	}

}
