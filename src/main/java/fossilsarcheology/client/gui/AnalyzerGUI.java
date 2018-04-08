package fossilsarcheology.client.gui;

import fossilsarcheology.server.block.entity.AnalyzerBlockEntity;
import fossilsarcheology.server.container.AnalyzerContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class AnalyzerGUI extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation("fossil:textures/gui/analyzer.png");
    private AnalyzerBlockEntity analyzer;

    public AnalyzerGUI(InventoryPlayer playerInventory, AnalyzerBlockEntity analyzer) {
        super(new AnalyzerContainer(playerInventory, analyzer));
        this.analyzer = analyzer;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String customName = this.analyzer.getName();
        this.fontRenderer.drawString(customName, this.xSize / 2 - this.fontRenderer.getStringWidth(customName) / 2, 6, 0x404040);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int drawX = (this.width - this.xSize) / 2;
        int drawY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(drawX, drawY, 0, 0, this.xSize, this.ySize);
        int progress = this.analyzer.getAnalyzeProgressScaled(22);
        this.drawTexturedModalRect(drawX + 80, drawY + 22, 177, 18, progress, 9);
    }
}
