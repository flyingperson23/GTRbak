package gtr.loaders.recipe.magnetic;


import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.UnificationEntry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagByte;

import java.util.ArrayList;
import java.util.List;

public class MagneticRecipeWrapper extends BlankRecipeWrapper implements ICraftingRecipeWrapper {

    private final List<ItemStack> inputs;
    private final ItemStack output;

    public MagneticRecipeWrapper(ItemTool toolItem) {
        ItemStack foodStack = new ItemStack(toolItem);

        this.inputs = new ArrayList<>();
        this.inputs.add(foodStack);
        this.inputs.add(OreDictUnifier.get(new UnificationEntry(OrePrefix.plate, Materials.SteelMagnetic)));
        ItemStack outputStack = new ItemStack(toolItem);
        outputStack.setTagInfo("magnetic", new NBTTagByte((byte) 0));
        this.output = outputStack;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, this.inputs);
        ingredients.setOutput(ItemStack.class, this.output);
    }

}