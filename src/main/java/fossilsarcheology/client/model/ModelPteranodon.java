package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoricFlying;
import fossilsarcheology.server.entity.prehistoric.EntityPterosaur;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelPteranodon extends ModelPrehistoric {
	public AdvancedModelRenderer LegLeft;
	public AdvancedModelRenderer WingL1;
	public AdvancedModelRenderer LegRight;
	public AdvancedModelRenderer WingR1;
	public AdvancedModelRenderer Body;
	public AdvancedModelRenderer WingL1Child;
	public AdvancedModelRenderer WingL1Child_1;
	public AdvancedModelRenderer WingR1Child;
	public AdvancedModelRenderer WingR1Child_1;
	public AdvancedModelRenderer FibreL;
	public AdvancedModelRenderer Tail;
	public AdvancedModelRenderer Neck1;
	public AdvancedModelRenderer Fibre2;
	public AdvancedModelRenderer FibreR;
	public AdvancedModelRenderer Neck2;
	public AdvancedModelRenderer Fibre1;
	public AdvancedModelRenderer Head;
	public AdvancedModelRenderer Beak2;
	public AdvancedModelRenderer Jaw;
	public AdvancedModelRenderer CrestBase;
	public AdvancedModelRenderer Beak1;
	public AdvancedModelRenderer Crest;
	public AdvancedModelRenderer HeadPivot;
	private ModelAnimator animator;

	public ModelPteranodon() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.WingL1 = new AdvancedModelRenderer(this, 32, 23);
		this.WingL1.setRotationPoint(2.2F, 16.6F, 3.7F);
		this.WingL1.addBox(-0.5F, -0.5F, -3.0F, 10, 1, 6, 0.0F);
		this.setRotateAngle(WingL1, -0.0F, 0.33161255787892263F, 1.0471975511965976F);
		this.FibreL = new AdvancedModelRenderer(this, 34, 34);
		this.FibreL.setRotationPoint(1.0F, -0.1F, 0.0F);
		this.FibreL.addBox(0.0F, -1.0F, 1.0F, 0, 1, 2, 0.0F);
		this.setRotateAngle(FibreL, 0.37175513067479216F, -0.0F, 0.0F);
		this.Neck1 = new AdvancedModelRenderer(this, 0, 41);
		this.Neck1.setRotationPoint(0.0F, 0.1F, 1.0F);
		this.Neck1.addBox(-1.5F, -1.0F, -4.0F, 3, 3, 4, 0.0F);
		this.setRotateAngle(Neck1, -0.11152653920243764F, 0.0F, 0.0F);
		this.WingR1 = new AdvancedModelRenderer(this, 0, 23);
		this.WingR1.setRotationPoint(-2.200000047683716F, 16.600000381469727F, 3.700000047683716F);
		this.WingR1.addBox(-9.5F, -0.5F, -3.0F, 10, 1, 6, 0.0F);
		this.setRotateAngle(WingR1, 0.0F, -0.33161255717277527F, -1.0471975803375244F);
		this.CrestBase = new AdvancedModelRenderer(this, 31, 52);
		this.CrestBase.setRotationPoint(0.0F, -1.9F, -2.5F);
		this.CrestBase.addBox(-1.0F, -1.2F, -4.5F, 2, 3, 5, 0.0F);
		this.setRotateAngle(CrestBase, 2.777342438698576F, -0.0F, -3.141592653589793F);
		this.Beak2 = new AdvancedModelRenderer(this, 18, 54);
		this.Beak2.setRotationPoint(0.0F, -2.3F, -5.8F);
		this.Beak2.addBox(-0.99F, 0.0F, -7.0F, 2, 2, 8, 0.0F);
		this.setRotateAngle(Beak2, 0.05462880558742251F, -0.0F, 0.0F);
		this.Head = new AdvancedModelRenderer(this, 0, 55);
		this.Head.addBox(-2.0F, -3.0F, -5.0F, 4, 4, 5, 0.0F);
		this.HeadPivot = new AdvancedModelRenderer(this, 0, 0);
		this.HeadPivot.setRotationPoint(0.0F, -0.6F, -3.4F);
		this.setRotateAngle(HeadPivot, 1.1383037381507017F, 0.0F, 0.0F);
		this.LegRight = new AdvancedModelRenderer(this, 20, 45);
		this.LegRight.setRotationPoint(-1.5F, 21.0F, 6.5F);
		this.LegRight.addBox(-0.30000001192092896F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.WingL1Child = new AdvancedModelRenderer(this, 58, 32);
		this.WingL1Child.setRotationPoint(9.5F, 0.10000000149011612F, -2.5F);
		this.WingL1Child.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(WingL1Child, 0.33161255717277527F, -0.04555309191346169F, -1.0283479690551758F);
		this.WingR1Child = new AdvancedModelRenderer(this, 58, 32);
		this.WingR1Child.setRotationPoint(-9.5F, 0.10000000149011612F, -2.5F);
		this.WingR1Child.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(WingR1Child, 0.33161255717277527F, 0.04555309191346169F, 1.0283479690551758F);
		this.Fibre2 = new AdvancedModelRenderer(this, 34, 34);
		this.Fibre2.setRotationPoint(0.0F, -0.5F, 3.0F);
		this.Fibre2.addBox(0.0F, -1.0F, 1.0F, 0, 1, 2, 0.0F);
		this.setRotateAngle(Fibre2, 0.37175513067479216F, -0.0F, 0.0F);
		this.Body = new AdvancedModelRenderer(this, 0, 30);
		this.Body.setRotationPoint(0.0F, 16.100000381469727F, 0.5F);
		this.Body.addBox(-2.0F, -1.5F, 0.0F, 4, 4, 7, 0.0F);
		this.setRotateAngle(Body, -0.40893062949180603F, -0.0F, 0.0F);
		this.WingR1Child_1 = new AdvancedModelRenderer(this, 0, 9);
		this.WingR1Child_1.setRotationPoint(-8.8F, 0.0F, 2.6F);
		this.WingR1Child_1.addBox(-15.0F, -0.4F, -0.7F, 16, 1, 4, 0.0F);
		this.setRotateAngle(WingR1Child_1, 0.0F, 2.6179938779914944F, -0.13962634015954636F);
		this.Jaw = new AdvancedModelRenderer(this, 38, 55);
		this.Jaw.setRotationPoint(0.0F, 0.5F, -4.8F);
		this.Jaw.addBox(-1.0F, -1.0F, -7.6F, 2, 1, 8, 0.0F);
		this.setRotateAngle(Jaw, 0.0022689280275926286F, -0.0F, 0.0F);
		this.LegLeft = new AdvancedModelRenderer(this, 20, 45);
		this.LegLeft.setRotationPoint(1.5F, 21.0F, 6.5F);
		this.LegLeft.addBox(-0.7F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.WingL1Child_1 = new AdvancedModelRenderer(this, 31, 18);
		this.WingL1Child_1.setRotationPoint(8.8F, 0.0F, 2.6F);
		this.WingL1Child_1.addBox(-0.0F, -0.4F, -0.7F, 16, 1, 4, 0.0F);
		this.setRotateAngle(WingL1Child_1, 0.0F, -2.6179938779914944F, 0.13962634015954636F);
		this.FibreR = new AdvancedModelRenderer(this, 34, 34);
		this.FibreR.setRotationPoint(-1.0F, -0.1F, 0.0F);
		this.FibreR.addBox(0.0F, -1.0F, 1.0F, 0, 1, 2, 0.0F);
		this.setRotateAngle(FibreR, 0.37175513067479216F, -0.0F, 0.0F);
		this.Neck2 = new AdvancedModelRenderer(this, 0, 48);
		this.Neck2.setRotationPoint(0.0F, 0.1F, -3.3F);
		this.Neck2.addBox(-1.0F, -1.0F, -4.0F, 2, 3, 4, 0.0F);
		this.setRotateAngle(Neck2, -0.3764675196551769F, -0.0F, 0.0F);
		this.Crest = new AdvancedModelRenderer(this, 31, 44);
		this.Crest.setRotationPoint(0.0F, -0.6F, -2.5F);
		this.Crest.addBox(-1.0F, 0.2F, -5.8F, 2, 2, 5, 0.0F);
		this.setRotateAngle(Crest, -0.27314402793711257F, 0.0F, 0.0F);
		this.Fibre1 = new AdvancedModelRenderer(this, 34, 34);
		this.Fibre1.setRotationPoint(0.0F, -0.1F, -0.5F);
		this.Fibre1.addBox(0.0F, -1.9F, -0.3F, 0, 1, 2, 0.0F);
		this.setRotateAngle(Fibre1, 0.7285004297824331F, -0.0F, 0.0F);
		this.Tail = new AdvancedModelRenderer(this, 22, 32);
		this.Tail.setRotationPoint(0.0F, 0.4F, 6.6F);
		this.Tail.addBox(-2.0F, -1.27F, 0.0F, 4, 3, 2, 0.0F);
		this.setRotateAngle(Tail, -0.29740410453983374F, -0.0F, 0.0F);
		this.Beak1 = new AdvancedModelRenderer(this, 18, 54);
		this.Beak1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Beak1.addBox(-1.01F, 0.0F, -7.0F, 2, 2, 8, 0.0F);
		this.Body.addChild(this.FibreL);
		this.Body.addChild(this.Neck1);
		this.Head.addChild(this.CrestBase);
		this.Head.addChild(this.Beak2);
		this.Neck2.addChild(this.HeadPivot);
		this.WingL1.addChild(this.WingL1Child);
		this.WingR1.addChild(this.WingR1Child);
		this.Body.addChild(this.Fibre2);
		this.WingR1.addChild(this.WingR1Child_1);
		this.HeadPivot.addChild(this.Head);
		this.Head.addChild(this.Jaw);
		this.WingL1.addChild(this.WingL1Child_1);
		this.Body.addChild(this.FibreR);
		this.Neck1.addChild(this.Neck2);
		this.CrestBase.addChild(this.Crest);
		this.Neck1.addChild(this.Fibre1);
		this.Body.addChild(this.Tail);
		this.Beak2.addChild(this.Beak1);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.Body.render(f5);
		this.LegLeft.render(f5);
		this.LegRight.render(f5);
		this.WingL1.render(f5);
		this.WingR1.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPterosaur pterosaur = (EntityPterosaur) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(pterosaur.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, Jaw, 40, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(pterosaur.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, Neck1, -41, 0, 0);
		ModelUtils.rotate(animator, Head, 57, 0, 0);
		ModelUtils.rotate(animator, Jaw, 15, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, Neck1, 6, 0, 0);
		ModelUtils.rotate(animator, Head, -14, 0, 0);
		ModelUtils.rotate(animator, Jaw, -15, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] neckParts = {this.Neck1, this.Neck2, this.Head};
		AdvancedModelRenderer[] rightWingParts = {this.WingR1, this.WingR1Child_1};
		AdvancedModelRenderer[] leftWingParts = {this.WingL1, this.WingL1Child_1};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(Neck1, f3, f4, 0.3F);
		ModelUtils.faceTargetMod(Neck2, f3, f4, 0.3F);
		ModelUtils.faceTargetMod(Head, f3, f4, 0.3F);
		float speed = 0.1F;
		float speed2 = 0.6F;
		float speed3 = 0.2F;
		this.bob(Body, speed, -0.15F, false, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed, 0.05F, 3, entity.ticksExisted, 1);
		{
			float sitProgress = ((EntityPrehistoric) entity).sleepProgress;
			sitAnimationRotation(WingL1, sitProgress, (float) Math.toRadians(23.48D), (float) Math.toRadians(1D), -(float) Math.toRadians(8D));
			sitAnimationRotation(Body, sitProgress, -((float) Math.toRadians(23.43D)), -((float) Math.toRadians(7.83D)), 0);
			sitAnimationRotation(Neck1, sitProgress, -((float) Math.toRadians(55D)), (float) Math.toRadians(5.22D), (float) Math.toRadians(18.26D));
			sitAnimationRotation(WingR1, sitProgress, (float) Math.toRadians(13.04D), (float) Math.toRadians(19D), ((float) Math.toRadians(16D)));
			sitAnimationRotation(Head, sitProgress, (float) Math.toRadians(50D), -((float) Math.toRadians(7.83D)), -((float) Math.toRadians(23.48D)));
			sitAnimationRotation(Neck2, sitProgress, (float) Math.toRadians(50D), 0, 0);
			sitAnimationPos(LegRight, sitProgress, -0.5F, 0, -3.4F);
			sitAnimationPos(LegLeft, sitProgress, -0.4F, 0, -3.4F);
		}
		{
			float sitProgress = ((EntityPrehistoricFlying) entity).flyProgress;
			sitAnimationRotation(WingR1Child, sitProgress, (float) Math.toRadians(18.999999959540737D), (float) Math.toRadians(2.609999910412874D), (float) Math.toRadians(58.919998497711354D));
			sitAnimationRotation(WingR1Child_1, sitProgress, 0, -(float) Math.toRadians(180D), 0);
			sitAnimationRotation(WingR1, sitProgress, (float) Math.toRadians(7.0D), (float) Math.toRadians(19.0D), ((float) Math.toRadians(55.0D)));
			sitAnimationRotation(WingL1Child_1, sitProgress, 0, ((float) Math.toRadians(180D)), 0);
			sitAnimationRotation(Head, sitProgress, -(float) Math.toRadians(60D), 0, 0);
			sitAnimationRotation(Body, sitProgress, ((float) Math.toRadians(16D)), 0, 0);
			sitAnimationRotation(LegRight, sitProgress, (float) Math.toRadians(60.0D), 0, 0);
			sitAnimationRotation(Neck2, sitProgress, ((float) Math.toRadians(10.43D)), 0, 0);
			sitAnimationRotation(Tail, sitProgress, -((float) Math.toRadians(17.04D)), 0, 0);
			sitAnimationRotation(LegLeft, sitProgress, (float) Math.toRadians(60.0D), 0, 0);
			sitAnimationRotation(WingL1, sitProgress, (float) Math.toRadians(7.0D), -(float) Math.toRadians(19.0D), -(float) Math.toRadians(55.0D));
			sitAnimationRotation(Neck1, sitProgress, (float) Math.toRadians(18D), 0, 0);
			sitAnimationRotation(WingL1Child, sitProgress, (float) Math.toRadians(18.999999959540737D), -((float) Math.toRadians(2.609999910412874D)), -((float) Math.toRadians(58.919998497711354D)));
			sitAnimationPos(WingR1Child_1, sitProgress, 0, 0.4F, -4F);
			sitAnimationPos(WingL1Child_1, sitProgress, 0, 0.4F, -4F);
			sitAnimationPos(LegRight, sitProgress, 0, -1.5F, -1F);
			sitAnimationPos(LegLeft, sitProgress, 0, -1.5F, -1F);
			sitAnimationPos(Head, sitProgress, 0, 1F, -0.8F);
			if (sitProgress >= 10) {
				this.chainFlap(rightWingParts, speed3, 0.9F, 1, f, f1);
				this.chainFlap(leftWingParts, speed3, -0.9F, 1, f, f1);
				this.swing(WingR1, speed3, 0.7F, false, 0F, -0.1F, f, f1);
				this.swing(WingL1, speed3, 0.7F, true, 0F, -0.1F, f, f1);
			} else {
				this.walk(LegLeft, speed2, 0.9F, false, 0F, 0F, f, f1);
				this.walk(LegRight, speed2, 0.9F, true, 0F, 0F, f, f1);
				this.swing(WingR1, speed2, 0.7F, false, 0F, 0F, f, f1);
				this.swing(WingL1, speed2, 0.7F, false, 0F, 0F, f, f1);
			}
		}
	}
}
