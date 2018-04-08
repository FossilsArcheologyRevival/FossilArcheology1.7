package fossilsarcheology.client.gui;

import fossilsarcheology.server.block.entity.TileEntityTimeMachine;
import fossilsarcheology.server.container.TimeMachineContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TimeMachineGUI extends GuiContainer {
    private static final ResourceLocation loc = new ResourceLocation("fossil:textures/gui/time_machine.png");
    final int SQR_WIDTH = 34;
    final int SQR_HEIGHT = 13;
    final int SQR_POSX = 131;
    private TileEntityTimeMachine timeMachineInstance;

    public TimeMachineGUI(InventoryPlayer playerInventory, TileEntityTimeMachine tile) {
        super(new TimeMachineContainer(playerInventory, tile));
        this.timeMachineInstance = tile;
        this.ySize = 188;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String chargeLevel = this.timeMachineInstance.getChargeLevel() / 10 + "%";
        int var3 = (34 - this.fontRenderer.getStringWidth(chargeLevel)) / 2;
        this.fontRenderer.drawString(I18n.format("tile.timeMachine.name"), 70 - I18n.format("tile.timeMachine.name").length(), 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        if (this.timeMachineInstance.isCharged()) {
            String comingSoon = "Coming Soon...";
            GL11.glPushMatrix();
            GL11.glScalef(1.25F, 1.25F, 1.25F);
            this.fontRenderer.drawString(comingSoon, this.xSize / 2 - comingSoon.length() * 4 + 37, this.ySize - 152 + 2, 0XBF0000, false);
            GL11.glPopMatrix();
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(loc);
        int centerX = (this.width - this.xSize) / 2;
        int centerY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(centerX, centerY, 0, 0, this.xSize, this.ySize);
        this.timeMachineInstance.getClass();
        float invertedChargeLevel = (float) (1000 - this.timeMachineInstance.getChargeLevel());
        this.timeMachineInstance.getClass();
        int scaledChargeLevel = (int) ((invertedChargeLevel / 1000.0F) * 75.0F);
        this.drawTexturedModalRect(centerX + 5, centerY + 17, 176, 2, 75, scaledChargeLevel);
    }
}
