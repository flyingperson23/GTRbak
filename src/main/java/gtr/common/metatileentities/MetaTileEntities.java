package gtr.common.metatileentities;

import gtr.api.GTValues;
import gtr.api.GregTechAPI;
import gtr.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gtr.api.metatileentity.SimpleMachineMetaTileEntity;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.machines.*;
import gtr.api.render.Textures;
import gtr.api.unification.material.Materials;
import gtr.api.util.GTLog;
import gtr.common.metatileentities.electric.*;
import gtr.common.metatileentities.electric.multiblockpart.*;
import gtr.common.metatileentities.multi.MetaTileEntityCokeOven;
import gtr.common.metatileentities.multi.MetaTileEntityCokeOvenHatch;
import gtr.common.metatileentities.multi.MetaTileEntityLargeBoiler;
import gtr.common.metatileentities.multi.MetaTileEntityLargeBoiler.BoilerType;
import gtr.common.metatileentities.multi.MetaTileEntityPrimitiveBlastFurnace;
import gtr.common.metatileentities.multi.electric.*;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityDieselEngine;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine.TurbineType;
import gtr.common.metatileentities.steam.*;
import gtr.common.metatileentities.steam.boiler.SteamCoalBoiler;
import gtr.common.metatileentities.steam.boiler.SteamLavaBoiler;
import gtr.common.metatileentities.steam.boiler.SteamSolarBoiler;
import gtr.common.metatileentities.storage.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

@SuppressWarnings("WeakerAccess")
public class MetaTileEntities {

    //HULLS
    public static MetaTileEntityHull[] HULL = new MetaTileEntityHull[GTValues.V.length];
    public static MetaTileEntityTransformer[] TRANSFORMER = new MetaTileEntityTransformer[3];
    public static MetaTileEntityBatteryBuffer[][] BATTERY_BUFFER = new MetaTileEntityBatteryBuffer[4][];
    public static MetaTileEntityCharger[] CHARGER = new MetaTileEntityCharger[GTValues.V.length];

    //BRONZE MACHINES SECTION
    public static SteamCoalBoiler STEAM_BOILER_COAL_BRONZE;
    public static SteamCoalBoiler STEAM_BOILER_COAL_STEEL;
    public static SteamSolarBoiler STEAM_BOILER_SOLAR_BRONZE;
    public static SteamLavaBoiler STEAM_BOILER_LAVA_BRONZE;
    public static SteamLavaBoiler STEAM_BOILER_LAVA_STEEL;
    public static SteamExtractor STEAM_EXTRACTOR_BRONZE;
    public static SteamExtractor STEAM_EXTRACTOR_STEEL;
    public static SteamMacerator STEAM_MACERATOR_BRONZE;
    public static SteamMacerator STEAM_MACERATOR_STEEL;
    public static SteamCompressor STEAM_COMPRESSOR_BRONZE;
    public static SteamCompressor STEAM_COMPRESSOR_STEEL;
    public static SteamHammer STEAM_HAMMER_BRONZE;
    public static SteamHammer STEAM_HAMMER_STEEL;
    public static SteamFurnace STEAM_FURNACE_BRONZE;
    public static SteamFurnace STEAM_FURNACE_STEEL;
    public static SteamAlloySmelter STEAM_ALLOY_SMELTER_BRONZE;
    public static SteamAlloySmelter STEAM_ALLOY_SMELTER_STEEL;

    //SIMPLE MACHINES SECTION
    public static SimpleMachineMetaTileEntity[] ELECTRIC_FURNACE = new SimpleMachineMetaTileEntity[4];
    public static MetaTileEntityMacerator[] MACERATOR = new MetaTileEntityMacerator[4];
    public static SimpleMachineMetaTileEntity[] ALLOY_SMELTER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] ARC_FURNACE = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] ASSEMBLER = new SimpleMachineMetaTileEntity[5];
    public static SimpleMachineMetaTileEntity[] AUTOCLAVE = new SimpleMachineMetaTileEntity[5];
    public static SimpleMachineMetaTileEntity[] BENDER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] BREWERY = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] CANNER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] CENTRIFUGE = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] CHEMICAL_BATH = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] CHEMICAL_REACTOR = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] COMPRESSOR = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] CUTTER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] DISTILLERY = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] ELECTROLYZER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] ELECTROMAGNETIC_SEPARATOR = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] EXTRACTOR = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] EXTRUDER = new SimpleMachineMetaTileEntity[3];
    public static SimpleMachineMetaTileEntity[] FERMENTER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] FLUID_CANNER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] FLUID_EXTRACTOR = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] FLUID_HEATER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] FLUID_SOLIDIFIER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] FORGE_HAMMER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] FORMING_PRESS = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] LATHE = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] MICROWAVE = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] MIXER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] ORE_WASHER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] PACKER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] UNPACKER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] PLASMA_ARC_FURNACE = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] POLARIZER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] LASER_ENGRAVER = new SimpleMachineMetaTileEntity[5];
    public static SimpleMachineMetaTileEntity[] SIFTER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] THERMAL_CENTRIFUGE = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] WIREMILL = new SimpleMachineMetaTileEntity[4];
    public static MetaTileEntityMassFab[] MASS_FAB = new MetaTileEntityMassFab[5];
    public static SimpleMachineMetaTileEntity[] CROP_GENE_EXTRACTOR = new SimpleMachineMetaTileEntity[5];
    public static MetaTileEntityCropWeedPicker[] CROP_WEED_PICKER = new MetaTileEntityCropWeedPicker[4];
    public static MetaTileEntityCropSynthesiser[] CROP_SYNTHESISER = new MetaTileEntityCropSynthesiser[5];
    public static MetaTileEntityCropReplicator[] CROP_REPLICATOR = new MetaTileEntityCropReplicator[5];
    public static MetaTileEntityScanner[] SCANNER = new MetaTileEntityScanner[5];
    public static SimpleMachineMetaTileEntity[] RECYCLER = new SimpleMachineMetaTileEntity[4];
    public static SimpleMachineMetaTileEntity[] AMPLIFABRICATOR = new SimpleMachineMetaTileEntity[5];
    public static SimpleMachineMetaTileEntity[] REPLICATOR = new SimpleMachineMetaTileEntity[5];
    public static MetaTileEntityMiner[] MINER = new MetaTileEntityMiner[5];

    //GENERATORS SECTION
    public static SimpleGeneratorMetaTileEntity[] DIESEL_GENERATOR = new SimpleGeneratorMetaTileEntity[4];
    public static SimpleGeneratorMetaTileEntity[] STEAM_TURBINE = new SimpleGeneratorMetaTileEntity[4];
    public static SimpleGeneratorMetaTileEntity[] GAS_TURBINE = new SimpleGeneratorMetaTileEntity[4];
    public static MetaTileEntityMagicEnergyAbsorber MAGIC_ENERGY_ABSORBER;

    //MULTIBLOCK PARTS SECTION
    public static MetaTileEntityItemBus[] ITEM_IMPORT_BUS = new MetaTileEntityItemBus[GTValues.V.length];
    public static MetaTileEntityItemBus[] ITEM_EXPORT_BUS = new MetaTileEntityItemBus[GTValues.V.length];
    public static MetaTileEntityFluidHatch[] FLUID_IMPORT_HATCH = new MetaTileEntityFluidHatch[GTValues.V.length];
    public static MetaTileEntityFluidHatch[] FLUID_EXPORT_HATCH = new MetaTileEntityFluidHatch[GTValues.V.length];
    //public static MetaTileEntityEnergyHatch[][] ENERGY_INPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length][5];
    //public static MetaTileEntityEnergyHatch[][] ENERGY_OUTPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length][5];

    public static MetaTileEntityEnergyHatch[] ENERGY_INPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length];
    public static MetaTileEntityEnergyHatch[] ENERGY_OUTPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length];

    public static MetaTileEntityRotorHolder[] ROTOR_HOLDER = new MetaTileEntityRotorHolder[3]; //HV, EV, IV
    public static MetaTileEntityCokeOvenHatch COKE_OVEN_HATCH;

    //MULTIBLOCKS SECTION
    public static MetaTileEntityPrimitiveBlastFurnace PRIMITIVE_BLAST_FURNACE;
    public static MetaTileEntityCokeOven COKE_OVEN;
    public static MetaTileEntityElectricBlastFurnace ELECTRIC_BLAST_FURNACE;
    public static MetaTileEntityVacuumFreezer VACUUM_FREEZER;
    public static MetaTileEntityImplosionCompressor IMPLOSION_COMPRESSOR;
    public static MetaTileEntityPyrolyseOven PYROLYSE_OVEN;
    public static MetaTileEntityDistillationTower DISTILLATION_TOWER;
    public static MetaTileEntityCrackingUnit CRACKER;
    public static MetaTileEntityMultiFurnace MULTI_FURNACE;
    public static MetaTileEntityDieselEngine DIESEL_ENGINE;

    public static MetaTileEntityLargeTurbine LARGE_STEAM_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_GAS_TURBINE;
    public static MetaTileEntityLargeTurbine LARGE_PLASMA_TURBINE;

    public static MetaTileEntityLargeBoiler LARGE_BRONZE_BOILER;
    public static MetaTileEntityLargeBoiler LARGE_STEEL_BOILER;
    public static MetaTileEntityLargeBoiler LARGE_TITANIUM_BOILER;
    public static MetaTileEntityLargeBoiler LARGE_TUNGSTENSTEEL_BOILER;

    public static MetaTileEntityFusionReactor FUSION_REACTOR;

    public static MetaTileEntityLargeTransformer LARGE_TRANSFORMER;
    public static MetaTileEntityLargeBatteryBuffer LARGE_BATTERY_BUFFER;
    public static MetaTileEntityBatteryHolder BATTERY_HOLDER;

    public static MetaTileEntityLargeHeatExchanger LHE;

    //STORAGE SECTION
    public static MetaTileEntityChest SMALL_WOODEN_CHEST;
    public static MetaTileEntityChest WOODEN_CHEST;
    public static MetaTileEntityChest BRONZE_CHEST;
    public static MetaTileEntityChest STEEL_CHEST;
    public static MetaTileEntityChest STAINLESS_STEEL_CHEST;
    public static MetaTileEntityChest TITANIUM_CHEST;
    public static MetaTileEntityChest TUNGSTENSTEEL_CHEST;
    public static MetaTileEntityLockedSafe LOCKED_SAFE;
    public static MetaTileEntityArmorTable ARMOR_TABLE;

    public static MetaTileEntityTank WOODEN_TANK;
    public static MetaTileEntityTank BRONZE_TANK;
    public static MetaTileEntityTank STEEL_TANK;
    public static MetaTileEntityTank STAINLESS_STEEL_TANK;
    public static MetaTileEntityTank TITANIUM_TANK;
    public static MetaTileEntityTank TUNGSTENSTEEL_TANK;


    //MISC MACHINES SECTION
    public static MetaTileEntityWorkbench WORKBENCH;
    public static MetaTileEntityPump[] PUMP = new MetaTileEntityPump[4];
    public static MetaTileEntityBlockBreaker[] BLOCK_BREAKER = new MetaTileEntityBlockBreaker[3];
    public static MetaTileEntityAirCollector[] AIR_COLLECTOR = new MetaTileEntityAirCollector[4];
    public static MetaTileEntityItemCollector[] ITEM_COLLECTOR = new MetaTileEntityItemCollector[4];
    public static MetaTileEntityTeslaCoil TESLA_COIL;
    public static MetaTileEntityQuantumChest[] QUANTUM_CHEST = new MetaTileEntityQuantumChest[3];
    public static MetaTileEntityQuantumTank[] QUANTUM_TANK = new MetaTileEntityQuantumTank[3];
    public static MetaTileEntityFisher[] FISHER = new MetaTileEntityFisher[4];
    public static MetaTileEntityCableDiode[][] CABLE_DIODES = new MetaTileEntityCableDiode[5][GTValues.DIODE_AMPS.length];
    public static MetaTileEntityWorldAccelerator[] WORLD_ACCELERATOR = new MetaTileEntityWorldAccelerator[5];
    public static MetaTileEntityWirelessCharger[] WIRELESS_CHARGER = new MetaTileEntityWirelessCharger[5];

    public static void init() {
        GTLog.logger.info("Registering MetaTileEntities");

        STEAM_BOILER_COAL_BRONZE = GregTechAPI.registerMetaTileEntity(1, new SteamCoalBoiler(gtrId("steam_boiler_coal_bronze"), false));
        STEAM_BOILER_COAL_STEEL = GregTechAPI.registerMetaTileEntity(2, new SteamCoalBoiler(gtrId("steam_boiler_coal_steel"), true));

        STEAM_BOILER_SOLAR_BRONZE = GregTechAPI.registerMetaTileEntity(3, new SteamSolarBoiler(gtrId("steam_boiler_solar_bronze"), false));

        STEAM_BOILER_LAVA_BRONZE = GregTechAPI.registerMetaTileEntity(5, new SteamLavaBoiler(gtrId("steam_boiler_lava_bronze"), false));
        STEAM_BOILER_LAVA_STEEL = GregTechAPI.registerMetaTileEntity(6, new SteamLavaBoiler(gtrId("steam_boiler_lava_steel"), true));

        STEAM_EXTRACTOR_BRONZE = GregTechAPI.registerMetaTileEntity(7, new SteamExtractor(gtrId("steam_extractor_bronze"), false));
        STEAM_EXTRACTOR_STEEL = GregTechAPI.registerMetaTileEntity(8, new SteamExtractor(gtrId("steam_extractor_steel"), true));

        STEAM_MACERATOR_BRONZE = GregTechAPI.registerMetaTileEntity(9, new SteamMacerator(gtrId("steam_macerator_bronze"), false));
        STEAM_MACERATOR_STEEL = GregTechAPI.registerMetaTileEntity(10, new SteamMacerator(gtrId("steam_macerator_steel"), true));

        STEAM_COMPRESSOR_BRONZE = GregTechAPI.registerMetaTileEntity(11, new SteamCompressor(gtrId("steam_compressor_bronze"), false));
        STEAM_COMPRESSOR_STEEL = GregTechAPI.registerMetaTileEntity(12, new SteamCompressor(gtrId("steam_compressor_steel"), true));

        STEAM_HAMMER_BRONZE = GregTechAPI.registerMetaTileEntity(13, new SteamHammer(gtrId("steam_hammer_bronze"), false));
        STEAM_HAMMER_STEEL = GregTechAPI.registerMetaTileEntity(14, new SteamHammer(gtrId("steam_hammer_steel"), true));

        STEAM_FURNACE_BRONZE = GregTechAPI.registerMetaTileEntity(15, new SteamFurnace(gtrId("steam_furnace_bronze"), false));
        STEAM_FURNACE_STEEL = GregTechAPI.registerMetaTileEntity(16, new SteamFurnace(gtrId("steam_furnace_steel"), true));

        STEAM_ALLOY_SMELTER_BRONZE = GregTechAPI.registerMetaTileEntity(17, new SteamAlloySmelter(gtrId("steam_alloy_smelter_bronze"), false));
        STEAM_ALLOY_SMELTER_STEEL = GregTechAPI.registerMetaTileEntity(18, new SteamAlloySmelter(gtrId("steam_alloy_smelter_steel"), true));

        ELECTRIC_FURNACE[0] = GregTechAPI.registerMetaTileEntity(50, new SimpleMachineMetaTileEntity(gtrId("electric_furnace.lv"), RecipeMaps.FURNACE_RECIPES, Textures.ELECTRIC_FURNACE_OVERLAY, GTValues.LV));
        ELECTRIC_FURNACE[1] = GregTechAPI.registerMetaTileEntity(51, new SimpleMachineMetaTileEntity(gtrId("electric_furnace.mv"), RecipeMaps.FURNACE_RECIPES, Textures.ELECTRIC_FURNACE_OVERLAY, GTValues.MV));
        ELECTRIC_FURNACE[2] = GregTechAPI.registerMetaTileEntity(52, new SimpleMachineMetaTileEntity(gtrId("electric_furnace.hv"), RecipeMaps.FURNACE_RECIPES, Textures.ELECTRIC_FURNACE_OVERLAY, GTValues.HV));
        ELECTRIC_FURNACE[3] = GregTechAPI.registerMetaTileEntity(53, new SimpleMachineMetaTileEntity(gtrId("electric_furnace.ev"), RecipeMaps.FURNACE_RECIPES, Textures.ELECTRIC_FURNACE_OVERLAY, GTValues.EV));

        MACERATOR[0] = GregTechAPI.registerMetaTileEntity(60, new MetaTileEntityMacerator(gtrId("macerator.lv"), RecipeMaps.MACERATOR_RECIPES, 1, Textures.MACERATOR_OVERLAY, GTValues.LV));
        MACERATOR[1] = GregTechAPI.registerMetaTileEntity(61, new MetaTileEntityMacerator(gtrId("macerator.mv"), RecipeMaps.MACERATOR_RECIPES, 1, Textures.MACERATOR_OVERLAY, GTValues.MV));
        MACERATOR[2] = GregTechAPI.registerMetaTileEntity(62, new MetaTileEntityMacerator(gtrId("macerator.hv"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.MACERATOR_OVERLAY, GTValues.HV));
        MACERATOR[3] = GregTechAPI.registerMetaTileEntity(63, new MetaTileEntityMacerator(gtrId("macerator.ev"), RecipeMaps.MACERATOR_RECIPES, 3, Textures.MACERATOR_OVERLAY, GTValues.EV));

        ALLOY_SMELTER[0] = GregTechAPI.registerMetaTileEntity(70, new SimpleMachineMetaTileEntity(gtrId("alloy_smelter.lv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, GTValues.LV));
        ALLOY_SMELTER[1] = GregTechAPI.registerMetaTileEntity(71, new SimpleMachineMetaTileEntity(gtrId("alloy_smelter.mv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, GTValues.MV));
        ALLOY_SMELTER[2] = GregTechAPI.registerMetaTileEntity(72, new SimpleMachineMetaTileEntity(gtrId("alloy_smelter.hv"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, GTValues.HV));
        ALLOY_SMELTER[3] = GregTechAPI.registerMetaTileEntity(73, new SimpleMachineMetaTileEntity(gtrId("alloy_smelter.ev"), RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, GTValues.EV));

        ARC_FURNACE[0] = GregTechAPI.registerMetaTileEntity(90, new SimpleMachineMetaTileEntity(gtrId("arc_furnace.lv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, GTValues.LV, false));
        ARC_FURNACE[1] = GregTechAPI.registerMetaTileEntity(91, new SimpleMachineMetaTileEntity(gtrId("arc_furnace.mv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, GTValues.MV, false));
        ARC_FURNACE[2] = GregTechAPI.registerMetaTileEntity(92, new SimpleMachineMetaTileEntity(gtrId("arc_furnace.hv"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, GTValues.HV, false));
        ARC_FURNACE[3] = GregTechAPI.registerMetaTileEntity(93, new SimpleMachineMetaTileEntity(gtrId("arc_furnace.ev"), RecipeMaps.ARC_FURNACE_RECIPES, Textures.ARC_FURNACE_OVERLAY, GTValues.EV, false));

        ASSEMBLER[0] = GregTechAPI.registerMetaTileEntity(100, new SimpleMachineMetaTileEntity(gtrId("assembler.lv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, GTValues.LV));
        ASSEMBLER[1] = GregTechAPI.registerMetaTileEntity(101, new SimpleMachineMetaTileEntity(gtrId("assembler.mv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, GTValues.MV));
        ASSEMBLER[2] = GregTechAPI.registerMetaTileEntity(102, new SimpleMachineMetaTileEntity(gtrId("assembler.hv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, GTValues.HV));
        ASSEMBLER[3] = GregTechAPI.registerMetaTileEntity(103, new SimpleMachineMetaTileEntity(gtrId("assembler.ev"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, GTValues.EV));
        ASSEMBLER[4] = GregTechAPI.registerMetaTileEntity(104, new SimpleMachineMetaTileEntity(gtrId("assembler.iv"), RecipeMaps.ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, GTValues.IV));

        AUTOCLAVE[0] = GregTechAPI.registerMetaTileEntity(110, new SimpleMachineMetaTileEntity(gtrId("autoclave.lv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, GTValues.LV, false));
        AUTOCLAVE[1] = GregTechAPI.registerMetaTileEntity(111, new SimpleMachineMetaTileEntity(gtrId("autoclave.mv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, GTValues.MV, false));
        AUTOCLAVE[2] = GregTechAPI.registerMetaTileEntity(112, new SimpleMachineMetaTileEntity(gtrId("autoclave.hv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, GTValues.HV, false));
        AUTOCLAVE[3] = GregTechAPI.registerMetaTileEntity(113, new SimpleMachineMetaTileEntity(gtrId("autoclave.ev"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, GTValues.EV, false));
        AUTOCLAVE[4] = GregTechAPI.registerMetaTileEntity(114, new SimpleMachineMetaTileEntity(gtrId("autoclave.iv"), RecipeMaps.AUTOCLAVE_RECIPES, Textures.AUTOCLAVE_OVERLAY, GTValues.IV, false));

        BENDER[0] = GregTechAPI.registerMetaTileEntity(120, new SimpleMachineMetaTileEntity(gtrId("bender.lv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, GTValues.LV));
        BENDER[1] = GregTechAPI.registerMetaTileEntity(121, new SimpleMachineMetaTileEntity(gtrId("bender.mv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, GTValues.MV));
        BENDER[2] = GregTechAPI.registerMetaTileEntity(122, new SimpleMachineMetaTileEntity(gtrId("bender.hv"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, GTValues.HV));
        BENDER[3] = GregTechAPI.registerMetaTileEntity(123, new SimpleMachineMetaTileEntity(gtrId("bender.ev"), RecipeMaps.BENDER_RECIPES, Textures.BENDER_OVERLAY, GTValues.EV));

        BREWERY[0] = GregTechAPI.registerMetaTileEntity(130, new SimpleMachineMetaTileEntity(gtrId("brewery.lv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, GTValues.LV));
        BREWERY[1] = GregTechAPI.registerMetaTileEntity(131, new SimpleMachineMetaTileEntity(gtrId("brewery.mv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, GTValues.MV));
        BREWERY[2] = GregTechAPI.registerMetaTileEntity(132, new SimpleMachineMetaTileEntity(gtrId("brewery.hv"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, GTValues.HV));
        BREWERY[3] = GregTechAPI.registerMetaTileEntity(133, new SimpleMachineMetaTileEntity(gtrId("brewery.ev"), RecipeMaps.BREWING_RECIPES, Textures.BREWERY_OVERLAY, GTValues.EV));

        CANNER[0] = GregTechAPI.registerMetaTileEntity(140, new SimpleMachineMetaTileEntity(gtrId("canner.lv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, GTValues.LV));
        CANNER[1] = GregTechAPI.registerMetaTileEntity(141, new SimpleMachineMetaTileEntity(gtrId("canner.mv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, GTValues.MV));
        CANNER[2] = GregTechAPI.registerMetaTileEntity(142, new SimpleMachineMetaTileEntity(gtrId("canner.hv"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, GTValues.HV));
        CANNER[3] = GregTechAPI.registerMetaTileEntity(143, new SimpleMachineMetaTileEntity(gtrId("canner.ev"), RecipeMaps.CANNER_RECIPES, Textures.CANNER_OVERLAY, GTValues.EV));

        CENTRIFUGE[0] = GregTechAPI.registerMetaTileEntity(150, new SimpleMachineMetaTileEntity(gtrId("centrifuge.lv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, GTValues.LV, false));
        CENTRIFUGE[1] = GregTechAPI.registerMetaTileEntity(151, new SimpleMachineMetaTileEntity(gtrId("centrifuge.mv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, GTValues.MV, false));
        CENTRIFUGE[2] = GregTechAPI.registerMetaTileEntity(152, new SimpleMachineMetaTileEntity(gtrId("centrifuge.hv"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, GTValues.HV, false));
        CENTRIFUGE[3] = GregTechAPI.registerMetaTileEntity(153, new SimpleMachineMetaTileEntity(gtrId("centrifuge.ev"), RecipeMaps.CENTRIFUGE_RECIPES, Textures.CENTRIFUGE_OVERLAY, GTValues.EV, false));

        CHEMICAL_BATH[0] = GregTechAPI.registerMetaTileEntity(180, new SimpleMachineMetaTileEntity(gtrId("chemical_bath.lv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, GTValues.LV));
        CHEMICAL_BATH[1] = GregTechAPI.registerMetaTileEntity(181, new SimpleMachineMetaTileEntity(gtrId("chemical_bath.mv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, GTValues.MV));
        CHEMICAL_BATH[2] = GregTechAPI.registerMetaTileEntity(182, new SimpleMachineMetaTileEntity(gtrId("chemical_bath.hv"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, GTValues.HV));
        CHEMICAL_BATH[3] = GregTechAPI.registerMetaTileEntity(183, new SimpleMachineMetaTileEntity(gtrId("chemical_bath.ev"), RecipeMaps.CHEMICAL_BATH_RECIPES, Textures.CHEMICAL_BATH_OVERLAY, GTValues.EV));

        CHEMICAL_REACTOR[0] = GregTechAPI.registerMetaTileEntity(190, new SimpleMachineMetaTileEntity(gtrId("chemical_reactor.lv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, GTValues.LV));
        CHEMICAL_REACTOR[1] = GregTechAPI.registerMetaTileEntity(191, new SimpleMachineMetaTileEntity(gtrId("chemical_reactor.mv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, GTValues.MV));
        CHEMICAL_REACTOR[2] = GregTechAPI.registerMetaTileEntity(192, new SimpleMachineMetaTileEntity(gtrId("chemical_reactor.hv"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, GTValues.HV));
        CHEMICAL_REACTOR[3] = GregTechAPI.registerMetaTileEntity(193, new SimpleMachineMetaTileEntity(gtrId("chemical_reactor.ev"), RecipeMaps.CHEMICAL_RECIPES, Textures.CHEMICAL_REACTOR_OVERLAY, GTValues.EV));

        COMPRESSOR[0] = GregTechAPI.registerMetaTileEntity(210, new SimpleMachineMetaTileEntity(gtrId("compressor.lv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, GTValues.LV));
        COMPRESSOR[1] = GregTechAPI.registerMetaTileEntity(211, new SimpleMachineMetaTileEntity(gtrId("compressor.mv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, GTValues.MV));
        COMPRESSOR[2] = GregTechAPI.registerMetaTileEntity(212, new SimpleMachineMetaTileEntity(gtrId("compressor.hv"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, GTValues.HV));
        COMPRESSOR[3] = GregTechAPI.registerMetaTileEntity(213, new SimpleMachineMetaTileEntity(gtrId("compressor.ev"), RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, GTValues.EV));

        CUTTER[0] = GregTechAPI.registerMetaTileEntity(220, new SimpleMachineMetaTileEntity(gtrId("cutter.lv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, GTValues.LV));
        CUTTER[1] = GregTechAPI.registerMetaTileEntity(221, new SimpleMachineMetaTileEntity(gtrId("cutter.mv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, GTValues.MV));
        CUTTER[2] = GregTechAPI.registerMetaTileEntity(222, new SimpleMachineMetaTileEntity(gtrId("cutter.hv"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, GTValues.HV));
        CUTTER[3] = GregTechAPI.registerMetaTileEntity(223, new SimpleMachineMetaTileEntity(gtrId("cutter.ev"), RecipeMaps.CUTTER_RECIPES, Textures.CUTTER_OVERLAY, GTValues.EV));

        DISTILLERY[0] = GregTechAPI.registerMetaTileEntity(230, new SimpleMachineMetaTileEntity(gtrId("distillery.lv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, GTValues.LV));
        DISTILLERY[1] = GregTechAPI.registerMetaTileEntity(231, new SimpleMachineMetaTileEntity(gtrId("distillery.mv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, GTValues.MV));
        DISTILLERY[2] = GregTechAPI.registerMetaTileEntity(232, new SimpleMachineMetaTileEntity(gtrId("distillery.hv"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, GTValues.HV));
        DISTILLERY[3] = GregTechAPI.registerMetaTileEntity(233, new SimpleMachineMetaTileEntity(gtrId("distillery.ev"), RecipeMaps.DISTILLERY_RECIPES, Textures.DISTILLERY_OVERLAY, GTValues.EV));

        ELECTROLYZER[0] = GregTechAPI.registerMetaTileEntity(240, new SimpleMachineMetaTileEntity(gtrId("electrolyzer.lv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, GTValues.LV, false));
        ELECTROLYZER[1] = GregTechAPI.registerMetaTileEntity(241, new SimpleMachineMetaTileEntity(gtrId("electrolyzer.mv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, GTValues.MV, false));
        ELECTROLYZER[2] = GregTechAPI.registerMetaTileEntity(242, new SimpleMachineMetaTileEntity(gtrId("electrolyzer.hv"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, GTValues.HV, false));
        ELECTROLYZER[3] = GregTechAPI.registerMetaTileEntity(243, new SimpleMachineMetaTileEntity(gtrId("electrolyzer.ev"), RecipeMaps.ELECTROLYZER_RECIPES, Textures.ELECTROLYZER_OVERLAY, GTValues.EV, false));

        ELECTROMAGNETIC_SEPARATOR[0] = GregTechAPI.registerMetaTileEntity(250, new SimpleMachineMetaTileEntity(gtrId("electromagnetic_separator.lv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, GTValues.LV));
        ELECTROMAGNETIC_SEPARATOR[1] = GregTechAPI.registerMetaTileEntity(251, new SimpleMachineMetaTileEntity(gtrId("electromagnetic_separator.mv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, GTValues.MV));
        ELECTROMAGNETIC_SEPARATOR[2] = GregTechAPI.registerMetaTileEntity(252, new SimpleMachineMetaTileEntity(gtrId("electromagnetic_separator.hv"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, GTValues.HV));
        ELECTROMAGNETIC_SEPARATOR[3] = GregTechAPI.registerMetaTileEntity(253, new SimpleMachineMetaTileEntity(gtrId("electromagnetic_separator.ev"), RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES, Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY, GTValues.EV));

        EXTRACTOR[0] = GregTechAPI.registerMetaTileEntity(260, new SimpleMachineMetaTileEntity(gtrId("extractor.lv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, GTValues.LV));
        EXTRACTOR[1] = GregTechAPI.registerMetaTileEntity(261, new SimpleMachineMetaTileEntity(gtrId("extractor.mv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, GTValues.MV));
        EXTRACTOR[2] = GregTechAPI.registerMetaTileEntity(262, new SimpleMachineMetaTileEntity(gtrId("extractor.hv"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, GTValues.HV));
        EXTRACTOR[3] = GregTechAPI.registerMetaTileEntity(263, new SimpleMachineMetaTileEntity(gtrId("extractor.ev"), RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, GTValues.EV));

        EXTRUDER[0] = GregTechAPI.registerMetaTileEntity(271, new SimpleMachineMetaTileEntity(gtrId("extruder.mv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, GTValues.MV));
        EXTRUDER[1] = GregTechAPI.registerMetaTileEntity(272, new SimpleMachineMetaTileEntity(gtrId("extruder.hv"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, GTValues.HV));
        EXTRUDER[2] = GregTechAPI.registerMetaTileEntity(273, new SimpleMachineMetaTileEntity(gtrId("extruder.ev"), RecipeMaps.EXTRUDER_RECIPES, Textures.EXTRUDER_OVERLAY, GTValues.EV));

        FERMENTER[0] = GregTechAPI.registerMetaTileEntity(280, new SimpleMachineMetaTileEntity(gtrId("fermenter.lv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, GTValues.LV));
        FERMENTER[1] = GregTechAPI.registerMetaTileEntity(281, new SimpleMachineMetaTileEntity(gtrId("fermenter.mv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, GTValues.MV));
        FERMENTER[2] = GregTechAPI.registerMetaTileEntity(282, new SimpleMachineMetaTileEntity(gtrId("fermenter.hv"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, GTValues.HV));
        FERMENTER[3] = GregTechAPI.registerMetaTileEntity(283, new SimpleMachineMetaTileEntity(gtrId("fermenter.ev"), RecipeMaps.FERMENTING_RECIPES, Textures.FERMENTER_OVERLAY, GTValues.EV));

        FLUID_CANNER[0] = GregTechAPI.registerMetaTileEntity(290, new SimpleMachineMetaTileEntity(gtrId("fluid_canner.lv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, GTValues.LV));
        FLUID_CANNER[1] = GregTechAPI.registerMetaTileEntity(291, new SimpleMachineMetaTileEntity(gtrId("fluid_canner.mv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, GTValues.MV));
        FLUID_CANNER[2] = GregTechAPI.registerMetaTileEntity(292, new SimpleMachineMetaTileEntity(gtrId("fluid_canner.hv"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, GTValues.HV));
        FLUID_CANNER[3] = GregTechAPI.registerMetaTileEntity(293, new SimpleMachineMetaTileEntity(gtrId("fluid_canner.ev"), RecipeMaps.FLUID_CANNER_RECIPES, Textures.FLUID_CANNER_OVERLAY, GTValues.EV));

        FLUID_EXTRACTOR[0] = GregTechAPI.registerMetaTileEntity(300, new SimpleMachineMetaTileEntity(gtrId("fluid_extractor.lv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, GTValues.LV));
        FLUID_EXTRACTOR[1] = GregTechAPI.registerMetaTileEntity(301, new SimpleMachineMetaTileEntity(gtrId("fluid_extractor.mv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, GTValues.MV));
        FLUID_EXTRACTOR[2] = GregTechAPI.registerMetaTileEntity(302, new SimpleMachineMetaTileEntity(gtrId("fluid_extractor.hv"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, GTValues.HV));
        FLUID_EXTRACTOR[3] = GregTechAPI.registerMetaTileEntity(303, new SimpleMachineMetaTileEntity(gtrId("fluid_extractor.ev"), RecipeMaps.FLUID_EXTRACTION_RECIPES, Textures.FLUID_EXTRACTOR_OVERLAY, GTValues.EV));

        FLUID_HEATER[0] = GregTechAPI.registerMetaTileEntity(310, new SimpleMachineMetaTileEntity(gtrId("fluid_heater.lv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, GTValues.LV));
        FLUID_HEATER[1] = GregTechAPI.registerMetaTileEntity(311, new SimpleMachineMetaTileEntity(gtrId("fluid_heater.mv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, GTValues.MV));
        FLUID_HEATER[2] = GregTechAPI.registerMetaTileEntity(312, new SimpleMachineMetaTileEntity(gtrId("fluid_heater.hv"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, GTValues.HV));
        FLUID_HEATER[3] = GregTechAPI.registerMetaTileEntity(313, new SimpleMachineMetaTileEntity(gtrId("fluid_heater.ev"), RecipeMaps.FLUID_HEATER_RECIPES, Textures.FLUID_HEATER_OVERLAY, GTValues.EV));

        FLUID_SOLIDIFIER[0] = GregTechAPI.registerMetaTileEntity(320, new SimpleMachineMetaTileEntity(gtrId("fluid_solidifier.lv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, GTValues.LV));
        FLUID_SOLIDIFIER[1] = GregTechAPI.registerMetaTileEntity(321, new SimpleMachineMetaTileEntity(gtrId("fluid_solidifier.mv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, GTValues.MV));
        FLUID_SOLIDIFIER[2] = GregTechAPI.registerMetaTileEntity(322, new SimpleMachineMetaTileEntity(gtrId("fluid_solidifier.hv"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, GTValues.HV));
        FLUID_SOLIDIFIER[3] = GregTechAPI.registerMetaTileEntity(323, new SimpleMachineMetaTileEntity(gtrId("fluid_solidifier.ev"), RecipeMaps.FLUID_SOLIDFICATION_RECIPES, Textures.FLUID_SOLIDIFIER_OVERLAY, GTValues.EV));

        FORGE_HAMMER[0] = GregTechAPI.registerMetaTileEntity(330, new SimpleMachineMetaTileEntity(gtrId("forge_hammer.lv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, GTValues.LV));
        FORGE_HAMMER[1] = GregTechAPI.registerMetaTileEntity(331, new SimpleMachineMetaTileEntity(gtrId("forge_hammer.mv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, GTValues.MV));
        FORGE_HAMMER[2] = GregTechAPI.registerMetaTileEntity(332, new SimpleMachineMetaTileEntity(gtrId("forge_hammer.hv"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, GTValues.HV));
        FORGE_HAMMER[3] = GregTechAPI.registerMetaTileEntity(333, new SimpleMachineMetaTileEntity(gtrId("forge_hammer.ev"), RecipeMaps.FORGE_HAMMER_RECIPES, Textures.FORGE_HAMMER_OVERLAY, GTValues.EV));

        FORMING_PRESS[0] = GregTechAPI.registerMetaTileEntity(340, new SimpleMachineMetaTileEntity(gtrId("forming_press.lv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, GTValues.LV));
        FORMING_PRESS[1] = GregTechAPI.registerMetaTileEntity(341, new SimpleMachineMetaTileEntity(gtrId("forming_press.mv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, GTValues.MV));
        FORMING_PRESS[2] = GregTechAPI.registerMetaTileEntity(342, new SimpleMachineMetaTileEntity(gtrId("forming_press.hv"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, GTValues.HV));
        FORMING_PRESS[3] = GregTechAPI.registerMetaTileEntity(343, new SimpleMachineMetaTileEntity(gtrId("forming_press.ev"), RecipeMaps.FORMING_PRESS_RECIPES, Textures.FORMING_PRESS_OVERLAY, GTValues.EV));

        LATHE[0] = GregTechAPI.registerMetaTileEntity(350, new SimpleMachineMetaTileEntity(gtrId("lathe.lv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, GTValues.LV));
        LATHE[1] = GregTechAPI.registerMetaTileEntity(351, new SimpleMachineMetaTileEntity(gtrId("lathe.mv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, GTValues.MV));
        LATHE[2] = GregTechAPI.registerMetaTileEntity(352, new SimpleMachineMetaTileEntity(gtrId("lathe.hv"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, GTValues.HV));
        LATHE[3] = GregTechAPI.registerMetaTileEntity(353, new SimpleMachineMetaTileEntity(gtrId("lathe.ev"), RecipeMaps.LATHE_RECIPES, Textures.LATHE_OVERLAY, GTValues.EV));

        MICROWAVE[0] = GregTechAPI.registerMetaTileEntity(360, new SimpleMachineMetaTileEntity(gtrId("microwave.lv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, GTValues.LV));
        MICROWAVE[1] = GregTechAPI.registerMetaTileEntity(361, new SimpleMachineMetaTileEntity(gtrId("microwave.mv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, GTValues.MV));
        MICROWAVE[2] = GregTechAPI.registerMetaTileEntity(362, new SimpleMachineMetaTileEntity(gtrId("microwave.hv"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, GTValues.HV));
        MICROWAVE[3] = GregTechAPI.registerMetaTileEntity(363, new SimpleMachineMetaTileEntity(gtrId("microwave.ev"), RecipeMaps.MICROWAVE_RECIPES, Textures.MICROWAVE_OVERLAY, GTValues.EV));

        MIXER[0] = GregTechAPI.registerMetaTileEntity(370, new SimpleMachineMetaTileEntity(gtrId("mixer.lv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, GTValues.LV, false));
        MIXER[1] = GregTechAPI.registerMetaTileEntity(371, new SimpleMachineMetaTileEntity(gtrId("mixer.mv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, GTValues.MV, false));
        MIXER[2] = GregTechAPI.registerMetaTileEntity(372, new SimpleMachineMetaTileEntity(gtrId("mixer.hv"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, GTValues.HV, false));
        MIXER[3] = GregTechAPI.registerMetaTileEntity(373, new SimpleMachineMetaTileEntity(gtrId("mixer.ev"), RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, GTValues.EV, false));

        ORE_WASHER[0] = GregTechAPI.registerMetaTileEntity(380, new SimpleMachineMetaTileEntity(gtrId("ore_washer.lv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, GTValues.LV));
        ORE_WASHER[1] = GregTechAPI.registerMetaTileEntity(381, new SimpleMachineMetaTileEntity(gtrId("ore_washer.mv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, GTValues.MV));
        ORE_WASHER[2] = GregTechAPI.registerMetaTileEntity(382, new SimpleMachineMetaTileEntity(gtrId("ore_washer.hv"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, GTValues.HV));
        ORE_WASHER[3] = GregTechAPI.registerMetaTileEntity(383, new SimpleMachineMetaTileEntity(gtrId("ore_washer.ev"), RecipeMaps.ORE_WASHER_RECIPES, Textures.ORE_WASHER_OVERLAY, GTValues.EV));

        PACKER[0] = GregTechAPI.registerMetaTileEntity(390, new SimpleMachineMetaTileEntity(gtrId("packer.lv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, GTValues.LV));
        PACKER[1] = GregTechAPI.registerMetaTileEntity(391, new SimpleMachineMetaTileEntity(gtrId("packer.mv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, GTValues.MV));
        PACKER[2] = GregTechAPI.registerMetaTileEntity(392, new SimpleMachineMetaTileEntity(gtrId("packer.hv"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, GTValues.HV));
        PACKER[3] = GregTechAPI.registerMetaTileEntity(393, new SimpleMachineMetaTileEntity(gtrId("packer.ev"), RecipeMaps.PACKER_RECIPES, Textures.PACKER_OVERLAY, GTValues.EV));

        UNPACKER[0] = GregTechAPI.registerMetaTileEntity(400, new SimpleMachineMetaTileEntity(gtrId("unpacker.lv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, GTValues.LV));
        UNPACKER[1] = GregTechAPI.registerMetaTileEntity(401, new SimpleMachineMetaTileEntity(gtrId("unpacker.mv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, GTValues.MV));
        UNPACKER[2] = GregTechAPI.registerMetaTileEntity(402, new SimpleMachineMetaTileEntity(gtrId("unpacker.hv"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, GTValues.HV));
        UNPACKER[3] = GregTechAPI.registerMetaTileEntity(403, new SimpleMachineMetaTileEntity(gtrId("unpacker.ev"), RecipeMaps.UNPACKER_RECIPES, Textures.UNPACKER_OVERLAY, GTValues.EV));

        PLASMA_ARC_FURNACE[0] = GregTechAPI.registerMetaTileEntity(410, new SimpleMachineMetaTileEntity(gtrId("plasma_arc_furnace.lv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, GTValues.LV, false));
        PLASMA_ARC_FURNACE[1] = GregTechAPI.registerMetaTileEntity(411, new SimpleMachineMetaTileEntity(gtrId("plasma_arc_furnace.mv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, GTValues.MV, false));
        PLASMA_ARC_FURNACE[2] = GregTechAPI.registerMetaTileEntity(412, new SimpleMachineMetaTileEntity(gtrId("plasma_arc_furnace.hv"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, GTValues.HV, false));
        PLASMA_ARC_FURNACE[3] = GregTechAPI.registerMetaTileEntity(413, new SimpleMachineMetaTileEntity(gtrId("plasma_arc_furnace.ev"), RecipeMaps.PLASMA_ARC_FURNACE_RECIPES, Textures.PLASMA_ARC_FURNACE_OVERLAY, GTValues.EV, false));

        POLARIZER[0] = GregTechAPI.registerMetaTileEntity(420, new SimpleMachineMetaTileEntity(gtrId("polarizer.lv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, GTValues.LV));
        POLARIZER[1] = GregTechAPI.registerMetaTileEntity(421, new SimpleMachineMetaTileEntity(gtrId("polarizer.mv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, GTValues.MV));
        POLARIZER[2] = GregTechAPI.registerMetaTileEntity(422, new SimpleMachineMetaTileEntity(gtrId("polarizer.hv"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, GTValues.HV));
        POLARIZER[3] = GregTechAPI.registerMetaTileEntity(423, new SimpleMachineMetaTileEntity(gtrId("polarizer.ev"), RecipeMaps.POLARIZER_RECIPES, Textures.POLARIZER_OVERLAY, GTValues.EV));

        LASER_ENGRAVER[0] = GregTechAPI.registerMetaTileEntity(430, new SimpleMachineMetaTileEntity(gtrId("laser_engraver.lv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, GTValues.LV));
        LASER_ENGRAVER[1] = GregTechAPI.registerMetaTileEntity(431, new SimpleMachineMetaTileEntity(gtrId("laser_engraver.mv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, GTValues.MV));
        LASER_ENGRAVER[2] = GregTechAPI.registerMetaTileEntity(432, new SimpleMachineMetaTileEntity(gtrId("laser_engraver.hv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, GTValues.HV));
        LASER_ENGRAVER[3] = GregTechAPI.registerMetaTileEntity(433, new SimpleMachineMetaTileEntity(gtrId("laser_engraver.ev"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, GTValues.EV));
        LASER_ENGRAVER[4] = GregTechAPI.registerMetaTileEntity(434, new SimpleMachineMetaTileEntity(gtrId("laser_engraver.iv"), RecipeMaps.LASER_ENGRAVER_RECIPES, Textures.LASER_ENGRAVER_OVERLAY, GTValues.IV));

        SIFTER[0] = GregTechAPI.registerMetaTileEntity(450, new SimpleMachineMetaTileEntity(gtrId("sifter.lv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, GTValues.LV));
        SIFTER[1] = GregTechAPI.registerMetaTileEntity(451, new SimpleMachineMetaTileEntity(gtrId("sifter.mv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, GTValues.MV));
        SIFTER[2] = GregTechAPI.registerMetaTileEntity(452, new SimpleMachineMetaTileEntity(gtrId("sifter.hv"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, GTValues.HV));
        SIFTER[3] = GregTechAPI.registerMetaTileEntity(453, new SimpleMachineMetaTileEntity(gtrId("sifter.ev"), RecipeMaps.SIFTER_RECIPES, Textures.SIFTER_OVERLAY, GTValues.EV));

        THERMAL_CENTRIFUGE[0] = GregTechAPI.registerMetaTileEntity(460, new SimpleMachineMetaTileEntity(gtrId("thermal_centrifuge.lv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, GTValues.LV));
        THERMAL_CENTRIFUGE[1] = GregTechAPI.registerMetaTileEntity(461, new SimpleMachineMetaTileEntity(gtrId("thermal_centrifuge.mv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, GTValues.MV));
        THERMAL_CENTRIFUGE[2] = GregTechAPI.registerMetaTileEntity(462, new SimpleMachineMetaTileEntity(gtrId("thermal_centrifuge.hv"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, GTValues.HV));
        THERMAL_CENTRIFUGE[3] = GregTechAPI.registerMetaTileEntity(463, new SimpleMachineMetaTileEntity(gtrId("thermal_centrifuge.ev"), RecipeMaps.THERMAL_CENTRIFUGE_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, GTValues.EV));

        WIREMILL[0] = GregTechAPI.registerMetaTileEntity(470, new SimpleMachineMetaTileEntity(gtrId("wiremill.lv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, GTValues.LV));
        WIREMILL[1] = GregTechAPI.registerMetaTileEntity(471, new SimpleMachineMetaTileEntity(gtrId("wiremill.mv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, GTValues.MV));
        WIREMILL[2] = GregTechAPI.registerMetaTileEntity(472, new SimpleMachineMetaTileEntity(gtrId("wiremill.hv"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, GTValues.HV));
        WIREMILL[3] = GregTechAPI.registerMetaTileEntity(473, new SimpleMachineMetaTileEntity(gtrId("wiremill.ev"), RecipeMaps.WIREMILL_RECIPES, Textures.WIREMILL_OVERLAY, GTValues.EV));

        MASS_FAB[0] = GregTechAPI.registerMetaTileEntity(1214, new MetaTileEntityMassFab(gtrId("mass_fab.lv"), RecipeMaps.MASS_FAB_RECIPES, Textures.MASS_FAB_OVERLAY, GTValues.LV));
        MASS_FAB[1] = GregTechAPI.registerMetaTileEntity(1215, new MetaTileEntityMassFab(gtrId("mass_fab.mv"), RecipeMaps.MASS_FAB_RECIPES, Textures.MASS_FAB_OVERLAY, GTValues.MV));
        MASS_FAB[2] = GregTechAPI.registerMetaTileEntity(1216, new MetaTileEntityMassFab(gtrId("mass_fab.hv"), RecipeMaps.MASS_FAB_RECIPES, Textures.MASS_FAB_OVERLAY, GTValues.HV));
        MASS_FAB[3] = GregTechAPI.registerMetaTileEntity(1217, new MetaTileEntityMassFab(gtrId("mass_fab.ev"), RecipeMaps.MASS_FAB_RECIPES, Textures.MASS_FAB_OVERLAY, GTValues.EV));
        MASS_FAB[4] = GregTechAPI.registerMetaTileEntity(1218, new MetaTileEntityMassFab(gtrId("mass_fab.iv"), RecipeMaps.MASS_FAB_RECIPES, Textures.MASS_FAB_OVERLAY, GTValues.IV));

        if (Loader.isModLoaded("ic2")) {
            CROP_GENE_EXTRACTOR[0] = GregTechAPI.registerMetaTileEntity(1219, new MetaTileEntityCropGeneExtractor(gtrId("gene_extractor.lv"), RecipeMapCropGeneExtractor.RECIPE_MAP, Textures.SCANNER, GTValues.LV));
            CROP_GENE_EXTRACTOR[1] = GregTechAPI.registerMetaTileEntity(1220, new MetaTileEntityCropGeneExtractor(gtrId("gene_extractor.mv"), RecipeMapCropGeneExtractor.RECIPE_MAP, Textures.SCANNER, GTValues.MV));
            CROP_GENE_EXTRACTOR[2] = GregTechAPI.registerMetaTileEntity(1221, new MetaTileEntityCropGeneExtractor(gtrId("gene_extractor.hv"), RecipeMapCropGeneExtractor.RECIPE_MAP, Textures.SCANNER, GTValues.HV));
            CROP_GENE_EXTRACTOR[3] = GregTechAPI.registerMetaTileEntity(1222, new MetaTileEntityCropGeneExtractor(gtrId("gene_extractor.ev"), RecipeMapCropGeneExtractor.RECIPE_MAP, Textures.SCANNER, GTValues.EV));
            CROP_GENE_EXTRACTOR[4] = GregTechAPI.registerMetaTileEntity(1223, new MetaTileEntityCropGeneExtractor(gtrId("gene_extractor.iv"), RecipeMapCropGeneExtractor.RECIPE_MAP, Textures.SCANNER, GTValues.IV));

            CROP_WEED_PICKER[0] = GregTechAPI.registerMetaTileEntity(1224, new MetaTileEntityCropWeedPicker(gtrId("weed_picker.lv"), GTValues.LV));
            CROP_WEED_PICKER[1] = GregTechAPI.registerMetaTileEntity(1225, new MetaTileEntityCropWeedPicker(gtrId("weed_picker.mv"), GTValues.MV));
            CROP_WEED_PICKER[2] = GregTechAPI.registerMetaTileEntity(1226, new MetaTileEntityCropWeedPicker(gtrId("weed_picker.hv"), GTValues.HV));
            CROP_WEED_PICKER[3] = GregTechAPI.registerMetaTileEntity(1227, new MetaTileEntityCropWeedPicker(gtrId("weed_picker.ev"), GTValues.EV));

            CROP_SYNTHESISER[0] = GregTechAPI.registerMetaTileEntity(1228, new MetaTileEntityCropSynthesiser(gtrId("crop_synthesiser.lv"), RecipeMapCropSynthesizer.RECIPE_MAP, Textures.SCANNER, GTValues.LV));
            CROP_SYNTHESISER[1] = GregTechAPI.registerMetaTileEntity(1229, new MetaTileEntityCropSynthesiser(gtrId("crop_synthesiser.mv"), RecipeMapCropSynthesizer.RECIPE_MAP, Textures.SCANNER, GTValues.MV));
            CROP_SYNTHESISER[2] = GregTechAPI.registerMetaTileEntity(1230, new MetaTileEntityCropSynthesiser(gtrId("crop_synthesiser.hv"), RecipeMapCropSynthesizer.RECIPE_MAP, Textures.SCANNER, GTValues.HV));
            CROP_SYNTHESISER[3] = GregTechAPI.registerMetaTileEntity(1231, new MetaTileEntityCropSynthesiser(gtrId("crop_synthesiser.ev"), RecipeMapCropSynthesizer.RECIPE_MAP, Textures.SCANNER, GTValues.EV));
            CROP_SYNTHESISER[4] = GregTechAPI.registerMetaTileEntity(1232, new MetaTileEntityCropSynthesiser(gtrId("crop_synthesiser.iv"), RecipeMapCropSynthesizer.RECIPE_MAP, Textures.SCANNER, GTValues.IV));

            CROP_REPLICATOR[0] = GregTechAPI.registerMetaTileEntity(1233, new MetaTileEntityCropReplicator(gtrId("crop_replicator.lv"), RecipeMapCropReplicator.RECIPE_MAP, Textures.SCANNER, GTValues.LV));
            CROP_REPLICATOR[1] = GregTechAPI.registerMetaTileEntity(1234, new MetaTileEntityCropReplicator(gtrId("crop_replicator.mv"), RecipeMapCropReplicator.RECIPE_MAP, Textures.SCANNER, GTValues.MV));
            CROP_REPLICATOR[2] = GregTechAPI.registerMetaTileEntity(1235, new MetaTileEntityCropReplicator(gtrId("crop_replicator.hv"), RecipeMapCropReplicator.RECIPE_MAP, Textures.SCANNER, GTValues.HV));
            CROP_REPLICATOR[3] = GregTechAPI.registerMetaTileEntity(1236, new MetaTileEntityCropReplicator(gtrId("crop_replicator.ev"), RecipeMapCropReplicator.RECIPE_MAP, Textures.SCANNER, GTValues.EV));
            CROP_REPLICATOR[4] = GregTechAPI.registerMetaTileEntity(1237, new MetaTileEntityCropReplicator(gtrId("crop_replicator.iv"), RecipeMapCropReplicator.RECIPE_MAP, Textures.SCANNER, GTValues.IV));

        }

        SCANNER[0] = GregTechAPI.registerMetaTileEntity(1238, new MetaTileEntityScanner(gtrId("scanner.lv"), RecipeMapScanner.RECIPE_MAP, Textures.SCANNER, GTValues.LV));
        SCANNER[1] = GregTechAPI.registerMetaTileEntity(1239, new MetaTileEntityScanner(gtrId("scanner.mv"), RecipeMapScanner.RECIPE_MAP, Textures.SCANNER, GTValues.MV));
        SCANNER[2] = GregTechAPI.registerMetaTileEntity(1240, new MetaTileEntityScanner(gtrId("scanner.hv"), RecipeMapScanner.RECIPE_MAP, Textures.SCANNER, GTValues.HV));
        SCANNER[3] = GregTechAPI.registerMetaTileEntity(1241, new MetaTileEntityScanner(gtrId("scanner.ev"), RecipeMapScanner.RECIPE_MAP, Textures.SCANNER, GTValues.EV));
        SCANNER[4] = GregTechAPI.registerMetaTileEntity(1242, new MetaTileEntityScanner(gtrId("scanner.iv"), RecipeMapScanner.RECIPE_MAP, Textures.SCANNER, GTValues.IV));

        RECYCLER[0] = GregTechAPI.registerMetaTileEntity(1243, new SimpleMachineMetaTileEntity(gtrId("recycler.lv"), RecipeMapRecycler.RECIPE_MAP, Textures.RECYCLER, GTValues.LV));
        RECYCLER[1] = GregTechAPI.registerMetaTileEntity(1244, new SimpleMachineMetaTileEntity(gtrId("recycler.mv"), RecipeMapRecycler.RECIPE_MAP, Textures.RECYCLER, GTValues.MV));
        RECYCLER[2] = GregTechAPI.registerMetaTileEntity(1245, new SimpleMachineMetaTileEntity(gtrId("recycler.hv"), RecipeMapRecycler.RECIPE_MAP, Textures.RECYCLER, GTValues.HV));
        RECYCLER[3] = GregTechAPI.registerMetaTileEntity(1246, new SimpleMachineMetaTileEntity(gtrId("recycler.ev"), RecipeMapRecycler.RECIPE_MAP, Textures.RECYCLER, GTValues.EV));

        AMPLIFABRICATOR[0] = GregTechAPI.registerMetaTileEntity(1247, new SimpleMachineMetaTileEntity(gtrId("amplifabricator.lv"), RecipeMaps.AMPLIFABRICATOR_RECIPES, Textures.AMPLIFABRICATOR, GTValues.LV));
        AMPLIFABRICATOR[1] = GregTechAPI.registerMetaTileEntity(1248, new SimpleMachineMetaTileEntity(gtrId("amplifabricator.mv"), RecipeMaps.AMPLIFABRICATOR_RECIPES, Textures.AMPLIFABRICATOR, GTValues.MV));
        AMPLIFABRICATOR[2] = GregTechAPI.registerMetaTileEntity(1249, new SimpleMachineMetaTileEntity(gtrId("amplifabricator.hv"), RecipeMaps.AMPLIFABRICATOR_RECIPES, Textures.AMPLIFABRICATOR, GTValues.HV));
        AMPLIFABRICATOR[3] = GregTechAPI.registerMetaTileEntity(1250, new SimpleMachineMetaTileEntity(gtrId("amplifabricator.ev"), RecipeMaps.AMPLIFABRICATOR_RECIPES, Textures.AMPLIFABRICATOR, GTValues.EV));
        AMPLIFABRICATOR[4] = GregTechAPI.registerMetaTileEntity(1270, new SimpleMachineMetaTileEntity(gtrId("amplifabricator.iv"), RecipeMaps.AMPLIFABRICATOR_RECIPES, Textures.AMPLIFABRICATOR, GTValues.IV));

        REPLICATOR[0] = GregTechAPI.registerMetaTileEntity(1271, new SimpleMachineMetaTileEntity(gtrId("replicator.lv"), RecipeMaps.REPLICATOR_RECIPES, Textures.REPLICATOR, GTValues.LV));
        REPLICATOR[1] = GregTechAPI.registerMetaTileEntity(1272, new SimpleMachineMetaTileEntity(gtrId("replicator.mv"), RecipeMaps.REPLICATOR_RECIPES, Textures.REPLICATOR, GTValues.MV));
        REPLICATOR[2] = GregTechAPI.registerMetaTileEntity(1273, new SimpleMachineMetaTileEntity(gtrId("replicator.hv"), RecipeMaps.REPLICATOR_RECIPES, Textures.REPLICATOR, GTValues.HV));
        REPLICATOR[3] = GregTechAPI.registerMetaTileEntity(1274, new SimpleMachineMetaTileEntity(gtrId("replicator.ev"), RecipeMaps.REPLICATOR_RECIPES, Textures.REPLICATOR, GTValues.EV));
        REPLICATOR[4] = GregTechAPI.registerMetaTileEntity(1275, new SimpleMachineMetaTileEntity(gtrId("replicator.iv"), RecipeMaps.REPLICATOR_RECIPES, Textures.REPLICATOR, GTValues.IV));

        MINER[0] = GregTechAPI.registerMetaTileEntity(1276, new MetaTileEntityMiner(gtrId("miner.lv"), GTValues.LV));
        MINER[1] = GregTechAPI.registerMetaTileEntity(1277, new MetaTileEntityMiner(gtrId("miner.mv"), GTValues.MV));
        MINER[2] = GregTechAPI.registerMetaTileEntity(1278, new MetaTileEntityMiner(gtrId("miner.hv"), GTValues.HV));
        MINER[3] = GregTechAPI.registerMetaTileEntity(1279, new MetaTileEntityMiner(gtrId("miner.ev"), GTValues.EV));
        MINER[4] = GregTechAPI.registerMetaTileEntity(1280, new MetaTileEntityMiner(gtrId("miner.iv"), GTValues.IV));

        DIESEL_GENERATOR[0] = GregTechAPI.registerMetaTileEntity(480, new SimpleGeneratorMetaTileEntity(gtrId("diesel_generator.lv"), RecipeMaps.DIESEL_GENERATOR_FUELS, Textures.DIESEL_GENERATOR_OVERLAY, GTValues.LV));
        DIESEL_GENERATOR[1] = GregTechAPI.registerMetaTileEntity(481, new SimpleGeneratorMetaTileEntity(gtrId("diesel_generator.mv"), RecipeMaps.DIESEL_GENERATOR_FUELS, Textures.DIESEL_GENERATOR_OVERLAY, GTValues.MV));
        DIESEL_GENERATOR[2] = GregTechAPI.registerMetaTileEntity(482, new SimpleGeneratorMetaTileEntity(gtrId("diesel_generator.hv"), RecipeMaps.DIESEL_GENERATOR_FUELS, Textures.DIESEL_GENERATOR_OVERLAY, GTValues.HV));

        STEAM_TURBINE[0] = GregTechAPI.registerMetaTileEntity(485, new SimpleGeneratorMetaTileEntity(gtrId("steam_turbine.lv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, GTValues.LV));
        STEAM_TURBINE[1] = GregTechAPI.registerMetaTileEntity(486, new SimpleGeneratorMetaTileEntity(gtrId("steam_turbine.mv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, GTValues.MV));
        STEAM_TURBINE[2] = GregTechAPI.registerMetaTileEntity(487, new SimpleGeneratorMetaTileEntity(gtrId("steam_turbine.hv"), RecipeMaps.STEAM_TURBINE_FUELS, Textures.STEAM_TURBINE_OVERLAY, GTValues.HV));

        GAS_TURBINE[0] = GregTechAPI.registerMetaTileEntity(490, new SimpleGeneratorMetaTileEntity(gtrId("gas_turbine.lv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, GTValues.LV));
        GAS_TURBINE[1] = GregTechAPI.registerMetaTileEntity(491, new SimpleGeneratorMetaTileEntity(gtrId("gas_turbine.mv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, GTValues.MV));
        GAS_TURBINE[2] = GregTechAPI.registerMetaTileEntity(492, new SimpleGeneratorMetaTileEntity(gtrId("gas_turbine.hv"), RecipeMaps.GAS_TURBINE_FUELS, Textures.GAS_TURBINE_OVERLAY, GTValues.HV));

        MAGIC_ENERGY_ABSORBER = GregTechAPI.registerMetaTileEntity(493, new MetaTileEntityMagicEnergyAbsorber(gtrId("magic_energy_absorber")));

        ITEM_COLLECTOR[0] = GregTechAPI.registerMetaTileEntity(494, new MetaTileEntityItemCollector(gtrId("item_collector.lv"), 1, 8));
        ITEM_COLLECTOR[1] = GregTechAPI.registerMetaTileEntity(495, new MetaTileEntityItemCollector(gtrId("item_collector.mv"), 2, 16));
        ITEM_COLLECTOR[2] = GregTechAPI.registerMetaTileEntity(496, new MetaTileEntityItemCollector(gtrId("item_collector.hv"), 3, 32));
        ITEM_COLLECTOR[3] = GregTechAPI.registerMetaTileEntity(497, new MetaTileEntityItemCollector(gtrId("item_collector.ev"), 4, 64));

        for (int i = 0; i < HULL.length; i++) {
            MetaTileEntityHull metaTileEntity = new MetaTileEntityHull(gtrId("hull." + GTValues.VN[i].toLowerCase()), i);
            GregTechAPI.registerMetaTileEntity(500 + i, metaTileEntity);
            HULL[i] = metaTileEntity;
        }

        PRIMITIVE_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(510, new MetaTileEntityPrimitiveBlastFurnace(gtrId("primitive_blast_furnace.bronze")));
        ELECTRIC_BLAST_FURNACE = GregTechAPI.registerMetaTileEntity(511, new MetaTileEntityElectricBlastFurnace(gtrId("electric_blast_furnace")));
        VACUUM_FREEZER = GregTechAPI.registerMetaTileEntity(512, new MetaTileEntityVacuumFreezer(gtrId("vacuum_freezer")));
        IMPLOSION_COMPRESSOR = GregTechAPI.registerMetaTileEntity(513, new MetaTileEntityImplosionCompressor(gtrId("implosion_compressor")));
        PYROLYSE_OVEN = GregTechAPI.registerMetaTileEntity(514, new MetaTileEntityPyrolyseOven(gtrId("pyrolyse_oven")));
        DISTILLATION_TOWER = GregTechAPI.registerMetaTileEntity(515, new MetaTileEntityDistillationTower(gtrId("distillation_tower")));
        MULTI_FURNACE = GregTechAPI.registerMetaTileEntity(516, new MetaTileEntityMultiFurnace(gtrId("multi_furnace")));
        DIESEL_ENGINE = GregTechAPI.registerMetaTileEntity(517, new MetaTileEntityDieselEngine(gtrId("diesel_engine")));
        CRACKER = GregTechAPI.registerMetaTileEntity(525, new MetaTileEntityCrackingUnit(gtrId("cracker")));

        LARGE_STEAM_TURBINE = GregTechAPI.registerMetaTileEntity(518, new MetaTileEntityLargeTurbine(gtrId("large_turbine.steam"), TurbineType.STEAM));
        LARGE_GAS_TURBINE = GregTechAPI.registerMetaTileEntity(519, new MetaTileEntityLargeTurbine(gtrId("large_turbine.gas"), TurbineType.GAS));
        LARGE_PLASMA_TURBINE = GregTechAPI.registerMetaTileEntity(520, new MetaTileEntityLargeTurbine(gtrId("large_turbine.plasma"), TurbineType.PLASMA));

        LARGE_BRONZE_BOILER = GregTechAPI.registerMetaTileEntity(521, new MetaTileEntityLargeBoiler(gtrId("large_boiler.bronze"), BoilerType.BRONZE));
        LARGE_STEEL_BOILER = GregTechAPI.registerMetaTileEntity(522, new MetaTileEntityLargeBoiler(gtrId("large_boiler.steel"), BoilerType.STEEL));
        LARGE_TITANIUM_BOILER = GregTechAPI.registerMetaTileEntity(523, new MetaTileEntityLargeBoiler(gtrId("large_boiler.titanium"), BoilerType.TITANIUM));
        LARGE_TUNGSTENSTEEL_BOILER = GregTechAPI.registerMetaTileEntity(524, new MetaTileEntityLargeBoiler(gtrId("large_boiler.tungstensteel"), BoilerType.TUNGSTENSTEEL));

        FUSION_REACTOR = GregTechAPI.registerMetaTileEntity(1100, new MetaTileEntityFusionReactor(gtrId("fusion_reactor"), GTValues.EV));

        COKE_OVEN = GregTechAPI.registerMetaTileEntity(526, new MetaTileEntityCokeOven(gtrId("coke_oven")));
        COKE_OVEN_HATCH = GregTechAPI.registerMetaTileEntity(527, new MetaTileEntityCokeOvenHatch(gtrId("coke_oven_hatch")));

        int[] batteryBufferSlots = new int[]{1, 4, 9, 16};
        for (int i = 0; i < GTValues.V.length; i++) {
            if (i > 0 && i <= TRANSFORMER.length) {
                MetaTileEntityTransformer transformer = new MetaTileEntityTransformer(gtrId("transformer." + GTValues.VN[i].toLowerCase()), i);
                TRANSFORMER[i - 1] = GregTechAPI.registerMetaTileEntity(600 + (i - 1), transformer);
            }
            if (i < BATTERY_BUFFER.length) {
                BATTERY_BUFFER[i] = new MetaTileEntityBatteryBuffer[batteryBufferSlots.length];
                for (int slot = 0; slot < batteryBufferSlots.length; slot++) {
                    String transformerId = "battery_buffer." + GTValues.VN[i].toLowerCase() + "." + batteryBufferSlots[slot];
                    MetaTileEntityBatteryBuffer batteryBuffer = new MetaTileEntityBatteryBuffer(gtrId(transformerId), i, batteryBufferSlots[slot]);
                    BATTERY_BUFFER[i][slot] = GregTechAPI.registerMetaTileEntity(610 + batteryBufferSlots.length * i + slot, batteryBuffer);
                }
            }
            String chargerId = "charger." + GTValues.VN[i].toLowerCase();
            MetaTileEntityCharger charger = new MetaTileEntityCharger(gtrId(chargerId), i, 4);
            CHARGER[i] = GregTechAPI.registerMetaTileEntity(680 + i, charger);
        }

        for (int i = 0; i < GTValues.V.length; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            ITEM_IMPORT_BUS[i] = new MetaTileEntityItemBus(gtrId("item_bus.import." + voltageName), i, false);
            ITEM_EXPORT_BUS[i] = new MetaTileEntityItemBus(gtrId("item_bus.export." + voltageName), i, true);
            FLUID_IMPORT_HATCH[i] = new MetaTileEntityFluidHatch(gtrId("fluid_hatch.import." + voltageName), i, false);
            FLUID_EXPORT_HATCH[i] = new MetaTileEntityFluidHatch(gtrId("fluid_hatch.export." + voltageName), i, true);


            ENERGY_INPUT_HATCH[i] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.input." + voltageName), i, false, 2);
            ENERGY_OUTPUT_HATCH[i] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.output." + voltageName), i, true, 2);


            /*
            ENERGY_INPUT_HATCH[i][0] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.input." + voltageName+"."+1), i, false, 1);
            ENERGY_OUTPUT_HATCH[i][0] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.output." + voltageName+"."+1), i, true, 1);

            ENERGY_INPUT_HATCH[i][1] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.input." + voltageName+"."+2), i, false, 2);
            ENERGY_OUTPUT_HATCH[i][1] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.output." + voltageName+"."+2), i, true, 2);

            ENERGY_INPUT_HATCH[i][2] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.input." + voltageName+"."+4), i, false, 4);
            ENERGY_OUTPUT_HATCH[i][2] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.output." + voltageName+"."+4), i, true, 4);

            ENERGY_INPUT_HATCH[i][3] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.input." + voltageName+"."+8), i, false, 8);
            ENERGY_OUTPUT_HATCH[i][3] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.output." + voltageName+"."+8), i, true, 8);

            ENERGY_INPUT_HATCH[i][4] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.input." + voltageName+"."+16), i, false, 16);
            ENERGY_OUTPUT_HATCH[i][4] = new MetaTileEntityEnergyHatch(gtrId("energy_hatch.output." + voltageName+"."+16), i, true, 16);
*/


            GregTechAPI.registerMetaTileEntity(700 + 10 * i, ITEM_IMPORT_BUS[i]);
            GregTechAPI.registerMetaTileEntity(700 + 10 * i + 1, ITEM_EXPORT_BUS[i]);
            GregTechAPI.registerMetaTileEntity(700 + 10 * i + 2, FLUID_IMPORT_HATCH[i]);
            GregTechAPI.registerMetaTileEntity(700 + 10 * i + 3, FLUID_EXPORT_HATCH[i]);
            /*GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 1, ENERGY_INPUT_HATCH[i][0]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 2, ENERGY_OUTPUT_HATCH[i][0]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 3, ENERGY_INPUT_HATCH[i][1]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 4, ENERGY_OUTPUT_HATCH[i][1]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 5, ENERGY_INPUT_HATCH[i][2]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 6, ENERGY_OUTPUT_HATCH[i][2]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 7, ENERGY_INPUT_HATCH[i][3]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 8, ENERGY_OUTPUT_HATCH[i][3]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 9, ENERGY_INPUT_HATCH[i][4]);
            GregTechAPI.registerMetaTileEntity(1250 + 11 * i + 10, ENERGY_OUTPUT_HATCH[i][4]);*/


            GregTechAPI.registerMetaTileEntity(1250 + 2*i + 1, ENERGY_INPUT_HATCH[i]);
            GregTechAPI.registerMetaTileEntity(1250 + 2*i + 2, ENERGY_OUTPUT_HATCH[i]);

        }

        ROTOR_HOLDER[0] = GregTechAPI.registerMetaTileEntity(817, new MetaTileEntityRotorHolder(gtrId("rotor_holder.hv"), GTValues.HV, 1.0f));
        ROTOR_HOLDER[1] = GregTechAPI.registerMetaTileEntity(818, new MetaTileEntityRotorHolder(gtrId("rotor_holder.ev"), GTValues.EV, 1.15f));
        ROTOR_HOLDER[2] = GregTechAPI.registerMetaTileEntity(819, new MetaTileEntityRotorHolder(gtrId("rotor_holder.iv"), GTValues.IV, 1.25f));

        SMALL_WOODEN_CHEST = GregTechAPI.registerMetaTileEntity(808, new MetaTileEntityChest(gtrId("small_wooden_chest"), Materials.Wood, 1, 1));
        WOODEN_CHEST = GregTechAPI.registerMetaTileEntity(807, new MetaTileEntityChest(gtrId("wooden_chest"), Materials.Wood, 9, 3));
        BRONZE_CHEST = GregTechAPI.registerMetaTileEntity(802, new MetaTileEntityChest(gtrId("bronze_chest"), Materials.Bronze, 9, 6));
        STEEL_CHEST = GregTechAPI.registerMetaTileEntity(803, new MetaTileEntityChest(gtrId("steel_chest"), Materials.Steel, 9, 8));
        STAINLESS_STEEL_CHEST = GregTechAPI.registerMetaTileEntity(804, new MetaTileEntityChest(gtrId("stainless_steel_chest"), Materials.StainlessSteel, 9, 10));
        TITANIUM_CHEST = GregTechAPI.registerMetaTileEntity(805, new MetaTileEntityChest(gtrId("titanium_chest"), Materials.Titanium, 12, 10));
        TUNGSTENSTEEL_CHEST = GregTechAPI.registerMetaTileEntity(806, new MetaTileEntityChest(gtrId("tungstensteel_chest"), Materials.TungstenSteel, 12, 14));

        WOODEN_TANK = GregTechAPI.registerMetaTileEntity(811, new MetaTileEntityTank(gtrId("wooden_tank"), Materials.Wood, 4000, 1, 3));
        BRONZE_TANK = GregTechAPI.registerMetaTileEntity(812, new MetaTileEntityTank(gtrId("bronze_tank"), Materials.Bronze, 8000, 4, 3));
        STEEL_TANK = GregTechAPI.registerMetaTileEntity(813, new MetaTileEntityTank(gtrId("steel_tank"), Materials.Steel, 16000, 7, 5));
        STAINLESS_STEEL_TANK = GregTechAPI.registerMetaTileEntity(814, new MetaTileEntityTank(gtrId("stainless_steel_tank"), Materials.StainlessSteel, 32000, 9, 7));
        TITANIUM_TANK = GregTechAPI.registerMetaTileEntity(815, new MetaTileEntityTank(gtrId("titanium_tank"), Materials.Titanium, 48000, 12, 9));
        TUNGSTENSTEEL_TANK = GregTechAPI.registerMetaTileEntity(816, new MetaTileEntityTank(gtrId("tungstensteel_tank"), Materials.TungstenSteel, 64000, 16, 9));

        FISHER[0] = GregTechAPI.registerMetaTileEntity(820, new MetaTileEntityFisher(gtrId("fisher.lv"), GTValues.LV));
        FISHER[1] = GregTechAPI.registerMetaTileEntity(821, new MetaTileEntityFisher(gtrId("fisher.mv"), GTValues.MV));
        FISHER[2] = GregTechAPI.registerMetaTileEntity(822, new MetaTileEntityFisher(gtrId("fisher.hv"), GTValues.HV));
        FISHER[3] = GregTechAPI.registerMetaTileEntity(823, new MetaTileEntityFisher(gtrId("fisher.ev"), GTValues.EV));

        LOCKED_SAFE = GregTechAPI.registerMetaTileEntity(824, new MetaTileEntityLockedSafe(gtrId("locked_safe")));
        WORKBENCH = GregTechAPI.registerMetaTileEntity(825, new MetaTileEntityWorkbench(gtrId("workbench")));
        ARMOR_TABLE = GregTechAPI.registerMetaTileEntity(826, new MetaTileEntityArmorTable(gtrId("armor_table")));

        for (int i = 1; i < 5; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            PUMP[i - 1] = new MetaTileEntityPump(gtrId("pump." + voltageName), i);
            AIR_COLLECTOR[i - 1] = new MetaTileEntityAirCollector(gtrId("air_collector." + voltageName), i);
            GregTechAPI.registerMetaTileEntity(900 + 10 * (i - 1), PUMP[i - 1]);
            GregTechAPI.registerMetaTileEntity(950 + 10 * (i - 1), AIR_COLLECTOR[i - 1]);
        }

        TESLA_COIL = new MetaTileEntityTeslaCoil(gtrId("tesla_coil"));
        GregTechAPI.registerMetaTileEntity(1001, TESLA_COIL);

        for (int i = 2; i < 5; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            QUANTUM_CHEST[i - 2] = new MetaTileEntityQuantumChest(gtrId("quantum_chest." + voltageName), i, 64 * 64000 * (i - 1));
            QUANTUM_TANK[i - 2] = new MetaTileEntityQuantumTank(gtrId("quantum_tank." + voltageName), i, 1000 * 64000 * (i - 1));
            GregTechAPI.registerMetaTileEntity(1010 + (i - 2), QUANTUM_CHEST[i - 2]);
            GregTechAPI.registerMetaTileEntity(1020 + (i - 2), QUANTUM_TANK[i - 2]);
        }

        for (int i = 1; i < 4; i++) {
            String voltageName = GTValues.VN[i].toLowerCase();
            BLOCK_BREAKER[i - 1] = new MetaTileEntityBlockBreaker(gtrId("block_breaker." + voltageName), i);
            GregTechAPI.registerMetaTileEntity(1030 + (i - 1), BLOCK_BREAKER[i - 1]);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < GTValues.DIODE_AMPS.length; j++) {
                MetaTileEntityCableDiode metaTileEntity = new MetaTileEntityCableDiode(gtrId("diode." + GTValues.VN[i].toLowerCase() + "." + GTValues.DIODE_AMPS[j]), i, GTValues.DIODE_AMPS[j]);
                GregTechAPI.registerMetaTileEntity(1040 + i + 6*j, metaTileEntity);
                CABLE_DIODES[i][j] = metaTileEntity;
            }
        }
        WORLD_ACCELERATOR[0] = GregTechAPI.registerMetaTileEntity(1200, new MetaTileEntityWorldAccelerator(gtrId("world_accelerator.lv"), GTValues.LV));
        WORLD_ACCELERATOR[1] = GregTechAPI.registerMetaTileEntity(1201, new MetaTileEntityWorldAccelerator(gtrId("world_accelerator.mv"), GTValues.MV));
        WORLD_ACCELERATOR[2] = GregTechAPI.registerMetaTileEntity(1202, new MetaTileEntityWorldAccelerator(gtrId("world_accelerator.hv"), GTValues.HV));
        WORLD_ACCELERATOR[3] = GregTechAPI.registerMetaTileEntity(1203, new MetaTileEntityWorldAccelerator(gtrId("world_accelerator.ev"), GTValues.EV));
        WORLD_ACCELERATOR[4] = GregTechAPI.registerMetaTileEntity(1204, new MetaTileEntityWorldAccelerator(gtrId("world_accelerator.iv"), GTValues.IV));

        LARGE_TRANSFORMER = GregTechAPI.registerMetaTileEntity(1205, new MetaTileEntityLargeTransformer(gtrId("large_transformer")));
        LARGE_BATTERY_BUFFER = GregTechAPI.registerMetaTileEntity(1206, new MetaTileEntityLargeBatteryBuffer(gtrId("large_battery_buffer")));
        BATTERY_HOLDER = GregTechAPI.registerMetaTileEntity(1207, new MetaTileEntityBatteryHolder(gtrId("battery_holder")));

        LHE = GregTechAPI.registerMetaTileEntity(1213, new MetaTileEntityLargeHeatExchanger(gtrId("lhe")));

        WIRELESS_CHARGER[0] = GregTechAPI.registerMetaTileEntity(1208, new MetaTileEntityWirelessCharger(gtrId("wireless_charger.lv"), GTValues.LV));
        WIRELESS_CHARGER[1] = GregTechAPI.registerMetaTileEntity(1209, new MetaTileEntityWirelessCharger(gtrId("wireless_charger.mv"), GTValues.MV));
        WIRELESS_CHARGER[2] = GregTechAPI.registerMetaTileEntity(1210, new MetaTileEntityWirelessCharger(gtrId("wireless_charger.hv"), GTValues.HV));
        WIRELESS_CHARGER[3] = GregTechAPI.registerMetaTileEntity(1211, new MetaTileEntityWirelessCharger(gtrId("wireless_charger.ev"), GTValues.EV));
        WIRELESS_CHARGER[4] = GregTechAPI.registerMetaTileEntity(1212, new MetaTileEntityWirelessCharger(gtrId("wireless_charger.iv"), GTValues.IV));

    }

     public static ResourceLocation gtrId(String name) {
        return new ResourceLocation(GTValues.MODID, name);
    }

}
