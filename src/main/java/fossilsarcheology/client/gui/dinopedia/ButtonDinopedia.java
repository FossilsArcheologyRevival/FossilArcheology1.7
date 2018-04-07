package fossilsarcheology.client.gui.dinopedia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ButtonDinopedia extends GuiButton {
	private int iconIndexHeight;
	private int iconIndexWidth;

	public ButtonDinopedia(int id, int xPosition, int yPosition, int width) {
		super(id, xPosition, yPosition, 32, 32, "");

		for (; width > 7; ) {
			width -= 8;
			iconIndexHeight++;
		}

		iconIndexWidth = width;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
	}
}