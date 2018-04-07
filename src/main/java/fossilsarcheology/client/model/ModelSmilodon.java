package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.EntitySmilodon;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelSmilodon extends ModelPrehistoric {
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer leftFrontThigh;
	public AdvancedModelRenderer rightFrontThigh;
	public AdvancedModelRenderer rightHindThigh;
	public AdvancedModelRenderer leftHindThigh;
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer tail;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer leftEar;
	public AdvancedModelRenderer rightEar;
	public AdvancedModelRenderer nose;
	public AdvancedModelRenderer upperRightFang;
	public AdvancedModelRenderer upperLeftFang;
	public AdvancedModelRenderer leftFrontLeg;
	public AdvancedModelRenderer rightFrontLeg;
	public AdvancedModelRenderer rightHindLeg;
	public AdvancedModelRenderer leftHindLeg;
	private ModelAnimator animator;

	public ModelSmilodon() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.lowerJaw = new AdvancedModelRenderer(this, 17, 10);
		this.lowerJaw.setRotationPoint(0.0F, 1.2F, -2.4F);
		this.lowerJaw.addBox(-1.0F, 0.5F, -3.0F, 2, 1, 3, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 20, 16);
		this.neck.setRotationPoint(0.0F, -0.4F, -6.5F);
		this.neck.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(neck, -0.136659280431156F, -0.0F, 0.0F);
		this.leftEar = new AdvancedModelRenderer(this, 6, 8);
		this.leftEar.setRotationPoint(2.1F, -1.2F, -0.5F);
		this.leftEar.addBox(-0.5F, -1.0F, -1.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(leftEar, -0.05235987755982988F, -0.0F, 0.0F);
		this.leftHindThigh = new AdvancedModelRenderer(this, 42, 0);
		this.leftHindThigh.setRotationPoint(-1.5F, 14.0F, 5.8F);
		this.leftHindThigh.addBox(-2.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
		this.rightEar = new AdvancedModelRenderer(this, 6, 8);
		this.rightEar.mirror = true;
		this.rightEar.setRotationPoint(-2.1F, -1.2F, -0.5F);
		this.rightEar.addBox(-0.5F, -1.0F, -1.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(rightEar, -0.05235987755982988F, -0.0F, 0.0F);
		this.rightHindLeg = new AdvancedModelRenderer(this, 34, 8);
		this.rightHindLeg.setRotationPoint(1.0F, 4.0F, 0.8F);
		this.rightHindLeg.addBox(-1.1F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
		this.rightFrontThigh = new AdvancedModelRenderer(this, 52, 0);
		this.rightFrontThigh.mirror = true;
		this.rightFrontThigh.setRotationPoint(-2.0F, 12.5F, -4.5F);
		this.rightFrontThigh.addBox(-2.0F, -1.0F, -1.5F, 2, 7, 3, 0.0F);
		this.rightFrontLeg = new AdvancedModelRenderer(this, 34, 0);
		this.rightFrontLeg.mirror = true;
		this.rightFrontLeg.setRotationPoint(-0.9F, 5.5F, -0.4F);
		this.rightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
		this.upperBody = new AdvancedModelRenderer(this, 30, 19);
		this.upperBody.setRotationPoint(0.0F, 2.4F, 1.5F);
		this.upperBody.addBox(-3.0F, -2.5F, -7.0F, 6, 6, 7, 0.0F);
		this.setRotateAngle(upperBody, -0.041713369122664476F, -0.0F, 0.0F);
		this.rightHindThigh = new AdvancedModelRenderer(this, 42, 0);
		this.rightHindThigh.setRotationPoint(1.5F, 14.0F, 5.8F);
		this.rightHindThigh.addBox(0.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
		this.nose = new AdvancedModelRenderer(this, 18, 0);
		this.nose.setRotationPoint(0.0F, -1.7F, -0.2F);
		this.nose.addBox(-1.0F, -0.5F, -3.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(nose, 0.136659280431156F, -0.0F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -0.4F, -3.3F);
		this.head.addBox(-2.5F, -1.5F, -3.0F, 5, 4, 3, 0.0F);
		this.setRotateAngle(head, 0.4553564018453205F, -0.0F, 0.0F);
		this.upperLeftFang = new AdvancedModelRenderer(this, 45, 14);
		this.upperLeftFang.setRotationPoint(1.0F, 1.0F, -2.12F);
		this.upperLeftFang.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(upperLeftFang, 0.004537856055185257F, -0.0F, 0.0F);
		this.leftFrontThigh = new AdvancedModelRenderer(this, 52, 0);
		this.leftFrontThigh.setRotationPoint(2.0F, 12.5F, -4.5F);
		this.leftFrontThigh.addBox(0.0F, -1.0F, -1.5F, 2, 7, 3, 0.0F);
		this.tail = new AdvancedModelRenderer(this, 44, 9);
		this.tail.setRotationPoint(0.0F, 0.4F, 8.1F);
		this.tail.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tail, -0.6373942428283291F, 3.141592653589793F, 0.0F);
		this.upperRightFang = new AdvancedModelRenderer(this, 45, 14);
		this.upperRightFang.mirror = true;
		this.upperRightFang.setRotationPoint(-1.0F, 1.0F, -2.12F);
		this.upperRightFang.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(upperRightFang, 0.004537856055185257F, 0.0F, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 18, 5);
		this.upperJaw.setRotationPoint(0.0F, 0.9F, -2.6F);
		this.upperJaw.addBox(-2.0F, -1.0F, -3.0F, 4, 2, 3, 0.0F);
		this.setRotateAngle(upperJaw, 0.03944444109507184F, -0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 0, 18);
		this.lowerBody.setRotationPoint(0.0F, 12.0F, -1.0F);
		this.lowerBody.addBox(-2.5F, 0.0F, 0.0F, 5, 5, 9, 0.0F);
		this.setRotateAngle(lowerBody, 0.008726646259971648F, -0.0F, 0.0F);
		this.leftFrontLeg = new AdvancedModelRenderer(this, 34, 0);
		this.leftFrontLeg.setRotationPoint(0.9F, 5.5F, -0.4F);
		this.leftFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
		this.leftHindLeg = new AdvancedModelRenderer(this, 34, 8);
		this.leftHindLeg.mirror = true;
		this.leftHindLeg.setRotationPoint(-1.0F, 4.0F, 0.8F);
		this.leftHindLeg.addBox(-0.9F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
		this.head.addChild(this.lowerJaw);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.leftEar);
		this.head.addChild(this.rightEar);
		this.rightHindThigh.addChild(this.rightHindLeg);
		this.rightFrontThigh.addChild(this.rightFrontLeg);
		this.lowerBody.addChild(this.upperBody);
		this.upperJaw.addChild(this.nose);
		this.neck.addChild(this.head);
		this.upperJaw.addChild(this.upperLeftFang);
		this.lowerBody.addChild(this.tail);
		this.upperJaw.addChild(this.upperRightFang);
		this.head.addChild(this.upperJaw);
		this.leftFrontThigh.addChild(this.leftFrontLeg);
		this.leftHindThigh.addChild(this.leftHindLeg);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.leftHindThigh.render(f5);
		this.rightFrontThigh.render(f5);
		this.rightHindThigh.render(f5);
		this.leftFrontThigh.render(f5);
		this.lowerBody.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntitySmilodon smilodon = (EntitySmilodon) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(smilodon.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, head, -25, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 45, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(smilodon.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -31, 0, 0);
		ModelUtils.rotate(animator, head, -40, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 70, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, neck, 6, 0, 0);
		ModelUtils.rotate(animator, head, -14, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 70, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);
		float speed = 0.1F;
		float speed2 = 0.6F;
		float degree = 0.5F;
		this.chainWave(tailParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, degree * 0.15F, -2, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed, degree * 0.15F, 3, entity.ticksExisted, 1);
		this.bob(lowerBody, speed, degree * 0.4F, false, entity.ticksExisted, 1);
		this.walk(upperBody, speed, degree * 0.01F, true, 0.1F, 0F, entity.ticksExisted, 1);
		this.walk(rightFrontThigh, speed2, degree * 0.7F, false, 0F, 0.1F, f, f1);
		this.walk(leftFrontThigh, speed2, degree * 0.7F, true, 0F, 0.1F, f, f1);
		this.walk(leftHindThigh, speed2, degree * 0.6F, true, 0F, -0.1F, f, f1);
		this.walk(rightHindThigh, speed2, degree * 0.6F, false, 0F, -0.1F, f, f1);
		this.walk(leftFrontLeg, speed2, degree * 0.6F, true, 0F, -0.2F, f, f1);
		this.walk(rightFrontLeg, speed2, degree * 0.6F, false, 0F, -0.2F, f, f1);
		this.walk(leftHindLeg, speed2, degree * 0.6F, true, 0.3F, 0.4F, f, f1);
		this.walk(rightHindLeg, speed2, degree * 0.6F, false, 0.3F, 0.4F, f, f1);
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(tail, sitProgress, -((float) Math.toRadians(36.52D)), 0, 0);
			sitAnimationRotation(leftFrontThigh, sitProgress, -((float) Math.toRadians(10.0D)), (float) Math.toRadians(18.0D), -((float) Math.toRadians(13.0D)));
			sitAnimationRotation(leftHindThigh, sitProgress, -((float) Math.toRadians(55.0D)), (float) Math.toRadians(20.0D), 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(39.13D), 0, 0);
			sitAnimationRotation(rightEar, sitProgress, -((float) Math.toRadians(3.0D)), 0, 0);
			sitAnimationRotation(upperBody, sitProgress, (float) Math.toRadians(5.22D), 0, 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(5.22D)), 0, 0);
			sitAnimationRotation(upperRightFang, sitProgress, (float) Math.toRadians(0.26D), 0, 0);
			sitAnimationRotation(nose, sitProgress, (float) Math.toRadians(7.83D), 0, 0);
			sitAnimationRotation(upperJaw, sitProgress, (float) Math.toRadians(2.26D), 0, 0);
			sitAnimationRotation(rightHindLeg, sitProgress, (float) Math.toRadians(160.0D), 0, 0);
			sitAnimationRotation(rightFrontThigh, sitProgress, -((float) Math.toRadians(10.0D)), -((float) Math.toRadians(18.0D)), (float) Math.toRadians(13.0D));
			sitAnimationRotation(leftHindLeg, sitProgress, (float) Math.toRadians(160.0D), 0, 0);
			sitAnimationRotation(leftEar, sitProgress, -((float) Math.toRadians(3.0D)), 0, 0);
			sitAnimationRotation(leftFrontLeg, sitProgress, -((float) Math.toRadians(100.0D)), 0, 0);
			sitAnimationRotation(upperLeftFang, sitProgress, (float) Math.toRadians(0.26D), 0, 0);
			sitAnimationRotation(neck, sitProgress, -((float) Math.toRadians(31.3D)), 0, 0);
			sitAnimationRotation(rightFrontLeg, sitProgress, -((float) Math.toRadians(100.0D)), 0, 0);
			sitAnimationRotation(rightHindThigh, sitProgress, -((float) Math.toRadians(55.0D)), -((float) Math.toRadians(20.0D)), 0);
			sitAnimationPos(lowerBody, sitProgress, 0, 5.1F, 0);
			sitAnimationPos(rightFrontThigh, sitProgress, 0, 5.1F, 0);
			sitAnimationPos(leftFrontThigh, sitProgress, 0, 5.1F, 0);
			sitAnimationPos(rightHindThigh, sitProgress, 0, 6, 0);
			sitAnimationPos(leftHindThigh, sitProgress, 0, 6, 0);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(head, sitProgress, -((float) Math.toRadians(30D)), 0, -((float) Math.toRadians(40D)));
			sitAnimationRotation(leftFrontThigh, sitProgress, -((float) Math.toRadians(10.0D)), (float) Math.toRadians(18.0D), -((float) Math.toRadians(13.0D)));
			sitAnimationRotation(rightFrontThigh, sitProgress, -((float) Math.toRadians(10.0D)), -((float) Math.toRadians(18.0D)), (float) Math.toRadians(13.0D));
			sitAnimationRotation(leftHindThigh, sitProgress, -((float) Math.toRadians(55.0D)), (float) Math.toRadians(20.0D), 0);
			sitAnimationRotation(leftFrontLeg, sitProgress, -((float) Math.toRadians(80.0D)), 0, 0);
			sitAnimationRotation(upperBody, sitProgress, (float) Math.toRadians(5.22D), 0, 0);
			sitAnimationRotation(rightHindThigh, sitProgress, -((float) Math.toRadians(55.0D)), -((float) Math.toRadians(20.0D)), 0);
			sitAnimationRotation(rightFrontLeg, sitProgress, -((float) Math.toRadians(80.0D)), 0, 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(5.22D)), 0, 0);
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(23.48D), (float) Math.toRadians(35D), 0);
			sitAnimationRotation(leftHindLeg, sitProgress, (float) Math.toRadians(140.0D), 0, 0);
			sitAnimationRotation(tail, sitProgress, -((float) Math.toRadians(36.52D)), (float) Math.toRadians(180.0D), 0);
			sitAnimationRotation(rightHindLeg, sitProgress, (float) Math.toRadians(140.0D), 0, 0);
			sitAnimationPos(lowerBody, sitProgress, 0, 5.1F, 0);
			sitAnimationPos(rightFrontThigh, sitProgress, 0, 5.1F, 0);
			sitAnimationPos(leftFrontThigh, sitProgress, 0, 5.1F, 0);
			sitAnimationPos(rightHindThigh, sitProgress, 0, 6, 0);
			sitAnimationPos(leftHindThigh, sitProgress, 0, 6, 0);
		}
	}

}
