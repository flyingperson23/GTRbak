package gtr.api.recipes.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.recipes.PrimitiveBlastFurnaceRecipe;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenExpansion("mods.gtr.recipe.PrimitiveBlastFurnaceRecipe")
@ZenRegister
public class PBFRecipeExpansion {

    @ZenMethod
    public static void remove(PrimitiveBlastFurnaceRecipe recipe) {
        RecipeMaps.getPrimitiveBlastFurnaceRecipes().remove(recipe);
    }
}
