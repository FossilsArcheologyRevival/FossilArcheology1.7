package fossilsarcheology.client.model;


import fossilsarcheology.server.entity.prehistoric.EntityIcthyosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIcthyosaurus extends ModelPrehistoric {
	public AdvancedModelRenderer leftHindFlipper;
	public AdvancedModelRenderer rightHindFlipper;
	public AdvancedModelRenderer leftFrontFlipper;
	public AdvancedModelRenderer rightFrontFlipper;
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer upperTailFin;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer lowerTailFin;
	public AdvancedModelRenderer upperTailFin_1;
	public AdvancedModelRenderer upperTailFin2;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	private ModelAnimator animator;

	public ModelIcthyosaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.rightFrontFlipper = new AdvancedModelRenderer(this, 62, 21);
		this.rightFrontFlipper.setRotationPoint(-2.5F, 15.5F, -5.4F);
		this.rightFrontFlipper.addBox(-1.0F, 0.0F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotateAngle(rightFrontFlipper, 0.5235987755982988F, -0.0F, 0.5235987755982988F);
		this.tail2 = new AdvancedModelRenderer(this, 59, 0);
		this.tail2.setRotationPoint(0.0F, 0.8F, 5.5F);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 8, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 27, 40);
		this.upperJaw.setRotationPoint(0.0F, 0.77F, -5.8F);
		this.upperJaw.addBox(-2.0F, -1.0F, -7.6F, 4, 3, 8, 0.0F);
		this.upperBody = new AdvancedModelRenderer(this, 77, 44);
		this.upperBody.setRotationPoint(0.0F, 9.4F, -6.0F);
		this.upperBody.addBox(-4.0F, 0.0F, 0.0F, 8, 10, 10, 0.0F);
		this.rightHindFlipper = new AdvancedModelRenderer(this, 50, 24);
		this.rightHindFlipper.setRotationPoint(-1.8F, 15.3F, 10.8F);
		this.rightHindFlipper.addBox(-1.0F, 0.0F, -2.0F, 1, 5, 3, 0.0F);
		this.setRotateAngle(rightHindFlipper, 0.5235987755982988F, -0.0F, 0.5235987755982988F);
		this.upperTailFin2 = new AdvancedModelRenderer(this, 21, 11);
		this.upperTailFin2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.upperTailFin2.addBox(-0.5F, 0.0F, -2.0F, 1, 8, 5, 0.0F);
		this.head = new AdvancedModelRenderer(this, 1, 40);
		this.head.setRotationPoint(0.0F, -0.5F, -4.0F);
		this.head.addBox(-3.0F, -2.0F, -6.0F, 6, 7, 6, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 79, 25);
		this.lowerBody.setRotationPoint(0.0F, 1.0F, 9.4F);
		this.lowerBody.addBox(-3.0F, -0.5F, 0.0F, 6, 9, 9, 0.0F);
		this.leftHindFlipper = new AdvancedModelRenderer(this, 50, 24);
		this.leftHindFlipper.mirror = true;
		this.leftHindFlipper.setRotationPoint(1.8F, 15.3F, 10.8F);
		this.leftHindFlipper.addBox(0.0F, 0.0F, -2.0F, 1, 5, 3, 0.0F);
		this.setRotateAngle(leftHindFlipper, 0.5235987755982988F, -0.0F, -0.5235987755982988F);
		this.lowerTailFin = new AdvancedModelRenderer(this, 36, 11);
		this.lowerTailFin.setRotationPoint(0.0F, 2.9F, 7.5F);
		this.lowerTailFin.addBox(-0.5F, 0.0F, -2.0F, 1, 9, 5, 0.0F);
		this.setRotateAngle(lowerTailFin, 0.40980330836826856F, -0.0F, 0.0F);
		this.upperTailFin = new AdvancedModelRenderer(this, 8, 11);
		this.upperTailFin.setRotationPoint(0.0F, 1.4F, 7.53F);
		this.upperTailFin.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 5, 0.0F);
		this.setRotateAngle(upperTailFin, 0.8196066167365371F, -0.0F, -3.141592653589793F);
		this.leftFrontFlipper = new AdvancedModelRenderer(this, 62, 21);
		this.leftFrontFlipper.mirror = true;
		this.leftFrontFlipper.setRotationPoint(2.5F, 15.5F, -5.4F);
		this.leftFrontFlipper.addBox(0.0F, 0.0F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotateAngle(leftFrontFlipper, 0.5235987755982988F, -0.0F, -0.5235987755982988F);
		this.upperTailFin_1 = new AdvancedModelRenderer(this, 21, 11);
		this.upperTailFin_1.setRotationPoint(0.0F, 2.4F, 7.63F);
		this.upperTailFin_1.addBox(-0.5F, 0.0F, -2.0F, 1, 8, 5, 0.0F);
		this.setRotateAngle(upperTailFin_1, 0.6373942428283291F, -0.0F, 3.141592653589793F);
		this.neck = new AdvancedModelRenderer(this, 52, 50);
		this.neck.setRotationPoint(0.0F, 3.5F, 1.0F);
		this.neck.addBox(-3.5F, -3.0F, -4.0F, 7, 8, 3, 0.0F);
		this.lowerJaw = new AdvancedModelRenderer(this, 27, 53);
		this.lowerJaw.setRotationPoint(0.0F, 3.77F, -5.9F);
		this.lowerJaw.addBox(-2.0F, -1.0F, -7.6F, 4, 1, 8, 0.0F);
		this.setRotateAngle(lowerJaw, -0.091106186954104F, -0.0F, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 85, 10);
		this.tail1.setRotationPoint(0.0F, 0.2F, 8.2F);
		this.tail1.addBox(-2.0F, 0.0F, 0.0F, 4, 7, 6, 0.0F);
		this.tail1.addChild(this.tail2);
		this.head.addChild(this.upperJaw);
		this.upperTailFin_1.addChild(this.upperTailFin2);
		this.neck.addChild(this.head);
		this.upperBody.addChild(this.lowerBody);
		this.tail2.addChild(this.lowerTailFin);
		this.upperBody.addChild(this.upperTailFin);
		this.tail2.addChild(this.upperTailFin_1);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.lowerJaw);
		this.lowerBody.addChild(this.tail1);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.rightFrontFlipper.render(f5);
		this.upperBody.render(f5);
		this.rightHindFlipper.render(f5);
		this.leftHindFlipper.render(f5);
		this.leftFrontFlipper.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityIcthyosaurus prehistoric = (EntityIcthyosaurus) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, lowerJaw, 29, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.FISH_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, head, 0, 15F, 0);
		ModelUtils.rotate(animator, lowerJaw, 30, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, head, 0, -15F, 0);
		ModelUtils.rotate(animator, lowerJaw, 30, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, head, 0, 15F, 0);
		ModelUtils.rotate(animator, lowerJaw, 30, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail1, this.tail2, this.upperTailFin};
		AdvancedModelRenderer[] neckParts = {this.head};
		upperJaw.setScale(1.01F, 1.01F, 1.01F);
		upperTailFin2.setScale(1.01F, 1.01F, 1.01F);
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		neck.rotateAngleY += f3 / (180F / (float) Math.PI) * 0.5F;
		head.rotateAngleY += f3 / (180F / (float) Math.PI) * 0.5F;
		float speed2 = 0.6F;
		this.chainSwing(tailParts, speed2, 0.3F, -3, f, f1);
		this.swing(upperBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.swing(lowerBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.chainSwing(neckParts, speed2, -0.1F, 2, f, f1);
		this.chainWave(neckParts, speed2, -0.05F, 2, f, f1);
		this.chainWave(tailParts, speed2, -0.025F, 2, f, f1);
		this.flap(leftFrontFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		this.flap(rightFrontFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.chainSwing(tailParts, speed2, -0.4F, 2, f, f1);
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}
}
