package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityHenodus;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHenodus extends ModelPrehistoric {
	public AdvancedModelRenderer Body;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer leftUpperArm;
	public AdvancedModelRenderer rightUpperArm;
	public AdvancedModelRenderer LShell;
	public AdvancedModelRenderer RShell;
	public AdvancedModelRenderer leftthigh;
	public AdvancedModelRenderer rightthigh;
	public AdvancedModelRenderer Tail1;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer leftLowerArm;
	public AdvancedModelRenderer rightLowerArm;
	public AdvancedModelRenderer leftleg;
	public AdvancedModelRenderer rightleg;
	public AdvancedModelRenderer Tail1_1;
	private ModelAnimator animator;

	public ModelHenodus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.LShell = new AdvancedModelRenderer(this, 67, 0);
		this.LShell.setRotationPoint(0.0F, 0.0F, -2.1F);
		this.LShell.addBox(3.5F, -0.6F, -5.8F, 9, 2, 19, 0.0F);
		this.setRotateAngle(LShell, 0.0F, -0.0F, 0.13962634015954636F);
		this.Tail1 = new AdvancedModelRenderer(this, 0, 33);
		this.Tail1.setRotationPoint(0.0F, 1.7F, 8.7F);
		this.Tail1.addBox(-2.0F, -2.0F, 0.0F, 4, 3, 5, 0.0F);
		this.setRotateAngle(Tail1, -0.136659280431156F, -0.0F, 0.0F);
		this.rightleg = new AdvancedModelRenderer(this, 32, 21);
		this.rightleg.setRotationPoint(-1.0F, 2.9F, -0.9F);
		this.rightleg.addBox(-0.9F, 0.0F, -5.0F, 2, 3, 5, 0.0F);
		this.setRotateAngle(rightleg, 1.7453292519943295F, -0.0F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.4F, -4.43F);
		this.head.addBox(-2.0F, -2.4F, -3.6F, 4, 3, 3, 0.0F);
		this.setRotateAngle(head, -0.136659280431156F, -0.0017453292519943296F, 0.0F);
		this.rightUpperArm = new AdvancedModelRenderer(this, 35, 13);
		this.rightUpperArm.setRotationPoint(-3.0F, 2.7F, -6.5F);
		this.rightUpperArm.addBox(-2.0F, -1.0F, -1.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightUpperArm, -0.5235987755982988F, -0.0F, 1.2217304763960306F);
		this.neck = new AdvancedModelRenderer(this, 0, 9);
		this.neck.setRotationPoint(0.0F, 1.1F, -3.5F);
		this.neck.addBox(-1.5F, -2.0F, -5.3F, 3, 3, 6, 0.0F);
		this.setRotateAngle(neck, 0.18203784098300857F, -0.0F, 0.0F);
		this.Body = new AdvancedModelRenderer(this, 67, 25);
		this.Body.setRotationPoint(0.0F, 19.5F, -2.1F);
		this.Body.addBox(-5.0F, -1.1F, -6.2F, 10, 4, 16, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 28, 0);
		this.upperJaw.setRotationPoint(0.0F, 0.2F, -4.93F);
		this.upperJaw.addBox(-2.5F, -2.4F, -1.4F, 5, 2, 3, 0.0F);
		this.setRotateAngle(upperJaw, 0.091106186954104F, -0.0F, 0.0F);
		this.leftleg = new AdvancedModelRenderer(this, 32, 21);
		this.leftleg.mirror = true;
		this.leftleg.setRotationPoint(1.0F, 2.9F, -0.9F);
		this.leftleg.addBox(-1.1F, 0.0F, -5.0F, 2, 3, 5, 0.0F);
		this.setRotateAngle(leftleg, 1.7453292519943295F, -0.0F, 0.0F);
		this.leftthigh = new AdvancedModelRenderer(this, 35, 13);
		this.leftthigh.mirror = true;
		this.leftthigh.setRotationPoint(3.0F, 2.7F, 9.5F);
		this.leftthigh.addBox(0.0F, -1.0F, -1.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(leftthigh, 0.17453292519943295F, -0.0F, -1.2217304763960306F);
		this.leftUpperArm = new AdvancedModelRenderer(this, 35, 13);
		this.leftUpperArm.mirror = true;
		this.leftUpperArm.setRotationPoint(3.0F, 2.7F, -6.5F);
		this.leftUpperArm.addBox(0.0F, -1.0F, -1.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(leftUpperArm, -0.5235987755982988F, -0.0F, -1.2217304763960306F);
		this.Tail1_1 = new AdvancedModelRenderer(this, 0, 24);
		this.Tail1_1.setRotationPoint(0.0F, 0.2F, 4.0F);
		this.Tail1_1.addBox(-1.5F, -2.0F, 0.0F, 3, 2, 7, 0.0F);
		this.RShell = new AdvancedModelRenderer(this, 67, 0);
		this.RShell.mirror = true;
		this.RShell.setRotationPoint(0.0F, 0.0F, -2.1F);
		this.RShell.addBox(-12.5F, -0.6F, -5.8F, 9, 2, 19, 0.0F);
		this.setRotateAngle(RShell, 0.0F, -0.0F, -0.13962634015954636F);
		this.rightthigh = new AdvancedModelRenderer(this, 35, 13);
		this.rightthigh.setRotationPoint(-3.0F, 2.7F, 9.5F);
		this.rightthigh.addBox(-2.0F, -1.0F, -1.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightthigh, 0.17453292519943295F, -0.0F, 1.2199851471440364F);
		this.leftLowerArm = new AdvancedModelRenderer(this, 32, 21);
		this.leftLowerArm.mirror = true;
		this.leftLowerArm.setRotationPoint(0.99F, 1.8F, 0.5F);
		this.leftLowerArm.addBox(-1.0F, -1.5F, -5.0F, 2, 3, 5, 0.0F);
		this.setRotateAngle(leftLowerArm, 1.7453292519943295F, -0.0F, 0.0F);
		this.rightLowerArm = new AdvancedModelRenderer(this, 32, 21);
		this.rightLowerArm.setRotationPoint(-0.99F, 1.8F, 0.5F);
		this.rightLowerArm.addBox(-1.0F, -1.5F, -5.0F, 2, 3, 5, 0.0F);
		this.setRotateAngle(rightLowerArm, 1.7453292519943295F, 0.0F, 0.0F);
		this.lowerJaw = new AdvancedModelRenderer(this, 49, 0);
		this.lowerJaw.setRotationPoint(0.0F, 0.3F, -2.93F);
		this.lowerJaw.addBox(-2.0F, -0.5F, -3.0F, 4, 1, 3, 0.0F);
		this.setRotateAngle(lowerJaw, -0.06928957130417489F, 0.0F, 0.0F);
		this.Body.addChild(this.LShell);
		this.Body.addChild(this.Tail1);
		this.rightthigh.addChild(this.rightleg);
		this.neck.addChild(this.head);
		this.Body.addChild(this.rightUpperArm);
		this.Body.addChild(this.neck);
		this.head.addChild(this.upperJaw);
		this.leftthigh.addChild(this.leftleg);
		this.Body.addChild(this.leftthigh);
		this.Body.addChild(this.leftUpperArm);
		this.Tail1.addChild(this.Tail1_1);
		this.Body.addChild(this.RShell);
		this.Body.addChild(this.rightthigh);
		this.leftUpperArm.addChild(this.leftLowerArm);
		this.rightUpperArm.addChild(this.rightLowerArm);
		this.head.addChild(this.lowerJaw);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.Body.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityHenodus prehistoric = (EntityHenodus) entity;
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
		ModelUtils.rotate(animator, neck, -4, 0, 0);
		ModelUtils.rotate(animator, head, 4, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 29, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);

	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.Tail1, this.Tail1_1};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(head, f3, f4, 1F);
		float speed2 = 0.4F;
		this.chainSwing(tailParts, speed2, 0.3F, -3, f, f1);
		this.swing(Body, speed2, 0.1F, true, 0, 0, f, f1);
		this.chainSwing(neckParts, speed2, -0.1F, 2, f, f1);
		this.chainWave(neckParts, speed2, -0.05F, 2, f, f1);
		this.chainWave(tailParts, speed2, -0.025F, 2, f, f1);
		this.walk(leftUpperArm, speed2, 0.6F, true, 0, 0.5F, f, f1);
		this.walk(rightUpperArm, speed2, 0.6F, true, 0, 0.5F, f, f1);
		this.walk(leftthigh, speed2, 0.6F, false, 0, 0.5F, f, f1);
		this.walk(rightthigh, speed2, 0.6F, false, 0, 0.5F, f, f1);
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
		float sleepProgress = ((EntityPrehistoric) entity).sleepProgress;
		this.sitAnimationRotation(head, sleepProgress, 0, 25, 0);
	}
}
