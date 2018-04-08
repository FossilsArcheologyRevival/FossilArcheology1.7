package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class FossilRecordItem extends ItemRecord implements DefaultRenderedItem {

	public final String recordName;


	public FossilRecordItem(String string, SoundEvent sound, String name) {
		super(name, sound);
		this.recordName = string;
		this.maxStackSize = 1;
		setCreativeTab(FATabRegistry.ITEMS);
		this.setUnlocalizedName(name);

	}
}