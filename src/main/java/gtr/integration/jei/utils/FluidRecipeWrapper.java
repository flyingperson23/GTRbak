package gtr.integration.jei.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.FluidStack;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import mezz.jei.api.recipe.IRecipeWrapper;

public class FluidRecipeWrapper implements IRecipeWrapper {
    public static final IRecipeWrapperFactory<FluidWrapper> FACTORY;
    public final FluidWrapper theRecipe;

    public FluidRecipeWrapper(final FluidWrapper recipe) {
        this.theRecipe = recipe;
    }

    public void getIngredients(final IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.FLUID, new FluidStack(this.theRecipe.getFluid(), 1000));
        ingredients.setOutput(VanillaTypes.ITEM, UniversalBucket.getFilledBucket(new UniversalBucket(), this.theRecipe.getFluid()));
        ingredients.setInput(VanillaTypes.ITEM, new ItemStack(Items.BUCKET));
    }

    static {
        FACTORY = (FluidRecipeWrapper::new);
    }
}
