package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelAncientHelmet extends ModelBiped {
	public ModelRenderer Snout;
	public ModelRenderer Ear1;
	public ModelRenderer Ear2;
	public ModelRenderer Tooth1;
	public ModelRenderer Tooth2;

	public ModelAncientHelmet(float f) {
		super(f, 0, 64, 64);
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.Ear2 = new ModelRenderer(this, 0, 32);
		this.Ear2.setRotationPoint(-3.0F, -8.0F, 3.5F);
		this.Ear2.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.Tooth2 = new ModelRenderer(this, 0, 0);
		this.Tooth2.setRotationPoint(-1.5F, -8.0F, -7.5F);
		this.Tooth2.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

		this.Ear1 = new ModelRenderer(this, 0, 32);
		this.Ear1.setRotationPoint(3.0F, -8.0F, 3.5F);
		this.Ear1.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.Tooth1 = new ModelRenderer(this, 0, 0);
		this.Tooth1.setRotationPoint(1.5F, -8.0F, -7.5F);
		this.Tooth1.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.Snout = new ModelRenderer(this, 0, 32);
		this.Snout.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.Snout.addBox(-2.0F, -3.0F, -8.0F, 4, 3, 12, 0.0F);
		this.bipedHead.addChild(this.Ear2);
		this.bipedHead.addChild(this.Tooth2);
		this.bipedHead.addChild(this.Ear1);
		this.bipedHead.addChild(this.Tooth1);
		this.bipedHead.addChild(this.Snout);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
