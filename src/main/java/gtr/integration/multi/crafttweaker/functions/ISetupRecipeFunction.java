package gtr.integration.multi.crafttweaker.functions;

import crafttweaker.annotations.ZenRegister;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IRecipe;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IRecipeLogic;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;

/**
 * Called whenever the worktable sets up for a new recipe.
 */
@FunctionalInterface
@ZenClass("mods.gregtech.recipe.functions.ISetupRecipeFunction")
@ZenRegister
public interface ISetupRecipeFunction {

    /**
     * Implement this with a function.
     */
    @ZenMethod
    void run(@Nonnull IRecipeLogic logic, @Nonnull IRecipe recipe);

}
