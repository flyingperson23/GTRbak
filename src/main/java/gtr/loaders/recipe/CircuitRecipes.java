package gtr.loaders.recipe;

import gtr.api.recipes.ModHandler;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.UnificationEntry;
import gtr.common.items.MetaItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gtr.api.GTValues.L;

public class CircuitRecipes {

    public static void init() {
        //Board
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(12)
            .input(OrePrefix.dust, Materials.SiliconDioxide, 32)
            .input(OrePrefix.ring, Materials.Copper, 2)
            .outputs(MetaItems.BOARD_LV.getStackForm())
            .buildAndRegister();


        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(12)
            .input(OrePrefix.dust, Materials.SiliconDioxide, 4)
            .input(OrePrefix.ring, Materials.Copper, 1)
            .input(OrePrefix.dustTiny, Materials.Silicon, 2)
            .input(OrePrefix.dust, Materials.SolderingAlloy, 2)
            .outputs(MetaItems.BOARD_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(250).EUt(12)
            .input(OrePrefix.plate, Materials.Polyethylene)
            .input(OrePrefix.ring, Materials.Brass, 2)
            .input(OrePrefix.dustTiny, Materials.Silicon, 10)
            .input(OrePrefix.dust, Materials.FerrousChloride)
            .outputs(MetaItems.BOARD_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(270).EUt(12)
            .input(OrePrefix.plate, Materials.Epoxid)
            .input(OrePrefix.ring, Materials.Cupronickel, 2)
            .input(OrePrefix.dustTiny, Materials.Silicon, 10)
            .input(OrePrefix.dust, Materials.PolyvinylAcetate)
            .outputs(MetaItems.BOARD_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(290).EUt(12)
            .input(OrePrefix.plate, Materials.Polychlorotrifluoroethylene, 2) //was Polyethylene
            .input(OrePrefix.ring, Materials.BerylliumCopper, 2)
            .input(OrePrefix.dustTiny, Materials.Silicon, 12)
            .input(OrePrefix.dust, Materials.Polyacrylonitrile, 2)
            .outputs(MetaItems.BOARD_EV.getStackForm())
            .buildAndRegister();


        //SOC		
        ModHandler.addShapelessRecipe("topaz_soc", MetaItems.SOC_LV.getStackForm(), "h", new UnificationEntry(OrePrefix.gem, Materials.Topaz), new UnificationEntry(OrePrefix.gem, Materials.Topaz));

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(80).EUt(4)
            .input(OrePrefix.plate, Materials.Topaz, 2)
            .input(OrePrefix.dust, Materials.Redstone, 4)
            .outputs(MetaItems.SOC_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(24)
            .input(OrePrefix.plate, Materials.EnderPearl, 8)
            .input(OrePrefix.dust, Materials.Redstone, 8)
            .outputs(MetaItems.SOC_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(120).EUt(48)
            .input(OrePrefix.plate, Materials.NetherQuartz, 8)
            .input(OrePrefix.dust, Materials.Redstone, 10)
            .outputs(MetaItems.SOC_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(140).EUt(120)
            .input(OrePrefix.plate, Materials.Lapis, 8)
            .input(OrePrefix.dust, Materials.Redstone, 12)
            .outputs(MetaItems.SOC_EV.getStackForm())
            .buildAndRegister();


        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(80).EUt(8)
            .inputs(MetaItems.SPRAY_EMPTY.getStackForm())
            .fluidInputs(Materials.SulfuricAcid.getFluid(L))
            .outputs(MetaItems.ETCHING_KIT_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(100).EUt(24)
            .inputs(MetaItems.SPRAY_EMPTY.getStackForm())
            .fluidInputs(Materials.NitricAcid.getFluid(L))
            .outputs(MetaItems.ETCHING_KIT_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(120).EUt(48)
            .inputs(MetaItems.SPRAY_EMPTY.getStackForm())
            .fluidInputs(Materials.HydrochloricAcid.getFluid(L))
            .outputs(MetaItems.ETCHING_KIT_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(140).EUt(120)
            .inputs(MetaItems.SPRAY_EMPTY.getStackForm())
            .fluidInputs(Materials.HydrocyanicAcid.getFluid(L))
            .outputs(MetaItems.ETCHING_KIT_EV.getStackForm())
            .buildAndRegister();

        //Wiring
        ModHandler.addShapedRecipe("red_alloy_circuit_wiring", MetaItems.WIRING_LV.getStackForm(), "CCC", "CxC", "CCC", 'C', new UnificationEntry(OrePrefix.wireGtSingle, Materials.RedAlloy));
        ModHandler.addShapedRecipe("copper_circuit_wiring", MetaItems.WIRING_MV.getStackForm(), "WWW", "WxW", "WWW", 'W', new UnificationEntry(OrePrefix.wireFine, Materials.Copper));
        ModHandler.addShapedRecipe("annealed_copper_circuit_wiring", MetaItems.WIRING_MV.getStackForm(), "WWW", "WxW", "WWW", 'W', new UnificationEntry(OrePrefix.wireFine, Materials.AnnealedCopper));


        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(180).EUt(4)
            .input(OrePrefix.foil, Materials.RedAlloy).input(OrePrefix.craftingLens, MarkerMaterials.Color.Red)
            .outputs(MetaItems.WIRING_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(210).EUt(24)
            .input(OrePrefix.foil, Materials.Copper).input(OrePrefix.craftingLens, MarkerMaterials.Color.Orange)
            .outputs(MetaItems.WIRING_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(240).EUt(74)
            .input(OrePrefix.foil, Materials.Electrum).input(OrePrefix.craftingLens, MarkerMaterials.Color.Blue)
            .outputs(MetaItems.WIRING_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(270).EUt(240)
            .input(OrePrefix.foil, Materials.Graphene).input(OrePrefix.craftingLens, MarkerMaterials.Color.LightBlue)
            .outputs(MetaItems.WIRING_EV.getStackForm())
            .buildAndRegister();


        //Circuits
        ModHandler.addShapelessRecipe("LV_to_configurable_circuit", MetaItems.INTEGRATED_CIRCUIT.getStackForm(), new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic));

        ModHandler.addShapedRecipe("vacuum_tube", MetaItems.CIRCUIT_VACUUM_TUBE_LV.getStackForm(), "PTP", "STS", "WWW", 'P', new ItemStack(Items.PAPER), 'T', MetaItems.GLASS_TUBE.getStackForm(), 'S', new ItemStack(Items.SLIME_BALL), 'W', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("vacuum_tube_fine", MetaItems.CIRCUIT_VACUUM_TUBE_LV.getStackForm(), "PTP", "STS", "WWW", 'P', new ItemStack(Items.PAPER), 'T', MetaItems.GLASS_TUBE.getStackForm(), 'S', new ItemStack(Items.SLIME_BALL), 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper));

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(160).EUt(8).input(OrePrefix.dust, Materials.Glass).notConsumable(MetaItems.SHAPE_MOLD_BALL).outputs(MetaItems.GLASS_TUBE.getStackForm()).buildAndRegister();


        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(8)
            .inputs(MetaItems.BOARD_LV.getStackForm(), MetaItems.SOC_LV.getStackForm(), MetaItems.WIRING_LV.getStackForm(2), MetaItems.ETCHING_KIT_LV.getStackForm(),
                OreDictUnifier.get(OrePrefix.dust, Materials.Gold, 2))
            .fluidInputs(Materials.Glue.getFluid(25), Materials.SolderingAlloy.getFluid(L/2))
            .outputs(MetaItems.CIRCUIT_LV.getStackForm(4))
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(24)
            .inputs(MetaItems.BOARD_MV.getStackForm(), MetaItems.SOC_MV.getStackForm(), MetaItems.WIRING_MV.getStackForm(2), MetaItems.ETCHING_KIT_MV.getStackForm(),
                OreDictUnifier.get(OrePrefix.dust, Materials.Gold, 2))
            .fluidInputs(Materials.Glue.getFluid(10), Materials.SolderingAlloy.getFluid(L))
            .outputs(MetaItems.CIRCUIT_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(24)
            .inputs(MetaItems.BOARD_HV.getStackForm(), MetaItems.SOC_HV.getStackForm(),
                MetaItems.WIRING_HV.getStackForm(2), OreDictUnifier.get(OrePrefix.dust, Materials.AmmoniumPersulfate, 2), MetaItems.ETCHING_KIT_HV.getStackForm(),
                OreDictUnifier.get(OrePrefix.dust, Materials.Gold, 2))
            .fluidInputs(Materials.CalciumAcetate.getFluid(L/2), Materials.SolderingAlloy.getFluid(L * 2), Materials.DichloroTrifluoroethane.getFluid(L/4))
            .outputs(MetaItems.CIRCUIT_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(24)
            .inputs(MetaItems.BOARD_EV.getStackForm(), MetaItems.SOC_EV.getStackForm(), MetaItems.ETCHING_KIT_EV.getStackForm(),
                MetaItems.WIRING_EV.getStackForm(2), OreDictUnifier.get(OrePrefix.dust, Materials.AmmoniumPersulfate, 4),
                OreDictUnifier.get(OrePrefix.dustSmall, Materials.AcrylonitrileButadieneStyrene, 6),
                OreDictUnifier.get(OrePrefix.dust, Materials.Gold, 2))
            .fluidInputs(Materials.BisphenolA.getFluid(200), Materials.SolderingAlloy.getFluid(L * 3), Materials.Acetonitrile.getFluid(L/2))
            .outputs(MetaItems.CIRCUIT_EV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(24)
            .inputs(MetaItems.BOARD_EV.getStackForm(), MetaItems.SOC_EV.getStackForm(), MetaItems.ETCHING_KIT_EV.getStackForm(),
                MetaItems.WIRING_EV.getStackForm(2), OreDictUnifier.get(OrePrefix.dust, Materials.AmmoniumPersulfate, 4),
                OreDictUnifier.get(OrePrefix.dustSmall, Materials.AcrylonitrileButadieneStyrene, 6),
                OreDictUnifier.get(OrePrefix.dust, Materials.Gold, 2))
            .fluidInputs(Materials.PolyvinylAcetate.getFluid(20), Materials.SolderingAlloy.getFluid(L * 3), Materials.Acetonitrile.getFluid(L/2))
            .outputs(MetaItems.CIRCUIT_EV.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2048)
            .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Steel), MetaItems.CIRCUIT_EV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.cableGtDouble, Materials.Superconductor, 16), MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm())
            .fluidInputs(Materials.SolderingAlloy.getFluid(L * 16), Materials.Technetium.getFluid(L * 2))
            .outputs(MetaItems.CIRCUIT_ULTIMATE.getStackForm())
            .buildAndRegister();
    }
}
