package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityCompsognathus;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCompsognathus extends ModelPrehistoric {

	public AdvancedModelRenderer body;
	public AdvancedModelRenderer LeftUpperLeg;
	public AdvancedModelRenderer RightUpperLeg;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer RightUpperArm;
	public AdvancedModelRenderer LeftUpperArm;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer headPivot;
	public AdvancedModelRenderer headFront;
	public AdvancedModelRenderer crest;
	public AdvancedModelRenderer headFront_1;
	public AdvancedModelRenderer EyeR;
	public AdvancedModelRenderer EyeL;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer RightLowerArm;
	public AdvancedModelRenderer RightUpperArmWing;
	public AdvancedModelRenderer LeftLowerArm;
	public AdvancedModelRenderer LeftUpperArmWing;
	public AdvancedModelRenderer LeftLowerLeg;
	public AdvancedModelRenderer LeftFoot;
	public AdvancedModelRenderer RightLowerLeg;
	public AdvancedModelRenderer RightFoot;
	private ModelAnimator animator;

	public ModelCompsognathus() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.LeftFoot = new AdvancedModelRenderer(this, 38, 0);
		this.LeftFoot.mirror = true;
		this.LeftFoot.setRotationPoint(0.5F, 4.7F, 0.7F);
		this.LeftFoot.addBox(-1.5F, 0.0F, -2.4F, 3, 0, 3, 0.0F);
		ModelUtils.setRotateAngle(LeftFoot, 0.22689280275926282F, 0.0F, 0.0F);
		this.LeftUpperLeg = new AdvancedModelRenderer(this, 38, 8);
		this.LeftUpperLeg.mirror = true;
		this.LeftUpperLeg.setRotationPoint(2.0F, 15.2F, 4.0F);
		this.LeftUpperLeg.addBox(0.0F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
		this.RightUpperLeg = new AdvancedModelRenderer(this, 38, 8);
		this.RightUpperLeg.mirror = true;
		this.RightUpperLeg.setRotationPoint(-2.0F, 15.2F, 4.0F);
		this.RightUpperLeg.addBox(-1.0F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
		this.LeftLowerLeg = new AdvancedModelRenderer(this, 47, 2);
		this.LeftLowerLeg.mirror = true;
		this.LeftLowerLeg.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.LeftLowerLeg.addBox(-0.01F, -0.2F, 0.3F, 1, 5, 1, 0.0F);
		ModelUtils.setRotateAngle(LeftLowerLeg, -0.22689280275926282F, 0.0F, 0.0F);
		this.RightUpperArm = new AdvancedModelRenderer(this, 0, 0);
		this.RightUpperArm.setRotationPoint(-2.0F, -1.5F, -1.5F);
		this.RightUpperArm.addBox(-1.01F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		ModelUtils.setRotateAngle(RightUpperArm, -0.17453292519943295F, 0.0F, 0.0F);
		this.EyeL = new AdvancedModelRenderer(this, 16, 14);
		this.EyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.EyeL.addBox(1.51F, -2.3F, -4.3F, 0, 3, 4, 0.0F);
		this.head = new AdvancedModelRenderer(this, 16, 2);
		this.head.setRotationPoint(0F, 0F, 0F);
		this.head.addBox(-1.5F, -2.3F, -4.3F, 3, 3, 4, 0.0F);
		ModelUtils.setRotateAngle(head, 0F, 0.0F, 0.0F);
		this.headPivot = new AdvancedModelRenderer(this, 0, 0);
		this.headPivot.setRotationPoint(0.0F, -2.4F, 0.2F);
		this.headPivot.addBox(0F, 0F, 0F, 0, 0, 0, 0F);
		ModelUtils.setRotateAngle(headPivot, -0.6829473363053812F, 0.0F, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 4, 4);
		this.neck.mirror = true;
		this.neck.setRotationPoint(0.0F, -2.3F, -2.3F);
		this.neck.addBox(-1.0F, -4.0F, -1.5F, 2, 5, 3, 0.0F);
		ModelUtils.setRotateAngle(neck, 0.7853981633974483F, 0.0F, 0.0F);
		this.RightLowerLeg = new AdvancedModelRenderer(this, 47, 2);
		this.RightLowerLeg.mirror = true;
		this.RightLowerLeg.setRotationPoint(-0.5F, 4.0F, 0.0F);
		this.RightLowerLeg.addBox(-0.49F, -0.2F, 0.3F, 1, 5, 1, 0.0F);
		ModelUtils.setRotateAngle(RightLowerLeg, -0.22689280275926282F, 0.0F, 0.0F);
		this.headFront_1 = new AdvancedModelRenderer(this, 20, 10);
		this.headFront_1.setRotationPoint(0.0F, 0.6F, -2.0F);
		this.headFront_1.addBox(-1.0F, -0.6F, -4.4F, 2, 1, 5, 0.0F);
		this.LeftUpperArm = new AdvancedModelRenderer(this, 0, 0);
		this.LeftUpperArm.setRotationPoint(2.0F, -1.5F, -1.5F);
		this.LeftUpperArm.addBox(0.01F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		ModelUtils.setRotateAngle(LeftUpperArm, -0.17453292519943295F, 0.0F, 0.0F);
		this.RightFoot = new AdvancedModelRenderer(this, 38, 0);
		this.RightFoot.mirror = true;
		this.RightFoot.setRotationPoint(0.0F, 4.7F, 0.7F);
		this.RightFoot.addBox(-1.5F, 0.0F, -2.4F, 3, 0, 3, 0.0F);
		ModelUtils.setRotateAngle(RightFoot, 0.22689280275926282F, 0.0F, 0.0F);
		this.headFront = new AdvancedModelRenderer(this, 28, 0);
		this.headFront.setRotationPoint(0.0F, -0.7F, -5.1F);
		this.headFront.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 3, 0.0F);
		this.EyeR = new AdvancedModelRenderer(this, 16, 14);
		this.EyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.EyeR.addBox(-1.51F, -2.3F, -4.3F, 0, 3, 4, 0.0F);
		this.LeftLowerArm = new AdvancedModelRenderer(this, 4, 0);
		this.LeftLowerArm.mirror = true;
		this.LeftLowerArm.setRotationPoint(0.0F, 1.9F, 0.0F);
		this.LeftLowerArm.addBox(-0.01F, -0.6F, -0.6F, 1, 3, 1, 0.0F);
		ModelUtils.setRotateAngle(LeftLowerArm, -0.22689280275926282F, 0.0F, 0.0F);
		this.body = new AdvancedModelRenderer(this, 0, 18);
		this.body.mirror = true;
		this.body.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.body.addBox(-2.0F, -4.0F, -3.0F, 4, 5, 8, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 26, 20);
		this.tail2.mirror = true;
		this.tail2.setRotationPoint(0.0F, 0.8F, 3.9F);
		this.tail2.addBox(-1.0F, -0.6F, 0.0F, 2, 2, 10, 0.0F);
		this.RightLowerArm = new AdvancedModelRenderer(this, 4, 0);
		this.RightLowerArm.mirror = true;
		this.RightLowerArm.setRotationPoint(-1.1F, 1.9F, 0.0F);
		this.RightLowerArm.addBox(0.19F, -0.6F, -0.6F, 1, 3, 1, 0.0F);
		ModelUtils.setRotateAngle(RightLowerArm, -0.22689280275926282F, 0.0F, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 4, 22);
		this.tail1.mirror = true;
		this.tail1.setRotationPoint(0.0F, -4.0F, 4.1F);
		this.tail1.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
		ModelUtils.setRotateAngle(tail1, -0.045553093477052F, 0.0F, 0.0F);
		this.RightUpperArmWing = new AdvancedModelRenderer(this, 0, 11);
		this.RightUpperArmWing.mirror = true;
		this.RightUpperArmWing.setRotationPoint(0.5F, 0.2F, -0.5F);
		this.RightUpperArmWing.addBox(-0.4F, -2.1F, 0.3F, 1, 4, 2, 0.0F);
		this.LeftUpperArmWing = new AdvancedModelRenderer(this, 0, 11);
		this.LeftUpperArmWing.mirror = true;
		this.LeftUpperArmWing.setRotationPoint(0.5F, 0.2F, -0.5F);
		this.LeftUpperArmWing.addBox(-0.4F, -2.1F, 0.3F, 1, 4, 2, 0.0F);
		this.crest = new AdvancedModelRenderer(this, 9, 12);
		this.crest.setRotationPoint(0.0F, -0.6F, -2.4F);
		this.crest.addBox(0.0F, -1.0F, 2.0F, 1, 2, 3, 0.0F);
		ModelUtils.setRotateAngle(crest, 0.31869712141416456F, 0.0F, 0.0F);
		this.LeftLowerLeg.addChild(this.LeftFoot);
		this.LeftUpperLeg.addChild(this.LeftLowerLeg);
		this.body.addChild(this.RightUpperArm);
		this.head.addChild(this.EyeL);
		this.headPivot.addChild(this.head);
		this.neck.addChild(this.headPivot);
		this.body.addChild(this.neck);
		this.RightUpperLeg.addChild(this.RightLowerLeg);
		this.head.addChild(this.headFront_1);
		this.body.addChild(this.LeftUpperArm);
		this.RightLowerLeg.addChild(this.RightFoot);
		this.head.addChild(this.headFront);
		this.head.addChild(this.EyeR);
		this.LeftUpperArm.addChild(this.LeftLowerArm);
		this.tail1.addChild(this.tail2);
		this.RightUpperArm.addChild(this.RightLowerArm);
		this.body.addChild(this.tail1);
		this.RightLowerArm.addChild(this.RightUpperArmWing);
		this.LeftLowerArm.addChild(this.LeftUpperArmWing);
		this.head.addChild(this.crest);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		RightUpperLeg.render(f5);
		body.render(f5);
		LeftUpperLeg.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityCompsognathus compsognathus = (EntityCompsognathus) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(compsognathus.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, headFront_1, 29, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(compsognathus.ATTACK_ANIMATION);
		animator.startKeyframe(15);
		animator.move(LeftUpperLeg, 0, 1F, -0.5F);
		animator.move(RightUpperLeg, 0, 1F, -0.5F);
		animator.move(body, 0, 1F, -0.5F);
		ModelUtils.rotate(animator, body, 15, 0, 0);
		ModelUtils.rotate(animator, RightLowerLeg, -30, 0, 0);
		ModelUtils.rotate(animator, LeftLowerLeg, -30, 0, 0);
		ModelUtils.rotate(animator, RightFoot, 30, 0, 0);
		ModelUtils.rotate(animator, LeftFoot, 30, 0, 0);
		ModelUtils.rotate(animator, LeftUpperArm, 0, 0, -50);
		ModelUtils.rotate(animator, RightUpperArm, 0, 0, 50);
		ModelUtils.rotate(animator, head, -20, 0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.startKeyframe(5);
		animator.move(LeftUpperLeg, 0, -9F, 0F);
		animator.move(RightUpperLeg, 0, -9F, 0F);
		animator.move(body, 0, -10F, 0F);
		ModelUtils.rotate(animator, body, -25, 0, 0);
		ModelUtils.rotate(animator, RightUpperLeg, -35, 0, 0);
		ModelUtils.rotate(animator, LeftUpperLeg, -35, 0, 0);
		ModelUtils.rotate(animator, RightLowerLeg, -30, 0, 0);
		ModelUtils.rotate(animator, LeftLowerLeg, -30, 0, 0);
		ModelUtils.rotate(animator, RightFoot, -55, 0, 0);
		ModelUtils.rotate(animator, LeftFoot, -55, 0, 0);
		ModelUtils.rotate(animator, LeftUpperArm, 0, 0, -50);
		ModelUtils.rotate(animator, RightUpperArm, 0, 0, 50);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.resetKeyframe(5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail1, this.tail2};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		AdvancedModelRenderer[] leftArmParts = {this.LeftUpperArm, this.LeftLowerArm};
		AdvancedModelRenderer[] rightArmParts = {this.RightUpperArm, this.RightLowerArm};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);

		float speed = 0.1F;
		float speed2 = 0.5F;
		float degree = 0.5F;
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(neck, sitProgress, 0.18203784098300857F, 0.0F, 0.0F);
			sitAnimationRotation(RightLowerLeg, sitProgress, 2.0943951023931953F + (float) Math.toRadians(15D), 0.0F, 0.0F);
			sitAnimationRotation(tail2, sitProgress, 0.091106186954104F, 0.0F, 0.0F);
			sitAnimationRotation(crest, sitProgress, 0.31869712141416456F, 0.0F, 0.0F);
			sitAnimationRotation(RightLowerArm, sitProgress, -0.22689280275926282F, 0.0F, 0.0F);
			sitAnimationRotation(LeftLowerLeg, sitProgress, 2.0943951023931953F + (float) Math.toRadians(15D), 0.0F, 0.0F);
			sitAnimationRotation(tail1, sitProgress, -0.18203784098300857F, 0.0F, 0.0F);
			sitAnimationRotation(LeftLowerArm, sitProgress, -0.22689280275926282F, 0.0F, 0.0F);
			sitAnimationRotation(LeftFoot, sitProgress, -0.7853981633974483F - (float) Math.toRadians(55D), 0.0F, 0.0F);
			sitAnimationRotation(RightUpperLeg, sitProgress, -1.0471975511965976F + (float) Math.toRadians(25D), 0.0F, 0.0F);
			sitAnimationRotation(RightUpperArm, sitProgress, -0.17453292519943295F, 0.0F, 0.7740535232594852F);
			sitAnimationRotation(LeftUpperArm, sitProgress, -0.17453292519943295F, 0.0F, -0.6373942428283291F);
			sitAnimationRotation(LeftUpperLeg, sitProgress, -1.0471975511965976F + (float) Math.toRadians(25D), 0.0F, 0.0F);
			sitAnimationRotation(RightFoot, sitProgress, -0.7853981633974483F - (float) Math.toRadians(55D), 0.0F, 0.0F);
			sitAnimationPos(body, sitProgress, 0F, 22F - ModelUtils.getDefaultPositionY(body), 0F);
			sitAnimationPos(RightUpperLeg, sitProgress, 0F, 19F - ModelUtils.getDefaultPositionY(RightUpperLeg), 0F);
			sitAnimationPos(LeftUpperLeg, sitProgress, 0F, 19F - ModelUtils.getDefaultPositionY(LeftUpperLeg), 0F);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(RightLowerLeg, sitProgress, 2.0943951023931953F + (float) Math.toRadians(15D), 0.0F, 0.0F);
			sitAnimationRotation(LeftLowerLeg, sitProgress, 2.0943951023931953F + (float) Math.toRadians(15D), 0.0F, 0.0F);
			sitAnimationRotation(LeftFoot, sitProgress, -0.7853981633974483F - (float) Math.toRadians(55D), 0.0F, 0.0F);
			sitAnimationRotation(RightUpperLeg, sitProgress, -1.0471975511965976F + (float) Math.toRadians(25D), 0.0F, 0.0F);
			sitAnimationRotation(LeftUpperLeg, sitProgress, -1.0471975511965976F + (float) Math.toRadians(25D), 0.0F, 0.0F);
			sitAnimationRotation(RightFoot, sitProgress, -0.7853981633974483F - (float) Math.toRadians(55D), 0.0F, 0.0F);
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(93.91D), -((float) Math.toRadians(15.65D)), (float) Math.toRadians(75.65D));
			sitAnimationRotation(LeftLowerArm, sitProgress, -((float) Math.toRadians(13.0D)), 0, 0);
			sitAnimationRotation(tail2, sitProgress, (float) Math.toRadians(7.83D), -((float) Math.toRadians(10.43D)), (float) Math.toRadians(2.61D));
			sitAnimationRotation(RightUpperArm, sitProgress, -((float) Math.toRadians(10.0D)), 0, (float) Math.toRadians(44.35D));
			sitAnimationRotation(head, sitProgress, -((float) Math.toRadians(67.83D)), -((float) Math.toRadians(7.83D)), -((float) Math.toRadians(5.22D)));
			sitAnimationRotation(tail1, sitProgress, -((float) Math.toRadians(23.48D)), -((float) Math.toRadians(18.26D)), 0);
			sitAnimationRotation(LeftUpperArm, sitProgress, -((float) Math.toRadians(10.0D)), 0, -((float) Math.toRadians(36.52D)));
			sitAnimationRotation(RightLowerArm, sitProgress, -((float) Math.toRadians(13.0D)), 0, 0);
			sitAnimationRotation(crest, sitProgress, (float) Math.toRadians(18.26D), 0, 0);
			sitAnimationPos(body, sitProgress, 0F, 22F - ModelUtils.getDefaultPositionY(body), 0F);
			sitAnimationPos(RightUpperLeg, sitProgress, 0F, 19F - ModelUtils.getDefaultPositionY(RightUpperLeg), 0F);
			sitAnimationPos(LeftUpperLeg, sitProgress, 0F, 19F - ModelUtils.getDefaultPositionY(LeftUpperLeg), 0F);

		}
		this.bob(body, speed, degree * 0.7F, false, entity.ticksExisted, 1);
		this.walk(LeftUpperLeg, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(LeftLowerLeg, speed2, 0.2F, false, 0F, -0.6F, f, f1);
		this.walk(LeftFoot, speed2, -0.6F, true, 2.5F, -0.4F, f, f1);
		this.walk(RightUpperLeg, speed2, 0.6F, true, 0F, -0.4F, f, f1);
		this.walk(RightLowerLeg, speed2, 0.2F, true, 0F, 0.6F, f, f1);
		this.walk(RightFoot, speed2, -0.6F, false, 2.5F, 0.4F, f, f1);
		this.chainWave(tailParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainWave(leftArmParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainWave(rightArmParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, degree * 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed2, degree * 0.25F, -3, f, f1);
		this.chainWave(neckParts, speed, degree * 0.15F, 3, entity.ticksExisted, 1);
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}

}
