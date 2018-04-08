package fossilsarcheology.client.gui;

import com.mojang.realmsclient.gui.ChatFormatting;
import fossilsarcheology.Revival;
import net.ilexiconn.llibrary.LLibrary;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.Charsets;
import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class FAMainMenuGUI extends GuiMainMenu {
	public static final int LAYER_COUNT = 2;
	public static final ResourceLocation splash = new ResourceLocation("fossil:splashes.txt");
	private ResourceLocation[] layerTextures = new ResourceLocation[FAMainMenuGUI.LAYER_COUNT];
	private int layerTick;
	private int backAdd;
	private int frontAdd;

	public FAMainMenuGUI() {
		super();
		this.backAdd = new Random().nextInt(1027);
		this.frontAdd = new Random().nextInt(2047);

		BufferedReader reader = null;
		try {
			List<String> list = new ArrayList<>();
			reader = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splash).getInputStream(), Charsets.UTF_8));
			String s;

			while ((s = reader.readLine()) != null) {
				s = s.trim();

				if (!s.isEmpty()) {
					list.add(s);
				}
			}

			if (!list.isEmpty()) {
				do {
					this.splashText = list.get(new Random().nextInt(list.size()));
				} while (this.splashText.hashCode() == 125780783);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void drawCenteredString(FontRenderer fontRenderer, String sting, int x, int y, int color) {
		if (sting.equals(splashText)) {
			fontRenderer.drawStringWithShadow(sting, x - fontRenderer.getStringWidth(sting) / 2, y, 0XF1E961);
		} else {
			fontRenderer.drawStringWithShadow(sting, x - fontRenderer.getStringWidth(sting) / 2, y, color);
		}
	}

	@Override
	public void initGui() {
		super.initGui();

		for (int i = 0; i < this.layerTextures.length; i++) {
			this.layerTextures[i] = new ResourceLocation(Revival.MODID, "textures/gui/parallax/layer" + i + ".png");
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		this.layerTick++;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		for (int i = 0; i < this.layerTextures.length; i++) {
			ResourceLocation layerTexture = this.layerTextures[i];
			this.mc.getTextureManager().bindTexture(layerTexture);
			drawTexturedModalRect(0, 0, (i == 1 ? this.backAdd : this.frontAdd) + ((this.layerTick + partialTicks) / (float) (this.layerTextures.length - i)) + (float) (i + 1) + 2048 * i / 4.0F, 0, this.width, this.height, 2048 / (this.layerTextures.length - i) * (this.height / 128.0F), this.height, this.zLevel);
			// Gui.drawRect(0, 0, this.width, this.height, 0x50000000);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
		}

		this.fontRenderer.drawStringWithShadow("Fossils & Archeology Revival " + ChatFormatting.RED + Revival.RELEASE_TYPE.getBranding(), 2, this.height - 10, 0xFFFFFFFF);

		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, MathHelper.sin(((float) this.layerTick + partialTicks) / 16.0F) * 4.0F, 0.0F);
		this.mc.getTextureManager().bindTexture(GuiMainMenu.MINECRAFT_TITLE_TEXTURES);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawTexturedModalRect(this.width / 2 - 274 / 2, 30, 0, 0, 155, 44);
		this.drawTexturedModalRect(this.width / 2 - 274 / 2 + 155, 30, 0, 45, 155, 44);
		GL11.glTranslatef((float) (this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float f1 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
		f1 = f1 * 100.0F / (float) (this.fontRenderer.getStringWidth(this.splashText) + 32);
		GL11.glScalef(f1, f1, f1);
		this.drawCenteredString(this.fontRenderer, this.splashText, 0, -8, 0xFFFFFF);
		GL11.glPopMatrix();

		ForgeHooksClient.renderMainMenu(this, this.fontRenderer, this.width, this.height, "");
		String s1 = "Copyright Mojang AB. Do not distribute!";
		this.drawString(this.fontRenderer, s1, this.width - this.fontRenderer.getStringWidth(s1) - 2, this.height - 10, 0xFFFFFFFF);

		for (Object button : this.buttonList) {
			((GuiButton) button).drawButton(this.mc, mouseX, mouseY, LLibrary.PROXY.getPartialTicks());
		}

		for (Object label : this.labelList) {
			((GuiLabel) label).drawLabel(this.mc, mouseX, mouseY);
		}
	}

	public void drawTexturedModalRect(double x, double y, double u, double v, double width, double height, double textureWidth, double textureHeight, double zLevel) {
		float f = 1.0F / (float) textureWidth;
		float f1 = 1.0F / (float) textureHeight;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) x, (double) (y + height), zLevel).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) (y + height), zLevel).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) y, zLevel).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
		vertexbuffer.pos((double) x, (double) y, zLevel).tex((double) (u * f), (double) (v * f1)).endVertex();
		tessellator.draw();
	}
}
