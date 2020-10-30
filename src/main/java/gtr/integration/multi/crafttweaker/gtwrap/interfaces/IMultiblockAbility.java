package gtr.integration.multi.crafttweaker.gtwrap.interfaces;

import crafttweaker.annotations.ZenRegister;
import gtr.integration.multi.crafttweaker.gtwrap.constants.ConstantMultiblockAbility;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import stanhebben.zenscript.annotations.ZenClass;

import javax.annotation.Nonnull;

/**
 * An ability that a multiblock may have, such as fluid import/export, or item import/export.
 *
 * @see ConstantMultiblockAbility
 */
@ZenClass("mods.gregtech.multiblock.IMultiblockAbility")
@ZenRegister
public interface IMultiblockAbility {

    @Nonnull
    MultiblockAbility<?> getInternal();

}
