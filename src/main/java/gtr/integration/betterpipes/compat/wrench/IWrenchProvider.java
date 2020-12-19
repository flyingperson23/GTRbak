package gtr.integration.betterpipes.compat.wrench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IWrenchProvider {
    /** @param item The item to check
     * @return Does this item count as one of your wrenches? */
    boolean isAcceptable(ItemStack item, TileEntity te);
    /** @param item The item to check
     * @param player The player using the item
     * @return Can the item be used? I.e. does it have enough power/durability? */
    boolean canBeUsed(ItemStack item, EntityPlayer player);
    /**@param item the item being used
     * @param player the player using the item
     * This is called when a wrench from your module is actually used. Use this method to damage your wrench, remove power, etc. */
    void use(ItemStack item, EntityPlayer player);
}
