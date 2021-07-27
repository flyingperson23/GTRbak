package gtr.integration.ic2;

import gtr.api.GTValues;
import gtr.api.capability.IEnergyContainer;
import gtr.api.recipes.MatchingMode;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.machines.*;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.common.items.MetaItems;
import gtr.common.items.behaviors.DataBehavior;
import gtr.integration.multi.helper.ReflectionHelper;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropSeed;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.item.IC2Items;
import ic2.api.item.IElectricItem;
import ic2.core.Ic2Fluid;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import ic2.core.crop.ItemCrop;
import ic2.core.crop.cropcard.CropBaseMetalCommon;
import ic2.core.item.ItemBattery;
import ic2.core.item.ItemCropSeed;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.LinkedHashMap;
import java.util.List;

import static gtr.common.items.MetaItems.PLANT_BALL;
import static gtr.common.items.MetaItems.RUBBER_DROP;

// This class is for all the shit that should only work if IC2 is enabled but I can't put it anywhere else
// because the import would crash it and I have no clue how to use @Optional
public class IC2Handler {
    public static boolean isAcceptable(TileEntity te) {
        if (te instanceof TileEntityBlock) {
            if (((TileEntityBlock) te).hasComponent(Energy.class)) {
                return true;
            }
        }
        if (!te.getBlockType().getTranslationKey().contains("ic2")) return false;
        return te instanceof IEnergySink || te instanceof IEnergySource;
    }

    public static int receiveEnergy(TileEntity te, long voltage, long amperes, EnumFacing directionFrom) {
        if (te instanceof IEnergySink) {
            IEnergySink sink = (IEnergySink) te;
            return (int) ((voltage * amperes) - sink.injectEnergy(directionFrom, voltage * amperes, voltage));
        } else if (te instanceof TileEntityBlock && ((TileEntityBlock) te).hasComponent(Energy.class)) {
            return (int) ((TileEntityBlock) te).getComponent(Energy.class).addEnergy(voltage*amperes);
        } else return 0;
    }

    public static boolean canItemReceiveEU(ItemStack s) {
        return s.getItem() instanceof IElectricItem;
    }

    public static long insertEnergyToItem(ItemStack s, IEnergyContainer container, int tier) {
        long requestedPower = (long) Math.min(ElectricItem.manager.getMaxCharge(s) - ElectricItem.manager.getCharge(s), GTValues.V[ElectricItem.manager.getTier(s)]);
        long powerToDeliver = Math.min(requestedPower, Math.min(container.getEnergyStored(), GTValues.V[tier]));
        return (long) ElectricItem.manager.charge(s, powerToDeliver, ElectricItem.manager.getTier(s), true, false);
    }

    public static void registerRubberOredict() {
        OreDictionary.registerOre("plateRubber", IC2Items.getItem("crafting", "rubber"));
    }

    public static boolean isBattery(ItemStack stack) {
        return stack.getItem() instanceof ItemBattery;
    }

    public static int getTier(ItemStack stack) {
        ItemBattery b = (ItemBattery) stack.getItem();
        return b.getTier(stack);
    }

    public static void registerRecipes() {
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(IC2Items.getItem("misc_resource", "resin"))
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 4))
            .duration(200).EUt(5)
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(400).EUt(5)
            .inputs(IC2Items.getItem("misc_resource", "resin"))
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 3))
            .chancedOutput(PLANT_BALL.getStackForm(), 1000, 850)
            .fluidOutputs(Materials.Glue.getFluid(100))
            .buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(20)
            .inputs(IC2Items.getItem("rubber_wood"))
            .chancedOutput(RUBBER_DROP.getStackForm(), 5000, 1200)
            .chancedOutput(PLANT_BALL.getStackForm(), 3750, 900)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon), 2500, 600)
            .chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Wood), 2500, 700)
            .fluidOutputs(Materials.Methane.getFluid(60))
            .buildAndRegister();

        // misc
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5)
            .inputs(IC2Items.getItem("terra_wart"))
            .fluidOutputs(Materials.Methane.getFluid(36))
            .buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(4)
            .inputs(IC2Items.getItem("crop_res", "milk_wart"))
            .chancedOutput(IC2Items.getItem("dust", "milk"), 10, 5)
            .fluidOutputs(Materials.Milk.getFluid(150))
            .buildAndRegister();

        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(4)
            .inputs(IC2Items.getItem("crop_res", "oil_berry"))
            .fluidOutputs(Materials.Oil.getFluid(100))
            .buildAndRegister();

        // bobsyeruncle
        ItemStack bob = IC2Items.getItem("crop_res", "bobs_yer_uncle_ranks_berry");
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(new ItemStack(bob.getItem(), 8, bob.getMetadata()))
            .outputs(IC2Items.getItem("crafting", "plant_ball"))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(2304).EUt(384)
            .inputs(new ItemStack(bob.getItem(), 16, bob.getMetadata()))
            .fluidInputs(Materials.UUM.getFluid(2))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Emerald))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(new ItemStack(bob.getItem(), 9, bob.getMetadata()), OreDictUnifier.get(OrePrefix.crushed, Materials.Emerald))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Beryllium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Emerald, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(new ItemStack(bob.getItem(), 9, bob.getMetadata()), OreDictUnifier.get(OrePrefix.crushed, Materials.Beryllium))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Beryllium, 4)).buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(bob)
            .outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Emerald))
            .buildAndRegister();

        // tine
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.TINE_TWIG.getStackForm(4))
            .outputs(IC2Items.getItem("crafting", "plant_ball"))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(2304).EUt(384)
            .inputs(MetaItems.TINE_TWIG.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(12))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Tin))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.TINE_TWIG.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Cassiterite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Tin.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Cassiterite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.TINE_TWIG.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Tin))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Iron.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Tin, 4)).buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.TINE_TWIG.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Tin))
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(400).EUt(2)
            .inputs(MetaItems.TINE_TWIG.getStackForm())
            .outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Wood, 2))
            .buildAndRegister();

        // argentia
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.ARGENTIA_LEAF.getStackForm(8))
            .outputs(IC2Items.getItem("crafting", "plant_ball"))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(13696).EUt(384)
            .inputs(MetaItems.ARGENTIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(11))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Silver))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.ARGENTIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Silver))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Lead.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Silver, 4)).buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.ARGENTIA_LEAF.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Silver))
            .buildAndRegister();

        // ferru
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(8))
            .outputs(IC2Items.getItem("crafting", "plant_ball"))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(7168).EUt(384)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(6))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Iron))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.BrownLimonite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.BrownLimonite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.BandedIron))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.BandedIron, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.VanadiumMagnetite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.VanadiumMagnetite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Pyrite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pyrite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.YellowLimonite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Nickel.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.YellowLimonite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Iron))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Nickel.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Iron, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.FERRU_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Magnetite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Iron.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Magnetite, 4)).buildAndRegister();

        // aurelia
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.AURELIA_LEAF.getStackForm(8))
            .outputs(IC2Items.getItem("crafting", "plant_ball"))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(25088).EUt(384)
            .inputs(MetaItems.AURELIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(20))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Gold))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.AURELIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Magnetite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Copper.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Gold, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.AURELIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Gold))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Copper.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Gold, 4)).buildAndRegister();

        // plumbilia
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.PLUMBILIA_LEAF.getStackForm(8))
            .outputs(IC2Items.getItem("crafting", "plant_ball"))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(26496).EUt(384)
            .inputs(MetaItems.PLUMBILIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(21))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Lead))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PLUMBILIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Lead))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Silver.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Lead, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PLUMBILIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Galena))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Galena, 4)).buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.PLUMBILIA_LEAF.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Lead))
            .buildAndRegister();

        // bauxia
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(3328).EUt(384)
            .inputs(MetaItems.BAUXIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(3))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Aluminium))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.BAUXIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Bauxite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Bauxite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.BAUXIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Aluminium))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Aluminium, 4)).buildAndRegister();

        // titania
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(6144).EUt(384)
            .inputs(MetaItems.TITANIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(5))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Titanium))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.TITANIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Bauxite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Bauxite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.TITANIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Titanium))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Titanium, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.TITANIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Ilmenite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Iron.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Ilmenite, 4)).buildAndRegister();

        // stargatium
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(12544).EUt(384)
            .inputs(MetaItems.STARGATIUM_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(10))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Naquadah))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.STARGATIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Naquadah))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.NaquadahEnriched.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Naquadah, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.STARGATIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.NaquadahEnriched))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Naquadah.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.NaquadahEnriched, 4)).buildAndRegister();

        // quantaria 1
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(24576).EUt(384)
            .inputs(MetaItems.QUANTARIA_LEAF_1.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(20))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Iridium))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.QUANTARIA_LEAF_1.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Iridium))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Platinum.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Iridium, 4)).buildAndRegister();

        // quantaria 2
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(24320).EUt(384)
            .inputs(MetaItems.QUANTARIA_LEAF_2.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(20))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Osmium))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.QUANTARIA_LEAF_2.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Osmium))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Iridium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Osmium, 4)).buildAndRegister();

        // reactoria
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(18048).EUt(384)
            .inputs(MetaItems.REACTORIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(15))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pitchblende))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.REACTORIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Pitchblende))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Thorium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pitchblende, 4)).buildAndRegister();

        // uranium
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(11520).EUt(384)
            .inputs(MetaItems.URANIUM_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(9))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Uraninite))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.URANIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Pitchblende))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Thorium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pitchblende, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.URANIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Uranium235))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Uranium235, 4)).buildAndRegister();

        // URANIUM INSTEAD OF 238
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.URANIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Uraninite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Uranium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Uraninite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.URANIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Uranium))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Lead.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Uranium, 4)).buildAndRegister();

        // thunder
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(29440).EUt(384)
            .inputs(MetaItems.THUNDER_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(23))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Thorium))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.THUNDER_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Thorium))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Uranium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Thorium, 4)).buildAndRegister();

        // nickelback
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(7424).EUt(384)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(6))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Nickel))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Wulfenite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Wulfenite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Pentlandite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Iron.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pentlandite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Nickel))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Cobalt.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Nickel, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Garnierite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Nickel.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Garnierite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Powellite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Powellite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.NICKELBACK_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Cobaltite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Cobalt.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Cobaltite, 4)).buildAndRegister();

        // galvania
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(8320).EUt(384)
            .inputs(MetaItems.GALVANIA_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(7))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Zinc))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.GALVANIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Sphalerite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Sphalerite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.GALVANIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Sulfur))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Sulfur, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.GALVANIA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Zinc))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Tin.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Zinc, 4)).buildAndRegister();

        // pyrolusite
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(7040).EUt(384)
            .inputs(MetaItems.PYROLUSIUM_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(6))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Manganese))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PYROLUSIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Manganese))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Chrome.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Manganese, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PYROLUSIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Spessartine))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Spessartine, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PYROLUSIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Grossular))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Grossular, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PYROLUSIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Pyrolusite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Manganese.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pyrolusite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PYROLUSIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Tantalite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Manganese.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Tantalite, 4)).buildAndRegister();

        // coppon
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(4)).outputs(new ItemStack(Blocks.WOOL, 1, 1))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(2)
            .inputs(MetaItems.COPPON_FIBER.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Copper))
            .buildAndRegister();

        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(8064).EUt(384)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(7))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Copper))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Pyrite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pyrite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Malachite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Copper.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Malachite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Stibnite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Antimony.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Stibnite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Chalcopyrite))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Chalcopyrite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Copper))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Cobalt.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Copper, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.COPPON_FIBER.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Tetrahedrite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Antimony.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Tetrahedrite, 4)).buildAndRegister();

        // scheelinium
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(5888).EUt(384)
            .inputs(MetaItems.SCHEELINIUM_LEAF.getStackForm(16))
            .fluidInputs(Materials.UUM.getFluid(5))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Scheelite))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.SCHEELINIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Scheelite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Manganese.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Scheelite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.SCHEELINIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Lithium))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Lithium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Lithium, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.SCHEELINIUM_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Tungstate))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Manganese.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Tungstate, 4)).buildAndRegister();

        // platina
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(24960).EUt(384)
            .inputs(MetaItems.PLATINA_LEAF.getStackForm(20))
            .fluidInputs(Materials.UUM.getFluid(5))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Platinum))
            .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PLATINA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Neodymium))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Neodymium, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PLATINA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Bastnasite))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Neodymium.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Bastnasite, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PLATINA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Platinum))
            .fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(Materials.Nickel.getFluid(144))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Platinum, 4)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(96).EUt(24)
            .inputs(MetaItems.PLATINA_LEAF.getStackForm(9), OreDictUnifier.get(OrePrefix.crushed, Materials.Palladium))
            .fluidInputs(Materials.Water.getFluid(1000))
            .outputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Palladium, 4)).buildAndRegister();

        CropCard genericCrop = Crops.instance.getCrops().toArray(new CropCard[0])[0];
        
        ItemStack circuit = MetaItems.INTEGRATED_CIRCUIT.getStackForm();
        circuit.setStackDisplayName("0 - Species, 1 - Growth, 2 - Gain, 3 - Resistance");
        ItemStack inputOrb = MetaItems.DATA_STICK.getStackForm();
        ItemStack outputOrb = inputOrb.copy();
        inputOrb.setStackDisplayName("Deletes Previous Data");
        ItemStack crop = ItemCropSeed.generateItemStackFromValues(genericCrop, 0, 0, 0, 4);
        crop.setStackDisplayName("Any input crop");
        outputOrb.setStackDisplayName("Recipe takes 16 EU/t and (80 * Crop Tier) ticks");
        RecipeMapCropGeneExtractor.JEI_MAP.recipeBuilder()
            .inputs(crop).inputs(inputOrb).notConsumable(circuit).outputs(outputOrb).duration(80).EUt(16).buildAndRegister();

        ItemStack species = MetaItems.DATA_STICK.getStackForm();
        ItemStack growth = species.copy();
        ItemStack gain = species.copy();
        ItemStack resistance = species.copy();

        NBTTagCompound speciesCompound = new NBTTagCompound();
        NBTTagCompound growthCompound = new NBTTagCompound();
        NBTTagCompound gainCompound = new NBTTagCompound();
        NBTTagCompound resistanceCompound = new NBTTagCompound();

        speciesCompound.setShort("data", (short) (DataBehavior.DataType.CROP_SPECIES.ordinal() & 0b111));
        growthCompound.setShort("data", (short) (DataBehavior.DataType.CROP_GROWTH.ordinal() & 0b111));
        gainCompound.setShort("data", (short) (DataBehavior.DataType.CROP_GAIN.ordinal() & 0b111));
        resistanceCompound.setShort("data", (short) (DataBehavior.DataType.CROP_RESISTANCE.ordinal() & 0b111));

        species.setTagCompound(speciesCompound);
        growth.setTagCompound(growthCompound);
        gain.setTagCompound(gainCompound);
        resistance.setTagCompound(resistanceCompound);

        species.setStackDisplayName("Any Crop Species Scan");
        growth.setStackDisplayName("Any Crop Growth Scan");
        gain.setStackDisplayName("Any Crop Gain Scan");
        resistance.setStackDisplayName("Any Crop Resistance Scan");

        ItemStack output = ItemCropSeed.generateItemStackFromValues(genericCrop, 0, 0, 0, 4);
        output.setStackDisplayName("Needs (500 * Crop Tier) mB UU-Matter and takes (160 * Crop Tier) seconds");

        RecipeMapCropSynthesizer.JEI_MAP.recipeBuilder().duration(160).EUt(16).notConsumable(species).notConsumable(growth).notConsumable(gain).notConsumable(resistance)
            .fluidInputs(Materials.UUM.getFluid(500)).outputs(output).buildAndRegister();

        ItemStack input = ItemCropSeed.generateItemStackFromValues(genericCrop, 0, 0, 0, 4);
        ItemStack output2 = input.copy();
        input.setStackDisplayName("Any Crop Seed Bag");
        output2.setStackDisplayName("Copy of Input, Uses (1000 * Crop Tier) mB UU-Matter and takes (80 * Crop Tier) ticks");
        RecipeMapCropReplicator.JEI_MAP.recipeBuilder().duration(80).EUt(16).notConsumable(input).outputs(output2)
            .fluidInputs(Materials.UUM.getFluid(1000)).buildAndRegister();

        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().duration(20).EUt(16)
            .fluidInputs(Materials.Concrete.getFluid(1000))
            .fluidOutputs(Materials.ConstructionFoam.getFluid(1000))
            .buildAndRegister();

        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().duration(20).EUt(16)
            .fluidInputs(Materials.ConstructionFoam.getFluid(1000))
            .fluidOutputs(Materials.Concrete.getFluid(1000))
            .buildAndRegister();

        ItemStack s = ItemCropSeed.generateItemStackFromValues(genericCrop, 0, 0, 0, 0);
        ItemStack s2 = ItemCropSeed.generateItemStackFromValues(genericCrop, 0, 0, 0, 4);
        s.setStackDisplayName("Any Crop");
        s2.setStackDisplayName("Scanned Crop");
        RecipeMapScanner.JEI_MAP.recipeBuilder().duration(160).EUt(8).inputs(s).outputs(s2).buildAndRegister();
    }

    @SubscribeEvent
    public void onCropRegister(Crops.CropRegisterEvent event) {
        IC2CropLoader.run();
        for (GTBaseCrop crop : GTBaseCrop.cropList) {
            event.register(crop);
            if (crop.registerBaseSeed != null) crop.registerBaseSeed.run();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ICropSeed) {
            CropCard c = ((ICropSeed) stack.getItem()).getCropFromStack(stack);
            if (c instanceof GTBaseCrop) {
                GTBaseCrop crop = (GTBaseCrop) c;
                if (crop.requiredMatBelow != null) {
                        event.getToolTip().add(TextFormatting.GRAY+"Requires block of "+crop.requiredMatBelow.toString()+" to reach max size");
                }
            }
            else if (c instanceof CropBaseMetalCommon) {
                CropBaseMetalCommon crop = (CropBaseMetalCommon) c;
                Object[] cropRootsRequirement = ReflectionHelper.getPrivate(CropBaseMetalCommon.class, "cropRootsRequirement", crop);
                if (cropRootsRequirement != null) {
                    for (Object o : cropRootsRequirement) {
                        if (o instanceof String) {
                            event.getToolTip().add(TextFormatting.GRAY + "Requires " + o + " below to reach max size");
                        } else if (o instanceof Block) {
                            event.getToolTip().add(TextFormatting.GRAY + "Requires " + ((Block) o).getLocalizedName() + " below to reach max size");
                        }
                    }
                }
            }
        }
    }

    public static void mapCrops() {
        CropCard[] crops = Crops.instance.getCrops().toArray(new CropCard[0]);
        for (int i = 0; i < crops.length; i++) {
            crops_intToCard.put(i, crops[i]);
            crops_cardToInt.put(crops[i], i);
        }
    }

    public static LinkedHashMap<Integer, CropCard> crops_intToCard = new LinkedHashMap<>();
    public static LinkedHashMap<CropCard, Integer> crops_cardToInt = new LinkedHashMap<>();

    public static short getSpecies(ItemStack stack) {
        return crops_cardToInt.get(((ICropSeed) stack.getItem()).getCropFromStack(stack)).shortValue();
    }

    public static short getGrowth(ItemStack stack) {
        return (short) ((ICropSeed) stack.getItem()).getGrowthFromStack(stack);
    }

    public static short getGain(ItemStack stack) {
        return (short) ((ICropSeed) stack.getItem()).getGainFromStack(stack);
    }

    public static short getResistance(ItemStack stack) {
        return (short) ((ICropSeed) stack.getItem()).getResistanceFromStack(stack);
    }

    public static String getSpeciesName(int id) {
        return crops_intToCard.get(id).getId();
    }

    public static Fluid getUU() {
        return FluidRegistry.getFluid("ic2uu_matter");
    }

    public static Fluid getPahoehoe() {
        return FluidRegistry.getFluid("ic2pahoehoe_lava");
    }

    public static Fluid getFoam() {
        return FluidRegistry.getFluid("ic2construction_foam");
    }

    public static Recipe getRecipe(RecipeMapScanner e, long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        for (ItemStack i : inputs) {
            if (i.getItem() instanceof ICropSeed) {
                ICropSeed seed = (ICropSeed) i.getItem();
                ItemStack s = ItemCropSeed.generateItemStackFromValues(seed.getCropFromStack(i), seed.getGrowthFromStack(i), seed.getGainFromStack(i), seed.getResistanceFromStack(i), 4);
                return e.recipeBuilder().duration(160).EUt(8).inputs(i).outputs(s).build().getResult();
            }
        }
        return null;
    }

    public static ItemStack getScrap() {
        return IC2Items.getItem("crafting", "scrap");
    }

    public static ItemStack getScrapBox() {
        return IC2Items.getItem("crafting", "scrap_box");
    }

}
