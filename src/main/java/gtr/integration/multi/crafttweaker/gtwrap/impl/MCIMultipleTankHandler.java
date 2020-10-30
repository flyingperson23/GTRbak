package gtr.integration.multi.crafttweaker.gtwrap.impl;

import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.liquid.MCLiquidStack;
import gtr.api.capability.IMultipleTankHandler;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IIFluidTank;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IIMultipleTankHandler;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;

public class MCIMultipleTankHandler implements IIMultipleTankHandler {

    private final IMultipleTankHandler inner;

    public MCIMultipleTankHandler(IMultipleTankHandler inner) {
        this.inner = inner;
    }

    @Override
    public IIFluidTank[] getFluidTanks() {
        return inner.getFluidTanks().stream().map(MCFluidTank::new).toArray(IIFluidTank[]::new);
    }

    @Override
    public int getTanks() {
        return inner.getTanks();
    }

    @Override
    public IIFluidTank getTankAt(int i) {
        return new MCFluidTank(inner.getTankAt(i));
    }

    @Override
    public int fill(ILiquidStack fluidStack, boolean doFill) {
        return inner.fill(CraftTweakerMC.getLiquidStack(fluidStack), doFill);
    }

    @Override
    @Nullable
    public ILiquidStack drain(ILiquidStack fluidStack, boolean doDrain) {
        return new MCLiquidStack(inner.drain(CraftTweakerMC.getLiquidStack(fluidStack), doDrain));
    }

    @Override
    @Nullable
    public ILiquidStack drain(int maxDrain, boolean doDrain) {
        return new MCLiquidStack(inner.drain(maxDrain, doDrain));
    }

    @Nonnull
    @Override
    public Iterator<IIFluidTank> iterator() {
        return inner.getFluidTanks().stream()
                .map(MCFluidTank::new)
                .map(IIFluidTank.class::cast)
                .iterator();
    }

}
