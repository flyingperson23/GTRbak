package gtr.loaders.recipe;

import gtr.api.GTValues;
import gtr.api.items.OreDictNames;
import gtr.api.metatileentity.ITieredMetaTileEntity;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.recipes.ModHandler;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.MarkerMaterials.Tier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.UnificationEntry;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gtr.common.blocks.BlockWireCoil.CoilType;
import gtr.common.blocks.MetaBlocks;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

import static gtr.common.blocks.BlockBoilerCasing.BoilerCasingType.*;
import static gtr.common.blocks.BlockFireboxCasing.FireboxCasingType.*;
import static gtr.common.blocks.BlockMachineCasing.MachineCasingType.*;
import static gtr.common.blocks.BlockMetalCasing.MetalCasingType.*;
import static gtr.common.blocks.BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING;
import static gtr.common.blocks.BlockTurbineCasing.TurbineCasingType.*;
import static gtr.common.blocks.BlockWarningSign.SignType.*;
import static gtr.common.blocks.BlockWireCoil.CoilType.CUPRONICKEL;
import static gtr.loaders.recipe.CraftingComponent.*;

public class MetaTileEntityLoader {

    public static void init() {
        ModHandler.addShapedRecipe("casing_lv", MetaBlocks.MACHINE_CASING.getItemVariant(LV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe("casing_mv", MetaBlocks.MACHINE_CASING.getItemVariant(MV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Aluminium));
        ModHandler.addShapedRecipe("casing_hv", MetaBlocks.MACHINE_CASING.getItemVariant(HV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel));
        ModHandler.addShapedRecipe("casing_ev", MetaBlocks.MACHINE_CASING.getItemVariant(EV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium));
        ModHandler.addShapedRecipe("casing_iv", MetaBlocks.MACHINE_CASING.getItemVariant(IV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel));
        ModHandler.addShapedRecipe("casing_luv", MetaBlocks.MACHINE_CASING.getItemVariant(LuV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Osmiridium));
        ModHandler.addShapedRecipe("casing_uv", MetaBlocks.MACHINE_CASING.getItemVariant(UV, 2), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.NaquadahAlloy));

        ModHandler.addShapedRecipe("casing_pyrolyse", MetaBlocks.MACHINE_CASING.getItemVariant(PYROLYSE), "ShS", "SBS", "SwS", 'S', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'B', MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS));
        ModHandler.addShapedRecipe("casing_primitive_bricks", MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS, 1), "XX", "XX", 'X', MetaItems.FIRECLAY_BRICK);
        ModHandler.addShapedRecipe("casing_coke_bricks", MetaBlocks.METAL_CASING.getItemVariant(COKE_BRICKS, 1), "XX", "XX", 'X', MetaItems.COKE_OVEN_BRICK);
        ModHandler.addShapedRecipe("casing_bronze_bricks", MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS, 3), "PhP", "PBP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'B', new ItemStack(Blocks.BRICK_BLOCK, 1));
        ModHandler.addShapedRecipe("casing_steel_solid", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Steel));
        ModHandler.addShapedRecipe("casing_titanium_stable", MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Titanium));
        ModHandler.addShapedRecipe("casing_invar_heatproof", MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Invar), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Invar));
        ModHandler.addShapedRecipe("casing_aluminium_frostproof", MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Aluminium), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Aluminium));
        ModHandler.addShapedRecipe("casing_stainless_clean", MetaBlocks.METAL_CASING.getItemVariant(STAINLESS_CLEAN, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.StainlessSteel));
        ModHandler.addShapedRecipe("casing_tungstensteel_robust", MetaBlocks.METAL_CASING.getItemVariant(TUNGSTENSTEEL_ROBUST, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.TungstenSteel));

        ModHandler.addShapedRecipe("casing_steel_turbine_casing", MetaBlocks.TURBINE_CASING.getItemVariant(STEEL_TURBINE_CASING, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Magnalium), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Steel));
        ModHandler.addShapedRecipe("casing_stainless_turbine_casing", MetaBlocks.TURBINE_CASING.getItemVariant(STAINLESS_TURBINE_CASING, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'F', MetaBlocks.TURBINE_CASING.getItemVariant(STEEL_TURBINE_CASING));
        ModHandler.addShapedRecipe("casing_titanium_turbine_casing", MetaBlocks.TURBINE_CASING.getItemVariant(TITANIUM_TURBINE_CASING, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'F', MetaBlocks.TURBINE_CASING.getItemVariant(STEEL_TURBINE_CASING));
        ModHandler.addShapedRecipe("casing_tungstensteel_turbine_casing", MetaBlocks.TURBINE_CASING.getItemVariant(TUNGSTENSTEEL_TURBINE_CASING, 3), "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'F', MetaBlocks.TURBINE_CASING.getItemVariant(STEEL_TURBINE_CASING));

        ModHandler.addShapedRecipe("casing_bronze_pipe", MetaBlocks.BOILER_CASING.getItemVariant(BRONZE_PIPE, 3), "PIP", "IFI", "PIP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Bronze), 'I', new UnificationEntry(OrePrefix.pipeMedium, Materials.Bronze));
        ModHandler.addShapedRecipe("casing_steel_pipe", MetaBlocks.BOILER_CASING.getItemVariant(STEEL_PIPE, 3), "PIP", "IFI", "PIP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Steel), 'I', new UnificationEntry(OrePrefix.pipeMedium, Materials.Steel));
        ModHandler.addShapedRecipe("casing_titanium_pipe", MetaBlocks.BOILER_CASING.getItemVariant(TITANIUM_PIPE, 3), "PIP", "IFI", "PIP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Titanium), 'I', new UnificationEntry(OrePrefix.pipeMedium, Materials.Titanium));
        ModHandler.addShapedRecipe("casing_tungstensteel_pipe", MetaBlocks.BOILER_CASING.getItemVariant(TUNGSTENSTEEL_PIPE, 3), "PIP", "IFI", "PIP", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.TungstenSteel), 'I', new UnificationEntry(OrePrefix.pipeMedium, Materials.TungstenSteel));
        ModHandler.addShapedRecipe("casing_bronze_firebox", MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(BRONZE_FIREBOX, 3), "PSP", "SFS", "PSP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Bronze), 'S', new UnificationEntry(OrePrefix.stick, Materials.Bronze));
        ModHandler.addShapedRecipe("casing_steel_firebox", MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(STEEL_FIREBOX, 3), "PSP", "SFS", "PSP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Steel), 'S', new UnificationEntry(OrePrefix.stick, Materials.Steel));
        ModHandler.addShapedRecipe("casing_titanium_firebox", MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(TITANIUM_FIREBOX, 3), "PSP", "SFS", "PSP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Titanium), 'S', new UnificationEntry(OrePrefix.stick, Materials.Titanium));
        ModHandler.addShapedRecipe("casing_tungstensteel_firebox", MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(TUNGSTENSTEEL_FIREBOX, 3), "PSP", "SFS", "PSP", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.TungstenSteel), 'S', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel));

        for (CoilType coilType : CoilType.values()) {
            if (coilType.getMaterial() != null) {
                ItemStack outputStack = MetaBlocks.WIRE_COIL.getItemVariant(coilType);
                ModHandler.addShapedRecipe(String.format("heating_coil_%s", coilType.getName()), outputStack, "XXX", "XwX", "XXX", 'X',
                    new UnificationEntry(OrePrefix.wireGtDouble, coilType.getMaterial()));
            }
        }

        ModHandler.addShapedRecipe("casing_bronze_gearbox", MetaBlocks.TURBINE_CASING.getItemVariant(BRONZE_GEARBOX, 3), "PhP", "GFG", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Bronze), 'G', new UnificationEntry(OrePrefix.gear, Materials.Bronze));
        ModHandler.addShapedRecipe("casing_steel_gearbox", MetaBlocks.TURBINE_CASING.getItemVariant(STEEL_GEARBOX, 3), "PhP", "GFG", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Steel), 'G', new UnificationEntry(OrePrefix.gear, Materials.Steel));
        ModHandler.addShapedRecipe("casing_titanium_gearbox", MetaBlocks.TURBINE_CASING.getItemVariant(TITANIUM_GEARBOX, 3), "PhP", "GFG", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Titanium), 'G', new UnificationEntry(OrePrefix.gear, Materials.Titanium));

        ModHandler.addShapedRecipe("casing_grate_casing", MetaBlocks.MULTIBLOCK_CASING.getItemVariant(GRATE_CASING, 3), "PVP", "PFP", "PMP", 'P', new ItemStack(Blocks.IRON_BARS, 1), 'F', new UnificationEntry(OrePrefix.frameGt, Materials.Steel), 'M', MetaItems.ELECTRIC_MOTOR_MV, 'V', new UnificationEntry(OrePrefix.rotor, Materials.Steel));

        ModHandler.addShapedRecipe("warning_sign_yellow_stripes", MetaBlocks.WARNING_SIGN.getItemVariant(YELLOW_STRIPES), "Y  ", " M ", "  B", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_small_yellow_stripes", MetaBlocks.WARNING_SIGN.getItemVariant(SMALL_YELLOW_STRIPES), "  Y", " M ", "B  ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_radioactive_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(RADIOACTIVE_HAZARD), " YB", " M ", "   ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_bio_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(BIO_HAZARD), " Y ", " MB", "   ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_explosion_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(EXPLOSION_HAZARD), " Y ", " M ", "  B", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_fire_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(FIRE_HAZARD), " Y ", " M ", " B ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_acid_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(ACID_HAZARD), " Y ", " M ", "B  ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_magic_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(MAGIC_HAZARD), " Y ", "BM ", "   ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_frost_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(FROST_HAZARD), "BY ", " M ", "   ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");
        ModHandler.addShapedRecipe("warning_sign_noise_hazard", MetaBlocks.WARNING_SIGN.getItemVariant(NOISE_HAZARD), "   ", " M ", "BY ", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'Y', "dyeYellow", 'B', "dyeBlack");

        ModHandler.addShapelessRecipe("yellow_stripes_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(YELLOW_STRIPES));
        ModHandler.addShapelessRecipe("small_yellow_stripes_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(SMALL_YELLOW_STRIPES));
        ModHandler.addShapelessRecipe("radioactive_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(RADIOACTIVE_HAZARD));
        ModHandler.addShapelessRecipe("bio_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(BIO_HAZARD));
        ModHandler.addShapelessRecipe("explosion_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(EXPLOSION_HAZARD));
        ModHandler.addShapelessRecipe("fire_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(FIRE_HAZARD));
        ModHandler.addShapelessRecipe("acid_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(ACID_HAZARD));
        ModHandler.addShapelessRecipe("magic_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(MAGIC_HAZARD));
        ModHandler.addShapelessRecipe("frost_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(FROST_HAZARD));
        ModHandler.addShapelessRecipe("noise_hazard_to_steel_solid_casing", MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), MetaBlocks.WARNING_SIGN.getItemVariant(NOISE_HAZARD));

        ModHandler.addShapedRecipe("hull_lv", MetaTileEntities.HULL[GTValues.LV].getStackForm(2), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(LV), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'H', new UnificationEntry(OrePrefix.plate, Materials.WroughtIron), 'P', new UnificationEntry(OrePrefix.plate, Materials.WroughtIron));
        ModHandler.addShapedRecipe("hull_mv", MetaTileEntities.HULL[GTValues.MV].getStackForm(2), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(MV), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'H', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'P', new UnificationEntry(OrePrefix.plate, Materials.WroughtIron));
        ModHandler.addShapedRecipe("hull_hv", MetaTileEntities.HULL[GTValues.HV].getStackForm(2), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(HV), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'H', new UnificationEntry(OrePrefix.plate, Materials.Polyethylene), 'P', new UnificationEntry(OrePrefix.plate, Materials.Polyethylene));
        ModHandler.addShapedRecipe("hull_ev", MetaTileEntities.HULL[GTValues.EV].getStackForm(2), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(EV), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'H', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'P', new UnificationEntry(OrePrefix.plate, Materials.Polyethylene));
        ModHandler.addShapedRecipe("hull_iv", MetaTileEntities.HULL[GTValues.IV].getStackForm(2), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(IV), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'H', new UnificationEntry(OrePrefix.plate, Materials.Technetium), 'P', new UnificationEntry(OrePrefix.plate, Materials.Polyethylene));

        ModHandler.addShapedRecipe("transformer_mv", MetaTileEntities.TRANSFORMER[GTValues.MV - 1].getStackForm(), " BB", "CM ", " BB", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'B', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("transformer_hv", MetaTileEntities.TRANSFORMER[GTValues.HV - 1].getStackForm(), " BB", "CM ", " BB", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'B', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("transformer_ev", MetaTileEntities.TRANSFORMER[GTValues.EV - 1].getStackForm(), "KBB", "CM ", "KBB", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'B', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'K', new UnificationEntry(OrePrefix.circuit, Tier.Advanced));

        //addHatchRecipes(0, OrePrefix.wireGtSingle);
        //addHatchRecipes(1, OrePrefix.wireGtDouble);
        //addHatchRecipes(2, OrePrefix.wireGtQuadruple);
        //addHatchRecipes(3, OrePrefix.wireGtOctal);
        //addHatchRecipes(4, OrePrefix.wireGtHex);

        ModHandler.addShapedRecipe("fluid_import_hatch_lv", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_import_hatch_mv", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.MV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_import_hatch_hv", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_import_hatch_ev", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.EV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_import_hatch_iv", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.IV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_import_hatch_luv", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LuV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_import_hatch_uv", MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.UV].getStackForm(), "G", "M", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));

        ModHandler.addShapedRecipe("fluid_export_hatch_lv", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.LV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("fluid_export_hatch_mv", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.MV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("fluid_export_hatch_hv", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("fluid_export_hatch_ev", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.EV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("fluid_export_hatch_iv", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.IV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("fluid_export_hatch_luv", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.LuV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("fluid_export_hatch_uv", MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.UV].getStackForm(), "M", "G", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'G', new ItemStack(Blocks.GLASS));

        ModHandler.addShapedRecipe("item_import_bus_lv", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_import_bus_mv", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.MV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_import_bus_hv", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_import_bus_ev", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.EV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_import_bus_iv", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.IV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_import_bus_luv", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LuV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_import_bus_uv", MetaTileEntities.ITEM_IMPORT_BUS[GTValues.UV].getStackForm(), "C", "M", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("item_export_bus_lv", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_export_bus_mv", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.MV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_export_bus_hv", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_export_bus_ev", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.EV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_export_bus_iv", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.IV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_export_bus_luv", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LuV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("item_export_bus_uv", MetaTileEntities.ITEM_EXPORT_BUS[GTValues.UV].getStackForm(), "M", "C", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("battery_buffer_lv_1x1", MetaTileEntities.BATTERY_BUFFER[GTValues.LV][0].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Tin), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_mv_1x1", MetaTileEntities.BATTERY_BUFFER[GTValues.MV][0].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Copper), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_hv_1x1", MetaTileEntities.BATTERY_BUFFER[GTValues.HV][0].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Gold), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_ev_1x1", MetaTileEntities.BATTERY_BUFFER[GTValues.EV][0].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Aluminium), 'T', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("battery_buffer_lv_2x2", MetaTileEntities.BATTERY_BUFFER[GTValues.LV][1].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Tin), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_mv_2x2", MetaTileEntities.BATTERY_BUFFER[GTValues.MV][1].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_hv_2x2", MetaTileEntities.BATTERY_BUFFER[GTValues.HV][1].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Gold), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_ev_2x2", MetaTileEntities.BATTERY_BUFFER[GTValues.EV][1].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Aluminium), 'T', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("battery_buffer_lv_3x3", MetaTileEntities.BATTERY_BUFFER[GTValues.LV][2].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtOctal, Materials.Tin), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_mv_3x3", MetaTileEntities.BATTERY_BUFFER[GTValues.MV][2].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtOctal, Materials.Copper), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_hv_3x3", MetaTileEntities.BATTERY_BUFFER[GTValues.HV][2].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtOctal, Materials.Gold), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_ev_3x3", MetaTileEntities.BATTERY_BUFFER[GTValues.EV][2].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtOctal, Materials.Aluminium), 'T', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("battery_buffer_lv_4x4", MetaTileEntities.BATTERY_BUFFER[GTValues.LV][3].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Tin), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_mv_4x4", MetaTileEntities.BATTERY_BUFFER[GTValues.MV][3].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Copper), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_hv_4x4", MetaTileEntities.BATTERY_BUFFER[GTValues.HV][3].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Gold), 'T', OreDictNames.chestWood);
        ModHandler.addShapedRecipe("battery_buffer_ev_4x4", MetaTileEntities.BATTERY_BUFFER[GTValues.EV][3].getStackForm(), "WTW", "WMW", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Aluminium), 'T', OreDictNames.chestWood);

        ModHandler.addShapedRecipe("charger_lv", MetaTileEntities.CHARGER[GTValues.LV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Tin), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_LV_LITHIUM, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic));
        ModHandler.addShapedRecipe("charger_mv", MetaTileEntities.CHARGER[GTValues.MV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Copper), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_MV_LITHIUM, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good));
        ModHandler.addShapedRecipe("charger_hv", MetaTileEntities.CHARGER[GTValues.HV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Gold), 'T', OreDictNames.chestWood, 'B', MetaItems.BATTERY_RE_HV_LITHIUM, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced));
        ModHandler.addShapedRecipe("charger_ev", MetaTileEntities.CHARGER[GTValues.EV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Aluminium), 'T', OreDictNames.chestWood, 'B', new UnificationEntry(OrePrefix.battery, Tier.Elite), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite));
        ModHandler.addShapedRecipe("charger_iv", MetaTileEntities.CHARGER[GTValues.IV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Tungsten), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_LAPOTRONIC_ORB, 'C', new UnificationEntry(OrePrefix.circuit, Tier.Ultimate));
        ModHandler.addShapedRecipe("charger_luv", MetaTileEntities.CHARGER[GTValues.LuV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.VanadiumGallium), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_LAPOTRONIC_ORB2, 'C', new UnificationEntry(OrePrefix.circuit, Tier.Ultimate));
        ModHandler.addShapedRecipe("charger_uv", MetaTileEntities.CHARGER[GTValues.UV].getStackForm(), "WTW", "WMW", "BCB", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.NaquadahAlloy), 'T', OreDictNames.chestWood, 'B', MetaItems.ENERGY_INFUSED_LAPOTRONIC_ORB, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate));

        ModHandler.addShapedRecipe("rotor_holder_hv", MetaTileEntities.ROTOR_HOLDER[0].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Gold), 'R', new UnificationEntry(OrePrefix.gear, Materials.StainlessSteel));
        ModHandler.addShapedRecipe("rotor_holder_ev", MetaTileEntities.ROTOR_HOLDER[1].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.YttriumBariumCuprate), 'R', new UnificationEntry(OrePrefix.gear, Materials.Chrome));
        ModHandler.addShapedRecipe("rotor_holder_iv", MetaTileEntities.ROTOR_HOLDER[2].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'W', new UnificationEntry(OrePrefix.wireGtHex, Tier.Elite), 'R', new UnificationEntry(OrePrefix.gear, Materials.HSSS));

        // STEAM MACHINES
        ModHandler.addShapedRecipe("bronze_hull", MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL), "PPP", "PhP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze));
        ModHandler.addShapedRecipe("bronze_bricks_hull", MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_BRICKS_HULL), "PPP", "PhP", "BBB", 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'B', new ItemStack(Blocks.BRICK_BLOCK));
        ModHandler.addShapedRecipe("steel_hull", MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_HULL), "PPP", "PhP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe("steel_bricks_hull", MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_BRICKS_HULL), "PPP", "PhP", "BBB", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'B', new ItemStack(Blocks.BRICK_BLOCK));

        ModHandler.addShapedRecipe("steam_boiler_coal_bronze", MetaTileEntities.STEAM_BOILER_COAL_BRONZE.getStackForm(), "PPP", "P P", "BFB", 'F', OreDictNames.craftingFurnace, 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'B', new ItemStack(Blocks.BRICK_BLOCK));
        ModHandler.addShapedRecipe("steam_boiler_coal_steel", MetaTileEntities.STEAM_BOILER_COAL_STEEL.getStackForm(), "PPP", "P P", "BFB", 'F', OreDictNames.craftingFurnace, 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'B', new ItemStack(Blocks.BRICK_BLOCK));
        ModHandler.addShapedRecipe("steam_boiler_lava_bronze", MetaTileEntities.STEAM_BOILER_LAVA_BRONZE.getStackForm(), "PPP", "GGG", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_BRICKS_HULL), 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("steam_boiler_lava_steel", MetaTileEntities.STEAM_BOILER_LAVA_STEEL.getStackForm(), "PPP", "GGG", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_BRICKS_HULL), 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'G', new ItemStack(Blocks.GLASS, 1));
        ModHandler.addShapedRecipe("steam_boiler_solar_bronze", MetaTileEntities.STEAM_BOILER_SOLAR_BRONZE.getStackForm(), "GGG", "SSS", "PMP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_BRICKS_HULL), 'P', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'S', new UnificationEntry(OrePrefix.plate, Materials.Silver), 'G', new ItemStack(Blocks.GLASS));

        ModHandler.addShapedRecipe("steam_furnace_bronze", MetaTileEntities.STEAM_FURNACE_BRONZE.getStackForm(), "XXX", "XMX", "XFX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_BRICKS_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("steam_furnace_steel", MetaTileEntities.STEAM_FURNACE_STEEL.getStackForm(), "XXX", "XMX", "XFX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_BRICKS_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("steam_macerator_bronze", MetaTileEntities.STEAM_MACERATOR_BRONZE.getStackForm(), "DXD", "XMX", "PXP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'P', OreDictNames.craftingPiston, 'D', new UnificationEntry(OrePrefix.gem, Materials.Diamond));
        ModHandler.addShapedRecipe("steam_macerator_steel", MetaTileEntities.STEAM_MACERATOR_STEEL.getStackForm(), "DXD", "XMX", "PXP", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel), 'P', OreDictNames.craftingPiston, 'D', new UnificationEntry(OrePrefix.gem, Materials.Diamond));
        ModHandler.addShapedRecipe("steam_extractor_bronze", MetaTileEntities.STEAM_EXTRACTOR_BRONZE.getStackForm(), "XXX", "PMG", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'P', OreDictNames.craftingPiston, 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("steam_extractor_steel", MetaTileEntities.STEAM_EXTRACTOR_STEEL.getStackForm(), "XXX", "PMG", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel), 'P', OreDictNames.craftingPiston, 'G', new ItemStack(Blocks.GLASS));
        ModHandler.addShapedRecipe("steam_hammer_bronze", MetaTileEntities.STEAM_HAMMER_BRONZE.getStackForm(), "XPX", "XMX", "XAX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'P', OreDictNames.craftingPiston, 'A', OreDictNames.craftingAnvil);
        ModHandler.addShapedRecipe("steam_hammer_steel", MetaTileEntities.STEAM_HAMMER_STEEL.getStackForm(), "XPX", "XMX", "XAX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel), 'P', OreDictNames.craftingPiston, 'A', OreDictNames.craftingAnvil);
        ModHandler.addShapedRecipe("steam_compressor_bronze", MetaTileEntities.STEAM_COMPRESSOR_BRONZE.getStackForm(), "XXX", "PMP", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'P', OreDictNames.craftingPiston);
        ModHandler.addShapedRecipe("steam_compressor_steel", MetaTileEntities.STEAM_COMPRESSOR_STEEL.getStackForm(), "XXX", "PMP", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel), 'P', OreDictNames.craftingPiston);
        ModHandler.addShapedRecipe("steam_alloy_smelter_bronze", MetaTileEntities.STEAM_ALLOY_SMELTER_BRONZE.getStackForm(), "XXX", "FMF", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_BRICKS_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("steam_alloy_smelter_steel", MetaTileEntities.STEAM_ALLOY_SMELTER_STEEL.getStackForm(), "XXX", "FMF", "XXX", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(STEEL_BRICKS_HULL), 'X', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel), 'F', OreDictNames.craftingFurnace);

        // MULTI BLOCK CONTROLLERS
        ModHandler.addShapedRecipe("bronze_primitive_blast_furnace", MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(), "PFP", "FwF", "PFP", 'P', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS), 'F', OreDictNames.craftingFurnace);
        ModHandler.addShapedRecipe("coke_oven", MetaTileEntities.COKE_OVEN.getStackForm(), "PIP", "IwI", "PIP", 'P', MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.COKE_BRICKS), 'I', new UnificationEntry(OrePrefix.plate, Materials.Iron));
        ModHandler.addShapelessRecipe("coke_oven_hatch", MetaTileEntities.COKE_OVEN_HATCH.getStackForm(), MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.COKE_BRICKS), MetaTileEntities.BRONZE_TANK.getStackForm());
        ModHandler.addShapedRecipe("electric_blast_furnace", MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), "FFF", "CMC", "WCW", 'M', MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF), 'F', OreDictNames.craftingFurnace, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("vacuum_freezer", MetaTileEntities.VACUUM_FREEZER.getStackForm(), "PPP", "CMC", "WCW", 'M', MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF), 'P', MetaItems.ELECTRIC_PUMP_MV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold));
        ModHandler.addShapedRecipe("implosion_compressor", MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm(), "OOO", "CMC", "WCW", 'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID), 'O', new UnificationEntry(OrePrefix.stone, Materials.Obsidian), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium));
        ModHandler.addShapedRecipe("distillation_tower", MetaTileEntities.DISTILLATION_TOWER.getStackForm(), "CBC", "FMF", "CBC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(MV), 'B', new UnificationEntry(OrePrefix.pipeLarge, Materials.StainlessSteel), 'C', new UnificationEntry(OrePrefix.circuit, Tier.Good), 'F', MetaItems.ELECTRIC_PUMP_MV);
        ModHandler.addShapedRecipe("cracking_unit", MetaTileEntities.CRACKER.getStackForm(), "CEC", "PHP", "CEC", 'C', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL), 'E', MetaItems.ELECTRIC_PUMP_HV, 'P', new UnificationEntry(OrePrefix.circuit, Tier.Advanced), 'H', MetaTileEntities.HULL[GTValues.HV].getStackForm());

        ModHandler.addShapedRecipe("pyrolyse_oven", MetaTileEntities.PYROLYSE_OVEN.getStackForm(), "WEP", "EME", "WCP", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'W', MetaItems.ELECTRIC_PISTON_MV, 'P', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Cupronickel), 'E', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'C', MetaItems.ELECTRIC_PUMP_MV);
        ModHandler.addShapedRecipe("diesel_engine", MetaTileEntities.DIESEL_ENGINE.getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_EV, 'E', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(OrePrefix.circuit, Tier.Elite), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.TungstenSteel), 'G', new UnificationEntry(OrePrefix.gear, Materials.Titanium));
        ModHandler.addShapedRecipe("engine_intake_casing", MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.ENGINE_INTAKE_CASING), "PhP", "RFR", "PwP", 'R', new UnificationEntry(OrePrefix.pipeMedium, Materials.Titanium), 'F', MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE), 'P', new UnificationEntry(OrePrefix.rotor, Materials.Titanium));
        ModHandler.addShapedRecipe("multi_furnace", MetaTileEntities.MULTI_FURNACE.getStackForm(), "PPP", "ASA", "CAC", 'P', Blocks.FURNACE, 'A', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'S', MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.AnnealedCopper));

        ModHandler.addShapedRecipe("large_steam_turbine", MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(OrePrefix.gear, Materials.Steel), 'P', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'A', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(OrePrefix.pipeLarge, Materials.Steel));
        ModHandler.addShapedRecipe("large_gas_turbine", MetaTileEntities.LARGE_GAS_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(OrePrefix.gear, Materials.StainlessSteel), 'P', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite), 'A', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(OrePrefix.pipeLarge, Materials.StainlessSteel));
        ModHandler.addShapedRecipe("large_plasma_turbine", MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm(), "PSP", "SAS", "CSC", 'S', new UnificationEntry(OrePrefix.gear, Materials.TungstenSteel), 'P', new UnificationEntry(OrePrefix.circuit, Tier.Ultimate), 'A', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', new UnificationEntry(OrePrefix.pipeLarge, Materials.TungstenSteel));

        ModHandler.addShapedRecipe("large_bronze_boiler", MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'S', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'A', MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS));
        ModHandler.addShapedRecipe("large_steel_boiler", MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'S', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'A', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID));
        ModHandler.addShapedRecipe("large_titanium_boiler", MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'S', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite), 'A', MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE));
        ModHandler.addShapedRecipe("large_tungstensteel_boiler", MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(), "PSP", "SAS", "PSP", 'P', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'S', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite), 'A', MetaBlocks.METAL_CASING.getItemVariant(TUNGSTENSTEEL_ROBUST));

        ModHandler.addShapedRecipe("large_transformer", MetaTileEntities.LARGE_TRANSFORMER.getStackForm(), "CWC", "WDW", "CWC", 'C', new UnificationEntry(OrePrefix.cableGtOctal, Materials.HSSG), 'W', MetaItems.FIELD_GENERATOR_EV, 'D', MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.HIGH_POWER));
        ModHandler.addShapedRecipe("high_power_casing", MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.HIGH_POWER), "CWC", "WHW", "CWC", 'C', OreDictUnifier.get(OrePrefix.plate, Materials.Acrylic), 'W', new UnificationEntry(OrePrefix.cableGtOctal, Materials.HSSG), 'H', HULL.getIngredient(GTValues.EV));

        // GENERATORS
        ModHandler.addShapedRecipe("diesel_generator_lv", MetaTileEntities.DIESEL_GENERATOR[0].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_LV, 'E', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'G', new UnificationEntry(OrePrefix.gear, Materials.Steel));
        ModHandler.addShapedRecipe("diesel_generator_mv", MetaTileEntities.DIESEL_GENERATOR[1].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_MV, 'E', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'G', new UnificationEntry(OrePrefix.gear, Materials.Aluminium));
        ModHandler.addShapedRecipe("diesel_generator_hv", MetaTileEntities.DIESEL_GENERATOR[2].getStackForm(), "PCP", "EME", "GWG", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_HV, 'E', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'G', new UnificationEntry(OrePrefix.gear, Materials.StainlessSteel));

        ModHandler.addShapedRecipe("gas_turbine_lv", MetaTileEntities.GAS_TURBINE[0].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Tin), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("gas_turbine_mv", MetaTileEntities.GAS_TURBINE[1].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Bronze), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("gas_turbine_hv", MetaTileEntities.GAS_TURBINE[2].getStackForm(), "CRC", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Steel), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold));

        ModHandler.addShapedRecipe("steam_turbine_lv", MetaTileEntities.STEAM_TURBINE[0].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_LV, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Tin), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'P', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze));
        ModHandler.addShapedRecipe("steam_turbine_mv", MetaTileEntities.STEAM_TURBINE[1].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_MV, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Bronze), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'P', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel));
        ModHandler.addShapedRecipe("steam_turbine_hv", MetaTileEntities.STEAM_TURBINE[2].getStackForm(), "PCP", "RMR", "EWE", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'E', MetaItems.ELECTRIC_MOTOR_HV, 'R', new UnificationEntry(OrePrefix.rotor, Materials.Steel), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'P', new UnificationEntry(OrePrefix.pipeSmall, Materials.StainlessSteel));

        ModHandler.addShapedRecipe("workbench_bronze", MetaTileEntities.WORKBENCH.getStackForm(), "CWC", "PHP", "PhP", 'C', OreDictNames.chestWood, 'W', new ItemStack(Blocks.CRAFTING_TABLE), 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'H', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL));

        ModHandler.addShapedRecipe("magic_energy_absorber", MetaTileEntities.MAGIC_ENERGY_ABSORBER.getStackForm(), "PCP", "PMP", "PCP", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'P', MetaItems.SENSOR_EV, 'C', new UnificationEntry(OrePrefix.circuit, Tier.Elite));

        // MACHINES
        registerMachineRecipe(MetaTileEntities.MINER, "APA", "QHQ", "CSC", 'A', ROBOT_ARM, 'P', PISTON, 'Q', CABLE_QUAD, 'H', HULL, 'C', CIRCUIT, 'S', SENSOR);
        registerMachineRecipe(MetaTileEntities.AMPLIFABRICATOR, "QPQ", "PHP", "CPC", 'Q', CABLE_QUAD, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
        registerMachineRecipe(MetaTileEntities.REPLICATOR, "EFE", "CHC", "EQE", 'E', EMITTER, 'F', FIELD_GENERATOR, 'H', HULL, 'C', CIRCUIT, 'Q', CABLE_QUAD);
        registerMachineRecipe(MetaTileEntities.RECYCLER, "GCG", "PHP", "WCW", 'G', Items.GLOWSTONE_DUST, 'C', CIRCUIT, 'P', PISTON, 'H', HULL, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CROP_REPLICATOR, "CFC", "CHC", "EWE", 'C', CIRCUIT, 'F', FIELD_GENERATOR, 'H', HULL, 'E', EMITTER, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.SCANNER, "CEC", "WHW", "CSC", 'C', CIRCUIT, 'E', EMITTER, 'W', CABLE, 'H', HULL, 'S', SENSOR);
        registerMachineRecipe(MetaTileEntities.CROP_GENE_EXTRACTOR, "SES", "CHC", "ISI", 'S', SENSOR, 'E', EMITTER, 'C', CABLE, 'H', HULL, 'I', CIRCUIT);
        registerMachineRecipe(MetaTileEntities.CROP_SYNTHESISER, "FCF", "CHC", "EEE", 'F', FIELD_GENERATOR, 'C', CIRCUIT, 'H', HULL, 'E', EMITTER);
        registerMachineRecipe(MetaTileEntities.CROP_WEED_PICKER, "RRR", "AHA", "RRR", 'R', OreDictUnifier.get(OrePrefix.stickLong, Materials.Steel), 'A', ROBOT_ARM, 'H', HULL);
        registerMachineRecipe(MetaTileEntities.MASS_FAB, "CFC", "WHW", "CFC", 'C', CIRCUIT, 'F', FIELD_GENERATOR, 'H', HULL, 'W', CABLE_QUAD);
        registerMachineRecipe(MetaTileEntities.ALLOY_SMELTER, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE, 'C', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.ASSEMBLER, "ACA", "VMV", "WCW", 'M', HULL, 'V', CONVEYOR, 'A', ROBOT_ARM, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.BENDER, "PwP", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CANNER, "WPW", "CMC", "GGG", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.COMPRESSOR, " C ", "PMP", "WCW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CUTTER, "WCG", "VMB", "CWE", 'M', HULL, 'E', MOTOR, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', OreDictNames.craftingGrinder);
        registerMachineRecipe(MetaTileEntities.ELECTRIC_FURNACE, "ECE", "CMC", "WCW", 'M', HULL, 'E', CIRCUIT, 'W', CABLE, 'C', COIL_HEATING);
        registerMachineRecipe(MetaTileEntities.EXTRACTOR, "GCG", "EMP", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.EXTRUDER, "CCE", "XMP", "CCE", 'M', HULL, 'X', PISTON, 'E', CIRCUIT, 'P', PIPE, 'C', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.LATHE, "WCW", "EMD", "CWP", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'D', DIAMOND);
        registerMachineRecipe(MetaTileEntities.MACERATOR, "PEG", "WWM", "CCW", 'M', HULL, 'E', MOTOR, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'G', GRINDER);
        registerMachineRecipe(MetaTileEntities.MICROWAVE, "LWC", "LMR", "LEC", 'M', HULL, 'E', MOTOR, 'R', EMITTER, 'C', CIRCUIT, 'W', CABLE, 'L', new UnificationEntry(OrePrefix.plate, Materials.Lead));
        registerMachineRecipe(MetaTileEntities.WIREMILL, "EWE", "CMC", "EWE", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.CENTRIFUGE, "CEC", "WMW", "CEC", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.ELECTROLYZER, "IGI", "IMI", "CWC", 'M', HULL, 'C', CIRCUIT, 'W', CABLE, 'I', WIRE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.THERMAL_CENTRIFUGE, "CEC", "OMO", "WEW", 'M', HULL, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'O', COIL_HEATING_DOUBLE);
        registerMachineRecipe(MetaTileEntities.ORE_WASHER, "RGR", "CEC", "WMW", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.PACKER, "BCB", "RMV", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.UNPACKER, "BCB", "VMR", "WCW", 'M', HULL, 'R', ROBOT_ARM, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_REACTOR, "GRG", "WEW", "CMC", 'M', HULL, 'R', ROTOR, 'E', MOTOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_CANNER, "GCG", "GMG", "WPW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.BREWERY, "GPG", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', new ItemStack(Blocks.GLASS));
        registerMachineRecipe(MetaTileEntities.FERMENTER, "WPW", "GMG", "WCW", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_EXTRACTOR, "GCG", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.FLUID_SOLIDIFIER, "PGP", "WMW", "CBC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS, 'B', OreDictNames.chestWood);
        registerMachineRecipe(MetaTileEntities.DISTILLERY, "GBG", "CMC", "WPW", 'M', HULL, 'P', PUMP, 'B', STICK_DISTILLATION, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.CHEMICAL_BATH, "VGW", "PGV", "CMC", 'M', HULL, 'P', PUMP, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.POLARIZER, "ZSZ", "WMW", "ZSZ", 'M', HULL, 'S', STICK, 'Z', COIL_ELECTRIC, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.ELECTROMAGNETIC_SEPARATOR, "VWZ", "WMS", "CWZ", 'M', HULL, 'S', STICK, 'Z', COIL_ELECTRIC, 'V', CONVEYOR, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.AUTOCLAVE, "IGI", "IMI", "CPC", 'M', HULL, 'P', PUMP, 'C', CIRCUIT, 'I', PLATE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.MIXER, "GRG", "GEG", "CMC", 'M', HULL, 'E', MOTOR, 'R', ROTOR, 'C', CIRCUIT, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.LASER_ENGRAVER, "PEP", "CMC", "WCW", 'M', HULL, 'E', EMITTER, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.FORMING_PRESS, "WPW", "CMC", "WPW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.FORGE_HAMMER, "WPW", "CMC", "WAW", 'M', HULL, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE, 'A', OreDictNames.craftingAnvil);
        registerMachineRecipe(MetaTileEntities.FLUID_HEATER, "OGO", "PMP", "WCW", 'M', HULL, 'P', PUMP, 'O', COIL_HEATING_DOUBLE, 'C', CIRCUIT, 'W', CABLE, 'G', GLASS);
        registerMachineRecipe(MetaTileEntities.SIFTER, "WFW", "PMP", "CFC", 'M', HULL, 'P', PISTON, 'F', MetaItems.ITEM_FILTER, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.ARC_FURNACE, "WGW", "CMC", "PPP", 'M', HULL, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE_QUAD, 'G', new UnificationEntry(OrePrefix.ingot, Materials.Graphite));
        registerMachineRecipe(MetaTileEntities.PLASMA_ARC_FURNACE, "WGW", "CMC", "TPT", 'M', HULL, 'P', PLATE, 'C', CIRCUIT, 'W', CABLE_QUAD, 'T', PUMP, 'G', new UnificationEntry(OrePrefix.ingot, Materials.Graphite));

        registerMachineRecipe(MetaTileEntities.PUMP, "WGW", "GMG", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', PISTON);
        registerMachineRecipe(MetaTileEntities.FISHER, "WTW", "PMP", "TGT", 'M', HULL, 'W', CIRCUIT, 'G', PUMP, 'T', MOTOR, 'P', PISTON);
        registerMachineRecipe(MetaTileEntities.AIR_COLLECTOR, "WFW", "PHP", "WCW", 'W', Blocks.IRON_BARS, 'F', MetaItems.FLUID_FILTER, 'P', PUMP, 'H', HULL, 'C', CIRCUIT);
        registerMachineRecipe(MetaTileEntities.ITEM_COLLECTOR, "MRM", "RHR", "CWC", 'M', MOTOR, 'R', ROTOR, 'H', HULL, 'C', CIRCUIT, 'W', CABLE);
        registerMachineRecipe(MetaTileEntities.BLOCK_BREAKER, "MGM", "CHC", "WSW", 'M', MOTOR, 'H', HULL, 'C', CIRCUIT, 'W', CABLE, 'S', Blocks.CHEST, 'G', MetaItems.COMPONENT_GRINDER_DIAMOND);

        registerMachineRecipe(MetaTileEntities.QUANTUM_CHEST, "CPC", "PHP", "CFC", 'C', CIRCUIT, 'P', PLATE, 'F', FIELD_GENERATOR, 'H', HULL);
        registerMachineRecipe(MetaTileEntities.QUANTUM_TANK, "CFC", "PHP", "CPC", 'C', CIRCUIT, 'P', PLATE, 'F', FIELD_GENERATOR, 'H', HULL);

        registerMachineRecipe(MetaTileEntities.WORLD_ACCELERATOR, "FEF", "EHE", "FEF", 'F', FIELD_GENERATOR, 'E', EMITTER, 'H', HULL);
        registerMachineRecipe(MetaTileEntities.WIRELESS_CHARGER, "EFE", "IHI", "CFC", 'E', EMITTER, 'F', FIELD_GENERATOR, 'H', HULL, 'I', CIRCUIT, 'F', FIELD_GENERATOR, 'C', CABLE);

        ModHandler.addShapelessRecipe("small_wooden_chest", MetaTileEntities.SMALL_WOODEN_CHEST.getStackForm(8), "chest", 's');
        ModHandler.addShapelessRecipe("wooden_chest", MetaTileEntities.WOODEN_CHEST.getStackForm(), "chest", 'r');
        ModHandler.addShapedRecipe("bronze_chest", MetaTileEntities.BRONZE_CHEST.getStackForm(), "XXX", "X X", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Bronze));
        ModHandler.addShapedRecipe("steel_chest", MetaTileEntities.STEEL_CHEST.getStackForm(), "XXX", "X X", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe("stainless_steel_chest", MetaTileEntities.STAINLESS_STEEL_CHEST.getStackForm(), "XXX", "X X", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel));
        ModHandler.addShapedRecipe("titanium_chest", MetaTileEntities.TITANIUM_CHEST.getStackForm(), "XXX", "X X", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Titanium));
        ModHandler.addShapedRecipe("tungsten_steel_chest", MetaTileEntities.TUNGSTENSTEEL_CHEST.getStackForm(), "XXX", "X X", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel));

        ModHandler.addShapedRecipe("wooden_tank", MetaTileEntities.WOODEN_TANK.getStackForm(), "XYX", "Y Y", "XYX", 'X', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'Y', new UnificationEntry(OrePrefix.blockGlass));
        ModHandler.addShapedRecipe("bronze_tank", MetaTileEntities.BRONZE_TANK.getStackForm(), "XYX", "Y Y", "XYX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'Y', new UnificationEntry(OrePrefix.blockGlass));
        ModHandler.addShapedRecipe("steel_tank", MetaTileEntities.STEEL_TANK.getStackForm(), "XYX", "Y Y", "XYX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'Y', new UnificationEntry(OrePrefix.blockGlass));
        ModHandler.addShapedRecipe("stainless_steel_tank", MetaTileEntities.STAINLESS_STEEL_TANK.getStackForm(), "XYX", "Y Y", "XYX", 'X', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'Y', new UnificationEntry(OrePrefix.blockGlass));
        ModHandler.addShapedRecipe("titanium_tank", MetaTileEntities.TITANIUM_TANK.getStackForm(), "XYX", "Y Y", "XYX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'Y', new UnificationEntry(OrePrefix.blockGlass));
        ModHandler.addShapedRecipe("tungsten_steel_tank", MetaTileEntities.TUNGSTENSTEEL_TANK.getStackForm(), "XYX", "Y Y", "XYX", 'X', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'Y', new UnificationEntry(OrePrefix.blockGlass));

        ModHandler.addShapedRecipe("tesla_coil", MetaTileEntities.TESLA_COIL.getStackForm(), "XXX", "YHY", "XXX", 'X', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper), 'Y', MetaItems.EMITTER_MV, 'H', MetaTileEntities.HULL[GTValues.MV].getStackForm());




        ModHandler.addShapedRecipe("energy_output_hatch_lv", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Tin));
        ModHandler.addShapedRecipe("energy_output_hatch_mv", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.MV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper));
        ModHandler.addShapedRecipe("energy_output_hatch_hv", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.HV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Gold));
        ModHandler.addShapedRecipe("energy_output_hatch_ev", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Aluminium));
        ModHandler.addShapedRecipe("energy_output_hatch_iv", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.IV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Tungsten));
        ModHandler.addShapedRecipe("energy_output_hatch_luv", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LuV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.VanadiumGallium));
        ModHandler.addShapedRecipe("energy_output_hatch_uv", MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.UV].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.NaquadahAlloy));

        ModHandler.addShapedRecipe("energy_input_hatch_lv", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Tin));
        ModHandler.addShapedRecipe("energy_input_hatch_mv", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.MV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper));
        ModHandler.addShapedRecipe("energy_input_hatch_hv", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Gold));
        ModHandler.addShapedRecipe("energy_input_hatch_ev", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Aluminium));
        ModHandler.addShapedRecipe("energy_input_hatch_iv", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.IV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Tungsten));
        ModHandler.addShapedRecipe("energy_input_hatch_luv", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LuV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.VanadiumGallium));
        ModHandler.addShapedRecipe("energy_input_hatch_uv", MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', new UnificationEntry(OrePrefix.wireGtDouble, Materials.NaquadahAlloy));

    }

    /*
    public static void addHatchRecipes(int i, OrePrefix p) {
        ModHandler.addShapedRecipe("energy_output_hatch_lv"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', new UnificationEntry(p, Materials.Tin));
        ModHandler.addShapedRecipe("energy_output_hatch_mv"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.MV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', new UnificationEntry(p, Materials.Copper));
        ModHandler.addShapedRecipe("energy_output_hatch_hv"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.HV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(p, Materials.Gold));
        ModHandler.addShapedRecipe("energy_output_hatch_ev"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(p, Materials.Aluminium));
        ModHandler.addShapedRecipe("energy_output_hatch_iv"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.IV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(p, Materials.Tungsten));
        ModHandler.addShapedRecipe("energy_output_hatch_luv"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.LuV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', new UnificationEntry(p, Materials.VanadiumGallium));
        ModHandler.addShapedRecipe("energy_output_hatch_uv"+i, MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.UV][i].getStackForm(), " MC", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', new UnificationEntry(p, Materials.NaquadahAlloy));

        ModHandler.addShapedRecipe("energy_input_hatch_lv"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.LV].getStackForm(), 'C', new UnificationEntry(p, Materials.Tin));
        ModHandler.addShapedRecipe("energy_input_hatch_mv"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.MV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.MV].getStackForm(), 'C', new UnificationEntry(p, Materials.Copper));
        ModHandler.addShapedRecipe("energy_input_hatch_hv"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.HV].getStackForm(), 'C', new UnificationEntry(p, Materials.Gold));
        ModHandler.addShapedRecipe("energy_input_hatch_ev"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'C', new UnificationEntry(p, Materials.Aluminium));
        ModHandler.addShapedRecipe("energy_input_hatch_iv"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.IV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'C', new UnificationEntry(p, Materials.Tungsten));
        ModHandler.addShapedRecipe("energy_input_hatch_luv"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LuV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.LuV].getStackForm(), 'C', new UnificationEntry(p, Materials.VanadiumGallium));
        ModHandler.addShapedRecipe("energy_input_hatch_uv"+i, MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV][i].getStackForm(), "CM ", 'M', MetaTileEntities.HULL[GTValues.UV].getStackForm(), 'C', new UnificationEntry(p, Materials.NaquadahAlloy));
    }*/



    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(T[] metaTileEntities, Object... recipe) {
        for (T metaTileEntity : metaTileEntities) {
            ModHandler.addShapedRecipe(metaTileEntity.getMetaName(), metaTileEntity.getStackForm(), prepareRecipe(metaTileEntity.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        for (int i = 3; i < recipe.length; i++) {
            if (recipe[i] instanceof CraftingComponent) {
                recipe[i] = ((CraftingComponent) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
    }

}