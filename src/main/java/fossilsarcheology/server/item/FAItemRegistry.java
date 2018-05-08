package fossilsarcheology.server.item;

import fossilsarcheology.Revival;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FAItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final ItemArmor.ArmorMaterial ANCIENT_HELMET_MATERIAL = EnumHelper.addArmorMaterial("AncientHelmet", "fossil:armor_ancient_helmet", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static final ItemArmor.ArmorMaterial BONE = EnumHelper.addArmorMaterial("Bone", "fossil:armor_bone", 25, new int[]{2, 7, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0F);
    public static final Item.ToolMaterial SCARAB_MATERIAL = EnumHelper.addToolMaterial("Scarab", 3, 1861, 8.0F, 4.0F, 25);
    public static final Item.ToolMaterial TOOTH_DAGGER_MATERIAL = EnumHelper.addToolMaterial("toothDagger", 3, 250, 70.0F, 1.5F, 25);
    public static final Item.ToolMaterial ICED_MEAT_MATERIAL = EnumHelper.addToolMaterial("IcedMeat", 1, 3, 1.0F, 3.5F, 10);

    public static final BioFossilItem BIOFOSSIL = new BioFossilItem(false);
    public static final BioFossilItem TAR_FOSSIL = new BioFossilItem(true);
    public static final BasicItem DINOPEDIA = new BasicItem("dinopedia");
    public static final BasicItem FAILURESAURUS_FLESH = new BasicItem("failuresaurus_flesh");
    public static final BasicItem AMBER = new BasicItem("amber");
    public static final BasicItem DOMINICAN_AMBER = new BasicItem("dominican_amber");
    public static final BasicItem CHICKEN_ESSENCE = new BasicItem("chicken_essence");
    public static final BasicItem SCARAB_GEM = new BasicItem("scarab_gem");
    public static final BasicItem AQUATIC_SCARAB_GEM = new BasicItem("aquatic_scarab_gem");
    public static final BasicAxeItem SCARAB_AXE = new BasicAxeItem(SCARAB_MATERIAL, "scarab_axe");
    public static final BasicPickaxeItem SCARAB_PICKAXE = new BasicPickaxeItem(SCARAB_MATERIAL, "scarab_pickaxe");
    public static final BasicSwordItem SCARAB_SWORD = new BasicSwordItem(SCARAB_MATERIAL, "scarab_sword");
    public static final BasicHoeItem SCARAB_HOE = new BasicHoeItem(SCARAB_MATERIAL, "scarab_hoe");
    public static final BasicShovelItem SCARAB_SHOVEL = new BasicShovelItem(SCARAB_MATERIAL, "scarab_shovel");
    public static final BasicArmorItem SKULL_HELMET = new BasicArmorItem(BONE, 0, EntityEquipmentSlot.HEAD, "skull_helmet");
    public static final BasicArmorItem RIBCAGE_CHESTPLATE = new BasicArmorItem(BONE, 1, EntityEquipmentSlot.CHEST, "skull_chestplate");
    public static final BasicArmorItem SHIN_LEGGINGS = new BasicArmorItem(BONE, 2, EntityEquipmentSlot.LEGS, "shin_leggings");
    public static final BasicArmorItem FEET_BOOTS = new BasicArmorItem(BONE, 3, EntityEquipmentSlot.FEET, "feet_boots");
    public static final BasicItem ANCIENT_KEY = new BasicItem("ancient_key");
    public static final BasicItem ANCIENT_CLOCK = new BasicItem("ancient_clock");
    public static final BasicItem SHELL = new BasicItem("shell");
    public static final BasicFoodItem SJL = new BasicFoodItem(8, 2, false, "sjl");
    public static final BasicItem MAGIC_CONCH = new BasicItem("magic_conch");
    public static final BasicFoodItem RAW_CHICKEN_SOUP = new BasicFoodItem(4, 2, false, "raw_chicken_soup");
    public static final BasicFoodItem COOKED_CHICKEN_SOUP = new BasicFoodItem(8, 2, false, "cooked_chicken_soup");
    public static final BasicFoodItem COOKED_EGG = new BasicFoodItem(4, 2, false, "cooked_egg");
    public static final BasicItem RELIC_SCRAP = new BasicItem("relic_scrap");
    public static final BasicItem POTTERY_SHARD = new BasicItem("pottery_shard");
    public static final BasicItem PLANT_FOSSIL = new BasicItem("plant_fossil");
    public static final BasicItem SKULL_STICK = new BasicItem("skull_stick");
    public static final BasicSwordItem TOOTH_DAGGER = new BasicSwordItem(TOOTH_DAGGER_MATERIAL, "tooth_dagger");
    public static final StoneTabletItem STONE_TABLET = new StoneTabletItem();
    public static final WhipItem WHIP = new WhipItem();
    public static final ToyBallItem TOY_BALL = new ToyBallItem();
    public static final ToyScratchingPost TOY_SCRATCHING_POST = new ToyScratchingPost();
    public static final ToyTetheredLog TOY_TETHERED_LOG = new ToyTetheredLog();
    public static final JavelinItem WOODEN_JAVELIN = new JavelinItem(Item.ToolMaterial.WOOD, "wooden_javelin", false);
    public static final JavelinItem STONE_JAVELIN = new JavelinItem(Item.ToolMaterial.STONE, "stone_javelin", false);
    public static final JavelinItem IRON_JAVELIN = new JavelinItem(Item.ToolMaterial.IRON, "iron_javelin", false);
    public static final JavelinItem GOLD_JAVELIN = new JavelinItem(Item.ToolMaterial.GOLD, "gold_javelin", false);
    public static final JavelinItem DIAMOND_JAVELIN = new JavelinItem(Item.ToolMaterial.DIAMOND, "diamond_javelin", false);
    public static final JavelinItem ANCIENT_JAVELIN = new JavelinItem(null, "ancient_javelin", true);
    public static final BasicItem BROKEN_SWORD = new BasicItem("broken_sword");
    public static final BasicItem BROKEN_HELMET = new BasicItem("broken_helmet");
    public static final AncientSwordItem ANCIENT_SWORD = new AncientSwordItem();
    public static final AncientHelmetItem ANCIENT_HELMET = new AncientHelmetItem();
    public static final BasicSwordItem ICED_MEAT = new BasicSwordItem(ICED_MEAT_MATERIAL, "iced_meat");
    public static final FossilSeedsItem FOSSIL_SEED = new FossilSeedsItem(true);
    public static final BasicItem FOSSIL_SEED_FERN = new BasicItem("fossil_seed_fern");
    public static final BasicItem PALAE_SAPLING_FOSSIL = new BasicItem("fossil_sapling_palae");
    public static final FossilSeedsItem SEED = new FossilSeedsItem(false);
    public static final FernSeedItem FERN_SEED = new FernSeedItem();
    public static final Item TAR_BUCKET = new ItemBucket(FABlockRegistry.TAR).setUnlocalizedName("tar_bucket").setContainerItem(Items.BUCKET).setCreativeTab(FATabRegistry.ITEMS);
    public static final BasicItem TARDROP = new BasicItem("tardrop");
    public static final FossilRecordItem FOSSIL_RECORD_BONES = new FossilRecordItem("record_bones_desc", FASoundRegistry.RECORD_BONES, "record_bones");
    public static final FossilRecordItem FOSSIL_RECORD_NANO_ANU = new FossilRecordItem("music.anu", FASoundRegistry.MUSIC_ANU, "record_anu");
    public static final FossilRecordItem FOSSIL_RECORD_NANO_SCARAB = new FossilRecordItem("music.scarab", FASoundRegistry.MUSIC_SCARAB, "record_scarab");
    public static final FossilRecordItem FOSSIL_RECORD_NANO_DISCOVERING = new FossilRecordItem("music.discovery", FASoundRegistry.RECORD_BONES, "record_discovering");
    public static final DinosaurBoneItem LEG_BONE = new DinosaurBoneItem("leg_bone");
    public static final DinosaurBoneItem UNIQUE_ITEM = new DinosaurBoneItem("unique_item");
    public static final DinosaurBoneItem FOOT = new DinosaurBoneItem("foot");
    public static final DinosaurBoneItem SKULL = new DinosaurBoneItem("skull");
    public static final DinosaurBoneItem ARM_BONE = new DinosaurBoneItem("arm_bone");
    public static final DinosaurBoneItem RIBCAGE = new DinosaurBoneItem("ribcage");
    public static final DinosaurBoneItem VERTEBRAE = new DinosaurBoneItem("vertebrae");

    public static Item registerItem(RegistryEvent.Register<Item> event, Item item) {
        String name = item.getUnlocalizedName().substring("item.".length());
        item.setRegistryName(new ResourceLocation(Revival.MODID, name));
        event.getRegistry().register(item);
        ITEMS.add(item);
        return item;
    }

    public static Item registerItem(RegistryEvent.Register<Item> event, Item item, String registryName) {
        item.setRegistryName(new ResourceLocation(Revival.MODID, registryName));
        event.getRegistry().register(item);
        ITEMS.add(item);
        return item;
    }
}
