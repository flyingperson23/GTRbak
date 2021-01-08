package gtr.loaders.recipe;

import gtr.api.GTValues;
import gtr.api.recipes.ModHandler;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.recipes.FuelRecipe;
import gtr.api.unification.material.Materials;
import gtr.api.util.ValidationResult;
import gtr.integration.ic2.IC2Handler;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FuelRecipes {

    public static void registerFuels() {

        registerDieselGeneratorFuel(Materials.BioFuel.getFluid(4), 1, GTValues.LV);
        registerDieselGeneratorFuel(Materials.LightFuel.getFluid(1), 8, GTValues.LV);
        registerDieselGeneratorFuel(Materials.Naphtha.getFluid(1), 8, GTValues.LV);
        registerDieselGeneratorFuel(Materials.Fuel.getFluid(1), 4, GTValues.LV);
        registerDieselGeneratorFuel(Materials.Oil.getFluid(2), 1, GTValues.LV);
        registerDieselGeneratorFuel(Materials.SulfuricLightFuel.getFluid(4), 5, GTValues.LV);
        registerDieselGeneratorFuel(Materials.Methanol.getFluid(8), 21, GTValues.LV);

        //high-tier diesel generator fuels
        registerDieselGeneratorFuel(Materials.Ethanol.getFluid(2), 1, GTValues.HV);
        registerDieselGeneratorFuel(Materials.NitroFuel.getFluid(1), 2, GTValues.HV);

        //steam generator fuels
        registerSteamGeneratorFuel(Materials.Steam.getFluid(54), 1, GTValues.LV);

        //low-tier gas turbine fuels
        registerGasGeneratorFuel(Materials.Hydrogen.getFluid(1), 1, GTValues.LV);
        registerGasGeneratorFuel(Materials.Methane.getFluid(1), 2, GTValues.LV);

        registerGasGeneratorFuel(Materials.NaturalGas.getFluid(8), 5, GTValues.LV);
        registerGasGeneratorFuel(Materials.Hydrogen.getFluid(8), 5, GTValues.LV);
        registerGasGeneratorFuel(Materials.CarbonMonoxide.getFluid(8), 6, GTValues.LV);
        registerGasGeneratorFuel(Materials.SulfuricGas.getFluid(32), 25, GTValues.LV);
        registerGasGeneratorFuel(Materials.SulfuricNaphtha.getFluid(4), 5, GTValues.LV);
        registerGasGeneratorFuel(Materials.Methane.getFluid(4), 14, GTValues.LV);
        registerGasGeneratorFuel(Materials.Ethylene.getFluid(1), 4, GTValues.LV);
        registerGasGeneratorFuel(Materials.Gas.getFluid(1), 5, GTValues.LV);
        registerGasGeneratorFuel(Materials.Ethane.getFluid(4), 21, GTValues.LV);
        registerGasGeneratorFuel(Materials.Propene.getFluid(1), 6, GTValues.LV);
        registerGasGeneratorFuel(Materials.Butadiene.getFluid(16), 103, GTValues.LV);
        registerGasGeneratorFuel(Materials.Propane.getFluid(4), 29, GTValues.LV);
        registerGasGeneratorFuel(Materials.Butene.getFluid(1), 8, GTValues.LV);
        registerGasGeneratorFuel(Materials.Phenol.getFluid(1), 9, GTValues.LV);
        registerGasGeneratorFuel(Materials.Benzene.getFluid(1), 9, GTValues.LV);
        registerGasGeneratorFuel(Materials.Butane.getFluid(4), 37, GTValues.LV);
        registerGasGeneratorFuel(Materials.LPG.getFluid(1), 10, GTValues.LV);
        registerGasGeneratorFuel(Materials.Naphtha.getFluid(1), 10, GTValues.LV);
        registerGasGeneratorFuel(Materials.Toluene.getFluid(4), 41, GTValues.LV);
        registerGasGeneratorFuel(Materials.Dimethylhydrazine.getFluid(1), 180, GTValues.LV);

        //semi-fluid fuels, like creosote
        registerSemiFluidGeneratorFuel(Materials.Creosote.getFluid(14), 1, GTValues.LV);
        registerSemiFluidGeneratorFuel(Materials.Biomass.getFluid(14), 1, GTValues.LV);
        registerSemiFluidGeneratorFuel(Materials.OilLight.getFluid(64), 5, GTValues.LV);
        registerSemiFluidGeneratorFuel(Materials.OilMedium.getFluid(32), 15, GTValues.LV);
        registerSemiFluidGeneratorFuel(Materials.OilHeavy.getFluid(16), 5, GTValues.LV);
        registerSemiFluidGeneratorFuel(Materials.SulfuricHeavyFuel.getFluid(16), 5, GTValues.LV);
        registerSemiFluidGeneratorFuel(Materials.HeavyFuel.getFluid(8), 15, GTValues.LV);

        //plasma turbine
        registerPlasmaFuel(Materials.Helium.getPlasma(1), 2560, GTValues.LV);
        registerPlasmaFuel(Materials.Nitrogen.getPlasma(1), 4032, GTValues.LV);
        registerPlasmaFuel(Materials.Oxygen.getPlasma(1), 4096, GTValues.LV);
        registerPlasmaFuel(Materials.Iron.getPlasma(16), 103219, GTValues.LV);
        registerPlasmaFuel(Materials.Nickel.getPlasma(16), 106905, GTValues.LV);

        //lhe
        registerLHEFuel(ModHandler.getLava(100), ModHandler.getWater(5), ModHandler.getPahoehoe(50), 7200, 1);
        registerLHEFuel(ModHandler.getLava(100), ModHandler.getDistilledWater(2), ModHandler.getPahoehoe(50), 7200, 1);
        if (Loader.isModLoaded("ic2")) {
            Fluid coolant = FluidRegistry.getFluid("ic2coolant");
            Fluid hotCoolant = FluidRegistry.getFluid("ic2hot_coolant");
            registerLHEFuel(new FluidStack(hotCoolant, 75), ModHandler.getWater(5), new FluidStack(coolant, 75), 7200, 1);
            registerLHEFuel(new FluidStack(hotCoolant, 75), ModHandler.getDistilledWater(2), new FluidStack(coolant, 75), 7200, 1);
        }
    }

    public static void registerPlasmaFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.PLASMA_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    public static void registerDieselGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.DIESEL_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    public static void registerSteamGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.STEAM_TURBINE_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    public static void registerGasGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.GAS_TURBINE_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    public static void registerSemiFluidGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.SEMI_FLUID_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GTValues.V[tier]));
    }

    public static void registerLHEFuel(FluidStack input1, FluidStack input2, FluidStack output1, int steamAmount, int duration) {
        RecipeMaps.LARGE_HEAT_EXCHANGER_FUELS.recipeBuilder()
            .duration(duration)
            .EUt(0)
            .fluidInputs(input1, input2)
            .fluidOutputs(output1, ModHandler.getSteam(steamAmount))
            .buildAndRegister();
    }

}