package fossilsarcheology.server.event;

import fossilsarcheology.Revival;
import fossilsarcheology.client.gui.FAMainMenuGUI;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FossilMainMenuEvent {
	@SubscribeEvent
	public void onGuiOpened(GuiOpenEvent event) {
		if (Revival.CONFIG.customMainMenu && event.getGui() instanceof GuiMainMenu && !(event.getGui() instanceof FAMainMenuGUI)) {
			event.setGui(new FAMainMenuGUI());
		}
	}
}