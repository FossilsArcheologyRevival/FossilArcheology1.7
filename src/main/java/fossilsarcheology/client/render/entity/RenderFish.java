package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.EntityFishBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFish extends RenderLiving<EntityFishBase> {

	public RenderFish(ModelBase model, RenderManager renderer) {
		super(renderer, model, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFishBase entity) {
		return new ResourceLocation(entity.getTexture());
	}

	@Override
	protected void preRenderCallback(EntityFishBase entity, float f) {
		if (entity.isChild()) {
			GL11.glScalef(0.5F, 0.5F, 0.5F);
		}
	}

}
