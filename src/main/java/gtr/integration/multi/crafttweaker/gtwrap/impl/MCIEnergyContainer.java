package gtr.integration.multi.crafttweaker.gtwrap.impl;

import crafttweaker.api.world.IFacing;
import gtr.api.capability.IEnergyContainer;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IIEnergyContainer;
import net.minecraft.util.EnumFacing;

public class MCIEnergyContainer implements IIEnergyContainer {

    private final IEnergyContainer inner;

    public MCIEnergyContainer(IEnergyContainer inner) {
        this.inner = inner;
    }

    @Override
    public IEnergyContainer getInternal() {
        return inner;
    }

    @Override
    public long acceptEnergyFromNetwork(IFacing enumFacing, long voltage, long amperage) {
        return inner.acceptEnergyFromNetwork((EnumFacing) enumFacing.getInternal(), voltage, amperage);
    }

    @Override
    public boolean inputsEnergy(IFacing enumFacing) {
        return inner.inputsEnergy((EnumFacing) enumFacing.getInternal());
    }

    @Override
    public boolean outputsEnergy(IFacing side) {
        return inner.outputsEnergy((EnumFacing) side.getInternal());
    }

    @Override
    public long changeEnergy(long differenceAmount) {
        return inner.changeEnergy(differenceAmount);
    }

    @Override
    public long addEnergy(long energyToAdd) {
        return inner.addEnergy(energyToAdd);
    }

    @Override
    public long removeEnergy(long energyToRemove) {
        return inner.removeEnergy(energyToRemove);
    }

    @Override
    public long getEnergyCanBeInserted() {
        return inner.getEnergyCanBeInserted();
    }

    @Override
    public long getEnergyStored() {
        return inner.getEnergyStored();
    }

    @Override
    public long getEnergyCapacity() {
        return inner.getEnergyCapacity();
    }

    @Override
    public long getOutputAmperage() {
        return inner.getOutputAmperage();
    }

    @Override
    public long getOutputVoltage() {
        return inner.getOutputVoltage();
    }

    @Override
    public long getInputAmperage() {
        return inner.getInputAmperage();
    }

    @Override
    public long getInputVoltage() {
        return inner.getInputVoltage();
    }

}
