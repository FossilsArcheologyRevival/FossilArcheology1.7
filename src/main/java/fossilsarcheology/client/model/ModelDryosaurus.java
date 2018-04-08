package fossilsarcheology.client.model;


import fossilsarcheology.server.entity.prehistoric.EntityDryosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDryosaurus extends ModelPrehistoric {
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer leftThigh;
	public AdvancedModelRenderer rightThigh;
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer leftUpperArm;
	public AdvancedModelRenderer rightUpperArm;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer headPivot;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer leftLowerArm;
	public AdvancedModelRenderer rightLowerArm;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer tail3;
	public AdvancedModelRenderer leftLeg;
	public AdvancedModelRenderer leftFoot;
	public AdvancedModelRenderer rightLeg;
	public AdvancedModelRenderer rightFoot;
	private ModelAnimator animator;

	public ModelDryosaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.leftLeg = new AdvancedModelRenderer(this, 2, 28);
		this.leftLeg.mirror = true;
		this.leftLeg.setRotationPoint(1.2F, 2.8F, 2.2F);
		this.leftLeg.addBox(-1.0F, 0.4F, -7.4F, 2, 2, 8, 0.0F);
		this.setRotateAngle(leftLeg, 0.9948376736367678F, -0.0F, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 28, 0);
		this.upperJaw.setRotationPoint(0.0F, -0.9F, -4.93F);
		this.upperJaw.addBox(-2.0F, -2.4F, -3.0F, 3, 3, 4, 0.0F);
		this.setRotateAngle(upperJaw, 0.045553093477052F, -0.0F, 0.0F);
		this.leftUpperArm = new AdvancedModelRenderer(this, 35, 13);
		this.leftUpperArm.mirror = true;
		this.leftUpperArm.setRotationPoint(3.0F, 2.7F, -4.0F);
		this.leftUpperArm.addBox(0.0F, -1.0F, -1.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(leftUpperArm, -0.06981317007977318F, -0.0F, 0.0F);
		this.tail3 = new AdvancedModelRenderer(this, 38, 24);
		this.tail3.setRotationPoint(0.0F, 0.6F, 8.4F);
		this.tail3.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 16, 0.0F);
		this.setRotateAngle(tail3, 0.045553093477052F, -0.0F, 0.0F);
		this.leftFoot = new AdvancedModelRenderer(this, 0, 40);
		this.leftFoot.mirror = true;
		this.leftFoot.setRotationPoint(0.2F, 0.9F, -6.3F);
		this.leftFoot.addBox(-1.5F, 0.0F, -3.1F, 3, 2, 4, 0.0F);
		this.setRotateAngle(leftFoot, -0.9948376736367678F, -0.0F, 0.0F);
		this.rightLeg = new AdvancedModelRenderer(this, 2, 28);
		this.rightLeg.setRotationPoint(-1.2F, 2.8F, 2.2F);
		this.rightLeg.addBox(-1.0F, 0.4F, -7.4F, 2, 2, 8, 0.0F);
		this.setRotateAngle(rightLeg, 0.9948376736367678F, -0.0F, 0.0F);
		this.rightUpperArm = new AdvancedModelRenderer(this, 35, 13);
		this.rightUpperArm.setRotationPoint(-3.0F, 2.7F, -4.0F);
		this.rightUpperArm.addBox(-2.0F, -1.0F, -1.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightUpperArm, -0.06981317007977318F, -0.0F, 0.0F);
		this.upperBody = new AdvancedModelRenderer(this, 67, 0);
		this.upperBody.setRotationPoint(0.0F, 1.3F, -2.1F);
		this.upperBody.addBox(-3.5F, -2.0F, -5.8F, 7, 7, 9, 0.0F);
		this.setRotateAngle(upperBody, 0.045553093477052F, -0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 65, 20);
		this.lowerBody.setRotationPoint(0.0F, 9.4F, -1.8F);
		this.lowerBody.addBox(-4.0F, -1.0F, 0.0F, 8, 8, 8, 0.0F);
		this.rightThigh = new AdvancedModelRenderer(this, 19, 35);
		this.rightThigh.setRotationPoint(-3.0F, 14.0F, 3.0F);
		this.rightThigh.addBox(-3.0F, -2.5F, -2.0F, 3, 7, 5, 0.0F);
		this.lowerJaw = new AdvancedModelRenderer(this, 49, 0);
		this.lowerJaw.setRotationPoint(0.0F, 0.1F, -4.83F);
		this.lowerJaw.addBox(-2.0F, -0.5F, -3.0F, 3, 1, 3, 0.0F);
		this.setRotateAngle(lowerJaw, 0.045553093477052F, 0.0F, 0.0F);
		this.leftLowerArm = new AdvancedModelRenderer(this, 32, 21);
		this.leftLowerArm.mirror = true;
		this.leftLowerArm.setRotationPoint(0.91F, 1.8F, 0.5F);
		this.leftLowerArm.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotateAngle(leftLowerArm, 0.8726646259971648F, -0.0F, 0.0F);
		this.rightFoot = new AdvancedModelRenderer(this, 0, 40);
		this.rightFoot.setRotationPoint(-0.2F, 0.9F, -6.3F);
		this.rightFoot.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 4, 0.0F);
		this.setRotateAngle(rightFoot, -0.9948376736367678F, -0.0F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 0);
		this.head.addBox(-3.0F, -4.0F, -5.0F, 5, 5, 6, 0.0F);
		this.headPivot = new AdvancedModelRenderer(this, 0, 0);
		this.headPivot.setRotationPoint(0.5F, 0.8F, -6.23F);
		this.setRotateAngle(headPivot, 1.0471975511965976F, -0.0017453292519943296F, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 105, 0);
		this.tail1.setRotationPoint(0.0F, -0.7F, 7.8F);
		this.tail1.addBox(-2.5F, 0.0F, 0.0F, 5, 6, 5, 0.0F);
		this.setRotateAngle(tail1, -0.136659280431156F, -0.0F, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 0, 13);
		this.neck.setRotationPoint(0.0F, 1.2F, -4.9F);
		this.neck.addBox(-2.0F, -2.0F, -9.0F, 4, 4, 10, 0.0F);
		this.setRotateAngle(neck, -0.8651597102135892F, -0.0F, 0.0F);
		this.leftThigh = new AdvancedModelRenderer(this, 19, 35);
		this.leftThigh.mirror = true;
		this.leftThigh.setRotationPoint(3.0F, 14.0F, 3.0F);
		this.leftThigh.addBox(0.0F, -2.5F, -2.0F, 3, 7, 5, 0.0F);
		this.rightLowerArm = new AdvancedModelRenderer(this, 32, 21);
		this.rightLowerArm.setRotationPoint(-0.91F, 1.8F, 0.5F);
		this.rightLowerArm.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotateAngle(rightLowerArm, 0.8726646259971648F, 0.0F, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 100, 19);
		this.tail2.setRotationPoint(0.0F, 0.7F, 4.1F);
		this.tail2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 10, 0.0F);
		this.setRotateAngle(tail2, 0.045553093477052F, 0.0F, 0.0F);
		this.leftThigh.addChild(this.leftLeg);
		this.head.addChild(this.upperJaw);
		this.upperBody.addChild(this.leftUpperArm);
		this.tail2.addChild(this.tail3);
		this.leftLeg.addChild(this.leftFoot);
		this.rightThigh.addChild(this.rightLeg);
		this.upperBody.addChild(this.rightUpperArm);
		this.lowerBody.addChild(this.upperBody);
		this.head.addChild(this.lowerJaw);
		this.leftUpperArm.addChild(this.leftLowerArm);
		this.rightLeg.addChild(this.rightFoot);
		this.neck.addChild(this.headPivot);
		this.headPivot.addChild(this.head);
		this.lowerBody.addChild(this.tail1);
		this.upperBody.addChild(this.neck);
		this.rightUpperArm.addChild(this.rightLowerArm);
		this.tail1.addChild(this.tail2);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.lowerBody.render(f5);
		this.leftThigh.render(f5);
		this.rightThigh.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, lowerJaw, 29, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -41, 0, 0);
		ModelUtils.rotate(animator, head, 57, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 15, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, neck, 16, 0, 0);
		ModelUtils.rotate(animator, head, 14, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, -15, 0, 0);
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
		EntityDryosaurus dino = (EntityDryosaurus) entity;
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		float speed = 0.1F;
		float speed2 = 0.7F;
		float degree = 0.5F;
		{
			float sitProgress = dino.sitProgress;
			sitAnimationRotationPrev(leftLeg, sitProgress, (float) Math.toRadians(30.0D), 0, 0);
			sitAnimationRotationPrev(leftThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotationPrev(leftUpperArm, sitProgress, -((float) Math.toRadians(4.0D)), 0, 0);
			sitAnimationRotationPrev(rightUpperArm, sitProgress, -((float) Math.toRadians(4.0D)), 0, 0);
			sitAnimationRotationPrev(rightLowerArm, sitProgress, (float) Math.toRadians(50.0D), 0, 0);
			sitAnimationRotationPrev(leftFoot, sitProgress, -((float) Math.toRadians(5.0D)), 0, 0);
			sitAnimationRotationPrev(tail3, sitProgress, (float) Math.toRadians(10.43D), 0, 0);
			sitAnimationRotationPrev(leftLowerArm, sitProgress, (float) Math.toRadians(50.0D), 0, 0);
			sitAnimationRotationPrev(rightFoot, sitProgress, -((float) Math.toRadians(5.0D)), 0, 0);
			sitAnimationRotationPrev(tail1, sitProgress, -((float) Math.toRadians(10.43D)), 0, 0);
			sitAnimationRotationPrev(tail2, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotationPrev(lowerBody, sitProgress, -((float) Math.toRadians(7.83D)), 0, 0);
			sitAnimationRotationPrev(rightThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotationPrev(upperBody, sitProgress, (float) Math.toRadians(10.43D), 0, 0);
			sitAnimationRotationPrev(rightLeg, sitProgress, (float) Math.toRadians(30.0D), 0, 0);
			sitAnimationPos(lowerBody, sitProgress, 0F, 5.6F, 0F);
			sitAnimationPos(leftThigh, sitProgress, 0F, 3.6F, 0F);
			sitAnimationPos(rightThigh, sitProgress, 0F, 3.6F, 0F);

		}
		{
			float sitProgress = dino.sleepProgress;
			sitAnimationRotationPrev(upperJaw, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotationPrev(upperBody, sitProgress, (float) Math.toRadians(13.04D), (float) Math.toRadians(15.65D), -((float) Math.toRadians(2.61D)));
			sitAnimationRotationPrev(leftLeg, sitProgress, (float) Math.toRadians(30.0D), 0, 0);
			sitAnimationRotationPrev(leftFoot, sitProgress, -((float) Math.toRadians(5.0D)), 0, 0);
			sitAnimationRotationPrev(lowerBody, sitProgress, -((float) Math.toRadians(2.61D)), 0, (float) Math.toRadians(10.43D));
			sitAnimationRotationPrev(headPivot, sitProgress, -((float) Math.toRadians(23.48D)), -(float) Math.toRadians(7.83D), -((float) Math.toRadians(7.83D)));
			sitAnimationRotationPrev(lowerJaw, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotationPrev(leftLowerArm, sitProgress, (float) Math.toRadians(50.0D), 0, 0);
			sitAnimationRotationPrev(rightLeg, sitProgress, (float) Math.toRadians(30.0D), 0, 0);
			sitAnimationRotationPrev(leftThigh, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotationPrev(tail2, sitProgress, (float) Math.toRadians(2.61D), -((float) Math.toRadians(31.3D)), -((float) Math.toRadians(7.83D)));
			sitAnimationRotationPrev(tail1, sitProgress, -((float) Math.toRadians(28.7D)), -((float) Math.toRadians(10.43D)), -((float) Math.toRadians(15.65D)));
			sitAnimationRotationPrev(neck, sitProgress, (float) Math.toRadians(23.48D), 0, -((float) Math.toRadians(5.22D)));
			sitAnimationRotationPrev(rightLowerArm, sitProgress, (float) Math.toRadians(70.43D), 0, 0);
			sitAnimationRotationPrev(rightThigh, sitProgress, -((float) Math.toRadians(25.0D)), (float) Math.toRadians(15.65D), (float) Math.toRadians(2.61D));
			sitAnimationRotationPrev(tail3, sitProgress, (float) Math.toRadians(26.09D), -((float) Math.toRadians(20.87D)), -((float) Math.toRadians(26.09D)));
			sitAnimationRotationPrev(leftUpperArm, sitProgress, -((float) Math.toRadians(26.09D)), 0, -((float) Math.toRadians(23.48D)));
			sitAnimationRotationPrev(rightUpperArm, sitProgress, -((float) Math.toRadians(44.35D)), -((float) Math.toRadians(10.43D)), 0);
			sitAnimationRotationPrev(rightFoot, sitProgress, -((float) Math.toRadians(5.0D)), 0, 0);
			sitAnimationPos(headPivot, sitProgress, 0F, 0.2F, -1.8F);
			sitAnimationPos(lowerBody, sitProgress, 0F, 5.6F, 0F);
			sitAnimationPos(leftThigh, sitProgress, 0F, 3.6F, 0F);
			sitAnimationPos(rightThigh, sitProgress, 0F, 3.6F, 0F);
		}
		this.bob(lowerBody, speed, degree * 0.7F, false, entity.ticksExisted, 1);
		this.walk(upperBody, speed, degree * 0.1F, false, 0, 0, entity.ticksExisted, 1);
		this.chainWave(tailParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainWave(leftArmParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainWave(rightArmParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed2, degree * 0.25F, -3, f, f1);
		this.chainWave(neckParts, speed, degree * 0.15F, 3, entity.ticksExisted, 1);
		this.walk(leftThigh, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(leftLeg, speed2, 0.2F, false, 0F, -0.6F, f, f1);
		this.walk(leftFoot, speed2, -0.6F, true, 2.5F, -0.4F, f, f1);
		this.walk(rightThigh, speed2, 0.6F, true, 0F, -0.4F, f, f1);
		this.walk(rightLeg, speed2, 0.2F, true, 0F, 0.6F, f, f1);
		this.walk(rightFoot, speed2, -0.6F, false, 2.5F, 0.4F, f, f1);
		this.chainWave(neckParts, speed2, degree * 0.5F, 4, f, f1);
		this.chainWave(tailParts, speed2, degree * 0.3F, -4, f, f1);
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);

	}

}
