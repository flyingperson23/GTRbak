package gtr.common.pipelike.inventory.tile;

import gtr.api.pipenet.block.simple.EmptyNodeData;
import gtr.api.pipenet.tile.TileEntityPipeBase;
import gtr.common.pipelike.inventory.InventoryPipeType;

public class TileEntityInventoryPipe extends TileEntityPipeBase<InventoryPipeType, EmptyNodeData> {

    @Override
    public Class<InventoryPipeType> getPipeTypeClass() {
        return InventoryPipeType.class;
    }

    @Override
    public boolean supportsTicking() {
        return false;
    }
}
