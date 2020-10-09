package gt6Pipes.compat;

import gt6Pipes.util.BlockWrapper;
import gtr.api.capability.GregtechCapabilities;
import gtr.common.ConfigHolder;
import gtr.common.items.MetaItems;
import gtr.common.pipelike.cable.BlockCable;
import gtr.common.pipelike.fluidpipe.BlockFluidPipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GTCEWrenchProvider implements IWrenchProvider {
    @Override
    public boolean enable() {
        return ConfigHolder.gt6Pipes;
    }

    @Override
    public boolean isAcceptable(ItemStack item, BlockWrapper b) {
        if (b.state.getBlock() instanceof BlockCable) {
            return item.getItem() == MetaItems.WIRE_CUTTER.getMetaItem();
        }

        return item.getItem() == MetaItems.WRENCH.getMetaItem() || item.getItem() == MetaItems.WRENCH_LV.getMetaItem() || item.getItem() == MetaItems.WRENCH_MV.getMetaItem() || item.getItem() == MetaItems.WRENCH_HV.getMetaItem();
    }

    @Override
    public boolean canBeUsed(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            return item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, true);
        }
        return item.getItemDamage() < item.getMaxDamage();
    }

    @Override
    public void use(ItemStack item, EntityPlayer player) {
        if (item.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            item.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null).damageItem(1, false);
        } else {
            item.damageItem(1, player);
        }
    }
}
