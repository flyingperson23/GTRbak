package gtr.integration.energistics.items;

import gtr.api.items.metaitem.MetaItem;
import net.minecraft.item.ItemStack;

public final class AEItems {
    public static MetaItem<?>.MetaValueItem AE2_STOCKER_LV;
    public static MetaItem<?>.MetaValueItem AE2_STOCKER_MV;
    public static MetaItem<?>.MetaValueItem AE2_STOCKER_HV;
    public static MetaItem<?>.MetaValueItem AE2_STOCKER_EV;
    public static MetaItem<?>.MetaValueItem AE2_STOCKER_IV;
    public static MetaItem<?>.MetaValueItem MACHINE_STATUS;
    public static MetaItem<?>.MetaValueItem FLUID_ENCODER;
    public static MetaItem<?>.MetaValueItem STOCKER_TERMINAL;
    public static AEItems1 metaItem1;

    private AEItems() {
    }

    public static void preInit() {
        metaItem1 = new AEItems1();
        metaItem1.setRegistryName("metaItem1");
    }

    public static void registerRecipes() {
        metaItem1.registerRecipes();
    }

    public static MetaItem<?>.MetaValueItem getMetaValueItemFromStack(ItemStack stack) {
        return ((MetaItem<?>) stack.getItem()).getItem(stack);
    }
}
