package fossilsarcheology.client.render.tile;


import fossilsarcheology.client.model.ModelFigurine;
import fossilsarcheology.server.block.entity.TileEntityFigurine;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class TileEntityFigurineRender extends TileEntitySpecialRenderer<TileEntityFigurine> {

	public static final ModelFigurine MODEL = new ModelFigurine();
	private static final ResourceLocation pristine_steve = new ResourceLocation("fossil:textures/blocks/figurines/figurine_steve_pristine.png");
	private static final ResourceLocation pristine_skeleton = new ResourceLocation("fossil:textures/blocks/figurines/figurine_skeleton_pristine.png");
	private static final ResourceLocation pristine_zombie = new ResourceLocation("fossil:textures/blocks/figurines/figurine_zombie_pristine.png");
	private static final ResourceLocation pristine_enderman = new ResourceLocation("fossil:textures/blocks/figurines/figurine_enderman_pristine.png");
	private static final ResourceLocation pristine_zombiepig = new ResourceLocation("fossil:textures/blocks/figurines/figurine_pigzombie_pristine.png");
	private static final ResourceLocation damaged_steve = new ResourceLocation("fossil:textures/blocks/figurines/figurine_steve_damaged.png");
	private static final ResourceLocation damaged_skeleton = new ResourceLocation("fossil:textures/blocks/figurines/figurine_skeleton_damaged.png");
	private static final ResourceLocation damaged_zombie = new ResourceLocation("fossil:textures/blocks/figurines/figurine_zombie_damaged.png");
	private static final ResourceLocation damaged_enderman = new ResourceLocation("fossil:textures/blocks/figurines/figurine_enderman_damaged.png");
	private static final ResourceLocation damaged_zombiepig = new ResourceLocation("fossil:textures/blocks/figurines/figurine_pigzombie_damaged.png");
	private static final ResourceLocation broken_steve = new ResourceLocation("fossil:textures/blocks/figurines/figurine_steve_broken.png");
	private static final ResourceLocation broken_skeleton = new ResourceLocation("fossil:textures/blocks/figurines/figurine_skeleton_broken.png");
	private static final ResourceLocation broken_zombie = new ResourceLocation("fossil:textures/blocks/figurines/figurine_zombie_broken.png");
	private static final ResourceLocation broken_enderman = new ResourceLocation("fossil:textures/blocks/figurines/figurine_enderman_broken.png");
	private static final ResourceLocation broken_zombiepig = new ResourceLocation("fossil:textures/blocks/figurines/figurine_pigzombie_broken.png");
	private static final ResourceLocation mysterious = new ResourceLocation("fossil:textures/blocks/figurines/figurine_mysterious.png");

	public TileEntityFigurineRender() {
	}

	@Override
	public void render(TileEntityFigurine te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 1.0F, 0.0F, 1.0F);
		rotateBlock(te.getWorld(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), te, te.getFigurineType());
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private void rotateBlock(World world, int x, int y, int z, TileEntityFigurine te, int par6) {
		if (world != null) {
			int dir = te.getFigurineRotation();
			GL11.glPushMatrix();
			GL11.glRotatef(dir * (90), 0F, 1F, 0F);
			GL11.glScalef(0.5F, 0.5F, 0.5F);

			switch (par6) {
				case 0:
				default:
					this.bindTexture(pristine_steve);
					this.MODEL.render(0.0625F, false);
					break;

				case 1:
					this.bindTexture(pristine_skeleton);
					this.MODEL.render(0.0625F, false);
					break;

				case 2:
					this.bindTexture(pristine_zombie);
					this.MODEL.render(0.0625F, false);
					break;

				case 3:
					this.bindTexture(pristine_enderman);
					this.MODEL.render(0.0625F, false);
					break;

				case 4:
					this.bindTexture(pristine_zombiepig);
					this.MODEL.render(0.0625F, false);
					break;

				case 5:
					this.bindTexture(damaged_steve);
					this.MODEL.render(0.0625F, false);
					break;

				case 6:
					this.bindTexture(damaged_skeleton);
					this.MODEL.render(0.0625F, false);
					break;

				case 7:
					this.bindTexture(damaged_zombie);
					this.MODEL.render(0.0625F, false);
					break;

				case 8:
					this.bindTexture(damaged_enderman);
					this.MODEL.render(0.0625F, false);
					break;

				case 9:
					this.bindTexture(damaged_zombiepig);
					this.MODEL.render(0.0625F, false);
					break;

				case 10:
					this.bindTexture(broken_steve);
					this.MODEL.render(0.0625F, true);
					break;

				case 11:
					this.bindTexture(broken_skeleton);
					this.MODEL.render(0.0625F, true);
					break;

				case 12:
					this.bindTexture(broken_zombie);
					this.MODEL.render(0.0625F, true);
					break;

				case 13:
					this.bindTexture(broken_enderman);
					this.MODEL.render(0.0625F, true);
					break;

				case 14:
					this.bindTexture(broken_zombiepig);
					this.MODEL.render(0.0625F, true);
					break;

				case 15:
					this.bindTexture(mysterious);
					this.MODEL.render(0.0625F, false);
					break;
			}

			// renders the model
			// this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
			// 0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glRotatef(0F, 0F, 1F, 0F);
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(pristine_steve);
			this.MODEL.render(0.0625F, false);
			GL11.glPopMatrix();
		}
	}

}
