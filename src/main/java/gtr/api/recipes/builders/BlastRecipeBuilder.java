package gtr.api.recipes.builders;

import com.google.common.collect.ImmutableMap;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeBuilder;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.recipeproperties.BlastTemperatureProperty;
import gtr.api.util.EnumValidationResult;
import gtr.api.util.GTLog;
import gtr.api.util.ValidationResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BlastRecipeBuilder extends RecipeBuilder<BlastRecipeBuilder> {

    private int blastFurnaceTemp;

    public BlastRecipeBuilder() {
    }

    public BlastRecipeBuilder(Recipe recipe, RecipeMap<BlastRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.blastFurnaceTemp = recipe.getRecipePropertyStorage().getRecipePropertyValue(BlastTemperatureProperty.getInstance(), 0);
    }

    public BlastRecipeBuilder(RecipeBuilder<BlastRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public BlastRecipeBuilder copy() {
        return new BlastRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("temperature")) {
            this.blastFurnaceTemp(((Number) value).intValue());
            return true;
        }
        return true;
    }

    public BlastRecipeBuilder blastFurnaceTemp(int blastFurnaceTemp) {
        if (blastFurnaceTemp <= 0) {
            GTLog.logger.error("Blast Furnace Temperature cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.blastFurnaceTemp = blastFurnaceTemp;
        return this;
    }

    public ValidationResult<Recipe> build() {
        Recipe recipe = new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
            duration, EUt, hidden);
        if (!recipe.getRecipePropertyStorage().store(ImmutableMap.of(BlastTemperatureProperty.getInstance(), blastFurnaceTemp))) {
            return ValidationResult.newResult(EnumValidationResult.INVALID, recipe);
        }

        return ValidationResult.newResult(finalizeAndValidate(), recipe);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(BlastTemperatureProperty.getInstance().getKey(), blastFurnaceTemp)
            .toString();
    }
}
