package gtr.integration.energistics.items.stats;

import appeng.api.parts.IPart;
import gtr.api.items.metaitem.stats.IItemComponent;
import net.minecraft.item.ItemStack;

public interface IPartProvider extends IItemComponent {
    IPart getPart(ItemStack stack);
}