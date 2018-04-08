package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class LayerCompsognathusEyes<T extends EntityPrehistoric> implements LayerRenderer<T> {
	private static final ResourceLocation EYES = new ResourceLocation("fossil:textures/model/compsognathus_0/overlay.png");
	private final RenderGlowingPrehistoric prehistoric;

	public LayerCompsognathusEyes(RenderGlowingPrehistoric spiderRendererIn) {
		this.prehistoric = spiderRendererIn;
	}

	public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (!entitylivingbaseIn.isSleeping()) {
			GL11.glDisable(GL11.GL_LIGHTING);
			this.prehistoric.bindTexture(EYES);
			this.prehistoric.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GL11.glEnable(GL11.GL_LIGHTING);

		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}