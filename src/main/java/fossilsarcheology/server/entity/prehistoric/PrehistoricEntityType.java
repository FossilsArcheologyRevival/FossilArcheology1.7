package fossilsarcheology.server.entity.prehistoric;

import fossilsarcheology.server.item.*;
import fossilsarcheology.server.tab.FATabRegistry;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public enum PrehistoricEntityType {
	PIG(EntityPig.class, MobType.VANILLA, TimePeriod.CURRENT, Diet.OMNIVORE, Parameter.NOTHING, 0, 0),
	COW(EntityCow.class, MobType.VANILLA, TimePeriod.CURRENT, Diet.HERBIVORE, Parameter.NOTHING, 0, 0),
	SHEEP(EntitySheep.class, MobType.VANILLA, TimePeriod.CURRENT, Diet.HERBIVORE, Parameter.NOTHING, 0, 0),
	HORSE(EntityHorse.class, MobType.VANILLA, TimePeriod.CURRENT, Diet.HERBIVORE, Parameter.NOTHING, 0, 0),
	CHICKEN(EntityChicken.class, MobType.CHICKEN, TimePeriod.CURRENT, Diet.HERBIVORE, Parameter.NOTHING, 0, 0),
	NAUTILUS(EntityNautilus.class, MobType.FISH, TimePeriod.MESOZOIC, Diet.NONE, Parameter.NOTHING, 0XC55F47, 0XF5F5F5),
	COELACANTH(EntityCoelacanth.class, MobType.FISH, TimePeriod.MESOZOIC, Diet.NONE, Parameter.NOTHING, 0X363941, 0X9BA1A9),
	ALLIGATOR_GAR(EntityAlligatorGar.class, MobType.FISH, TimePeriod.MESOZOIC, Diet.NONE, Parameter.NOTHING, 0X43462A, 0XAF4231),
	STURGEON(EntitySturgeon.class, MobType.FISH, TimePeriod.MESOZOIC, Diet.NONE, Parameter.NOTHING, 0X655D5B, 0XE6E3E3),
	TRICERATOPS(EntityTriceratops.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.HERBIVORE, 0X64352D, 0X251A17, 0.8F),
	VELOCIRAPTOR(EntityVelociraptor.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE_EGG, Parameter.TAME | Parameter.CARNIVORE, 0X4A0D04, 0XC9C9C9, 0.5F),
	TYRANNOSAURUS(EntityTyrannosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.CARNIVORE, 0X9D8A74, 0X4C3116, 0.9F),
	PTEROSAUR(EntityPterosaur.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCIVORE, Parameter.MODEL | Parameter.TAME | Parameter.CARNIVORE, 0XD6D6D6, 0X3B3B3B, 0.4F),
	PLESIOSAUR(EntityPlesiosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.CARNIVORE, 0XE4A86E, 0XE17920),
	MOSASAURUS(EntityMosasaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCCARNIVORE, Parameter.MODEL | Parameter.CARNIVORE, 0X888D90, 0X3A4C52),
	STEGOSAURUS(EntityStegosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.HERBIVORE, 0X9C8138, 0X651817, 0.7F),
	DILOPHOSAURUS(EntityDilophosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE, Parameter.TAME | Parameter.CARNIVORE, 0X4E5931, 0XF25314, 0.5F),
	BRACHIOSAURUS(EntityBrachiosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.HERBIVORE, 0X52523E, 0X222114),
	SPINOSAURUS(EntitySpinosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCCARNIVORE, Parameter.MODEL | Parameter.CARNIVORE, 0X84512A, 0X562F20, 0.8F),
	COMPSOGNATHUS(EntityCompsognathus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE, Parameter.MODEL | Parameter.TAME | Parameter.CARNIVORE, 0XCBC7C4, 0X3A312C, 0.2F),
	ANKYLOSAURUS(EntityAnkylosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.HERBIVORE, 0X8A5B49, 0X211B13, 0.7F),
	PACHYCEPHALOSAURUS(EntityPachycephalosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.HERBIVORE, 0XB6A989, 0X7D5E3A, 0.6F),
	DEINONYCHUS(EntityDeinonychus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE_EGG, Parameter.MODEL | Parameter.TAME | Parameter.CARNIVORE, 0X2B2424, 0XC8C8C8, 0.6F),
	GALLIMIMUS(EntityGallimimus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.OMNIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.HERB_CARN, 0X66412B, 0X5E2518, 0.5F),
	LIOPLEURODON(EntityLiopleurodon.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCCARNIVORE, Parameter.MODEL | Parameter.CARNIVORE, 0XBFC7C2, 0X1D211E),
	ALLOSAURUS(EntityAllosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.CARNIVORE, 0X907B6C, 0X5F422D, 0.8F),
	SARCOSUCHUS(EntitySarcosuchus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCCARNIVORE, Parameter.TAME | Parameter.CARNIVORE, 0X4B4929, 0X8D8C65, 0.7F),
	CERATOSAURUS(EntityCeratosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.CARNIVORE, Parameter.MODEL | Parameter.TAME | Parameter.RIDE | Parameter.CARNIVORE, 0XB4B4A7, 0X776446, 0.6F),
	DRYOSAURUS(EntityDryosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.HERBIVORE, 0X704C26, 0XC5C09A, 0.6F),
	THERIZINOSAURUS(EntityTherizinosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.HERBIVORE, 0X322212, 0XCA9C72, 0.8F),
	PARASAUROLOPHUS(EntityParasaurolophus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.MODEL | Parameter.TAME | Parameter.HERBIVORE, 0X7E8E30, 0X4C5438, 1F),
	HENODUS(EntityHenodus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.HERBIVORE, 0X613C27, 0X9C8060),
	ICTHYOSAURUS(EntityIcthyosaurus.class, MobType.DINOSAUR, TimePeriod.MESOZOIC, Diet.PISCIVORE, Parameter.TAME | Parameter.CARNIVORE, 0X2A2632, 0XCEC296),
	CONFUCIUSORNIS(EntityConfuciusornis.class, MobType.BIRD, TimePeriod.MESOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.HERBIVORE, 0XDAE5E9, 0X8B8B8D),
	DODO(EntityDodo.class, MobType.BIRD, TimePeriod.CENOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.HERBIVORE, 0X655751, 0XBEA47B),
	GASTORNIS(EntityGastornis.class, MobType.BIRD, TimePeriod.CENOZOIC, Diet.HERBIVORE, Parameter.TAME, 0X346C5E, 0XC8C8C8),
	KELENKEN(EntityKelenken.class, MobType.BIRD, TimePeriod.CENOZOIC, Diet.CARNIVORE, Parameter.TAME, 0X392F24, 0XF2EBD5),
	PHORUSRHACOS(EntityPhorusrhacos.class, MobType.BIRD, TimePeriod.CENOZOIC, Diet.CARNIVORE, Parameter.TAME, 0X5F4E3E, 0XD4D4D4),
	TITANIS(EntityTitanis.class, MobType.BIRD, TimePeriod.CENOZOIC, Diet.CARNIVORE, Parameter.TAME, 0X484848, 0XEFEFEF),
	MAMMOTH(EntityMammoth.class, MobType.MAMMAL, TimePeriod.CENOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.RIDE | Parameter.HERBIVORE, 0X3D2E19, 0X24170B),
	SMILODON(EntitySmilodon.class, MobType.MAMMAL, TimePeriod.CENOZOIC, Diet.CARNIVORE, Parameter.TAME | Parameter.CARNIVORE, 0XB88C64, 0XECDFCE),
	QUAGGA(EntityQuagga.class, MobType.MAMMAL, TimePeriod.CENOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.RIDE | Parameter.HERBIVORE, 0X763C24, 0XD3B9AB),
	ELASMOTHERIUM(EntityElasmotherium.class, MobType.MAMMAL, TimePeriod.CENOZOIC, Diet.HERBIVORE, Parameter.TAME | Parameter.RIDE | Parameter.HERBIVORE, 0X6B321B, 0X666666);
	private final Class<? extends Entity> entity;
	public MobType mobType;
	public Diet diet;
	public TimePeriod timePeriod;
	public int maximimAge = 999;
	public int adultAge = 6;
	public int teenAge = 3;
	public double minHealth = 20;
	public double maxHealth = 20;
	public double minStrength = 2;
	public double maxStrength = 2;
	public double minSpeed = 0.25D;
	public double maxSpeed = 0.3D;
	public int breedTicks = 3000;
	public int ageTicks = 12000;
	public int maxHunger = 100;
	public float hungerLevel = 0.8f;
	public float experience = 1.0f;
	public float experienceIncrement = 0.2f;
	public int parameters = 0;
	public Item orderItem;
	public Item fishItem;
	public Item foodItem;
	public Item cookedFoodItem;
	public Item dnaItem;
	public Item eggItem;
	public Item embryoItem;
	public Item birdEggItem;
	public Item bestBirdEggItem;
	public int growTime = 10000;
	public int primaryEggColor;
	public int secondaryEggColor;
	public float eggScale;
	public String friendlyName;
	public String resourceName;
	private float sizeBaby = 1;
	private float sizeTeen = 1;
	private float sizeAdult = 1;

	PrehistoricEntityType(Class<? extends Entity> entity, MobType mobType, TimePeriod period, Diet diet, int parameters, int primaryEggColor, int secondaryEggColor) {
		this.entity = entity;
		this.mobType = mobType;
		this.timePeriod = period;
		this.diet = diet;
		this.parameters = parameters;
		this.primaryEggColor = primaryEggColor;
		this.secondaryEggColor = secondaryEggColor;
		this.eggScale = 1;
		this.resourceName = this.name().toLowerCase(Locale.ENGLISH);
		this.friendlyName = this.name().toUpperCase(Locale.ENGLISH).substring(0, 1) + this.resourceName.substring(1);
	}

	PrehistoricEntityType(Class<? extends Entity> entity, MobType mobType, TimePeriod period, Diet diet, int parameters, int primaryEggColor, int secondaryEggColor, float eggScale) {
		this.entity = entity;
		this.mobType = mobType;
		this.timePeriod = period;
		this.diet = diet;
		this.parameters = parameters;
		this.primaryEggColor = primaryEggColor;
		this.secondaryEggColor = secondaryEggColor;
		this.eggScale = eggScale;
		this.resourceName = this.name().toLowerCase(Locale.ENGLISH);
		this.friendlyName = this.name().toUpperCase(Locale.ENGLISH).substring(0, 1) + this.resourceName.substring(1);
	}

	public static void register(RegistryEvent.Register<Item> event) {
		for (PrehistoricEntityType type : PrehistoricEntityType.values()) {
			MobType mobType = type.mobType;
			String resourceName = type.resourceName;
			type.dnaItem = new DNAItem(type);
			FAItemRegistry.registerItem(event, type.dnaItem, ((PrehistoricEntityItem) type.dnaItem).resourceName);
			if (mobType == MobType.FISH) {
				type.eggItem = new FishItem(type, true);
				type.fishItem = new FishItem(type, false);
				FAItemRegistry.registerItem(event, type.eggItem, ((PrehistoricEntityItem) type.eggItem).resourceName);
				FAItemRegistry.registerItem(event, type.fishItem, ((PrehistoricEntityItem) type.fishItem).resourceName);
			} else if (mobType == MobType.DINOSAUR) {
				type.eggItem = new DinoEggItem(type);
				FAItemRegistry.registerItem(event, type.eggItem, ((PrehistoricEntityItem) type.eggItem).resourceName);
			}
			if (mobType == MobType.MAMMAL || mobType == MobType.VANILLA) {
				type.embryoItem = new MammalEmbryoItem(type);
				FAItemRegistry.registerItem(event, type.embryoItem, ((PrehistoricEntityItem) type.embryoItem).resourceName);
			}
			if (mobType == MobType.BIRD || mobType == MobType.CHICKEN) {
				if (mobType == MobType.BIRD) {
					type.birdEggItem = new BirdEggItem(type, false);
					FAItemRegistry.registerItem(event, type.birdEggItem, ((PrehistoricEntityItem) type.birdEggItem).resourceName);
				}
				type.bestBirdEggItem = new BirdEggItem(type, true);
				FAItemRegistry.registerItem(event, type.bestBirdEggItem, ((PrehistoricEntityItem) type.bestBirdEggItem).resourceName);
			}
			if (type.timePeriod != TimePeriod.CURRENT) {
				if (type.mobType != MobType.FISH) {
					type.foodItem = new ItemDinoMeat(3, 0.3F, true, "meat", type).setUnlocalizedName("raw" + type.friendlyName).setCreativeTab(FATabRegistry.ITEMS);
					FAItemRegistry.registerItem(event, type.foodItem, resourceName + "_meat");
				}
				if (type != NAUTILUS) {
					type.cookedFoodItem = new ItemDinoMeat(8, 0.8F, true, "cooked", type).setUnlocalizedName("cooked" + type.friendlyName).setCreativeTab(FATabRegistry.ITEMS);
					FAItemRegistry.registerItem(event, type.cookedFoodItem, resourceName + "_cooked");
				}
			}
		}
	}

	public static boolean isDNA(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.dnaItem == item) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDinoEgg(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.DINOSAUR) {
				if (entity.eggItem == item) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isFoodItem(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.foodItem == item) {
				return true;
			}
		}
		return false;
	}

	public static Item getDNA(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.bestBirdEggItem == item || entity.birdEggItem == item || entity.embryoItem == item || entity.foodItem == item || entity.eggItem == item) {
				return entity.dnaItem;
			}
		}
		return null;
	}

	public static boolean isEmbryo(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.MAMMAL) {
				if (entity.embryoItem == item) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isBirdEgg(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.BIRD) {
				if (entity.birdEggItem == item) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isBestBirdEgg(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.BIRD || entity.mobType == MobType.CHICKEN) {
				if (entity.bestBirdEggItem == item) {
					return true;
				}
			}
		}
		return false;
	}

	public static Item getFoodItem(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.bestBirdEggItem == item || entity.birdEggItem == item || entity.embryoItem == item || entity.dnaItem == item || entity.eggItem == item) {
				return entity.foodItem;
			}
		}
		return null;
	}

	public static Item getEgg(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.DINOSAUR) {
				if (entity.foodItem == item || entity.dnaItem == item) {
					return entity.eggItem;
				}
			}
			if (entity.mobType == MobType.FISH) {
				if (entity.dnaItem == item) {
					return entity.eggItem;
				}
			}
		}
		return null;
	}

	public static Item getEmbryo(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.MAMMAL) {
				if (entity.dnaItem == item || entity.foodItem == item) {
					return entity.embryoItem;
				}
			}
		}
		return null;
	}

	public static Item getBirdEgg(Item item) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.BIRD) {
				if (entity.bestBirdEggItem == item || entity.dnaItem == item || entity.foodItem == item) {
					return entity.birdEggItem;
				}
			}
		}
		return null;
	}

	public static Item getBestBirdEgg(Item i0) {
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.mobType == MobType.BIRD || entity.mobType == MobType.CHICKEN) {
				if (entity.birdEggItem == i0 || entity.dnaItem == i0 || entity.foodItem == i0) {
					return entity.bestBirdEggItem;
				}
			}
		}
		return null;
	}

	public static int getIndex(Item item) {
		for (int index = 0; index < values().length; index++) {
			PrehistoricEntityType entity = values()[index];
			if (entity.bestBirdEggItem == item || entity.embryoItem == item || entity.birdEggItem == item || entity.dnaItem == item || entity.foodItem == item || entity.eggItem == item) {
				return index;
			}
		}
		return -1;
	}

	public static PrehistoricEntityType getRandomTimePeriod(Random random, TimePeriod period) {
		List<PrehistoricEntityType> mesozoic = new ArrayList<>();
		for (PrehistoricEntityType entity : PrehistoricEntityType.values()) {
			if (entity.timePeriod == period) {
				mesozoic.add(entity);
			}
		}
		int index = mesozoic.size() < 1 ? 0 : random.nextInt(mesozoic.size());
		return mesozoic.get(index);
	}

	public static Item getRandomDNA(Random random, TimePeriod period) {
		return PrehistoricEntityType.getRandomTimePeriod(random, period).dnaItem;
	}

	public static PrehistoricEntityType getRandomBioFossil(Random random, boolean tar) {
		List<PrehistoricEntityType> entities = new ArrayList<>();
		for (int i = 0; i < values().length; i++) {
			PrehistoricEntityType type = values()[i];
			if (type.mobType != MobType.VANILLA && type.mobType != MobType.CHICKEN && type.mobType != MobType.FISH) {
				if (tar) {
					if (type.timePeriod == TimePeriod.CENOZOIC/* && PrehistoricEntity.class.isAssignableFrom(type.entity)*/) { //TODO
						entities.add(type);
					}
				} else {
					if (type.timePeriod == TimePeriod.MESOZOIC || type.timePeriod == TimePeriod.PALEOZOIC) {
						entities.add(type);
					}
				}
			}
		}
		int index = random.nextInt(entities.size());
		return entities.get(index);
	}

	public static PrehistoricEntityType getRandom() {
		int index = ThreadLocalRandom.current().nextInt(PrehistoricEntityType.values().length);
		return PrehistoricEntityType.values()[index];
	}

	public static int getBones() {
		List<PrehistoricEntityType> bones = new ArrayList<>();
		for (PrehistoricEntityType entity : values()) {
			if (entity.timePeriod != TimePeriod.CURRENT || entity.mobType != MobType.FISH) {
				bones.add(entity);
			}
		}
		return bones.size();
	}

	public Entity invokeClass(World world) {
		Entity entity = null;
		if (Entity.class.isAssignableFrom(this.entity)) {
			try {
				entity = this.entity.getDeclaredConstructor(World.class).newInstance(world);
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}

	private void setOrderItem(Item order) {
		this.orderItem = order;
	}

	private void setAges(int teenAge, int adultAge, int maxAge) {
		if (teenAge > 0) {
			this.teenAge = teenAge;
		}
		if (adultAge > 0) {
			this.adultAge = adultAge;
		}
		if (maxAge > 0) {
			this.maximimAge = maxAge;
		}
	}

	private void setDinoSize(float sizeBaby, float sizeTeen, float sizeAdult) {
		this.sizeBaby = sizeBaby;
		this.sizeTeen = sizeTeen;
		this.sizeAdult = sizeAdult;
	}

	private void setProperties(double minHealth, double maxHealth, double minStrength, double maxStrength, double minSpeed, double maxSpeed, int maxHunger) {
		if (minHealth > 0) {
			this.minHealth = minHealth;
		}
		if (minStrength > 0) {
			this.minStrength = minStrength;
		}
		if (minSpeed > 0) {
			this.minSpeed = minSpeed;
		}
		if (maxHealth > 0) {
			this.maxHealth = maxHealth;
		}
		if (maxStrength > 0) {
			this.maxStrength = maxStrength;
		}
		if (maxSpeed > 0) {
			this.maxSpeed = maxSpeed;
		}
		if (maxHunger > 0) {
			this.maxHunger = maxHunger;
		}
	}

	private void setProperties(int breedTicks, int ageTicks, float hungerLevel) {
		if (breedTicks > 0) {
			this.breedTicks = breedTicks;
		}
		if (ageTicks > 0) {
			this.ageTicks = ageTicks;
		}
		if (hungerLevel > 0) {
			this.hungerLevel = hungerLevel;
		}
	}

	public boolean isAquatic() {
//        return this == PLESIOSAUR || this == MOSASAURUS || this == LIOPLEURODON; TODO
		return false;
	}

	private void setExperience(float experience, float experienceIncrement) {
		if (experience > 0) {
			this.experience = experience;
		}
		if (experienceIncrement > 0) {
			this.experienceIncrement = experienceIncrement;
		}
	}

	public Class<? extends Entity> getEntity() {
		return this.entity;
	}

	public boolean isModelable() {
		return (this.parameters & Parameter.MODEL) != 0;
	}

	public boolean isTameable() {
		return (this.parameters & Parameter.TAME) != 0;
	}

	public boolean isRideable() {
		return (this.parameters & Parameter.RIDE) != 0;
	}

	public boolean canCarryItems() {
		return (this.parameters & Parameter.CARRY) != 0;
	}

	public boolean useFeeder() {
		return this.diet != Diet.NONE && this.diet != Diet.INSECTIVORE && this.diet != Diet.PISCIVORE;
	}

	public boolean isHerbivore() {
		return (this.parameters & Parameter.HERBIVORE) != 0;
	}

	public boolean isCarnivore() {
		return (this.parameters & Parameter.CARNIVORE) != 0;
	}

	public String getFriendlyName() {
		return this.friendlyName;
	}

	interface Parameter {
		int NOTHING = 0;

		int NO_FEEDER = 0;// Bits 0+1: Dinos Can't use Feeder at all
		int HERBIVORE = 1;// Bit 0: Dino can use Herbivore Part of Feeder
		int CARNIVORE = 2;// Bit 1: Dino can use Carnivore Part of Feeder
		int HERB_CARN = 3;// Bits 0+1: Dinos can use Herbivore and Carnivore Part of
		// Feeder

		int MODEL = 1 << 2; // Bit 2: Dino is Modelable
		int TAME = 1 << 3; // Bit 3: Dino is Tameable
		int RIDE = 1 << 4; // Bit 4: Dino is Rideable
		int CARRY = 1 << 5; // Bit 5: Dino can Carry Items
	}
}