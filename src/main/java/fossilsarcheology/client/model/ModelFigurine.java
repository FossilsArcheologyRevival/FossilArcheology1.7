package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFigurine extends ModelBase {

	ModelRenderer Body;
	ModelRenderer Head;
	ModelRenderer ArmLeft;
	ModelRenderer ArmRight;

	public ModelFigurine() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new ModelRenderer(this, 0, 14);
		Body.addBox(-5F, -9F, -4F, 10, 9, 8);
		Body.setRotationPoint(0F, 24F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-3.5F, -5F, -3.5F, 7, 7, 7);
		Head.setRotationPoint(0F, 13F, 0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		ArmLeft = new ModelRenderer(this, 36, 21);
		ArmLeft.addBox(5F, -1F, -1.5F, 2, 8, 3);
		ArmLeft.setRotationPoint(0F, 17F, 0F);
		ArmLeft.setTextureSize(64, 32);
		ArmLeft.mirror = true;
		ArmRight = new ModelRenderer(this, 36, 21);
		ArmRight.addBox(-7F, -1F, -1.5F, 2, 8, 3);
		ArmRight.setRotationPoint(0F, 17F, 0F);
		ArmRight.setTextureSize(64, 32);
		ArmRight.mirror = true;
	}

	public void render(float f5, boolean broken) {
		Body.render(f5);
		Head.render(f5);
		if (!broken) {
			ArmLeft.render(f5);
			ArmRight.render(f5);
		}
	}

}