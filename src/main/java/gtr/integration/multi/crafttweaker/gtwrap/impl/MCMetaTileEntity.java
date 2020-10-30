package gtr.integration.multi.crafttweaker.gtwrap.impl;

import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IFacing;
import crafttweaker.api.world.IWorld;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IMetaTileEntity;

import javax.annotation.Nonnull;

public class MCMetaTileEntity implements IMetaTileEntity {

    @Nonnull
    private final MetaTileEntity inner;

    public MCMetaTileEntity(@Nonnull MetaTileEntity inner) {
        this.inner = inner;
    }

    @Nonnull
    @Override
    public MetaTileEntity getInternal() {
        return inner;
    }

    public IWorld getWorld() {
        return CraftTweakerMC.getIWorld(inner.getWorld());
    }

    public IBlockPos getPos() {
        return CraftTweakerMC.getIBlockPos(inner.getPos());
    }

    public long getTimer() {
        return inner.getTimer();
    }

    public String getMetaName() {
        return inner.getMetaName();
    }

    public String getMetaFullName() {
        return inner.getMetaFullName();
    }

    public IFacing getFrontFacing() {
        return CraftTweakerMC.getIFacing(inner.getFrontFacing());
    }

}
