package gtr.api.recipes.builders;

import com.google.common.collect.ImmutableMap;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeBuilder;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.recipeproperties.ImplosionExplosiveProperty;
import gtr.api.util.EnumValidationResult;
import gtr.api.util.GTLog;
import gtr.api.util.GTUtility;
import gtr.api.util.ValidationResult;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.builder.ToStringBuilder;
import stanhebben.zenscript.annotations.ZenMethod;

public class ImplosionRecipeBuilder extends RecipeBuilder<ImplosionRecipeBuilder> {

    protected int explosivesAmount;
    protected ItemStack explosivesType;

    public ImplosionRecipeBuilder() {
    }

    public ImplosionRecipeBuilder(Recipe recipe, RecipeMap<ImplosionRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.explosivesType = recipe.getRecipePropertyStorage().getRecipePropertyValue(ImplosionExplosiveProperty.getInstance(), ItemStack.EMPTY);
    }

    public ImplosionRecipeBuilder(RecipeBuilder<ImplosionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public ImplosionRecipeBuilder copy() {
        return new ImplosionRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("explosives")) {
            explosivesAmount((int) value);
            return true;
        }
        return false;
    }

    @Override
    public boolean applyProperty(String key, ItemStack exploType) {
        if (key.equals("explosives")) {
            explosivesAmount(exploType.getCount());
            explosivesType = exploType;
            return true;
        }
        return false;
    }

    @ZenMethod
    public ImplosionRecipeBuilder explosivesAmount(int explosivesAmount) {
        if (!GTUtility.isBetweenInclusive(1, 64, explosivesAmount)) {
            GTLog.logger.error("Amount of explosives should be from 1 to 64 inclusive", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.explosivesAmount = explosivesAmount;
        return this;
    }

    @ZenMethod
    public ImplosionRecipeBuilder explosivesType(ItemStack explosivesType) {
        this.explosivesType = explosivesType;
        return this;
    }

    public ValidationResult<Recipe> build() {

        int amount  = Math.max(1, explosivesAmount / 2);
        if(explosivesType == null) {
            this.explosivesType = new ItemStack(Blocks.TNT, amount);
        }
        else {
            this.explosivesType = new ItemStack(explosivesType.getItem(), amount, explosivesType.getMetadata());
        }
        inputs.add(CountableIngredient.from(explosivesType));



        Recipe recipe = new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
            duration, EUt, hidden);

        if (!recipe.getRecipePropertyStorage().store(ImmutableMap.of(ImplosionExplosiveProperty.getInstance(), explosivesType))) {
            return ValidationResult.newResult(EnumValidationResult.INVALID, recipe);
        }

        return ValidationResult.newResult(finalizeAndValidate(), recipe);

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append(ImplosionExplosiveProperty.getInstance().getKey(), explosivesType)
            .toString();
    }
}
