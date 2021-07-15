package gtr.integration.ic2;

import gtr.api.capability.IElectricItem;
import ic2.api.item.ElectricItem;
import ic2.core.item.ItemBattery;
import net.minecraft.item.ItemStack;

import java.util.function.BiConsumer;

public class IC2ElectricItem implements IElectricItem {

    private ItemBattery battery;
    private ItemStack stack;

    public IC2ElectricItem(ItemStack stack) {
        this.battery = (ItemBattery) stack.getItem();
        this.stack = stack;
    }

    @Override
    public boolean canProvideChargeExternally() {
        return true;
    }

    @Override
    public void addChargeListener(BiConsumer<ItemStack, Long> chargeListener) {

    }

    @Override
    public long charge(long amount, int chargerTier, boolean ignoreTransferLimit, boolean simulate) {
        return (long) ElectricItem.manager.charge(stack, amount, chargerTier+1, ignoreTransferLimit, simulate);
    }

    @Override
    public long discharge(long amount, int dischargerTier, boolean ignoreTransferLimit, boolean externally, boolean simulate) {
        return (long) ElectricItem.manager.discharge(stack, amount, dischargerTier+1, ignoreTransferLimit, externally, simulate);
    }

    @Override
    public long getTransferLimit() {
        return (long) battery.getTransferLimit(stack);
    }

    @Override
    public long getMaxCharge() {
        return (long) ElectricItem.manager.getMaxCharge(stack);
    }

    @Override
    public long getCharge() {
        return (long) ElectricItem.manager.getCharge(stack);
    }

    @Override
    public int getTier() {
        return ElectricItem.manager.getTier(stack)-1;
    }
}
