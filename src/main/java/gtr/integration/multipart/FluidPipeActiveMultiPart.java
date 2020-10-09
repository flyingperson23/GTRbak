package gtr.integration.multipart;

import gtr.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gtr.common.pipelike.fluidpipe.FluidPipeProperties;
import gtr.common.pipelike.fluidpipe.FluidPipeType;
import gtr.common.pipelike.fluidpipe.tile.TileEntityFluidPipeTickable;
import net.minecraft.nbt.NBTTagCompound;

public class FluidPipeActiveMultiPart extends FluidPipeMultiPart {

    private boolean isActivePart;

    FluidPipeActiveMultiPart() {
    }



    @Override
    protected TileEntityMaterialPipeBase<FluidPipeType, FluidPipeProperties> createTileEntity() {
        TileEntityFluidPipeTickable tileEntity = (TileEntityFluidPipeTickable) pipeBlock.createNewTileEntity(true);
        tileEntity.setActive(isActivePart);
        return tileEntity;
    }

    @Override
    public void save(NBTTagCompound tag) {
        super.save(tag);
        tag.setBoolean("ActiveNode", isActivePart);
    }

    @Override
    public void load(NBTTagCompound tag) {
        super.load(tag);
        this.isActivePart = tag.getBoolean("ActiveNode");
    }
}
