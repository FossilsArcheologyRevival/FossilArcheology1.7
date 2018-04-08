	package fossilsarcheology.server.block;

    import fossilsarcheology.Revival;
import fossilsarcheology.server.api.BlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
    import net.minecraftforge.event.RegistryEvent;
    import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FABlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    @GameRegistry.ObjectHolder(Revival.MODID + ":fossil")
    public static final FossilBlock FOSSIL = new FossilBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":amber_ore")
    public static final AmberOreBlock AMBER_ORE = new AmberOreBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":skull")
    public static final SkullBlock SKULL_BLOCK = new SkullBlock(false);
    @GameRegistry.ObjectHolder(Revival.MODID + ":skull_lantern")
    public static final SkullBlock SKULL_LANTERN = new SkullBlock(true);
    @GameRegistry.ObjectHolder(Revival.MODID + ":culture_vat_idle")
    public static final CultivateBlock CULTIVATE_IDLE = new CultivateBlock(false);
    @GameRegistry.ObjectHolder(Revival.MODID + ":culture_vat_active")
    public static final CultivateBlock CULTIVATE_ACTIVEE = new CultivateBlock(true);
    @GameRegistry.ObjectHolder(Revival.MODID + ":worktable_idle")
    public static final WorktableBlock WORKTABLE_IDLE = new WorktableBlock(false);
    @GameRegistry.ObjectHolder(Revival.MODID + ":worktable_active")
    public static final WorktableBlock WORKTABLE_ACTIVE = new WorktableBlock(true);
    @GameRegistry.ObjectHolder(Revival.MODID + ":sifter_idle")
    public static final SifterBlock SIFTER_IDLE = new SifterBlock(false);
    @GameRegistry.ObjectHolder(Revival.MODID + ":sifter_active")
    public static final SifterBlock SIFTER_ACTIVE = new SifterBlock(true);
    @GameRegistry.ObjectHolder(Revival.MODID + ":analyzer_idle")
    public static final AnalyzerBlock ANALYZER = new AnalyzerBlock(false);
    @GameRegistry.ObjectHolder(Revival.MODID + ":analyzer_active")
    public static final AnalyzerBlock ANALYZER_ACTIVE = new AnalyzerBlock(true);
    @GameRegistry.ObjectHolder(Revival.MODID + ":bubble_machine")
    public static final BubbleBlowerBlock BUBBLE_MACHINE = new BubbleBlowerBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":feeder")
    public static final FeederBlock FEEDER = new FeederBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":permafrost")
    public static final PermafrostBlock PERMAFROST = new PermafrostBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":iced_stone")
    public static final IcedStoneBlock ICED_STONE = new IcedStoneBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":dense_sand")
    public static final DenseSandBlock DENSE_SAND = new DenseSandBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":strong_glass")
    public static final StrongGlassBlock STRONG_GLASS = new StrongGlassBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_glass")
    public static final AncientGlassBlock ANCIENT_GLASS = new AncientGlassBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":slime_trail")
    public static final SlimeTrailBlock SLIME_TRAIL = new SlimeTrailBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_stone")
    public static final AncientStoneBlock ANCIENT_STONE = new AncientStoneBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_stone_bricks")
    public static final AncientStonebrickBlock ANCIENT_STONE_BRICK = new AncientStonebrickBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_stone_stairs")
    public static final FossilStairsBlock ANCIENT_STONE_STAIRS = new FossilStairsBlock(ANCIENT_STONE.getDefaultState(), "ancientStoneStairs");
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_stone_slab")
    public static final FossilSlabBlock ANCIENT_STONE_SINGLESLAB = new AncientStoneSlabBlock.Half("ancientStoneSlab", 1.7F, 7.5F, SoundType.STONE);
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_stone_double_slab")
    public static final FossilSlabBlock ANCIENT_STONE_DOUBLESLAB = new AncientStoneSlabBlock.Double("ancientStoneSlab", 1.7F, 7.5F, SoundType.STONE);
    @GameRegistry.ObjectHolder(Revival.MODID + ":volcanic_ash")
    public static final VolcanicAshBlock VOLCANIC_ASH = new VolcanicAshBlock("ash");
    @GameRegistry.ObjectHolder(Revival.MODID + ":volcanic_brick")
    public static final VolcanicAshBlock VOLCANIC_BRICK = new VolcanicAshBlock("brick");
    @GameRegistry.ObjectHolder(Revival.MODID + ":volcanic_rock")
    public static final VolcanicAshBlock VOLCANIC_ROCK = new VolcanicAshBlock("rock");
    @GameRegistry.ObjectHolder(Revival.MODID + ":volcanic_stairs")
    public static final FossilStairsBlock VOLCANIC_STAIRS = new FossilStairsBlock(VOLCANIC_BRICK.getDefaultState(), "volcanicStairs");
    @GameRegistry.ObjectHolder(Revival.MODID + ":volcanic_double_slab")
    public static final FossilSlabBlock VOLCANIC_DOUBLESLAB = new VolcanicSlabBlock.Double("volcanicSlab", 1.4F, 7.5F, SoundType.STONE);
    @GameRegistry.ObjectHolder(Revival.MODID + ":volcanic_slab")
    public static final FossilSlabBlock VOLCANIC_SINGLESLAB = new VolcanicSlabBlock.Half("volcanicSlab", 1.4F, 7.5F, SoundType.STONE);
    @GameRegistry.ObjectHolder(Revival.MODID + ":anu_portal")
    public static final AnuPortalBlock ANU_PORTAL = new AnuPortalBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":anu_statue")
    public static final AnuStatueBlock ANU_STATUE = new AnuStatueBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":home_portal")
    public static final HomePortalBlock HOME_PORTAL = new HomePortalBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":anubite_statue")
    public static final AnubiteStatueBlock ANUBITE_STATUE = new AnubiteStatueBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_chest")
    public static final AncientChestBlock ANCIENT_CHEST = new AncientChestBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":sarcophagus")
    public static final SarcophagusBlock SARCOPHAGUS = new SarcophagusBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":figurine")
    public static final FigurineBlock FIGURINE = new FigurineBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":amphora_vase")
    public static final AmphoraVaseBlock AMPHORA_VASE = new AmphoraVaseBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":kylix_vase")
    public static final KylixVaseBlock KYLIX_VASE = new KylixVaseBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":volute_vase")
    public static final VoluteVaseBlock VOLUTE_VASE = new VoluteVaseBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":time_machine")
    public static final TimeMachineBlock TIME_MACHINE = new TimeMachineBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":drum")
    public static final DrumBlock DRUM = new DrumBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":tar")
    public static final TarBlock TAR = new TarBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":obsidian_spikes")
    public static final ObsidianSpikesBlock OBSIDIAN_SPIKES = new ObsidianSpikesBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_log")
    public static final PalmLogBlock PALM_LOG = new PalmLogBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_leaves")
    public static final PalmLeavesBlock PALM_LEAVES = new PalmLeavesBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_sapling")
    public static final PalmSaplingBlock PALM_SAPLING = new PalmSaplingBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_planks")
    public static final PalmPlanksBlock PALM_PLANKS = new PalmPlanksBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_planks_double_slab")
    public static final FossilSlabBlock PALM_PLANKS_DOUBLESLAB = new PalmPlanksSlabBlock.Double("palmPlanksSlab", 1.4F, 7.5F, SoundType.WOOD);
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_planks_slab")
    public static final FossilSlabBlock PALM_PLANKS_SINGLESLAB = new PalmPlanksSlabBlock.Half("palmPlanksSlab", 1.4F, 7.5F, SoundType.WOOD);
    @GameRegistry.ObjectHolder(Revival.MODID + ":palm_planks_stairs")
    public static final FossilStairsBlock PALM_PLANKS_STAIRS = new FossilStairsBlock(PALM_PLANKS.getDefaultState(), "palmStairs");

    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_wood")
    public static final AncientWoodBlock ANCIENT_WOOD = new AncientWoodBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_wood_pillar")
    public static final AncientWoodPillarBlock ANCIENT_WOOD_PILLAR = new AncientWoodPillarBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_wood_plate")
    public static final AncientWoodPlateBlock ANCIENT_WOOD_PLATE = new AncientWoodPlateBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_wood_stairs")
    public static final FossilStairsBlock ANCIENT_WOOD_STAIRS = new FossilStairsBlock(ANCIENT_WOOD.getDefaultState(), "ancient_wood_stairs");
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_wood_double_slab")
    public static final FossilSlabBlock ANCIENT_WOOD_DOUBLESLAB = new AncientWoodSlabBlock.Double("ancientWoodSlab", 1.4F, 7.5F, SoundType.WOOD);
    @GameRegistry.ObjectHolder(Revival.MODID + ":ancient_wood_slab")
    public static final FossilSlabBlock ANCIENT_WOOD_SINGLESLAB = new AncientWoodSlabBlock.Half("ancientWoodSlab", 1.4F, 7.5F, SoundType.WOOD);

    @GameRegistry.ObjectHolder(Revival.MODID + ":ferns")
    public static final FernsBlock FERNS = new FernsBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":dillhoffia")
    public static final DillhoffiaFlowerBlock DILLHOFFIA_FLOWER = new DillhoffiaFlowerBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":sarracenia")
    public static final TallFlowerBlock SARRACENIA_FLOWER = new TallFlowerBlock("sarracenia");
    @GameRegistry.ObjectHolder(Revival.MODID + ":cephalotaxus")
    public static final ShortFlowerBlock CEPHALOTAXUS_FLOWER = new ShortFlowerBlock("cephalotaxus");
    @GameRegistry.ObjectHolder(Revival.MODID + ":licopodiophyta")
    public static final ShortFlowerBlock LICOPODIOPHYTA_FLOWER = new ShortFlowerBlock("licopodiophyta");
    @GameRegistry.ObjectHolder(Revival.MODID + ":paleopanax")
    public static final TallFlowerBlock PALEOPANAX_FLOWER = new TallFlowerBlock("paleopanax");
    @GameRegistry.ObjectHolder(Revival.MODID + ":zamites")
    public static final ShortFlowerBlock ZAMITES_FLOWER = new ShortFlowerBlock("zamites");
    @GameRegistry.ObjectHolder(Revival.MODID + ":bennettitales_small")
    public static final ShortFlowerBlock BENNETTITALES_SMALL_FLOWER = new ShortFlowerBlock("bennettitales_small");
    @GameRegistry.ObjectHolder(Revival.MODID + ":bennettitales_large")
    public static final TallFlowerBlock BENNETTITALES_LARGE_FLOWER = new TallFlowerBlock("bennettitales_large");
    @GameRegistry.ObjectHolder(Revival.MODID + ":welwitschia")
    public static final ShortFlowerBlock WELWITSCHIA_FLOWER = new ShortFlowerBlock("welwitschia");
    @GameRegistry.ObjectHolder(Revival.MODID + ":horsetail_small")
    public static final ShortFlowerBlock HORSETAIL_SMALL_FLOWER = new ShortFlowerBlock("horsetail_small");
    @GameRegistry.ObjectHolder(Revival.MODID + ":horsetail_large")
    public static final TallFlowerBlock HORSETAIL_LARGE_FLOWER = new TallFlowerBlock("horsetail_large");
    @GameRegistry.ObjectHolder(Revival.MODID + ":mutant_plant")
    public static final TallFlowerBlock MUTANT_FLOWER = new TallFlowerBlock("mutant_plant");
    @GameRegistry.ObjectHolder(Revival.MODID + ":tempskya")
    public static final TempskyaBlock TEMPSKYA_FLOWER = new TempskyaBlock();
    @GameRegistry.ObjectHolder(Revival.MODID + ":vaccinium")
    public static final ShortFlowerBlock VACCINIUM_FLOWER = new ShortFlowerBlock("vaccinium");
    @GameRegistry.ObjectHolder(Revival.MODID + ":osmunda")
    public static final ShortFlowerBlock OSMUNDA_FLOWER = new ShortFlowerBlock("osmunda");
    @GameRegistry.ObjectHolder(Revival.MODID + ":crataegus")
    public static final TallFlowerBlock CRATAEGUS_FLOWER = new TallFlowerBlock("crataegus");
    @GameRegistry.ObjectHolder(Revival.MODID + ":florissantia")
    public static final ShortFlowerBlock FLORISSANTIA_FLOWER = new ShortFlowerBlock("florissantia");
    @GameRegistry.ObjectHolder(Revival.MODID + ":ependra")
    public static final ShortFlowerBlock EPENDRA_FLOWER = new ShortFlowerBlock("ependra");


    public static void registerBlock(RegistryEvent.Register<Block> event, Block block) {
        String name = block.getUnlocalizedName().substring("tile.".length());
        if(block.getRegistryName() == null){
            ResourceLocation identifier = new ResourceLocation(Revival.MODID, name);
            block.setRegistryName(identifier);
        }
        event.getRegistry().register(block);
        BLOCKS.add(block);

    }
}
