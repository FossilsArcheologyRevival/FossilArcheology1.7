package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelAnubite extends ModelBiped {

	ModelRenderer WolfHead;
	ModelRenderer Nose;
	ModelRenderer Ear1;
	ModelRenderer Ear2;
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer sword;

	public ModelAnubite() {
		textureWidth = 128;
		textureHeight = 64;

		WolfHead = new ModelRenderer(this, 0, 0);
		WolfHead.addBox(-4F, -8F, -4F, 8, 8, 7);
		WolfHead.setRotationPoint(0F, -2F, 0F);
		WolfHead.setTextureSize(128, 64);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 31, 4);
		Nose.addBox(-2F, -5.5F, -8F, 4, 5, 5);
		Nose.setRotationPoint(0F, -2F, -0F);
		Nose.setTextureSize(128, 64);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 58, 14);
		Ear1.mirror = true;
		Ear1.addBox(-3.5F, -13F, 0F, 2, 5, 1);
		Ear1.setRotationPoint(0F, -2F, 0F);
		Ear1.setTextureSize(128, 64);
		setRotation(Ear1, 0F, 0F, 0F);
		Ear2 = new ModelRenderer(this, 58, 14);
		Ear2.addBox(1.5F, -13F, 0F, 2, 5, 1);
		Ear2.setRotationPoint(0F, -2F, 0F);
		Ear2.setTextureSize(128, 64);
		setRotation(Ear2, 0F, 0F, 0F);
		Ear2.mirror = true;
		body = new ModelRenderer(this, 16, 15);
		body.addBox(-4F, 0F, -2F, 8, 13, 4);
		body.setRotationPoint(0F, -2F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 15);
		rightleg.mirror = true;
		rightleg.addBox(-2F, 1F, -2F, 4, 13, 4);
		rightleg.setRotationPoint(-2F, 10F, 0F);
		rightleg.setTextureSize(128, 64);
		setRotation(rightleg, 0F, 0F, 0.0174533F);
		leftleg = new ModelRenderer(this, 0, 15);
		leftleg.addBox(-2F, 1F, -2F, 4, 13, 4);
		leftleg.setRotationPoint(2F, 10F, 0F);
		leftleg.setTextureSize(128, 64);
		setRotation(leftleg, 0F, 0F, -0.0174533F);
		rightarm = new ModelRenderer(this, 40, 15);
		rightarm.addBox(-3F, -2F, -2F, 4, 13, 4);
		rightarm.setRotationPoint(-5F, 0F, 0F);
		rightarm.setTextureSize(128, 64);
		setRotation(rightarm, 0F, 0F, 0.0349066F);
		rightarm.mirror = true;
		leftarm = new ModelRenderer(this, 40, 15);
		leftarm.mirror = true;
		leftarm.addBox(-1F, -2F, -2F, 4, 13, 4);
		leftarm.setRotationPoint(5F, 0F, 0F);
		leftarm.setTextureSize(128, 64);
		setRotation(leftarm, 0F, 0F, -0.0349066F);
		sword = new ModelRenderer(this, 0, 16);
		sword.addBox(0F, -16F, -16F, 0, 16, 16);
		sword.setRotationPoint(-1F, 12F, 5F);
		sword.setTextureSize(128, 64);
		sword.mirror = true;
		setRotation(sword, 0.4712389F, 0F, 0F);
		rightarm.addChild(sword);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		WolfHead.render(f5);
		Nose.render(f5);
		Ear1.render(f5);
		Ear2.render(f5);
		body.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.WolfHead.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.WolfHead.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.Nose.rotateAngleY = this.WolfHead.rotateAngleY;
		this.Nose.rotateAngleX = this.WolfHead.rotateAngleX;
		this.Ear1.rotateAngleY = this.WolfHead.rotateAngleY;
		this.Ear1.rotateAngleX = this.WolfHead.rotateAngleX;
		this.Ear2.rotateAngleY = this.WolfHead.rotateAngleY;
		this.Ear2.rotateAngleX = this.WolfHead.rotateAngleX;
		this.rightarm.rotateAngleX = this.bipedRightArm.rotateAngleX;
		this.leftarm.rotateAngleX = this.bipedLeftArm.rotateAngleX;
		this.rightarm.rotateAngleY = this.bipedRightArm.rotateAngleY;
		this.leftarm.rotateAngleY = this.bipedLeftArm.rotateAngleY;
		this.rightleg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
		this.leftleg.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
	}

	public void renderBlock(float f5) {
		WolfHead.render(f5);
		Nose.render(f5);
		Ear1.render(f5);
		Ear2.render(f5);
		body.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		rightarm.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
		leftarm.render(f5);
	}

}
