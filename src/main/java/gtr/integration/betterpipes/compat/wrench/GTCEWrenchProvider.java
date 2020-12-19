package gtr.integration.betterpipes.compat.wrench;

import gtr.api.capability.GregtechCapabilities;
import gtr.common.pipelike.cable.tile.TileEntityCable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GTCEWrenchProvider implements IWrenchProvider {

    @Override
    public boolean isAcceptable(ItemStack item, TileEntity te) {
        if (te instanceof TileEntityCable) {
            return item.hasCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null);
        }
        return item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null);
    }

    @Override
    public boolean canBeUsed(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            return item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, true);
        } else if (item.hasCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null)) {
            return item.getCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null).damageItem(1, true);
        }
        return false;
    }

    @Override
    public void use(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, false);
        } else if (item.hasCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null).damageItem(1, false);
        }
    }
}
