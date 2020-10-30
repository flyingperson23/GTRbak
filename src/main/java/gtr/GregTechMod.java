package gtr;

import codechicken.lib.CodeChickenLib;
import crafttweaker.CraftTweakerAPI;
import gtr.api.GTValues;
import gtr.api.capability.SimpleCapabilityManager;
import gtr.api.cover.CoverBehaviorUIFactory;
import gtr.api.items.gui.PlayerInventoryUIFactory;
import gtr.api.metatileentity.MetaTileEntityUIFactory;
import gtr.api.model.ResourcePackHook;
import gtr.api.net.NetworkHandler;
import gtr.api.recipes.RecipeMap;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.Material;
import gtr.api.util.AnnotatedMaterialHandlerLoader;
import gtr.api.util.GTLog;
import gtr.api.util.NBTUtil;
import gtr.api.worldgen.config.WorldGenRegistry;
import gtr.common.*;
import gtr.common.asm.CustomClassWriter;
import gtr.common.blocks.MetaBlocks;
import gtr.common.blocks.modelfactories.BlockCompressedFactory;
import gtr.common.blocks.modelfactories.BlockFrameFactory;
import gtr.common.blocks.modelfactories.BlockOreFactory;
import gtr.common.command.GregTechCommand;
import gtr.common.covers.CoverBehaviors;
import gtr.common.covers.filter.FilterTypeRegistry;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.common.render.WrenchOverlayHandler;
import gtr.common.util.ResourcePackFix;
import gtr.common.worldgen.LootTableHelper;
import gtr.common.worldgen.WorldGenAbandonedBase;
import gtr.common.worldgen.WorldGenRubberTree;
import gtr.integration.multi.client.PreviewHandler;
import gtr.integration.multipart.GTMultipartFactory;
import gtr.integration.theoneprobe.TheOneProbeCompatibility;
import gtr.loaders.dungeon.DungeonLootLoader;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

@Mod(modid = GTValues.MODID,
    name = "GT: Remastered",
    acceptedMinecraftVersions = "[1.12,1.13)",
    dependencies = "required:forge@[14.23.5.2847,);" + CodeChickenLib.MOD_VERSION_DEP + "after:forestry;required:ctm;after:forgemultipartcbe;after:jei@[4.15.0,);after:crafttweaker;after:ic2;")
public class GregTechMod {

    public int counter = 0;

    static {
        FluidRegistry.enableUniversalBucket();
        if (FMLCommonHandler.instance().getSide().isClient()) {
            ResourcePackHook.init();
            BlockOreFactory.init();
            BlockCompressedFactory.init();
            BlockFrameFactory.init();
        }
    }

    @Mod.Instance(GTValues.MODID)
    public static GregTechMod instance;

    @SidedProxy(modId = GTValues.MODID, clientSide = "gtr.common.ClientProxy", serverSide = "gtr.common.CommonProxy")
    public static CommonProxy proxy;

    public static final SimpleNetworkWrapper WRENCH_NET_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("gtr.wrenchnet");
    public WrenchOverlayHandler wrenchHandler = new WrenchOverlayHandler();

    public static final SimpleNetworkWrapper DISPLAY_INFO_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("gtr.displaynet");

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void onConstruction(FMLConstructionEvent event) {
        ModContainer selfModContainer = Loader.instance().activeModContainer();
        if (selfModContainer.getSource().isDirectory()) {
            //check and fix resource pack file path as needed
            ResourcePackFix.fixResourcePackLocation(selfModContainer);
        }
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GTLog.init(event.getModLog());
        NetworkHandler.init();
        MetaTileEntityUIFactory.INSTANCE.init();
        PlayerInventoryUIFactory.INSTANCE.init();
        CoverBehaviorUIFactory.INSTANCE.init();
        SimpleCapabilityManager.init();
        OreDictUnifier.init();
        NBTUtil.registerSerializers();

        //first, register primary materials and run material handlers
        Materials.register();
        AnnotatedMaterialHandlerLoader.discoverAndLoadAnnotatedMaterialHandlers(event.getAsmData());
        Material.runMaterialHandlers();

        //then, run CraftTweaker early material registration scripts
        if (GTValues.isModLoaded(GTValues.MODID_CT)) {
            GTLog.logger.info("Running early CraftTweaker initialization scripts...");
            runEarlyCraftTweakerScripts();
        }

        //freeze material registry before processing items, blocks and fluids
        Material.freezeRegistry();

        MetaBlocks.init();
        MetaItems.init();
        MetaFluids.init();
        MetaTileEntities.init();
        MetaEntities.init();

        proxy.onPreLoad();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new WrenchOverlayHandler());
        proxy.onLoad();
        proxy.init(event);
        if (RecipeMap.isFoundInvalidRecipe()) {
            GTLog.logger.fatal("Seems like invalid recipe was found.");
            //crash if config setting is set to false, or we are in deobfuscated environment
            if (!ConfigHolder.ignoreErrorOrInvalidRecipes || !FMLForgePlugin.RUNTIME_DEOBF) {
                GTLog.logger.fatal("Loading cannot continue. Either fix or report invalid recipes, or enable ignoreErrorOrInvalidRecipes in the config as a temporary solution");
                throw new LoaderException("Found at least one invalid recipe. Please read the log above for more details.");
            } else {
                GTLog.logger.fatal("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                GTLog.logger.fatal("Ignoring invalid recipes and continuing loading");
                GTLog.logger.fatal("Some things may lack recipes or have invalid ones, proceed at your own risk");
                GTLog.logger.fatal("Report to GTR github to get more help and fix the problem");
                GTLog.logger.fatal("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

        if (GTValues.isModLoaded(GTValues.MODID_FMP)) {
            GTLog.logger.info("ForgeMultiPart found. Legacy block conversion enabled.");
            registerForgeMultipartCompat();
        }

        if (GTValues.isModLoaded(GTValues.MODID_TOP)) {
            GTLog.logger.info("TheOneProbe found. Enabling integration...");
            TheOneProbeCompatibility.registerCompatibility();
        }

        WorldGenRegistry.INSTANCE.initializeRegistry();
        GameRegistry.registerWorldGenerator(new WorldGenAbandonedBase(), 20000);
        if (!ConfigHolder.disableRubberTreeGeneration) {
            GameRegistry.registerWorldGenerator(new WorldGenRubberTree(), 10000);
        }

        LootTableHelper.initialize();
        FilterTypeRegistry.init();
        CoverBehaviors.init();
        DungeonLootLoader.init();
    }

    @Method(modid = GTValues.MODID_FMP)
    private void registerForgeMultipartCompat() {
        GTMultipartFactory.INSTANCE.registerFactory();
    }

    @Method(modid = GTValues.MODID_CT)
    private void runEarlyCraftTweakerScripts() {
        CraftTweakerAPI.tweaker.loadScript(false, "gtr");
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostLoad();
        wrenchHandler.postInit();


        GTLog.logger.log(Level.DEBUG, "Confirming ASM Transformations...");
        CustomClassWriter.customClassLoader = null;

        if (event.getSide().isClient()) PreviewHandler.init();
    }

    @Mod.EventHandler
    public void onServerLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new GregTechCommand());
    }

}