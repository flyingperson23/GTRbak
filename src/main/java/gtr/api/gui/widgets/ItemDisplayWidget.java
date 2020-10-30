package gtr.api.gui.widgets;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

public class ItemDisplayWidget extends SlotWidget{
    public ItemDisplayWidget(int slots, ItemStack stack, int slotIndex, int xPosition, int yPosition, boolean canTakeItems, boolean canPutItems) {
        super(new IItemHandlerModifiable() {
            @Override
            public void setStackInSlot(int slot, @Nonnull ItemStack stack) {

            }

            @Override
            public int getSlots() {
                return slots;
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return stack;
            }

            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        }, slotIndex, xPosition, yPosition, canTakeItems, canPutItems);
    }
}
