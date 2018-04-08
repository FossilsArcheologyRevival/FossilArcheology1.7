package fossilsarcheology.client.gui.dinopedia;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ButtonDinopediaPage extends GuiButton {
	private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation("fossil:textures/gui/dinopedia.png");
	private final boolean nextPage;
	private int page;
	private int lastpage = 1;

	public ButtonDinopediaPage(int id, int xPos, int yPos, boolean nextPage, int bookpage) {
		super(id, xPos, yPos, 34, 24, "");
		this.nextPage = nextPage;
		page = bookpage;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.enabled) {
			boolean hovering = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(BOOK_TEXTURE);
			int width = 0;
			int height = 223;

			// if ((hovering) || (this.nextPage && page == lastpage) ||
			// (!this.nextPage && page == 0))
			if ((this.nextPage && page == lastpage) || (!this.nextPage && page == 0)) {
				height -= 23;
			}

			if (!this.nextPage) {
				width += 34;
			}

			this.drawTexturedModalRect(this.x, this.y, width, height, 34, 30);
		}
	}
}