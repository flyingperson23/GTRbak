package gtr.integration.multi.crafttweaker.gtwrap.impl;

import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IMultiblockAbility;

import javax.annotation.Nonnull;

public class MCMultiblockAbility<T> implements IMultiblockAbility {

    private final MultiblockAbility<T> inner;

    public MCMultiblockAbility(MultiblockAbility<T> inner) {
        this.inner = inner;
    }

    @Nonnull
    @Override
    public MultiblockAbility<T> getInternal() {
        return inner;
    }

}
