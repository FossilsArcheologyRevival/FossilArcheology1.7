package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnuTotem extends ModelBase {
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

	public ModelAnuTotem() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.RightTusk = new ModelRenderer(this, 0, 0);
		this.RightTusk.setRotationPoint(-2.5F, -3.0F, -1.5F);
		this.RightTusk.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.field_78112_f = new ModelRenderer(this, 16, 0);
		this.field_78112_f.setRotationPoint(-4.0F, 5.0F, 0.0F);
		this.field_78112_f.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78112_f, 0.0F, 0.0F, 0.14116187756067558F);
		this.Hornleft = new ModelRenderer(this, 0, 17);
		this.Hornleft.setRotationPoint(3.0F, -8.0F, 3.5F);
		this.Hornleft.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.field_78124_i = new ModelRenderer(this, 47, 0);
		this.field_78124_i.setRotationPoint(-2.0F, 14.0F, 0.0F);
		this.field_78124_i.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78124_i, -0.0F, 0.0F, -3.1003275537854505E-17F);
		this.field_78113_g = new ModelRenderer(this, 0, 0);
		this.field_78113_g.setRotationPoint(4.0F, 5.0F, 0.0F);
		this.field_78113_g.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.setRotateAngle(field_78113_g, -1.605702911834783F, -0.045553093477052F, 0.0F);
		this.field_78123_h = new ModelRenderer(this, 47, 0);
		this.field_78123_h.setRotationPoint(2.0F, 14.0F, 0.0F);
		this.field_78123_h.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.field_78115_e = new ModelRenderer(this, 40, 18);
		this.field_78115_e.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.field_78115_e.addBox(-4.0F, -4.0F, -2.0F, 8, 10, 4, 0.0F);
		this.Mouth = new ModelRenderer(this, 26, 17);
		this.Mouth.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.Mouth.addBox(-3.0F, -3.0F, -2.0F, 6, 3, 2, 0.0F);
		this.LeftTusk = new ModelRenderer(this, 0, 0);
		this.LeftTusk.setRotationPoint(2.5F, -3.0F, -1.5F);
		this.LeftTusk.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.HornLeft = new ModelRenderer(this, 0, 17);
		this.HornLeft.setRotationPoint(-3.0F, -8.0F, 3.5F);
		this.HornLeft.addBox(-1.0F, -6.0F, -0.5F, 2, 6, 1, 0.0F);
		this.Head = new ModelRenderer(this, 0, 16);
		this.Head.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(Head, 0.07214433182969043F, 0.0F, 0.0F);
		this.field_78114_d = new ModelRenderer(this, 24, 2);
		this.field_78114_d.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.field_78114_d.addBox(-2.0F, -3.0F, -8.0F, 4, 3, 12, 0.0F);
		this.setRotateAngle(field_78114_d, 1.1626228326695439E-17F, 0.0F, 0.0F);
		this.Mouth.addChild(this.RightTusk);
		this.Head.addChild(this.Hornleft);
		this.Head.addChild(this.Mouth);
		this.Mouth.addChild(this.LeftTusk);
		this.Head.addChild(this.HornLeft);
		this.Head.addChild(this.field_78114_d);
	}

	public void renderBlock(float f5) {
		this.field_78112_f.render(f5);
		this.field_78124_i.render(f5);
		this.field_78113_g.render(f5);
		this.field_78123_h.render(f5);
		this.field_78115_e.render(f5);
		this.Head.render(f5);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.field_78112_f.render(f5);
		this.field_78124_i.render(f5);
		this.field_78113_g.render(f5);
		this.field_78123_h.render(f5);
		this.field_78115_e.render(f5);
		this.Head.render(f5);
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
