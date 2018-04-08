package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelVaseVolute extends ModelBase {

	ModelRenderer Base;
	ModelRenderer Body;
	ModelRenderer Neck;
	ModelRenderer LipF;
	ModelRenderer LipB;
	ModelRenderer LipR;
	ModelRenderer LipL;
	ModelRenderer HandleL2;
	ModelRenderer HandleL1;
	ModelRenderer HandleR1;
	ModelRenderer HandleR2;

	public ModelVaseVolute() {
		textureWidth = 64;
		textureHeight = 64;

		Base = new ModelRenderer(this, 0, 53);
		Base.addBox(-5F, 0F, -5F, 10, 1, 10);
		Base.setRotationPoint(0F, 23F, 0F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 33);
		Body.addBox(-6F, 0F, -6F, 12, 7, 12);
		Body.setRotationPoint(0F, 16F, 0F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 17);
		Neck.addBox(-5F, 0F, -5F, 10, 5, 10);
		Neck.setRotationPoint(0F, 11F, 0F);
		Neck.setTextureSize(64, 64);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		LipF = new ModelRenderer(this, 0, 12);
		LipF.addBox(-6F, 0F, -2F, 12, 1, 3);
		LipF.setRotationPoint(0F, 10F, -4F);
		LipF.setTextureSize(64, 64);
		LipF.mirror = true;
		setRotation(LipF, 0F, 0F, 0F);
		LipB = new ModelRenderer(this, 0, 12);
		LipB.addBox(-6F, 0F, -1F, 12, 1, 3);
		LipB.setRotationPoint(0F, 10F, 4F);
		LipB.setTextureSize(64, 64);
		LipB.mirror = true;
		setRotation(LipB, 0F, 0F, 0F);
		LipR = new ModelRenderer(this, 0, 4);
		LipR.addBox(-2F, 0F, -3F, 3, 1, 6);
		LipR.setRotationPoint(-4F, 10F, 0F);
		LipR.setTextureSize(64, 64);
		LipR.mirror = true;
		setRotation(LipR, 0F, 0F, 0F);
		LipL = new ModelRenderer(this, 0, 4);
		LipL.addBox(-1F, 0F, -3F, 3, 1, 6);
		LipL.setRotationPoint(4F, 10F, 0F);
		LipL.setTextureSize(64, 64);
		LipL.mirror = true;
		setRotation(LipL, 0F, 0F, 0F);
		HandleL2 = new ModelRenderer(this, 41, 16);
		HandleL2.addBox(-2F, -2F, -1F, 2, 2, 2);
		HandleL2.setRotationPoint(7F, 10F, 0F);
		HandleL2.setTextureSize(64, 64);
		HandleL2.mirror = true;
		setRotation(HandleL2, 0F, 0F, 0F);
		HandleL1 = new ModelRenderer(this, 41, 21);
		HandleL1.addBox(0F, -5F, -1F, 1, 7, 2);
		HandleL1.setRotationPoint(6F, 15F, 0F);
		HandleL1.setTextureSize(64, 64);
		HandleL1.mirror = true;
		setRotation(HandleL1, 0F, 0F, 0F);
		HandleR1 = new ModelRenderer(this, 41, 21);
		HandleR1.addBox(-1F, -5F, -1F, 1, 7, 2);
		HandleR1.setRotationPoint(-6F, 15F, 0F);
		HandleR1.setTextureSize(64, 64);
		HandleR1.mirror = true;
		setRotation(HandleR1, 0F, 0F, 0F);
		HandleR1.mirror = false;
		HandleR2 = new ModelRenderer(this, 41, 16);
		HandleR2.addBox(0F, -2F, -1F, 2, 2, 2);
		HandleR2.setRotationPoint(-7F, 10F, 0F);
		HandleR2.setTextureSize(64, 64);
		HandleR2.mirror = true;
		setRotation(HandleR2, 0F, 0F, 0F);
		HandleR2.mirror = false;
	}

	public void render(float f5) {
		Base.render(f5);
		Body.render(f5);
		Neck.render(f5);
		LipF.render(f5);
		LipB.render(f5);
		LipR.render(f5);
		LipL.render(f5);
		HandleL2.render(f5);
		HandleL1.render(f5);
		HandleR1.render(f5);
		HandleR2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
