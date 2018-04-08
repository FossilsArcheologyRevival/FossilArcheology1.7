package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.StoneTabletEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class StoneTabletRenderer extends Render<StoneTabletEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Revival.MODID, "textures/entities/stone_tablet.png");

    public StoneTabletRenderer(RenderManager renderManager) {
        super(renderManager);
    }

    private void renderTablet(StoneTabletEntity entity, int width, int height, int textureU, int textureV) {
        float xOffset = -width / 2.0F;
        float yOffset = -height / 2.0F;
        for (int blockX = 0; blockX < width / 16; ++blockX) {
            for (int blockZ = 0; blockZ < height / 16; ++blockZ) {
                float maxX = xOffset + ((blockX + 1) * 16);
                float minX = xOffset + (blockX * 16);
                float maxY = yOffset + ((blockZ + 1) * 16);
                float minY = yOffset + (blockZ * 16);
                this.setLightmap(entity, (maxX + minX) / 2.0F, (maxY + minY) / 2.0F);
                float minTexX = (textureU + width - blockX * 16) / 256.0F;
                float maxTexX = (textureU + width - (blockX + 1) * 16) / 256.0F;
                float minTexY = (textureV + height - blockZ * 16) / 256.0F;
                float maxTexY = (textureV + height - (blockZ + 1) * 16) / 256.0F;
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder builder = tessellator.getBuffer();
                builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
                builder.pos(maxX, minY, -0.5).tex(maxTexX, minTexY).normal(0.0F, 0.0F, -1.0F).endVertex();
                builder.pos(minX, minY, -0.5).tex(minTexX, minTexY).normal(0.0F, 0.0F, -1.0F).endVertex();
                builder.pos(minX, maxY, -0.5).tex(minTexX, maxTexY).normal(0.0F, 0.0F, -1.0F).endVertex();
                builder.pos(maxX, maxY, -0.5).tex(maxTexX, maxTexY).normal(0.0F, 0.0F, -1.0F).endVertex();
                builder.pos(maxX, maxY, 0.5).tex(0.75, 0.0).normal(0.0F, 0.0F, 1.0F).endVertex();
                builder.pos(minX, maxY, 0.5).tex(0.8125, 0.0).normal(0.0F, 0.0F, 1.0F).endVertex();
                builder.pos(minX, minY, 0.5).tex(0.8125, 0.0625).normal(0.0F, 0.0F, 1.0F).endVertex();
                builder.pos(maxX, minY, 0.5).tex(0.75, 0.0625).normal(0.0F, 0.0F, 1.0F).endVertex();
                builder.pos(maxX, maxY, -0.5).tex(0.75, 0.001953125).normal(0.0F, 1.0F, 0.0F).endVertex();
                builder.pos(minX, maxY, -0.5).tex(0.8125, 0.001953125).normal(0.0F, 1.0F, 0.0F).endVertex();
                builder.pos(minX, maxY, 0.5).tex(0.8125, 0.001953125).normal(0.0F, 1.0F, 0.0F).endVertex();
                builder.pos(maxX, maxY, 0.5).tex(0.75, 0.001953125).normal(0.0F, 1.0F, 0.0F).endVertex();
                builder.pos(maxX, minY, 0.5).tex(0.75, 0.001953125).normal(0.0F, -1.0F, 0.0F).endVertex();
                builder.pos(minX, minY, 0.5).tex(0.8125, 0.001953125).normal(0.0F, -1.0F, 0.0F).endVertex();
                builder.pos(minX, minY, -0.5).tex(0.8125, 0.001953125).normal(0.0F, -1.0F, 0.0F).endVertex();
                builder.pos(maxX, minY, -0.5).tex(0.75, 0.001953125).normal(0.0F, -1.0F, 0.0F).endVertex();
                builder.pos(maxX, maxY, 0.5).tex(0.751953125, 0.0).normal(-1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(maxX, minY, 0.5).tex(0.751953125, 0.0625).normal(-1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(maxX, minY, -0.5).tex(0.751953125, 0.0625).normal(-1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(maxX, maxY, -0.5).tex(0.751953125, 0.0).normal(-1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(minX, maxY, -0.5).tex(0.751953125, 0.0).normal(1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(minX, minY, -0.5).tex(0.751953125, 0.0625).normal(1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(minX, minY, 0.5).tex(0.751953125, 0.0625).normal(1.0F, 0.0F, 0.0F).endVertex();
                builder.pos(minX, maxY, 0.5).tex(0.751953125, 0.0).normal(1.0F, 0.0F, 0.0F).endVertex();
                tessellator.draw();
            }
        }
    }

    private void setLightmap(StoneTabletEntity entity, float centerX, float centerY) {
        int x = MathHelper.floor(entity.posX);
        int y = MathHelper.floor(entity.posY + (centerY / 16.0F));
        int z = MathHelper.floor(entity.posZ);
        EnumFacing facing = entity.facingDirection;
        if (facing == EnumFacing.NORTH) {
            x = MathHelper.floor(entity.posX + (centerX / 16.0F));
        } else if (facing == EnumFacing.WEST) {
            z = MathHelper.floor(entity.posZ - (centerX / 16.0F));
        } else if (facing == EnumFacing.SOUTH) {
            x = MathHelper.floor(entity.posX - (centerX / 16.0F));
        } else if (facing == EnumFacing.EAST) {
            z = MathHelper.floor(entity.posZ + (centerX / 16.0F));
        }
        int combinedLight = this.renderManager.world.getCombinedLight(new BlockPos(x, y, z), 0);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, combinedLight % 65536, combinedLight / 65536);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
    }

    @Override
    public void doRender(StoneTabletEntity entity, double x, double y, double z, float yaw, float partialTicks) {
        if (entity.variant != null) {
            this.bindEntityTexture(entity);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.enableRescaleNormal();
            StoneTabletEntity.Variant variant = entity.variant;
            float scale = 0.0625F;
            GlStateManager.scale(scale, scale, scale);
            this.renderTablet(entity, variant.sizeX, variant.sizeY, variant.offsetX, variant.offsetY);
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(StoneTabletEntity entity) {
        return TEXTURE;
    }
}