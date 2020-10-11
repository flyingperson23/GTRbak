package gt6Pipes.compat;

import gt6Pipes.util.BlockWrapper;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.items.toolitem.ToolMetaItem;
import gtr.common.ConfigHolder;
import gtr.common.items.MetaItems;
import gtr.common.items.MetaTool;
import gtr.common.items.behaviors.WireCutterBehavior;
import gtr.common.pipelike.cable.BlockCable;
import gtr.common.pipelike.fluidpipe.BlockFluidPipe;
import gtr.common.tools.ToolWireCutter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTCEWrenchProvider implements IWrenchProvider {
    @Override
    public boolean enable() {
        return ConfigHolder.gt6Pipes;
    }

    @Override
    public boolean isAcceptable(ItemStack item, BlockWrapper b) {
        if (b.state.getBlock() instanceof BlockCable) {
            for (int i : OreDictionary.getOreIDs(item)) {
                if (OreDictionary.getOreName(i).equals("craftingToolWireCutter")) return true;
            }
            return false;
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
        return item.getItemDamage() < item.getMaxDamage();
    }

    @Override
    public void use(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, false);
        } else if (item.hasCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WIRE_CUTTER, null).damageItem(1, false);
        } else {
                item.damageItem(1, player);
        }
    }
}
