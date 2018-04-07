package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.projectile.AncientJavelinEntity;
import fossilsarcheology.server.entity.projectile.JavelinEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class JavelinRenderer extends Render<JavelinEntity> {
	public static final ResourceLocation ANCIENT_JAVELIN = new ResourceLocation(Revival.MODID, "textures/entities/ancient_javelin.png");
	public static final ResourceLocation DIAMOND_JAVELIN = new ResourceLocation(Revival.MODID, "textures/entities/diamond_javelin.png");
	public static final ResourceLocation GOLD_JAVELIN = new ResourceLocation(Revival.MODID, "textures/entities/gold_javelin.png");
	public static final ResourceLocation IRON_JAVELIN = new ResourceLocation(Revival.MODID, "textures/entities/iron_javelin.png");
	public static final ResourceLocation STONE_JAVELIN = new ResourceLocation(Revival.MODID, "textures/entities/stone_javelin.png");
	public static final ResourceLocation WOODEN_JAVELIN = new ResourceLocation(Revival.MODID, "textures/entities/wooden_javelin.png");

	public JavelinRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(JavelinEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		this.bindEntityTexture(entity);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		GlStateManager.enableRescaleNormal();
		float shake = (float) entity.arrowShake - partialTicks;
		if (shake > 0.0F) {
			GlStateManager.rotate(-MathHelper.sin(shake * 3.0F) * shake, 0.0F, 0.0F, 1.0F);
		}
		GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(0.1F, 0.1F, 0.1F);
		GlStateManager.translate(-4.0F, 0.0F, 0.0F);
		if (this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}
		GlStateManager.glNormal3f(0.05625F, 0.0F, 0.0F);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-7.0, -2.0, -2.0).tex(0.0, 0.15625).endVertex();
		buffer.pos(-7.0, -2.0, 2.0).tex(0.15625, 0.15625).endVertex();
		buffer.pos(-7.0, 2.0, 2.0).tex(0.15625, 0.3125).endVertex();
		buffer.pos(-7.0, 2.0, -2.0).tex(0.0, 0.3125).endVertex();
		tessellator.draw();
		GlStateManager.glNormal3f(-0.05625F, 0.0F, 0.0F);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-7.0, 2.0, -2.0).tex(0.0, 0.15625).endVertex();
		buffer.pos(-7.0, 2.0, 2.0).tex(0.15625, 0.15625).endVertex();
		buffer.pos(-7.0, -2.0, 2.0).tex(0.15625, 0.3125).endVertex();
		buffer.pos(-7.0, -2.0, -2.0).tex(0.0, 0.3125).endVertex();
		tessellator.draw();
		for (int i = 0; i < 4; ++i) {
			GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.glNormal3f(0.0F, 0.0F, 0.05625F);
			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			buffer.pos(-8.0, -2.0, 0.0).tex(0.0, 0.0).endVertex();
			buffer.pos(8.0, -2.0, 0.0).tex(0.5, 0.0).endVertex();
			buffer.pos(8.0, 2.0, 0.0).tex(0.5, 0.15625).endVertex();
			buffer.pos(-8.0, 2.0, 0.0).tex(0.0, 0.15625).endVertex();
			tessellator.draw();
		}
		if (this.renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(JavelinEntity entity) {
		if (entity instanceof AncientJavelinEntity) {
			return ANCIENT_JAVELIN;
		} else {
			switch (entity.material) {
				case WOOD:
					return WOODEN_JAVELIN;
				case STONE:
					return STONE_JAVELIN;
				case IRON:
					return IRON_JAVELIN;
				case GOLD:
					return GOLD_JAVELIN;
				case DIAMOND:
					return DIAMOND_JAVELIN;
			}
		}
		return null;
	}
}