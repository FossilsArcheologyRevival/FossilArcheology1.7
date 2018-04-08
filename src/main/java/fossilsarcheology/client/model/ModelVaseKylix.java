package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelVaseKylix extends ModelBase {

	ModelRenderer Base;
	ModelRenderer Body;
	ModelRenderer Neck;
	ModelRenderer RightLip;
	ModelRenderer LeftLip;
	ModelRenderer FrontLip;
	ModelRenderer BackLip;
	ModelRenderer NubbinL;
	ModelRenderer NubbinR;

	public ModelVaseKylix() {
		textureWidth = 32;
		textureHeight = 32;

		Base = new ModelRenderer(this, 0, 27);
		Base.addBox(-2F, 0F, -2F, 4, 1, 4);
		Base.setRotationPoint(0F, 23F, 0F);
		Base.setTextureSize(32, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 19);
		Body.addBox(-1F, 0F, -1F, 2, 5, 2);
		Body.setRotationPoint(0F, 18F, 0F);
		Body.setTextureSize(32, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 9);
		Neck.addBox(-4F, 0F, -4F, 8, 1, 8);
		Neck.setRotationPoint(0F, 17F, 0F);
		Neck.setTextureSize(32, 32);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		RightLip = new ModelRenderer(this, 0, 0);
		RightLip.addBox(0F, 0F, -4F, 1, 1, 8);
		RightLip.setRotationPoint(3F, 16F, 0F);
		RightLip.setTextureSize(32, 32);
		RightLip.mirror = true;
		setRotation(RightLip, 0F, 0F, 0F);
		RightLip.mirror = false;
		LeftLip = new ModelRenderer(this, 0, 0);
		LeftLip.addBox(-1F, 0F, -4F, 1, 1, 8);
		LeftLip.setRotationPoint(-3F, 16F, 0F);
		LeftLip.setTextureSize(32, 32);
		LeftLip.mirror = true;
		setRotation(LeftLip, 0F, 0F, 0F);
		FrontLip = new ModelRenderer(this, 11, 0);
		FrontLip.addBox(-3F, 0F, -1F, 6, 1, 1);
		FrontLip.setRotationPoint(0F, 16F, -3F);
		FrontLip.setTextureSize(32, 32);
		FrontLip.mirror = true;
		setRotation(FrontLip, 0F, 0F, 0F);
		BackLip = new ModelRenderer(this, 11, 0);
		BackLip.addBox(-3F, 0F, 0F, 6, 1, 1);
		BackLip.setRotationPoint(0F, 16F, 3F);
		BackLip.setTextureSize(32, 32);
		BackLip.mirror = true;
		setRotation(BackLip, 0F, 0F, 0F);
		NubbinL = new ModelRenderer(this, 0, 0);
		NubbinL.addBox(-1F, 0F, -1F, 2, 1, 2);
		NubbinL.setRotationPoint(4F, 16F, 0F);
		NubbinL.setTextureSize(32, 32);
		NubbinL.mirror = true;
		setRotation(NubbinL, 0F, 0F, -0.5402259F);
		NubbinR = new ModelRenderer(this, 0, 0);
		NubbinR.addBox(-2F, 0.5F, -1F, 2, 1, 2);
		NubbinR.setRotationPoint(-3F, 16F, 0F);
		NubbinR.setTextureSize(32, 32);
		NubbinR.mirror = true;
		setRotation(NubbinR, 0F, 0F, 0.5402318F);
		NubbinR.mirror = false;
	}

	public void render(float f5) {
		Base.render(f5);
		Body.render(f5);
		Neck.render(f5);
		RightLip.render(f5);
		LeftLip.render(f5);
		FrontLip.render(f5);
		BackLip.render(f5);
		NubbinL.render(f5);
		NubbinR.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
