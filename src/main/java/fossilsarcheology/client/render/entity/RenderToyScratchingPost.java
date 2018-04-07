package fossilsarcheology.client.render.entity;

import fossilsarcheology.client.model.ModelToyScratchingPost;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderToyScratchingPost extends RenderLiving {

	public RenderToyScratchingPost(RenderManager manager) {
		super(manager, new ModelToyScratchingPost(), 0.4F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("fossil:textures/model/toy/scratching_post.png");
	}
}
