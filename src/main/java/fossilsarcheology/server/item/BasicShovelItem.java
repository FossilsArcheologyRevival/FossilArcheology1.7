package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.item.ItemSpade;

public class BasicShovelItem extends ItemSpade implements DefaultRenderedItem {

	public BasicShovelItem(ToolMaterial toolmaterial, String name) {
		super(toolmaterial);
		this.setUnlocalizedName(name);
		this.setCreativeTab(FATabRegistry.ITEMS);
	}
}
