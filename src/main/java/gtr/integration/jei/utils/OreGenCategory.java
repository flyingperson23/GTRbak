package gtr.integration.jei.utils;

import gtr.api.GTValues;
import gtr.common.blocks.BlockOre;
import gtr.common.blocks.MetaBlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class OreGenCategory implements IRecipeCategory<OreGenWrapper> {

    private final IDrawable background;
    private final IDrawable icon;

    public OreGenCategory(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(new ResourceLocation(GTValues.MODID, "textures/gui/base/background.png"), 0, 168, 125, 0)
                .addPadding(0, 70, 0, 0)
                .build();
        icon = guiHelper.createDrawableIngredient(new ItemStack(((BlockOre) MetaBlocks.ORES.toArray()[0])));
    }


    @Override
    public String getUid() {
        return GTValues.MODID + ":ores";
    }

    @Override
    public String getTitle() {
        return "Ore Generation";
    }

    @Override
    public String getModName() {
        return GTValues.MODID;
    }


    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, OreGenWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        int counter = 0;

        for (List<ItemStack> i : recipeWrapper.ores) {
            guiItemStacks.init(counter, true, counter*20, 50);
            guiItemStacks.set(counter, i);
            counter++;
        }

        for (List<FluidStack> f : recipeWrapper.fluids) {
            guiFluidStacks.init(counter, true, counter*20, 50);
            guiFluidStacks.set(counter, f);
            counter++;
        }
    }
}
