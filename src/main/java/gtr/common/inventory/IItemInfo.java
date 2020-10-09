package gtr.common.inventory;

import gtr.api.util.ItemStackKey;

public interface IItemInfo {

    int getTotalItemAmount();

    ItemStackKey getItemStackKey();
}
