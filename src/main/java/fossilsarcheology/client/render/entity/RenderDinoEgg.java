package fossilsarcheology.client.render.entity;

import fossilsarcheology.client.model.ModelDinoEgg;
import fossilsarcheology.server.entity.EntityDinosaurEgg;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDinoEgg extends RenderLiving {

	public RenderDinoEgg(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelDinoEgg(), 0.25F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float f) {
		GL11.glScalef(((EntityDinosaurEgg) entity).selfType.eggScale, ((EntityDinosaurEgg) entity).selfType.eggScale, ((EntityDinosaurEgg) entity).selfType.eggScale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(((EntityDinosaurEgg) entity).getTexture());
	}

}
