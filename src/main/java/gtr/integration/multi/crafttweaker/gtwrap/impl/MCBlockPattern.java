package gtr.integration.multi.crafttweaker.gtwrap.impl;

import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IFacing;
import crafttweaker.api.world.IWorld;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.PatternMatchContext;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IBlockPattern;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;

public class MCBlockPattern implements IBlockPattern {

    @Nonnull
    private BlockPattern internal;

    public MCBlockPattern(@Nonnull BlockPattern internal) {
        this.internal = internal;
    }

    @Nonnull
    @Override
    public BlockPattern getInternal() {
        return internal;
    }

    @Override
    public int getFingerLength() {
        return internal.getFingerLength();
    }

    @Override
    public int getThumbLength() {
        return internal.getThumbLength();
    }

    @Override
    public int getPalmLength() {
        return internal.getPalmLength();
    }

    @Override
    public MCPatternMatchContext checkPatternAt(IWorld world, IBlockPos centerPos, IFacing facing) {
        PatternMatchContext match = internal.checkPatternAt(CraftTweakerMC.getWorld(world), CraftTweakerMC.getBlockPos(centerPos), (EnumFacing) facing.getInternal());
        return match == null ? null : new MCPatternMatchContext(match);
    }

}
