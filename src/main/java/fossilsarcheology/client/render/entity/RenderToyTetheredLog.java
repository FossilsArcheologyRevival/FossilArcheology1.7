package fossilsarcheology.client.render.entity;

import fossilsarcheology.client.model.ModelToyTetheredLog;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderToyTetheredLog extends RenderLiving {

	public RenderToyTetheredLog(RenderManager manager) {
		super(manager, new ModelToyTetheredLog(), 0.4F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("fossil:textures/model/toy/log_swing.png");
	}
}
