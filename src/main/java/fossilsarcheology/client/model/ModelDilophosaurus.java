package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDilophosaurus extends ModelPrehistoric {
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer rightThigh;
	public AdvancedModelRenderer leftThigh;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer tail3;
	public AdvancedModelRenderer rightUpperArm;
	public AdvancedModelRenderer leftUpperArm;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer rightLowerArm;
	public AdvancedModelRenderer leftLowerArm;
	public AdvancedModelRenderer headPivot;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer leftCrest;
	public AdvancedModelRenderer rightCrest;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer rightLeg;
	public AdvancedModelRenderer rightFoot;
	public AdvancedModelRenderer leftLeg;
	public AdvancedModelRenderer leftFoot;
	private ModelAnimator animator;

	public ModelDilophosaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.tail1 = new AdvancedModelRenderer(this, 92, 0);
		this.tail1.setRotationPoint(0.0F, -0.2F, 8.3F);
		this.tail1.addBox(-2.5F, -2.3F, 0.0F, 5, 5, 8, 0.0F);
		this.setRotateAngle(tail1, -0.045553093477052F, -0.0F, 0.0F);
		this.upperBody = new AdvancedModelRenderer(this, 66, 0);
		this.upperBody.setRotationPoint(0.0F, -0.5F, -0.5F);
		this.upperBody.addBox(-3.0F, -2.0F, -6.0F, 6, 6, 7, 0.0F);
		this.setRotateAngle(upperBody, 0.011344640137963142F, -0.0F, 0.0F);
		this.leftUpperArm = new AdvancedModelRenderer(this, 14, 10);
		this.leftUpperArm.mirror = true;
		this.leftUpperArm.setRotationPoint(3.0F, 0.0F, -5.0F);
		this.leftUpperArm.addBox(0.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.rightLowerArm = new AdvancedModelRenderer(this, 0, 10);
		this.rightLowerArm.setRotationPoint(-1.0F, 3.0F, 0.5F);
		this.rightLowerArm.addBox(-0.9F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(rightLowerArm, -0.2617993877991494F, 0.0F, 0.0F);
		this.leftLeg = new AdvancedModelRenderer(this, 24, 12);
		this.leftLeg.setRotationPoint(1.5F, 3.5F, 2.5F);
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
		this.setRotateAngle(leftLeg, -0.2617993877991494F, -0.0F, 0.0F);
		this.leftThigh = new AdvancedModelRenderer(this, 24, 0);
		this.leftThigh.mirror = true;
		this.leftThigh.setRotationPoint(3.4F, 12.7F, 4.5F);
		this.leftThigh.addBox(0.0F, -1.0F, -2.5F, 3, 6, 5, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 42, 21);
		this.neck.setRotationPoint(0.0F, -0.2F, -5.3F);
		this.neck.addBox(-2.0F, -1.4F, -9.0F, 4, 4, 10, 0.0F);
		this.setRotateAngle(neck, -0.4553564018453205F, -0.0F, 0.0F);
		this.lowerJaw = new AdvancedModelRenderer(this, 2, 13);
		this.lowerJaw.setRotationPoint(0.0F, 0.9F, -3.8F);
		this.lowerJaw.addBox(-1.5F, 0.0F, -6.5F, 3, 1, 6, 0.0F);
		this.setRotateAngle(lowerJaw, 0.045553093477052F, -0.0F, 0.0F);
		this.leftLowerArm = new AdvancedModelRenderer(this, 0, 10);
		this.leftLowerArm.mirror = true;
		this.leftLowerArm.setRotationPoint(1.0F, 3.0F, 0.5F);
		this.leftLowerArm.addBox(-1.1F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(leftLowerArm, -0.2617993877991494F, -0.0F, 0.0F);
		this.tail3 = new AdvancedModelRenderer(this, 88, 15);
		this.tail3.setRotationPoint(0.0F, -0.3F, 10.4F);
		this.tail3.addBox(-1.0F, -0.7F, -0.2F, 2, 2, 17, 0.0F);
		this.setRotateAngle(tail3, -0.091106186954104F, -0.0F, 0.0F);
		this.leftFoot = new AdvancedModelRenderer(this, 35, 21);
		this.leftFoot.setRotationPoint(0.0F, 6.1F, -0.4F);
		this.leftFoot.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 4, 0.0F);
		this.setRotateAngle(leftFoot, 0.2617993877991494F, -0.0F, 0.0F);
		this.rightUpperArm = new AdvancedModelRenderer(this, 14, 10);
		this.rightUpperArm.setRotationPoint(-3.0F, 0.0F, -5.0F);
		this.rightUpperArm.addBox(-2.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 0, 0);
		this.upperJaw.setRotationPoint(0.0F, -0.8F, -5.2F);
		this.upperJaw.addBox(-2.0F, -2.0F, -5.2F, 4, 4, 5, 0.0F);
		this.setRotateAngle(upperJaw, 0.045553093477052F, -0.0F, 0.0F);
		this.rightCrest = new AdvancedModelRenderer(this, 18, 11);
		this.rightCrest.setRotationPoint(-1.5F, -2.3F, -5.6F);
		this.rightCrest.addBox(0.0F, -4.0F, -5.0F, 0, 4, 10, 0.0F);
		this.setRotateAngle(rightCrest, 0.008028514559173916F, -0.0F, 0.0F);
		this.headPivot = new AdvancedModelRenderer(this, 0, 0);
		this.headPivot.setRotationPoint(0.0F, 0.8F, -6.8F);
		this.setRotateAngle(headPivot, 0.6829473363053812F, -0.0F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 20);
		this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 5, 6, 0.0F);
		this.rightThigh = new AdvancedModelRenderer(this, 24, 0);
		this.rightThigh.setRotationPoint(-3.4F, 12.7F, 4.5F);
		this.rightThigh.addBox(-3.0F, -1.0F, -2.5F, 3, 6, 5, 0.0F);
		this.leftCrest = new AdvancedModelRenderer(this, 18, 11);
		this.leftCrest.setRotationPoint(1.5F, -2.3F, -5.6F);
		this.leftCrest.addBox(0.0F, -4.0F, -5.0F, 0, 4, 10, 0.0F);
		this.setRotateAngle(leftCrest, 0.008028514559173916F, -0.0F, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 93, 36);
		this.tail2.setRotationPoint(0.0F, -0.3F, 7.29F);
		this.tail2.addBox(-2.0F, -1.4F, 0.0F, 4, 3, 12, 0.0F);
		this.setRotateAngle(tail2, 0.045553093477052F, -0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 67, 13);
		this.lowerBody.setRotationPoint(0.0F, 11.7F, -1.8F);
		this.lowerBody.addBox(-3.5F, -3.0F, 0.0F, 7, 8, 10, 0.0F);
		this.rightFoot = new AdvancedModelRenderer(this, 35, 21);
		this.rightFoot.mirror = true;
		this.rightFoot.setRotationPoint(0.0F, 6.1F, -0.4F);
		this.rightFoot.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 4, 0.0F);
		this.setRotateAngle(rightFoot, 0.2617993877991494F, -0.0F, 0.0F);
		this.rightLeg = new AdvancedModelRenderer(this, 24, 12);
		this.rightLeg.mirror = true;
		this.rightLeg.setRotationPoint(-1.5F, 3.5F, 2.5F);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
		this.setRotateAngle(rightLeg, -0.2617993877991494F, -0.0F, 0.0F);
		this.lowerBody.addChild(this.tail1);
		this.lowerBody.addChild(this.upperBody);
		this.upperBody.addChild(this.leftUpperArm);
		this.rightUpperArm.addChild(this.rightLowerArm);
		this.leftThigh.addChild(this.leftLeg);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.lowerJaw);
		this.leftUpperArm.addChild(this.leftLowerArm);
		this.tail2.addChild(this.tail3);
		this.leftLeg.addChild(this.leftFoot);
		this.upperBody.addChild(this.rightUpperArm);
		this.head.addChild(this.upperJaw);
		this.head.addChild(this.rightCrest);
		this.neck.addChild(this.headPivot);
		this.headPivot.addChild(this.head);
		this.head.addChild(this.leftCrest);
		this.tail1.addChild(this.tail2);
		this.rightLeg.addChild(this.rightFoot);
		this.rightThigh.addChild(this.rightLeg);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.leftThigh.render(f5);
		this.rightThigh.render(f5);
		this.lowerBody.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, lowerJaw, 20, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -41, 0, 0);
		ModelUtils.rotate(animator, head, 57, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 15, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, neck, 6, 0, 0);
		ModelUtils.rotate(animator, head, -14, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 15, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail1, this.tail2, this.tail3};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		AdvancedModelRenderer[] leftArmParts = {this.leftUpperArm, this.leftLowerArm};
		AdvancedModelRenderer[] rightArmParts = {this.rightUpperArm, this.rightLowerArm};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);

		float speed = 0.1F;
		float speed2 = 0.3F;
		float degree = 0.5F;
		this.walk(upperBody, speed, degree * 0.1F, false, 1F, 0F, entity.ticksExisted, 1);
		this.bob(lowerBody, speed, degree * 0.7F, false, entity.ticksExisted, 1);
		this.walk(leftThigh, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(leftLeg, speed2, 0.2F, false, 0F, -0.6F, f, f1);
		this.walk(leftFoot, speed2, -0.6F, true, 2.5F, -0.4F, f, f1);
		this.walk(rightThigh, speed2, 0.6F, true, 0F, -0.4F, f, f1);
		this.walk(rightLeg, speed2, 0.2F, true, 0F, 0.6F, f, f1);
		this.walk(rightFoot, speed2, -0.6F, false, 2.5F, 0.4F, f, f1);
		this.walk(rightFoot, speed2, degree * -0.8F, false, 2.5F, 0.4F, f, f1);
		this.chainWave(tailParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainWave(leftArmParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainWave(rightArmParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed2, degree * 0.25F, -3, f, f1);
		this.chainSwing(neckParts, speed2, degree * 0.5F, 3, f, f1);
		this.chainWave(neckParts, speed, degree * 0.15F, 3, entity.ticksExisted, 1);
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(upperBody, sitProgress, (float) Math.toRadians(0.65D), 0, 0);
			sitAnimationRotation(upperJaw, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(leftFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(leftUpperArm, sitProgress, (float) Math.toRadians(2.0D), (float) Math.toRadians(5.0D), -((float) Math.toRadians(26.0D)));
			sitAnimationRotation(lowerJaw, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(tail2, sitProgress, -((float) Math.toRadians(7.83D)), 0, 0);
			sitAnimationRotation(tail3, sitProgress, (float) Math.toRadians(10.43D), 0, 0);
			sitAnimationRotation(leftCrest, sitProgress, (float) Math.toRadians(0.46D), 0, 0);
			sitAnimationRotation(rightLeg, sitProgress, -((float) Math.toRadians(65.0D)), 0, 0);
			sitAnimationRotation(rightFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(rightThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(rightCrest, sitProgress, (float) Math.toRadians(0.46D), 0, 0);
			sitAnimationRotation(tail1, sitProgress, -((float) Math.toRadians(10.43D)), 0, 0);
			sitAnimationRotation(leftLowerArm, sitProgress, -((float) Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(leftThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(rightLowerArm, sitProgress, -((float) Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(headPivot, sitProgress, (float) Math.toRadians(70.43D), 0, 0);
			sitAnimationRotation(leftLeg, sitProgress, -((float) Math.toRadians(65.0D)), 0, 0);
			sitAnimationRotation(rightUpperArm, sitProgress, (float) Math.toRadians(2.0D), -((float) Math.toRadians(5.0D)), (float) Math.toRadians(26.0D));
			sitAnimationRotation(neck, sitProgress, -((float) Math.toRadians(57.39D)), 0, 0);
			sitAnimationPos(lowerBody, sitProgress, 0, 5.8F, 0);
			sitAnimationPos(leftThigh, sitProgress, 0, 5.8F, 0);
			sitAnimationPos(rightThigh, sitProgress, 0, 5.8F, 0);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(rightCrest, sitProgress, (float) Math.toRadians(0.46D), 0, 0);
			sitAnimationRotation(leftThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(upperJaw, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(rightThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(tail1, sitProgress, -((float) Math.toRadians(18.26D)), (float) Math.toRadians(13.04D), (float) Math.toRadians(7.83D));
			sitAnimationRotation(leftCrest, sitProgress, (float) Math.toRadians(0.46D), 0, 0);
			sitAnimationRotation(leftLeg, sitProgress, -((float) Math.toRadians(65.0D)), 0, 0);
			sitAnimationRotation(tail2, sitProgress, (float) Math.toRadians(5.22D), (float) Math.toRadians(13.04D), (float) Math.toRadians(10.43D));
			sitAnimationRotation(lowerJaw, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(rightUpperArm, sitProgress, (float) Math.toRadians(2.0D), -((float) Math.toRadians(23.48D)), (float) Math.toRadians(39.13D));
			sitAnimationRotation(leftLowerArm, sitProgress, -((float) Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(rightLowerArm, sitProgress, -((float) Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(tail3, sitProgress, (float) Math.toRadians(15.65D), (float) Math.toRadians(28.7D), (float) Math.toRadians(5.22D));
			sitAnimationRotation(leftUpperArm, sitProgress, (float) Math.toRadians(2.0D), -((float) Math.toRadians(20.87D)), -((float) Math.toRadians(44.35D)));
			sitAnimationRotation(rightFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(head, sitProgress, -((float) Math.toRadians(70D)), -((float) Math.toRadians(23.48D)), (float) Math.toRadians(18.26D));
			sitAnimationRotation(rightLeg, sitProgress, -((float) Math.toRadians(65.0D)), 0, 0);
			sitAnimationRotation(leftFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(upperBody, sitProgress, (float) Math.toRadians(18.26D), 0, 0);
			sitAnimationRotation(neck, sitProgress, ((float) Math.toRadians(40D)), 0, 0);
			sitAnimationPos(lowerBody, sitProgress, 0, 5.8F, 0);
			sitAnimationPos(leftThigh, sitProgress, 0, 5.8F, 0);
			sitAnimationPos(rightThigh, sitProgress, 0, 5.8F, 0);

		}
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}

}
