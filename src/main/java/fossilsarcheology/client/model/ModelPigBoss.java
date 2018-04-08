package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPigBoss extends ModelBiped {
	public ModelRenderer LeftWing1;
	public ModelRenderer RightWing1;
	public ModelRenderer Mouth;
	public ModelRenderer field_78114_d;
	public ModelRenderer Hornleft;
	public ModelRenderer HornLeft;
	public ModelRenderer LeftTusk;
	public ModelRenderer RightTusk;
	public ModelRenderer LeftWing2;
	public ModelRenderer LeftWing3;
	public ModelRenderer RightWing2;
	public ModelRenderer RightWing3;

	public ModelPigBoss() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.HornLeft = new ModelRenderer(this, 0, 17);
		this.HornLeft.setRotationPoint(-3.0F, -8.0F, 3.5F);
		this.HornLeft.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 47, 0);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.setRotationPoint(2.0F, 14.0F, 0.0F);
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Hornleft = new ModelRenderer(this, 0, 17);
		this.Hornleft.setRotationPoint(3.0F, -8.0F, 3.5F);
		this.Hornleft.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.RightWing3 = new ModelRenderer(this, 36, 43);
		this.RightWing3.mirror = true;
		this.RightWing3.setRotationPoint(-12.0F, 1.0F, 0.0F);
		this.RightWing3.addBox(-6.0F, -4.0F, 0.0F, 6, 10, 0, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 0, 0);
		this.bipedLeftArm.setRotationPoint(4.0F, 5.5F, 0.0F);
		this.bipedLeftArm.addBox(0.0F, -1.5F, -2.0F, 4, 10, 4, 0.0F);
		this.LeftWing1 = new ModelRenderer(this, 0, 42);
		this.LeftWing1.setRotationPoint(2.0F, 7.5F, 1.9F);
		this.LeftWing1.addBox(0.0F, -3.0F, 0.0F, 6, 10, 0, 0.0F);
		this.setRotateAngle(LeftWing1, 0.0F, -0.4363323129985824F, 0.0F);
		this.LeftWing2 = new ModelRenderer(this, 12, 43);
		this.LeftWing2.setRotationPoint(6.0F, 0.0F, 0.0F);
		this.LeftWing2.addBox(0.0F, -4.0F, 0.0F, 12, 12, 0, 0.0F);
		this.setRotateAngle(LeftWing2, 0.0F, 0.4363323129985824F, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 47, 0);
		this.bipedRightLeg.setRotationPoint(-2.0F, 14.0F, 0.0F);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.LeftWing3 = new ModelRenderer(this, 36, 43);
		this.LeftWing3.setRotationPoint(12.0F, 1.0F, 0.0F);
		this.LeftWing3.addBox(0.0F, -4.0F, 0.0F, 6, 10, 0, 0.0F);
		this.bipedBody = new ModelRenderer(this, 40, 18);
		this.bipedBody.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.bipedBody.addBox(-4.0F, -4.0F, -2.0F, 8, 10, 4, 0.0F);
		this.bipedHead = new ModelRenderer(this, 0, 16);
		this.bipedHead.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.LeftTusk = new ModelRenderer(this, 0, 0);
		this.LeftTusk.setRotationPoint(2.5F, -3.0F, -1.5F);
		this.LeftTusk.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.Mouth = new ModelRenderer(this, 26, 17);
		this.Mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.Mouth.addBox(-3.0F, -3.0F, -2.0F, 6, 3, 2, 0.0F);
		this.RightWing2 = new ModelRenderer(this, 12, 43);
		this.RightWing2.mirror = true;
		this.RightWing2.setRotationPoint(-6.0F, 0.0F, 0.0F);
		this.RightWing2.addBox(-12.0F, -4.0F, 0.0F, 12, 12, 0, 0.0F);
		this.setRotateAngle(RightWing2, 0.0F, -0.4363323129985824F, 0.0F);
		this.field_78114_d = new ModelRenderer(this, 24, 2);
		this.field_78114_d.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.field_78114_d.addBox(-2.0F, -3.0F, -8.0F, 4, 3, 12, 0.0F);
		this.RightTusk = new ModelRenderer(this, 0, 0);
		this.RightTusk.setRotationPoint(-2.5F, -3.0F, -1.5F);
		this.RightTusk.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 16, 0);
		this.bipedRightArm.setRotationPoint(-4.0F, 5.5F, 0.0F);
		this.bipedRightArm.addBox(-4.0F, -1.5F, -2.0F, 4, 10, 4, 0.0F);
		this.RightWing1 = new ModelRenderer(this, 0, 42);
		this.RightWing1.mirror = true;
		this.RightWing1.setRotationPoint(-2.0F, 7.5F, 1.9F);
		this.RightWing1.addBox(-6.0F, -3.0F, 0.0F, 6, 10, 0, 0.0F);
		this.setRotateAngle(RightWing1, 0.0F, 0.4553564018453205F, 0.0F);
		this.bipedHead.addChild(this.HornLeft);
		this.bipedHead.addChild(this.Hornleft);
		this.RightWing2.addChild(this.RightWing3);
		this.LeftWing1.addChild(this.LeftWing2);
		this.LeftWing2.addChild(this.LeftWing3);
		this.Mouth.addChild(this.LeftTusk);
		this.bipedHead.addChild(this.Mouth);
		this.RightWing1.addChild(this.RightWing2);
		this.bipedHead.addChild(this.field_78114_d);
		this.Mouth.addChild(this.RightTusk);
	}


	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bipedLeftLeg.render(f5);
		this.bipedLeftArm.render(f5);
		this.bipedRightLeg.render(f5);
		this.bipedBody.render(f5);
		this.bipedHead.render(f5);
		this.bipedRightArm.render(f5);
		EntityAnu anu = (EntityAnu) entity;
		if (anu.getAttackMode() == 1) {
			LeftWing1.render(f5);
			RightWing1.render(f5);
		}

	}

	public void renderBlock(float f5) {
		this.bipedLeftLeg.render(f5);
		this.bipedLeftArm.render(f5);
		this.bipedRightLeg.render(f5);
		this.bipedBody.render(f5);
		this.bipedHead.render(f5);
		this.bipedRightArm.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		EntityAnu anu = (EntityAnu) entity;

		float motion = 1.0F;
		this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F / motion;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F / motion;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 / motion;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 / motion;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;
		this.bipedRightLeg.rotateAngleZ = 0.0F;
		this.bipedLeftLeg.rotateAngleZ = 0.0F;

		if (anu.getAttackMode() != 1) {
			this.bipedLeftArm.rotateAngleX = MathHelper.cos((f) * 0.63330555F + 1) * 1.0F * f1;
			this.bipedRightArm.rotateAngleX = MathHelper.cos((f) * 0.63330555F + (float) Math.PI) * 1.0F * f1;
			this.bipedLeftLeg.rotateAngleX = MathHelper.cos((f) * 0.63330555F + (float) Math.PI + 2) * 1.0F * f1;
			this.bipedRightLeg.rotateAngleX = MathHelper.cos((f) * 0.63330555F + 1) * 1.0F * f1;
		}
		if (anu.getAttackMode() == 1) {
			this.bipedHead.rotateAngleX = (float) Math.toRadians(-35D);
			this.bipedHead.rotateAngleY = 0;
			this.bipedLeftArm.rotateAngleX = (float) Math.toRadians(-125D);
			this.bipedRightArm.rotateAngleX = 0;
			this.bipedRightLeg.rotateAngleX = 0;
			this.bipedLeftLeg.rotateAngleX = 0;

		} else {
			this.bipedHead.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.bipedHead.rotateAngleY = f3 / (180F / (float) Math.PI);
		}
		this.LeftWing2.rotateAngleY = 0.5F * MathHelper.sin(f1 * 0.1F + (f));
		this.RightWing2.rotateAngleY = -0.5F * MathHelper.sin(f1 * 0.1F + (f));
		this.LeftWing3.rotateAngleY = 0.5F * MathHelper.sin(f1 * 0.1F + (f + 1));
		this.RightWing3.rotateAngleY = -0.5F * MathHelper.sin(f1 * 0.1F + (f + 1));

	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
