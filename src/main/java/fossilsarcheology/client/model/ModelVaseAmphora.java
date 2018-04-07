package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelVaseAmphora extends ModelBase {

	ModelRenderer Base;
	ModelRenderer Body;
	ModelRenderer Neck;
	ModelRenderer RightHandle1;
	ModelRenderer RightHandle2;
	ModelRenderer LeftHandle1;
	ModelRenderer LeftHandle2;
	ModelRenderer RightHandle3;
	ModelRenderer RightHandle4;
	ModelRenderer LeftHandle3;
	ModelRenderer LeftHandle4;
	ModelRenderer BackLip;
	ModelRenderer FrontLip;
	ModelRenderer RightLip;
	ModelRenderer LeftLip;

	public ModelVaseAmphora() {
		textureWidth = 64;
		textureHeight = 64;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(-6F, 0F, -6F, 12, 2, 12);
		Base.setRotationPoint(0F, 22F, 0F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 16);
		Body.addBox(-4F, -7F, -4F, 8, 8, 8);
		Body.setRotationPoint(0F, 21F, 0F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 34);
		Neck.addBox(-3F, 0F, -3F, 6, 10, 6);
		Neck.setRotationPoint(0F, 4F, 0F);
		Neck.setTextureSize(64, 64);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		RightHandle1 = new ModelRenderer(this, 0, 15);
		RightHandle1.addBox(0F, -6F, -1F, 1, 6, 2);
		RightHandle1.setRotationPoint(3F, 15F, 0F);
		RightHandle1.setTextureSize(64, 64);
		RightHandle1.mirror = true;
		setRotation(RightHandle1, 0F, 0F, 0.3733352F);
		RightHandle2 = new ModelRenderer(this, 0, 33);
		RightHandle2.addBox(0F, -4F, -1F, 1, 4, 2);
		RightHandle2.setRotationPoint(5F, 10F, 0F);
		RightHandle2.setTextureSize(64, 64);
		RightHandle2.mirror = true;
		setRotation(RightHandle2, 0F, 0F, 0F);
		LeftHandle1 = new ModelRenderer(this, 0, 15);
		LeftHandle1.mirror = true;
		LeftHandle1.addBox(-1F, -6F, -1F, 1, 6, 2);
		LeftHandle1.setRotationPoint(-3F, 15F, 0F);
		LeftHandle1.setTextureSize(64, 64);
		setRotation(LeftHandle1, 0F, 0F, -0.3733434F);
		LeftHandle2 = new ModelRenderer(this, 0, 33);
		LeftHandle2.mirror = true;
		LeftHandle2.addBox(-1F, -4F, -1F, 1, 4, 2);
		LeftHandle2.setRotationPoint(-5F, 10F, 0F);
		LeftHandle2.setTextureSize(64, 64);
		setRotation(LeftHandle2, 0F, 0F, 0F);
		RightHandle3 = new ModelRenderer(this, 0, 55);
		RightHandle3.addBox(0F, 0F, -1F, 1, 2, 2);
		RightHandle3.setRotationPoint(4F, 5F, 0F);
		RightHandle3.setTextureSize(64, 64);
		RightHandle3.mirror = true;
		setRotation(RightHandle3, 0F, 0F, -0.5948578F);
		RightHandle4 = new ModelRenderer(this, 0, 51);
		RightHandle4.addBox(0F, 0F, -1F, 2, 1, 2);
		RightHandle4.setRotationPoint(3F, 4F, 0F);
		RightHandle4.setTextureSize(64, 64);
		RightHandle4.mirror = true;
		setRotation(RightHandle4, 0F, 0F, 0.2602503F);
		RightHandle4.mirror = false;
		LeftHandle3 = new ModelRenderer(this, 0, 55);
		LeftHandle3.mirror = true;
		LeftHandle3.addBox(-1F, 0F, -1F, 1, 2, 2);
		LeftHandle3.setRotationPoint(-4F, 5F, 0F);
		LeftHandle3.setTextureSize(64, 64);
		setRotation(LeftHandle3, 0F, 0F, 0.5948606F);
		LeftHandle4 = new ModelRenderer(this, 0, 51);
		LeftHandle4.mirror = true;
		LeftHandle4.addBox(-2F, 0F, -1F, 2, 1, 2);
		LeftHandle4.setRotationPoint(-3F, 4F, 0F);
		LeftHandle4.setTextureSize(64, 64);
		setRotation(LeftHandle4, 0F, 0F, -0.260246F);
		BackLip = new ModelRenderer(this, 8, 58);
		BackLip.addBox(-3F, 0F, 0F, 8, 1, 2);
		BackLip.setRotationPoint(-1F, 3F, 2F);
		BackLip.setTextureSize(64, 64);
		BackLip.mirror = true;
		setRotation(BackLip, 0F, 0F, 0F);
		FrontLip = new ModelRenderer(this, 8, 58);
		FrontLip.addBox(-3F, 0F, -2F, 8, 1, 2);
		FrontLip.setRotationPoint(-1F, 3F, -2F);
		FrontLip.setTextureSize(64, 64);
		FrontLip.mirror = true;
		setRotation(FrontLip, 0F, 0F, 0F);
		RightLip = new ModelRenderer(this, 36, 0);
		RightLip.addBox(0F, 0F, -2F, 2, 1, 4);
		RightLip.setRotationPoint(2F, 3F, 0F);
		RightLip.setTextureSize(64, 64);
		RightLip.mirror = true;
		setRotation(RightLip, 0F, 0F, 0F);
		LeftLip = new ModelRenderer(this, 36, 0);
		LeftLip.addBox(-2F, 0F, -2F, 2, 1, 4);
		LeftLip.setRotationPoint(-2F, 3F, 0F);
		LeftLip.setTextureSize(64, 64);
		LeftLip.mirror = true;
		setRotation(LeftLip, 0F, 0F, 0F);
	}

	public void render(float f5) {
		Base.render(f5);
		Body.render(f5);
		Neck.render(f5);
		RightHandle1.render(f5);
		RightHandle2.render(f5);
		LeftHandle1.render(f5);
		LeftHandle2.render(f5);
		RightHandle3.render(f5);
		RightHandle4.render(f5);
		LeftHandle3.render(f5);
		LeftHandle4.render(f5);
		BackLip.render(f5);
		FrontLip.render(f5);
		RightLip.render(f5);
		LeftLip.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
