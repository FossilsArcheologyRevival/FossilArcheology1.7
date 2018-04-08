package fossilsarcheology.server.world.anu;


import fossilsarcheology.server.world.FAWorldRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderTreasure extends WorldProvider {

	@Override
	public void init() {
		this.biomeProvider = new BiomeProviderSingle(FAWorldRegistry.TREASURE_BIOME);
		this.doesWaterVaporize = true;
		this.nether = true;

	}

	@Override
	public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
		return 0.0F;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		int i = 10518688;
		float f2 = (float) Math.cos(p_76562_1_ * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		float f3 = (float) (i >> 16 & 255) / 255.0F;
		float f4 = (float) (i >> 8 & 255) / 255.0F;
		float f5 = (float) (i & 255) / 255.0F;
		f3 *= f2 * 0.0F + 0.15F;
		f4 *= f2 * 0.0F + 0.15F;
		f5 *= f2 * 0.0F + 0.15F;
		return new Vec3d((double) f3, (double) f4, (double) f5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}

	/**
	 * Returns array with sunrise/sunset colors
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
		return null;
	}

	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderTreasure(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 8.0F;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int y) {
		return this.world.getBlockState(this.world.getHeight(new BlockPos(x, 0, y))).getMaterial().blocksMovement();
	}

	@Override
	public int getAverageGroundLevel() {
		return 50;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
		return false;
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
		return 0;
	}

	@Override
	public DimensionType getDimensionType() {
		return FAWorldRegistry.TREASURE_ROOM;
	}
}
