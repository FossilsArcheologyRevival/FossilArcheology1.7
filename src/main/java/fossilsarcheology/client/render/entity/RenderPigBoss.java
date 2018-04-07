package fossilsarcheology.client.render.entity;

import fossilsarcheology.client.model.ModelPigBoss;
import fossilsarcheology.client.render.entity.layer.LayerAnuGlow;
import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPigBoss extends RenderLiving {

	public RenderPigBoss(RenderManager manager) {
		super(manager, new ModelPigBoss(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerAnuGlow(this));

	}

	protected ResourceLocation func_110919_a(EntityAnu par1Entity) {
		return new ResourceLocation("fossil:textures/model/PigBoss.png");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.func_110919_a((EntityAnu) par1Entity);
	}

	protected void preRenderScale(EntityAnu mob, float par2) {
		if (mob.getAttackMode() == 1) {
			GL11.glRotatef(35, 1, 0, 0);
		}
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
		this.preRenderScale((EntityAnu) par1EntityLivingBase, par2);
	}

}
