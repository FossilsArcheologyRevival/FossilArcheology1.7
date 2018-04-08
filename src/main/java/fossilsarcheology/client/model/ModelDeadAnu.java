package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.utility.EntityAnuDead;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelDeadAnu extends ModelBase {
	public ModelRenderer field_78112_f;
	public ModelRenderer field_78124_i;
	public ModelRenderer Head;
	public ModelRenderer field_78115_e;
	public ModelRenderer field_78113_g;
	public ModelRenderer field_78123_h;
	public ModelRenderer Mouth;
	public ModelRenderer field_78114_d;
	public ModelRenderer Hornleft;
	public ModelRenderer HornLeft;
	public ModelRenderer LeftTusk;
	public ModelRenderer RightTusk;

	public ModelDeadAnu() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.field_78115_e = new ModelRenderer(this, 40, 18);
		this.field_78115_e.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.field_78115_e.addBox(-4.0F, -4.0F, -2.0F, 8, 10, 4, 0.0F);
		this.Mouth = new ModelRenderer(this, 26, 17);
		this.Mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.Mouth.addBox(-3.0F, -3.0F, -2.0F, 6, 3, 2, 0.0F);
		this.LeftTusk = new ModelRenderer(this, 0, 0);
		this.LeftTusk.setRotationPoint(2.5F, -3.0F, -1.5F);
		this.LeftTusk.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.field_78112_f = new ModelRenderer(this, 16, 0);
		this.field_78112_f.setRotationPoint(-3.5F, 5.0F, 0.1F);
		this.field_78112_f.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78112_f, 0.0F, 0.0F, 0.22759093446006054F);
		this.RightTusk = new ModelRenderer(this, 0, 0);
		this.RightTusk.setRotationPoint(-2.5F, -3.0F, -1.5F);
		this.RightTusk.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.field_78113_g = new ModelRenderer(this, 0, 0);
		this.field_78113_g.setRotationPoint(3.5F, 5.0F, 0.1F);
		this.field_78113_g.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78113_g, 0.0F, 0.0F, -0.22759093446006054F);
		this.HornLeft = new ModelRenderer(this, 0, 17);
		this.HornLeft.setRotationPoint(-3.0F, -8.0F, 3.5F);
		this.HornLeft.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.field_78114_d = new ModelRenderer(this, 24, 2);
		this.field_78114_d.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.field_78114_d.addBox(-2.0F, -3.0F, -8.0F, 4, 3, 12, 0.0F);
		this.Hornleft = new ModelRenderer(this, 0, 17);
		this.Hornleft.setRotationPoint(3.0F, -8.0F, 3.5F);
		this.Hornleft.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.field_78123_h = new ModelRenderer(this, 47, 0);
		this.field_78123_h.setRotationPoint(2.0F, 13.7F, 0.1F);
		this.field_78123_h.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78123_h, 0.0F, 0.0F, -0.18203784098300857F);
		this.field_78124_i = new ModelRenderer(this, 47, 0);
		this.field_78124_i.setRotationPoint(-2.0F, 13.7F, 0.1F);
		this.field_78124_i.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78124_i, 0.0F, 0.0F, 0.18203784098300857F);
		this.Head = new ModelRenderer(this, 0, 16);
		this.Head.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(Head, -0.091106186954104F, 0.0F, 0.0F);
		this.Head.addChild(this.Mouth);
		this.Mouth.addChild(this.LeftTusk);
		this.Mouth.addChild(this.RightTusk);
		this.Head.addChild(this.HornLeft);
		this.Head.addChild(this.field_78114_d);
		this.Head.addChild(this.Hornleft);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		float alpha;
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 1.4F, 0.6F);
		GL11.glRotatef(-90, 1, 0, 0);
		if (entity instanceof EntityAnuDead) {
			if (((EntityAnuDead) entity).deathTicks > 40) {
				alpha = 1 - (((((EntityAnuDead) entity).deathTicks - 40) / 9) * 0.01F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
			}
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.field_78115_e.render(f5);
		this.field_78112_f.render(f5);
		this.field_78113_g.render(f5);
		this.field_78123_h.render(f5);
		this.field_78124_i.render(f5);
		this.Head.render(f5);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
