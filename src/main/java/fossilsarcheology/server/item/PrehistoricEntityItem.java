package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.item.Item;

public class PrehistoricEntityItem extends Item implements DefaultRenderedItem {
    protected PrehistoricEntityType type;
    public String resourceName = "";
    public PrehistoricEntityItem(String name, PrehistoricEntityType type) {
        super();
        this.type = type;
        this.setUnlocalizedName(name + type.friendlyName);
        this.resourceName = type.resourceName + "_" + name;
        this.setCreativeTab(FATabRegistry.ITEMS);
    }
}
