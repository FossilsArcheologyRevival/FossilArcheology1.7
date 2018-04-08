package fossilsarcheology.server;

import fossilsarcheology.Revival;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.achievement.FossilAchievements;
import fossilsarcheology.server.api.BlockEntity;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.FAFluidRegistry;
import fossilsarcheology.server.block.IBlockItem;
import fossilsarcheology.server.block.ISlabItem;
import fossilsarcheology.server.block.entity.*;
import fossilsarcheology.server.block.entity.block.TileEntityVolute;
import fossilsarcheology.server.container.*;
import fossilsarcheology.server.entity.EntityFishBase;
import fossilsarcheology.server.entity.FAEntityRegistry;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.event.FossilBonemealEvent;
import fossilsarcheology.server.event.FossilCraftingEvent;
import fossilsarcheology.server.event.FossilLivingEvent;
import fossilsarcheology.server.event.FossilPickupItemEvent;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.recipe.FAOreDictRegistry;
import fossilsarcheology.server.recipe.FARecipeRegistry;
import fossilsarcheology.server.util.FossilFoodMappings;
import fossilsarcheology.server.world.FAWorldGenerator;
import fossilsarcheology.server.world.FAWorldRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class ServerProxy implements IGuiHandler {
    public static final int GUI_ANALYZER = 0;
    public static final int GUI_CULTIVATE = 1;
    public static final int GUI_FEEDER = 2;
    public static final int GUI_WORKTABLE = 3;
    public static final int GUI_SIFTER = 4;
    public static final int GUI_TIME_MACHINE = 5;
    public static final int GUI_DINOPEDIA = 6;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        try {
            for (Field f : FABlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block) {
                    FABlockRegistry.registerBlock(event, (Block) obj);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        FABlockRegistry.registerBlock(event, block);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        GameRegistry.registerTileEntity(TileEntityCultivate.class, "fossil.cultivate");
        GameRegistry.registerTileEntity(TileEntityFeeder.class, "fossil.feeder");
        GameRegistry.registerTileEntity(TileEntityWorktable.class, "fossil.archeology_workbench");
        GameRegistry.registerTileEntity(AnalyzerBlockEntity.class, "fossil.analyzer");
        GameRegistry.registerTileEntity(TileEntityAncientChest.class, "fossil.ancient_chest");
        GameRegistry.registerTileEntity(TileEntityAnubiteStatue.class, "fossil.anubite");
        GameRegistry.registerTileEntity(TileEntityAnuStatue.class, "fossil.anu_statue");
        GameRegistry.registerTileEntity(TileEntityFigurine.class, "fossil.figurine");
        GameRegistry.registerTileEntity(TileEntityKylix.class, "fossil.kylix");
        GameRegistry.registerTileEntity(TileEntitySarcophagus.class, "fossil.sarcophagus");
        GameRegistry.registerTileEntity(TileEntitySifter.class, "fossil.sifter");
        GameRegistry.registerTileEntity(TileEntityAmphora.class, "fossil.amphora");
        GameRegistry.registerTileEntity(TileEntityTimeMachine.class, "fossil.time_machine");
        GameRegistry.registerTileEntity(TileEntityVolute.class, "fossil.volute");


    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        try {
            for (Field f : FAItemRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Item) {
                    FAItemRegistry.registerItem(event, (Item) obj);
                } else if (obj instanceof Item[]) {
                    for (Item item : (Item[]) obj) {
                        FAItemRegistry.registerItem(event, item);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            for (Field f : FABlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block) {
                    registerItemBlocks((Block)obj, event);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        registerItemBlocks(block, event);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        PrehistoricEntityType.register(event);

    }

    private static void registerItemBlocks(Block block, RegistryEvent.Register<Item> event){
        if(block instanceof IBlockItem){
            ItemBlock itemBlock = new ItemBlock(block);
            if (IBlockItem.class.isAssignableFrom(((IBlockItem)block).getItemBlockClass())) {
                try {
                    String name = itemBlock.getUnlocalizedName().substring("item.".length());
                    itemBlock.setRegistryName(new ResourceLocation(Revival.MODID, name));
                    event.getRegistry().register(itemBlock);
                    itemBlock = ((IBlockItem)block).getItemBlockClass().getDeclaredConstructor(World.class).newInstance(block);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
            String name = itemBlock.getUnlocalizedName().substring("item.".length());
            itemBlock.setRegistryName(new ResourceLocation(Revival.MODID, name));
            event.getRegistry().register(itemBlock);
            event.getRegistry().register(itemBlock);
        }else if(block instanceof ISlabItem){
            ItemBlock itemBlock = ((ISlabItem)block).getItemBlock();
            String name = itemBlock.getUnlocalizedName().substring("item.".length());
            itemBlock.setRegistryName(new ResourceLocation(Revival.MODID, name));
            event.getRegistry().register(itemBlock);
        }else{
            ItemBlock itemBlock = new ItemBlock(block);
            String name = itemBlock.getUnlocalizedName().substring("item.".length());
            itemBlock.setRegistryName(new ResourceLocation(Revival.MODID, name));
            event.getRegistry().register(itemBlock);
        }
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        try {
            for (Field f : FASoundRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof SoundEvent) {
                    event.getRegistry().register((SoundEvent)obj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void registerBiome(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(FAWorldRegistry.ANU_BIOME.setRegistryName("Lair of Darkness"));
        event.getRegistry().register(FAWorldRegistry.TREASURE_BIOME.setRegistryName("Treasure"));

    }

    public void onPreInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(Revival.INSTANCE, this);
        FAFluidRegistry.register();
        FAEntityRegistry.register();
        FAOreDictRegistry.register();
        FossilFoodMappings.register();
        FAWorldRegistry.register();


    }

    public void onInit() {
        MinecraftForge.EVENT_BUS.register(new FossilCraftingEvent());
        MinecraftForge.EVENT_BUS.register(new FossilPickupItemEvent());
        MinecraftForge.EVENT_BUS.register(new FossilBonemealEvent());
        MinecraftForge.EVENT_BUS.register(new FossilLivingEvent());
        MinecraftForge.TERRAIN_GEN_BUS.register(new FAWorldGenerator());
    }

    public void calculateChainBuffer(EntityFishBase entity) {

    }

    public void calculateChainBuffer(EntityPrehistoric entity) {

    }

    public void onPostInit() {

    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (id == GUI_ANALYZER) {
            return new AnalyzerContainer(player.inventory, (AnalyzerBlockEntity) world.getTileEntity(pos));
        }
        if (id == GUI_CULTIVATE) {
            return new CultivateContainer(player.inventory, (TileEntityCultivate) world.getTileEntity(pos));
        }
        if (id == GUI_FEEDER) {
            return new FeederContainer(player.inventory, (TileEntityFeeder) world.getTileEntity(pos));
        }
        if (id == GUI_WORKTABLE) {
            return new WorktableContainer(player.inventory, (TileEntityWorktable) world.getTileEntity(pos));
        }
        if (id == GUI_SIFTER) {
            return new SifterContainer(player.inventory, (TileEntitySifter) world.getTileEntity(pos));
        }
        if (id == GUI_TIME_MACHINE) {
            return new TimeMachineContainer(player.inventory, (TileEntityTimeMachine) world.getTileEntity(pos));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void spawnPacketItemParticles(World worldObj, float f, float f1, float f2, double motionX, double motionY, double motionZ, Item item) {

    }

    public void spawnPacketBlockParticles(World worldObj, float f, float f1, float f2, double motionX, double motionY, double motionZ, Block block) {
    }

    public void spawnPacketHeartParticles(World worldObj, float f, float f1, float f2, double motionX, double motionY, double motionZ) {
    }

    public void spawnBubbleParticles(World world, float f, float f1, float f2, double motionX, double motionY, double motionZ) {
    }

    public void spawnAnuParticle(World world, double posX, double posY, double posZ) {
    }

    public void playSound(SoundEvent sound) {
    }

    public void stopSound(SoundEvent sound) {
    }

    public net.minecraft.client.model.ModelBiped getArmorModel(int id) {
        return null;
    }
}
