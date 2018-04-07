package fossilsarcheology.client.model;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFailuresaurus extends AdvancedModelBase {
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer floorbody;
	public AdvancedModelRenderer mainbody;
	public AdvancedModelRenderer head;

	public ModelFailuresaurus() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new AdvancedModelRenderer(this, 0, 16);
		this.head.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.head.addBox(-4.0F, -6.0F, -8.0F, 8, 6, 10, 0.0F);
		this.mainbody = new AdvancedModelRenderer(this, 2, 3);
		this.mainbody.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.mainbody.addBox(-7.0F, 0.0F, -5.0F, 14, 3, 10, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 18, 6);
		this.neck.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.neck.addBox(-5.0F, -4.0F, -2.0F, 10, 4, 5, 0.0F);
		this.floorbody = new AdvancedModelRenderer(this, 0, 1);
		this.floorbody.setRotationPoint(0, 23.0F, 0);
		this.floorbody.addBox(-5.0F, 0.0F, -7.0F, 10, 1, 14, 0.0F);
		this.neck.addChild(this.head);
		this.updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.mainbody.render(f5);
		this.neck.render(f5);
		this.floorbody.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.resetToDefaultPose();
		float bob = (float) (Math.sin(f * 0.025) * f1 * 0.025 - f1 * 1);
		float bob2 = (float) (Math.sin(f * 0.025) * f1 * 0.025 - f1 * 1);
		this.mainbody.setScale(1, 1, 1 - bob);
		this.floorbody.setScale(1, 1, 1 - bob2);
		head.rotateAngleY += f3 / (180F / (float) Math.PI);
		head.rotateAngleX += f4 / (180F / (float) Math.PI);
	}

}
