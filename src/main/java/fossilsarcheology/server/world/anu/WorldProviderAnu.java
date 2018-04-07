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

public class WorldProviderAnu extends WorldProvider {

	@Override
	public void init() {
		this.biomeProvider = new BiomeProviderSingle(FAWorldRegistry.ANU_BIOME);
		this.doesWaterVaporize = true;
		this.nether = true;

	}

	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderAnu(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
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
		return new Vec3d(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
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

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 8.0F;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map
	 * spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_) {
		return this.world.getBlockState(this.world.getHeight(new BlockPos(p_76566_1_, 0, p_76566_2_))).getMaterial().blocksMovement();
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
		return FAWorldRegistry.ANU_LAIR;
	}
}
