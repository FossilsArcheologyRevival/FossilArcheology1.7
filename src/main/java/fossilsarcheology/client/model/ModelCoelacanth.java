package fossilsarcheology.client.model;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoelacanth extends AdvancedModelBase {
	public AdvancedModelRenderer Body;
	public AdvancedModelRenderer Head;
	public AdvancedModelRenderer Tail;
	public AdvancedModelRenderer RightFrontFlipper;
	public AdvancedModelRenderer LeftFrontFlipper;
	public AdvancedModelRenderer BackFlipper;
	public AdvancedModelRenderer DorsalFin2;
	public AdvancedModelRenderer UpperJaw;
	public AdvancedModelRenderer LowerJaw;
	public AdvancedModelRenderer Tail2;
	public AdvancedModelRenderer BackFlipper_1;
	public AdvancedModelRenderer DorsalFin2_1;
	public AdvancedModelRenderer MiddleTailFin;
	public AdvancedModelRenderer UpperTailFin;
	public AdvancedModelRenderer LowerTailFin;

	public ModelCoelacanth() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.Head = new AdvancedModelRenderer(this, 40, 47);
		this.Head.setRotationPoint(0.0F, 0.0F, -0.2F);
		this.Head.addBox(-3.0F, -3.3F, -6.0F, 6, 8, 6, 0.0F);
		this.setRotateAngle(Head, 0.045553093477052F, -0.0F, 0.0F);
		this.DorsalFin2_1 = new AdvancedModelRenderer(this, 7, 0);
		this.DorsalFin2_1.setRotationPoint(0.0F, -2.8F, 6.5F);
		this.DorsalFin2_1.addBox(-0.5F, -5.0F, -1.5F, 1, 5, 3, 0.0F);
		this.setRotateAngle(DorsalFin2_1, -0.9560913642424937F, -0.0F, 0.0F);
		this.LowerTailFin = new AdvancedModelRenderer(this, 29, 21);
		this.LowerTailFin.setRotationPoint(0.0F, -3.299999999999997F, -1.4000000000000021F);
		this.LowerTailFin.addBox(-0.5F, 0.5F, -2.8F, 1, 8, 4, 0.0F);
		this.setRotateAngle(LowerTailFin, 0.15934856070708237F, 0.0F, 0.0F);
		this.DorsalFin2 = new AdvancedModelRenderer(this, 7, 11);
		this.DorsalFin2.setRotationPoint(0.0F, -2.1F, 5.0F);
		this.DorsalFin2.addBox(-0.5F, -5.9F, -2.8F, 1, 8, 5, 0.0F);
		this.setRotateAngle(DorsalFin2, -1.0016444577195458F, -0.0F, 0.0F);
		this.Tail2 = new AdvancedModelRenderer(this, 39, 23);
		this.Tail2.setRotationPoint(0.0F, 0.0F, 12.0F);
		this.Tail2.addBox(-2.0F, -3.7F, 0.0F, 4, 7, 5, 0.0F);
		this.BackFlipper = new AdvancedModelRenderer(this, 40, 4);
		this.BackFlipper.mirror = true;
		this.BackFlipper.setRotationPoint(0.0F, 4.8F, 8.0F);
		this.BackFlipper.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(BackFlipper, 0.40980330836826856F, -0.0F, 0.0F);
		this.LeftFrontFlipper = new AdvancedModelRenderer(this, 20, 22);
		this.LeftFrontFlipper.setRotationPoint(3.0F, 3.3F, 1.6F);
		this.LeftFrontFlipper.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3, 0.0F);
		this.setRotateAngle(LeftFrontFlipper, 0.5235987755982988F, -0.0F, -0.5235987755982988F);
		this.UpperJaw = new AdvancedModelRenderer(this, 20, 35);
		this.UpperJaw.setRotationPoint(0.0F, -2.2F, -5.1F);
		this.UpperJaw.addBox(-2.5F, -1.0F, -5.9F, 5, 4, 6, 0.0F);
		this.setRotateAngle(UpperJaw, 0.22759093446006054F, -0.0F, 0.0F);
		this.Body = new AdvancedModelRenderer(this, 66, 36);
		this.Body.setRotationPoint(0.0F, 18.0F, -10.0F);
		this.Body.addBox(-3.5F, -3.7F, 0.0F, 7, 9, 12, 0.0F);
		this.MiddleTailFin = new AdvancedModelRenderer(this, 36, 11);
		this.MiddleTailFin.setRotationPoint(0.0F, 0.2F, 5.2F);
		this.MiddleTailFin.addBox(-0.5F, -0.37F, -2.5F, 1, 6, 6, 0.0F);
		this.setRotateAngle(MiddleTailFin, 1.5707963267948966F, -0.0F, 0.0F);
		this.LowerJaw = new AdvancedModelRenderer(this, 48, 35);
		this.LowerJaw.setRotationPoint(0.0F, 2.2F, -6.0F);
		this.LowerJaw.addBox(-2.0F, -1.0F, -4.0F, 4, 3, 5, 0.0F);
		this.setRotateAngle(LowerJaw, -0.045553093477052F, -0.0F, 0.0F);
		this.Tail = new AdvancedModelRenderer(this, 62, 15);
		this.Tail.setRotationPoint(0.0F, 1.1F, 11.5F);
		this.Tail.addBox(-3.0F, -4.3F, 0.0F, 6, 8, 12, 0.0F);
		this.UpperTailFin = new AdvancedModelRenderer(this, 51, 11);
		this.UpperTailFin.setRotationPoint(0.0F, -3.599999999999998F, 1.5F);
		this.UpperTailFin.addBox(-0.5F, 0.4F, 0.3F, 1, 8, 4, 0.0F);
		this.setRotateAngle(UpperTailFin, -0.20490165418413425F, 0.0F, 0.0F);
		this.RightFrontFlipper = new AdvancedModelRenderer(this, 20, 22);
		this.RightFrontFlipper.mirror = true;
		this.RightFrontFlipper.setRotationPoint(-3.0F, 3.3F, 1.6F);
		this.RightFrontFlipper.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3, 0.0F);
		this.setRotateAngle(RightFrontFlipper, 0.5235987755982988F, -0.0F, 0.5235987755982988F);
		this.BackFlipper_1 = new AdvancedModelRenderer(this, 40, 4);
		this.BackFlipper_1.mirror = true;
		this.BackFlipper_1.setRotationPoint(0.0F, 3.2F, 8.3F);
		this.BackFlipper_1.addBox(-0.5F, -0.8F, -1.2F, 1, 4, 3, 0.0F);
		this.setRotateAngle(BackFlipper_1, 0.5235987755982988F, -0.0F, 0.0F);
		this.Body.addChild(this.Head);
		this.Tail.addChild(this.DorsalFin2_1);
		this.MiddleTailFin.addChild(this.LowerTailFin);
		this.Body.addChild(this.DorsalFin2);
		this.Tail.addChild(this.Tail2);
		this.Body.addChild(this.BackFlipper);
		this.Body.addChild(this.LeftFrontFlipper);
		this.Head.addChild(this.UpperJaw);
		this.Tail2.addChild(this.MiddleTailFin);
		this.Head.addChild(this.LowerJaw);
		this.Body.addChild(this.Tail);
		this.MiddleTailFin.addChild(this.UpperTailFin);
		this.Body.addChild(this.RightFrontFlipper);
		this.Tail.addChild(this.BackFlipper_1);
		this.updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Body.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.resetToDefaultPose();
		this.MiddleTailFin.setScale(1.1F, 1.1F, 1.1F);
		AdvancedModelRenderer[] tailParts = {this.Tail, this.Tail2, this.MiddleTailFin};
		float speed = 0.4F;
		this.chainWave(tailParts, speed, 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, 0.5F, -3, entity.ticksExisted, 1);
		this.swing(Body, speed, 0.3F, true, 0, 0, entity.ticksExisted, 1);
		this.flap(RightFrontFlipper, speed, 1.3F, true, 0, -1.3F, entity.ticksExisted, 1);
		this.flap(LeftFrontFlipper, speed, 1.3F, false, 0, -1.3F, entity.ticksExisted, 1);
		if (!entity.isInWater()) {
			this.Body.rotateAngleZ = (float) Math.toRadians(90);
			this.bob(Body, -speed, 5F, false, entity.ticksExisted, 1);
			this.walk(LowerJaw, speed, 0.4F, true, 0, -0.5F, entity.ticksExisted, 1);
		}
	}

	public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
