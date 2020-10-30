package gtr.api.capability.impl;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import gtr.api.metatileentity.MTETrait;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.BitSet;
import java.util.Objects;
import java.util.stream.IntStream;

public class EnergyContainerBatteryHolder extends MTETrait implements IEnergyContainer {

    private final BitSet batterySlotsUsedThisTick = new BitSet();

    public EnergyContainerBatteryHolder(MetaTileEntity metaTileEntity) {
        super(metaTileEntity);
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
        long initialAmperage = amperage;
        if (side == null || inputsEnergy(side)) {
            IItemHandlerModifiable inventory = getInventory();
            for (int i = 0; i < inventory.getSlots(); i++) {
                if (batterySlotsUsedThisTick.get(i)) continue;
                ItemStack batteryStack = inventory.getStackInSlot(i);
                IElectricItem electricItem = getBatteryContainer(batteryStack);
                if (electricItem == null) continue;
                if (charge(electricItem, voltage, electricItem.getTier(), true)) {
                    charge(electricItem, voltage, electricItem.getTier(), false);
                    inventory.setStackInSlot(i, batteryStack);
                    this.batterySlotsUsedThisTick.set(i);
                    if (--amperage == 0) break;
                }
            }
        }
        long amperageUsed = initialAmperage - amperage;
        if (amperageUsed > 0L) {
            notifyEnergyListener(false);
        }
        return amperageUsed;
    }

    private static boolean charge(IElectricItem electricItem, long voltage, int tier, boolean simulate) {
        return electricItem.charge(voltage, tier, true, simulate) == voltage;
    }

    private static long chargeItem(IElectricItem electricItem, long amount, int tier, boolean discharge) {
        if (!discharge) {
            return electricItem.charge(amount, tier, false, false);
        } else {
            return electricItem.discharge(amount, tier, true, true, false);
        }
    }

    @Override
    public void update() {
        if (!metaTileEntity.getWorld().isRemote) {
            this.batterySlotsUsedThisTick.clear();
            EnumFacing outFacing = metaTileEntity.getFrontFacing();
            TileEntity tileEntity = metaTileEntity.getWorld().getTileEntity(metaTileEntity.getPos().offset(outFacing));
            if (tileEntity == null) {
                return;
            }
            IEnergyContainer energyContainer = tileEntity.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, outFacing.getOpposite());
            if (energyContainer == null) {
                return;
            }

            IItemHandlerModifiable inventory = getInventory();
            long voltage = getOutputVoltage();
            long maxAmperage = 0L;
            TIntList slotsList = new TIntArrayList();
            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack batteryStack = inventory.getStackInSlot(i);
                IElectricItem electricItem = getBatteryContainer(batteryStack);
                if (electricItem == null) continue;
                if (electricItem.discharge(voltage, electricItem.getTier(), true, true, true) == voltage) {
                    slotsList.add(i);
                    maxAmperage++;
                }
            }
            if (maxAmperage == 0) return;
            long amperageUsed = energyContainer.acceptEnergyFromNetwork(outFacing.getOpposite(), voltage, maxAmperage);
            if (amperageUsed == 0) return;
            for (int i : slotsList.toArray()) {
                ItemStack batteryStack = inventory.getStackInSlot(i);
                IElectricItem electricItem = getBatteryContainer(batteryStack);
                if (electricItem == null) continue;
                electricItem.discharge(voltage, electricItem.getTier(), true, true, false);
                inventory.setStackInSlot(i, batteryStack);
                if (--amperageUsed == 0) break;
            }
            notifyEnergyListener(false);
        }
    }

    @Override
    public long getEnergyCapacity() {
        return IntStream.range(0, getInventory().getSlots()).mapToObj(getInventory()::getStackInSlot).map(this::getBatteryContainer).filter(Objects::nonNull).mapToLong(IElectricItem::getMaxCharge).sum();
    }

    @Override
    public long getEnergyStored() {
        return IntStream.range(0, getInventory().getSlots()).mapToObj(getInventory()::getStackInSlot).map(this::getBatteryContainer).filter(Objects::nonNull).mapToLong(IElectricItem::getCharge).sum();
    }

    @Override
    public long getInputAmperage() {
        return IntStream.range(0, getInventory().getSlots()).mapToObj(getInventory()::getStackInSlot).map(this::getBatteryContainer).filter(Objects::nonNull).count();
    }

    public IElectricItem getBatteryContainer(ItemStack itemStack) {
        IElectricItem electricItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem != null && electricItem.canProvideChargeExternally())
            return electricItem;
        return null;
    }

    @Override
    public long changeEnergy(long energyToAdd) {
        boolean isDischarge = energyToAdd < 0L;
        energyToAdd = Math.abs(energyToAdd);
        long initialEnergyToAdd = energyToAdd;
        IItemHandlerModifiable inventory = getInventory();
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack batteryStack = inventory.getStackInSlot(i);
            IElectricItem electricItem = getBatteryContainer(batteryStack);
            if (electricItem == null) continue;
            long charged = chargeItem(electricItem, energyToAdd, electricItem.getTier(), isDischarge);
            energyToAdd -= charged;
            if(energyToAdd == 0L) break;
        }
        long energyAdded = initialEnergyToAdd - energyToAdd;
        if(energyAdded > 0L) {
            notifyEnergyListener(false);
        }
        return energyAdded;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        super.deserializeNBT(compound);
        notifyEnergyListener(true);
    }

    public void notifyEnergyListener(boolean isInitialChange) {
        if (metaTileEntity instanceof EnergyContainerHandler.IEnergyChangeListener) {
            ((EnergyContainerHandler.IEnergyChangeListener) metaTileEntity).onEnergyChanged(this, isInitialChange);
        }
    }

    @Override
    public long getInputVoltage() {
        return 0;
    }

    @Override
    public long getOutputVoltage() {
        return 0;
    }

    @Override
    public long getOutputAmperage() {
        return getInputAmperage();
    }

    @Override
    public boolean inputsEnergy(EnumFacing side) {
        return false;
    }

    @Override
    public boolean outputsEnergy(EnumFacing side) {
        return false;
    }

    @Override
    public String getName() {
        return "BatteryHolderEnergyContainer";
    }

    @Override
    public int getNetworkID() {
        return TraitNetworkIds.TRAIT_ID_ENERGY_CONTAINER;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if(capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
            return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this);
        }
        return null;
    }

    protected IItemHandlerModifiable getInventory() {
        return metaTileEntity.getImportItems();
    }
}
