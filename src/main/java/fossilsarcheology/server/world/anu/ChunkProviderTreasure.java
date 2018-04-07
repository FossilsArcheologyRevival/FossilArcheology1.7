package fossilsarcheology.server.world.anu;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkProviderTreasure extends ChunkProviderAnu {

	public ChunkProviderTreasure(World worldObjIn, boolean mapFeaturesEnabledIn, long seed) {
		super(worldObjIn, mapFeaturesEnabledIn, seed);
	}

	public Chunk provideChunk(int x, int z) {
		this.chunkX = x;
		this.chunkZ = z;
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();
		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}
		chunk.generateSkylightMap();
		return chunk;
	}

}