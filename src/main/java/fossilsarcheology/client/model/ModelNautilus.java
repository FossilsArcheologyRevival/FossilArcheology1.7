package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityNautilus;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNautilus extends AdvancedModelBase {
	public AdvancedModelRenderer shell;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer flap;
	public AdvancedModelRenderer tenticle_0;
	public AdvancedModelRenderer tenticle_1;
	public AdvancedModelRenderer tenticle_2;
	public AdvancedModelRenderer tenticle_3;
	public AdvancedModelRenderer tenticle_4;
	public AdvancedModelRenderer tenticle_5;

	public ModelNautilus() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new AdvancedModelRenderer(this, 40, 0);
		this.head.setRotationPoint(0.0F, 0.7F, 0.0F);
		this.head.addBox(-1.5F, -2.5F, -4.5F, 3, 5, 5, 0.0F);
		this.setRotateAngle(head, 0.004537856055185257F, 0.0F, 0.0F);
		this.tenticle_5 = new AdvancedModelRenderer(this, 30, 9);
		this.tenticle_5.setRotationPoint(1.3F, 1.7F, -4.5F);
		this.tenticle_5.addBox(0.0F, -0.5F, -4.0F, 0, 1, 4, 0.0F);
		this.setRotateAngle(tenticle_5, 0.0F, -0.3490658503988659F, 0.7853981633974483F);
		this.tenticle_0 = new AdvancedModelRenderer(this, 30, 9);
		this.tenticle_0.setRotationPoint(-1.3F, -0.5F, -4.5F);
		this.tenticle_0.addBox(0.0F, -0.5F, -4.0F, 0, 1, 4, 0.0F);
		this.setRotateAngle(tenticle_0, 0.0F, 0.3490658503988659F, 0.7853981633974483F);
		this.tenticle_4 = new AdvancedModelRenderer(this, 30, 9);
		this.tenticle_4.setRotationPoint(1.3F, 0.6F, -4.5F);
		this.tenticle_4.addBox(0.0F, -0.5F, -4.0F, 0, 1, 4, 0.0F);
		this.setRotateAngle(tenticle_4, 0.0F, -0.4363323129985824F, 0.0F);
		this.tenticle_2 = new AdvancedModelRenderer(this, 30, 9);
		this.tenticle_2.setRotationPoint(-1.3F, 1.7F, -4.5F);
		this.tenticle_2.addBox(0.0F, -0.5F, -4.0F, 0, 1, 4, 0.0F);
		this.setRotateAngle(tenticle_2, 0.0F, 0.3490658503988659F, -0.7853981633974483F);
		this.flap = new AdvancedModelRenderer(this, 20, 2);
		this.flap.setRotationPoint(0.0F, -3.9F, 0.0F);
		this.flap.addBox(-2.0F, 0.0F, -6.0F, 4, 2, 6, 0.0F);
		this.setRotateAngle(flap, 0.27314402793711257F, 0.0F, 0.0F);
		this.tenticle_3 = new AdvancedModelRenderer(this, 30, 9);
		this.tenticle_3.setRotationPoint(1.3F, -0.5F, -4.5F);
		this.tenticle_3.addBox(0.0F, -0.5F, -4.0F, 0, 1, 4, 0.0F);
		this.setRotateAngle(tenticle_3, 0.0F, -0.3490658503988659F, -0.7853981633974483F);
		this.tenticle_1 = new AdvancedModelRenderer(this, 30, 9);
		this.tenticle_1.setRotationPoint(-1.3F, 0.6F, -4.5F);
		this.tenticle_1.addBox(0.0F, -0.5F, -4.0F, 0, 1, 4, 0.0F);
		this.setRotateAngle(tenticle_1, 0.0F, 0.4363323129985824F, 0.0F);
		this.shell = new AdvancedModelRenderer(this, 0, 0);
		this.shell.setRotationPoint(0.0F, 20.0F, -1.3F);
		this.shell.addBox(-2.5F, -6.0F, 0.0F, 5, 10, 10, 0.0F);
		this.setRotateAngle(shell, 0.07144541004295828F, 0.0F, -0.004841412281086836F);
		this.shell.addChild(this.head);
		this.head.addChild(this.tenticle_5);
		this.head.addChild(this.tenticle_0);
		this.head.addChild(this.tenticle_4);
		this.head.addChild(this.tenticle_2);
		this.shell.addChild(this.flap);
		this.head.addChild(this.tenticle_3);
		this.head.addChild(this.tenticle_1);
		this.updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.shell.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.resetToDefaultPose();
		float speed = 0.2F;
		shell.rotateAngleY = (float) Math.toRadians(180);

		if (!entity.isInWater()) {
			this.shell.rotateAngleZ = (float) Math.toRadians(90);
		} else {
			this.bob(shell, -speed, 0.5F, true, entity.ticksExisted, 1);
		}
		{
			float shellProgress = ((EntityNautilus) entity).shellProgress;
			sitAnimationRotation(flap, shellProgress, (float) Math.toRadians(75), 0, 0);
			sitAnimationRotation(tenticle_0, shellProgress, -(float) Math.toRadians(20), 0, 0);
			sitAnimationRotation(tenticle_1, shellProgress, -(float) Math.toRadians(25), 0, 0);
			sitAnimationRotation(tenticle_2, shellProgress, -(float) Math.toRadians(20), -(float) Math.toRadians(20), 0);
			sitAnimationRotation(tenticle_3, shellProgress, (float) Math.toRadians(20), 0, 0);
			sitAnimationRotation(tenticle_4, shellProgress, (float) Math.toRadians(25), 0, 0);
			sitAnimationRotation(tenticle_5, shellProgress, (float) Math.toRadians(20), (float) Math.toRadians(20), 0);

			sitAnimationOffset(tenticle_0, shellProgress, -0.1F, 0, -0.25F);
			sitAnimationOffset(tenticle_1, shellProgress, -0.1F, 0, -0.25F);
			sitAnimationOffset(tenticle_2, shellProgress, -0.1F, 0, -0.25F);
			sitAnimationOffset(tenticle_3, shellProgress, 0.1F, 0, -0.25F);
			sitAnimationOffset(tenticle_4, shellProgress, 0.1F, 0, -0.25F);
			sitAnimationOffset(tenticle_5, shellProgress, 0.1F, 0, -0.25F);
			sitAnimationOffset(head, shellProgress, 0, 0, -0.35F);
			if (shellProgress == 0) {
				tenticle_0.swing(speed, 0.4F, false, 0, 0, entity.ticksExisted, 1);
				tenticle_1.swing(speed, 0.4F, false, 0, 0, entity.ticksExisted, 1);
				tenticle_2.swing(speed, 0.4F, false, 0, 0, entity.ticksExisted, 1);
				tenticle_3.swing(speed, 0.4F, true, 0, 0, entity.ticksExisted, 1);
				tenticle_4.swing(speed, 0.4F, true, 0, 0, entity.ticksExisted, 1);
				tenticle_5.swing(speed, 0.4F, true, 0, 0, entity.ticksExisted, 1);
			}
		}
	}

	public void sitAnimationOffset(AdvancedModelRenderer modelRenderer, float progress, float x, float y, float z) {
		modelRenderer.offsetX -= progress * x / 20.0F;
		modelRenderer.offsetY -= progress * y / 20.0F;
		modelRenderer.offsetZ -= progress * z / 20.0F;
	}

	public void sitAnimationRotation(AdvancedModelRenderer modelRenderer, float sitProgress, float rotX, float rotY, float rotZ) {
		modelRenderer.rotateAngleX = sitProgress * rotX / 20.0F;
		modelRenderer.rotateAngleY += sitProgress * rotY / 20.0F;
		modelRenderer.rotateAngleZ += sitProgress * rotZ / 20.0F;
	}

	public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
