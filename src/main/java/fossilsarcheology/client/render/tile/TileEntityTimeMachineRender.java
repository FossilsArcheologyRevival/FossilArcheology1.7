package fossilsarcheology.client.render.tile;

import fossilsarcheology.client.model.ModelTimeMachineClock;
import fossilsarcheology.server.block.entity.TileEntityTimeMachine;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class TileEntityTimeMachineRender extends TileEntitySpecialRenderer<TileEntityTimeMachine> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("fossil:textures/entities/time_machine_clock.png");
	private static final ModelTimeMachineClock MODEL = new ModelTimeMachineClock();

	public final float RndRound = ((float) Math.PI * 2F);
	private final int TickReset = 600;
	private int UpdateTick = 0;
	private float Hour = 0.0F;
	private float Minute = 0.0F;

	public void render(TileEntityTimeMachine var1, double var2, double var4, double var6, float var8, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) var2 + 0.5F, (float) var4 + 0.75F, (float) var6 + 0.5F);
		float var9 = (float) var1.clockCounter + var8;
		GL11.glTranslatef(0.0F, 0.5F + MathHelper.sin(var9 * 0.1F) * 0.01F, 0.0F);
		float var10;

		for (var10 = var1.currectFacingAngle - var1.sendingCurrentFacing; var10 >= (float) Math.PI; var10 -= ((float) Math.PI * 2F)) {
		}

		while (var10 < -(float) Math.PI) {
			var10 += ((float) Math.PI * 2F);
		}

		float var11 = var1.sendingCurrentFacing + var10 * var8;
		GL11.glRotatef(-var11 * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		this.bindTexture(TEXTURE);
		float var12 = 0.0F;
		float var13 = 0.0F;
		var12 = (var12 - (float) MathHelper.fastFloor((double) var12)) * 1.6F - 0.3F;
		var13 = (var13 - (float) MathHelper.fastFloor((double) var13)) * 1.6F - 0.3F;

		if (var12 < 0.0F) {
			var12 = 0.0F;
		}

		if (var13 < 0.0F) {
			var13 = 0.0F;
		}

		if (var12 > 1.0F) {
			var12 = 1.0F;
		}

		if (var13 > 1.0F) {
			var13 = 1.0F;
		}

		float var14 = 0.0F;

		this.showProgress(MODEL, var1);

		if (var1.playerClosing) {
			MODEL.EdgeRotate(var9, 0.5F, var13, var14, 0.0F, 0.0625F);
		} else {
			MODEL.EdgePullBack();
		}
		if (var1.isClockInPlace()) {
			MODEL.render(null, var9, 0.5F, var13, var14, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	private void showProgress(ModelTimeMachineClock var1, TileEntityTimeMachine var2) {
		float var10002 = (float) var2.getChargeLevel();
		var2.getClass();
		var1.UpdateTime(0.0F, var10002 / 1000.0F * ((float) Math.PI * 2F));
	}

}
