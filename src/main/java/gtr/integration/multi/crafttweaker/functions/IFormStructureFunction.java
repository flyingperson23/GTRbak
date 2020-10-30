package gtr.integration.multi.crafttweaker.functions;

import crafttweaker.annotations.ZenRegister;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IControllerTile;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IPatternMatchContext;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;

/**
 * Called when the multiblock is formed.
 *
 * Use this to store any extra data from the pattern matching.
 */
@FunctionalInterface
@ZenClass("mods.gregtech.multiblock.functions.IFormStructureFunction")
@ZenRegister
public interface IFormStructureFunction {

    /**
     * Implement this with a function.
     *
     * @param controller The multiblock controller.
     * @param context The context of the structure.
     */
    @ZenMethod
    void formStructure(@Nonnull IControllerTile controller, @Nonnull IPatternMatchContext context);

}
