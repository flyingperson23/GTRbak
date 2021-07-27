package gtr.common;

import gtr.api.GTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GTValues.MODID)
public class ConfigHolder {

    @Config.Comment("Whether to enable more verbose logging. Default: false")
    public static boolean debug = false;

    @Config.Comment("Whether to increase number of rolls for dungeon chests. Increases dungeon loot drastically. Default: true")
    public static boolean increaseDungeonLoot = true;

    @Config.Comment("Whether to hide facades of all blocks in JEI and creative search menu. Default: true")
    @Config.RequiresMcRestart
    public static boolean hideFacadesInJEI = true;

    @Config.Comment("Whether to hide filled cells in JEI and creative search menu. Default: true")
    @Config.RequiresMcRestart
    public static boolean hideFilledCellsInJEI = true;

    @Config.Comment("Whether to hide filled tanks in JEI and creative search menu. Default: true")
    @Config.RequiresMcRestart
    public static boolean hideFilledTanksInJEI = true;

    @Config.Comment("Specifies min amount of veins in section. Default: 0")
    public static int minVeinsInSection = 0;

    @Config.Comment("Replicator UUM exponent")
    public static double uuAmplifier = 1.2;

    @Config.Comment("Replicator time exponent")
    public static double timeAmplifier = 1.2;

    @Config.Comment("Specifies additional random amount of veins in section. Default: 2")
    public static int additionalVeinsInSection = 2;

    @Config.Comment("Whether veins should be generated in center of chunk. Default: false")
    public static boolean generateVeinsInCenterOfChunk = false;

    @Config.Comment("Whether to disable vanilla ores generation in world. Default: true")
    public static boolean disableVanillaOres = true;

    @Config.Comment("Whether to disable rubber tree world generation. Default: false")
    @Config.RequiresMcRestart
    public static boolean disableRubberTreeGeneration = false;

    @Config.Comment("Whether machines should explode when overloaded with power. Default: true")
    public static boolean doExplosions = true;

    @Config.Comment("Energy use multiplier for electric items. Default: 100")
    public static int energyUsageMultiplier = 100;

    @Config.Comment("Chance of generating abandoned base in chunk = 1 / THIS_VALUE. 0 disables abandoned base generation. Default: 1000")
    public static int abandonedBaseRarity = 1000;

    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Chance with which flint and steel will create fire. Default: 50")
    public static int flintChanceToCreateFire = 50;

    @Config.Comment("If true, insufficient energy supply will reset recipe progress to zero. If false, progress will decrease to zero with 2x speed. Default: false")
    @Config.RequiresWorldRestart
    public static boolean insufficientEnergySupplyWipesRecipeProgress = false;

    @Config.Comment("RF per GTEU")
    public static int rfPerEU = 4;

    @Config.Comment("Galacticraft Joules per GTEU")
    public static double joulesPerEU = 6.4;

    @Config.Comment("Specifies priorities of mods in ore dictionary item registration. First ModID has highest priority, last - lowest. " +
        "Unspecified ModIDs follow standard sorting, but always have lower priority than last specified ModID.")
    @Config.RequiresMcRestart
    public static String[] modPriorities = {"gtr"};

    @Config.Comment("Setting this to true makes GTCE ignore error and invalid recipes that would otherwise cause crash. Default: true")
    @Config.RequiresMcRestart
    public static boolean ignoreErrorOrInvalidRecipes = true;

    @Config.Comment("Setting this to false causes GTCE to not register additional methane recipes for foods in the centrifuge. Default: true")
    @Config.RequiresMcRestart
    public static boolean addFoodMethaneRecipes = true;

    @Config.Comment("Category that contains configs for changing vanilla recipes")
    @Config.RequiresMcRestart
    public static VanillaRecipes vanillaRecipes = new VanillaRecipes();

    @Config.Comment("Category that contains configs for machines with specific behavior")
    public static MachineSpecificConfiguration machineSpecific = new MachineSpecificConfiguration();

    @Config.Comment("Category that contains configs for the NanoSaber")
    public static NanoSaberConfiguration nanoSaberConfiguration = new NanoSaberConfiguration();

    @Config.Comment("Category that contains configs for the NanoBow")
    public static NanoBowConfiguration nanoBowConfiguration = new NanoBowConfiguration();

    @Config.Comment("Sets the bonus EU output of Steam Turbines. Default: 6144")
    @Config.RequiresMcRestart
    public static int steamTurbineBonusOutput = 6144;

    @Config.Comment("Sets the bonus EU output of Plasma Turbines. Default: 6144")
    @Config.RequiresMcRestart
    public static int plasmaTurbineBonusOutput = 6144;

    @Config.Comment("Sets the bonus EU output of Gas Turbines. Default: 6144")
    @Config.RequiresMcRestart
    public static int gasTurbineBonusOutput = 6144;

    @Config.Comment("If true, powered zero loss wires will damage the player. Default: false")
    public static boolean doLosslessWiresDamage = false;

    @Config.Comment("Radius for magnet inhibitor. Default: 3")
    public static int magnetInhibitorRange = 3;

    @Config.Comment("Radius for electromagnet. Default: 8")
    public static int magnetRange = 8;

    @Config.Comment("EU used for each magnet item pickup event. Default: 256")
    public static int euPerMagnetPickup = 256;

    @Config.Comment("If true, lossless cables will have lossy wires. Default: false")
    @Config.RequiresMcRestart
    public static boolean doLosslessWiresMakeLossyCables = false;

    public static class VanillaRecipes {

        @Config.Comment("Whether to nerf the paper crafting recipe. Default: true")
        public boolean nerfPaperCrafting = true;

        @Config.Comment("Whether to make flint and steel recipe require a steel nugget instead of an iron ingot. Default: true.")
        public boolean flintAndSteelRequireSteel = true;

        @Config.Comment("Whether to nerf wood crafting to 2 planks from 1 log. Default: false")
        public boolean nerfWoodCrafting = false;

        @Config.Comment("Whether to nerf wood crafting to 2 sticks from 2 planks. Default: false")
        public boolean nerfStickCrafting = false;

        @Config.Comment("Whether to make the iron bucket recipe harder by requiring a hammer and plates. Default: true")
        public boolean bucketRequirePlatesAndHammer = true;

        @Config.Comment("Recipes for items like iron doors, trapdoors, pressure plates, cauldrons, hoppers, and iron bars require iron plates and a hammer. Default: true")
        public boolean ironConsumingCraftingRecipesRequirePlates = true;

        @Config.Comment("Whether crafting a bowl requires a knife instead of only planks. Default: true")
        public boolean bowlRequireKnife = true;
    }

    public static class MachineSpecificConfiguration {
        @Config.Comment("Array of blacklisted dimension IDs in which Air Collector does not work.")
        public int[] airCollectorDimensionBlacklist = new int[]{};
    }

    public static class NanoSaberConfiguration {

        @Config.RangeDouble(min = 0, max = Integer.MAX_VALUE)
        @Config.Comment("The multiplier to the damage when sword is powered. Default: 3.0")
        @Config.RequiresMcRestart
        public double nanoSaberDamageMultiplier = 3;

        @Config.RangeDouble(min = 0, max = Integer.MAX_VALUE)
        @Config.Comment("The base damage of the NanoSaber. Default: 16.0")
        @Config.RequiresMcRestart
        public double nanoSaberBaseDamage = 16;

        @Config.Comment("Should Zombies spawn with charged, active NanoSabers on hard difficulty? Default: true")
        public boolean zombieSpawnWithSabers = true;

        @Config.RangeInt(min = 1, max = 512)
        @Config.Comment("The EU/t consumption of the NanoSaber. Default: 64")
        @Config.RequiresMcRestart
        public int energyConsumption = 64;
    }



    public static class NanoBowConfiguration {

        @Config.RangeDouble(min = 0, max = Integer.MAX_VALUE)
        @Config.Comment("The base damage of the NanoBow arrows. Default: 24.0")
        @Config.RequiresMcRestart
        public double nanoBowBaseDamage = 24;

        @Config.RangeInt(min = 1, max = 16384)
        @Config.Comment("The EU/shot consumption of the NanoBow. Default: 64")
        @Config.RequiresMcRestart
        public int energyConsumption = 6400;
    }


    @Config.Comment({"Sets HUD location", "1 - left-upper conrer", "2 - right-upper corner", "3 - left-bottom corner", "4 - right-bottom corner"})
    public static byte hudLocation = 1;
    @Config.Comment("Horizontal offset of HUD [0 ~ 100)")
    public static byte hudOffsetX = 0;
    @Config.Comment("Vertical ooffset of HUD [0 ~ 100)")
    public static byte hudOffsetY = 0;

    @Config.Comment("Config options for GregsConstruct features")
    public static GregsConstruct GregsConstruct = new GregsConstruct();

    public static class GregsConstruct {

        @Config.Comment("Enable/Disable all GregsConstruct features")
        @Config.Name("Enable Greg's Construct")
        @Config.RequiresMcRestart
        public boolean EnableGregsConstruct = true;

        @Config.Comment("Add Tools with GT Metals to Tinkers")
        @Config.Name("Tinker's metal tools")
        @Config.RequiresMcRestart
        public boolean TinkersMetalTools = true;

        @Config.Comment("Add Tools with GT Gems to Tinkers")
        @Config.Name("Tinker's gem tools")
        @Config.RequiresMcRestart
        public boolean TinkersGemTools = true;

        @Config.Comment("Add Smelting for GT Materials to Tinkers Smeltery")
        @Config.Name("Tinker's material smelting")
        @Config.RequiresMcRestart
        public boolean TinkersMaterialsSmelting = true;

        @Config.Comment("Add Alloying of GT Materials to Tinkers Smeltery")
        @Config.Name("Tinker's material alloying")
        @Config.RequiresMcRestart
        public boolean TinkersMaterialAlloying = true;

        @Config.Comment("Enable Glass recipe changes")
        @Config.Name("Greg's Construct glass processing")
        @Config.RequiresMcRestart
        public boolean GregsConstructGlassProcessing = true;

        @Config.Comment("Whether or not to register fluid solidification recipes for parts")
        @Config.Name("Fluid solidification recipes for parts")
        public boolean castingRecipes = true;
    }
}
