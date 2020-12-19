package gtr.loaders.recipe.magnetic;


import gtr.api.items.materialitem.MaterialMetaItem;
import gtr.api.unification.material.Materials;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RecipeMagnetic extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    private int foodSlot = 0;

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        int numStacks = 0;
        List<Integer> occupiedSlots = new ArrayList<>();
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) {
                numStacks++;
                occupiedSlots.add(i);
            }
        }
        if (numStacks != 2) {
            return false;
        }
        ItemStack foodStack = ItemStack.EMPTY;
        ItemStack oilStack = ItemStack.EMPTY;
        for (int i : occupiedSlots) {
            ItemStack tempStack = inv.getStackInSlot(i);
            if (tempStack.getItem() instanceof ItemTool && foodStack.isEmpty()) {
                foodStack = tempStack;
                this.foodSlot = i;
            } else if (tempStack.getItem() instanceof MaterialMetaItem && ((MaterialMetaItem) tempStack.getItem()).getMaterial(tempStack) == Materials.SteelMagnetic && oilStack.isEmpty()) {
                oilStack = tempStack;
            } else {
                return false;
            }
        }
        return !foodStack.hasTagCompound() || !foodStack.getTagCompound().hasKey("magnetic");
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack foodStack = inv.getStackInSlot(foodSlot);
        if (foodStack.isEmpty() || !(foodStack.getItem() instanceof ItemTool)) {
            return ItemStack.EMPTY;
        }
        ItemStack returnStack = new ItemStack(foodStack.getItem(), 1, foodStack.getItemDamage());
        NBTTagCompound amendedTag = foodStack.getTagCompound();
        if (amendedTag == null) {
            amendedTag = new NBTTagCompound();
        } else {
            amendedTag = amendedTag.copy();
        }
        amendedTag.setTag("magnetic", new NBTTagByte((byte) 0));
        returnStack.setTagCompound(amendedTag);
        return returnStack;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        return NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
    }

    @Override
    public boolean canFit(int width, int height) {
        return width <= 3 && height <= 3;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

}
