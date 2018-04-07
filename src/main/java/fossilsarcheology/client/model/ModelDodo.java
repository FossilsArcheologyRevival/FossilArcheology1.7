package fossilsarcheology.client.model;


import fossilsarcheology.server.entity.prehistoric.EntityDodo;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelDodo extends ModelPrehistoric {
	public AdvancedModelRenderer body;
	public AdvancedModelRenderer rightLeg;
	public AdvancedModelRenderer leftLeg;
	public AdvancedModelRenderer tail;
	public AdvancedModelRenderer rightWing;
	public AdvancedModelRenderer leftWing;
	public AdvancedModelRenderer bottom;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer headPivot;
	public AdvancedModelRenderer beakUpper;
	public AdvancedModelRenderer beakLower;
	public AdvancedModelRenderer rightFoot;
	public AdvancedModelRenderer leftFoot;
	private ModelAnimator animator;

	public ModelDodo() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leftWing = new AdvancedModelRenderer(this, 34, 25);
		this.leftWing.setRotationPoint(3.5F, 3.0F, 3.0F);
		this.leftWing.addBox(0.0F, -1.0F, -2.0F, 1, 3, 4, 0.0F);
		this.leftFoot = new AdvancedModelRenderer(this, 22, 24);
		this.leftFoot.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.leftFoot.addBox(-1.5F, 0.0F, -3.0F, 3, 0, 3, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 0, 16);
		this.neck.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.neck.addBox(-2.0F, -8.0F, -1.5F, 4, 8, 3, 0.0F);
		this.setRotateAngle(neck, -0.33161255787892263F, 0.0F, 0.0F);
		this.beakUpper = new AdvancedModelRenderer(this, 48, 13);
		this.beakUpper.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.beakUpper.addBox(-1.5F, -1.0F, -5.0F, 3, 2, 5, 0.0F);
		this.leftLeg = new AdvancedModelRenderer(this, 28, 27);
		this.leftLeg.setRotationPoint(1.5F, 21.0F, 0.0F);
		this.leftLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 0, 0.0F);
		this.beakLower = new AdvancedModelRenderer(this, 48, 20);
		this.beakLower.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.beakLower.addBox(-1.5F, 0.0F, -5.0F, 3, 1, 5, 0.0F);
		this.body = new AdvancedModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0F, 13.0F, -6.0F);
		this.body.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 9, 0.0F);
		this.rightFoot = new AdvancedModelRenderer(this, 22, 24);
		this.rightFoot.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.rightFoot.addBox(-1.5F, 0.0F, -3.0F, 3, 0, 3, 0.0F);
		this.rightWing = new AdvancedModelRenderer(this, 34, 25);
		this.rightWing.setRotationPoint(-3.5F, 3.0F, 3.0F);
		this.rightWing.addBox(-1.0F, -1.0F, -2.0F, 1, 3, 4, 0.0F);
		this.bottom = new AdvancedModelRenderer(this, 0, 27);
		this.bottom.setRotationPoint(0.0F, 7.0F, 5.0F);
		this.bottom.addBox(-3.5F, 0.0F, -2.0F, 7, 1, 4, 0.0F);
		this.tail = new AdvancedModelRenderer(this, 14, 16);
		this.tail.setRotationPoint(0.0F, 1.0F, 6.5F);
		this.tail.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(tail, -0.18587756533739608F, -0.0F, 0.0F);
		this.rightLeg = new AdvancedModelRenderer(this, 28, 27);
		this.rightLeg.setRotationPoint(-1.5F, 21.0F, 0.0F);
		this.rightLeg.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 0, 0.0F);
		this.head = new AdvancedModelRenderer(this, 35, 5);
		this.head.addBox(-2.5F, -3.0F, -4.0F, 5, 4, 4, 0.0F);
		this.headPivot = new AdvancedModelRenderer(this, 0, 0);
		this.headPivot.setRotationPoint(0.0F, -6.0F, 0.3F);
		this.setRotateAngle(headPivot, 0.33161255787892263F, 0.0F, 0.0F);
		this.body.addChild(this.leftWing);
		this.leftLeg.addChild(this.leftFoot);
		this.body.addChild(this.neck);
		this.head.addChild(this.beakUpper);
		this.head.addChild(this.beakLower);
		this.rightLeg.addChild(this.rightFoot);
		this.body.addChild(this.rightWing);
		this.body.addChild(this.bottom);
		this.body.addChild(this.tail);
		this.headPivot.addChild(this.head);
		this.neck.addChild(this.headPivot);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.body.render(f5);
		this.leftLeg.render(f5);
		this.rightLeg.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, beakLower, 29, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -41, 0, 0);
		ModelUtils.rotate(animator, head, 57, 0, 0);
		ModelUtils.rotate(animator, beakLower, 15, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, neck, 16, 0, 0);
		ModelUtils.rotate(animator, head, 14, 0, 0);
		ModelUtils.rotate(animator, beakLower, -15, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		EntityDodo dodo = (EntityDodo) entity;
		if (this.isChild) {
			this.head.setScale(1.5F, 1.5F, 1.5F);
			this.beakLower.rotationPointY = -0.5F;
			this.beakUpper.rotationPointZ = -3.5F;
			this.beakLower.rotationPointZ = -3.5F;

		} else {
			this.head.setScale(1F, 1F, 1F);
			this.beakLower.rotationPointY = 0F;
			this.beakUpper.rotationPointZ = -4F;
			this.beakLower.rotationPointZ = -4F;
		}
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		float speed = 0.9F;
		float speed2 = 0.1F;
		ModelUtils.faceTargetMod(head, f3, f4, 1F);
		this.bob(body, speed2, -0.4F, false, entity.ticksExisted, 1);
		this.walk(leftLeg, speed, 1.9F, false, 0F, 0F, f, f1);
		this.walk(rightLeg, speed, 1.9F, true, 0F, 0F, f, f1);
		this.walk(leftFoot, speed, 1.9F, true, 0.3F, 0F, f, f1);
		this.walk(rightFoot, speed, 1.9F, false, 0.3F, 0F, f, f1);
		this.chainWave(tailParts, speed2, 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed2, 0.15F, -3, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed2, 0.1F, -3, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed, 0.4F, -3, f, f1);
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(neck, sitProgress, -((float) Math.toRadians(16.0D)), 0, 0);
			sitAnimationRotation(rightLeg, sitProgress, -((float) Math.toRadians(90.0D)), 0, 0);
			sitAnimationRotation(rightFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(leftLeg, sitProgress, -((float) Math.toRadians(90.0D)), 0, 0);
			sitAnimationRotation(leftFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(tail, sitProgress, -((float) Math.toRadians(10.65D)), 0, 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(16.0D), 0, 0);
			sitAnimationPos(body, sitProgress, 0, 3F, 0);
			sitAnimationPos(rightLeg, sitProgress, 0, 3F, 0);
			sitAnimationPos(leftLeg, sitProgress, 0, 3F, 0);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(neck, sitProgress, -((float) Math.toRadians(16.0D)), -((float) Math.toRadians(16.0D)), 0);
			sitAnimationRotation(rightLeg, sitProgress, -((float) Math.toRadians(90.0D)), 0, 0);
			sitAnimationRotation(rightFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(leftLeg, sitProgress, -((float) Math.toRadians(90.0D)), 0, 0);
			sitAnimationRotation(leftFoot, sitProgress, (float) Math.toRadians(90.0D), 0, 0);
			sitAnimationRotation(tail, sitProgress, -((float) Math.toRadians(10.65D)), 0, 0);
			sitAnimationRotation(head, sitProgress, (float) Math.toRadians(44.0D), 0, 0);
			sitAnimationPos(body, sitProgress, 0, 3F, 0);
			sitAnimationPos(rightLeg, sitProgress, 0, 3F, 0);
			sitAnimationPos(leftLeg, sitProgress, 0, 3F, 0);

		}


	}
}
