package me.towdium.jecalculation;

import mcp.MethodsReturnNonnullByDefault;
import me.towdium.jecalculation.network.IProxy;
import me.towdium.jecalculation.network.packets.PCalculator;
import me.towdium.jecalculation.network.packets.PEdit;
import me.towdium.jecalculation.network.packets.PRecord;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

/**
 * @author Towdium
 */
@Mod.EventBusSubscriber
@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@Mod(
        modid = JustEnoughCalculation.Reference.MODID,
        name = JustEnoughCalculation.Reference.MODNAME,
        version = JustEnoughCalculation.Reference.VERSION,
        dependencies = "required-after:jei@[4.15.0.268,)"
        //clientSideOnly = true
)
public class JustEnoughCalculation {
    @Mod.Instance(JustEnoughCalculation.Reference.MODID)
    public static JustEnoughCalculation instance;
    @SidedProxy(modId = "jecalculation",
            clientSide = "me.towdium.jecalculation.network.ProxyClient",
            serverSide = "me.towdium.jecalculation.network.ProxyCommon")
    public static IProxy proxy;
    public static SimpleNetworkWrapper network;
    public static Logger logger = LogManager.getLogger(Reference.MODID);

    @NetworkCheckHandler
    public static boolean networkCheck(Map<String, String> mods, Side s) {
        if (s == Side.SERVER) return true;
        else return mods.containsKey(Reference.MODID);
    }

    @Mod.EventHandler
    public static void initPre(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        network.registerMessage(PCalculator.Handler.class, PCalculator.class, 0, Side.SERVER);
        network.registerMessage(PRecord.Handler.class, PRecord.class, 1, Side.CLIENT);
        network.registerMessage(PEdit.Handler.class, PEdit.class, 2, Side.SERVER);
        proxy.initPre();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public static void initPost(FMLPostInitializationEvent event) {
        proxy.initPost();
    }

    public static class Reference {
        public static final String MODID = "jecalculation";
        public static final String MODNAME = "Just Enough Calculation";
        public static final String VERSION = "@VERSION@";
    }
}
