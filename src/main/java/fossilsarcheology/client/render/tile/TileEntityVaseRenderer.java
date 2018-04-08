package fossilsarcheology.client.render.tile;

import fossilsarcheology.client.model.ModelVaseAmphora;
import fossilsarcheology.client.model.ModelVaseKylix;
import fossilsarcheology.client.model.ModelVaseVolute;
import fossilsarcheology.server.block.entity.TileEntityAmphora;
import fossilsarcheology.server.block.entity.TileEntityKylix;
import fossilsarcheology.server.block.entity.block.TileEntityVolute;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class TileEntityVaseRenderer extends TileEntitySpecialRenderer {

    private static final ResourceLocation damaged_volute = new ResourceLocation("fossil:textures/blocks/vases/vase_damaged_volute.png");
    private static final ResourceLocation restored_volute = new ResourceLocation("fossil:textures/blocks/vases/vase_restored_volute.png");
    private static final ResourceLocation redFigure_volute = new ResourceLocation("fossil:textures/blocks/vases/vase_redFigure_volute.png");
    private static final ResourceLocation blackFigure_volute = new ResourceLocation("fossil:textures/blocks/vases/vase_blackFigure_volute.png");
    private static final ResourceLocation porcelain_volute = new ResourceLocation("fossil:textures/blocks/vases/vase_porcelain_volute.png");
    private static final ResourceLocation damaged_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_damaged_amphora.png");
    private static final ResourceLocation restored_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_restored_amphora.png");
    private static final ResourceLocation redFigure_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_redFigure_amphora.png");
    private static final ResourceLocation blackFigure_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_blackFigure_amphora.png");
    private static final ResourceLocation porcelain_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_porcelain_amphora.png");
    private static final ResourceLocation damaged_kylix = new ResourceLocation("fossil:textures/blocks/vases/vase_damaged_kylix.png");
    private static final ResourceLocation restored_kylix = new ResourceLocation("fossil:textures/blocks/vases/vase_restored_kylix.png");
    private static final ResourceLocation redFigure_kylix = new ResourceLocation("fossil:textures/blocks/vases/vase_redFigure_kylix.png");
    private static final ResourceLocation blackFigure_kylix = new ResourceLocation("fossil:textures/blocks/vases/vase_blackFigure_kylix.png");
    private static final ResourceLocation porcelain_kylix = new ResourceLocation("fossil:textures/blocks/vases/vase_porcelain_kylix.png");
    public static final ModelVaseVolute VOLUTE_MODEL = new ModelVaseVolute();
    public static final ModelVaseAmphora AMPHORA_MODEL = new ModelVaseAmphora();
    public static final ModelVaseKylix KYLIX_MODEL = new ModelVaseKylix();
    private int vaseType;

    public TileEntityVaseRenderer(int vaseType) {
        this.vaseType = vaseType;
    }

    public void render(TileEntity te, double x, double y, double z, float scale, int destroyProgress, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 1.0F, 0.0F, 1.0F);
        rotateBlock(te.getWorld(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), te);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    // rotates block
    private void rotateBlock(World world, int x, int y, int z, TileEntity te) {
        if (world != null) {
            int dir = 0;
            int meta = 0;
            if(te instanceof TileEntityAmphora){
                dir = ((TileEntityAmphora)te).getVaseRotation();
                meta = ((TileEntityAmphora)te).getVaseType();
            }
            if(te instanceof TileEntityKylix){
                dir = ((TileEntityKylix)te).getVaseRotation();
                meta = ((TileEntityKylix)te).getVaseType();

            }
            if(te instanceof TileEntityVolute){
                dir = ((TileEntityVolute)te).getVaseRotation();
                meta = ((TileEntityVolute)te).getVaseType();

            }
            GL11.glPushMatrix();
            GL11.glRotatef(dir * (90), 0F, 1F, 0F);
            GL11.glScalef(1F, 1F, 1F);

            switch (vaseType) {
                case 1:
                default:
                    switch (meta) {
                        case 0:
                        default:
                            this.bindTexture(damaged_volute);
                            this.VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 1:
                            this.bindTexture(restored_volute);
                            this.VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 2:
                            this.bindTexture(redFigure_volute);
                            this.VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 3:
                            this.bindTexture(blackFigure_volute);
                            this.VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 4:
                            this.bindTexture(porcelain_volute);
                            this.VOLUTE_MODEL.render(0.0625F);
                            break;
                    }
                    break;
                case 0:
                    switch (meta) {
                        case 0:
                        default:
                            this.bindTexture(damaged_amphora);
                            this.AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 1:
                            this.bindTexture(restored_amphora);
                            this.AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 2:
                            this.bindTexture(redFigure_amphora);
                            this.AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 3:
                            this.bindTexture(blackFigure_amphora);
                            this.AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 4:
                            this.bindTexture(porcelain_amphora);
                            this.AMPHORA_MODEL.render(0.0625F);
                            break;
                    }
                    break;
                case 2:
                    switch (meta) {
                        case 0:
                        default:
                            this.bindTexture(damaged_kylix);
                            this.KYLIX_MODEL.render(0.0625F);
                            break;
                        case 1:
                            this.bindTexture(restored_kylix);
                            this.KYLIX_MODEL.render(0.0625F);
                            break;
                        case 2:
                            this.bindTexture(redFigure_kylix);
                            this.KYLIX_MODEL.render(0.0625F);
                            break;
                        case 3:
                            this.bindTexture(blackFigure_kylix);
                            this.KYLIX_MODEL.render(0.0625F);
                            break;
                        case 4:
                            this.bindTexture(porcelain_kylix);
                            this.KYLIX_MODEL.render(0.0625F);
                            break;
                    }
                    break;
            }
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glRotatef(0F, 0F, 1F, 0F);
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(damaged_volute);
            this.VOLUTE_MODEL.render(0.0625F);
            GL11.glPopMatrix();
        }
    }

}
