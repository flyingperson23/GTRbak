package gtr.api.pipenet.block.simple;

import gtr.api.pipenet.PipeNet;
import gtr.api.pipenet.WorldPipeNet;
import gtr.api.pipenet.block.BlockPipe;
import gtr.api.pipenet.block.IPipeType;
import gtr.api.pipenet.tile.IPipeTile;
import gtr.api.pipenet.tile.TileEntityPipeBase;
import net.minecraft.item.ItemStack;

public abstract class BlockSimplePipe<PipeType extends Enum<PipeType> & IPipeType<NodeDataType>, NodeDataType, WorldPipeNetType extends WorldPipeNet<NodeDataType, ? extends PipeNet<NodeDataType>>> extends BlockPipe<PipeType, NodeDataType, WorldPipeNetType> {

    @Override
    public NodeDataType createProperties(IPipeTile<PipeType, NodeDataType> pipeTile) {
        return createProperties(pipeTile.getPipeType());
    }

    @Override
    public NodeDataType createItemProperties(ItemStack itemStack) {
        return createProperties(getItemPipeType(itemStack));
    }

    protected abstract NodeDataType createProperties(PipeType pipeType);

    @Override
    public ItemStack getDropItem(IPipeTile<PipeType, NodeDataType> pipeTile) {
        return new ItemStack(this, 1, pipeTile.getPipeType().ordinal());
    }

    @Override
    public PipeType getItemPipeType(ItemStack itemStack) {
        return getPipeTypeClass().getEnumConstants()[itemStack.getMetadata()];
    }

    @Override
    public void setTileEntityData(TileEntityPipeBase<PipeType, NodeDataType> pipeTile, ItemStack itemStack) {
        pipeTile.setPipeData(this, getItemPipeType(itemStack));
    }
}
