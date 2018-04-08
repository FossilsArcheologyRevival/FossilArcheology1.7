package fossilsarcheology.server.util;

import com.google.common.collect.Maps;
import fossilsarcheology.server.entity.prehistoric.Diet;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.Map;

public enum FoodMappings {
    INSTANCE;

    private Map<ItemStack, Integer> carnivoreItemDiet;
    private Map<ItemStack, Integer> herbivoreItemDiet;
    private Map<ItemStack, Integer> omnivoreItemDiet;
    private Map<ItemStack, Integer> piscivoreItemDiet;
    private Map<ItemStack, Integer> carnivoreEggItemDiet;
    private Map<ItemStack, Integer> insectivoreItemDiet;
    private Map<ItemStack, Integer> pisccarnivoreItemDiet;
    private Map<Block, Integer> carnivoreBlockDiet;
    private Map<Block, Integer> herbivoreBlockDiet;
    private Map<Block, Integer> omnivoreBlockDiet;
    private Map<Block, Integer> piscivoreBlockDiet;
    private Map<Block, Integer> carnivoreEggBlockDiet;
    private Map<Block, Integer> insectivoreBlockDiet;
    private Map<Block, Integer> pisccarnivoreBlockDiet;
    private Map<Class<? extends Entity>, Integer> carnivoreEntityDiet;
    private Map<Class<? extends Entity>, Integer> herbivoreEntityDiet;
    private Map<Class<? extends Entity>, Integer> omnivoreEntityDiet;
    private Map<Class<? extends Entity>, Integer> piscivoreEntityDiet;
    private Map<Class<? extends Entity>, Integer> carnivore_eggEntityDiet;
    private Map<Class<? extends Entity>, Integer> insectivoreEntityDiet;
    private Map<Class<? extends Entity>, Integer> pisccarnivoreEntityDiet;

    /**
     * Add an item to a specific diet.
     *
     * @param item The item to be added as Food.
     * @param food The amount of food points for the item.
     * @param diet The specific diet to add the item to.
     */
    public void addToItemMappings(ItemStack item, int food, Diet diet) {
        if (item != null) {
            switch (diet) {
                case CARNIVORE:
                    if (this.carnivoreItemDiet == null) {
                        this.carnivoreItemDiet = Maps.newHashMap();
                    }
                    if (!this.carnivoreItemDiet.containsKey(item)) {
                        this.carnivoreItemDiet.put(item, food);
                    }
                    break;
                case HERBIVORE:
                    if (this.herbivoreItemDiet == null) {
                        this.herbivoreItemDiet = Maps.newHashMap();
                    }
                    if (!this.herbivoreItemDiet.containsKey(item)) {
                        this.herbivoreItemDiet.put(item, food);
                    }
                    break;
                case OMNIVORE:
                    if (this.omnivoreItemDiet == null) {
                        this.omnivoreItemDiet = Maps.newHashMap();
                    }
                    if (!this.omnivoreItemDiet.containsKey(item)) {
                        this.omnivoreItemDiet.put(item, food);
                    }
                    break;
                case PISCIVORE:
                    if (this.piscivoreItemDiet == null) {
                        this.piscivoreItemDiet = Maps.newHashMap();
                    }
                    if (!this.piscivoreItemDiet.containsKey(item)) {
                        this.piscivoreItemDiet.put(item, food);
                    }
                    break;
                case CARNIVORE_EGG:
                    if (this.carnivoreEggItemDiet == null) {
                        this.carnivoreEggItemDiet = Maps.newHashMap();
                    }
                    if (!this.carnivoreEggItemDiet.containsKey(item)) {
                        this.carnivoreEggItemDiet.put(item, food);
                    }
                    break;
                case INSECTIVORE:
                    if (this.insectivoreItemDiet == null) {
                        this.insectivoreItemDiet = Maps.newHashMap();
                    }
                    if (!this.insectivoreItemDiet.containsKey(item)) {
                        this.insectivoreItemDiet.put(item, food);
                    }
                    break;
                case PISCCARNIVORE:
                    if (this.pisccarnivoreItemDiet == null) {
                        this.pisccarnivoreItemDiet = Maps.newHashMap();
                    }
                    if (!this.pisccarnivoreItemDiet.containsKey(item)) {
                        this.pisccarnivoreItemDiet.put(item, food);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Get the amount of food points from an item in the mapping.
     *
     * @param item The item to find.
     * @param diet The specific diet to find the item from.
     */
    public int getItemFoodAmount(ItemStack item, Diet diet) {
        switch (diet) {
            case CARNIVORE:
                if (this.carnivoreItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.carnivoreItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.carnivoreItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            case HERBIVORE:
                if (this.herbivoreItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.herbivoreItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.herbivoreItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            case OMNIVORE:
                if (this.omnivoreItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.omnivoreItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.omnivoreItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            case PISCIVORE:
                if (this.piscivoreItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.piscivoreItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.piscivoreItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            case CARNIVORE_EGG:
                if (this.carnivoreEggItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.carnivoreEggItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.carnivoreEggItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            case INSECTIVORE:
                if (this.insectivoreItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.insectivoreItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.insectivoreItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            case PISCCARNIVORE:
                if (this.piscivoreItemDiet != null) {
                    for (Map.Entry<ItemStack, Integer> entry : this.piscivoreItemDiet.entrySet()) {
                        ItemStack compareStack = entry.getKey();
                        if (item.isItemEqual(compareStack)) {
                            return this.piscivoreItemDiet.get(compareStack);
                        }
                    }
                }
                break;
            default:
                return 0;
        }
        return 0;
    }

    /**
     * Add an block to a specific diet. Usually only for herbivores and omnivores.
     *
     * @param block The block to be added as Food.
     * @param food The amount of food points for the block.
     * @param diet The specific diet to add the block to.
     * @param registerItem Register the block's item as food or not. Usually true, but false for technical blocks like wheat.
     */
    public void addToBlockMappings(Block block, int food, Diet diet, boolean registerItem) {
        switch (diet) {
            case CARNIVORE:
                if (this.carnivoreBlockDiet == null) {
                    this.carnivoreBlockDiet = Maps.newHashMap();
                }
                if (!this.carnivoreBlockDiet.containsKey(block)) {
                    this.carnivoreBlockDiet.put(block, food);
                }
                break;
            case HERBIVORE:
                if (this.herbivoreBlockDiet == null) {
                    this.herbivoreBlockDiet = Maps.newHashMap();
                }
                if (!this.herbivoreBlockDiet.containsKey(block)) {
                    this.herbivoreBlockDiet.put(block, food);
                }
                break;
            case OMNIVORE:
                if (this.omnivoreBlockDiet == null) {
                    this.omnivoreBlockDiet = Maps.newHashMap();
                }
                if (!this.omnivoreBlockDiet.containsKey(block)) {
                    this.omnivoreBlockDiet.put(block, food);
                }
                break;
            case PISCIVORE:
                if (this.piscivoreBlockDiet == null) {
                    this.piscivoreBlockDiet = Maps.newHashMap();
                }
                if (!this.piscivoreBlockDiet.containsKey(block)) {
                    this.piscivoreBlockDiet.put(block, food);
                }
                break;
            case CARNIVORE_EGG:
                if (this.carnivoreEggBlockDiet == null) {
                    this.carnivoreEggBlockDiet = Maps.newHashMap();
                }
                if (!this.carnivoreEggBlockDiet.containsKey(block)) {
                    this.carnivoreEggBlockDiet.put(block, food);
                }
                break;
            case INSECTIVORE:
                if (this.insectivoreBlockDiet == null) {
                    this.insectivoreBlockDiet = Maps.newHashMap();
                }
                if (!this.insectivoreBlockDiet.containsKey(block)) {
                    this.insectivoreBlockDiet.put(block, food);
                }
                break;
            case PISCCARNIVORE:
                if (this.pisccarnivoreBlockDiet == null) {
                    this.pisccarnivoreBlockDiet = Maps.newHashMap();
                }
                if (!this.pisccarnivoreBlockDiet.containsKey(block)) {
                    this.pisccarnivoreBlockDiet.put(block, food);
                }
                break;
            default:
                break;
        }
        if (registerItem) {
            this.addToItemMappings(new ItemStack(Item.getItemFromBlock(block)), food, diet);
        }
    }

    /**
     * Get the amount of food points from a block in the mapping.
     *
     * @param block The block to find.
     * @param diet The specific diet to find the block from.
     */
    public int getBlockFoodAmount(Block block, Diet diet) {
        switch (diet) {
            case CARNIVORE:
                if (this.carnivoreBlockDiet != null && this.carnivoreBlockDiet.containsKey(block)) {
                    return this.carnivoreBlockDiet.get(block);
                }
                break;
            case HERBIVORE:
                if (this.herbivoreBlockDiet != null && this.herbivoreBlockDiet.containsKey(block)) {
                    return this.herbivoreBlockDiet.get(block);
                }
                break;
            case OMNIVORE:
                if (this.omnivoreBlockDiet != null && this.omnivoreBlockDiet.containsKey(block)) {
                    return this.omnivoreBlockDiet.get(block);
                }
                break;
            case PISCIVORE:
                if (this.piscivoreBlockDiet != null && this.piscivoreBlockDiet.containsKey(block)) {
                    return this.piscivoreBlockDiet.get(block);
                }
                break;
            case CARNIVORE_EGG:
                if (this.carnivoreEggBlockDiet != null && this.carnivoreEggBlockDiet.containsKey(block)) {
                    return this.carnivoreEggBlockDiet.get(block);
                }
                break;
            case INSECTIVORE:
                if (this.insectivoreBlockDiet != null && this.insectivoreBlockDiet.containsKey(block)) {
                    return this.insectivoreBlockDiet.get(block);
                }
                break;
            case PISCCARNIVORE:
                if (this.pisccarnivoreBlockDiet != null && this.pisccarnivoreBlockDiet.containsKey(block)) {
                    return this.pisccarnivoreBlockDiet.get(block);
                }
                break;
            default:
                return 0;
        }
        return 0;
    }

    public void addToEntityMappings(Class<? extends Entity> entity, int food, Diet diet) {
        switch (diet) {
            case CARNIVORE:
                if (this.carnivoreEntityDiet == null) {
                    this.carnivoreEntityDiet = Maps.newHashMap();
                }
                if (!this.carnivoreEntityDiet.containsKey(entity)) {
                    this.carnivoreEntityDiet.put(entity, food);
                }
                break;
            case HERBIVORE:
                if (this.herbivoreEntityDiet == null) {
                    this.herbivoreEntityDiet = Maps.newHashMap();
                }
                if (!this.herbivoreEntityDiet.containsKey(entity)) {
                    this.herbivoreEntityDiet.put(entity, food);
                }
                break;
            case OMNIVORE:
                if (this.omnivoreEntityDiet == null) {
                    this.omnivoreEntityDiet = Maps.newHashMap();
                }
                if (!this.omnivoreEntityDiet.containsKey(entity)) {
                    this.omnivoreEntityDiet.put(entity, food);
                }
                break;
            case PISCIVORE:
                if (this.piscivoreEntityDiet == null) {
                    this.piscivoreEntityDiet = Maps.newHashMap();
                }
                if (!this.piscivoreEntityDiet.containsKey(entity)) {
                    this.piscivoreEntityDiet.put(entity, food);
                }
                break;
            case CARNIVORE_EGG:
                if (this.carnivore_eggEntityDiet == null) {
                    this.carnivore_eggEntityDiet = Maps.newHashMap();
                }
                if (!this.carnivore_eggEntityDiet.containsKey(entity)) {
                    this.carnivore_eggEntityDiet.put(entity, food);
                }
                break;
            case INSECTIVORE:
                if (this.insectivoreEntityDiet == null) {
                    this.insectivoreEntityDiet = Maps.newHashMap();
                }
                if (!this.insectivoreEntityDiet.containsKey(entity)) {
                    this.insectivoreEntityDiet.put(entity, food);
                }
                break;
            case PISCCARNIVORE:
                if (this.pisccarnivoreEntityDiet == null) {
                    this.pisccarnivoreEntityDiet = Maps.newHashMap();
                }
                if (!this.pisccarnivoreEntityDiet.containsKey(entity)) {
                    this.pisccarnivoreEntityDiet.put(entity, food);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Add an entity to a specific diet. Usually only for carnivores and omnivores.
     *
     * @param entity The entity class to be added as Food.
     * @param diet The specific diet to add the entity to.
     */
    public int getEntityFoodAmount(Class<? extends Entity> entity, Diet diet) {
        switch (diet) {
            case CARNIVORE:
                if (this.carnivoreEntityDiet != null && this.carnivoreEntityDiet.containsKey(entity)) {
                    return this.carnivoreEntityDiet.get(entity);
                }
                break;
            case HERBIVORE:
                if (this.herbivoreEntityDiet != null && this.herbivoreEntityDiet.containsKey(entity)) {
                    return this.herbivoreEntityDiet.get(entity);
                }
                break;
            case OMNIVORE:
                if (this.omnivoreEntityDiet != null && this.omnivoreEntityDiet.containsKey(entity)) {
                    return this.omnivoreEntityDiet.get(entity);
                }
                break;
            case PISCIVORE:
                if (this.piscivoreEntityDiet != null && this.piscivoreEntityDiet.containsKey(entity)) {
                    return this.piscivoreEntityDiet.get(entity);
                }
                break;
            case CARNIVORE_EGG:
                if (this.carnivore_eggEntityDiet != null && this.carnivore_eggEntityDiet.containsKey(entity)) {
                    return this.carnivore_eggEntityDiet.get(entity);
                }
                break;
            case INSECTIVORE:
                if (this.insectivoreEntityDiet != null && this.insectivoreEntityDiet.containsKey(entity)) {
                    return this.insectivoreEntityDiet.get(entity);
                }
                break;
            case PISCCARNIVORE:
                if (this.pisccarnivoreEntityDiet != null && this.pisccarnivoreEntityDiet.containsKey(entity)) {
                    return this.pisccarnivoreEntityDiet.get(entity);
                }
                break;
            default:
                return 0;
        }
        return 0;
    }

    /**
     * Gives out a list of all of the food items for a diet. Used in dinopedia.
     *
     * @param diet The specific diet to show.
     */
    public Map<ItemStack, Integer> getFoodRenderList(Diet diet) {
        switch (diet) {
            case CARNIVORE:
                if (this.carnivoreItemDiet == null) {
                    this.carnivoreItemDiet = Maps.newHashMap();
                }
                return this.carnivoreItemDiet;
            case HERBIVORE:
                if (this.herbivoreItemDiet == null) {
                    this.herbivoreItemDiet = Maps.newHashMap();
                }
                return this.herbivoreItemDiet;
            case OMNIVORE:
                if (this.omnivoreItemDiet == null) {
                    this.omnivoreItemDiet = Maps.newHashMap();
                }
                return this.omnivoreItemDiet;
            case PISCIVORE:
                if (this.piscivoreItemDiet == null) {
                    this.piscivoreItemDiet = Maps.newHashMap();
                }
                return this.piscivoreItemDiet;
            case CARNIVORE_EGG:
                if (this.carnivoreEggItemDiet == null) {
                    this.carnivoreEggItemDiet = Maps.newHashMap();
                }
                return this.carnivoreEggItemDiet;
            case INSECTIVORE:
                if (this.insectivoreItemDiet == null) {
                    this.insectivoreItemDiet = Maps.newHashMap();
                }
                return this.insectivoreItemDiet;
            case PISCCARNIVORE:
                if (this.pisccarnivoreItemDiet == null) {
                    this.pisccarnivoreItemDiet = Maps.newHashMap();
                }
                return this.pisccarnivoreItemDiet;
            default:
                return this.carnivoreItemDiet;
        }
    }

    /**
     * Removes an item from the mapping.
     *
     * @param diet The specific diet to show.
     */
    public void removeItemMapping(ItemStack item, Diet diet) {
        this.getFoodRenderList(diet).remove(item);
    }

    /**
     * Adds a item, block, or entity class to all the carnivore mappings.
     *
     * @param entity The entity class being registered.
     * @param food The amount of food points for the object.
     */
    public void addMeat(Class<? extends Entity> entity, int food) {
        this.addToEntityMappings(entity, food, Diet.CARNIVORE);
        this.addToEntityMappings(entity, food, Diet.CARNIVORE_EGG);
        this.addToEntityMappings(entity, food, Diet.OMNIVORE);
        this.addToEntityMappings(entity, food, Diet.PISCCARNIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the carnivore mappings.
     *
     * @param block The block being registered.
     * @param food The amount of food points for the object.
     */
    public void addMeat(Block block, int food) {
        this.addToBlockMappings(block, food, Diet.CARNIVORE, true);
        this.addToBlockMappings(block, food, Diet.CARNIVORE_EGG, true);
        this.addToBlockMappings(block, food, Diet.OMNIVORE, true);
        this.addToBlockMappings(block, food, Diet.PISCCARNIVORE, true);
    }

    /**
     * Adds a item, block, or entity class to all the carnivore mappings.
     *
     * @param item The item being registered.
     * @param food The amount of food points for the object.
     */
    public void addMeat(ItemStack item, int food) {
        this.addToItemMappings(item, food, Diet.CARNIVORE);
        this.addToItemMappings(item, food, Diet.CARNIVORE_EGG);
        this.addToItemMappings(item, food, Diet.OMNIVORE);
        this.addToItemMappings(item, food, Diet.PISCCARNIVORE);
    }

    /**
     * Adds all ore dictionary registries to all the carnivore mappings.
     *
     * @param ore_dictionary_name The ore dictionary registry name being registered.
     * @param food The amount of food points for the object.
     */
    public void addMeat(String ore_dictionary_name, int food) {
        this.addOreDictionary(ore_dictionary_name, food, Diet.CARNIVORE);
        this.addOreDictionary(ore_dictionary_name, food, Diet.CARNIVORE_EGG);
        this.addOreDictionary(ore_dictionary_name, food, Diet.OMNIVORE);
        this.addOreDictionary(ore_dictionary_name, food, Diet.PISCCARNIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the herbivore mappings.
     *
     * @param entity The entity class being registered.
     * @param food The amount of food points for the object.
     */
    public void addPlant(Class<? extends Entity> entity, int food) {
        this.addToEntityMappings(entity, food, Diet.HERBIVORE);
        this.addToEntityMappings(entity, food, Diet.OMNIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the herbivore mappings.
     *
     * @param block The block being registered.
     * @param food The amount of food points for the object.
     */
    public void addPlant(Block block, int food) {
        this.addToBlockMappings(block, food, Diet.HERBIVORE, true);
        this.addToBlockMappings(block, food, Diet.OMNIVORE, true);
    }

    /**
     * Adds a item, block, or entity class to all the herbivore mappings.
     *
     * @param item The item being registered.
     * @param food The amount of food points for the object.
     */
    public void addPlant(ItemStack item, int food) {
        this.addToItemMappings(item, food, Diet.HERBIVORE);
        this.addToItemMappings(item, food, Diet.OMNIVORE);
    }

    /**
     * Adds all ore dictionary registries to all the herbivore mappings.
     *
     * @param ore_dictionary_name The ore dictionary registry name being registered.
     * @param food The amount of food points for the object.
     */
    public void addPlant(String ore_dictionary_name, int food) {
        this.addOreDictionary(ore_dictionary_name, food, Diet.HERBIVORE);
        this.addOreDictionary(ore_dictionary_name, food, Diet.OMNIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the piscivore mappings.
     *
     * @param entity The entity class being registered.
     * @param food The amount of food points for the object.
     */
    public void addFish(Class<? extends Entity> entity, int food) {
        this.addToEntityMappings(entity, food, Diet.PISCCARNIVORE);
        this.addToEntityMappings(entity, food, Diet.PISCIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the piscivore mappings.
     *
     * @param block The block being registered.
     * @param food The amount of food points for the object.
     */
    public void addFish(Block block, int food) {
        this.addToBlockMappings(block, food, Diet.PISCCARNIVORE, true);
        this.addToBlockMappings(block, food, Diet.PISCIVORE, true);
    }

    /**
     * Adds a item, block, or entity class to all the piscivore mappings.
     *
     * @param item The item being registered.
     * @param food The amount of food points for the object.
     */
    public void addFish(ItemStack item, int food) {
        this.addToItemMappings(item, food, Diet.PISCCARNIVORE);
        this.addToItemMappings(item, food, Diet.PISCIVORE);
    }

    /**
     * Adds all ore dictionary registries to all the piscivore mappings.
     *
     * @param ore_dictionary_name The ore dictionary registry name being registered.
     * @param food The amount of food points for the object.
     */
    public void addFish(String ore_dictionary_name, int food) {
        this.addOreDictionary(ore_dictionary_name, food, Diet.PISCCARNIVORE);
        this.addOreDictionary(ore_dictionary_name, food, Diet.PISCIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the egg eating mappings.
     *
     * @param entity The entity class being registered.
     * @param food The amount of food points for the object.
     */
    public void addEgg(Class<? extends Entity> entity, int food) {
        this.addToEntityMappings(entity, food, Diet.CARNIVORE_EGG);
        this.addToEntityMappings(entity, food, Diet.OMNIVORE);
    }

    /**
     * Adds a item, block, or entity class to all the egg eating mappings.
     *
     * @param block The block being registered.
     * @param food The amount of food points for the object.
     */
    public void addEgg(Block block, int food) {
        this.addToBlockMappings(block, food, Diet.CARNIVORE_EGG, true);
        this.addToBlockMappings(block, food, Diet.OMNIVORE, true);
    }

    /**
     * Adds a item, block, or entity class to all the egg eating mappings.
     *
     * @param item The item being registered.
     * @param food The amount of food points for the object.
     */
    public void addEgg(ItemStack item, int food) {
        this.addToItemMappings(item, food, Diet.CARNIVORE_EGG);
        this.addToItemMappings(item, food, Diet.OMNIVORE);
    }

    /**
     * Adds all ore dictionary registries to all the egg eating mappings.
     *
     * @param ore_dictionary_name The ore dictionary registry name being registered.
     * @param food The amount of food points for the object.
     */
    public void addEgg(String ore_dictionary_name, int food) {
        this.addOreDictionary(ore_dictionary_name, food, Diet.CARNIVORE_EGG);
        this.addOreDictionary(ore_dictionary_name, food, Diet.OMNIVORE);
    }

    private void addOreDictionary(String dict_name, int food_value, Diet diet) {
        List<ItemStack> stacks = OreDictionary.getOres(dict_name);
        if (!stacks.isEmpty()) {
            for (ItemStack stack : stacks) {
                if (stack != null) {
                    if (stack.getItem() instanceof ItemBlock) {
                        this.addToBlockMappings(((ItemBlock) stack.getItem()).getBlock(), food_value, diet, true);
                    } else {
                        this.addToItemMappings(stack, food_value, diet);
                    }
                }
            }
        }
    }
}
