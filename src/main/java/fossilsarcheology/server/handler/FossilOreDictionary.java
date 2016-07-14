package fossilsarcheology.server.handler;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.enums.EnumMobType;
import fossilsarcheology.server.enums.EnumPrehistoric;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class FossilOreDictionary {
    public static void oreRegistration() {
        OreDictionary.registerOre("plankWood", new ItemStack(FABlockRegistry.INSTANCE.palaePlanks));
        OreDictionary.registerOre("plankWood", new ItemStack(FABlockRegistry.INSTANCE.ancientWood));
        OreDictionary.registerOre("logWood", new ItemStack(FABlockRegistry.INSTANCE.palmLog));
        OreDictionary.registerOre("treeLeaves", new ItemStack(FABlockRegistry.INSTANCE.palmLeaves));
        OreDictionary.registerOre("slabWood", new ItemStack(FABlockRegistry.INSTANCE.palaeSingleSlab));
        OreDictionary.registerOre("slabWood", new ItemStack(FABlockRegistry.INSTANCE.ancientWoodSingleSlab));
        OreDictionary.registerOre("stairWood", new ItemStack(FABlockRegistry.INSTANCE.palaeStairs));
        OreDictionary.registerOre("stairWood", new ItemStack(FABlockRegistry.INSTANCE.ancientWoodStairs));
        OreDictionary.registerOre("treeSapling", new ItemStack(FABlockRegistry.INSTANCE.palmSap));
        OreDictionary.registerOre("record", new ItemStack(FAItemRegistry.INSTANCE.fossilrecordBones));
        OreDictionary.registerOre("oreFossil", new ItemStack(FABlockRegistry.INSTANCE.blockFossil));
        OreDictionary.registerOre("oreAmber", new ItemStack(FABlockRegistry.INSTANCE.amberOre));
        OreDictionary.registerOre("stoneBasalt", new ItemStack(FABlockRegistry.INSTANCE.volcanicRock));
        OreDictionary.registerOre("stoneBasaltBricks", new ItemStack(FABlockRegistry.INSTANCE.volcanicBrick));
        OreDictionary.registerOre("blockGlass", new ItemStack(FABlockRegistry.INSTANCE.strongGlass));
        OreDictionary.registerOre("glass", new ItemStack(FABlockRegistry.INSTANCE.strongGlass));
        OreDictionary.registerOre("blockGlassColorless", new ItemStack(FABlockRegistry.INSTANCE.strongGlass));
        OreDictionary.registerOre("blockGlass", new ItemStack(FABlockRegistry.INSTANCE.ancientGlass));
        OreDictionary.registerOre("glass", new ItemStack(FABlockRegistry.INSTANCE.ancientGlass));
        OreDictionary.registerOre("dustAsh", new ItemStack(FABlockRegistry.INSTANCE.volcanicAsh));
        OreDictionary.registerOre("blockAsh", new ItemStack(FABlockRegistry.INSTANCE.volcanicAsh));
        OreDictionary.registerOre("blockTar", new ItemStack(FABlockRegistry.INSTANCE.tar));
        OreDictionary.registerOre("skull", new ItemStack(FABlockRegistry.INSTANCE.blockSkull));
        OreDictionary.registerOre("denseSand", new ItemStack(FABlockRegistry.INSTANCE.denseSand));
        OreDictionary.registerOre("skullLantern", new ItemStack(FABlockRegistry.INSTANCE.skullLantern));
        OreDictionary.registerOre("stoneAncient", new ItemStack(FABlockRegistry.INSTANCE.ancientStone));
        OreDictionary.registerOre("stoneAncientBrick", new ItemStack(FABlockRegistry.INSTANCE.ancientStonebrick));
        OreDictionary.registerOre("stone", new ItemStack(FABlockRegistry.INSTANCE.ancientStone));
        OreDictionary.registerOre("stoneBrick", new ItemStack(FABlockRegistry.INSTANCE.ancientStonebrick));

        OreDictionary.registerOre("artifact", new ItemStack(FAItemRegistry.INSTANCE.relic));
        OreDictionary.registerOre("fossil", new ItemStack(FAItemRegistry.INSTANCE.biofossil));
        OreDictionary.registerOre("gemScarab", new ItemStack(FAItemRegistry.INSTANCE.gem));
        OreDictionary.registerOre("gemScarabBlue", new ItemStack(FAItemRegistry.INSTANCE.gem_blue));
        OreDictionary.registerOre("gemAmber", new ItemStack(FAItemRegistry.INSTANCE.amber));
        OreDictionary.registerOre("gemDominicanAmber", new ItemStack(FAItemRegistry.INSTANCE.DominicanAmber));
        OreDictionary.registerOre("dinosaurClaw", new ItemStack(FAItemRegistry.INSTANCE.claw, 1, 1));
        OreDictionary.registerOre("dinosaurClaw", new ItemStack(FAItemRegistry.INSTANCE.claw, 1, 8));
        OreDictionary.registerOre("dinosaurClaw", new ItemStack(FAItemRegistry.INSTANCE.claw, 1, 11));
        OreDictionary.registerOre("dinosaurClaw", new ItemStack(FAItemRegistry.INSTANCE.claw, 1, 14));
        OreDictionary.registerOre("dinosaurClaw", new ItemStack(FAItemRegistry.INSTANCE.claw, 1, 15));
        OreDictionary.registerOre("listAllseed", new ItemStack(FAItemRegistry.INSTANCE.seed));
        OreDictionary.registerOre("flowerSeed", new ItemStack(FAItemRegistry.INSTANCE.seed));
        OreDictionary.registerOre("listAllseed", new ItemStack(FAItemRegistry.INSTANCE.fernSeed));
        OreDictionary.registerOre("sporeFern", new ItemStack(FAItemRegistry.INSTANCE.fernSeed));
        OreDictionary.registerOre("materialRotten", new ItemStack(FAItemRegistry.INSTANCE.failuresaurusFlesh));
        OreDictionary.registerOre("slimeball", new ItemStack(FAItemRegistry.INSTANCE.failuresaurusFlesh));
        OreDictionary.registerOre("tar", new ItemStack(FAItemRegistry.INSTANCE.tardrop));
        OreDictionary.registerOre("slimeball", new ItemStack(FAItemRegistry.INSTANCE.tardrop));
        OreDictionary.registerOre("tarBucket", new ItemStack(FAItemRegistry.INSTANCE.tar_bucket));
        OreDictionary.registerOre("bucketTar", new ItemStack(FAItemRegistry.INSTANCE.tar_bucket));
        OreDictionary.registerOre("record", new ItemStack(FAItemRegistry.INSTANCE.recordNano_Anu));
        OreDictionary.registerOre("record", new ItemStack(FAItemRegistry.INSTANCE.recordNano_Scarab));
        OreDictionary.registerOre("record", new ItemStack(FAItemRegistry.INSTANCE.recordNano_Discovering));

        OreDictionary.registerOre("dinosaurSkull", new ItemStack(FAItemRegistry.INSTANCE.skull, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("dinosaurLegBone", new ItemStack(FAItemRegistry.INSTANCE.legBone, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("dinosaurArmBone", new ItemStack(FAItemRegistry.INSTANCE.armBone, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("dinosaurVertebrae", new ItemStack(FAItemRegistry.INSTANCE.vertebrae, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("dinosaurRibCage", new ItemStack(FAItemRegistry.INSTANCE.dinoRibCage, 1, OreDictionary.WILDCARD_VALUE));

        for (EnumPrehistoric prehistoric : EnumPrehistoric.values()) {
            if (prehistoric.eggItem != null) {
                OreDictionary.registerOre("listAllegg", prehistoric.eggItem);
                OreDictionary.registerOre("objectEgg", prehistoric.eggItem);
                OreDictionary.registerOre("bakingEgg", prehistoric.eggItem);
                if (prehistoric.type == EnumMobType.FISH) {
                    OreDictionary.registerOre("foodRoe", prehistoric.eggItem);
                    OreDictionary.registerOre("foodCaviar", prehistoric.eggItem);
                }
            }
            if (prehistoric.foodItem != null) {
                OreDictionary.registerOre("listAllmeatraw", prehistoric.foodItem);
            }
            if (prehistoric.cookedFoodItem != null) {
                OreDictionary.registerOre("listAllmeatcooked", prehistoric.cookedFoodItem);
            }
        }
        OreDictionary.registerOre("foodCalamariraw", EnumPrehistoric.Nautilus.fishItem);
        OreDictionary.registerOre("foodCalamaricooked", FAItemRegistry.INSTANCE.sjl);

    }
}
