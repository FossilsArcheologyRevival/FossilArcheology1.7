package fossilsarcheology.client.gui;

import fossilsarcheology.Revival;
import fossilsarcheology.server.block.entity.TileEntitySifter;
import fossilsarcheology.server.container.SifterContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class SifterGUI extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(Revival.MODID + ":" + "textures/gui/sifter.png");
    private TileEntitySifter sifterInventory;

    public SifterGUI(InventoryPlayer playerInventory, TileEntitySifter sifterInventory) {
        super(new SifterContainer(playerInventory, sifterInventory));
        this.sifterInventory = sifterInventory;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String inventoryName = I18n.format(sifterInventory.getName());
        this.fontRenderer.drawString(inventoryName, this.xSize / 8 - this.fontRenderer.getStringWidth(inventoryName) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int centerX = (this.width - this.xSize) / 2;
        int centerY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(centerX, centerY, 0, 0, this.xSize, this.ySize);
        int siftTimeRemaining;

        if (this.sifterInventory.isBurning()) {
            siftTimeRemaining = this.sifterInventory.getSiftTimeRemainingScaled(12);
        }

        siftTimeRemaining = this.sifterInventory.getSiftProgressScaled(26);
        this.drawTexturedModalRect(centerX + 75, centerY + 33, 181, 2, 31, siftTimeRemaining + 1);

    }
}