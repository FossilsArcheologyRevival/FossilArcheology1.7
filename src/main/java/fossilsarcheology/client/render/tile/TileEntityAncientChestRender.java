package fossilsarcheology.client.render.tile;

import fossilsarcheology.server.block.AncientChestBlock;
import fossilsarcheology.server.block.entity.TileEntityAncientChest;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityAncientChestRender extends TileEntitySpecialRenderer<TileEntityAncientChest> {
	private static final ResourceLocation texture = new ResourceLocation("fossil:textures/model/ancient_chest.png");
	private static final ItemStack key = new ItemStack(FAItemRegistry.ANCIENT_KEY);
	private static final ModelChest model = new ModelChest();

	public TileEntityAncientChestRender() {
	}

	public void render(TileEntityAncientChest tileentity, double x, double y, double z, float f, int destroy, float alpha) {
		short short1 = 0;
		int i = 0;
		if (tileentity != null && tileentity.hasWorld()) {
			i = tileentity.getBlockType().getStateFromMeta(tileentity.getBlockMetadata()).getValue(AncientChestBlock.FACING).getHorizontalIndex();
			switch (i) {
				case 0:
					short1 = 180;
					break;
				case 1:
					short1 = -90;
					break;
				case 2:
					short1 = 0;
					break;
				case 3:
					short1 = 90;
			}
		}
		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glPushMatrix();
		GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = 0;
		if (tileentity != null && tileentity.hasWorld()) {
			if (tileentity.chestLidCounter > 90) {
				f1 = tileentity.chestLidCounter2 + tileentity.chestLidCounter;
			} else {
				f1 = tileentity.chestLidCounter;
			}
		}
		this.model.chestLid.rotateAngleX = -(float) Math.toRadians(f1);
		this.model.renderAll();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		if (tileentity != null && tileentity.hasWorld() && tileentity.chestState == 1) {
			this.renderItem(x, y, z, i);
		}
	}

	public void renderItem(double x, double y, double z, int i) {

		if (i == 2) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x + 0.5F, (float) y + 0.45F, (float) z - 0.3F);
			GL11.glRotatef((float) 180, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90, 0F, 1F, 0F);
			GL11.glRotatef(45, 0F, 0F, -1F);
			GL11.glScalef(scale, scale, scale);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();
		}
		if (i == 3) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x + 0.5F, (float) y + 0.45F, (float) z + 1.3F);
			GL11.glRotatef(90, 0F, 1F, 0F);
			GL11.glRotatef(45, 0F, 0F, -1F);
			GL11.glScalef(scale, scale, scale);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();
		}
		if (i == 4) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x - 0.3F, (float) y + 0.45F, (float) z + 0.5F);
			GL11.glRotatef((float) -90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90, 0F, 1F, 0F);
			GL11.glRotatef(45, 0F, 0F, -1F);
			GL11.glScalef(scale, scale, scale);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();

		}
		if (i == 5) {
			GL11.glPushMatrix();
			float scale = 1F;
			GL11.glTranslatef((float) x + 1.3F, (float) y + 0.45F, (float) z + 0.5F);
			GL11.glRotatef((float) 90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90, 0F, 1F, 0F);
			GL11.glRotatef(45, 0F, 0F, -1F);
			GL11.glScalef(scale, scale, scale);
			Minecraft.getMinecraft().getRenderItem().renderItem(key, ItemCameraTransforms.TransformType.FIXED);
			GL11.glPopMatrix();
		}

	}

}
