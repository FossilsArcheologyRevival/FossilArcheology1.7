package fossilsarcheology.client.render.tile;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelAnuTotem;
import fossilsarcheology.server.block.AnuStatueBlock;
import fossilsarcheology.server.block.entity.TileEntityAnuStatue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityAnuStatueRender extends TileEntitySpecialRenderer<TileEntityAnuStatue> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Revival.MODID, "textures/model/anu_statue.png");
    private static final ModelAnuTotem MODEL = new ModelAnuTotem();

    @Override
    public void render(TileEntityAnuStatue entity, double x, double y, double z, float f, int destroy, float alpha) {
        EnumFacing facing = EnumFacing.SOUTH;
        if (entity != null && entity.hasWorld()) {
            facing = entity.getWorld().getBlockState(entity.getPos()).getValue(AnuStatueBlock.FACING);
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5F, y + 1.5F, z + 0.5F);
        GlStateManager.rotate(180, 0F, 0F, 1F);
        GlStateManager.rotate(facing.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        MODEL.renderBlock(0.0625F);
        GlStateManager.popMatrix();
    }
}
