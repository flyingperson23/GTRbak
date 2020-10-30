package gtr.integration.multi.crafttweaker.gtwrap.impl;

import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.liquid.MCLiquidStack;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IIFluidTank;
import net.minecraftforge.fluids.IFluidTank;

import javax.annotation.Nullable;

public class MCFluidTank implements IIFluidTank {

    private final IFluidTank inner;

    public MCFluidTank(IFluidTank inner) {
        this.inner = inner;
    }

    @Override
    @Nullable
    public ILiquidStack getFluid() {
        return new MCLiquidStack(inner.getFluid());
    }

    @Override
    public int getFluidAmount() {
        return inner.getFluidAmount();
    }

    @Override
    public int getCapacity() {
        return inner.getCapacity();
    }

    @Override
    public int fill(ILiquidStack fluidStack, boolean doFill) {
        return inner.fill(CraftTweakerMC.getLiquidStack(fluidStack), doFill);
    }

    @Override
    @Nullable
    public ILiquidStack drain(int maxDrain, boolean doDrain) {
        return new MCLiquidStack(inner.drain(maxDrain, doDrain));
    }

}
