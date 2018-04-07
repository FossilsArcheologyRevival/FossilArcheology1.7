package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPrehistoric extends RenderLiving {

	public RenderPrehistoric(ModelBase model) {
		super(Minecraft.getMinecraft().getRenderManager(), model, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (entity instanceof EntityPrehistoric) {
			EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
			return new ResourceLocation(prehistoric.getTexture());
		} else {
			return null;
		}
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float f) {
		EntityPrehistoric dino = (EntityPrehistoric) entity;
		GL11.glScalef(dino.getAgeScale(), dino.getAgeScale(), dino.getAgeScale());
		GL11.glScalef(dino.getGender() == 1 ? dino.getMaleSize() : 1, dino.getGender() == 1 ? dino.getMaleSize() : 1, dino.getGender() == 1 ? dino.getMaleSize() : 1);
		this.shadowSize = dino.width * 0.45F;
	}

	public RenderManager getRenderManager() {
		return this.renderManager;
	}
}
