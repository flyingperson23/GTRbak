package gtr.integration.multi.crafttweaker.gtwrap.impl;

import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IMultiblockShapeInfo;

import javax.annotation.Nonnull;

public class MCMultiblockShapeInfo implements IMultiblockShapeInfo {

    @Nonnull
    private final MultiblockShapeInfo inner;

    public MCMultiblockShapeInfo(@Nonnull MultiblockShapeInfo inner) {
        this.inner = inner;
    }

    @Nonnull
    @Override
    public MultiblockShapeInfo getInternal() {
        return inner;
    }

}
