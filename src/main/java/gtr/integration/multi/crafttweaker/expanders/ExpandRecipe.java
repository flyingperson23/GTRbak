package gtr.integration.multi.crafttweaker.expanders;

import crafttweaker.annotations.ZenRegister;
import gtr.api.recipes.crafttweaker.CTRecipe;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCRecipe;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IRecipe;
import gtr.integration.multi.helper.ReflectionHelper;
import stanhebben.zenscript.annotations.ZenCaster;
import stanhebben.zenscript.annotations.ZenExpansion;

@ZenRegister
@ZenExpansion("mods.gtr.recipe.Recipe")
public class ExpandRecipe {

    @ZenCaster
    public static IRecipe asIRecipe(CTRecipe recipe) {
        return new MCRecipe(ReflectionHelper.getPrivate(CTRecipe.class, "backingRecipe", recipe));
    }

}
