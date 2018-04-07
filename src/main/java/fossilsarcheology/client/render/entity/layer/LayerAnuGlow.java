package fossilsarcheology.client.render.entity.layer;

import fossilsarcheology.client.render.entity.RenderPigBoss;
import fossilsarcheology.server.entity.monster.EntityAnu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class LayerAnuGlow implements LayerRenderer<EntityAnu> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("fossil:textures/model/PigBoss_overlay.png");
	private final RenderPigBoss anurender;

	public LayerAnuGlow(RenderPigBoss anu) {
		this.anurender = anu;
	}

	public void doRenderLayer(EntityAnu entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		this.anurender.bindTexture(TEXTURE);
		this.anurender.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.popMatrix();
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}