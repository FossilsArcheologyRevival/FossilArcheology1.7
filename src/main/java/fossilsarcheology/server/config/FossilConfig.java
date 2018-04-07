package fossilsarcheology.server.config;

import net.ilexiconn.llibrary.server.config.ConfigEntry;

public class FossilConfig {
	@ConfigEntry(category = "generation")
	public boolean generatePalaeoraphe = false;
	@ConfigEntry(category = "generation")
	public boolean generateHellShips = true;
	@ConfigEntry(category = "generation")
	public boolean generateAcademy = true;
	@ConfigEntry(category = "generation")
	public boolean generateShips = true;
	@ConfigEntry(category = "generation")
	public boolean generateTemple = true;
	@ConfigEntry(category = "generation")
	public boolean generateFossils = true;
	@ConfigEntry(category = "generation")
	public boolean generatePermafrost = true;
	@ConfigEntry(category = "generation")
	public boolean generateVolcanicRock = true;
	@ConfigEntry(category = "generation")
	public boolean generateAztecWeaponShops = true;
	@ConfigEntry(category = "generation")
	public boolean generateMoai = true;
	@ConfigEntry(category = "generation")
	public boolean generateTarSites = true;
	@ConfigEntry(category = "generation")
	public boolean generateFossilSites = true;
	@ConfigEntry(category = "generation")
	public int[] oreGenerationDimensions = {0};

	@ConfigEntry(category = "entity spawning")
	public boolean spawnCoelacanth = true;
	@ConfigEntry(category = "entity spawning")
	public boolean spawnSturgeon = true;
	@ConfigEntry(category = "entity spawning")
	public boolean spawnAlligatorGar = true;
	@ConfigEntry(category = "entity spawning")
	public boolean spawnNautilus = true;
	@ConfigEntry
	public boolean healingDinos = true;
	@ConfigEntry
	public boolean starvingDinos = true;
	@ConfigEntry
	public boolean dinoBlockBreaking = true;
	@ConfigEntry
	public boolean skullOverlay = true;
	@ConfigEntry
	public boolean loginMessage = true;
	@ConfigEntry
	public boolean allowTableEnchantments = true;
	@ConfigEntry
	public boolean allowBookEnchantments = true;
	@ConfigEntry
	public boolean allowBreeding = true;
	@ConfigEntry(category = "client")
	public boolean customMainMenu = true;

	@ConfigEntry(category = "dinosaurs")
	public boolean featheredTRex = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredDeinonychus = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredGallimimus = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredCompsognathus = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean quilledTriceratops = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredVelociraptor = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredTherizinosaurus = true;
	@ConfigEntry(category = "dinosaurs")
	public boolean eggsLikeChickens = false;
	@ConfigEntry(category = "dinosaurs")
	public boolean sleepingParticles = true;
	@ConfigEntry(category = "ids")
	public int biomeIDDarknessLair = 128;
	@ConfigEntry(category = "ids")
	public int biomeIDTreasure = 127;
	@ConfigEntry(category = "ids")
	public int dimensionIDDarknessLair = -23;
	@ConfigEntry(category = "ids")
	public int dimensionIDTreasure = -34;
	@ConfigEntry(category = "ids")
	public int enchantmentIDArcheology = 91;
	@ConfigEntry(category = "ids")
	public int enchantmentIDPaleontology = 90;
	@ConfigEntry(category = "ids")
	public int villagerId = 303;
}
