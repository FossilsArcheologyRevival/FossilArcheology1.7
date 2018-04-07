package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGlowingPrehistoric extends RenderLiving {

	public ResourceLocation overlay;

	public RenderGlowingPrehistoric(ModelBase model, ResourceLocation overlay) {
		super(Minecraft.getMinecraft().getRenderManager(), model, 0.3F);
		this.overlay = overlay;
		this.addLayer(new LayerCompsognathusEyes(this));

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
		if (entity instanceof EntityPrehistoric) {
			EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
			GL11.glScalef(prehistoric.getAgeScale(), prehistoric.getAgeScale(), prehistoric.getAgeScale());
		}
	}

}
