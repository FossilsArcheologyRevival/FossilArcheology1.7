package fossilsarcheology.client.model;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToyScratchingPost extends AdvancedModelBase {
	public AdvancedModelRenderer Wool;
	public AdvancedModelRenderer Post;
	public AdvancedModelRenderer Base;

	public ModelToyScratchingPost() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.Base = new AdvancedModelRenderer(this, 24, 0);
		this.Base.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.Base.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 10, 0.0F);
		this.Post = new AdvancedModelRenderer(this, 0, 4);
		this.Post.mirror = true;
		this.Post.setRotationPoint(0.0F, 24.2F, 0.0F);
		this.Post.addBox(-4.0F, -32.0F, -4.0F, 8, 32, 8, 0.0F);
		this.Wool = new AdvancedModelRenderer(this, 28, 35);
		this.Wool.setRotationPoint(0.0F, 24.2F, 0.0F);
		this.Wool.addBox(-4.5F, -30.6F, -4.5F, 9, 20, 9, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Base.render(f5);
		this.Post.render(f5);
		this.Wool.render(f5);
	}

	public void render(float f5) {
		this.Base.render(f5);
		this.Post.render(f5);
		this.Wool.render(f5);
	}
}
