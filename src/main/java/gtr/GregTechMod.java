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
import gtr.common.input.Keybinds;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.common.util.ResourcePackFix;
import gtr.common.worldgen.LootTableHelper;
import gtr.common.worldgen.WorldGenAbandonedBase;
import gtr.common.worldgen.WorldGenRubberTree;
import gtr.integration.betterpipes.compat.ICompatBase;
import gtr.integration.betterpipes.compat.gtce.CompatGTCEEnergy;
import gtr.integration.betterpipes.compat.gtce.CompatGTCEFluid;
import gtr.integration.betterpipes.compat.gtce.CompatGTCEItem;
import gtr.integration.betterpipes.compat.wrench.GTCEWrenchProvider;
import gtr.integration.betterpipes.compat.wrench.IWrenchProvider;
import gtr.integration.betterpipes.network.MessageGetConnections;
import gtr.integration.betterpipes.network.MessagePlaySound;
import gtr.integration.betterpipes.network.MessageReturnConnections;
import gtr.integration.betterpipes.network.MessageSwingArm;
import gtr.integration.multi.client.PreviewHandler;
import gtr.integration.theoneprobe.TheOneProbeCompatibility;
import gtr.integration.tinkers.TinkersMaterials;
import gtr.loaders.dungeon.DungeonLootLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.classloading.FMLForgePlugin;
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

import java.util.ArrayList;

@Mod(modid = GTValues.MODID,
    name = "GT: Remastered",
    acceptedMinecraftVersions = "[1.12,1.13)",
    dependencies = "required:forge@[14.23.5.2847,);" + CodeChickenLib.MOD_VERSION_DEP + "after:forestry;after:tconstruct;required:ctm;after:forgemultipartcbe;after:jei@[4.15.0,);after:crafttweaker;after:ic2;")
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

    public ArrayList<ICompatBase> COMPAT_LIST = new ArrayList<>();
    public ArrayList<IWrenchProvider> WRENCH_PROVIDERS = new ArrayList<>();
    public ArrayList<BlockPos> wrenchMap = new ArrayList<>();

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

        Keybinds.register();

        if (ConfigHolder.GregsConstruct.EnableGregsConstruct && Loader.isModLoaded("tconstruct"))
            TinkersMaterials.preInit();

    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {

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


        WRENCH_NET_WRAPPER.registerMessage(MessageGetConnections.MessageHandler.class, MessageGetConnections.class, 0, Side.SERVER);
        WRENCH_NET_WRAPPER.registerMessage(MessageReturnConnections.MessageHandler.class, MessageReturnConnections.class, 1, Side.CLIENT);
        WRENCH_NET_WRAPPER.registerMessage(MessagePlaySound.MessageHandler.class, MessagePlaySound.class, 2, Side.CLIENT);
        WRENCH_NET_WRAPPER.registerMessage(MessageSwingArm.MessageHandler.class, MessageSwingArm.class, 3, Side.CLIENT);

    }

    @Method(modid = GTValues.MODID_CT)
    private void runEarlyCraftTweakerScripts() {
        CraftTweakerAPI.tweaker.loadScript(false, "gtr");
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostLoad();


        GTLog.logger.log(Level.DEBUG, "Confirming ASM Transformations...");
        CustomClassWriter.customClassLoader = null;

        if (event.getSide().isClient()) PreviewHandler.init();

        COMPAT_LIST.add(new CompatGTCEItem());
        COMPAT_LIST.add(new CompatGTCEFluid());
        COMPAT_LIST.add(new CompatGTCEEnergy());
        WRENCH_PROVIDERS.add(new GTCEWrenchProvider());
    }

    @Mod.EventHandler
    public void onServerLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new GregTechCommand());
    }

}