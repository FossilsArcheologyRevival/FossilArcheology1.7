package fossilsarcheology.client.model;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSturgeon extends AdvancedModelBase {
	public AdvancedModelRenderer Body;
	public AdvancedModelRenderer Head;
	public AdvancedModelRenderer Tail;
	public AdvancedModelRenderer LeftFrontFlipper;
	public AdvancedModelRenderer RightFrontFlipper;
	public AdvancedModelRenderer UpperJaw;
	public AdvancedModelRenderer BottomJaw;
	public AdvancedModelRenderer Tail2;
	public AdvancedModelRenderer LeftBackFlipper;
	public AdvancedModelRenderer RightBackFlipper;
	public AdvancedModelRenderer Tail3;
	public AdvancedModelRenderer DorsalFin;
	public AdvancedModelRenderer VentralFin;
	public AdvancedModelRenderer MiddleTailFin;
	public AdvancedModelRenderer UpperTailFin;
	public AdvancedModelRenderer LowerTailFin;

	public ModelSturgeon() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.Head = new AdvancedModelRenderer(this, 40, 47);
		this.Head.setRotationPoint(0.0F, 0.0F, 0.3F);
		this.Head.addBox(-4.5F, -3.0F, -6.0F, 9, 6, 6, 0.0F);
		this.setRotateAngle(Head, 0.09407324668249435F, -0.0F, 0.0F);
		this.RightBackFlipper = new AdvancedModelRenderer(this, 40, 4);
		this.RightBackFlipper.mirror = true;
		this.RightBackFlipper.setRotationPoint(-2.5F, 1.1F, 11.0F);
		this.RightBackFlipper.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
		this.setRotateAngle(RightBackFlipper, 0.5235987755982988F, -0.0F, 0.5235987755982988F);
		this.DorsalFin = new AdvancedModelRenderer(this, 7, 11);
		this.DorsalFin.setRotationPoint(0.0F, -1.8F, 0.0F);
		this.DorsalFin.addBox(-0.5F, -5.0F, -1.5F, 1, 5, 3, 0.0F);
		this.setRotateAngle(DorsalFin, -1.0780898789568973F, -0.0F, 0.0F);
		this.LeftFrontFlipper = new AdvancedModelRenderer(this, 20, 22);
		this.LeftFrontFlipper.setRotationPoint(3.0F, 0.7F, 3.0F);
		this.LeftFrontFlipper.addBox(-0.5F, 1.0F, -1.5F, 1, 6, 3, 0.0F);
		this.setRotateAngle(LeftFrontFlipper, 0.5235987755982988F, -0.0F, -0.5235987755982988F);
		this.Tail = new AdvancedModelRenderer(this, 62, 15);
		this.Tail.setRotationPoint(0.0F, 0.6F, 11.5F);
		this.Tail.addBox(-3.0F, -3.5F, 0.0F, 6, 6, 13, 0.0F);
		this.UpperJaw = new AdvancedModelRenderer(this, 20, 35);
		this.UpperJaw.setRotationPoint(0.0F, 0.0F, -5.5F);
		this.UpperJaw.addBox(-3.0F, -1.0F, -8.0F, 6, 3, 8, 0.0F);
		this.Tail2 = new AdvancedModelRenderer(this, 39, 23);
		this.Tail2.setRotationPoint(0.0F, 0.0F, 12.9F);
		this.Tail2.addBox(-2.5F, -3.1F, -0.2F, 5, 5, 5, 0.0F);
		this.VentralFin = new AdvancedModelRenderer(this, 7, 2);
		this.VentralFin.setRotationPoint(0.0F, 0.8F, 1.0F);
		this.VentralFin.addBox(-0.5F, -5.0F, -1.5F, 1, 5, 2, 0.0F);
		this.setRotateAngle(VentralFin, -2.267880830041432F, -0.0F, 0.0F);
		this.RightFrontFlipper = new AdvancedModelRenderer(this, 20, 22);
		this.RightFrontFlipper.mirror = true;
		this.RightFrontFlipper.setRotationPoint(-3.0F, 0.7F, 3.0F);
		this.RightFrontFlipper.addBox(-0.5F, 1.0F, -1.5F, 1, 6, 3, 0.0F);
		this.setRotateAngle(RightFrontFlipper, 0.5235987755982988F, -0.0F, 0.5235987755982988F);
		this.LeftBackFlipper = new AdvancedModelRenderer(this, 40, 4);
		this.LeftBackFlipper.setRotationPoint(2.5F, 1.1F, 11.0F);
		this.LeftBackFlipper.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
		this.setRotateAngle(LeftBackFlipper, 0.5235987755982988F, -0.0F, -0.5235987755982988F);
		this.Body = new AdvancedModelRenderer(this, 70, 36);
		this.Body.setRotationPoint(0.0F, 18.0F, -12.0F);
		this.Body.addBox(-4.0F, -3.0F, 0.0F, 8, 7, 12, 0.0F);
		this.BottomJaw = new AdvancedModelRenderer(this, 48, 35);
		this.BottomJaw.setRotationPoint(0.0F, 2.0F, -5.0F);
		this.BottomJaw.addBox(-2.5F, 0.0F, -4.8F, 5, 1, 5, 0.0F);
		this.setRotateAngle(BottomJaw, -0.091106186954104F, -0.0F, 0.0F);
		this.MiddleTailFin = new AdvancedModelRenderer(this, 36, 11);
		this.MiddleTailFin.mirror = true;
		this.MiddleTailFin.setRotationPoint(0.0F, 0.5F, 5.3F);
		this.MiddleTailFin.addBox(-0.5F, -0.37F, -3.0F, 1, 3, 5, 0.0F);
		this.setRotateAngle(MiddleTailFin, 1.5707963267948966F, 0.0F, 0.0F);
		this.UpperTailFin = new AdvancedModelRenderer(this, 51, 11);
		this.UpperTailFin.setRotationPoint(0.0F, 0.09999999999999787F, -0.5F);
		this.UpperTailFin.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 3, 0.0F);
		this.setRotateAngle(UpperTailFin, 0.7517133088339576F, 0.0F, 0.0F);
		this.LowerTailFin = new AdvancedModelRenderer(this, 29, 21);
		this.LowerTailFin.setRotationPoint(0.0F, 0.6999999999999993F, -0.3999999999999986F);
		this.LowerTailFin.addBox(-0.5F, 0.0F, -2.5F, 1, 5, 3, 0.0F);
		this.setRotateAngle(LowerTailFin, -0.9016370915802705F, 0.0F, 0.0F);
		this.Tail3 = new AdvancedModelRenderer(this, 61, 4);
		this.Tail3.setRotationPoint(0.0F, -1.3F, 4.6F);
		this.Tail3.addBox(-1.5F, -1.3F, 0.0F, 3, 4, 5, 0.0F);
		this.Body.addChild(this.Head);
		this.Tail.addChild(this.RightBackFlipper);
		this.Tail2.addChild(this.DorsalFin);
		this.Body.addChild(this.LeftFrontFlipper);
		this.Body.addChild(this.Tail);
		this.Head.addChild(this.UpperJaw);
		this.Tail.addChild(this.Tail2);
		this.Tail2.addChild(this.VentralFin);
		this.Body.addChild(this.RightFrontFlipper);
		this.Tail.addChild(this.LeftBackFlipper);
		this.Head.addChild(this.BottomJaw);
		this.Tail3.addChild(this.MiddleTailFin);
		this.MiddleTailFin.addChild(this.UpperTailFin);
		this.MiddleTailFin.addChild(this.LowerTailFin);
		this.Tail2.addChild(this.Tail3);
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
		this.walk(BottomJaw, 0.1F, 0.2F, true, 0, -0.2F, entity.ticksExisted, 1);
		if (!entity.isInWater()) {
			this.Body.rotateAngleZ = (float) Math.toRadians(90);
			this.bob(Body, -speed, 5F, false, entity.ticksExisted, 1);
		}
	}

	public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
