package fossilsarcheology.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTimeMachineClock extends ModelBase {
	ModelRenderer H = (new ModelRenderer(this, 0, 5)).setTextureSize(64, 32);
	ModelRenderer M;
	ModelRenderer axie;
	ModelRenderer C;
	ModelRenderer C2;
	ModelRenderer C3;
	ModelRenderer C21;
	ModelRenderer C31;

	public ModelTimeMachineClock() {
		this.H.addBox(-0.5333334F, -3.0F, -0.5F, 1, 3, 1);
		this.H.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.H, 0.0F, 0.0F, 0.0F);
		this.H.mirror = true;
		this.M = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
		this.M.addBox(-0.5F, -4.0F, -0.51F, 1, 4, 1);
		this.M.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.M, 0.0F, 0.0F, 0.0F);
		this.M.mirror = true;
		this.axie = (new ModelRenderer(this, 22, 0)).setTextureSize(64, 32);
		this.axie.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
		this.axie.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.axie, 0.01745329F, 0.0F, 0.0F);
		this.axie.mirror = true;
		this.C = (new ModelRenderer(this, 4, 0)).setTextureSize(64, 32);
		this.C.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 1);
		this.C.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.C, 0.0F, 0.0F, 0.0F);
		this.C.mirror = true;
		this.C2 = (new ModelRenderer(this, 0, 10)).setTextureSize(64, 32);
		this.C2.addBox(-4.5F, -4.5F, -0.5F, 9, 9, 2);
		this.C2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.C2, 0.0F, 0.0F, 0.0F);
		this.C2.mirror = true;
		this.C3 = (new ModelRenderer(this, 0, 21)).setTextureSize(64, 32);
		this.C3.addBox(-4.5F, -4.5F, -0.5F, 9, 9, 2);
		this.C3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.C3, 0.0F, 0.0F, ((float) Math.PI / 4F));
		this.C3.mirror = true;
		this.C21 = (new ModelRenderer(this, 22, 19)).setTextureSize(64, 32);
		this.C21.addBox(-5.0F, -5.0F, -1.0F, 10, 10, 3);
		this.C21.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.C21, 0.0F, 0.0F, 0.0F);
		this.C21.mirror = true;
		this.C31 = (new ModelRenderer(this, 22, 6)).setTextureSize(64, 32);
		this.C31.addBox(-5.0F, -5.0F, -1.0F, 10, 10, 3);
		this.C31.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotation(this.C31, 0.0F, 0.0F, ((float) Math.PI / 4F));
		this.C31.mirror = true;
	}

	public void renderItem(float var7, float worldTime) {
		this.axie.render(var7);
		this.C.render(var7);
		GL11.glPushMatrix();
		GL11.glRotatef(worldTime * -1F, 0, 0, 1);
		this.H.render(var7);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(worldTime * 1F, 0, 0, 1);
		this.M.render(var7);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(worldTime, 0, 0, 1);
		this.C21.render(var7);
		this.C31.render(var7);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(-1 * worldTime, 0, 0, 1);
		this.C2.render(var7);
		this.C3.render(var7);
		GL11.glPopMatrix();
	}

	@Override
	public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		super.render(var1, var2, var3, var4, var5, var6, var7);
		this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
		this.H.render(var7);
		this.M.render(var7);
		this.axie.render(var7);
		this.C.render(var7);
		this.C2.render(var7);
		this.C3.render(var7);
		this.C21.render(var7);
		this.C31.render(var7);
	}

	public void EdgeRotate(float var1, float var2, float var3, float var4, float var5, float var6) {
		this.C2.rotateAngleX = MathHelper.cos(var1 / 4.797769F) * 0.5235988F * var2 + 0.0F;
		this.C2.rotateAngleZ = MathHelper.cos(var1 / 9.595538F) * (float) Math.PI * var2 + 0.0F;
		this.C3.rotateAngleX = MathHelper.cos(var1 / 4.797769F) * 0.5235988F * var2 + 0.0F;
		this.C3.rotateAngleZ = MathHelper.cos(var1 / 9.595538F) * (float) Math.PI * var2 + ((float) Math.PI / 4F);
		this.C21.rotateAngleX = MathHelper.cos(var1 / 4.797769F) * -0.5235988F * var2 + 0.0F;
		this.C21.rotateAngleZ = MathHelper.cos(var1 / 9.595538F) * -(float) Math.PI * var2 + 0.0F;
		this.C31.rotateAngleX = MathHelper.cos(var1 / 4.797769F) * -0.5235988F * var2 + 0.0F;
		this.C31.rotateAngleZ = MathHelper.cos(var1 / 9.595538F) * -(float) Math.PI * var2 + ((float) Math.PI / 4F);
	}

	public void EdgePullBack() {
		this.C2.rotateAngleX = 0.0F;
		this.C2.rotateAngleY = 0.0F;
		this.C2.rotateAngleZ = 0.0F;
		this.C3.rotateAngleX = 0.0F;
		this.C3.rotateAngleY = 0.0F;
		this.C3.rotateAngleZ = ((float) Math.PI / 4F);
		this.C21.rotateAngleX = 0.0F;
		this.C21.rotateAngleY = 0.0F;
		this.C21.rotateAngleZ = 0.0F;
		this.C31.rotateAngleX = 0.0F;
		this.C31.rotateAngleY = 0.0F;
		this.C31.rotateAngleZ = ((float) Math.PI / 4F);
	}

	public void UpdateTime(float var1, float var2) {
		if ((double) var1 <= Math.PI) {
			this.H.rotateAngleZ = var1;
		} else {
			this.H.rotateAngleZ = (float) ((double) var1 - (Math.PI * 2D));
		}

		if ((double) var2 <= Math.PI) {
			this.M.rotateAngleZ = var2;
		} else {
			this.M.rotateAngleZ = (float) ((double) var2 - (Math.PI * 2D));
		}
	}

	private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
		var1.rotateAngleX = var2;
		var1.rotateAngleY = var3;
		var1.rotateAngleZ = var4;
	}
}
