package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTriceratops extends ModelPrehistoric {
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer rightFrontThigh;
	public AdvancedModelRenderer leftFrontThigh;
	public AdvancedModelRenderer rightHindThigh;
	public AdvancedModelRenderer leftHindThigh;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer quills;
	public AdvancedModelRenderer tail3;
	public AdvancedModelRenderer quills_1;
	public AdvancedModelRenderer quills_2;
	public AdvancedModelRenderer headPivot;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer beak;
	public AdvancedModelRenderer frill;
	public AdvancedModelRenderer leftHorn1;
	public AdvancedModelRenderer rightHorn1;
	public AdvancedModelRenderer beakbottom;
	public AdvancedModelRenderer noseHorn;
	public AdvancedModelRenderer frillProtrusions;
	public AdvancedModelRenderer leftHorn2;
	public AdvancedModelRenderer rightHorn2;
	public AdvancedModelRenderer rightFrontLeg;
	public AdvancedModelRenderer leftFrontLeg;
	public AdvancedModelRenderer rightHindLeg;
	public AdvancedModelRenderer leftHindLeg;
	private ModelAnimator animator;

	public ModelTriceratops() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.upperBody = new AdvancedModelRenderer(this, 77, 0);
		this.upperBody.setRotationPoint(0.0F, 14.6F, 1.0F);
		this.upperBody.addBox(-3.5F, 0.0F, -6.0F, 7, 6, 6, 0.0F);
		ModelUtils.setRotateAngle(upperBody, 0.12217304763960307F, -0.0F, 0.0F);
		this.frillProtrusions = new AdvancedModelRenderer(this, 20, 8);
		this.frillProtrusions.setRotationPoint(1.0F, 0.1F, 0.0F);
		this.frillProtrusions.addBox(-6.0F, -7.6F, -0.1F, 10, 8, 1, 0.0F);
		ModelUtils.setRotateAngle(frillProtrusions, 0.0013962634015954637F, -0.0F, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 0, 0);
		this.neck.setRotationPoint(0.5F, 0.8F, -5.0F);
		this.neck.addBox(-2.0F, 0.0F, -3.0F, 3, 4, 3, 0.0F);
		ModelUtils.setRotateAngle(neck, -0.06981317007977318F, -0.0F, 0.0F);
		this.beakbottom = new AdvancedModelRenderer(this, 14, 43);
		this.beakbottom.setRotationPoint(0.0F, 2.0F, -3.5F);
		this.beakbottom.addBox(-1.0F, -1.0F, -2.7F, 2, 1, 3, 0.0F);
		ModelUtils.setRotateAngle(beakbottom, 0.2513223807039196F, -0.0F, 0.0F);
		this.leftHindThigh = new AdvancedModelRenderer(this, 12, 24);
		this.leftHindThigh.setRotationPoint(2.4F, 18.0F, 4.0F);
		this.leftHindThigh.addBox(0.0F, -1.0F, -2.0F, 2, 4, 3, 0.0F);
		this.rightHorn2 = new AdvancedModelRenderer(this, 33, 27);
		this.rightHorn2.setRotationPoint(0.1F, 0.29F, -3.2F);
		this.rightHorn2.addBox(-0.5F, -0.8F, -4.0F, 1, 1, 4, 0.0F);
		ModelUtils.setRotateAngle(rightHorn2, 0.1832595714594046F, 0.0F, 0.0F);
		this.quills_2 = new AdvancedModelRenderer(this, 18, 52);
		this.quills_2.setRotationPoint(-0.5F, 0.7F, 3.5F);
		this.quills_2.addBox(0.0F, -5.0F, -2.0F, 1, 5, 2, 0.0F);
		ModelUtils.setRotateAngle(quills_2, 0.05235987755982988F, 0.0F, 0.0F);
		this.leftFrontThigh = new AdvancedModelRenderer(this, 0, 16);
		this.leftFrontThigh.setRotationPoint(2.8F, 19.4F, -3.0F);
		this.leftFrontThigh.addBox(0.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F);
		ModelUtils.setRotateAngle(leftFrontThigh, 0.20943951023931953F, -0.0F, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 68, 17);
		this.tail2.setRotationPoint(0.0F, 0.6F, 2.5F);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		ModelUtils.setRotateAngle(tail2, -0.05044001538263612F, -0.0F, 0.0F);
		this.quills_1 = new AdvancedModelRenderer(this, 10, 51);
		this.quills_1.setRotationPoint(-0.5F, 0.2F, 2.5F);
		this.quills_1.addBox(0.0F, -5.0F, -2.0F, 1, 5, 3, 0.0F);
		ModelUtils.setRotateAngle(quills_1, 0.05235987755982988F, 0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 104, 1);
		this.lowerBody.setRotationPoint(0.0F, 1.98F, -1.5F);
		this.lowerBody.addBox(-3.0F, -2.0F, 0.0F, 6, 6, 6, 0.0F);
		ModelUtils.setRotateAngle(lowerBody, -0.17907078125461823F, 0.0F, 0.0F);
		this.leftFrontLeg = new AdvancedModelRenderer(this, 16, 19);
		this.leftFrontLeg.setRotationPoint(1.3F, 1.6F, 0.5F);
		this.leftFrontLeg.addBox(-1.5F, -1.0F, -3.1F, 2, 2, 3, 0.0F);
		ModelUtils.setRotateAngle(leftFrontLeg, 1.3613568165555772F, -0.0F, 0.0F);
		this.rightHindThigh = new AdvancedModelRenderer(this, 12, 24);
		this.rightHindThigh.mirror = true;
		this.rightHindThigh.setRotationPoint(-2.4F, 18.0F, 4.0F);
		this.rightHindThigh.addBox(-2.0F, -1.0F, -2.0F, 2, 4, 3, 0.0F);
		this.tail3 = new AdvancedModelRenderer(this, 54, 21);
		this.tail3.setRotationPoint(0.0F, 0.5F, 2.5F);
		this.tail3.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
		ModelUtils.setRotateAngle(tail3, 0.2555162024919699F, -0.0F, 0.0F);
		this.rightFrontLeg = new AdvancedModelRenderer(this, 16, 19);
		this.rightFrontLeg.mirror = true;
		this.rightFrontLeg.setRotationPoint(-1.3F, 1.6F, 0.5F);
		this.rightFrontLeg.addBox(-0.5F, -1.0F, -3.1F, 2, 2, 3, 0.0F);
		ModelUtils.setRotateAngle(rightFrontLeg, 1.3613568165555772F, -0.0F, 0.0F);
		this.leftHindLeg = new AdvancedModelRenderer(this, 32, 19);
		this.leftHindLeg.setRotationPoint(0.4F, 2.0F, 0.3F);
		this.leftHindLeg.addBox(-0.5F, -1.0F, -4.0F, 2, 2, 4, 0.0F);
		ModelUtils.setRotateAngle(leftHindLeg, 1.5707963267948966F, -0.0F, 0.0F);
		this.rightHindLeg = new AdvancedModelRenderer(this, 32, 19);
		this.rightHindLeg.mirror = true;
		this.rightHindLeg.setRotationPoint(-0.4F, 2.0F, 0.3F);
		this.rightHindLeg.addBox(-1.5F, -1.0F, -4.0F, 2, 2, 4, 0.0F);
		ModelUtils.setRotateAngle(rightHindLeg, 1.5707963267948966F, -0.0F, 0.0F);
		this.frill = new AdvancedModelRenderer(this, 20, 0);
		this.frill.setRotationPoint(0.0F, -0.8F, -1.2F);
		this.frill.addBox(-4.0F, -7.0F, 0.0F, 8, 7, 1, 0.0F);
		ModelUtils.setRotateAngle(frill, -0.6618288523562498F, -0.0F, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 48, 10);
		this.tail1.setRotationPoint(0.0F, -1.7F, 5.5F);
		this.tail1.addBox(-2.5F, -0.2F, 0.0F, 5, 4, 3, 0.0F);
		ModelUtils.setRotateAngle(tail1, -0.463210383479295F, -0.0F, 0.0F);
		this.beak = new AdvancedModelRenderer(this, 0, 43);
		this.beak.setRotationPoint(0.0F, -0.1F, -3.4F);
		this.beak.addBox(-1.0F, -1.0F, -3.5F, 2, 2, 4, 0.0F);
		ModelUtils.setRotateAngle(beak, 0.2808606942177475F, -0.0F, 0.0F);
		this.rightHorn1 = new AdvancedModelRenderer(this, 32, 35);
		this.rightHorn1.setRotationPoint(-1.5F, -1.7F, -1.0F);
		this.rightHorn1.addBox(-0.4F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		ModelUtils.setRotateAngle(rightHorn1, -0.8051203839449842F, 0.06981317007977318F, -0.20943951023931953F);
		this.headPivot = new AdvancedModelRenderer(this, 0, 34);
		this.headPivot.setRotationPoint(-0.5F, 1.8F, -2.2F);
		ModelUtils.setRotateAngle(headPivot, 0.20943951023931953F, -0.0F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 34);
		this.head.setRotationPoint(0, 0, 0);
		this.head.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4, 0.0F);
		ModelUtils.setRotateAngle(head, 0F, -0.0F, 0.0F);
		this.rightFrontThigh = new AdvancedModelRenderer(this, 0, 16);
		this.rightFrontThigh.mirror = true;
		this.rightFrontThigh.setRotationPoint(-2.8F, 19.4F, -3.0F);
		this.rightFrontThigh.addBox(-2.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F);
		ModelUtils.setRotateAngle(rightFrontThigh, 0.20943951023931953F, -0.0F, 0.0F);
		this.leftHorn1 = new AdvancedModelRenderer(this, 32, 35);
		this.leftHorn1.setRotationPoint(1.5F, -1.7F, -1.0F);
		this.leftHorn1.addBox(-0.6F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		ModelUtils.setRotateAngle(leftHorn1, -0.8051203839449842F, -0.06981317007977318F, 0.20943951023931953F);
		this.leftHorn2 = new AdvancedModelRenderer(this, 33, 27);
		this.leftHorn2.setRotationPoint(-0.1F, 0.29F, -3.6F);
		this.leftHorn2.addBox(-0.5F, -0.8F, -4.0F, 1, 1, 4, 0.0F);
		ModelUtils.setRotateAngle(leftHorn2, 0.1832595714594046F, 0.0F, 0.0F);
		this.quills = new AdvancedModelRenderer(this, 0, 50);
		this.quills.setRotationPoint(-0.5F, 1.2F, 2.8F);
		this.quills.addBox(0.0F, -5.0F, -4.0F, 1, 5, 4, 0.0F);
		ModelUtils.setRotateAngle(quills, 0.05235987755982988F, 0.0F, 0.0F);
		this.noseHorn = new AdvancedModelRenderer(this, 24, 37);
		this.noseHorn.setRotationPoint(0.0F, -0.2F, -2.0F);
		this.noseHorn.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
		ModelUtils.setRotateAngle(noseHorn, -1.1760028499937791F, -0.0F, 0.0F);
		this.frill.addChild(this.frillProtrusions);
		this.upperBody.addChild(this.neck);
		this.headPivot.addChild(this.head);
		this.head.addChild(this.beakbottom);
		this.rightHorn1.addChild(this.rightHorn2);
		this.tail3.addChild(this.quills_2);
		this.tail1.addChild(this.tail2);
		this.tail2.addChild(this.quills_1);
		this.upperBody.addChild(this.lowerBody);
		this.leftFrontThigh.addChild(this.leftFrontLeg);
		this.tail2.addChild(this.tail3);
		this.rightFrontThigh.addChild(this.rightFrontLeg);
		this.leftHindThigh.addChild(this.leftHindLeg);
		this.rightHindThigh.addChild(this.rightHindLeg);
		this.head.addChild(this.frill);
		this.lowerBody.addChild(this.tail1);
		this.head.addChild(this.beak);
		this.head.addChild(this.rightHorn1);
		this.neck.addChild(this.headPivot);
		this.head.addChild(this.leftHorn1);
		this.leftHorn1.addChild(this.leftHorn2);
		this.tail1.addChild(this.quills);
		this.beak.addChild(this.noseHorn);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.rightFrontThigh.render(f5);
		this.leftFrontThigh.render(f5);
		this.upperBody.render(f5);
		this.rightHindThigh.render(f5);
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
		ModelUtils.rotate(animator, beakbottom, 24, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, head, 30, 0, 0);
		ModelUtils.rotate(animator, neck, 25, 0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -30, 0, 0);
		ModelUtils.rotate(animator, head, -30, 0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.resetKeyframe(5);

		// animator.setAnimationId(EntityNewPrehistoric.animation_sit.animationId);
		// animator.startPhase(20);
		// sitPose(true);
		// animator.endPhase();
		//
		// animator.setAnimationId(EntityNewPrehistoric.animation_getUp.animationId);
		// animator.startPhase(0);
		// sitPose(true);
		// animator.endPhase();
		// animator.resetPhase(20);
		// animator.endPhase();

	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail1, this.tail2, this.tail3};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);

		float speed = 0.5F;
		float speed2 = 0.1F;
		float degree = 0.5F;
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(rightHindLeg, sitProgress, (float) Math.toRadians(110.0D), 0, 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(13.04D)), 0, 0);
			sitAnimationRotation(tail3, sitProgress, -(float) Math.toRadians(14.64D), 0, 0);
			sitAnimationRotation(leftHindThigh, sitProgress, -((float) Math.toRadians(10.0D)), 0, 0);
			sitAnimationRotation(tail2, sitProgress, (float) Math.toRadians(13.04D), 0, 0);
			sitAnimationRotation(rightFrontThigh, sitProgress, -((float) Math.toRadians(13.0D)), 0, 0);
			sitAnimationRotation(leftHindLeg, sitProgress, (float) Math.toRadians(110.0D), 0, 0);
			sitAnimationRotation(leftFrontThigh, sitProgress, -((float) Math.toRadians(13.0D)), 0, 0);
			sitAnimationRotation(tail1, sitProgress, ((float) Math.toRadians(15D)), 0, 0);
			sitAnimationRotation(quills, sitProgress, (float) Math.toRadians(3.0D), 0, 0);
			sitAnimationRotation(upperBody, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(rightHindThigh, sitProgress, -((float) Math.toRadians(10.0D)), 0, 0);
			sitAnimationRotation(rightFrontLeg, sitProgress, -(float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(leftFrontLeg, sitProgress, -(float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(neck, sitProgress, -((float) Math.toRadians(18.26D)), 0, 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(31.3D), 0, 0);
			sitAnimationPos(upperBody, sitProgress, 0F, 16.20F - ModelUtils.getDefaultPositionY(upperBody), 0F);
			sitAnimationPos(rightFrontThigh, sitProgress, 0F, 21.30F - ModelUtils.getDefaultPositionY(rightFrontThigh), 0F);
			sitAnimationPos(leftFrontThigh, sitProgress, 0F, 21.30F - ModelUtils.getDefaultPositionY(leftFrontThigh), 0F);
			sitAnimationPos(rightHindThigh, sitProgress, 0F, 20.50F - ModelUtils.getDefaultPositionY(rightHindThigh), 0F);
			sitAnimationPos(leftHindThigh, sitProgress, 0F, 20.50F - ModelUtils.getDefaultPositionY(leftHindThigh), 0F);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(upperBody, sitProgress, 0, (float) Math.toRadians(2.61D), -((float) Math.toRadians(18.26D)));
			sitAnimationRotation(rightFrontThigh, sitProgress, -((float) Math.toRadians(30D)), (float) Math.toRadians(2.61D), 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(26.09D), (float) Math.toRadians(5.22D), -((float) Math.toRadians(20.87D)));
			sitAnimationRotation(leftFrontThigh, sitProgress, -((float) Math.toRadians(13.0D)), -((float) Math.toRadians(2.61D)), -((float) Math.toRadians(41.74D)));
			sitAnimationRotation(tail3, sitProgress, (float) Math.toRadians(14.64D), 0, 0);
			sitAnimationRotation(leftHindThigh, sitProgress, (float) Math.toRadians(10D), -((float) Math.toRadians(10.43D)), -((float) Math.toRadians(18.26D)));
			sitAnimationRotation(rightFrontLeg, sitProgress, -(float) Math.toRadians(83.48D), 0, 0);
			sitAnimationRotation(rightHindThigh, sitProgress, -(float) Math.toRadians(10.0D), -((float) Math.toRadians(5.22D)), (float) Math.toRadians(26.09D));
			sitAnimationRotation(neck, sitProgress, (float) Math.toRadians(10.43D), -((float) Math.toRadians(15.65D)), -((float) Math.toRadians(20.87D)));
			sitAnimationRotation(leftFrontLeg, sitProgress, -(float) Math.toRadians(50D), 0, 0);
			sitAnimationRotation(rightHindLeg, sitProgress, (float) Math.toRadians(93.91D), (float) Math.toRadians(2.61D), 0);
			sitAnimationRotation(lowerBody, sitProgress, -((float) Math.toRadians(7.83D)), -((float) Math.toRadians(5.22D)), 0);
			sitAnimationRotation(tail1, sitProgress, -((float) Math.toRadians(23.48D)), 0, (float) Math.toRadians(5.22D));
			sitAnimationRotation(leftHindLeg, sitProgress, (float) Math.toRadians(80.87D), 0, 0);
			sitAnimationRotation(tail2, sitProgress, (float) Math.toRadians(18.26D), -((float) Math.toRadians(13.04D)), (float) Math.toRadians(7.83D));
			sitAnimationPos(upperBody, sitProgress, 0F, 16.20F - ModelUtils.getDefaultPositionY(upperBody), 0F);
			sitAnimationPos(rightFrontThigh, sitProgress, 1F, 21.30F - ModelUtils.getDefaultPositionY(rightFrontThigh), 0F);
			sitAnimationPos(leftFrontThigh, sitProgress, 1F, 21.30F - ModelUtils.getDefaultPositionY(leftFrontThigh), 0F);
			sitAnimationPos(rightHindThigh, sitProgress, 1F, 20.50F - ModelUtils.getDefaultPositionY(rightHindThigh), 0F);
			sitAnimationPos(leftHindThigh, sitProgress, 1F, 20.50F - ModelUtils.getDefaultPositionY(leftHindThigh), 0F);
		}
		this.walk(upperBody, speed2, degree * 0.05F, false, 1F, 0F, entity.ticksExisted, 1);
		this.walk(lowerBody, speed2, degree * -0.05F, false, 1F, 0F, entity.ticksExisted, 1);
		this.bob(upperBody, speed2, degree * 0.2F, false, entity.ticksExisted, 1);
		this.walk(leftHindThigh, speed, degree * 0.8F, false, 0F, 0.4F, f, f1);
		this.walk(leftHindLeg, speed, degree * 0.2F, false, 0F, -0.6F, f, f1);
		this.walk(rightHindThigh, speed, degree * 0.8F, true, 0F, 0.4F, f, f1);
		this.walk(rightHindLeg, speed, degree * 0.2F, true, 0F, -0.6F, f, f1);
		this.walk(leftFrontThigh, speed, degree * 0.8F, true, 0F, 0.4F, f, f1);
		this.walk(leftFrontLeg, speed, degree * 0.2F, true, 0F, -0.6F, f, f1);
		this.walk(rightFrontThigh, speed, degree * 0.8F, false, 0F, 0.4F, f, f1);
		this.walk(rightFrontLeg, speed, degree * 0.2F, false, 0F, -0.6F, f, f1);
		this.chainWave(tailParts, speed2, degree * 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed2, degree * 0.35F, -3, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed2, degree * 0.05F, -3, entity.ticksExisted, 1);
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}

	// @Override
	// public void sitPose(boolean animate) {
	// ModelUtils.animateOrSetRotation(animator, animate, lowerBody,
	// -0.17907078125461823F, 0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, leftHindLeg,
	// 0.45378560551852565F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, leftFrontThigh,
	// -0.22689280275926282F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, neck,
	// -0.31869712141416456F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, rightHindLeg,
	// 0.45378560551852565F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, tail1,
	// -0.40980330836826856F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, upperBody,
	// -0.045553093477052F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, quills_1,
	// 0.05235987755982988F, 0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, tail2,
	// 0.22759093446006054F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, rightFrontThigh,
	// -0.22689280275926282F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, rightFrontLeg,
	// 0.22689280275926282F, 0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, beak,
	// 0.2808606942177475F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, head,
	// 0.5462880558742251F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, leftFrontLeg,
	// 0.22689280275926282F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, quills,
	// 0.05235987755982988F, 0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, beakbottom,
	// 0.2513223807039196F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, tail3,
	// 0.2555162024919699F, -0.0F, 0.0F, false);
	// ModelUtils.animateOrSetRotation(animator, animate, quills_2,
	// 0.05235987755982988F, 0.0F, 0.0F, false);
	// ModelUtils.animateToPos(animator, animate, upperBody, 0F, 16.20F -
	// upperBody.initRotationPointY, 0F, true);
	// ModelUtils.animateToPos(animator, animate, rightFrontThigh, 0F, 21.3F -
	// rightFrontThigh.initRotationPointY, 0F, true);
	// ModelUtils.animateToPos(animator, animate, leftFrontThigh, 0F, 21.3F -
	// leftFrontThigh.initRotationPointY, 0F, true);
	// ModelUtils.animateToPos(animator, animate, rightHindThigh, 0F, 19.9F -
	// rightHindThigh.initRotationPointY, 0F, true);
	// ModelUtils.animateToPos(animator, animate, leftHindThigh, 0F, 19.9F -
	// leftHindThigh.initRotationPointY, 0F, true);
	// ModelUtils.setPos(animator, animate, upperBody, 0F, 16.20F, 1F, false);
	// ModelUtils.setPos(animator, animate, rightFrontThigh, -2.8F, 21.30F, -3F,
	// false);
	// ModelUtils.setPos(animator, animate, leftFrontThigh, 2.8F, 21.30F, -3F,
	// false);
	// ModelUtils.setPos(animator, animate, rightHindThigh, -2.4F, 19.90F, 4F,
	// false);
	// ModelUtils.setPos(animator, animate, leftHindThigh, 2.4F, 19.90F, 4F,
	// false);
	// }
}
