package gtr.loaders.recipe;

import gtr.api.GTValues;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.ModHandler;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.builders.CokeOvenRecipeBuilder;
import gtr.api.recipes.builders.PBFRecipeBuilder;
import gtr.api.recipes.ingredients.IntCircuitIngredient;
import gtr.api.recipes.machines.RecipeMapRecycler;
import gtr.api.recipes.machines.RecipeMapScanner;
import gtr.api.unification.Element;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.MarkerMaterials.Tier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.DustMaterial;
import gtr.api.unification.material.type.FluidMaterial;
import gtr.api.unification.material.type.IngotMaterial;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.MaterialStack;
import gtr.common.ConfigHolder;
import gtr.common.blocks.BlockConcrete.ConcreteVariant;
import gtr.common.blocks.BlockGranite.GraniteVariant;
import gtr.common.blocks.BlockMachineCasing.MachineCasingType;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockMineral.MineralVariant;
import gtr.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gtr.common.blocks.BlockTurbineCasing.TurbineCasingType;
import gtr.common.blocks.BlockWireCoil.CoilType;
import gtr.common.blocks.MetaBlocks;
import gtr.common.blocks.StoneBlock;
import gtr.common.blocks.StoneBlock.ChiselingVariant;
import gtr.common.blocks.wood.BlockGregLog.LogVariant;
import gtr.common.items.MetaItems;
import gtr.common.items.behaviors.DataBehavior;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.common.metatileentities.storage.MetaTileEntityQuantumChest;
import gtr.common.metatileentities.storage.MetaTileEntityQuantumTank;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collection;

import static gtr.api.GTValues.L;
import static gtr.api.GTValues.M;
import static gtr.api.util.DyeUtil.getOrdictColorName;
import static gtr.common.items.MetaItems.*;

public class MachineRecipeLoader {


    private MachineRecipeLoader() {
    }

    public static void init() {
        ChemistryRecipes.init();
        FuelRecipes.registerFuels();
        AssemblyLineRecipeLoader.registerAssemblyLineRecipes();
        ComponentRecipes.init();

        registerCutterRecipes();
        registerChemicalRecipes();
        registerChemicalBathRecipes();
        registerDecompositionRecipes();
        registerBlastFurnaceRecipes();
        registerAssemblerRecipes();
        registerAlloyRecipes();
        registerBendingCompressingRecipes();
        registerCokeOvenRecipes();
        registerFluidRecipes();
        registerMixingCrystallizationRecipes();
        registerPrimitiveBlastFurnaceRecipes();
        registerRecyclingRecipes();
        registerStoneBricksRecipes();
        registerOrganicRecyclingRecipes();
        registerFusionRecipes();
        registerNBTRemoval();
        registerUURecipes();

    }

    private static void registerUURecipes() {
        RecipeMaps.MASS_FAB_RECIPES.recipeBuilder()
            .fluidInputs(Materials.UUA.getFluid(1))
            .fluidOutputs(Materials.UUM.getFluid(1))
            .EUt(256).duration(803).buildAndRegister();

        RecipeMaps.MASS_FAB_RECIPES.recipeBuilder()
            .fluidOutputs(Materials.UUM.getFluid(1))
            .EUt(256).duration(3215).buildAndRegister();

        for (Material m : Material.MATERIAL_REGISTRY) {
            if (m.element != null) {
                if (m instanceof DustMaterial) {
                    for (String s : OreDictUnifier.getOreDictionaryNames(OreDictUnifier.get(OrePrefix.dust, m))) {
                        if (!s.toLowerCase().contains("regular")) {
                            ItemStack output3 = MetaItems.DATA_STICK.getStackForm();
                            output3.setTagCompound(new NBTTagCompound());
                            output3.getTagCompound().setShort("data", DataBehavior.combine((short) m.element.ordinal(), DataBehavior.DataType.ELEMENT));
                            RecipeMapScanner.JEI_MAP.recipeBuilder().EUt(32).duration(2400).input(s, 1).inputs(MetaItems.DATA_STICK.getStackForm())
                                .outputs(output3).buildAndRegister();
                            RecipeMapScanner.RECIPE_MAP.recipeBuilder().EUt(32).duration(2400).input(s, 1).inputs(MetaItems.DATA_STICK.getStackForm())
                                .outputs(output3).buildAndRegister();
                        }
                    }

                    ItemStack stick = DATA_STICK.getStackForm();
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setShort("data", DataBehavior.combine((short) m.element.ordinal(), DataBehavior.DataType.ELEMENT));
                    stick.setTagCompound(compound);
                    long time = getTime(m.element);
                    long uu = getUU(m.element);
                    if (time > 0 && uu > 0 && time < Integer.MAX_VALUE && uu < Integer.MAX_VALUE) {
                        RecipeMaps.REPLICATOR_RECIPES.recipeBuilder().notConsumable(stick).fluidInputs(Materials.UUM.getFluid((int) uu)).outputs(OreDictUnifier.get(OrePrefix.dust, m)).duration((int) time).EUt(30).buildAndRegister();
                    }
                } else if (m instanceof FluidMaterial && ((FluidMaterial) m).getMaterialFluid() != null) {
                    for (String s : OreDictUnifier.getOreDictionaryNames(OreDictUnifier.get(OrePrefix.dust, m))) {
                        if (!s.toLowerCase().contains("regular")) {
                            ItemStack output3 = MetaItems.DATA_STICK.getStackForm();
                            output3.setTagCompound(new NBTTagCompound());
                            output3.getTagCompound().setShort("data", DataBehavior.combine((short) m.element.ordinal(), DataBehavior.DataType.ELEMENT));
                            RecipeMapScanner.JEI_MAP.recipeBuilder().EUt(32).duration(2400).fluidInputs(((FluidMaterial) m).getFluid(144)).inputs(MetaItems.DATA_STICK.getStackForm())
                                .outputs(output3).buildAndRegister();
                            RecipeMapScanner.RECIPE_MAP.recipeBuilder().EUt(32).duration(2400).fluidInputs(((FluidMaterial) m).getFluid(144)).inputs(MetaItems.DATA_STICK.getStackForm())
                                .outputs(output3).buildAndRegister();
                        }
                    }

                    ItemStack stick = DATA_STICK.getStackForm();
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setShort("data", DataBehavior.combine((short) m.element.ordinal(), DataBehavior.DataType.ELEMENT));
                    stick.setTagCompound(compound);
                    long time = getTime(m.element);
                    long uu = getUU(m.element);
                    if (time > 0 && uu > 0 && time < Integer.MAX_VALUE && uu < Integer.MAX_VALUE) {
                        RecipeMaps.REPLICATOR_RECIPES.recipeBuilder().notConsumable(stick).fluidInputs(Materials.UUM.getFluid((int) uu)).fluidOutputs(((FluidMaterial) m).getFluid(144)).duration((int) time).EUt(30).buildAndRegister();
                    }


                }
            }
        }

        ItemStack i = DATA_STICK.getStackForm();
        ItemStack i2 = DATA_STICK.getStackForm();
        ItemStack i3 = DATA_STICK.getStackForm();
        i.setStackDisplayName("Data Stick To Copy");
        i2.setStackDisplayName("Any Data Stick (Will Be Erased)");
        i3.setStackDisplayName("Copy of Input Stick");

        RecipeMapScanner.JEI_MAP.recipeBuilder().duration(80).EUt(24).notConsumable(i).inputs(i2).outputs(i3).buildAndRegister();


        ItemStack s3 = new ItemStack(Blocks.COBBLESTONE, 1);
        s3.setStackDisplayName("This can be any item");
        RecipeMapRecycler.JEI_MAP.recipeBuilder().duration(40).EUt(1).inputs(s3).chancedOutput(RecipeMapRecycler.getScrap(), 1250, 1000).buildAndRegister();

        if (!Loader.isModLoaded("ic2")) {
            ModHandler.addShapedRecipe("scrap_box", SCRAP_BOX.getStackForm(), "III", "III", "III", 'I', SCRAP.getStackForm());
        }

        ItemStack scrap = RecipeMapRecycler.getScrap();
        scrap.setCount(9);
        RecipeMaps.AMPLIFABRICATOR_RECIPES.recipeBuilder().duration(180).EUt(30).inputs(scrap).fluidOutputs(Materials.UUA.getFluid(9)).buildAndRegister();

        RecipeMaps.AMPLIFABRICATOR_RECIPES.recipeBuilder().duration(180).EUt(30).inputs(RecipeMapRecycler.getScrapBox()).fluidOutputs(Materials.UUA.getFluid(9)).buildAndRegister();

    }

    private static long getUU(Element e) {
        double exponent = ConfigHolder.uuAmplifier;
        return (long) Math.pow(e.getMass(), exponent);
    }

    private static long getTime(Element e) {
        double exponent = ConfigHolder.timeAmplifier;
        return 1024L * (long) Math.pow(e.getMass(), exponent);
    }

    private static void registerNBTRemoval() {
        for (MetaTileEntityQuantumChest chest : MetaTileEntities.QUANTUM_CHEST)
            ModHandler.addShapelessRecipe("quantum_chest_nbt_" + chest.getTier(), chest.getStackForm(), chest.getStackForm());

        for (MetaTileEntityQuantumTank tank : MetaTileEntities.QUANTUM_TANK)
            ModHandler.addShapelessRecipe("quantum_tank_nbt_" + tank.getTier(), tank.getStackForm(), tank.getStackForm());
    }

    private static void registerFusionRecipes() {

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Tritium.getFluid(16), Materials.Deuterium.getFluid(16))
            .fluidOutputs(Materials.Helium.getPlasma(16))
            .duration(512)
            .EUt(16384)
            .EUToStart(150000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Beryllium.getFluid(16), Materials.Yttrium.getFluid(16))
            .fluidOutputs(Materials.Technetium.getFluid(16))
            .duration(64)
            .EUt(16384)
            .EUToStart(5000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Iridium.getFluid(16), Materials.Niobium.getFluid(16))
            .fluidOutputs(Materials.Oganesson.getFluid(16))
            .duration(128)
            .EUt(65536)
            .EUToStart(100000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Carbon.getFluid(125), Materials.Helium3.getFluid(125))
            .fluidOutputs(Materials.Oxygen.getPlasma(125))
            .duration(32)
            .EUt(4096)
            .EUToStart(80000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Beryllium.getFluid(16), Materials.Deuterium.getFluid(375))
            .fluidOutputs(Materials.Nitrogen.getPlasma(175))
            .duration(16)
            .EUt(16384)
            .EUToStart(180000000).buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Silicon.getFluid(16), Materials.Magnesium.getFluid(16))
            .fluidOutputs(Materials.Iron.getPlasma(125))
            .duration(32)
            .EUt(8192)
            .EUToStart(360000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Potassium.getFluid(16), Materials.Fluorine.getFluid(125))
            .fluidOutputs(Materials.Nickel.getPlasma(125))
            .duration(16)
            .EUt(32768)
            .EUToStart(480000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Beryllium.getFluid(16), Materials.Tungsten.getFluid(16))
            .fluidOutputs(Materials.Platinum.getFluid(16))
            .duration(32)
            .EUt(32768)
            .EUToStart(150000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Plutonium.getFluid(16), Materials.Thorium.getFluid(16))
            .fluidOutputs(Materials.Naquadah.getFluid(16))
            .duration(64)
            .EUt(32768)
            .EUToStart(300000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Tungsten.getFluid(16), Materials.Helium.getFluid(16))
            .fluidOutputs(Materials.Osmium.getFluid(16))
            .duration(64)
            .EUt(24578)
            .EUToStart(150000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Manganese.getFluid(16), Materials.Hydrogen.getFluid(16))
            .fluidOutputs(Materials.Iron.getFluid(16))
            .duration(64)
            .EUt(8192)
            .EUToStart(120000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Mercury.getFluid(16), Materials.Magnesium.getFluid(16))
            .fluidOutputs(Materials.Uranium.getFluid(16))
            .duration(64)
            .EUt(49152)
            .EUToStart(240000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Gold.getFluid(16), Materials.Aluminium.getFluid(16))
            .fluidOutputs(Materials.Uranium.getFluid(16))
            .duration(64)
            .EUt(49152)
            .EUToStart(240000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Uranium.getFluid(16), Materials.Helium.getFluid(16))
            .fluidOutputs(Materials.Plutonium.getFluid(16))
            .duration(128)
            .EUt(49152)
            .EUToStart(480000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Vanadium.getFluid(16), Materials.Hydrogen.getFluid(125))
            .fluidOutputs(Materials.Chrome.getFluid(16))
            .duration(64)
            .EUt(24576)
            .EUToStart(140000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Gold.getFluid(16), Materials.Mercury.getFluid(16))
            .fluidOutputs(Materials.Radon.getFluid(125))
            .duration(64)
            .EUt(32768)
            .EUToStart(200000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Tantalum.getFluid(16), Materials.Tritium.getFluid(16))
            .fluidOutputs(Materials.Tungsten.getFluid(16))
            .duration(16)
            .EUt(24576)
            .EUToStart(200000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Silver.getFluid(16), Materials.Lithium.getFluid(16))
            .fluidOutputs(Materials.Indium.getFluid(16))
            .duration(32)
            .EUt(24576)
            .EUToStart(380000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Lithium.getFluid(16), Materials.Tungsten.getFluid(16))
            .fluidOutputs(Materials.Iridium.getFluid(16))
            .duration(32)
            .EUt(32768)
            .EUToStart(300000000)
            .buildAndRegister();

        RecipeMaps.FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Materials.Arsenic.getFluid(16), Materials.Iridium.getFluid(16))
            .fluidOutputs(Materials.Darmstadtium.getFluid(16))
            .duration(32)
            .EUt(524288)
            .EUToStart(480000000)
            .buildAndRegister();

    }

    private static void registerBendingCompressingRecipes() {
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().inputs(new ItemStack(Blocks.ICE, 2, OreDictionary.WILDCARD_VALUE)).outputs(new ItemStack(Blocks.PACKED_ICE)).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().input(OrePrefix.dust, Materials.Ice, 1).outputs(new ItemStack(Blocks.ICE)).buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.WHEAT, 9))
            .inputs(new CountableIngredient(new IntCircuitIngredient(9), 0))
            .outputs(new ItemStack(Blocks.HAY_BLOCK))
            .duration(200).EUt(2)
            .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
            .input(OrePrefix.dust, Materials.Fireclay)
            .outputs(COMPRESSED_FIRECLAY.getStackForm())
            .duration(100).EUt(2)
            .buildAndRegister();


        for (MetaItem.MetaValueItem shapeMold : SHAPE_MOLDS) {
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .duration(120).EUt(22)
                .notConsumable(shapeMold.getStackForm())
                .inputs(SHAPE_EMPTY.getStackForm())
                .outputs(shapeMold.getStackForm())
                .buildAndRegister();
        }

        for (MetaItem.MetaValueItem shapeExtruder : SHAPE_EXTRUDERS) {
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .duration(120).EUt(22)
                .notConsumable(shapeExtruder.getStackForm())
                .inputs(SHAPE_EMPTY.getStackForm())
                .outputs(shapeExtruder.getStackForm())
                .buildAndRegister();
        }

        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
            .duration(512).EUt(512)
            .inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Beryllium, 8),
                OreDictUnifier.get(OrePrefix.dust, Materials.Graphite, 4))
            .outputs(NEUTRON_REFLECTOR.getStackForm())
            .buildAndRegister();

        RecipeMaps.BENDER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(OrePrefix.plate, Materials.Steel, 4)
            .outputs(SHAPE_EMPTY.getStackForm())
            .duration(180).EUt(12)
            .buildAndRegister();

        RecipeMaps.BENDER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(OrePrefix.plate, Materials.Iron, 12)
            .outputs(new ItemStack(Items.BUCKET, 4))
            .duration(800).EUt(4)
            .buildAndRegister();

        RecipeMaps.BENDER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(OrePrefix.plate, Materials.WroughtIron, 12)
            .outputs(new ItemStack(Items.BUCKET, 4))
            .duration(800).EUt(4)
            .buildAndRegister();

        RecipeMaps.BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(OrePrefix.plate, Materials.Iron, 2)
            .outputs(FLUID_CELL.getStackForm())
            .duration(200).EUt(30)
            .buildAndRegister();

        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
            .input(OrePrefix.plate, Materials.Iron, 2)
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .outputs(FLUID_CELL.getStackForm())
            .duration(200).EUt(30)
            .buildAndRegister();
    }

    private static void registerPrimitiveBlastFurnaceRecipes() {
        PBFRecipeBuilder.start().input(OrePrefix.ingot, Materials.Iron).output(OreDictUnifier.get(OrePrefix.ingot, Materials.Steel)).duration(1500).fuelAmount(2).buildAndRegister();
        PBFRecipeBuilder.start().input(OrePrefix.block, Materials.Iron).output(OreDictUnifier.get(OrePrefix.block, Materials.Steel)).duration(13500).fuelAmount(18).buildAndRegister();
        PBFRecipeBuilder.start().input(OrePrefix.ingot, Materials.WroughtIron).output(OreDictUnifier.get(OrePrefix.ingot, Materials.Steel)).duration(600).fuelAmount(2).buildAndRegister();
        PBFRecipeBuilder.start().input(OrePrefix.block, Materials.WroughtIron).output(OreDictUnifier.get(OrePrefix.block, Materials.Steel)).duration(5600).fuelAmount(18).buildAndRegister();
    }

    private static void registerCokeOvenRecipes() {
        CokeOvenRecipeBuilder.start().input(OrePrefix.log, Materials.Wood).output(OreDictUnifier.get(OrePrefix.gem, Materials.Charcoal)).fluidOutput(Materials.Creosote.getFluid(250)).duration(900).buildAndRegister();
        CokeOvenRecipeBuilder.start().input(OrePrefix.gem, Materials.Coal).output(OreDictUnifier.get(OrePrefix.gem, Materials.Coke)).fluidOutput(Materials.Creosote.getFluid(500)).duration(900).buildAndRegister();
        CokeOvenRecipeBuilder.start().input(OrePrefix.block, Materials.Coal).output(OreDictUnifier.get(OrePrefix.block, Materials.Coke)).fluidOutput(Materials.Creosote.getFluid(4500)).duration(8100).buildAndRegister();
    }

    private static void registerStoneBricksRecipes() {
        //decorative blocks: normal variant -> brick variant
        registerBrickRecipe(MetaBlocks.CONCRETE, ConcreteVariant.LIGHT_CONCRETE, ConcreteVariant.LIGHT_BRICKS);
        registerBrickRecipe(MetaBlocks.CONCRETE, ConcreteVariant.DARK_CONCRETE, ConcreteVariant.DARK_BRICKS);
        registerBrickRecipe(MetaBlocks.GRANITE, GraniteVariant.BLACK_GRANITE, GraniteVariant.BLACK_GRANITE_BRICKS);
        registerBrickRecipe(MetaBlocks.GRANITE, GraniteVariant.RED_GRANITE, GraniteVariant.RED_GRANITE_BRICKS);
        registerBrickRecipe(MetaBlocks.MINERAL, MineralVariant.MARBLE, MineralVariant.MARBLE_BRICKS);
        registerBrickRecipe(MetaBlocks.MINERAL, MineralVariant.BASALT, MineralVariant.BASALT_BRICKS);

        //decorative blocks: normal chiseling -> different chiseling
        registerChiselingRecipes(MetaBlocks.CONCRETE);
        registerChiselingRecipes(MetaBlocks.GRANITE);
        registerChiselingRecipes(MetaBlocks.MINERAL);
    }

    private static final MaterialStack[] solderingList = {
        new MaterialStack(Materials.Tin, 2L),
        new MaterialStack(Materials.SolderingAlloy, 1L)
    };


    private static void registerMixingCrystallizationRecipes() {
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
            .input(OrePrefix.dust, Materials.Clay, 1)
            .input(OrePrefix.dust, Materials.Stone, 3)
            .fluidInputs(Materials.Water.getFluid(500))
            .fluidOutputs(Materials.Concrete.getFluid(576))
            .duration(20).EUt(16)
            .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.CONCRETE.getItemVariant(ConcreteVariant.LIGHT_CONCRETE, ChiselingVariant.NORMAL))
            .fluidInputs(Materials.Water.getFluid(144))
            .outputs(MetaBlocks.CONCRETE.getItemVariant(ConcreteVariant.DARK_CONCRETE, ChiselingVariant.NORMAL))
            .duration(12).EUt(4)
            .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
            .duration(64).EUt(16)
            .fluidInputs(Materials.Water.getFluid(1000))
            .input("sand", 2)
            .input(OrePrefix.dust, Materials.Stone, 6)
            .input(OrePrefix.dust, Materials.Flint)
            .fluidOutputs(Materials.ConstructionFoam.getFluid(1000))
            .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
            .duration(1200).EUt(16)
            .input(OrePrefix.dust, Materials.Ruby, 9)
            .input(OrePrefix.dust, Materials.Redstone, 9)
            .outputs(ENERGIUM_DUST.getStackForm(9))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().inputs(ENERGIUM_DUST.getStackForm(9))
            .fluidInputs(Materials.Water.getFluid(1800))
            .outputs(ENERGY_CRYSTAL.getStackForm())
            .duration(2000).EUt(120)
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
            .inputs(ENERGIUM_DUST.getStackForm(9))
            .fluidInputs(ModHandler.getDistilledWater(1800))
            .outputs(ENERGY_CRYSTAL.getStackForm())
            .duration(1500).EUt(120)
            .buildAndRegister();
    }

    private static void registerOrganicRecyclingRecipes() {
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(800).EUt(3).input("treeSapling", 1).fluidInputs(Materials.Water.getFluid(100)).fluidOutputs(Materials.Biomass.getFluid(100)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Items.NETHER_WART)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        if (ConfigHolder.addFoodMethaneRecipes) {
            for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
                if (item instanceof ItemFood) {
                    ItemFood itemFood = (ItemFood) item;
                    Collection<ItemStack> subItems = ModHandler.getAllSubItems(new ItemStack(item, 1, GTValues.W));
                    for (ItemStack itemStack : subItems) {
                        int healAmount = itemFood.getHealAmount(itemStack);
                        float saturationModifier = itemFood.getSaturationModifier(itemStack);
                        if (healAmount > 0) {
                            FluidStack outputStack = Materials.Methane.getFluid(Math.round(9 * healAmount * (1.0f + saturationModifier)));
                            RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(itemStack).fluidOutputs(outputStack).buildAndRegister();
                        }
                    }
                }
            }
        }
    }

    private static void registerChemicalRecipes() {
        RecipeMaps.VACUUM_RECIPES.recipeBuilder().duration(50).fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Ice.getFluid(1000)).buildAndRegister();
        RecipeMaps.VACUUM_RECIPES.recipeBuilder().duration(400).fluidInputs(Materials.Air.getFluid(4000)).fluidOutputs(Materials.LiquidAir.getFluid(4000)).buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3).inputs(PLANT_BALL.getStackForm()).fluidInputs(Materials.Water.getFluid(180)).fluidOutputs(Materials.Biomass.getFluid(180)).buildAndRegister();
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().duration(150).EUt(2).fluidInputs(Materials.Biomass.getFluid(100)).fluidOutputs(Materials.FermentedBiomass.getFluid(100)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(192).inputs(new ItemStack(Items.SUGAR)).input(OrePrefix.dustTiny, Materials.Polyethylene, 1).fluidInputs(Materials.Toluene.getFluid(133)).outputs(GELLED_TOLUENE.getStackForm(2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).fluidInputs(Materials.HydrogenSulfide.getFluid(2000), ModHandler.getWater(2000)).fluidOutputs(Materials.SulfuricAcid.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).input(OrePrefix.dust, Materials.Saltpeter, 1).fluidInputs(Materials.Naphtha.getFluid(576)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Potassium, 1)).fluidOutputs(Materials.Polycaprolactam.getFluid(1296)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).input(OrePrefix.dust, Materials.Silicon, 1).fluidInputs(Materials.Epichlorhydrin.getFluid(144)).fluidOutputs(Materials.Silicone.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(Materials.Epichlorhydrin.getFluid(144), Materials.Naphtha.getFluid(3000), Materials.NitrogenDioxide.getFluid(1000)).fluidOutputs(Materials.Epoxid.getFluid(L * 2)).buildAndRegister();

        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(1500).EUt(30).fluidInputs(Materials.Water.getFluid(3000)).fluidOutputs(Materials.Hydrogen.getFluid(2000), Materials.Oxygen.getFluid(1000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(1500).EUt(30).fluidInputs(ModHandler.getDistilledWater(3000)).fluidOutputs(Materials.Hydrogen.getFluid(2000), Materials.Oxygen.getFluid(1000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.DYE, 3)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Calcium, 1)).duration(96).EUt(26).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().inputs(new ItemStack(Blocks.SAND, 8)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SiliconDioxide, 1)).duration(500).EUt(25).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().input(OrePrefix.dust, Materials.Graphite, 1).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon, 4)).duration(100).EUt(26).buildAndRegister();

        for (FluidMaterial material : new FluidMaterial[]{Materials.Water, Materials.DistilledWater}) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.NetherQuartz, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(material.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz, 3)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.CertusQuartz, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(material.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.CertusQuartz, 3)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.Quartzite, 3).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(material.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.Quartzite, 3)).buildAndRegister();
        }

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1000).input(OrePrefix.dust, Materials.Uraninite, 1).input(OrePrefix.dust, Materials.Aluminium, 1).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Uranium, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1000).input(OrePrefix.dust, Materials.Uraninite, 1).input(OrePrefix.dust, Materials.Magnesium, 1).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Uranium, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).input(OrePrefix.dust, Materials.Calcium, 1).input(OrePrefix.dust, Materials.Carbon, 1).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Calcite, 5)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1150).input(OrePrefix.dust, Materials.Sulfur, 1).fluidInputs(Materials.Water.getFluid(2000)).fluidOutputs(Materials.SulfuricAcid.getFluid(3000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).input(OrePrefix.crushedPurified, Materials.Pentlandite).fluidInputs(Materials.NitricAcid.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.PlatinumGroupSludge)).fluidOutputs(Materials.NickelSulfateSolution.getFluid(9000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).input(OrePrefix.dust, Materials.Quicklime).fluidInputs(Materials.CarbonDioxide.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Calcite)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).input(OrePrefix.dust, Materials.Calcite).notConsumable(new IntCircuitIngredient(1)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Quicklime)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(8000).input(OrePrefix.dust, Materials.Sulfur, 1).input(OrePrefix.dust, Materials.Sodium, 1).fluidInputs(Materials.Oxygen.getFluid(4000)).fluidOutputs(Materials.SodiumPersulfate.getFluid(6000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(2700).input(OrePrefix.dust, Materials.Carbon, 1).fluidInputs(Materials.Water.getFluid(2000), Materials.Nitrogen.getFluid(1000)).fluidOutputs(Materials.Glyceryl.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(16).input(OrePrefix.dust, Materials.RawRubber, 9).input(OrePrefix.dust, Materials.Sulfur, 1).fluidOutputs(Materials.Rubber.getFluid(1296)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.MELON, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.nugget, Materials.Gold, 8).outputs(new ItemStack(Items.SPECKLED_MELON)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.CARROT, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.nugget, Materials.Gold, 8).outputs(new ItemStack(Items.GOLDEN_CARROT)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.APPLE, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.ingot, Materials.Gold, 8).outputs(new ItemStack(Items.GOLDEN_APPLE)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).inputs(new ItemStack(Items.APPLE, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.block, Materials.Gold, 8).outputs(new ItemStack(Items.GOLDEN_APPLE, 1, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(24).inputs(GELLED_TOLUENE.getStackForm(4)).fluidInputs(Materials.SulfuricAcid.getFluid(250)).outputs(new ItemStack(Blocks.TNT)).buildAndRegister();
        RecipeMaps.WIREMILL_RECIPES.recipeBuilder().duration(80).EUt(48).input(OrePrefix.ingot, Materials.Polycaprolactam, 1).outputs(new ItemStack(Items.STRING, 32)).buildAndRegister();
    }

    private static final MaterialStack[][] alloySmelterList = {
        {new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 4L)},
        {new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 4L)},
        {new MaterialStack(Materials.Copper, 1), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Cupronickel, 2L)},
        {new MaterialStack(Materials.Copper, 1), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1)},
        {new MaterialStack(Materials.AnnealedCopper, 3L), new MaterialStack(Materials.Tin, 1), new MaterialStack(Materials.Bronze, 4L)},
        {new MaterialStack(Materials.AnnealedCopper, 3L), new MaterialStack(Materials.Zinc, 1), new MaterialStack(Materials.Brass, 4L)},
        {new MaterialStack(Materials.AnnealedCopper, 1), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Cupronickel, 2L)},
        {new MaterialStack(Materials.AnnealedCopper, 1), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1)},
        {new MaterialStack(Materials.Iron, 2L), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Invar, 3L)},
        {new MaterialStack(Materials.WroughtIron, 2L), new MaterialStack(Materials.Nickel, 1), new MaterialStack(Materials.Invar, 3L)},
        {new MaterialStack(Materials.Tin, 9L), new MaterialStack(Materials.Antimony, 1), new MaterialStack(Materials.SolderingAlloy, 10L)},
        {new MaterialStack(Materials.Lead, 4L), new MaterialStack(Materials.Antimony, 1), new MaterialStack(Materials.BatteryAlloy, 5L)},
        {new MaterialStack(Materials.Gold, 1), new MaterialStack(Materials.Silver, 1), new MaterialStack(Materials.Electrum, 2L)},
        {new MaterialStack(Materials.Magnesium, 1), new MaterialStack(Materials.Aluminium, 2L), new MaterialStack(Materials.Magnalium, 3L)}};

    private static void registerAlloyRecipes() {
        for (MaterialStack[] stack : alloySmelterList) {
            if (stack[0].material instanceof IngotMaterial) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .duration((int) stack[2].amount * 50).EUt(16)
                    .input(OrePrefix.ingot, stack[0].material, (int) stack[0].amount)
                    .input(OrePrefix.dust, stack[1].material, (int) stack[1].amount)
                    .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                    .buildAndRegister();
            }
            if (stack[1].material instanceof IngotMaterial) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .duration((int) stack[2].amount * 50).EUt(16)
                    .input(OrePrefix.dust, stack[0].material, (int) stack[0].amount)
                    .input(OrePrefix.ingot, stack[1].material, (int) stack[1].amount)
                    .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                    .buildAndRegister();
            }
            if (stack[0].material instanceof IngotMaterial && stack[1].material instanceof IngotMaterial) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .duration((int) stack[2].amount * 50).EUt(16)
                    .input(OrePrefix.ingot, stack[0].material, (int) stack[0].amount)
                    .input(OrePrefix.ingot, stack[1].material, (int) stack[1].amount)
                    .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                    .buildAndRegister();
            }
            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                .duration((int) stack[2].amount * 50).EUt(16)
                .input(OrePrefix.dust, stack[0].material, (int) stack[0].amount)
                .input(OrePrefix.dust, stack[1].material, (int) stack[1].amount)
                .outputs(OreDictUnifier.get(OrePrefix.ingot, stack[2].material, (int) stack[2].amount))
                .buildAndRegister();
        }

        for (OrePrefix prefix : Arrays.asList(OrePrefix.dust, OrePrefix.dustSmall, OrePrefix.dustTiny)) {
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (800 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Boron, 1).input(prefix, Materials.Glass, 7).outputs(OreDictUnifier.getDust(Materials.Glass, 8L * prefix.materialAmount)).buildAndRegister();
            // RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (600 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Nickel, 1).input(prefix, Materials.Zinc, 1).input(prefix, Materials.Iron, 4).outputs(OreDictUnifier.getDust(Materials.FerriteMixture, 6 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (100 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.EnderPearl, 1).input(prefix, Materials.Blaze, 1).outputs(OreDictUnifier.getDust(Materials.EnderEye, prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (200 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Gold, 1).input(prefix, Materials.Silver, 1).outputs(OreDictUnifier.getDust(Materials.Electrum, 2 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (300 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Iron, 2).input(prefix, Materials.Nickel, 1).outputs(OreDictUnifier.getDust(Materials.Invar, 3 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (900 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Iron, 4).input(prefix, Materials.Invar, 3).input(prefix, Materials.Manganese, 1).input(prefix, Materials.Chrome, 1).outputs(OreDictUnifier.getDust(Materials.StainlessSteel, 9 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (300 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Iron, 1).input(prefix, Materials.Aluminium, 1).input(prefix, Materials.Chrome, 1).outputs(OreDictUnifier.getDust(Materials.Kanthal, 3 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (600 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Copper, 3).input(prefix, Materials.Barium, 2).input(prefix, Materials.Yttrium, 1).outputs(OreDictUnifier.getDust(Materials.YttriumBariumCuprate, 6 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (400 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Copper, 3).input(prefix, Materials.Zinc, 1).outputs(OreDictUnifier.getDust(Materials.Brass, 4 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (400 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Copper, 3).input(prefix, Materials.Tin, 1).outputs(OreDictUnifier.getDust(Materials.Bronze, 4 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (200 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Copper, 1).input(prefix, Materials.Nickel, 1).outputs(OreDictUnifier.getDust(Materials.Cupronickel, 2 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (500 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Copper, 1).input(prefix, Materials.Gold, 4).outputs(OreDictUnifier.getDust(Materials.RoseGold, 5 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (500 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Copper, 1).input(prefix, Materials.Silver, 4).outputs(OreDictUnifier.getDust(Materials.SterlingSilver, 5 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (500 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Bismuth, 1).input(prefix, Materials.Brass, 4).outputs(OreDictUnifier.getDust(Materials.BismuthBronze, 5 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (900 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Cobalt, 5).input(prefix, Materials.Chrome, 2).input(prefix, Materials.Nickel, 1).input(prefix, Materials.Molybdenum, 1).outputs(OreDictUnifier.getDust(Materials.Ultimet, 9 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (400 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Saltpeter, 2).input(prefix, Materials.Sulfur, 1).input(prefix, Materials.Coal, 1).outputs(OreDictUnifier.getDust(Materials.Gunpowder, 4 * prefix.materialAmount)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (300 * prefix.materialAmount / M)).EUt(8).input(prefix, Materials.Saltpeter, 2).input(prefix, Materials.Sulfur, 1).input(prefix, Materials.Charcoal, 1).outputs(OreDictUnifier.getDust(Materials.Gunpowder, 3 * prefix.materialAmount)).buildAndRegister();
        }

        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().inputs(INGOT_MIXED_METAL.getStackForm()).outputs(ADVANCED_ALLOY_PLATE.getStackForm()).buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder().inputs(INGOT_IRIDIUM_ALLOY.getStackForm()).outputs(PLATE_IRIDIUM_ALLOY.getStackForm()).explosivesAmount(8).buildAndRegister();
    }

    private static void registerAssemblerRecipes() {
        for (IngotMaterial cableMaterial : new IngotMaterial[]{Materials.YttriumBariumCuprate, Materials.NiobiumTitanium, Materials.VanadiumGallium}) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.wireGtSingle, cableMaterial, 3)
                .input(OrePrefix.plate, Materials.TungstenSteel, 3)
                .inputs(ELECTRIC_PUMP_LV.getStackForm())
                .fluidInputs(Materials.Nitrogen.getFluid(2000), Materials.Technetium.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Superconductor, 3))
                .duration(20).EUt(512)
                .buildAndRegister();
        }

        for (Material cableMaterial : new Material[] {Materials.HSSG, Materials.NaquadahEnriched, Materials.NaquadahAlloy, Materials.NiobiumTitanium,
            Materials.VanadiumGallium, Materials.YttriumBariumCuprate, Materials.Superconductor}) {

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, cableMaterial))
                .circuitMeta(24)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, cableMaterial))
                .circuitMeta(24)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, cableMaterial))
                .circuitMeta(24)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, cableMaterial))
                .circuitMeta(24)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtHex, cableMaterial))
                .circuitMeta(24)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtHex, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, cableMaterial, 2))
                .circuitMeta(25)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L / 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, cableMaterial, 4))
                .circuitMeta(25)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, cableMaterial, 8))
                .circuitMeta(25)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L * 2))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();

            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, cableMaterial, 16))
                .circuitMeta(25)
                .fluidInputs(Materials.SiliconeRubber.getFluid(L * 4))
                .outputs(OreDictUnifier.get(OrePrefix.cableGtHex, cableMaterial))
                .duration(150).EUt(8)
                .buildAndRegister();
        }

        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(SPRAY_EMPTY.getStackForm())
                .input(getOrdictColorName(dyeColor), 1)
                .outputs(SPRAY_CAN_DYES[dyeColor.getMetadata()].getStackForm())
                .EUt(8).duration(200)
                .buildAndRegister();
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .duration(8192).EUt(8192)
            .inputs(ENERGY_LAPOTRONIC_ORB2.getStackForm(6))
            .input(OrePrefix.plate, Materials.Darmstadtium, 16)
            .inputs(CIRCUIT_ULTIMATE.getStackForm())
            .outputs(ENERGY_INFUSED_LAPOTRONIC_ORB.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .duration(4096).EUt(4096)
            .inputs(ENERGY_LAPOTRONIC_ORB.getStackForm(6))
            .input(OrePrefix.plate, Materials.Technetium, 16)
            .inputs(CIRCUIT_ULTIMATE.getStackForm())
            .outputs(ENERGY_LAPOTRONIC_ORB2.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .duration(2048).EUt(2048)
            .inputs(LAPOTRON_CRYSTAL.getStackForm(6))
            .input(OrePrefix.plate, Materials.AcrylonitrileButadieneStyrene, 16)
            .inputs(CIRCUIT_EV.getStackForm())
            .outputs(ENERGY_LAPOTRONIC_ORB.getStackForm())
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plate, Materials.Rubber, 2)
            .input(OrePrefix.plate, Materials.Aluminium, 2)
            .outputs(COVER_SHUTTER.getStackForm(4))
            .EUt(16).duration(200)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plate, Materials.Aluminium, 2)
            .input(OrePrefix.dust, Materials.Redstone)
            .outputs(COVER_MACHINE_CONTROLLER.getStackForm(1))
            .EUt(16).duration(200)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1800).EUt(30).input(OrePrefix.dust, Materials.EnderPearl, 1).input(OrePrefix.circuit, MarkerMaterials.Tier.Basic, 4).fluidInputs(Materials.Osmium.getFluid(L * 2)).outputs(FIELD_GENERATOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1800).EUt(120).input(OrePrefix.dust, Materials.EnderEye, 1).input(OrePrefix.circuit, MarkerMaterials.Tier.Good, 4).fluidInputs(Materials.Osmium.getFluid(576)).outputs(FIELD_GENERATOR_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1800).EUt(480).inputs(QUANTUM_EYE.getStackForm()).input(OrePrefix.circuit, MarkerMaterials.Tier.Advanced, 4).fluidInputs(Materials.Osmium.getFluid(1152)).outputs(FIELD_GENERATOR_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1800).EUt(1920).input(OrePrefix.dust, Materials.NetherStar, 1).input(OrePrefix.circuit, MarkerMaterials.Tier.Elite, 4).fluidInputs(Materials.Osmium.getFluid(2304)).outputs(FIELD_GENERATOR_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1800).EUt(7680).inputs(QUANTUM_STAR.getStackForm()).input(OrePrefix.circuit, Tier.Ultimate, 4).fluidInputs(Materials.Osmium.getFluid(4608)).outputs(FIELD_GENERATOR_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(480).EUt(240).input(OrePrefix.dust, Materials.Graphite, 8).input(OrePrefix.foil, Materials.Silicon, 1).fluidInputs(Materials.Glue.getFluid(250)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Graphene, 1)).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).inputs(new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.stick, Materials.Wood, 1).outputs(new ItemStack(Blocks.TORCH, 4)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.Gold, 2).outputs(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 1)).circuitMeta(2).duration(200).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.Iron, 2).outputs(new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 1)).circuitMeta(2).duration(200).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.Iron, 6).outputs(new ItemStack(Items.IRON_DOOR, 3)).circuitMeta(6).duration(600).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.Iron, 7).outputs(new ItemStack(Items.CAULDRON, 1)).circuitMeta(7).duration(700).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.stick, Materials.Iron, 3).outputs(new ItemStack(Blocks.IRON_BARS, 4)).circuitMeta(3).duration(300).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.WroughtIron, 2).outputs(new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 1)).circuitMeta(2).duration(200).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.WroughtIron, 6).outputs(new ItemStack(Items.IRON_DOOR, 3)).circuitMeta(6).duration(600).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.plate, Materials.WroughtIron, 7).outputs(new ItemStack(Items.CAULDRON, 1)).circuitMeta(7).duration(700).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.stick, Materials.WroughtIron, 3).outputs(new ItemStack(Blocks.IRON_BARS, 4)).circuitMeta(3).duration(300).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.stick, Materials.Wood, 3).outputs(new ItemStack(Blocks.OAK_FENCE, 1)).circuitMeta(3).duration(300).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.stick, Materials.Wood, 2).input(OrePrefix.ring, Materials.Iron, 2).outputs(new ItemStack(Blocks.TRIPWIRE_HOOK, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.stick, Materials.Wood, 2).input(OrePrefix.ring, Materials.WroughtIron, 2).outputs(new ItemStack(Blocks.TRIPWIRE_HOOK, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).inputs(new ItemStack(Items.STRING, 3, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.stick, Materials.Wood, 3).outputs(new ItemStack(Items.BOW, 1)).duration(400).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).inputs(new ItemStack(Blocks.STONE)).outputs(new ItemStack(Blocks.STONEBRICK, 1, 0)).circuitMeta(4).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).inputs(new ItemStack(Blocks.SANDSTONE)).outputs(new ItemStack(Blocks.SANDSTONE, 1, 2)).circuitMeta(1).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).inputs(new ItemStack(Blocks.SANDSTONE, 1, 1)).outputs(new ItemStack(Blocks.SANDSTONE, 1, 0)).circuitMeta(1).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).inputs(new ItemStack(Blocks.SANDSTONE, 1, 2)).outputs(new ItemStack(Blocks.SANDSTONE, 1, 0)).circuitMeta(1).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Steel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LV, 4)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Aluminium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MV, 4)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.StainlessSteel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.HV, 4)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Titanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.EV, 4)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.TungstenSteel, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.IV, 4)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Osmiridium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LuV, 4)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.NaquadahAlloy, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.UV, 4)).circuitMeta(8).duration(50).buildAndRegister();

        for (CoilType coilType : CoilType.values()) {
            if (coilType.getMaterial() != null) {
                ItemStack outputStack = MetaBlocks.WIRE_COIL.getItemVariant(coilType);
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(8)
                    .input(OrePrefix.wireGtDouble, coilType.getMaterial(), 8)
                    .outputs(outputStack)
                    .duration(50).EUt(16).buildAndRegister();
            }
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Invar, 6).input(OrePrefix.frameGt, Materials.Invar, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.INVAR_HEATPROOF, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Steel, 6).input(OrePrefix.frameGt, Materials.Steel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.STEEL_SOLID, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Aluminium, 6).input(OrePrefix.frameGt, Materials.Aluminium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.ALUMINIUM_FROSTPROOF, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.TungstenSteel, 6).input(OrePrefix.frameGt, Materials.TungstenSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.TUNGSTENSTEEL_ROBUST, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.StainlessSteel, 6).input(OrePrefix.frameGt, Materials.StainlessSteel, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.STAINLESS_CLEAN, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Materials.Titanium, 6).input(OrePrefix.frameGt, Materials.Titanium, 1).outputs(MetaBlocks.METAL_CASING.getItemVariant(MetalCasingType.TITANIUM_STABLE, 3)).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2048).inputs(MetaTileEntities.HULL[GTValues.EV].getStackForm(), NEUTRON_REFLECTOR.getStackForm(2)).input(OrePrefix.plate, Materials.TungstenSteel, 6).outputs(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(MultiblockCasingType.FUSION_CASING)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(64).input(OrePrefix.plate, Materials.Magnalium, 6).input(OrePrefix.frameGt, Materials.Steel, 1).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(64).inputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING)).input(OrePrefix.plate, Materials.StainlessSteel, 6).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STAINLESS_TURBINE_CASING, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(64).inputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING)).input(OrePrefix.plate, Materials.Titanium, 6).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.TITANIUM_TURBINE_CASING, 3)).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(64).inputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.STEEL_TURBINE_CASING)).input(OrePrefix.plate, Materials.TungstenSteel, 6).outputs(MetaBlocks.TURBINE_CASING.getItemVariant(TurbineCasingType.TUNGSTENSTEEL_TURBINE_CASING, 3)).duration(50).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LV)).input(OrePrefix.cableGtSingle, Materials.Tin, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[GTValues.LV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MV)).input(OrePrefix.cableGtSingle, Materials.Copper, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[GTValues.MV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.MV)).input(OrePrefix.cableGtSingle, Materials.AnnealedCopper, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[GTValues.MV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.HV)).input(OrePrefix.cableGtSingle, Materials.Gold, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[GTValues.HV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.EV)).input(OrePrefix.cableGtSingle, Materials.Aluminium, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[GTValues.EV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.IV)).input(OrePrefix.cableGtSingle, Materials.Tungsten, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2), Materials.Technetium.getFluid(L / 2)).outputs(MetaTileEntities.HULL[GTValues.IV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.LuV)).input(OrePrefix.cableGtSingle, Materials.VanadiumGallium, 2).fluidInputs(Materials.Polyethylene.getFluid(L * 2), Materials.Oganesson.getFluid(L / 2)).outputs(MetaTileEntities.HULL[GTValues.LuV].getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(MachineCasingType.UV)).input(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy, 2).fluidInputs(Materials.Polytetrafluoroethylene.getFluid(L * 2), Materials.Oganesson.getFluid(L / 2)).outputs(MetaTileEntities.HULL[GTValues.UV].getStackForm(2)).buildAndRegister();


        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(1).input(OrePrefix.cableGtSingle, Materials.Tin, 1).input(OrePrefix.plate, Materials.BatteryAlloy, 1).fluidInputs(Materials.Polyethylene.getFluid(144)).outputs(BATTERY_HULL_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(2).input(OrePrefix.cableGtSingle, Materials.Copper, 2).input(OrePrefix.plate, Materials.BatteryAlloy, 3).fluidInputs(Materials.Polyethylene.getFluid(432)).outputs(BATTERY_HULL_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(2).input(OrePrefix.cableGtSingle, Materials.AnnealedCopper, 2).input(OrePrefix.plate, Materials.BatteryAlloy, 3).fluidInputs(Materials.Polyethylene.getFluid(432)).outputs(BATTERY_HULL_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4).input(OrePrefix.cableGtSingle, Materials.Gold, 4).input(OrePrefix.plate, Materials.BatteryAlloy, 9).fluidInputs(Materials.Polyethylene.getFluid(1296)).outputs(BATTERY_HULL_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Items.STRING, 4, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SLIME_BALL, 1, OreDictionary.WILDCARD_VALUE)).outputs(new ItemStack(Items.LEAD, 2)).duration(200).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Blocks.CHEST, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.plate, Materials.Iron, 5).outputs(new ItemStack(Blocks.HOPPER)).duration(800).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Blocks.TRAPPED_CHEST, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.plate, Materials.Iron, 5).outputs(new ItemStack(Blocks.HOPPER)).duration(800).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Blocks.CHEST, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.plate, Materials.WroughtIron, 5).outputs(new ItemStack(Blocks.HOPPER)).duration(800).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Blocks.TRAPPED_CHEST, 1, OreDictionary.WILDCARD_VALUE)).input(OrePrefix.plate, Materials.WroughtIron, 5).outputs(new ItemStack(Blocks.HOPPER)).duration(800).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Items.BLAZE_POWDER)).input(OrePrefix.gem, Materials.EnderPearl, 1).outputs(new ItemStack(Items.ENDER_EYE, 1, 0)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).inputs(new ItemStack(Items.BLAZE_ROD)).input(OrePrefix.gem, Materials.EnderPearl, 6).outputs(new ItemStack(Items.ENDER_EYE, 6, 0)).duration(2500).buildAndRegister();
        //RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(2).input(OrePrefix.gear, Materials.CobaltBrass, 1).input(OrePrefix.dust, Materials.Diamond, 1).outputs(COMPONENT_SAW_BLADE_DIAMOND.getStackForm(1)).duration(1600).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.dust, Materials.Redstone, 4).input(OrePrefix.dust, Materials.Glowstone, 4).outputs(new ItemStack(Blocks.REDSTONE_LAMP, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.dust, Materials.Redstone, 1).input(OrePrefix.stick, Materials.Wood, 1).outputs(new ItemStack(Blocks.REDSTONE_TORCH, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.dust, Materials.Redstone, 1).input(OrePrefix.plate, Materials.Iron, 4).outputs(new ItemStack(Items.COMPASS, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.dust, Materials.Redstone, 1).input(OrePrefix.plate, Materials.WroughtIron, 4).outputs(new ItemStack(Items.COMPASS, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(4).input(OrePrefix.dust, Materials.Redstone, 1).input(OrePrefix.plate, Materials.Gold, 4).outputs(new ItemStack(Items.CLOCK, 1)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.stick, Materials.Wood, 1).input(OrePrefix.dust, Materials.Sulfur, 1).outputs(new ItemStack(Blocks.TORCH, 2)).duration(400).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(1).input(OrePrefix.stick, Materials.Wood, 1).input(OrePrefix.dust, Materials.Phosphorus, 1).outputs(new ItemStack(Blocks.TORCH, 6)).duration(400).buildAndRegister();

    }

    private static void registerBlastFurnaceRecipes() {
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration((int) Math.max(Materials.TungstenSteel.getAverageMass() / 80, 1) * Materials.TungstenSteel.blastFurnaceTemperature).EUt(480).input(OrePrefix.ingot, Materials.Tungsten, 1).input(OrePrefix.ingot, Materials.Steel, 1).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.TungstenSteel, 2), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 1)).blastFurnaceTemp(Materials.TungstenSteel.blastFurnaceTemperature).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration((int) Math.max(Materials.TungstenCarbide.getAverageMass() / 40, 1) * Materials.TungstenCarbide.blastFurnaceTemperature).EUt(480).input(OrePrefix.ingot, Materials.Tungsten, 1).input(OrePrefix.dust, Materials.Carbon, 1).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.TungstenCarbide, 1), OreDictUnifier.get(OrePrefix.dustSmall, Materials.Ash, 2)).blastFurnaceTemp(Materials.TungstenCarbide.blastFurnaceTemperature).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration((int) Math.max(Materials.VanadiumGallium.getAverageMass() / 40, 1) * Materials.VanadiumGallium.blastFurnaceTemperature).EUt(480).input(OrePrefix.ingot, Materials.Vanadium, 3).input(OrePrefix.ingot, Materials.Gallium, 1).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.VanadiumGallium, 4), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 2)).blastFurnaceTemp(Materials.VanadiumGallium.blastFurnaceTemperature).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration((int) Math.max(Materials.NiobiumTitanium.getAverageMass() / 80, 1) * Materials.NiobiumTitanium.blastFurnaceTemperature).EUt(480).input(OrePrefix.ingot, Materials.Niobium, 1).input(OrePrefix.ingot, Materials.Titanium, 1).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.NiobiumTitanium, 2), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 1)).blastFurnaceTemp(Materials.NiobiumTitanium.blastFurnaceTemperature).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration((int) Math.max(Materials.Nichrome.getAverageMass() / 32, 1) * Materials.Nichrome.blastFurnaceTemperature).EUt(480).input(OrePrefix.ingot, Materials.Nickel, 4).input(OrePrefix.ingot, Materials.Chrome, 1).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.Nichrome, 5), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 2)).blastFurnaceTemp(Materials.Nichrome.blastFurnaceTemperature).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(400).EUt(100).input(OrePrefix.dust, Materials.Ruby, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Aluminium, 5), OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, 4)).blastFurnaceTemp(1200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(320).EUt(100).input(OrePrefix.gem, Materials.Ruby, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Aluminium, 5), OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, 4)).blastFurnaceTemp(1200).buildAndRegister();
        //RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(400).EUt(100).input(OrePrefix.dust, Materials.GreenSapphire, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Aluminium, 5), OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, 4)).blastFurnaceTemp(1200).buildAndRegister();
        //RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(320).EUt(100).input(OrePrefix.gem, Materials.GreenSapphire, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Aluminium, 5), OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, 4)).blastFurnaceTemp(1200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(400).EUt(100).input(OrePrefix.dust, Materials.Sapphire, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Aluminium, 5), OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, 4)).blastFurnaceTemp(1200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(320).EUt(100).input(OrePrefix.gem, Materials.Sapphire, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Aluminium, 5), OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh, 4)).blastFurnaceTemp(1200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(800).EUt(500).input(OrePrefix.dust, Materials.Ilmenite, 1).input(OrePrefix.dust, Materials.Carbon, 1).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.WroughtIron, 4), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Rutile, 5)).blastFurnaceTemp(1700).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(800).EUt(480).input(OrePrefix.dust, Materials.Magnesium, 2).fluidInputs(Materials.TitaniumTetrachloride.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.Titanium, 1), OreDictUnifier.get(OrePrefix.dust, Materials.MagnesiumChloride, 2)).blastFurnaceTemp(Materials.Titanium.blastFurnaceTemperature + 200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).input(OrePrefix.dust, Materials.Galena, 1).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.Silver, 4), OreDictUnifier.get(OrePrefix.nugget, Materials.Lead, 4)).blastFurnaceTemp(1500).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(400).EUt(500).input(OrePrefix.dust, Materials.Magnetite, 1).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.nugget, Materials.WroughtIron, 4), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 1)).blastFurnaceTemp(1000).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).input(OrePrefix.ingot, Materials.Iron, 1).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Steel, 1), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 1)).blastFurnaceTemp(1000).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(100).EUt(120).input(OrePrefix.ingot, Materials.PigIron, 1).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Steel, 1), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 1)).blastFurnaceTemp(1000).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(100).EUt(120).input(OrePrefix.ingot, Materials.WroughtIron, 1).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Steel, 1), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh, 1)).blastFurnaceTemp(1000).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).input(OrePrefix.dust, Materials.Copper, 1).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.AnnealedCopper, 1)).blastFurnaceTemp(1200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).input(OrePrefix.ingot, Materials.Copper, 1).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.AnnealedCopper, 1)).blastFurnaceTemp(1200).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(1920).input(OrePrefix.ingot, Materials.Iridium, 3).input(OrePrefix.ingot, Materials.Osmium, 1).fluidInputs(Materials.Helium.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.Osmiridium, 4)).blastFurnaceTemp(2900).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(8192).input(OrePrefix.ingot, Materials.Naquadah, 1).input(OrePrefix.ingot, Materials.Osmiridium, 1).fluidInputs(Materials.LiquidAir.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.ingotHot, Materials.NaquadahAlloy, 2)).blastFurnaceTemp(Materials.NaquadahAlloy.blastFurnaceTemperature).buildAndRegister();
    }


    private static void registerDecompositionRecipes() {
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24)
            .input(OrePrefix.dustPure, Materials.Monazite)
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Monazite))
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium), 4000, 900)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Neodymium), 2000, 600)
            .buildAndRegister();

        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24)
            .input(OrePrefix.dustPure, Materials.Bastnasite)
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Bastnasite))
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium), 4000, 900)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Neodymium), 2000, 600)
            .buildAndRegister();


        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(1600).EUt(8)
            .fluidInputs(Materials.Air.getFluid(10000))
            .fluidOutputs(Materials.Nitrogen.getFluid(3900),
                Materials.Oxygen.getFluid(1000))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(RUBBER_DROP.getStackForm())
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 4))
            .duration(200).EUt(5)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(400).EUt(5)
            .inputs(RUBBER_DROP.getStackForm())
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 3))
            .chancedOutput(PLANT_BALL.getStackForm(), 1000, 850)
            .fluidOutputs(Materials.Glue.getFluid(100))
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(20)
            .inputs(MetaBlocks.LOG.getItem(LogVariant.RUBBER_WOOD))
            .chancedOutput(RUBBER_DROP.getStackForm(), 5000, 1200)
            .chancedOutput(PLANT_BALL.getStackForm(), 3750, 900)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon), 2500, 600)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Wood), 2500, 700)
            .fluidOutputs(Materials.Methane.getFluid(60))
            .buildAndRegister();

        if (Loader.isModLoaded("ic2")) {
            IC2Handler.registerRecipes();
        }

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(30)
            .inputs(new ItemStack(Blocks.DIRT, 1, GTValues.W))
            .chancedOutput(PLANT_BALL.getStackForm(), 1250, 700)
            .chancedOutput(new ItemStack(Blocks.SAND), 5000, 1200)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Clay, 1), 4000, 900)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(30)
            .inputs(new ItemStack(Blocks.GRASS))
            .chancedOutput(PLANT_BALL.getStackForm(), 3000, 1200)
            .chancedOutput(new ItemStack(Blocks.SAND), 5000, 1200)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Clay, 1), 5000, 900)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(650).EUt(30)
            .inputs(new ItemStack(Blocks.MYCELIUM))
            .chancedOutput(new ItemStack(Blocks.RED_MUSHROOM), 2500, 900)
            .chancedOutput(new ItemStack(Blocks.BROWN_MUSHROOM), 2500, 900)
            .chancedOutput(new ItemStack(Blocks.SAND), 5000, 1200)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Clay, 1), 5000, 900)
            .buildAndRegister();

        //electromagnetic separation recipes
        //RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.BrownLimonite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BrownLimonite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        //RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.YellowLimonite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.YellowLimonite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Nickel).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Nickel)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Pentlandite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Pentlandite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.BandedIron).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BandedIron)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Ilmenite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ilmenite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Pyrite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Pyrite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Tin).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Tin)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Chromite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Chromite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000, 600).buildAndRegister();
        //RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Monazite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Monazite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Neodymium), 2000, 600).buildAndRegister();
        //RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Bastnasite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Bastnasite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Neodymium), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.VanadiumMagnetite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.VanadiumMagnetite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gold), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold), 2000, 600).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Magnetite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Magnetite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gold), 4000, 900).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold), 2000, 600).buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(30)
            .input(OrePrefix.dust, Materials.Ash)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Quicklime, 2), 9500, 0)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Potash), 6400, 0)
            //.chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Magnesia), 6000, 0)
            //.chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.PhosphorousPentoxide), 500, 0)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.SodaAsh), 5000, 0)
            .buildAndRegister();


        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(50).EUt(30)
            .input(OrePrefix.dust, Materials.DarkAsh, 2)
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ash, 1),
                OreDictUnifier.get(OrePrefix.dust, Materials.Carbon, 1))
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(488).EUt(80)
            .input(OrePrefix.dust, Materials.Glowstone, 1)
            .outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Redstone, 2),
                OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gold, 2))
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(800).EUt(320).input(OrePrefix.dust, Materials.Uranium, 1).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Plutonium, 1), 200, 80).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Uranium235, 1), 2000, 350).buildAndRegister();
        //RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(1600).EUt(320).input(OrePrefix.dust, Materials.Plutonium, 1).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Uranium, 1), 3000, 450).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Plutonium241, 1), 2000, 300).buildAndRegister();
        //RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(3200).EUt(320).input(OrePrefix.dust, Materials.Naquadah, 1).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Naquadria, 1), 1000, 300).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.NaquadahEnriched, 1), 5000, 750).buildAndRegister();
        //RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(6400).EUt(640).input(OrePrefix.dust, Materials.NaquadahEnriched, 1).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Naquadah, 1), 3000, 400).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Naquad, 1), 2000, 450).buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(20)
            .input(OrePrefix.dust, Materials.Endstone, 1)
            .chancedOutput(new ItemStack(Blocks.SAND), 9000, 300)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Tungstate, 1), 1250, 450)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Platinum, 1), 625, 150)
            .fluidOutputs(Materials.Helium.getFluid(140))
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(20)
            .input(OrePrefix.dust, Materials.Netherrack, 1)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Redstone, 1), 5625, 850)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gold, 1), 625, 120)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Sulfur, 1), 9900, 100)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Coal, 1), 5625, 850)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(80)
            .inputs(new ItemStack(Blocks.SOUL_SAND))
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Saltpeter, 1), 8000, 480)
            .chancedOutput(new ItemStack(Blocks.SAND), 9000, 130)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Coal, 1), 2000, 340)
            .fluidOutputs(Materials.Oil.getFluid(100))
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(80).EUt(80)
            .fluidInputs(Materials.Lava.getFluid(100))
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Tantalum, 1), 250, 90)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold, 1), 250, 80)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Tin, 1), 1000, 270)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Tungstate, 1), 250, 70)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Copper, 1), 2000, 320)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Silver, 1), 250, 80)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(60).EUt(80)
            .fluidInputs(Materials.Pahoehoe.getFluid(100))
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Tantalum, 1), 750, 90)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold, 1), 750, 80)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Tin, 1), 3000, 270)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Tungstate, 1), 750, 70)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Copper, 1), 6000, 320)
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Silver, 1), 750, 80)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(64).EUt(20)
            .input(OrePrefix.dust, Materials.RareEarth, 1)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Cadmium, 1), 2500, 400)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium, 1), 2500, 400)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Caesium, 1), 2500, 400)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Cerium, 1), 2500, 400)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Yttrium, 1), 2500, 400)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Lanthanum, 1), 2500, 400)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(900).EUt(30)
            .input(OrePrefix.dust, Materials.PlatinumGroupSludge)
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SiliconDioxide),
                OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gold),
                OreDictUnifier.get(OrePrefix.dustTiny, Materials.Platinum))
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Palladium), 8000, 900)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Iridium), 6000, 850)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Osmium), 6000, 850)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(20).fluidInputs(Materials.Hydrogen.getFluid(160)).fluidOutputs(Materials.Deuterium.getFluid(40)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(80).fluidInputs(Materials.Deuterium.getFluid(160)).fluidOutputs(Materials.Tritium.getFluid(40)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(80).fluidInputs(Materials.Helium.getFluid(80)).fluidOutputs(Materials.Helium3.getFluid(5)).buildAndRegister();


        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).input("treeSapling", 8).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.WHEAT, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.POTATO, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.CARROT, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.CACTUS, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.REEDS, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.BROWN_MUSHROOM, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.RED_MUSHROOM, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.BEETROOT, 8)).outputs(PLANT_BALL.getStackForm()).buildAndRegister();

    }

    private static void registerCutterRecipes() {
        for (int i = 0; i < 16; i++) {
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(50).EUt(8).inputs(new ItemStack(Blocks.STAINED_GLASS, 3, i)).outputs(new ItemStack(Blocks.STAINED_GLASS_PANE, 8, i)).buildAndRegister();
        }
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(50).EUt(8).inputs(new ItemStack(Blocks.GLASS, 3)).outputs(new ItemStack(Blocks.GLASS_PANE, 8)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(25).EUt(8).inputs(new ItemStack(Blocks.STONE)).outputs(new ItemStack(Blocks.STONE_SLAB, 2)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(25).EUt(8).inputs(new ItemStack(Blocks.SANDSTONE)).outputs(new ItemStack(Blocks.STONE_SLAB, 2, 1)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(25).EUt(8).inputs(new ItemStack(Blocks.COBBLESTONE)).outputs(new ItemStack(Blocks.STONE_SLAB, 2, 3)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(25).EUt(8).inputs(new ItemStack(Blocks.BRICK_BLOCK)).outputs(new ItemStack(Blocks.STONE_SLAB, 2, 4)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(25).EUt(8).inputs(new ItemStack(Blocks.STONEBRICK)).outputs(new ItemStack(Blocks.STONE_SLAB, 2, 5)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(25).EUt(8).inputs(new ItemStack(Blocks.NETHER_BRICK)).outputs(new ItemStack(Blocks.STONE_SLAB, 2, 6)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(100).EUt(16).inputs(new ItemStack(Blocks.GLOWSTONE)).outputs(OreDictUnifier.get(OrePrefix.plate, Materials.Glowstone, 4)).buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(100).EUt(256).inputs(NANO_SUIT_JETPACK.getStackForm()).outputs(NANO_CHESTPLATE.getStackForm(), ELECTRIC_JETPACK.getStackForm()).fluidInputs(Materials.Lubricant.getFluid(L)).buildAndRegister();
    }

    private static void registerRecyclingRecipes() {
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
            .input(OrePrefix.stone.name(), 1)
            .outputs(new ItemStack(Blocks.COBBLESTONE, 1))
            .EUt(8).duration(200)
            .buildAndRegister();

        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
            .input(OrePrefix.cobblestone.name(), 1)
            .outputs(new ItemStack(Blocks.GRAVEL, 1))
            .EUt(8).duration(200)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.GRAVEL, 1))
            .outputs(new ItemStack(Blocks.SAND))
            .EUt(8).duration(200)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .input(OrePrefix.stone, Materials.Endstone)
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Endstone))
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Tungstate), 1200, 280)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .input(OrePrefix.stone, Materials.Netherrack)
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Netherrack, 1))
            .chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold, 1), 500, 120)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .input(OrePrefix.stone, Materials.Soapstone)
            .outputs(OreDictUnifier.get(OrePrefix.dustImpure, Materials.Talc, 1))
            .chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Chromite, 1), 1000, 280)
            .buildAndRegister();

    }

    private static void registerFluidRecipes() {

        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(32).EUt(4)
            .fluidInputs(Materials.Ice.getFluid(144))
            .circuitMeta(1)
            .fluidOutputs(Materials.Water.getFluid(144)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
            .inputs(new ItemStack(Items.MELON_SEEDS, 1, OreDictionary.WILDCARD_VALUE))
            .fluidOutputs(Materials.SeedOil.getFluid(3)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
            .inputs(new ItemStack(Items.PUMPKIN_SEEDS, 1, OreDictionary.WILDCARD_VALUE))
            .fluidOutputs(Materials.SeedOil.getFluid(6)).buildAndRegister();

        for (ItemStack stack : OreDictUnifier.getAllWithOreDictionaryName("seedCanola")) {
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2)
                .inputs(stack)
                .fluidOutputs(Materials.SeedOil.getFluid(50)).buildAndRegister();
        }

        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(BATTERY_HULL_LV.getStackForm()).fluidInputs(Materials.Mercury.getFluid(1000)).outputs(BATTERY_SU_LV_MERCURY.getChargedStack(Long.MAX_VALUE)).buildAndRegister();
        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(200).EUt(30).inputs(BATTERY_HULL_MV.getStackForm()).fluidInputs(Materials.Mercury.getFluid(4000)).outputs(BATTERY_SU_MV_MERCURY.getChargedStack(Long.MAX_VALUE)).buildAndRegister();
        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(400).EUt(30).inputs(BATTERY_HULL_HV.getStackForm()).fluidInputs(Materials.Mercury.getFluid(16000)).outputs(BATTERY_SU_HV_MERCURY.getChargedStack(Long.MAX_VALUE)).buildAndRegister();
        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(100).EUt(30).inputs(BATTERY_HULL_LV.getStackForm()).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(BATTERY_SU_LV_SULFURIC_ACID.getChargedStack(Long.MAX_VALUE)).buildAndRegister();
        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(200).EUt(30).inputs(BATTERY_HULL_MV.getStackForm()).fluidInputs(Materials.SulfuricAcid.getFluid(4000)).outputs(BATTERY_SU_MV_SULFURIC_ACID.getChargedStack(Long.MAX_VALUE)).buildAndRegister();
        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().duration(400).EUt(30).inputs(BATTERY_HULL_HV.getStackForm()).fluidInputs(Materials.SulfuricAcid.getFluid(16000)).outputs(BATTERY_SU_HV_SULFURIC_ACID.getChargedStack(Long.MAX_VALUE)).buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(600).EUt(28).input(OrePrefix.dust, Materials.Quartzite, 1).fluidOutputs(Materials.Glass.getFluid(72)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(4).inputs(new ItemStack(Items.COAL, 1, 1)).chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Ash, 1), 1000, 200).fluidOutputs(Materials.Creosote.getFluid(100)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(16).EUt(4).input(OrePrefix.dust, Materials.Wood, 1).chancedOutput(PLANT_BALL.getStackForm(), 200, 30).fluidOutputs(Materials.Creosote.getFluid(5)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(4).inputs(new ItemStack(Items.SNOWBALL)).fluidOutputs(Materials.Water.getFluid(250)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(4).inputs(new ItemStack(Blocks.SNOW)).fluidOutputs(Materials.Water.getFluid(1000)).buildAndRegister();

        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(128).EUt(4).notConsumable(SHAPE_MOLD_BALL.getStackForm()).fluidInputs(Materials.Water.getFluid(250)).outputs(new ItemStack(Items.SNOWBALL)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(128).EUt(4).notConsumable(SHAPE_MOLD_BALL.getStackForm()).fluidInputs(ModHandler.getDistilledWater(250)).outputs(new ItemStack(Items.SNOWBALL)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(512).EUt(4).notConsumable(SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(Materials.Water.getFluid(1000)).outputs(new ItemStack(Blocks.SNOW)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(512).EUt(4).notConsumable(SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(ModHandler.getDistilledWater(1000)).outputs(new ItemStack(Blocks.SNOW)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(1024).EUt(16).notConsumable(SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(Materials.Lava.getFluid(1000)).outputs(new ItemStack(Blocks.OBSIDIAN)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(12).EUt(4).notConsumable(SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(Materials.Glowstone.getFluid(576)).outputs(new ItemStack(Blocks.GLOWSTONE)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(12).EUt(4).notConsumable(SHAPE_MOLD_BLOCK.getStackForm()).fluidInputs(Materials.Glass.getFluid(144)).outputs(new ItemStack(Blocks.GLASS)).buildAndRegister();

        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(12).EUt(4).notConsumable(SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(Materials.Glass.getFluid(144)).outputs(OreDictUnifier.get(OrePrefix.plate, Materials.Glass, 1)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(128).EUt(16).notConsumable(SHAPE_MOLD_ANVIL.getStackForm()).fluidInputs(Materials.Iron.getFluid(31 * L)).outputs(new ItemStack(Blocks.ANVIL)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(128).EUt(16).notConsumable(SHAPE_MOLD_ANVIL.getStackForm()).fluidInputs(Materials.WroughtIron.getFluid(31 * L)).outputs(new ItemStack(Blocks.ANVIL)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(100).EUt(16).notConsumable(SHAPE_MOLD_BALL.getStackForm()).fluidInputs(Materials.Toluene.getFluid(100)).outputs(GELLED_TOLUENE.getStackForm()).buildAndRegister();

        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(12).EUt(4).notConsumable(SHAPE_MOLD_BLOCK).fluidInputs(ModHandler.getPahoehoe(1000)).outputs(MetaBlocks.MINERAL.getItemVariant(MineralVariant.BASALT, ChiselingVariant.NORMAL)).buildAndRegister();

        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(32).fluidInputs(Materials.Water.getFluid(6)).circuitMeta(1).fluidOutputs(Materials.Steam.getFluid(960)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(32).fluidInputs(ModHandler.getDistilledWater(6)).circuitMeta(1).fluidOutputs(Materials.Steam.getFluid(960)).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().input(OrePrefix.ingot, Materials.Iron, 31).notConsumable(SHAPE_MOLD_ANVIL).outputs(new ItemStack(Blocks.ANVIL)).duration(31 * 512).EUt(4 * 16).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().input(OrePrefix.ingot, Materials.WroughtIron, 31).notConsumable(SHAPE_MOLD_ANVIL).outputs(new ItemStack(Blocks.ANVIL)).duration(31 * 512).EUt(4 * 16).buildAndRegister();
    }

    private static void registerChemicalBathRecipes() {
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(200).EUt(4).input(OrePrefix.dust, Materials.Wood, 1).fluidInputs(Materials.Water.getFluid(100)).outputs(new ItemStack(Items.PAPER)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(100).EUt(4).input(OrePrefix.dust, Materials.Paper, 1).fluidInputs(Materials.Water.getFluid(100)).outputs(new ItemStack(Items.PAPER)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(new ItemStack(Items.REEDS, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.Water.getFluid(100)).outputs(new ItemStack(Items.PAPER)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(200).EUt(4).input(OrePrefix.dust, Materials.Wood, 1).fluidInputs(ModHandler.getDistilledWater(100)).outputs(new ItemStack(Items.PAPER)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(100).EUt(4).input(OrePrefix.dust, Materials.Paper, 1).fluidInputs(ModHandler.getDistilledWater(100)).outputs(new ItemStack(Items.PAPER)).buildAndRegister();

        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(new ItemStack(Items.REEDS, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(ModHandler.getDistilledWater(100)).outputs(new ItemStack(Items.PAPER)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.Chlorine.getFluid(50)).outputs(new ItemStack(Blocks.WOOL)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(new ItemStack(Blocks.CARPET, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.Chlorine.getFluid(25)).outputs(new ItemStack(Blocks.CARPET)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.Chlorine.getFluid(50)).outputs(new ItemStack(Blocks.HARDENED_CLAY)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(new ItemStack(Blocks.STAINED_GLASS, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.Chlorine.getFluid(50)).outputs(new ItemStack(Blocks.GLASS)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, OreDictionary.WILDCARD_VALUE)).fluidInputs(Materials.Chlorine.getFluid(20)).outputs(new ItemStack(Blocks.GLASS_PANE)).buildAndRegister();

        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(512).inputs(new ItemStack(Items.ENDER_EYE)).fluidInputs(Materials.Radon.getFluid(1000)).outputs(QUANTUM_EYE.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(400).EUt(2048).inputs(new ItemStack(Items.NETHER_STAR)).fluidInputs(Materials.Radon.getFluid(1000)).outputs(QUANTUM_STAR.getStackForm()).buildAndRegister();

    }

    private static <T extends Enum<T> & IStringSerializable> void registerBrickRecipe(StoneBlock<T> stoneBlock, T normalVariant, T brickVariant) {
        ModHandler.addShapedRecipe(stoneBlock.getRegistryName().getNamespace() + "_" + normalVariant + "_bricks",
            stoneBlock.getItemVariant(brickVariant, ChiselingVariant.NORMAL, 4),
            "XX", "XX", 'X',
            stoneBlock.getItemVariant(normalVariant, ChiselingVariant.NORMAL));
    }

    private static <T extends Enum<T> & IStringSerializable> void registerChiselingRecipes(StoneBlock<T> stoneBlock) {
        for (T variant : stoneBlock.getVariantValues()) {
            boolean isBricksVariant = variant.getName().endsWith("_bricks");
            if (!isBricksVariant) {
                ModHandler.addSmeltingRecipe(stoneBlock.getItemVariant(variant, ChiselingVariant.CRACKED), stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL));
                RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(12).EUt(4)
                    .inputs(stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL))
                    .outputs(stoneBlock.getItemVariant(variant, ChiselingVariant.CRACKED))
                    .buildAndRegister();
            } else {
                ModHandler.addSmeltingRecipe(stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL), stoneBlock.getItemVariant(variant, ChiselingVariant.CRACKED));
            }
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(12).EUt(4)
                .inputs(stoneBlock.getItemVariant(variant, !isBricksVariant ? ChiselingVariant.CRACKED : ChiselingVariant.NORMAL))
                .fluidInputs(Materials.Water.getFluid(144))
                .outputs(stoneBlock.getItemVariant(variant, ChiselingVariant.MOSSY))
                .buildAndRegister();
            ModHandler.addShapelessRecipe(stoneBlock.getRegistryName().getPath() + "_chiseling_" + variant,
                stoneBlock.getItemVariant(variant, ChiselingVariant.CHISELED),
                stoneBlock.getItemVariant(variant, ChiselingVariant.NORMAL));
        }
    }
}
