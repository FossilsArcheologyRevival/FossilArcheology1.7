package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.utility.EntityToyBall;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToyBall extends AdvancedModelBase {
	public AdvancedModelRenderer ball;

	public ModelToyBall() {
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.ball = new AdvancedModelRenderer(this, 0, 0);
		this.ball.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.ball.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.ball.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		ball.rotateAngleX = (float) Math.toRadians(((EntityToyBall) entity).rollValue * 7);
	}
}
