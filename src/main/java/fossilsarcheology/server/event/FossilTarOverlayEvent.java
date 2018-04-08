package fossilsarcheology.server.event;


import com.mojang.realmsclient.gui.ChatFormatting;
import fossilsarcheology.Revival;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.util.ReleaseType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FossilTarOverlayEvent {
	@SubscribeEvent
	public void onBlockOverlay(RenderBlockOverlayEvent e) {
		if (e.getPlayer().world.getBlockState(e.getBlockPos()) == FABlockRegistry.TAR) {
			e.setCanceled(true);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("fossil:textures/blocks/tar.png"));
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder vertexbuffer = tessellator.getBuffer();
			float f = 1;
			GlStateManager.color(f, f, f, 0.5F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.pushMatrix();
			float f1 = 4.0F;
			float f2 = -1.0F;
			float f3 = 1.0F;
			float f4 = -1.0F;
			float f5 = 1.0F;
			float f6 = -0.5F;
			float f7 = -e.getPlayer().rotationYaw / 64.0F;
			float f8 = e.getPlayer().rotationPitch / 64.0F;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
			vertexbuffer.pos(-1.0D, -1.0D, -0.5D).tex((double) (4.0F + f7), (double) (4.0F + f8)).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, -0.5D).tex((double) (0.0F + f7), (double) (4.0F + f8)).endVertex();
			vertexbuffer.pos(1.0D, 1.0D, -0.5D).tex((double) (0.0F + f7), (double) (0.0F + f8)).endVertex();
			vertexbuffer.pos(-1.0D, 1.0D, -0.5D).tex((double) (4.0F + f7), (double) (0.0F + f8)).endVertex();
			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
		}
	}

	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if (Revival.RELEASE_TYPE == ReleaseType.DEVELOP && event.getEntity() == Minecraft.getMinecraft().player) {
			Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString(ChatFormatting.BOLD.toString() + ChatFormatting.RED.toString() + "You're running a development build of F/A: Revival"), false);
		}
	}
}
