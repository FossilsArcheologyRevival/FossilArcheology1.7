package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.EntityDinosaurEgg;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDinoEgg extends AdvancedModelBase {
	public AdvancedModelRenderer Egg1;
	public AdvancedModelRenderer Egg2;
	public AdvancedModelRenderer Egg3;
	public AdvancedModelRenderer Egg4;

	public ModelDinoEgg() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Egg1 = new AdvancedModelRenderer(this, 0, 12);
		this.Egg1.setRotationPoint(0.0F, 19.6F, 0.0F);
		this.Egg1.addBox(-3.0F, -2.8F, -3.0F, 6, 6, 6, 0.0F);
		this.Egg4 = new AdvancedModelRenderer(this, 28, 16);
		this.Egg4.setRotationPoint(0.0F, -0.9F, 0.0F);
		this.Egg4.addBox(-2.0F, -4.8F, -2.0F, 4, 4, 4, 0.0F);
		this.Egg2 = new AdvancedModelRenderer(this, 22, 2);
		this.Egg2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Egg2.addBox(-2.5F, -0.6F, -2.5F, 5, 5, 5, 0.0F);
		this.Egg3 = new AdvancedModelRenderer(this, 0, 0);
		this.Egg3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Egg3.addBox(-2.5F, -4.6F, -2.5F, 5, 5, 5, 0.0F);
		this.Egg3.addChild(this.Egg4);
		this.Egg1.addChild(this.Egg2);
		this.Egg1.addChild(this.Egg3);
		this.updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Egg1.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		this.resetToDefaultPose();
		if (((EntityDinosaurEgg) entity).getBirthTick() > ((EntityDinosaurEgg) entity).totalHatchTime * 0.9F) {
			this.swing(Egg1, 0.3F, 0.5F, false, 0.25F, 0, entity.ticksExisted, 1);
			this.flap(Egg1, 0.3F, 0.5F, true, 0.25F, 0, entity.ticksExisted, 1);
			this.bob(Egg1, 0.3F, 0.9F, true, entity.ticksExisted, 1);
		}
	}

}
