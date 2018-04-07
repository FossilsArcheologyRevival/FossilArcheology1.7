package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.utility.EntityToyTetheredLog;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelToyTetheredLog extends AdvancedModelBase {
	public AdvancedModelRenderer rope0;
	public AdvancedModelRenderer rope1;
	public AdvancedModelRenderer rope2;
	public AdvancedModelRenderer log;
	private ModelAnimator animator;

	public ModelToyTetheredLog() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.rope1 = new AdvancedModelRenderer(this, 0, 0);
		this.rope1.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.rope1.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.rope2 = new AdvancedModelRenderer(this, 0, 0);
		this.rope2.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.rope2.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.log = new AdvancedModelRenderer(this, 0, 0);
		this.log.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.log.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F);
		this.rope0 = new AdvancedModelRenderer(this, 0, 0);
		this.rope0.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.rope0.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.rope0.addChild(this.rope1);
		this.rope1.addChild(this.rope2);
		this.rope2.addChild(this.log);
		this.updateDefaultPose();
		this.animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.rope0.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animator.update(entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(EntityToyTetheredLog.KNOCKBACK_ANIMATION);
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, rope0, -15, 0, 0);
		ModelUtils.rotate(animator, rope1, -15, 0, 0);
		ModelUtils.rotate(animator, rope2, -15, 0, 0);
		ModelUtils.rotate(animator, log, -15, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(5);
		animator.startKeyframe(5);
		ModelUtils.rotate(animator, rope0, 15, 0, 0);
		ModelUtils.rotate(animator, rope1, 15, 0, 0);
		ModelUtils.rotate(animator, rope2, 15, 0, 0);
		ModelUtils.rotate(animator, log, 15, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(5);
	}
}
