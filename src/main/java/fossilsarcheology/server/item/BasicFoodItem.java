package fossilsarcheology.server.item;

import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.item.ItemFood;

public class BasicFoodItem extends ItemFood {
	public BasicFoodItem(int amount, float saturation, boolean isWolfFood, String name) {
		super(amount, saturation, isWolfFood);
		this.setCreativeTab(FATabRegistry.ITEMS);
		this.setUnlocalizedName(name);
	}
}
