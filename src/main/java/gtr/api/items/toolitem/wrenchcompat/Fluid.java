package gtr.api.items.toolitem.wrenchcompat;

import gtr.api.pipenet.tile.AttachmentType;
import gtr.api.util.GTUtility;
import gtr.common.blocks.MetaBlocks;
import gtr.common.pipelike.fluidpipe.tile.TileEntityFluidPipe;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.ArrayList;
import java.util.List;

public class Fluid implements ICompatBase {

    @Override
    public boolean canConnect(TileEntity te, EnumFacing direction) {

        TileEntity te2 = te.getWorld().getTileEntity(te.getPos().offset(direction, 1));
        if (te2 != null) {
            return te2.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction.getOpposite()) || te2 instanceof TileEntityFluidPipe;
        }
        return false;
    }

    @Override
    public ArrayList<EnumFacing> getConnections(TileEntity te) {
        ArrayList<EnumFacing> connections = new ArrayList<>();
        if (isAcceptable(te)) {
            TileEntityFluidPipe cable = (TileEntityFluidPipe) te;
            int mask = cable.getPipeBlock().getActualConnections(cable.getPipeBlock().getPipeTileEntity(te), te.getWorld());
            connections = GTUtility.fromGTCEBitmask(mask);
        }
        return connections;
    }

    @Override
    public boolean isAcceptable(TileEntity te) {
        return te instanceof TileEntityFluidPipe;
    }

    @Override
    public void connect(TileEntity te, EnumFacing direction, EntityPlayer player) {
        if (isAcceptable(te)) {
            TileEntityFluidPipe cable = (TileEntityFluidPipe) te;
            cable.setConnectionBlocked(AttachmentType.PIPE, direction, false);

            if (te.getWorld() instanceof WorldServer) {
                GTUtility.update(te);
            }
        }
        System.out.println(te.getPos()+" connect");

    }

    @Override
    public void disconnect(TileEntity te, EnumFacing direction, EntityPlayer player) {
        if (isAcceptable(te)) {
            TileEntityFluidPipe cable = (TileEntityFluidPipe) te;
            cable.setConnectionBlocked(AttachmentType.PIPE, direction, true);

            if (te.getWorld() instanceof WorldServer) {
                GTUtility.update(te);
            }
        }

        System.out.println(te.getPos()+" disconnect");

    }

    @Override
    public List<Block> getAcceptedBlocks() {
        ArrayList<Block> accepted = new ArrayList<>();
        accepted.add(MetaBlocks.FLUID_PIPE);
        return accepted;
    }

    @Override
    public float getBreakSpeed() {
        return 80f;
    }
}
