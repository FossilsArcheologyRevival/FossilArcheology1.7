package fossilsarcheology.server.recipe;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.entity.prehistoric.MobType;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class FARecipeRegistry {

    public static String[] dyes = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};

    public static void register(RegistryEvent.Register<IRecipe> event) {
        /*
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.SKULL_LANTERN, 1), "X", "Y", 'X', FABlockRegistry.SKULL_BLOCK, 'Y', Blocks.TORCH);
        GameRegistry.addRecipe(new ItemStack(Items.DYE, 5, 15), "X", 'X', FABlockRegistry.SKULL_BLOCK);
        GameRegistry.addRecipe(new ItemStack(Items.DYE, 5, 15), "X", 'X', FABlockRegistry.SKULL_LANTERN);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FABlockRegistry.ANALYZER, 1), "XYX", "XWX", 'X', "ingotIron", 'Y', "artifact", 'W', "fossil"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FABlockRegistry.WORKTABLE_IDLE, 1), Items.PAPER, "craftingTableWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FABlockRegistry.WORKTABLE_IDLE, 1), "XYX", "YZY", "YXY", 'X', Items.STRING, 'Y', "plankWood", 'Z', Blocks.IRON_BARS));
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.TIME_MACHINE, 1), "XYX", "ZUZ", "VXV", 'X', FAItemRegistry.SCARAB_GEM, 'Y', Items.NETHER_STAR, 'Z', Items.GOLD_INGOT, 'U', Items.ENDER_PEARL, 'V', Blocks.OBSIDIAN);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.CULTIVATE_IDLE, 1), "XYX", "XWX", "ZZZ", 'X', Blocks.GLASS, 'Y', new ItemStack(Items.DYE, 1, 2), 'W', Items.WATER_BUCKET, 'Z', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CAKE, 1), "MMM", "SES", "WWW", 'M', Items.MILK_BUCKET, 'S', Items.SUGAR, 'W', Items.WHEAT, 'E', "foodEgg"));
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.OBSIDIAN_SPIKES, 4), "A A", "A A", 'A', Blocks.OBSIDIAN);
        GameRegistry.addRecipe(new ItemStack(FAItemRegistry.SKULL_STICK, 1), "X", "Y", 'X', FABlockRegistry.SKULL_BLOCK, 'Y', Items.STICK);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FABlockRegistry.DRUM, 1), "ZZZ", "XYX", "XXX", 'X', "plankWood", 'Y', Items.REDSTONE, 'Z', Items.LEATHER));
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.FEEDER, 1), new Object[]{"XYX", "ZAB", "BBB", 'X', Items.IRON_INGOT, 'Y', Blocks.GLASS, 'Z', Blocks.STONE_BUTTON, 'A', Items.BUCKET, 'B', Blocks.STONE});
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_AXE), Items.GOLDEN_AXE, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_AXE), Items.DIAMOND_AXE, FAItemRegistry.SCARAB_AXE);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_PICKAXE), Items.GOLDEN_PICKAXE, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_PICKAXE), Items.DIAMOND_PICKAXE, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.AQUATIC_SCARAB_GEM), FAItemRegistry.DOMINICAN_AMBER, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_HOE), Items.GOLDEN_HOE, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_HOE), Items.DIAMOND_HOE, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_SWORD), Items.GOLDEN_SWORD, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_SWORD), Items.DIAMOND_SWORD, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_SHOVEL), Items.GOLDEN_SHOVEL, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.SCARAB_SHOVEL), Items.DIAMOND_SHOVEL, FAItemRegistry.SCARAB_GEM);
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.DENSE_SAND, 2), Items.QUARTZ, Blocks.SAND);
        GameRegistry.addRecipe(new ShapelessOreRecipe(FAItemRegistry.DINOPEDIA, Items.BOOK, "fossil"));
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.TARDROP, 4, 0), FAItemRegistry.TAR_BUCKET);
        GameRegistry.addShapelessRecipe(new ItemStack(FAItemRegistry.RAW_CHICKEN_SOUP, 1, 0), Items.BUCKET, Items.CHICKEN);
        GameRegistry.addRecipe(new ItemStack(FAItemRegistry.CHICKEN_ESSENCE, 8), "XXX", "XYX", "XXX", 'X', Items.GLASS_BOTTLE, 'Y', FAItemRegistry.COOKED_CHICKEN_SOUP);
        GameRegistry.addRecipe(new ItemStack(FAItemRegistry.WHIP, 1), "XXS", "XTS", "TXS", 'T', Items.STICK, 'S', Items.STRING);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.VOLCANIC_BRICK, 4), "VV", "VV", 'V', FABlockRegistry.VOLCANIC_ROCK);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.VOLCANIC_SINGLESLAB, 6), "PPP", 'P', FABlockRegistry.VOLCANIC_BRICK);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.VOLCANIC_STAIRS, 4), "P  ", "PP ", "PPP", 'P', FABlockRegistry.VOLCANIC_BRICK);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.VOLCANIC_STAIRS, 4), "  P", " PP", "PPP", 'P', FABlockRegistry.VOLCANIC_BRICK);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.PALM_PLANKS, 4), "P", 'P', FABlockRegistry.PALM_LOG);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.PALM_PLANKS_SINGLESLAB, 6), new Object[] {"PPP", 'P', FABlockRegistry.PALM_PLANKS});
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.PALM_PLANKS_STAIRS, 4), "P  ", "PP ", "PPP", 'P', FABlockRegistry.PALM_PLANKS);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.PALM_PLANKS_STAIRS, 4), "  P", " PP", "PPP", 'P', FABlockRegistry.PALM_PLANKS);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FAItemRegistry.FEET_BOOTS, 1), "* *", "# #", '#', new ItemStack(FAItemRegistry.FOOT, 1, OreDictionary.WILDCARD_VALUE), '*', "dinosaurClaw"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FAItemRegistry.FEET_BOOTS, 1), "   ", "* *", "# #", '#', new ItemStack(FAItemRegistry.FOOT, 1, OreDictionary.WILDCARD_VALUE), '*', "dinosaurClaw"));
        GameRegistry.addRecipe(new ItemStack(FAItemRegistry.SHIN_LEGGINGS, 1), "###", "* *", "# #", '#', Items.BONE, '*', new ItemStack(FAItemRegistry.LEG_BONE, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FAItemRegistry.RIBCAGE_CHESTPLATE, 1), "B B", " R ", "BVB", 'B', Items.BONE, 'R', "dinosaurRibCage", 'V', "dinosaurVertebrae"));
        GameRegistry.addRecipe(new ItemStack(FAItemRegistry.SKULL_HELMET, 1), "#X#", "# #", '#', Items.BONE, 'X', new ItemStack(FAItemRegistry.SKULL, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1), "X X", "X X", "XXX", 'X', FAItemRegistry.POTTERY_SHARD);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1), "XX ", "XX ", "XX ", 'X', FAItemRegistry.POTTERY_SHARD);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1), " XX", " XX", " XX", 'X', FAItemRegistry.POTTERY_SHARD);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.KYLIX_VASE, 1), "   ", "XXX", " X ", 'X', FAItemRegistry.POTTERY_SHARD);
        GameRegistry.addRecipe(new ItemStack(FABlockRegistry.KYLIX_VASE, 1), "XXX", " X ", "   ", 'X', FAItemRegistry.POTTERY_SHARD);
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 2), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 3), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 14), new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 2), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 3), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 14), new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 2), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 3), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 14), new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 1));
        GameRegistry.addSmelting(FAItemRegistry.RAW_CHICKEN_SOUP, new ItemStack(FAItemRegistry.COOKED_CHICKEN_SOUP), 3.0F);
        GameRegistry.addSmelting(FABlockRegistry.DENSE_SAND, new ItemStack(FABlockRegistry.STRONG_GLASS), 3.0F);
        GameRegistry.addSmelting(Items.EGG, new ItemStack(FAItemRegistry.COOKED_EGG), 3.0F);
        for (int i = 0; i < PrehistoricEntityType.values().length; i++) {
            if (PrehistoricEntityType.values()[i].mobType == MobType.DINOSAUR && !PrehistoricEntityType.values()[i].isAquatic()) {
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].eggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 3.0F);
            }
            if (PrehistoricEntityType.values()[i].mobType == MobType.BIRD) {
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].birdEggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 3.0F);
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].bestBirdEggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 3.0F);
            }
            if (PrehistoricEntityType.values()[i].mobType == MobType.CHICKEN) {
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].bestBirdEggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 3.0F);
            }
            if (PrehistoricEntityType.values()[i].foodItem != null) {
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].foodItem, new ItemStack(PrehistoricEntityType.values()[i].cookedFoodItem), 3.0F);
            }
            if (PrehistoricEntityType.values()[i].fishItem != null && PrehistoricEntityType.values()[i] != PrehistoricEntityType.NAUTILUS) {
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].fishItem, new ItemStack(PrehistoricEntityType.values()[i].cookedFoodItem), 3.0F);
            }
            if (PrehistoricEntityType.values()[i].fishItem != null && PrehistoricEntityType.values()[i] == PrehistoricEntityType.NAUTILUS) {
                GameRegistry.addSmelting(PrehistoricEntityType.values()[i].fishItem, new ItemStack(FAItemRegistry.SJL), 3.0F);
            }

        }
        GameRegistry.addSmelting(FAItemRegistry.ICED_MEAT, new ItemStack(Items.BEEF), 3.0F);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FAItemRegistry.TOY_BALL, 1, 15), "XYX", "YZY", "XYX", 'X', Items.STRING, 'Y', Blocks.WOOL, 'Z', "slimeball"));
        for (int i = 0; i < 16; i++) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FAItemRegistry.TOY_BALL, 1, i), new ItemStack(FAItemRegistry.TOY_BALL, 1, OreDictionary.WILDCARD_VALUE), "dye" + dyes[i]));
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FAItemRegistry.TOY_TETHERED_LOG), "X", "X", "Y", 'X', Items.STRING, 'Y', "logWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FAItemRegistry.TOY_SCRATCHING_POST), "YYY", "YZY", " X ", 'X', "slabWood", 'Z', "stickWood", 'Y', Blocks.WOOL));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FABlockRegistry.BUBBLE_MACHINE), "XYX", "YZY", "XYX", 'X', "nuggetGold", 'Y', "ingotGold", 'Z', Items.WATER_BUCKET));

    */
        FAItemRegistry.ANCIENT_HELMET_MATERIAL.setRepairItem(new ItemStack(FAItemRegistry.SCARAB_GEM));
        FAItemRegistry.SCARAB_MATERIAL.setRepairItem(new ItemStack(FAItemRegistry.SCARAB_GEM));
    }
}
