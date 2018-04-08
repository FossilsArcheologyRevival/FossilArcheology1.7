package fossilsarcheology.server.item;

import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import net.minecraft.item.ItemFood;

public class ItemDinoMeat extends ItemFood {
	public String resourceName = "";

	public ItemDinoMeat(int amount, float saturation, boolean isWolfMeat, String name, PrehistoricEntityType type) {
		super(amount, saturation, isWolfMeat);
		this.resourceName = type.resourceName + "_" + name;
	}
}
