package fossilsarcheology;

import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.config.FossilConfig;
import fossilsarcheology.server.entity.utility.*;
import fossilsarcheology.server.message.*;
import fossilsarcheology.server.util.ReleaseType;
import net.ilexiconn.llibrary.server.config.Config;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.ilexiconn.llibrary.server.network.NetworkWrapper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Revival.MODID, name = "Fossils and Archeology Revival", version = Revival.VERSION, dependencies = "required-after:llibrary@[" + Revival.LLIBRARY_VERSION + ",)")
public class Revival {
    public static final String MODID = "fossil";
    public static final String VERSION = "8.0.0-develop";
    public static final ReleaseType RELEASE_TYPE = ReleaseType.parseVersion(VERSION);
    public static final String LLIBRARY_VERSION = "1.7.9";

    public static final Logger LOGGER = LogManager.getLogger("fossils");

    @SidedProxy(clientSide = "fossilsarcheology.client.ClientProxy", serverSide = "fossilsarcheology.server.ServerProxy")
    public static ServerProxy PROXY;
    @NetworkWrapper({MessageFoodParticles.class, MessageSetDay.class, MessageHappyParticles.class, MessageUpdateEgg.class, MessageRollBall.class})
    public static SimpleNetworkWrapper NETWORK_WRAPPER;
    @Config
    public static FossilConfig CONFIG;

    @Mod.Instance(MODID)
    public static Revival INSTANCE;
    public static Object PEDIA_OBJECT;

    public static void debug(String message) {
        if (RELEASE_TYPE.enableDebugging()) {
            LOGGER.debug(message);
        }
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        RevivalAspectRegistry.register();
        PROXY.onPreInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        PROXY.onInit();
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        PROXY.onPostInit();
    }
}
