package fossilsarcheology.server.config;

import net.ilexiconn.llibrary.server.config.ConfigEntry;

public class FossilConfig {
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generatePalaeoraphe = false;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateHellShips = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateAcademy = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateShips = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateTemple = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateFossils = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generatePermafrost = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateVolcanicRock = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateAztecWeaponShops = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateMoai = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateTarSites = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateFossilSites = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public int[] oreGenerationDimensions = {0};

	@ConfigEntry(category = "entity spawning")
	@SuppressWarnings("deprecation")
	public boolean spawnCoelacanth = true;
	@ConfigEntry(category = "entity spawning")
	@SuppressWarnings("deprecation")
	public boolean spawnSturgeon = true;
	@ConfigEntry(category = "entity spawning")
	@SuppressWarnings("deprecation")
	public boolean spawnAlligatorGar = true;
	@ConfigEntry(category = "entity spawning")
	@SuppressWarnings("deprecation")
	public boolean spawnNautilus = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean healingDinos = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean starvingDinos = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean dinoBlockBreaking = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean skullOverlay = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean loginMessage = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean allowTableEnchantments = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean allowBookEnchantments = true;
	@ConfigEntry
	@SuppressWarnings("deprecation")
	public boolean allowBreeding = true;
	@ConfigEntry(category = "client")
	@SuppressWarnings("deprecation")
	public boolean customMainMenu = true;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredTRex = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredDeinonychus = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredGallimimus = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredCompsognathus = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean quilledTriceratops = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredVelociraptor = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean featheredTherizinosaurus = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean eggsLikeChickens = false;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "dinosaurs")
	public boolean sleepingParticles = true;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int biomeIDDarknessLair = 128;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int biomeIDTreasure = 127;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int dimensionIDDarknessLair = -23;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int dimensionIDTreasure = -34;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int enchantmentIDArcheology = 91;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int enchantmentIDPaleontology = 90;
	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "ids")
	public int villagerId = 303;
}
