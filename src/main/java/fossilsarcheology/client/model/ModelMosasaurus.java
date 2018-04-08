package fossilsarcheology.client.model;


import fossilsarcheology.server.entity.prehistoric.EntityMosasaurus;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMosasaurus extends ModelPrehistoric {
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer leftFrontFlipper;
	public AdvancedModelRenderer rightFrontFlipper;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer leftHindFlipper;
	public AdvancedModelRenderer rightHindFlipper;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer lowerTailFin;
	public AdvancedModelRenderer upperTailFin;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer jawBase;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer bottomJaw;
	public AdvancedModelRenderer teeth;
	private ModelAnimator animator;

	public ModelMosasaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.upperBody = new AdvancedModelRenderer(this, 77, 43);
		this.upperBody.setRotationPoint(0.0F, 16.0F, -6.0F);
		this.upperBody.addBox(-4.0F, 0.0F, 0.0F, 8, 7, 10, 0.0F);
		this.leftHindFlipper = new AdvancedModelRenderer(this, 50, 24);
		this.leftHindFlipper.mirror = true;
		this.leftHindFlipper.setRotationPoint(1.5F, 4.5F, 9.5F);
		this.leftHindFlipper.addBox(0.0F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(leftHindFlipper, 0.5235987755982987F, 0.0F, -0.5235987755982987F);
		this.jawBase = new AdvancedModelRenderer(this, 5, 52);
		this.jawBase.setRotationPoint(0.0F, 1.5F, 0.1F);
		this.jawBase.addBox(-2.5F, 0.0F, -6.0F, 5, 2, 6, 0.0F);
		this.bottomJaw = new AdvancedModelRenderer(this, 30, 51);
		this.bottomJaw.setRotationPoint(0.0F, 0.01F, -5.5F);
		this.bottomJaw.addBox(-1.5F, 0.0F, -7.0F, 3, 2, 7, 0.0F);
		this.teeth = new AdvancedModelRenderer(this, 27, 28);
		this.teeth.setRotationPoint(0.0F, -0.03F, 0.0F);
		this.teeth.addBox(-2.0F, 2.0F, -7.0F, 4, 1, 7, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 79, 25);
		this.lowerBody.setRotationPoint(0.0F, 0.5F, 10.0F);
		this.lowerBody.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 10, 0.0F);
		this.head = new AdvancedModelRenderer(this, 1, 40);
		this.head.setRotationPoint(0.0F, -0.5F, -4.0F);
		this.head.addBox(-3.0F, -2.0F, -6.0F, 6, 4, 6, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 85, 12);
		this.tail1.setRotationPoint(0.0F, 0.5F, 10.0F);
		this.tail1.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 7, 0.0F);
		this.rightFrontFlipper = new AdvancedModelRenderer(this, 62, 21);
		this.rightFrontFlipper.setRotationPoint(-2.5F, 6.0F, 2.5F);
		this.rightFrontFlipper.addBox(-1.0F, 0.0F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotateAngle(rightFrontFlipper, 0.5235987755982987F, 0.0F, 0.5235987755982987F);
		this.lowerTailFin = new AdvancedModelRenderer(this, 36, 11);
		this.lowerTailFin.setRotationPoint(0.0F, 2.0F, 8.4F);
		this.lowerTailFin.addBox(-0.5F, 0.0F, -2.0F, 1, 9, 4, 0.0F);
		this.setRotateAngle(lowerTailFin, 1.1335913491703171F, -0.0F, 0.0F);
		this.upperTailFin = new AdvancedModelRenderer(this, 21, 11);
		this.upperTailFin.setRotationPoint(-0.5F, 2.3F, 8.73F);
		this.upperTailFin.addBox(0.0F, 0.0F, -2.0F, 1, 5, 4, 0.0F);
		this.setRotateAngle(upperTailFin, 1.8938567713390468F, -0.0F, 0.0F);
		this.leftFrontFlipper = new AdvancedModelRenderer(this, 62, 21);
		this.leftFrontFlipper.mirror = true;
		this.leftFrontFlipper.setRotationPoint(2.5F, 6.0F, 2.5F);
		this.leftFrontFlipper.addBox(0.0F, 0.0F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotateAngle(leftFrontFlipper, 0.5235987755982987F, 0.0F, -0.5235987755982987F);
		this.neck = new AdvancedModelRenderer(this, 52, 50);
		this.neck.setRotationPoint(0.0F, 3.5F, 0.0F);
		this.neck.addBox(-3.5F, -3.0F, -4.0F, 7, 6, 4, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 27, 40);
		this.upperJaw.setRotationPoint(0.0F, -0.13F, -6.0F);
		this.upperJaw.addBox(-2.0F, -1.0F, -7.0F, 4, 3, 7, 0.0F);
		this.rightHindFlipper = new AdvancedModelRenderer(this, 50, 24);
		this.rightHindFlipper.setRotationPoint(-1.5F, 4.5F, 9.5F);
		this.rightHindFlipper.addBox(-1.0F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(rightHindFlipper, 0.5235987755982987F, 0.0F, 0.5235987755982987F);
		this.tail2 = new AdvancedModelRenderer(this, 59, 0);
		this.tail2.setRotationPoint(0.0F, 0.5F, 7.0F);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 9, 0.0F);
		this.lowerBody.addChild(this.leftHindFlipper);
		this.head.addChild(this.jawBase);
		this.jawBase.addChild(this.bottomJaw);
		this.upperJaw.addChild(this.teeth);
		this.upperBody.addChild(this.lowerBody);
		this.neck.addChild(this.head);
		this.lowerBody.addChild(this.tail1);
		this.upperBody.addChild(this.rightFrontFlipper);
		this.tail2.addChild(this.lowerTailFin);
		this.tail2.addChild(this.upperTailFin);
		this.upperBody.addChild(this.leftFrontFlipper);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.upperJaw);
		this.lowerBody.addChild(this.rightHindFlipper);
		this.tail1.addChild(this.tail2);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
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
		ModelUtils.rotate(animator, jawBase, 15, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(EntityMosasaurus.SHAKE_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, -23F, 0);
		ModelUtils.rotate(animator, head, 0, -23F, 0);
		ModelUtils.rotate(animator, jawBase, 15F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, 23F, 0);
		ModelUtils.rotate(animator, head, 0, 23F, 0);
		ModelUtils.rotate(animator, jawBase, 15F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, -23F, 0);
		ModelUtils.rotate(animator, head, 0, -23F, 0);
		ModelUtils.rotate(animator, jawBase, 15F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, 23F, 0);
		ModelUtils.rotate(animator, head, 0, 23F, 0);
		ModelUtils.rotate(animator, jawBase, 15F, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, head, 0, -10F, 0);
		ModelUtils.rotate(animator, jawBase, 35F, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.lowerBody, this.tail1, this.tail2, this.upperTailFin};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).weakProgress;
			sitAnimationRotationPrev(rightFrontFlipper, sitProgress, (float) Math.toRadians(30.0D), 0, (float) Math.toRadians(30.0D));
			sitAnimationRotationPrev(lowerTailFin, sitProgress, (float) Math.toRadians(64.95D), 0, 0);
			sitAnimationRotationPrev(upperTailFin, sitProgress, (float) Math.toRadians(108.51D), 0, 0);
			sitAnimationRotationPrev(leftFrontFlipper, sitProgress, (float) Math.toRadians(30.0D), (float) Math.toRadians(18.26D), -((float) Math.toRadians(30.0D)));
			sitAnimationRotationPrev(lowerBody, sitProgress, -((float) Math.toRadians(7.83D)), (float) Math.toRadians(5.22D), -((float) Math.toRadians(2.61D)));
			sitAnimationRotationPrev(head, sitProgress, (float) Math.toRadians(2.61D), (float) Math.toRadians(13.04D), -((float) Math.toRadians(13.04D)));
			sitAnimationRotationPrev(neck, sitProgress, (float) Math.toRadians(7.83D), 0, 0);
			sitAnimationRotationPrev(tail2, sitProgress, (float) Math.toRadians(10.43D), -((float) Math.toRadians(13.04D)), -((float) Math.toRadians(5.22D)));
			sitAnimationRotationPrev(leftHindFlipper, sitProgress, (float) Math.toRadians(30.0D), 0, -((float) Math.toRadians(30.0D)));
			sitAnimationRotationPrev(rightHindFlipper, sitProgress, (float) Math.toRadians(30.0D), 0, (float) Math.toRadians(30.0D));
			sitAnimationRotationPrev(jawBase, sitProgress, (float) Math.toRadians(13.04D), 0, 0);
		}
		this.lowerTailFin.rotateAngleY = this.upperTailFin.rotateAngleY;
		float speed = 0.1F;
		float speed2 = 0.4F;
		this.lowerTailFin.setScale(1.01F, 1.01F, 1.01F);
		this.chainSwing(tailParts, speed2, 0.3F, -3, f, f1);
		this.swing(upperBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.swing(lowerBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.chainSwing(neckParts, speed2, -0.1F, 2, f, f1);
		this.flap(leftFrontFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		this.flap(rightFrontFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.flap(leftHindFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.flap(rightHindFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
		if (entity.getPassengers().isEmpty()) {
			ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
			ModelUtils.faceTargetMod(head, f3, f4, 0.5F);
		}
	}

}
