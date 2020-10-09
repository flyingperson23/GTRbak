package gtr.common.items;

import gtr.api.items.armor.ArmorMetaItem;
import gtr.common.items.armor.ArmorLogicRebreather;

public class MetaArmor extends ArmorMetaItem<ArmorMetaItem<?>.ArmorMetaValueItem> {

    @Override
    public void registerSubItems() {
        MetaItems.REBREATHER = addItem(0, "rebreather").setArmorLogic(new ArmorLogicRebreather(16));
    }
}
