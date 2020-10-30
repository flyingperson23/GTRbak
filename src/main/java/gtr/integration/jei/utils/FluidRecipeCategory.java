package gtr.integration.jei.utils;

import gtr.api.GTValues;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import net.minecraft.item.ItemStack;
import java.util.List;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.IGuiHelper;
import net.minecraft.util.ResourceLocation;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;

public class FluidRecipeCategory implements IRecipeCategory<FluidRecipeWrapper> {
    public static final String NAME = "gtr.fluidcategory";
    private final IDrawable background;
    private final IDrawable overlay;
    private final IDrawable icon;
    public static final ResourceLocation ICON;

    public FluidRecipeCategory(final IGuiHelper helper) {
        this.background = helper.createDrawable(new ResourceLocation(GTValues.MODID, "textures/gui/fluid/background.png"), 0, 0, 128, 80);
        this.overlay = helper.createDrawable(new ResourceLocation(GTValues.MODID, "textures/gui/fluid/overlay.png"), 0, 0, 81, 53);
        this.icon = helper.drawableBuilder(FluidRecipeCategory.ICON, 0, 0, 16, 16).setTextureSize(16, 16).build();
    }

    public String getUid() {
        return NAME;
    }

    public String getTitle() {
        return "Fluid Dictionary";
    }

    public String getModName() {
        return GTValues.MODID;
    }

    public IDrawable getBackground() {
        return this.background;
    }

    public IDrawable getIcon() {
        return this.icon;
    }

    public void setRecipe(final IRecipeLayout recipeLayout, final FluidRecipeWrapper recipeWrapper, final IIngredients ingredients) {
        final List<List<ItemStack>> outputItem = ingredients.getOutputs(VanillaTypes.ITEM);
        final List<List<ItemStack>> inputItem = ingredients.getInputs(VanillaTypes.ITEM);
        final IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        final IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiFluidStacks.init(0, false, 16, 16, 81, 49, 1000, false, this.overlay);
        if (outputItem.size() > 0) {
            guiItemStacks.init(1, false, 102, 47);
        }
        if (inputItem.size() > 0) {
            guiItemStacks.init(2, true, 102, 15);
        }
        guiFluidStacks.set(ingredients);
        guiItemStacks.set(ingredients);
        guiFluidStacks.addTooltipCallback(new FluidTooltipCallback());
    }

    static {
        ICON = new ResourceLocation(GTValues.MODID, "textures/gui/fluid/icon.png");
    }
}
