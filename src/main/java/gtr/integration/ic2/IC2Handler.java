package gtr.integration.ic2;

import gtr.api.GTValues;
import gtr.api.capability.IEnergyContainer;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.common.blocks.MetaBlocks;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.item.IC2Items;
import ic2.api.item.IElectricItem;
import ic2.core.Ic2Fluid;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import ic2.core.block.comp.Fluids;
import ic2.core.item.ItemBattery;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

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
            System.out.println(voltage);
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
    }

}
