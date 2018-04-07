package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;

public class DNAItem extends PrehistoricEntityItem implements DefaultRenderedItem {
	public DNAItem(PrehistoricEntityType type) {
		super("dna", type);
	}
}
