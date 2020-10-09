package gtr.loaders.recipe;

import gtr.api.recipes.ModHandler;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.ingredients.IntCircuitIngredient;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.DustMaterial;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.util.GTUtility;
import gtr.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.OreDictionary;
import static gtr.api.GTValues.L;
import static gtr.api.GTValues.B;

import java.util.List;

public class ChemistryRecipes {

    public static void init() {

        //Cracking recipes
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Ethane.getFluid(1000)).fluidOutputs(Materials.HydroCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Ethylene.getFluid(1000)).fluidOutputs(Materials.HydroCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Propene.getFluid(1000)).fluidOutputs(Materials.HydroCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Propane.getFluid(1000)).fluidOutputs(Materials.HydroCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.LightFuel.getFluid(1000)).fluidOutputs(Materials.HydroCrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Butane.getFluid(1000)).fluidOutputs(Materials.HydroCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Naphtha.getFluid(1000)).fluidOutputs(Materials.HydroCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.HydroCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Gas.getFluid(1000)).fluidOutputs(Materials.HydroCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Butene.getFluid(1000)).fluidOutputs(Materials.HydroCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Butadiene.getFluid(1000)).fluidOutputs(Materials.HydroCrackedButadiene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Ethane.getFluid(1000)).fluidOutputs(Materials.SteamCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Ethylene.getFluid(1000)).fluidOutputs(Materials.SteamCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Propene.getFluid(1000)).fluidOutputs(Materials.SteamCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Propane.getFluid(1000)).fluidOutputs(Materials.SteamCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.LightFuel.getFluid(1000)).fluidOutputs(Materials.SteamCrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Butane.getFluid(1000)).fluidOutputs(Materials.SteamCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Naphtha.getFluid(1000)).fluidOutputs(Materials.SteamCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.SteamCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Gas.getFluid(1000)).fluidOutputs(Materials.SteamCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Butene.getFluid(1000)).fluidOutputs(Materials.SteamCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Butadiene.getFluid(1000)).fluidOutputs(Materials.SteamCrackedButadiene.getFluid(1000)).buildAndRegister();

        //Distillation Recipes
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(Materials.Creosote.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedEthane.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(2000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.SteamCrackedEthane.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 2)).fluidOutputs(Materials.Methane.getFluid(1500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedEthylene.getFluid(1000)).fluidOutputs(Materials.Ethane.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.SteamCrackedEthylene.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon)).fluidOutputs(Materials.Methane.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedPropene.getFluid(1000)).fluidOutputs(Materials.Propane.getFluid(500), Materials.Ethylene.getFluid(500), Materials.Methane.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(180).EUt(120).fluidInputs(Materials.SteamCrackedPropene.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 6)).fluidOutputs(Materials.Methane.getFluid(1500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedPropane.getFluid(1000)).fluidOutputs(Materials.Ethane.getFluid(1000), Materials.Methane.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(Materials.SteamCrackedPropane.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 3)).fluidOutputs(Materials.Ethylene.getFluid(500), Materials.Methane.getFluid(3500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedLightFuel.getFluid(1000)).fluidOutputs(Materials.Naphtha.getFluid(800), Materials.Butane.getFluid(150), Materials.Propane.getFluid(200), Materials.Ethane.getFluid(125), Materials.Methane.getFluid(125)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.SteamCrackedLightFuel.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon)).fluidOutputs(Materials.HeavyFuel.getFluid(50), Materials.Naphtha.getFluid(100), Materials.Toluene.getFluid(30), Materials.Benzene.getFluid(150), Materials.Butene.getFluid(65), Materials.Butadiene.getFluid(50), Materials.Propane.getFluid(50), Materials.Propene.getFluid(250), Materials.Ethane.getFluid(50), Materials.Ethylene.getFluid(250), Materials.Methane.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(Materials.HydroCrackedButane.getFluid(750)).fluidOutputs(Materials.Propane.getFluid(500), Materials.Ethane.getFluid(500), Materials.Methane.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(Materials.SteamCrackedButane.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 9)).fluidOutputs(Materials.Propane.getFluid(250), Materials.Ethane.getFluid(250), Materials.Ethylene.getFluid(250), Materials.Methane.getFluid(4000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedNaphtha.getFluid(1000)).fluidOutputs(Materials.Butane.getFluid(800), Materials.Propane.getFluid(300), Materials.Ethane.getFluid(250), Materials.Methane.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.SteamCrackedNaphtha.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon, 3)).fluidOutputs(Materials.HeavyFuel.getFluid(25), Materials.LightFuel.getFluid(50), Materials.Toluene.getFluid(20), Materials.Benzene.getFluid(100), Materials.Butene.getFluid(50), Materials.Butadiene.getFluid(50), Materials.Propane.getFluid(15), Materials.Propene.getFluid(300), Materials.Ethane.getFluid(65), Materials.Ethylene.getFluid(500), Materials.Methane.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedHeavyFuel.getFluid(1000)).fluidOutputs(Materials.LightFuel.getFluid(600), Materials.Naphtha.getFluid(100), Materials.Butane.getFluid(100), Materials.Propane.getFluid(100), Materials.Ethane.getFluid(75), Materials.Methane.getFluid(75)).buildAndRegister();

        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.SteamCrackedHeavyFuel.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon, 3)).fluidOutputs(Materials.LightFuel.getFluid(100), Materials.Naphtha.getFluid(125), Materials.Toluene.getFluid(80), Materials.Benzene.getFluid(400), Materials.Butene.getFluid(80), Materials.Butadiene.getFluid(50), Materials.Propane.getFluid(10), Materials.Propene.getFluid(100), Materials.Ethane.getFluid(15), Materials.Ethylene.getFluid(150), Materials.Methane.getFluid(150)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.HydroCrackedGas.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(1400), Materials.Hydrogen.getFluid(1340), Materials.Helium.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(96).EUt(120).fluidInputs(Materials.SteamCrackedGas.getFluid(800)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon)).fluidOutputs(Materials.Propene.getFluid(6), Materials.Ethane.getFluid(6), Materials.Ethylene.getFluid(20), Materials.Methane.getFluid(914), Materials.Helium.getFluid(16)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(Materials.HydroCrackedButene.getFluid(750)).fluidOutputs(Materials.Butane.getFluid(250), Materials.Propene.getFluid(250), Materials.Ethane.getFluid(250), Materials.Methane.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(Materials.SteamCrackedButene.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon, 3)).fluidOutputs(Materials.Propene.getFluid(250), Materials.Ethylene.getFluid(625), Materials.Methane.getFluid(3000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(Materials.HydroCrackedButadiene.getFluid(750)).fluidOutputs(Materials.Butene.getFluid(500), Materials.Ethylene.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(Materials.SteamCrackedButadiene.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon, 2)).fluidOutputs(Materials.Propene.getFluid(250), Materials.Ethylene.getFluid(375), Materials.Methane.getFluid(2250)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(96).fluidInputs(Materials.OilLight.getFluid(150)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(10), Materials.SulfuricLightFuel.getFluid(20), Materials.SulfuricNaphtha.getFluid(30), Materials.SulfuricGas.getFluid(240)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(96).fluidInputs(Materials.OilMedium.getFluid(100)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getFluid(60)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(288).fluidInputs(Materials.OilHeavy.getFluid(150)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(250), Materials.SulfuricLightFuel.getFluid(45), Materials.SulfuricNaphtha.getFluid(15), Materials.SulfuricGas.getFluid(600)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(96).fluidInputs(Materials.Oil.getFluid(50)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getFluid(60)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(600).EUt(64).fluidInputs(Materials.DilutedHydrochloricAcid.getFluid(2000)).fluidOutputs(Materials.Water.getFluid(1000), Materials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(600).EUt(120).fluidInputs(Materials.DilutedSulfuricAcid.getFluid(3000)).fluidOutputs(Materials.SulfuricAcid.getFluid(2000), Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(Materials.WoodTar.getFluid(1000)).fluidOutputs(Materials.Creosote.getFluid(500), Materials.Phenol.getFluid(75), Materials.Benzene.getFluid(350), Materials.Toluene.getFluid(75)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(160).EUt(120).fluidInputs(Materials.Water.getFluid(576)).fluidOutputs(Materials.DistilledWater.getFluid(520)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(80).EUt(480).fluidInputs(Materials.CalciumAcetate.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Quicklime, 3)).fluidOutputs(Materials.Acetone.getFluid(1000), Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(80).EUt(640).fluidInputs(Materials.Acetone.getFluid(1000)).fluidOutputs(Materials.Ethenone.getFluid(1000), Materials.Methane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(5).fluidInputs(Materials.Gas.getFluid(8000)).fluidOutputs(Materials.Methane.getFluid(4000), Materials.LPG.getFluid(4000)).buildAndRegister();

        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(160).EUt(24).circuitMeta(1).fluidInputs(Materials.Toluene.getFluid(30)).fluidOutputs(Materials.LightFuel.getFluid(30)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).circuitMeta(1).fluidInputs(Materials.HeavyFuel.getFluid(10)).fluidOutputs(Materials.Toluene.getFluid(4)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).circuitMeta(2).fluidInputs(Materials.HeavyFuel.getFluid(10)).fluidOutputs(Materials.Benzene.getFluid(4)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(32).EUt(24).circuitMeta(3).fluidInputs(Materials.HeavyFuel.getFluid(20)).fluidOutputs(Materials.Phenol.getFluid(5)).buildAndRegister();

        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).fluidInputs(Materials.OilLight.getFluid(300)).circuitMeta(4).fluidOutputs(Materials.Oil.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).fluidInputs(Materials.OilMedium.getFluid(200)).circuitMeta(4).fluidOutputs(Materials.Oil.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).fluidInputs(Materials.OilHeavy.getFluid(100)).circuitMeta(4).fluidOutputs(Materials.Oil.getFluid(100)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(1000), Materials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), Materials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), Materials.Tetrafluoroethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), Materials.Tetrafluoroethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(4320)).buildAndRegister();


        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricLightFuel.getFluid(12000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.LightFuel.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricHeavyFuel.getFluid(8000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.HeavyFuel.getFluid(8000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricNaphtha.getFluid(12000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.Naphtha.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricGas.getFluid(16000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.Gas.getFluid(16000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.NaturalGas.getFluid(16000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.Gas.getFluid(16000)).buildAndRegister();



        List<Tuple<ItemStack, Integer>> seedEntries = GTUtility.getGrassSeedEntries();
        for (Tuple<ItemStack, Integer> seedEntry : seedEntries) {
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .duration(32).EUt(2)
                .inputs(seedEntry.getFirst())
                .fluidOutputs(Materials.SeedOil.getFluid(10))
                .buildAndRegister();
        }

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.WHEAT_SEEDS)).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.BEETROOT_SEEDS)).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, Materials.SodiumHydroxide).fluidInputs(Materials.SeedOil.getFluid(6000), Materials.Methanol.getFluid(1000)).fluidOutputs(Materials.Glycerol.getFluid(1000), Materials.BioDiesel.getFluid(6000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, Materials.SodiumHydroxide).fluidInputs(Materials.SeedOil.getFluid(6000), Materials.Ethanol.getFluid(1000)).fluidOutputs(Materials.Glycerol.getFluid(1000), Materials.BioDiesel.getFluid(6000)).buildAndRegister();



        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Materials.Honey.getFluid(180)).fluidOutputs(Materials.Biomass.getFluid(270)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(600).EUt(3).input("treeSapling", 1).fluidInputs(Materials.Honey.getFluid(100)).fluidOutputs(Materials.Biomass.getFluid(150)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Materials.Honey.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
























        ModHandler.removeRecipes(new ItemStack(Items.GOLDEN_APPLE));
        ModHandler.removeRecipes(new ItemStack(Items.GOLDEN_APPLE, 1, 1));
        ModHandler.removeRecipes(Items.SPECKLED_MELON);
        ModHandler.removeRecipes(Items.GOLDEN_CARROT);
        ModHandler.removeRecipes(new ItemStack(Blocks.TNT));

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.MELON, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.nugget, Materials.Gold, 8).outputs(new ItemStack(Items.SPECKLED_MELON)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.CARROT, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.nugget, Materials.Gold, 8).outputs(new ItemStack(Items.GOLDEN_CARROT)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.APPLE, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.ingot, Materials.Gold, 8).outputs(new ItemStack(Items.GOLDEN_APPLE)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.APPLE, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.block, Materials.Gold, 8).outputs(new ItemStack(Items.GOLDEN_APPLE, 1, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(24).inputs(new ItemStack(Items.SUGAR, 4, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.SulfuricAcid.getFluid(250), Materials.NitricAcid.getFluid(250)).outputs(new ItemStack(Blocks.TNT)).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(80).EUt(16).input(OrePrefix.dust, Materials.Quicklime).fluidInputs(Materials.AceticAcid.getFluid(B * 2)).fluidOutputs(Materials.CalciumAcetate.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(80).EUt(16).input(OrePrefix.dust, Materials.Calcium).fluidInputs(Materials.AceticAcid.getFluid(B * 2)).fluidOutputs(Materials.CalciumAcetate.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(240).EUt(16).input(OrePrefix.dust, Materials.Calcite).fluidInputs(Materials.AceticAcid.getFluid(B * 2)).fluidOutputs(Materials.CalciumAcetate.getFluid(B * 2)).buildAndRegister();

        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(Materials.Acetone.getFluid(100)).fluidOutputs(Materials.Ethenone.getFluid(100)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(Materials.CalciumAcetate.getFluid(200)).fluidOutputs(Materials.Acetone.getFluid(200)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(190).fluidInputs(Materials.SulfuricAcid.getFluid(B / 4), Materials.NitricAcid.getFluid(B / 4), Materials.Toluene.getFluid(B / 4)).outputs(new ItemStack(Blocks.TNT, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(950).EUt(30).fluidInputs(Materials.Water.getFluid(B * 2), Materials.NitrogenDioxide.getFluid(B * 4), Materials.Oxygen.getFluid(B)).fluidOutputs(Materials.NitricAcid.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(Materials.Ethylene.getFluid(B), Materials.AceticAcid.getFluid(B * 2), Materials.Methanol.getFluid(B)).fluidOutputs(Materials.Glue.getFluid(B * 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).input(OrePrefix.dust, Materials.Quicklime).fluidInputs(Materials.CarbonDioxide.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Calcite)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).input(OrePrefix.dust, Materials.Calcite).notConsumable(new IntCircuitIngredient(1)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Quicklime)).fluidOutputs(Materials.CarbonDioxide.getFluid(B)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Isoprene.getFluid(B), Materials.Air.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Isoprene.getFluid(B), Materials.Oxygen.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(Materials.Butadiene.getFluid(800), Materials.Styrene.getFluid(200), Materials.Air.getFluid(B * 3)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawStyreneButadieneRubber)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(Materials.Butadiene.getFluid(800), Materials.Styrene.getFluid(200), Materials.Oxygen.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawStyreneButadieneRubber, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dust, Materials.Polydimethylsiloxane, 9).input(OrePrefix.dust, Materials.Sulfur).fluidOutputs(Materials.SiliconeRubber.getFluid(1296)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).input(OrePrefix.dust, Materials.Silicon).fluidInputs(Materials.Epichlorhydrin.getFluid(144)).fluidOutputs(Materials.SiliconeRubber.getFluid(144)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Propene.getFluid(B * 2)).fluidOutputs(Materials.Methane.getFluid(B), Materials.Isoprene.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(3500).EUt(30).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Hydrogen.getFluid(B * 4)).fluidOutputs(Materials.Methane.getFluid(B * 5)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Ethylene.getFluid(B), Materials.Propene.getFluid(B)).fluidOutputs(Materials.Hydrogen.getFluid(B * 2), Materials.Isoprene.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dust, Materials.RawStyreneButadieneRubber, 9).input(OrePrefix.dust, Materials.Sulfur).fluidOutputs(Materials.StyreneButadieneRubber.getFluid(L * 9)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(B * 8).input(OrePrefix.dust, Materials.Sulfur, 1).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.Oxygen.getFluid(B * 4)).fluidOutputs(Materials.SodiumPersulfate.getFluid(B * 6)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(2700).input(OrePrefix.dust, Materials.Carbon, 1).fluidInputs(Materials.Water.getFluid(B * 2), Materials.Nitrogen.getFluid(B)).fluidOutputs(Materials.Glyceryl.getFluid(B * 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).fluidInputs(Materials.HydrogenSulfide.getFluid(B * 2), ModHandler.getWater(B * 2)).fluidOutputs(Materials.SulfuricAcid.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).input(OrePrefix.dust, Materials.Saltpeter, 1).fluidInputs(Materials.Naphtha.getFluid(B / 5)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Potassium, 1)).fluidOutputs(Materials.Polycaprolactam.getFluid(L * 9)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).input(OrePrefix.dust, Materials.Silicon, 1).fluidInputs(Materials.Epichlorhydrin.getFluid(B)).fluidOutputs(Materials.Silicone.getFluid(L)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(Materials.Epichlorhydrin.getFluid(B), Materials.Naphtha.getFluid(B * 3), Materials.NitrogenDioxide.getFluid(B)).fluidOutputs(Materials.Epoxid.getFluid(L * 2)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.NetherQuartz, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.Water.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.CertusQuartz, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.Water.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.CertusQuartz, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.Quartzite, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.Water.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.Quartzite,3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.NetherQuartz, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.DistilledWater.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.CertusQuartz, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.DistilledWater.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.CertusQuartz, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.Quartzite, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.DistilledWater.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.Quartzite, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(B).input(OrePrefix.dust, Materials.Uraninite, 1).input(OrePrefix.dust, Materials.Aluminium, 1).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Uranium, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(B).input(OrePrefix.dust, Materials.Uraninite, 1).input(OrePrefix.dust, Materials.Magnesium, 1).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Uranium, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.Calcium, 1).input(OrePrefix.dust, Materials.Carbon, 1).fluidInputs(Materials.Oxygen.getFluid(B * 3)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Calcite, 5)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1150).input(OrePrefix.dust, Materials.Sulfur, 1).fluidInputs(Materials.Water.getFluid(B * 2)).fluidOutputs(Materials.SulfuricAcid.getFluid(B * 3)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).input(OrePrefix.crushedPurified, Materials.Chalcopyrite).fluidInputs(Materials.NitricAcid.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.PlatinumGroupSludge)).fluidOutputs(Materials.BlueVitriolSolution.getFluid(9000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).input(OrePrefix.crushedPurified, Materials.Pentlandite).fluidInputs(Materials.NitricAcid.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.PlatinumGroupSludge)).fluidOutputs(Materials.NickelSulfateSolution.getFluid(9000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(B * 8).input(OrePrefix.dust, Materials.Sulfur, 1).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.Oxygen.getFluid(B * 4)).fluidOutputs(Materials.SodiumPersulfate.getFluid(B * 6)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(2700).input(OrePrefix.dust, Materials.Carbon, 1).fluidInputs(Materials.Water.getFluid(B * 2), Materials.Nitrogen.getFluid(B)).fluidOutputs(Materials.Glyceryl.getFluid(B * 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(480).input(OrePrefix.dust, Materials.Rutile, 1).input(OrePrefix.dust, Materials.Carbon, 3).fluidInputs(Materials.Chlorine.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust,Materials.Ash,1)).fluidOutputs(Materials.TitaniumTetrachloride.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(240).input(OrePrefix.dust, Materials.Sodium, 1).input(OrePrefix.dust, Materials.MagnesiumChloride, 2).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Magnesium)).fluidOutputs(Materials.Chlorine.getFluid(B + (B / 2))).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(16).input(OrePrefix.dust, Materials.RawRubber, 9).input(OrePrefix.dust, Materials.Sulfur, 1).fluidOutputs(Materials.Rubber.getFluid(L * 9)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(96).input(OrePrefix.dust, Materials.Sulfur).fluidInputs(Materials.NitricAcid.getFluid(B)).fluidOutputs(Materials.SulfurDioxide.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(24).fluidInputs(Materials.SulfurDioxide.getFluid(B), Materials.HydrogenSulfide.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Sulfur, 3)).fluidOutputs(ModHandler.getWater(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(24).fluidInputs(Materials.SulfurDioxide.getFluid(B * 2), ModHandler.getWater(B * 2)).fluidOutputs(Materials.Oxygen.getFluid(B), Materials.SulfuricAcid.getFluid(B * 2)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60000).EUt(8).input(OrePrefix.ingot, Materials.Plutonium239, 3).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Plutonium239, 3)).fluidOutputs(Materials.Radon.getFluid(B / 20)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(B), Materials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(B), Materials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), Materials.Tetrafluoroethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(4320)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), Materials.Tetrafluoroethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(4320)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).input(OrePrefix.dust, Materials.SodiumHydroxide).fluidInputs(Materials.Epichlorhydrin.getFluid(B), Materials.BisphenolA.getFluid(B)).fluidOutputs(Materials.Epoxid.getFluid(B), Materials.SaltWater.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(320).input(OrePrefix.dust, Materials.Carbon, 2).input(OrePrefix.dust, Materials.Rutile).fluidInputs(Materials.Chlorine.getFluid(B * 4)).fluidOutputs(Materials.CarbonMonoxide.getFluid(B * 2), Materials.TitaniumTetrachloride.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(22).fluidInputs(Materials.Toluene.getFluid(B), Materials.Oxygen.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ash, 2)).fluidOutputs(Materials.Phenol.getFluid(B), Materials.Water.getFluid(B)).buildAndRegister();

        //Preparations for various plastics
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(120).EUt(44).fluidInputs(Materials.EthyleneDichloride.getFluid(B), Materials.Steam.getFluid(B/10)).fluidOutputs(Materials.VinylChloride.getFluid(B), Materials.HydrochloricAcid.getFluid(B)).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.dustTiny, Materials.Zinc, 4).fluidInputs(Materials.AceticAcid.getFluid(B)).fluidOutputs(Materials.VinylAcetate.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(42).input(OrePrefix.dustTiny, Materials.Palladium).fluidInputs(Materials.AceticAcid.getFluid(B * 2), Materials.Oxygen.getFluid(B), Materials.Ethylene.getFluid(B * 2)).fluidOutputs(Materials.VinylAcetate.getFluid(B * 5)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(110).EUt(24).fluidInputs(Materials.PolyvinylAcetate.getFluid(L), Materials.Acetone.getFluid(B * 3)).fluidOutputs(Materials.Glue.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(110).EUt(24).fluidInputs(Materials.PolyvinylAcetate.getFluid(L), Materials.MethylAcetate.getFluid(B * 3)).fluidOutputs(Materials.Glue.getFluid(B * 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120).input(OrePrefix.dust, Materials.Barite).input(OrePrefix.gem, Materials.Coke, 2).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BariumSulfide)).fluidOutputs(Materials.CarbonDioxide.getFluid(B * 2)).buildAndRegister();







       // RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(400).EUt(2).fluidInputs(Materials.CarbonDioxide.getFluid(B)).input(OrePrefix.dust, Materials.BariumSulfide).fluidOutputs(Materials.Witherite.getFluid(B)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.HydrochloricAcid.getFluid(1000), Materials.Acetone.getFluid(1000), Materials.Phenol.getFluid(2000)).fluidOutputs(Materials.BisphenolA.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();



        ModHandler.addSmeltingRecipe(OreDictUnifier.get(OrePrefix.dust, Materials.Witherite), OreDictUnifier.get(OrePrefix.dust, Materials.BariumOxide));
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120).input(OrePrefix.dust, Materials.BariumOxide, 2).fluidInputs(Materials.Oxygen.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BariumPeroxide)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(14).fluidInputs(Materials.SulfuricAcid.getFluid(B), Materials.Ammonia.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.AmmoniumSulfate)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(700).EUt(55).fluidInputs(Materials.SulfuricAcid.getFluid(B)).input(OrePrefix.dust, Materials.AmmoniumSulfate).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.AmmoniumPersulfate)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(320).fluidInputs(Materials.Chloroform.getFluid(B), Materials.HydrofluoricAcid.getFluid(B * 2)).fluidOutputs(Materials.Chlorodifluoromethane.getFluid(B), Materials.HydrochloricAcid.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).inputs(OreDictUnifier.get(OrePrefix.log, Materials.Wood, 4)).fluidInputs(Materials.Chlorodifluoromethane.getFluid(B * 2)).outputs(new ItemStack(Items.COAL, 5, 1)).fluidOutputs(Materials.Tetrafluoroethylene.getFluid(B)).duration(320).EUt(96).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120).fluidInputs(Materials.Chlorine.getFluid(B * 6)).input(OrePrefix.dust, Materials.Iron, 2).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.FerricChloride, 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(150).fluidInputs(Materials.HydrochloricAcid.getFluid(B * 2)).input(OrePrefix.dust, Materials.Iron, 2).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.FerrousChloride)).fluidOutputs(Materials.Hydrogen.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(150).fluidInputs(Materials.HydrochloricAcid.getFluid(B * 8)).input(OrePrefix.dust, Materials.Magnetite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.FerrousChloride)).fluidOutputs(Materials.FerricChloride.getFluid(L * 2), Materials.Water.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(55).EUt(8).fluidInputs(Materials.FerrousChloride.getFluid(L * 2), Materials.Chlorine.getFluid(B * 2)).fluidOutputs(Materials.FerricChloride.getFluid(L * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(55).EUt(8).fluidInputs(Materials.FerrousChloride.getFluid(L * 4), Materials.Oxygen.getFluid(B * 2), Materials.HydrochloricAcid.getFluid(B * 4)).fluidOutputs(Materials.FerricChloride.getFluid(L * 4), Materials.Water.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(300).EUt(340).fluidInputs(Materials.Chlorine.getFluid(B * 2), Materials.Ethylene.getFluid(B)).input(OrePrefix.dust, Materials.FerricChloride).fluidOutputs(Materials.EthyleneDichloride.getFluid(B)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(200).EUt(340).fluidInputs(Materials.Chlorine.getFluid(B * 6), Materials.EthyleneDichloride.getFluid(B)).fluidOutputs(Materials.Tetrachloroethylene.getFluid(B), Materials.HydrochloricAcid.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(1094).fluidInputs(Materials.Tetrachloroethylene.getFluid(B * 2), Materials.HydrofluoricAcid.getFluid(B), Materials.Chloroform.getFluid(B / 2)).fluidOutputs(Materials.DichloroTrifluoroethane.getFluid(B + (B / 2)), Materials.Trichlorotrifluoroethane.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(410).fluidInputs(Materials.Methanol.getFluid(B * 2), Materials.Trichlorotrifluoroethane.getFluid(B * 2), Materials.Zinc.getFluid(L * 2)).fluidOutputs(Materials.Chlorotrifluoroethylene.getFluid(B * 2), Materials.Chlorine.getFluid(B * 2)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(B).EUt(320).fluidInputs(Materials.Propene.getFluid(B * 2), Materials.Ammonia.getFluid(B * 2), Materials.Air.getFluid(B * 3)).fluidOutputs(Materials.SOHIOMixture.getFluid(B), Materials.Water.getFluid(B * 6)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(Materials.SOHIOMixture.getFluid(B)).fluidOutputs(Materials.Acrylonitrile.getFluid(B / 2), Materials.Acetonitrile.getFluid(B / 2), Materials.HydrocyanicAcid.getFluid(B / 5), Materials.AmmoniumSulfate.getFluid(B / 10)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(B * 10), Materials.Ammonia.getFluid(B * 4)).fluidOutputs(Materials.NitricOxide.getFluid(B * 4), Materials.Water.getFluid(B * 6)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(480).fluidInputs(Materials.Ammonia.getFluid(B), Materials.Methanol.getFluid(B * 2)).fluidOutputs(Materials.Water.getFluid(B * 2), Materials.Dimethylamine.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.HypochlorousAcid.getFluid(B), Materials.Ammonia.getFluid(B)).fluidOutputs(Materials.Water.getFluid(B), Materials.Chloramine.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(B * 4), Materials.Ammonia.getFluid(B)).fluidOutputs(Materials.NitricAcid.getFluid(B), Materials.Water.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Sodium).fluidInputs(Materials.Water.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SodiumHydroxide)).fluidOutputs(Materials.Hydrogen.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).input(OrePrefix.dust, Materials.SodiumHydroxide).fluidInputs(Materials.AllylChloride.getFluid(B), Materials.HypochlorousAcid.getFluid(B)).fluidOutputs(Materials.Epichlorhydrin.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Propene.getFluid(B), Materials.Chlorine.getFluid(B * 2)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B), Materials.AllylChloride.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(320).fluidInputs(Materials.Ammonia.getFluid(B), Materials.CarbonDioxide.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Urea, 3)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(440).EUt(30).fluidInputs(Materials.SodiumCyanide.getFluid(B / 10), Materials.Acetone.getFluid(B / 10)).fluidOutputs(Materials.AcetoneCyanohydrin.getFluid(B / 5)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(20).EUt(24).fluidInputs(Materials.AcetoneCyanohydrin.getFluid(B / 10), Materials.Methanol.getFluid((B / 10) + (B / 20))).fluidOutputs(Materials.MethylMethacrylate.getFluid(B / 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(110).inputs(OreDictUnifier.get(OrePrefix.dust, Materials.Urea, 6), OreDictUnifier.get(OrePrefix.dust, Materials.SodiumHydroxide, 3)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SodiumCyanate, 2)).buildAndRegister();


        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(80).EUt(90).fluidInputs(Materials.Water.getFluid(B)).input(OrePrefix.dust, Materials.SodiumCyanate).fluidOutputs(Materials.SodiumCyanide.getFluid(B/4), Materials.Oxygen.getFluid(B/10)).buildAndRegister();

        //Polymerization
        //Polyacrylonitrile
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(900).EUt(220).fluidInputs(Materials.Acrylonitrile.getFluid(B * 2), Materials.BariumPeroxide.getFluid(L)).fluidOutputs(Materials.Polyacrylonitrile.getFluid(L * 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1100).EUt(190).fluidInputs(Materials.Acrylonitrile.getFluid(B), Materials.AmmoniumPersulfate.getFluid(B)).fluidOutputs(Materials.Polyacrylonitrile.getFluid(L * 2)).buildAndRegister();
        //For MV
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(B), Materials.Ethylene.getFluid(B)).fluidOutputs(Materials.Polyethylene.getFluid(L)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(700).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(B), Materials.Ethylene.getFluid(B)).fluidOutputs(Materials.Polyethylene.getFluid(L + (L / 2))).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(B * 4), Materials.Ethylene.getFluid(B * 4), Materials.TitaniumTetrachloride.getFluid(B / 10)).fluidOutputs(Materials.Polyethylene.getFluid(L * 5)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(B * 2), Materials.Ethylene.getFluid(B * 4), Materials.TitaniumTetrachloride.getFluid(B / 10)).fluidOutputs(Materials.Polyethylene.getFluid(L * 5)).buildAndRegister();
        //For HV
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(650).EUt(120).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(B), Materials.VinylAcetate.getFluid(B)).fluidOutputs(Materials.PolyvinylAcetate.getFluid(L)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(120).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(B), Materials.VinylAcetate.getFluid(B)).fluidOutputs(Materials.PolyvinylAcetate.getFluid(L + (L / 2))).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(90).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(B * 4), Materials.VinylAcetate.getFluid(B * 5), Materials.TitaniumTetrachloride.getFluid(B / 10)).fluidOutputs(Materials.PolyvinylAcetate.getFluid(L * 8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(90).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(B * 2), Materials.VinylAcetate.getFluid(B * 5), Materials.TitaniumTetrachloride.getFluid(B / 10)).fluidOutputs(Materials.PolyvinylAcetate.getFluid(L * 8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1400).EUt(480).fluidInputs(Materials.Acrylonitrile.getFluid(B / 2), Materials.Butadiene.getFluid(B / 2), Materials.Styrene.getFluid(B / 2)).fluidOutputs(Materials.AcrylonitrileButadieneStyrene.getFluid(L * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(B).EUt(1440).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.MethylMethacrylate.getFluid(B / 4), Materials.Fluorine.getFluid(B)).fluidOutputs(Materials.Acrylic.getFluid(L)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1100).EUt(1920).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.MethylMethacrylate.getFluid(B), Materials.Fluorine.getFluid(B * 4)).fluidOutputs(Materials.Acrylic.getFluid(L * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1800).EUt(23405).fluidInputs(Materials.Chlorotrifluoroethylene.getFluid(B), Materials.BariumPeroxide.getFluid(L * 5)).fluidOutputs(Materials.Polychlorotrifluoroethylene.getFluid(L * 6)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).fluidInputs(Materials.Dimethyldichlorosilane.getFluid(B), Materials.Water.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Polydimethylsiloxane, 3)).fluidOutputs(Materials.DilutedHydrochloricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(96).input(OrePrefix.dust, Materials.Silicon).fluidInputs(Materials.HydrochloricAcid.getFluid(B * 2), Materials.Methanol.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Polydimethylsiloxane, 3)).fluidOutputs(Materials.DilutedHydrochloricAcid.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(96).input(OrePrefix.dust, Materials.Silicon).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Water.getFluid(B), Materials.Chlorine.getFluid(B * 4), Materials.Methane.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Polydimethylsiloxane, 3)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B * 2), Materials.DilutedHydrochloricAcid.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).fluidInputs(Materials.Chlorine.getFluid(B), Materials.Hydrogen.getFluid(B)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30).input(OrePrefix.dust, Materials.Salt, 2).fluidInputs(Materials.SulfuricAcid.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SodiumBisulfate)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Chlorine.getFluid(B * 6), Materials.Methane.getFluid(B)).fluidOutputs(Materials.HydrochloricAcid.getFluid(3000), Materials.Chloroform.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Chlorine.getFluid(B * 2), Materials.Methane.getFluid(B)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B), Materials.Chloromethane.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Chlorine.getFluid(B * 2), Materials.Benzene.getFluid(500)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B), Materials.Dichlorobenzene.getFluid(500)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Propene.getFluid(B), Materials.Chlorine.getFluid(B * 2)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B), Materials.AllylChloride.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Chlorine.getFluid(B * 2), Materials.Ethylene.getFluid(B)).fluidOutputs(Materials.VinylChloride.getFluid(B), Materials.HydrochloricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).input(OrePrefix.dust, Materials.Apatite).fluidInputs(Materials.Water.getFluid(B * 10), Materials.SulfuricAcid.getFluid(B * 5)).fluidOutputs(Materials.HydrochloricAcid.getFluid(B), Materials.PhosphoricAcid.getFluid(B * 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).fluidInputs(Materials.SulfurDioxide.getFluid(B), Materials.Oxygen.getFluid(B)).fluidOutputs(Materials.SulfurTrioxide.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(30).input(OrePrefix.dust, Materials.Sulfur).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(B * 3)).fluidOutputs(Materials.SulfurTrioxide.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(256).fluidInputs(Materials.Chloroform.getFluid(B), Materials.HydrofluoricAcid.getFluid(B * 2)).fluidOutputs(Materials.DilutedHydrochloricAcid.getFluid(B * 3), Materials.Tetrafluoroethylene.getFluid(B / 20)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(720).EUt(30).fluidInputs(Materials.SaltWater.getFluid(B * 2)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SodiumHydroxide)).fluidOutputs(Materials.Chlorine.getFluid(B), Materials.Hydrogen.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Sodium).fluidInputs(Materials.Water.getFluid(B)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SodiumHydroxide)).fluidOutputs(Materials.Hydrogen.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(B), Materials.Styrene.getFluid(L)).fluidOutputs(Materials.Polystyrene.getFluid(L)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(B / 2), Materials.Styrene.getFluid(L)).fluidOutputs(Materials.Polystyrene.getFluid(L  + (L / 2))).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(B * 4), Materials.Styrene.getFluid(L * 5), Materials.TitaniumTetrachloride.getFluid(B / 10)).fluidOutputs(Materials.Polystyrene.getFluid(L * 9)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(B * 2), Materials.Styrene.getFluid(L * 5), Materials.TitaniumTetrachloride.getFluid(B / 10)).fluidOutputs(Materials.Polystyrene.getFluid(L * 7)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(B), Materials.HydrochloricAcid.getFluid(B), Materials.Ethylene.getFluid(B)).fluidOutputs(Materials.Water.getFluid(B), Materials.VinylChloride.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(B), Materials.Cumene.getFluid(B)).fluidOutputs(Materials.Phenol.getFluid(B), Materials.Acetone.getFluid(B)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(2).input(OrePrefix.dust, Materials.Wood, 4).fluidInputs(Materials.SulfuricAcid.getFluid(B)).outputs(new ItemStack(Items.COAL, 1, 1)).fluidOutputs(Materials.DilutedSulfuricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(2).inputs(new ItemStack(Items.SUGAR, 4)).fluidInputs(Materials.SulfuricAcid.getFluid(B)).outputs(new ItemStack(Items.COAL, 1, 1)).fluidOutputs(Materials.DilutedSulfuricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(B), Materials.AceticAcid.getFluid(B)).fluidOutputs(Materials.Ethenone.getFluid(B), Materials.DilutedSulfuricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).input(OrePrefix.dust, Materials.Calcite).fluidInputs(Materials.AceticAcid.getFluid(B * 4)).fluidOutputs(Materials.Acetone.getFluid(B * 4), Materials.CarbonDioxide.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).input(OrePrefix.dust, Materials.Quicklime).fluidInputs(Materials.AceticAcid.getFluid(B * 4)).fluidOutputs(Materials.Acetone.getFluid(B * 4), Materials.CarbonDioxide.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).input(OrePrefix.dust, Materials.Calcium).fluidInputs(Materials.AceticAcid.getFluid(B * 4)).fluidOutputs(Materials.Acetone.getFluid(B * 4), Materials.CarbonDioxide.getFluid(B * 4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(Materials.Methanol.getFluid(B), Materials.AceticAcid.getFluid(B)).fluidOutputs(Materials.MethylAcetate.getFluid(B), Materials.Water.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).fluidInputs(Materials.Glycerol.getFluid(B), Materials.HydrochloricAcid.getFluid(B)).fluidOutputs(Materials.Water.getFluid(B * 2), Materials.Epichlorhydrin.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).input(OrePrefix.dust, Materials.SodiumHydroxide).fluidInputs(Materials.AllylChloride.getFluid(B), Materials.HypochlorousAcid.getFluid(B)).fluidOutputs(Materials.SaltWater.getFluid(B), Materials.Epichlorhydrin.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).input(OrePrefix.dust, Materials.Sulfur).fluidInputs(Materials.Hydrogen.getFluid(B * 2)).fluidOutputs(Materials.HydrogenSulfide.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricLightFuel.getFluid(B * 12), Materials.Hydrogen.getFluid(B * 2)).fluidOutputs(Materials.HydrogenSulfide.getFluid(B), Materials.LightFuel.getFluid(B * 12)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricHeavyFuel.getFluid(B * 8), Materials.Hydrogen.getFluid(B * 2)).fluidOutputs(Materials.HydrogenSulfide.getFluid(B), Materials.HeavyFuel.getFluid(B * 8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricNaphtha.getFluid(B * 12), Materials.Hydrogen.getFluid(B * 2)).fluidOutputs(Materials.HydrogenSulfide.getFluid(B), Materials.Naphtha.getFluid(B * 12)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricGas.getFluid(B * 16), Materials.Hydrogen.getFluid(B * 2)).fluidOutputs(Materials.HydrogenSulfide.getFluid(B), Materials.Gas.getFluid(B * 16)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.NaturalGas.getFluid(B * 16), Materials.Hydrogen.getFluid(B * 2)).fluidOutputs(Materials.HydrogenSulfide.getFluid(B), Materials.Gas.getFluid(B * 16)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1250).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Nitrogen.getFluid(B), Materials.Oxygen.getFluid(B * 2)).fluidOutputs(Materials.NitrogenDioxide.getFluid(B * 3)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(96).fluidInputs(Materials.Hydrogen.getFluid(6000), Materials.CarbonDioxide.getFluid(B)).fluidOutputs(Materials.Water.getFluid(B), Materials.Methanol.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(96).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Hydrogen.getFluid(4000), Materials.CarbonMonoxide.getFluid(B)).fluidOutputs(Materials.Methanol.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(96).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Hydrogen.getFluid(4000), Materials.Oxygen.getFluid(B)).fluidOutputs(Materials.Methanol.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(8).fluidInputs(Materials.Mercury.getFluid(B), Materials.Water.getFluid(B * 10), Materials.Chlorine.getFluid(B * 10)).fluidOutputs(Materials.HypochlorousAcid.getFluid(B * 10)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Water.getFluid(B), Materials.Chlorine.getFluid(B * 2)).fluidOutputs(Materials.DilutedHydrochloricAcid.getFluid(B), Materials.HypochlorousAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(960).EUt(480).fluidInputs(Materials.Dimethylamine.getFluid(B), Materials.Chloramine.getFluid(B)).fluidOutputs(Materials.Dimethylhydrazine.getFluid(B), Materials.DilutedHydrochloricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1040).EUt(480).fluidInputs(Materials.Methanol.getFluid(B * 2), Materials.Ammonia.getFluid(B), Materials.HypochlorousAcid.getFluid(B)).fluidOutputs(Materials.Dimethylhydrazine.getFluid(B), Materials.DilutedHydrochloricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).input(OrePrefix.dust, Materials.Sulfur).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(B * 2)).fluidOutputs(Materials.SulfurDioxide.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Oxygen.getFluid(3000), Materials.HydrogenSulfide.getFluid(B)).fluidOutputs(Materials.Water.getFluid(B), Materials.SulfurDioxide.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).fluidInputs(Materials.Hydrogen.getFluid(B), Materials.Fluorine.getFluid(B)).fluidOutputs(Materials.HydrofluoricAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Salt).fluidInputs(Materials.Water.getFluid(B)).fluidOutputs(Materials.SaltWater.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Ethylene.getFluid(B), Materials.Benzene.getFluid(B)).fluidOutputs(Materials.Hydrogen.getFluid(B * 2), Materials.Styrene.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1920).EUt(30).fluidInputs(Materials.PhosphoricAcid.getFluid(B), Materials.Benzene.getFluid(B * 8), Materials.Propene.getFluid(B * 8)).fluidOutputs(Materials.Cumene.getFluid(B * 8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).input(OrePrefix.dust, Materials.Silicon).fluidInputs(Materials.Chloromethane.getFluid(B * 2)).fluidOutputs(Materials.Dimethyldichlorosilane.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(B * 2), Materials.Ethylene.getFluid(B)).fluidOutputs(Materials.AceticAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(30).fluidInputs(Materials.CarbonMonoxide.getFluid(B), Materials.Methanol.getFluid(B)).fluidOutputs(Materials.AceticAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Hydrogen.getFluid(B * 4), Materials.CarbonMonoxide.getFluid(B * 2)).fluidOutputs(Materials.AceticAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).input(OrePrefix.dust, Materials.Carbon, 2).notConsumable(new IntCircuitIngredient(4)).fluidInputs(Materials.Oxygen.getFluid(B * 2), Materials.Hydrogen.getFluid(B * 4)).fluidOutputs(Materials.AceticAcid.getFluid(B)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(80).EUt(16).input(OrePrefix.dust, Materials.Quicklime).fluidInputs(Materials.AceticAcid.getFluid(B * 2)).fluidOutputs(Materials.CalciumAcetate.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(80).EUt(16).input(OrePrefix.dust, Materials.Calcium).fluidInputs(Materials.AceticAcid.getFluid(B * 2)).fluidOutputs(Materials.CalciumAcetate.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(240).EUt(16).input(OrePrefix.dust, Materials.Calcite).fluidInputs(Materials.AceticAcid.getFluid(B * 2)).fluidOutputs(Materials.CalciumAcetate.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(600).input(OrePrefix.dust, Materials.Aluminium, 4).fluidInputs(Materials.IndiumConcentrate.getFluid(B * 8)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Indium)).fluidOutputs(Materials.LeadZincSolution.getFluid(B * 8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(30).EUt(240).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.NitrogenDioxide.getFluid(3000), Materials.Water.getFluid(B)).fluidOutputs(Materials.NitricOxide.getFluid(B), Materials.NitricAcid.getFluid(B * 2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(B * 10), Materials.Ammonia.getFluid(4000)).fluidOutputs(Materials.NitricOxide.getFluid(4000), Materials.Water.getFluid(6000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(B), Materials.AceticAcid.getFluid(B), Materials.Ethylene.getFluid(B)).fluidOutputs(Materials.Water.getFluid(B), Materials.VinylAcetate.getFluid(B)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(30).input(OrePrefix.dust, Materials.Sphalerite, 2).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Zinc), OreDictUnifier.get(OrePrefix.dust, Materials.Sulfur)).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gallium), 2500, 900).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).fluidOutputs(Materials.CarbonMonoxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Charcoal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonMonoxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Coal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonMonoxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Charcoal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonMonoxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Coal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonMonoxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(8).input(OrePrefix.dust, Materials.Carbon).fluidInputs(Materials.CarbonDioxide.getFluid(1000)).fluidOutputs(Materials.CarbonMonoxide.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(384).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Hydrogen.getFluid(3000), Materials.Nitrogen.getFluid(1000)).fluidOutputs(Materials.Ammonia.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.HypochlorousAcid.getFluid(1000), Materials.Ammonia.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), Materials.Chloramine.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(Materials.Ammonia.getFluid(1000), Materials.Methanol.getFluid(2000)).fluidOutputs(Materials.Water.getFluid(2000), Materials.Dimethylamine.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).input(OrePrefix.dust, Materials.Phosphorus).fluidInputs(Materials.Water.getFluid(1500), Materials.Oxygen.getFluid(2500)).fluidOutputs(Materials.PhosphoricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.HydrochloricAcid.getFluid(1000), Materials.Methanol.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), Materials.Chloromethane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(150).input(OrePrefix.crushedPurified, Materials.Sphalerite).input(OrePrefix.crushedPurified, Materials.Galena).fluidInputs(Materials.SulfuricAcid.getFluid(4000)).fluidOutputs(Materials.IndiumConcentrate.getFluid(8000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Charcoal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Coal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Charcoal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Coal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(480).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Water.getFluid(2000), Materials.Methane.getFluid(1000)).fluidOutputs(Materials.Hydrogen.getFluid(8000), Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(20).EUt(480).fluidInputs(Materials.BioDiesel.getFluid(1000), Materials.Tetranitromethane.getFluid(40)).fluidOutputs(Materials.NitroFuel.getFluid(750)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(20).EUt(480).fluidInputs(Materials.Fuel.getFluid(1000), Materials.Tetranitromethane.getFluid(20)).fluidOutputs(Materials.NitroFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(20).EUt(5).fluidInputs(Materials.Butane.getFluid(320)).fluidOutputs(Materials.LPG.getFluid(370)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(20).EUt(5).fluidInputs(Materials.Propane.getFluid(320)).fluidOutputs(Materials.LPG.getFluid(290)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(16).EUt(120).fluidInputs(Materials.LightFuel.getFluid(5000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.Fuel.getFluid(6000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120).fluidInputs(Materials.NitricAcid.getFluid(8000), Materials.Ethenone.getFluid(1000)).fluidOutputs(Materials.Tetranitromethane.getFluid(2000), Materials.Water.getFluid(9000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(4000), Materials.Ammonia.getFluid(1000)).fluidOutputs(Materials.NitricAcid.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).notConsumable(new IntCircuitIngredient(4)).fluidInputs(Materials.Water.getFluid(1000), Materials.Oxygen.getFluid(1000), Materials.NitrogenDioxide.getFluid(2000)).fluidOutputs(Materials.NitricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480).notConsumable(new IntCircuitIngredient(24)).fluidInputs(Materials.Oxygen.getFluid(4000), Materials.Nitrogen.getFluid(1000), Materials.Hydrogen.getFluid(3000)).fluidOutputs(Materials.NitricAcid.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();

        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(Materials.SeedOil.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(Materials.WoodVinegar.getFluid(1000)).fluidOutputs(Materials.AceticAcid.getFluid(100), Materials.Water.getFluid(500), Materials.Ethanol.getFluid(10), Materials.Methanol.getFluid(300), Materials.Acetone.getFluid(50), Materials.MethylAcetate.getFluid(10)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(Materials.FermentedBiomass.getFluid(1000)).fluidOutputs(Materials.AceticAcid.getFluid(25), Materials.Water.getFluid(375), Materials.Ethanol.getFluid(150), Materials.Methanol.getFluid(150), Materials.Ammonia.getFluid(100), Materials.CarbonDioxide.getFluid(400), Materials.Methane.getFluid(600)).buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(32).EUt(400).fluidInputs(Materials.Biomass.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Wood, 8)).fluidOutputs(Materials.Ethanol.getFluid(600), Materials.Water.getFluid(300)).buildAndRegister();

        for (DustMaterial dust : new DustMaterial[] {Materials.Talc, Materials.Soapstone, Materials.Redstone}) {
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Materials.Oil.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Materials.Creosote.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Materials.SeedOil.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
        }
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(1000), Materials.Ethanol.getFluid(1000)).fluidOutputs(Materials.Ethylene.getFluid(1000), Materials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();

    }

}
