package gtr.integration.multi.crafttweaker.functions;

import crafttweaker.annotations.ZenRegister;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IRecipeLogic;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;

/**
 * Called every time a recipe completes.
 */
@FunctionalInterface
@ZenClass("mods.gtr.recipe.functions.ICompleteRecipeFunction")
@ZenRegister
public interface ICompleteRecipeFunction {

    /**
     * Implement this with a function.
     */
    @ZenMethod
    void run(@Nonnull IRecipeLogic logic);

}
