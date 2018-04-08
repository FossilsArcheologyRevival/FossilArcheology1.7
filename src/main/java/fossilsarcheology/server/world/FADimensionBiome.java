package fossilsarcheology.server.world;


import fossilsarcheology.server.entity.monster.EntitySentryPigman;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.Biome;

public class FADimensionBiome extends Biome {
	public int genSelector;

	public FADimensionBiome(boolean anu, BiomeProperties props, Block block) {
		super(props);
		this.topBlock = block.getDefaultState();
		this.fillerBlock = block.getDefaultState();
		this.genSelector = genSelector;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();

		if (anu) {
			this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 200, 1, 8));
			this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySentryPigman.class, 200, 1, 4));
		}
	}
}
