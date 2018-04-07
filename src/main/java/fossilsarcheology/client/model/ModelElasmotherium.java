package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelElasmotherium extends ModelPrehistoric {
	public AdvancedModelRenderer middleBody;
	public AdvancedModelRenderer rightFrontThigh;
	public AdvancedModelRenderer rightHindThigh;
	public AdvancedModelRenderer leftFrontThigh;
	public AdvancedModelRenderer leftHindThigh;
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer EarR;
	public AdvancedModelRenderer EarL;
	public AdvancedModelRenderer lowerHorn;
	public AdvancedModelRenderer upperHorn;
	public AdvancedModelRenderer tail;
	public AdvancedModelRenderer rightFrontLeg;
	public AdvancedModelRenderer rightHindLeg;
	public AdvancedModelRenderer leftFrontLeg;
	public AdvancedModelRenderer leftHindLeg;
	private ModelAnimator animator;

	public ModelElasmotherium() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.head = new AdvancedModelRenderer(this, 25, 53);
		this.head.setRotationPoint(0.0F, 1.0F, -4.5F);
		this.head.addBox(-2.5F, -3.0F, -4.0F, 5, 5, 4, 0.0F);
		this.setRotateAngle(head, 6.981317007977319E-4F, -0.0F, 0.0F);
		this.tail = new AdvancedModelRenderer(this, 0, 0);
		this.tail.setRotationPoint(0.0F, -1.5F, 5.7F);
		this.tail.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 2, 0.0F);
		this.setRotateAngle(tail, 0.5009094953223726F, -0.0F, 0.0F);
		this.lowerJaw = new AdvancedModelRenderer(this, 51, 25);
		this.lowerJaw.setRotationPoint(0.0F, 0.8F, -3.5F);
		this.lowerJaw.addBox(-1.0F, -0.9F, -3.6F, 2, 2, 4, 0.0F);
		this.setRotateAngle(lowerJaw, -0.31869712141416456F, -0.0F, 0.0F);
		this.rightHindThigh = new AdvancedModelRenderer(this, 0, 45);
		this.rightHindThigh.mirror = true;
		this.rightHindThigh.setRotationPoint(-3.0F, 17.0F, 7.4F);
		this.rightHindThigh.addBox(-2.0F, -1.0F, -1.5F, 2, 5, 3, 0.0F);
		this.middleBody = new AdvancedModelRenderer(this, 0, 0);
		this.middleBody.setRotationPoint(0.0F, 10.8F, -5.0F);
		this.middleBody.addBox(-4.0F, -2.0F, 0.0F, 8, 10, 10, 0.0F);
		this.setRotateAngle(middleBody, -0.041713369122664476F, -0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 39, 36);
		this.lowerBody.setRotationPoint(0.0F, 1.7F, 8.97F);
		this.lowerBody.addBox(-3.0F, -4.0F, 0.0F, 6, 10, 6, 0.0F);
		this.rightFrontLeg = new AdvancedModelRenderer(this, 10, 38);
		this.rightFrontLeg.setRotationPoint(-0.8F, 3.5F, 0.5F);
		this.rightFrontLeg.addBox(-1.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.rightFrontThigh = new AdvancedModelRenderer(this, 0, 37);
		this.rightFrontThigh.mirror = true;
		this.rightFrontThigh.setRotationPoint(-2.8F, 16.5F, -6.5F);
		this.rightFrontThigh.addBox(-2.0F, -1.0F, -1.5F, 2, 5, 3, 0.0F);
		this.leftFrontThigh = new AdvancedModelRenderer(this, 0, 37);
		this.leftFrontThigh.setRotationPoint(2.8F, 16.5F, -6.5F);
		this.leftFrontThigh.addBox(0.0F, -1.0F, -1.5F, 2, 5, 3, 0.0F);
		this.upperHorn = new AdvancedModelRenderer(this, 51, 57);
		this.upperHorn.setRotationPoint(0.0F, -3.5F, -0.3F);
		this.upperHorn.addBox(-0.5F, -5.8F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(upperHorn, -0.27314402793711257F, -0.0F, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 42, 0);
		this.neck.setRotationPoint(0.0F, -0.7F, -4.5F);
		this.neck.addBox(-3.0F, -3.0F, -5.0F, 6, 7, 5, 0.0F);
		this.setRotateAngle(neck, 0.27314402793711257F, -0.0F, 0.0F);
		this.EarR = new AdvancedModelRenderer(this, 51, 31);
		this.EarR.setRotationPoint(-1.8F, -2.0F, -2.0F);
		this.EarR.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(EarR, -0.767944870877505F, -0.4363323129985824F, -0.3141592653589793F);
		this.rightHindLeg = new AdvancedModelRenderer(this, 10, 46);
		this.rightHindLeg.setRotationPoint(-0.7F, 3.0F, 0.5F);
		this.rightHindLeg.addBox(-1.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.leftHindThigh = new AdvancedModelRenderer(this, 0, 45);
		this.leftHindThigh.setRotationPoint(3.0F, 17.0F, 7.4F);
		this.leftHindThigh.addBox(0.0F, -1.0F, -1.5F, 2, 5, 3, 0.0F);
		this.EarL = new AdvancedModelRenderer(this, 51, 31);
		this.EarL.mirror = true;
		this.EarL.setRotationPoint(1.8F, -2.0F, -2.0F);
		this.EarL.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(EarL, -0.767944870877505F, 0.4363323129985824F, 0.3141592653589793F);
		this.upperBody = new AdvancedModelRenderer(this, 0, 20);
		this.upperBody.setRotationPoint(0.0F, 2.5F, 2.0F);
		this.upperBody.addBox(-3.5F, -5.0F, -6.0F, 7, 11, 6, 0.0F);
		this.setRotateAngle(upperBody, 0.15707963267948966F, -0.0F, 0.0F);
		this.leftHindLeg = new AdvancedModelRenderer(this, 10, 46);
		this.leftHindLeg.mirror = true;
		this.leftHindLeg.setRotationPoint(0.7F, 3.0F, 0.5F);
		this.leftHindLeg.addBox(-1.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 48, 18);
		this.upperJaw.setRotationPoint(0.0F, -1.8F, -3.7F);
		this.upperJaw.addBox(-1.5F, -1.0F, -4.0F, 3, 3, 4, 0.0F);
		this.setRotateAngle(upperJaw, 0.136659280431156F, -0.0F, 0.0F);
		this.leftFrontLeg = new AdvancedModelRenderer(this, 10, 38);
		this.leftFrontLeg.mirror = true;
		this.leftFrontLeg.setRotationPoint(0.8F, 3.5F, 0.5F);
		this.leftFrontLeg.addBox(-1.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.lowerHorn = new AdvancedModelRenderer(this, 56, 56);
		this.lowerHorn.setRotationPoint(0.0F, -0.5F, -0.3F);
		this.lowerHorn.addBox(-1.0F, -4.7F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(lowerHorn, 0.4553564018453205F, -0.0F, 0.0F);
		this.neck.addChild(this.head);
		this.lowerBody.addChild(this.tail);
		this.head.addChild(this.lowerJaw);
		this.middleBody.addChild(this.lowerBody);
		this.rightFrontThigh.addChild(this.rightFrontLeg);
		this.lowerHorn.addChild(this.upperHorn);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.EarR);
		this.rightHindThigh.addChild(this.rightHindLeg);
		this.head.addChild(this.EarL);
		this.middleBody.addChild(this.upperBody);
		this.leftHindThigh.addChild(this.leftHindLeg);
		this.head.addChild(this.upperJaw);
		this.leftFrontThigh.addChild(this.leftFrontLeg);
		this.upperJaw.addChild(this.lowerHorn);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.rightHindThigh.render(f5);
		this.middleBody.render(f5);
		this.rightFrontThigh.render(f5);
		this.leftFrontThigh.render(f5);
		this.leftHindThigh.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 15, 0, 0);
		ModelUtils.rotate(animator, head, -20, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 44, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, head, 15, 0, 0);
		ModelUtils.rotate(animator, neck, 23, 0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, head, -25, 0, 0);
		ModelUtils.rotate(animator, neck, -33, 0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.resetKeyframe(5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(head, f3, f4, 1);
		float speed = 0.1F;
		float speed2 = 1.1F;
		this.chainWave(tailParts, speed, 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, 0.15F, -2, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed, 0.15F, 3, entity.ticksExisted, 1);
		this.bob(middleBody, speed, 0.3F, false, entity.ticksExisted, 1);
		this.walk(lowerBody, speed, 0.05F, true, 0F, 0F, entity.ticksExisted, 1);
		this.walk(upperBody, speed, 0.05F, true, 0F, 0F, entity.ticksExisted, 1);
		this.walk(leftFrontThigh, speed2, 0.8F, true, 0F, -0.4F, f, f1);
		this.walk(rightFrontThigh, speed2, 0.8F, false, 0F, -0.4F, f, f1);
		this.walk(leftHindThigh, speed2, 0.8F, false, 0F, 0.4F, f, f1);
		this.walk(rightHindThigh, speed2, 0.8F, true, 0F, 0.4F, f, f1);
		this.walk(leftFrontLeg, speed2, 0.6F, true, 0F, 0.4F, f, f1);
		this.walk(rightFrontLeg, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(leftHindLeg, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(rightHindLeg, speed2, 0.6F, true, 0F, 0.4F, f, f1);
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(upperBody, sitProgress, (float) Math.toRadians(26.09D), 0, 0);
			sitAnimationRotation(leftHindLeg, sitProgress, (float) Math.toRadians(136.0D), 0, 0);
			sitAnimationRotation(lowerJaw, sitProgress, -((float) Math.toRadians(18.26D)), 0, 0);
			sitAnimationRotation(middleBody, sitProgress, -((float) Math.toRadians(31.3D)), 0, 0);
			sitAnimationRotation(upperJaw, sitProgress, (float) Math.toRadians(7.83D), 0, 0);
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(rightHindLeg, sitProgress, (float) Math.toRadians(136.0D), 0, 0);
			sitAnimationRotation(leftHindThigh, sitProgress, -((float) Math.toRadians(18.0D)), 0, 0);
			sitAnimationRotation(rightHindThigh, sitProgress, -((float) Math.toRadians(18.0D)), 0, 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(tail, sitProgress, (float) Math.toRadians(28.7D), 0, 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(18.26D), 0, 0);
			sitAnimationPos(middleBody, sitProgress, 0, 0.4F, 0);
			sitAnimationPos(rightHindThigh, sitProgress, 0, 3.3F, -4);
			sitAnimationPos(leftHindThigh, sitProgress, 0, 3.3F, -4);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(rightHindThigh, sitProgress, -((float) Math.toRadians(18.0D)), (float) Math.toRadians(15.65D), 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(5.65D), -((float) Math.toRadians(2.61D)), -((float) Math.toRadians(26.09D)));
			sitAnimationRotation(leftHindThigh, sitProgress, -((float) Math.toRadians(18.0D)), 0, 0);
			sitAnimationRotation(leftHindLeg, sitProgress, (float) Math.toRadians(106.0D), 0, 0);
			sitAnimationRotation(rightFrontThigh, sitProgress, -((float) Math.toRadians(55.101214229390365D)), 0, 0);
			sitAnimationRotation(rightHindLeg, sitProgress, (float) Math.toRadians(106.0D), 0, 0);
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(15.48D), 0, -((float) Math.toRadians(12.144853216893267D)));
			sitAnimationRotation(middleBody, sitProgress, 0, 0, -((float) Math.toRadians(5.22D)));
			sitAnimationRotation(leftFrontThigh, sitProgress, -((float) Math.toRadians(73.04D)), -((float) Math.toRadians(7.83D)), -((float) Math.toRadians(10.43D)));
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(rightFrontLeg, sitProgress, -((float) Math.toRadians(31.3D)), 0, 0);
			sitAnimationRotation(leftFrontLeg, sitProgress, -((float) Math.toRadians(13.04D)), 0, 0);
			sitAnimationPos(middleBody, sitProgress, 0, 4F, 0);
			sitAnimationPos(rightHindThigh, sitProgress, 0, 3.3F, 0);
			sitAnimationPos(leftHindThigh, sitProgress, 0, 3.3F, 0);
			sitAnimationPos(rightFrontThigh, sitProgress, 0, 3.3F, 0);
			sitAnimationPos(leftFrontThigh, sitProgress, 0, 3.3F, 0);
		}
	}
}
