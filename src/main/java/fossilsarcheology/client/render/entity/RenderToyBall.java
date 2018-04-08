package fossilsarcheology.client.render.entity;

import fossilsarcheology.client.model.ModelToyBall;
import fossilsarcheology.server.entity.utility.EntityToyBall;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderToyBall extends RenderLiving {

	public static final float[][] fleeceColorTable = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};

	public RenderToyBall(RenderManager manager) {
		super(manager, new ModelToyBall(), 0.3F);
	}

	protected void preRenderCallback(EntityLivingBase living, float f) {
		int i = ~((EntityToyBall) living).getColor() & 15;
		GL11.glColor3f(fleeceColorTable[i][0], fleeceColorTable[i][1], fleeceColorTable[i][2]);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("fossil:textures/model/toy/ball.png");
	}
}
