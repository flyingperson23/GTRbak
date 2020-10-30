package gtr.integration.multi.crafttweaker.gtwrap.impl;


import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IICubeRenderer;

import javax.annotation.Nonnull;

public class MCCubeRenderer implements IICubeRenderer {

    @Nonnull
    private final gtr.api.render.ICubeRenderer inner;

    public MCCubeRenderer(@Nonnull gtr.api.render.ICubeRenderer delegate) {
        this.inner = delegate;
    }

    @Nonnull
    @Override
    public gtr.api.render.ICubeRenderer getInternal() {
        return inner;
    }

}
