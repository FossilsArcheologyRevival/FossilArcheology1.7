package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelTerrorBird extends ModelPrehistoric {
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer rightThigh;
	public AdvancedModelRenderer leftThigh;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer leftWing;
	public AdvancedModelRenderer rightWing;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer neck_1;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer upperBeak;
	public AdvancedModelRenderer lowerBeak;
	public AdvancedModelRenderer crest1;
	public AdvancedModelRenderer crest11;
	public AdvancedModelRenderer mouth;
	public AdvancedModelRenderer crest2;
	public AdvancedModelRenderer rightLeg;
	public AdvancedModelRenderer rightFoot;
	public AdvancedModelRenderer leftLeg;
	public AdvancedModelRenderer leftFoot;
	private ModelAnimator animator;

	public ModelTerrorBird() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.tail1 = new AdvancedModelRenderer(this, 64, 10);
		this.tail1.setRotationPoint(-1.0F, -1.8F, 6.0F);
		this.tail1.addBox(-2.0F, 0.0F, 1.4F, 4, 1, 14, 0.0F);
		this.setRotateAngle(tail1, -0.045553093477052F, -0.0F, 0.0F);
		this.leftThigh = new AdvancedModelRenderer(this, 44, 51);
		this.leftThigh.mirror = true;
		this.leftThigh.setRotationPoint(2.21F, 6.7F, 2.21F);
		this.leftThigh.addBox(0.0F, -1.0F, -2.5F, 4, 8, 5, 0.0F);
		this.upperBeak = new AdvancedModelRenderer(this, 28, 33);
		this.upperBeak.setRotationPoint(0.0F, 1.0F, -5.8F);
		this.upperBeak.addBox(-3.0F, -3.0F, -9.0F, 6, 5, 9, 0.0F);
		this.setRotateAngle(upperBeak, 0.045553093477052F, -0.0F, 0.0F);
		this.rightFoot = new AdvancedModelRenderer(this, 72, 50);
		this.rightFoot.mirror = true;
		this.rightFoot.setRotationPoint(0.0F, 10.33F, 0.5F);
		this.rightFoot.addBox(-2.0F, 0.0F, -4.0F, 4, 2, 5, 0.0F);
		this.setRotateAngle(rightFoot, 0.13962634015954636F, -0.0F, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 88, 0);
		this.neck.setRotationPoint(-1.0F, 3.2F, -0.3F);
		this.neck.addBox(-2.01F, -8.5F, -2.5F, 4, 11, 5, 0.0F);
		this.setRotateAngle(neck, 0.7285004297824331F, -0.0F, 0.0F);
		this.lowerBeak = new AdvancedModelRenderer(this, 51, 27);
		this.lowerBeak.setRotationPoint(0.0F, 2.1F, -5.5F);
		this.lowerBeak.addBox(-2.5F, 0.0F, -9.0F, 5, 2, 9, 0.0F);
		this.crest2 = new AdvancedModelRenderer(this, 62, 10);
		this.crest2.setRotationPoint(0.5F, -0.4F, 2.0F);
		this.crest2.addBox(-1.0F, -3.6F, 1.5F, 2, 6, 4, 0.0F);
		this.setRotateAngle(crest2, 0.22759093446006054F, -0.0F, 0.0F);
		this.mouth = new AdvancedModelRenderer(this, 0, 54);
		this.mouth.setRotationPoint(0.0F, 0.0F, -0.8F);
		this.mouth.addBox(-2.5F, 0.0F, -8.0F, 5, 1, 8, 0.0F);
		this.leftWing = new AdvancedModelRenderer(this, 108, 0);
		this.leftWing.setRotationPoint(4.0F, 2.0F, 5.5F);
		this.leftWing.addBox(0.0F, -1.0F, -4.5F, 1, 5, 9, 0.0F);
		this.setRotateAngle(leftWing, 0.0F, -0.017453292519943295F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 18, 47);
		this.head.setRotationPoint(0.0F, -9.9F, 2.4F);
		this.head.addBox(-3.5F, -2.0F, -6.0F, 7, 7, 6, 0.0F);
		this.setRotateAngle(head, -0.091106186954104F, 0.0F, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 84, 27);
		this.tail2.setRotationPoint(-1.0F, -1.2F, 8.8F);
		this.tail2.addBox(-1.5F, 0.0F, 0.2F, 3, 3, 5, 0.0F);
		this.setRotateAngle(tail2, -0.045553093477052F, -0.0F, 0.0F);
		this.neck_1 = new AdvancedModelRenderer(this, 88, 0);
		this.neck_1.setRotationPoint(0F, -8.3F, -0.87F);
		this.neck_1.addBox(-2.0F, -10.7F, -1.6F, 4, 11, 5, 0.0F);
		this.setRotateAngle(neck_1, -0.40980330836826856F, 0.0F, 0.0F);
		this.rightThigh = new AdvancedModelRenderer(this, 44, 51);
		this.rightThigh.setRotationPoint(-2.21F, 6.7F, 2.21F);
		this.rightThigh.addBox(-4.0F, -1.0F, -2.5F, 4, 8, 5, 0.0F);
		this.crest1 = new AdvancedModelRenderer(this, 66, 0);
		this.crest1.mirror = true;
		this.crest1.setRotationPoint(-0.3F, 1.2F, -0.5F);
		this.crest1.addBox(-2.5F, -3.0F, 0.0F, 3, 6, 4, 0.0F);
		this.setRotateAngle(crest1, -0.17453292519943295F, -0.0F, 0.0F);
		this.rightWing = new AdvancedModelRenderer(this, 108, 0);
		this.rightWing.setRotationPoint(-6.0F, 2.0F, 5.5F);
		this.rightWing.addBox(-1.0F, -1.0F, -4.5F, 1, 5, 9, 0.0F);
		this.setRotateAngle(rightWing, 0.0F, -0.017453292519943295F, 0.0F);
		this.crest11 = new AdvancedModelRenderer(this, 66, 0);
		this.crest11.setRotationPoint(0.3F, 1.2F, -0.5F);
		this.crest11.addBox(-0.5F, -3.0F, 0.0F, 3, 6, 4, 0.0F);
		this.setRotateAngle(crest11, -0.17453292519943295F, -0.0F, 0.0F);
		this.rightLeg = new AdvancedModelRenderer(this, 98, 36);
		this.rightLeg.setRotationPoint(-2.0F, 5.0F, 1.5F);
		this.rightLeg.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
		this.setRotateAngle(rightLeg, -0.13962634015954636F, -0.0F, 0.0F);
		this.leftFoot = new AdvancedModelRenderer(this, 72, 50);
		this.leftFoot.setRotationPoint(0.0F, 10.33F, 0.5F);
		this.leftFoot.addBox(-2.0F, 0.0F, -4.0F, 4, 2, 5, 0.0F);
		this.setRotateAngle(leftFoot, 0.13962634015954636F, -0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 2, 24);
		this.lowerBody.setRotationPoint(0.0F, 3.5F, 11.0F);
		this.lowerBody.addBox(-5.0F, -3.0F, 0.0F, 8, 9, 9, 0.0F);
		this.setRotateAngle(lowerBody, -0.17453292519943295F, 0.0F, 0.0F);
		this.upperBody = new AdvancedModelRenderer(this, 1, 0);
		this.upperBody.setRotationPoint(1.0F, -1.3F, -8.8F);
		this.upperBody.addBox(-6.0F, 0.0F, 0.0F, 10, 11, 13, 0.0F);
		this.leftLeg = new AdvancedModelRenderer(this, 98, 36);
		this.leftLeg.setRotationPoint(2.0F, 5.0F, 1.5F);
		this.leftLeg.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
		this.setRotateAngle(leftLeg, -0.13962634015954636F, -0.0F, 0.0F);
		this.lowerBody.addChild(this.tail1);
		this.head.addChild(this.upperBeak);
		this.rightLeg.addChild(this.rightFoot);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.lowerBeak);
		this.crest1.addChild(this.crest2);
		this.upperBeak.addChild(this.mouth);
		this.upperBody.addChild(this.leftWing);
		this.neck_1.addChild(this.head);
		this.lowerBody.addChild(this.tail2);
		this.neck.addChild(this.neck_1);
		this.head.addChild(this.crest1);
		this.upperBody.addChild(this.rightWing);
		this.head.addChild(this.crest11);
		this.rightThigh.addChild(this.rightLeg);
		this.leftLeg.addChild(this.leftFoot);
		this.upperBody.addChild(this.lowerBody);
		this.leftThigh.addChild(this.leftLeg);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.leftThigh.render(f5);
		this.rightThigh.render(f5);
		this.upperBody.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, head, -15, 0, 0);
		ModelUtils.rotate(animator, lowerBeak, 45, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -19, 0, 0);
		ModelUtils.rotate(animator, head, 13, 0, 0);
		ModelUtils.rotate(animator, lowerBeak, 25, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, neck, 110, 0, 0);
		ModelUtils.rotate(animator, neck_1, 10, 0, 0);
		ModelUtils.rotate(animator, head, -25, 0, 0);
		ModelUtils.rotate(animator, lowerBeak, -25, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		neck_1.setScale(1.01F, 1.01F, 1.01F);
		AdvancedModelRenderer[] neckParts = {this.neck, this.neck_1, this.head};
		AdvancedModelRenderer[] tailParts = {this.lowerBody, this.tail1, this.tail2};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);
		float speed = 0.1F;
		float speed2 = 0.5F;
		float degree = 0.5F;
		this.bob(upperBody, speed, degree * 0.4F, false, entity.ticksExisted, 1);

		this.walk(leftThigh, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(leftLeg, speed2, 0.2F, false, 0F, -0.6F, f, f1);
		this.walk(leftFoot, speed2, -0.6F, true, 2.5F, -0.4F, f, f1);
		this.walk(rightThigh, speed2, 0.6F, true, 0F, -0.4F, f, f1);
		this.walk(rightLeg, speed2, 0.2F, true, 0F, 0.6F, f, f1);
		this.walk(rightFoot, speed2, -0.6F, false, 2.5F, 0.4F, f, f1);
		this.chainWave(neckParts, speed2, degree * 0.3F, 4, f, f1);
		if (((EntityPrehistoric) (entity)).isSleeping()) {
			this.walk(upperBody, speed, degree * 0.05F, false, 0, 0, entity.ticksExisted, 1);
			this.chainWave(neckParts, speed, degree * 0.05F, 3, entity.ticksExisted, 1);
		} else {
			this.walk(upperBody, speed, degree * 0.1F, false, 0, 0, entity.ticksExisted, 1);
			this.chainWave(neckParts, speed, degree * 0.15F, 3, entity.ticksExisted, 1);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(rightWing, sitProgress, 0, -((float) Math.toRadians(1.0D)), 0);
			sitAnimationRotation(tail2, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(crest11, sitProgress, -((float) Math.toRadians(10.0D)), 0, 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(28.7D)), 0, 0);
			sitAnimationRotation(rightFoot, sitProgress, (float) Math.toRadians(88.0D), 0, 0);
			sitAnimationRotation(neck_1, sitProgress, -((float) Math.toRadians(31.3D)), 0, 0);
			sitAnimationRotation(head, sitProgress, -((float) Math.toRadians(10.43D)), 0, 0);
			sitAnimationRotation(rightLeg, sitProgress, -((float) Math.toRadians(88.0D)), 0, 0);
			sitAnimationRotation(crest1, sitProgress, -((float) Math.toRadians(10.0D)), 0, 0);
			sitAnimationRotation(leftFoot, sitProgress, (float) Math.toRadians(88.0D), 0, 0);
			sitAnimationRotation(leftWing, sitProgress, 0, -((float) Math.toRadians(1.0D)), 0);
			sitAnimationRotation(leftLeg, sitProgress, -((float) Math.toRadians(88.0D)), 0, 0);
			sitAnimationRotation(tail1, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(upperBeak, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(crest2, sitProgress, (float) Math.toRadians(13.04D), 0, 0);
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(54.78D), 0, 0);
			sitAnimationPos(rightThigh, sitProgress, 0F, 14.30F - ModelUtils.getDefaultPositionY(leftThigh), 0F);
			sitAnimationPos(leftThigh, sitProgress, 0F, 14.30F - ModelUtils.getDefaultPositionY(leftThigh), 0F);
			sitAnimationPos(upperBody, sitProgress, 0F, 17.30F - ModelUtils.getDefaultPositionY(leftThigh), 0F);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(head, sitProgress, -((float) Math.toRadians(104.35D)), 0, 0);
			sitAnimationRotation(tail2, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(rightWing, sitProgress, 0, -((float) Math.toRadians(46.96D)), 0);
			sitAnimationRotation(tail1, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(crest1, sitProgress, (float) Math.toRadians(23.0D), 0, 0);
			sitAnimationRotation(leftLeg, sitProgress, -((float) Math.toRadians(88.0D)), 0, 0);
			sitAnimationRotation(rightFoot, sitProgress, (float) Math.toRadians(88.0D), 0, 0);
			sitAnimationRotation(neck_1, sitProgress, -(float) Math.toRadians(10.43D), 0, 0);
			sitAnimationRotation(leftWing, sitProgress, 0, (float) Math.toRadians(44.35D), 0);
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(104.35D), 0, 0);
			sitAnimationRotation(leftFoot, sitProgress, (float) Math.toRadians(88.0D), 0, 0);
			sitAnimationRotation(crest2, sitProgress, (float) Math.toRadians(13.04D), 0, 0);
			sitAnimationRotation(upperBeak, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(crest11, sitProgress, (float) Math.toRadians(23.0D), 0, 0);
			sitAnimationRotation(rightLeg, sitProgress, -((float) Math.toRadians(88.0D)), 0, 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(28.7D)), 0, 0);
			sitAnimationPos(rightThigh, sitProgress, 0F, 14.30F - ModelUtils.getDefaultPositionY(leftThigh), 0F);
			sitAnimationPos(leftThigh, sitProgress, 0F, 14.30F - ModelUtils.getDefaultPositionY(leftThigh), 0F);
			sitAnimationPos(upperBody, sitProgress, 0F, 17.30F - ModelUtils.getDefaultPositionY(leftThigh), 0F);
		}
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer(tailParts);

	}
}
