package gtr.api.recipes.builders;

import com.google.common.collect.ImmutableMap;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeBuilder;
import gtr.api.recipes.RecipeMap;
import gtr.api.util.EnumValidationResult;
import gtr.api.util.GTLog;
import gtr.api.util.ValidationResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FusionRecipeBuilder extends RecipeBuilder<FusionRecipeBuilder> {

    private int EUToStart;

    public FusionRecipeBuilder() {
    }

    public FusionRecipeBuilder(Recipe recipe, RecipeMap<FusionRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.EUToStart = recipe.getIntegerProperty("EUToStart");
    }

    public FusionRecipeBuilder(RecipeBuilder<FusionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public FusionRecipeBuilder copy() {
        return new FusionRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("eu_to_start")) {
            this.EUToStart(((Number) value).intValue());
            return true;
        }
        return false;
    }

    public FusionRecipeBuilder EUToStart(int EUToStart) {
        if (EUToStart <= 0) {
            GTLog.logger.error("EU to start cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.EUToStart = EUToStart;
        return this;
    }

    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
            new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                ImmutableMap.of("eu_to_start", EUToStart),
                duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("EUToStart", EUToStart)
            .toString();
    }
}
