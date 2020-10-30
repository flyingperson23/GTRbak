package gtr.integration.multi.crafttweaker.gtwrap.interfaces;

import crafttweaker.annotations.ZenRegister;
import gtr.integration.multi.crafttweaker.construction.MultiblockShapeInfoBuilder;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import stanhebben.zenscript.annotations.ZenClass;

import javax.annotation.Nonnull;

/**
 * Represents an example structure for a multiblock, shown in JEI or as an in-world preview.
 *
 * @see MultiblockShapeInfoBuilder
 */
@ZenClass("mods.gregtech.multiblock.IMultiblockShapeInfo")
@ZenRegister
public interface IMultiblockShapeInfo {

    @Nonnull
    MultiblockShapeInfo getInternal();

}
