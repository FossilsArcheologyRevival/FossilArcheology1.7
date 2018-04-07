package fossilsarcheology.server.api;

import net.minecraft.block.properties.IProperty;

public interface IgnoreRenderProperty {
	IProperty<?>[] getIgnoredProperties();
}
