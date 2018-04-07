package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.item.ItemPickaxe;

public class BasicPickaxeItem extends ItemPickaxe implements DefaultRenderedItem {

	public BasicPickaxeItem(ToolMaterial toolmaterial, String name) {
		super(toolmaterial);
		this.setUnlocalizedName(name);
		this.setCreativeTab(FATabRegistry.ITEMS);
	}
}
