package fossilsarcheology.client.render.entity;

import fossilsarcheology.client.model.ModelAnubite;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAnubite extends RenderBiped {
	private static final ResourceLocation skeletonTextures = new ResourceLocation("fossil:textures/model/Anubite_ancient.png");

	public RenderAnubite(RenderManager render) {
		super(render, new ModelAnubite(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity) {
		return skeletonTextures;
	}

}