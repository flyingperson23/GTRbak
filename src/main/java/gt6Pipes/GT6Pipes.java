package gt6Pipes;

import gt6Pipes.compat.*;
import gt6Pipes.compat.Energy;
import gt6Pipes.compat.Fluid;
import gt6Pipes.compat.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GT6Pipes {
    // So uh yeah the reason all this is here is that I may or may not have already coded a standalone mod to do exactly this and I'm entirely too
    // lazy to figure out how to do it properly so fuck it imma just include a stripped version of Better Pipes (go check it out it's great)



    public static Logger logger;

    public static GT6Pipes instance = new GT6Pipes();

    public static final SimpleNetworkWrapper BETTER_PIPES_NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("gt6pipes");

    public int counter = 0;

    public ArrayList<BlockPos> wrenchMap = new ArrayList<>();

    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    public void init() {
        MinecraftForge.EVENT_BUS.register(new GT6PipesEventHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void postInit() {
        COMPAT_LIST.add(new Item());
        COMPAT_LIST.add(new Fluid());
        COMPAT_LIST.add(new Energy());
        WRENCH_PROVIDERS.add(new GTCEWrenchProvider());
    }

    public ArrayList<ICompatBase> COMPAT_LIST = new ArrayList<>();
    public ArrayList<IWrenchProvider> WRENCH_PROVIDERS = new ArrayList<>();
}
