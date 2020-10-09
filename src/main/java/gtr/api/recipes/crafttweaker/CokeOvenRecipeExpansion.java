package gtr.api.recipes.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.recipes.CokeOvenRecipe;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenExpansion("mods.gtr.recipe.CokeOvenRecipe")
@ZenRegister
public class CokeOvenRecipeExpansion {

    @ZenMethod
    public static void remove(CokeOvenRecipe recipe) {
        RecipeMaps.getCokeOvenRecipes().remove(recipe);
    }
}
