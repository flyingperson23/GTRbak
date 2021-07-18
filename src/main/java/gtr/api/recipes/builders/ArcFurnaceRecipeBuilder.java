package gtr.api.recipes.builders;

import com.google.common.collect.ImmutableMap;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeBuilder;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.FluidMaterial;
import gtr.api.util.ValidationResult;

public class ArcFurnaceRecipeBuilder extends RecipeBuilder<ArcFurnaceRecipeBuilder> {

    public ArcFurnaceRecipeBuilder() {
    }

    public ArcFurnaceRecipeBuilder(RecipeBuilder<ArcFurnaceRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public ArcFurnaceRecipeBuilder copy() {
        return new ArcFurnaceRecipeBuilder(this);
    }

    @Override
    public void buildAndRegister() {

        if (fluidInputs.isEmpty()) {
            fluidInputs(Materials.Oxygen.getFluid(this.duration));
            for (FluidMaterial material : new FluidMaterial[]{Materials.Oxygen, Materials.Nitrogen}) {
                int plasmaAmount = (int) Math.max(1L, this.duration / (material.getAverageMass() * 16L));
                SimpleRecipeBuilder builder = RecipeMaps.PLASMA_ARC_FURNACE_RECIPES.recipeBuilder()
                    .inputsIngredients(this.inputs)
                    .outputs(this.outputs)
                    .duration(Math.max(1, this.duration / 16))
                    .EUt(this.EUt / 3)
                    .fluidInputs(material.getPlasma(plasmaAmount))
                    .fluidOutputs(material.getFluid(plasmaAmount));
                builder.buildAndRegister();
            }
        }
        super.buildAndRegister();
    }

    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
            new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs, duration, EUt, hidden));

    }

}
