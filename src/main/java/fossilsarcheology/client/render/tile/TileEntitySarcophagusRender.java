package fossilsarcheology.client.render.tile;

import fossilsarcheology.client.model.ModelPigBoss;
import fossilsarcheology.client.model.ModelSarcophagus;
import fossilsarcheology.server.block.SarcophagusBlock;
import fossilsarcheology.server.block.entity.TileEntitySarcophagus;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntitySarcophagusRender extends TileEntitySpecialRenderer<TileEntitySarcophagus> {

	private static final ResourceLocation texture = new ResourceLocation("fossil:textures/model/sarcophagus.png");
	private static final ResourceLocation texture_anu = new ResourceLocation("fossil:textures/model/PigBoss.png");
	private static final ItemStack key = new ItemStack(FAItemRegistry.SCARAB_GEM);
	private static final ModelSarcophagus model = new ModelSarcophagus();
	private static final ModelPigBoss model_anu = new ModelPigBoss();

	public TileEntitySarcophagusRender() {
	}

	public void render(TileEntitySarcophagus chest, double x, double y, double z, float f, int destroy, float alpha) {
		short short1 = 0;
		int i = 0;
		if (chest != null && chest.hasWorld()) {
			i = chest.getBlockType().getStateFromMeta(chest.getBlockMetadata()).getValue(SarcophagusBlock.FACING).getHorizontalIndex();
			switch (i) {
				case 0:
					short1 = 0;
					break;
				case 1:
					short1 = -90;
					break;
				case 2:
					short1 = 180;
					break;
				case 3:
					short1 = 90;
			}
		}
		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, -0.5F, 0.5F);
		GL11.glPushMatrix();
		GL11.glRotatef((float) -short1, 0.0F, 1.0F, 0.0F);
		float f1 = 0;
		if (chest != null && chest.hasWorld()) {
			f1 = chest.chestLidCounter;
		}
		this.model.hinge.rotateAngleY = -(float) Math.toRadians(f1);
		this.model.renderBlock(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		if (chest != null && chest.hasWorld() && chest.chestState == 1) {
			this.renderItem(x, y, z, i);
		}
		if (chest != null && chest.hasWorld() && chest.chestState != 3) {
			this.bindTexture(texture_anu);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GL11.glScalef(0.9F, -0.9F, -0.9F);
			GL11.glTranslatef(0.5F, -0.5F, 0.5F);
			if (i == 2) {
				short1 = 180;
			}

			if (i == 3) {
				short1 = 0;
			}

			if (i == 4) {
				short1 = 90;
			}

			if (i == 5) {
				short1 = -90;
			}
			GL11.glPushMatrix();
			GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0F, -0.0625F, 0.2F);

			this.model_anu.renderBlock(0.0625F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

	public void renderItem(double x, double y, double z, int i) {
		if (i == 2) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.2F, (float) z);
			GL11.glScalef(scale, scale, scale * 2F);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();
		}
		if (i == 3) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.2F, (float) z + 1F);
			GL11.glRotatef(180, 0F, 1F, 0F);
			GL11.glScalef(scale, scale, scale * 2F);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();
		}
		if (i == 4) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x, (float) y + 1.2F, (float) z + 0.5F);
			GL11.glRotatef((float) -90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180, 0F, 1F, 0F);
			GL11.glScalef(scale, scale, scale * 2F);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();

		}
		if (i == 5) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x + 1F, (float) y + 1.2F, (float) z + 0.5F);
			GL11.glRotatef((float) 90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-180, 0F, 1F, 0F);
			GL11.glScalef(scale, scale, scale * 2F);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();
		}

	}
}