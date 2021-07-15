package gtr.common.asm;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import gtr.api.items.metaitem.MetaItem;
import ic2.api.item.ElectricItem;
import ic2.core.block.comp.Energy;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.item.ItemBatteryChargeHotbar;
import ic2.core.util.StackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

public class IC2Hooks {
    public static void emitGTEU(TileEntityBaseGenerator generator) {
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));

            if (te != null) {
                IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                if (container != null) {
                    int voltage = (int) Math.pow(2, 3+2*e.getSourceTier());
                    int packets = Math.min(e.getPacketOutput(), (int) e.getEnergy() / voltage);
                    if (packets > 0) {
                        long ampsUsed = container.acceptEnergyFromNetwork(facing.getOpposite(), voltage, packets);
                        for (int i = 0; i < ampsUsed; i++) {
                            e.useEnergy(voltage);
                        }
                    }
                }
            }
        }
    }

    public static void emitGTEU2(TileEntityElectricBlock generator) {
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));

            if (te != null) {
                IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                if (container != null) {
                    int voltage = (int) Math.pow(2, 3+2*e.getSourceTier());
                    int packets = Math.min(e.getPacketOutput(), (int) e.getEnergy() / voltage);
                    if (packets > 0) {
                        long ampsUsed = container.acceptEnergyFromNetwork(facing.getOpposite(), voltage, packets);
                        for (int i = 0; i < ampsUsed; i++) {
                            e.useEnergy(voltage);
                        }
                    }
                }
            }
        }
    }

    public static void chargeHotbar(ItemBatteryChargeHotbar battery, ItemStack stack, World world, Entity entity) {
        if (entity instanceof EntityPlayerMP && world.getTotalWorldTime() % 10L < (long) battery.getTier(stack) && isEnabled(stack)) {
            double limit = battery.getTransferLimit(stack);
            int tier = battery.getTier(stack);
            EntityPlayer thePlayer = (EntityPlayer)entity;
            List<ItemStack> inventory = thePlayer.inventory.mainInventory;
            for(int i = 0; i < 9 && limit > 0.0D; ++i) {
                ItemStack toCharge = inventory.get(i);
                if (!StackUtil.isEmpty(toCharge) && (i != thePlayer.inventory.currentItem) && (toCharge.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) || toCharge.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                    IElectricItem e = toCharge.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                    if (e != null) {
                        double charge = e.getMaxCharge() - e.getCharge();
                        charge = ElectricItem.manager.discharge(stack, charge, tier, true, false, false);
                        e.charge((long) charge, tier, true, false);
                        limit -= charge;
                    } else if (toCharge.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                        IEnergyContainer e2 = toCharge.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
                        if (e2 != null) {
                            double charge = e2.getEnergyCapacity() - e2.getEnergyStored();
                            charge = ElectricItem.manager.discharge(stack, charge, tier, true, false, false);
                            e2.addEnergy((long) charge);
                            limit -= charge;
                        }
                    }
                }
            }
        }
    }

    private static boolean isEnabled(ItemStack stack) {
        if (stack != null && stack.hasTagCompound()) {
            if (!stack.getTagCompound().hasKey("mode")) return true;
            return stack.getTagCompound().getByte("mode") == 0;
        } else return false;
    }
}
