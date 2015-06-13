package mods.fossil.entity.mobs;

import java.util.ArrayList;

import mods.fossil.entity.mob.EntityAllosaurus;
import mods.fossil.entity.mob.EntityAnkylosaurus;
import mods.fossil.entity.mob.EntityBrachiosaurus;
import mods.fossil.entity.mob.EntityCompsognathus;
import mods.fossil.entity.mob.EntityDeinonychus;
import mods.fossil.entity.mob.EntityDilophosaurus;
import mods.fossil.entity.mob.EntityGallimimus;
import mods.fossil.entity.mob.EntityLiopleurodon;
import mods.fossil.entity.mob.EntityMosasaurus;
import mods.fossil.entity.mob.EntityNautilus;
import mods.fossil.entity.mob.EntityPachycephalosaurus;
import mods.fossil.entity.mob.EntityPlesiosaur;
import mods.fossil.entity.mob.EntityPterosaur;
import mods.fossil.entity.mob.EntitySarcosuchus;
import mods.fossil.entity.mob.EntitySpinosaurus;
import mods.fossil.entity.mob.EntityStegosaurus;
import mods.fossil.entity.mob.EntityTRex;
import mods.fossil.entity.mob.EntityTriceratops;
import mods.fossil.entity.mob.EntityVelociraptor;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;

public enum EnumEntityPrehistoric {
	// entityClass tameable rideable canCarry canFormHerds territorial targetFoodHabbit
	Allosaurus(EntityAllosaurus.class, true, true, true, false, true, 0),
	Ankylosaurus(EntityAnkylosaurus.class, true, true, true, false, true, 1),
	Brachiosaurus(EntityBrachiosaurus.class, true, true, true, true, false, 1),
	Compsognathus(EntityCompsognathus.class, true, false, true, true, true, 2),
	Deinonychus(EntityDeinonychus.class, true, false, true, false, false, 0),
	Dilophosaurus(EntityDilophosaurus.class, true, false, true, true, true, 0),
	Gallimimus(EntityGallimimus.class, true, true, true, true, false, 2),
	Liopleurodon(EntityLiopleurodon.class, true, false, true, false, true, 0),
	Mosasaurus(EntityMosasaurus.class, true, false, true, true, true, 0),
	Nautilus(EntityNautilus.class, false, false, false, false, false, 0),
	Pachycephalosaurus(EntityPachycephalosaurus.class, true, false, true, true, true, 1),
	Plesiosaur(EntityPlesiosaur.class, true, true, true, true, true, 0),
	Pterosaur(EntityPterosaur.class, true, false, true, true, true, 0),
	Sarcosuchus(EntitySarcosuchus.class, true, true, false, true, true, 0),
	Spinosaurus(EntitySpinosaurus.class, true, false, true, false, true, 0),
	Stegosaurus(EntityStegosaurus.class, true, false, true, true, false, 1),
	Triceratops(EntityTriceratops.class, true, true, true, true, true, 1),
	TyrannosaurusRex(EntityTRex.class, true, false, true, true, true, 0),
	Velociraptor(EntityVelociraptor.class, true, false, true, true, true, 0);
	
	public static int FOOD_HABBIT_CARNIVORE = 0;
	public static int FOOD_HABBIT_HERBIVORE = 1;
	public static int FOOD_HABBIT_OMNIVORE = 2;
	
	private ArrayList<EnumEdibleFoodstuff> foodItems = new ArrayList<EnumEdibleFoodstuff>();
	private ArrayList<EnumEdibleFoodBlocks> foodBlocks = new ArrayList<EnumEdibleFoodBlocks>();
	private ArrayList<EnumEdibleFoodMob> foodMobs = new ArrayList<EnumEdibleFoodMob>();
	private ArrayList<EnumEntityPrehistoric> fleesFrom = new ArrayList<EnumEntityPrehistoric>();
	
	private Item dropItemMeat;
	
	private double baseHealth;
	private double baseDamage;
	private double baseSpeed;
	private double baseStrength;
	private double maxHealth;
	private double maxDamage;
	private double maxSpeed;
	private double maxStrength;
	private double speedRunMult;
	private float baseSize;
	private float maxSize;
	private float baseExp;
	private float baseEyeHeight;
	private float baseBoundingBoxHeight;
	private float baseBoundingBoxWidth;
	private float expDaily;
	private float maxAwarenessRadius;
	private float immediateAwarenessRadius;
	private float herdWanderRadius;
	private float hungerLevel;
	private float territorialRadius;
	private int maxHerdSize;
	private int targetFoodHabbit;
	private int maxHunger;
	private int adultAge;
	private int teenAge;
	private int hungerIfEaten;
	private int healIfEaten;
	private int ticksPerHungerDecrement;
	private boolean canFormHerds;
	private boolean tameable;
	private boolean rideable;
	private boolean canCarry;
	private boolean attacksPlayersAsAdult;
	private boolean territorial;
	
	private final Class entityClass;
	
	private EnumEntityPrehistoric(Class entityClass, boolean tameable, boolean rideable, boolean canCarry, boolean canFormHerds, boolean territorial, int targetFoodHabbit) {
		this.entityClass = entityClass;
		this.tameable = tameable;
		this.rideable = rideable;
		this.canCarry = canCarry;
		this.canFormHerds = canFormHerds;
		this.territorial = territorial;
		this.targetFoodHabbit = targetFoodHabbit;
	}
	
	public boolean shouldRunFromEntity(EntityPrehistoric e) {
		return fleesFrom.contains(e.getType());
	}
	
	public boolean willEat(Item item) {
		for(EnumEdibleFoodstuff foodItem: foodItems) {
			if(foodItem.isItem(item)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean willEat(Block block) {
		for(EnumEdibleFoodBlocks foodBlock: foodBlocks) {
			if(foodBlock.isBlock(block)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean willAttack(EntityLiving entity) {
		for(EnumEdibleFoodMob foodMob: foodMobs) {
			if(foodMob.isEntity(entity)) {
				return true;
			}
		}
		return false;
	}
	
	private void setDimensions(float baseBoundingBoxHeight, float baseBoundingBoxWidth, float baseEyeHeight) {
		this.baseBoundingBoxHeight = baseBoundingBoxHeight;
		this.baseBoundingBoxWidth = baseBoundingBoxWidth;
		this.baseEyeHeight = baseEyeHeight;
	}
	
	private void setBaseValues(double health, double damage, double speed, double strength, float size, float exp) {
		baseHealth = health;
		baseDamage = damage;
		baseSpeed = speed;
		baseStrength = strength;
		baseSize = size;
		baseExp = exp;
	}
	
	private void setMaxValues(double health, double damage, double speed, double strength, float size) {
		maxHealth = health;
		maxDamage = damage;
		maxSpeed = speed;
		maxStrength = strength;
		maxSize = size;
	}
	
	private void setHerdProperties(int maxHerdSize, float maxAwarenessRadius, float immediateAwarenessRadius, float herdWanderRadius) {
		this.maxHerdSize = maxHerdSize;
		this.maxAwarenessRadius = maxAwarenessRadius;
		this.immediateAwarenessRadius = immediateAwarenessRadius;
		this.herdWanderRadius = herdWanderRadius;
	}
	
	private void setAges(int teenAge, int adultAge) {
		this.teenAge = teenAge;
		this.adultAge = adultAge;
	}
	
	private void setIfEaten(int hunger, int health) {
		this.hungerIfEaten = hunger;
		this.healIfEaten = health;
	}
	
	private void setExpDaily(float expDaily) {
		this.expDaily = expDaily;
	}
	
	private void setSpeedRunMultiplier(double speedRunMult) {
		this.speedRunMult = speedRunMult;
	}
	
	private void setHungerProperties(float hungerLevel, int ticksPerHungerDecrement) {
		this.hungerLevel = hungerLevel;
		this.ticksPerHungerDecrement = ticksPerHungerDecrement;
	}
	
	private void setAttacksPlayersAsAdult(boolean attacks) {
		this.attacksPlayersAsAdult = attacks;
	}
	
	private void setDropItem(Item item) {
		this.dropItemMeat = item;
	}
	
	private void setTerritorialRadius(float radius) {
		this.territorialRadius = radius;
	}
	
	public Item getDropItem() {
		return dropItemMeat;
	}
	
	public double getBaseHealth() {
		return baseHealth;
	}

	public double getBaseDamage() {
		return baseDamage;
	}

	public double getBaseSpeed() {
		return baseSpeed;
	}
	
	public double getBaseStrength() {
		return baseStrength;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public double getMaxDamage() {
		return maxDamage;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public double getMaxStrength() {
		return maxStrength;
	}

	public double getSpeedRunMult() {
		return speedRunMult;
	}

	public float getBaseSize() {
		return baseSize;
	}

	public float getMaxSize() {
		return maxSize;
	}

	public float getBaseEyeHeight() {
		return baseEyeHeight;
	}

	public float getBaseBoundingBoxHeight() {
		return baseBoundingBoxHeight;
	}

	public float getBaseBoundingBoxWidth() {
		return baseBoundingBoxWidth;
	}

	public float getBaseExp() {
		return baseExp;
	}

	public float getExpDaily() {
		return expDaily;
	}

	public float getMaxAwarenessRadius() {
		return maxAwarenessRadius;
	}

	public float getImmediateAwarenessRadius() {
		return immediateAwarenessRadius;
	}

	public float getHerdWanderRadius() {
		return herdWanderRadius;
	}
	
	public float getHungerLevel() {
		return hungerLevel;
	}

	public int getMaxHerdSize() {
		return maxHerdSize;
	}

	public int getTargetFoodHabbit() {
		return targetFoodHabbit;
	}

	public int getMaxHunger() {
		return maxHunger;
	}

	public int getAdultAge() {
		return adultAge;
	}

	public int getTeenAge() {
		return teenAge;
	}
	
	public int getHungerIfEaten() {
		return hungerIfEaten;
	}
	
	public int getHealthIfEaten() {
		return healIfEaten;
	}

	public int getTicksPerHungerDecrement() {
		return ticksPerHungerDecrement;
	}
	
	public boolean isCanFormHerds() {
		return canFormHerds;
	}

	public boolean isTameable() {
		return tameable;
	}

	public boolean isRideable() {
		return rideable;
	}

	public boolean isCanCarry() {
		return canCarry;
	}
	
	public boolean attacksPlayersAsAdult() {
		return attacksPlayersAsAdult;
	}
	
	public boolean eatsMeat() {
		return targetFoodHabbit == this.FOOD_HABBIT_CARNIVORE || targetFoodHabbit == this.FOOD_HABBIT_OMNIVORE;
	}
	
	public boolean eatsVegetables() {
		return targetFoodHabbit == this.FOOD_HABBIT_HERBIVORE || targetFoodHabbit == this.FOOD_HABBIT_OMNIVORE;
	}
	
	public boolean eatsBlocks() {
		return !foodBlocks.isEmpty();
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public static void init() {
		Allosaurus.setBaseValues(10.0D, 2.0D, 0.25D, 2.0D, 0.55F, 1.0F);
		Allosaurus.setMaxValues(40.0D, 11.0D, 0.42D, 11.0D, 3.1F);
		Allosaurus.setDimensions(1.4F, 1.3F, 1.0F); // Fix eye height
		Allosaurus.setAges(5, 10);
		Allosaurus.setAttacksPlayersAsAdult(true);
		Allosaurus.setExpDaily(0.2F);
		Allosaurus.setSpeedRunMultiplier(2.0D);
		Allosaurus.setIfEaten(25, 1);
		Allosaurus.setTerritorialRadius(20.0F);
		
	}
	
}
