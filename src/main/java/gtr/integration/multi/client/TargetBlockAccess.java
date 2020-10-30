package gtr.integration.multi.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class TargetBlockAccess implements IBlockAccess {

    private final IBlockAccess delegate;
    private BlockPos targetPos;

    public TargetBlockAccess(IBlockAccess delegate, BlockPos pos) {
        this.delegate = delegate;
        this.targetPos = pos;
    }

    public void setPos(BlockPos pos) {
        targetPos = pos;
    }

    @Nullable
    @Override
    public TileEntity getTileEntity(BlockPos pos) {
        return pos.equals(BlockPos.ORIGIN) ? delegate.getTileEntity(targetPos) : null;
    }

    @Override
    public int getCombinedLight(@Nonnull BlockPos pos, int lightValue) {
        return 15;
    }

    @Nonnull
    @Override
    public IBlockState getBlockState(BlockPos pos) {
        return pos.equals(BlockPos.ORIGIN) ? delegate.getBlockState(targetPos) : Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean isAirBlock(BlockPos pos) {
        return !pos.equals(BlockPos.ORIGIN) || delegate.isAirBlock(targetPos);
    }

    @Nonnull
    @Override
    public Biome getBiome(@Nonnull BlockPos pos) {
        return delegate.getBiome(targetPos);
    }

    @ParametersAreNonnullByDefault
    @Override
    public int getStrongPower(BlockPos pos, EnumFacing direction) {
        return 0;
    }

    @Nonnull
    @Override
    public WorldType getWorldType() {
        return delegate.getWorldType();
    }

    @Override
    public boolean isSideSolid(BlockPos pos, @Nonnull EnumFacing side, boolean _default) {
        return pos.equals(BlockPos.ORIGIN) && delegate.isSideSolid(targetPos, side, _default);
    }

}
