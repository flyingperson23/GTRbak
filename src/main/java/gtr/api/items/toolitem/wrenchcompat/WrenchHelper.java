package gtr.api.items.toolitem.wrenchcompat;

import gtr.api.capability.GregtechCapabilities;
import gtr.common.ConfigHolder;
import gtr.common.pipelike.cable.BlockCable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static gtr.common.render.WrenchOverlayHandler.BlockWrapper;


public class WrenchHelper {
    public static boolean enable() {
        return ConfigHolder.gt6Pipes;
    }

    public static boolean isAcceptable(ItemStack item, BlockWrapper b) {
        if (item != null && !item.isEmpty()) {
            if (b.state.getBlock() instanceof BlockCable) {
                for (int i : OreDictionary.getOreIDs(item)) {
                    if (OreDictionary.getOreName(i).equals("craftingToolWireCutter")) return true;
                }
                return false;
            }

            return item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null);
        }
        return false;
    }

    public static boolean canBeUsed(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            return item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, true);
        } else if (item.hasCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null)) {
            return item.getCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null).damageItem(1, true);
        }
        return item.getItemDamage() < item.getMaxDamage();
    }

    public static void use(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, false);
        } else if (item.hasCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null).damageItem(1, false);
        } else {
            item.damageItem(1, player);
        }
    }
}
