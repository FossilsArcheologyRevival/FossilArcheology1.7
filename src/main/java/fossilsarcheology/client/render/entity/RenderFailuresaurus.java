package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.monster.EntityFailuresaurus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFailuresaurus extends RenderLiving<EntityFailuresaurus> {
	private static final ResourceLocation failuresaurus_default = new ResourceLocation("fossil:textures/model/failuresaurus_0/failuresaurus_0.png");
	private static final ResourceLocation failuresaurus_creepy = new ResourceLocation("fossil:textures/model/failuresaurus_0/failuresaurus_1.png");
	private static final ResourceLocation failuresaurus_cute = new ResourceLocation("fossil:textures/model/failuresaurus_0/failuresaurus_2.png");

	public RenderFailuresaurus(ModelBase model, RenderManager renderer) {
		super(renderer, model, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFailuresaurus entity) {
		switch (entity.getSkin()) {
			case 0:
			default:
				return failuresaurus_default;
			case 1:
				return failuresaurus_creepy;
			case 2:
				return failuresaurus_cute;
		}
	}
}
