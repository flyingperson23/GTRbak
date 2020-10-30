package gtr.integration.multi.crafttweaker.gtwrap.impl;

import crafttweaker.api.block.IBlockState;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IFacing;
import crafttweaker.api.world.IWorld;
import gtr.api.multiblock.BlockWorldState;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IBlockWorldState;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IPatternMatchContext;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;

public class MCBlockWorldState implements IBlockWorldState {

    @Nonnull
    private final BlockWorldState inner;

    public MCBlockWorldState(@Nonnull BlockWorldState inner) {
        this.inner = inner;
    }

    @Nonnull
    @Override
    public BlockWorldState getInternal() {
        return inner;
    }

    @Override
    public IPatternMatchContext getMatchContext() {
        return new MCPatternMatchContext(inner.getMatchContext());
    }

    @Override
    public IPatternMatchContext getLayerContext() {
        return new MCPatternMatchContext(inner.getLayerContext());
    }

    @Override
    public IBlockState getBlockState() {
        return CraftTweakerMC.getBlockState((inner.getBlockState()));
    }

    @Override
    public IBlockPos getPos() {
        return CraftTweakerMC.getIBlockPos(inner.getPos());
    }

    @Override
    public IBlockState getOffsetState(IFacing face) {
        return CraftTweakerMC.getBlockState(inner.getOffsetState((EnumFacing) face.getInternal())); // no helper method for this it seems
    }

    @Override
    public IWorld getWorld() {
        return CraftTweakerMC.getIWorld(inner.getWorld());
    }

}
