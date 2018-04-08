package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.utility.EntityAnuDead;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDeadAnu extends RenderLiving {
	private static final ResourceLocation anu = new ResourceLocation("fossil:textures/model/PigBoss.png");

	public RenderDeadAnu(RenderManager render, ModelBase par1ModelBase, float par2) {
		super(render, par1ModelBase, par2);
	}

	public void renderDodo(EntityAnuDead par1Entity, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110919_a(EntityAnuDead par1Entity) {
		return anu;

	}

	@Override
	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		this.renderDodo((EntityAnuDead) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.func_110919_a((EntityAnuDead) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderDodo((EntityAnuDead) par1Entity, par2, par4, par6, par8, par9);
	}
}
