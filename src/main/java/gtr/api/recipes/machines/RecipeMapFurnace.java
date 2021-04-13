package gtr.api.recipes.machines;

import gtr.api.recipes.MatchingMode;
import gtr.api.recipes.ModHandler;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import gtr.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMapFurnace extends RecipeMap<SimpleRecipeBuilder> {

    public RecipeMapFurnace(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, int amperage, SimpleRecipeBuilder defaultRecipe) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        Recipe normalRecipe = super.findRecipe(voltage, inputs, fluidInputs, outputFluidTankCapacity, m);
        if (normalRecipe != null || inputs.size() == 0 || inputs.get(0).isEmpty())
            return normalRecipe;
        for(ItemStack input : inputs) {
            ItemStack output = ModHandler.getSmeltingOutput(input);

            if(!output.isEmpty()) {
                return this.recipeBuilder()
                    .inputs(GTUtility.copyAmount(1, input))
                    .outputs(output)
                    .duration(128).EUt(4)
                    .build().getResult();
            }
        }

        return null;
    }
}
