package gtr.common.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;

/**
 * Just a basic tile entity with block state caching system
 */
public abstract class TileEntityBase extends TileEntity {

    private IBlockState blockState;

    public IBlockState getBlockState() {
        if (blockState == null) {
            updateBlockState();
        }
        return this.blockState;
    }

    @Override
    public void markDirty() {
        updateBlockState();
    }

    private void updateBlockState() {
        this.blockState = getWorld().getBlockState(getPos());
    }

    public void setBlockState(IBlockState blockState) {
        //throw new IllegalStateException("Could not set block state for in-world tile entity");
    }

}
