package gtr.common.metatileentities.multi.electric.generator;

import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.IMultipleTankHandler;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.recipes.machines.FuelRecipeMap;
import gtr.api.recipes.recipes.FuelRecipe;
import gtr.api.unification.material.Materials;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

public class DieselEngineWorkableHandler extends FuelRecipeLogic {

    private final int maxCycleLength = 20;
    private int currentCycle = 0;
    private boolean isUsingOxygen = false;

    public DieselEngineWorkableHandler(MetaTileEntity metaTileEntity, FuelRecipeMap recipeMap,
                                       Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity, recipeMap, energyContainer, fluidTank, maxVoltage);
    }

    public FluidStack getFuelStack() {
        if (previousRecipe == null)
            return null;
        FluidStack fuelStack = previousRecipe.getRecipeFluid();
        return fluidTank.get().drain(new FluidStack(fuelStack.getFluid(), Integer.MAX_VALUE), false);
    }

    @Override
    protected boolean checkRecipe(FuelRecipe recipe) {
        FluidStack lubricantStack = Materials.Lubricant.getFluid(2);
        FluidStack drainStack = fluidTank.get().drain(lubricantStack, false);
        return (drainStack != null && drainStack.amount >= 2) || currentCycle < maxCycleLength;
    }

    @Override
    protected int calculateFuelAmount(FuelRecipe currentRecipe) {
        FluidStack oxygenStack = Materials.Oxygen.getFluid(2);
        FluidStack drainOxygenStack = fluidTank.get().drain(oxygenStack, false);
        this.isUsingOxygen = drainOxygenStack != null && drainOxygenStack.amount >= 2;
        return super.calculateFuelAmount(currentRecipe) * (isUsingOxygen ? 2 : 1);
    }

    @Override
    protected long startRecipe(FuelRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        if (this.currentCycle >= maxCycleLength) {
            FluidStack lubricantStack = Materials.Lubricant.getFluid(2);
            fluidTank.get().drain(lubricantStack, true);
            this.currentCycle = 0;
        } else this.currentCycle++;
        if (isUsingOxygen) {
            FluidStack oxygenStack = Materials.Oxygen.getFluid(2);
            fluidTank.get().drain(oxygenStack, true);
        }
        return maxVoltage * (isUsingOxygen ? 3 : 1);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        compound.setInteger("Cycle", currentCycle);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        this.currentCycle = compound.getInteger("Cycle");
    }
}
