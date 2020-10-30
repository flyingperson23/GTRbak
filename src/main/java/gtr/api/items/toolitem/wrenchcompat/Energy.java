package gtr.api.items.toolitem.wrenchcompat;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.pipenet.tile.AttachmentType;
import gtr.api.util.GTUtility;
import gtr.common.ConfigHolder;
import gtr.common.blocks.MetaBlocks;
import gtr.common.pipelike.cable.tile.TileEntityCable;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraftforge.energy.CapabilityEnergy;


import java.util.ArrayList;
import java.util.List;

public class Energy implements ICompatBase {

    @Override
    public boolean canConnect(TileEntity te, EnumFacing direction) {
        TileEntity te2 = GTUtility.getTE(GTUtility.fromTE(te).offset(direction));
        if (te2 != null) {
            return te2.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, direction.getOpposite())
                || te2 instanceof TileEntityCable
                || (ConfigHolder.rfCompat && te2.hasCapability(CapabilityEnergy.ENERGY, direction.getOpposite()))
                || (ConfigHolder.euCompat && IC2Handler.isAcceptable(te2));
        }
        return false;
    }

    @Override
    public ArrayList<EnumFacing> getConnections(TileEntity te) {
        ArrayList<EnumFacing> connections = new ArrayList<>();
        if (isAcceptable(te)) {
            TileEntityCable cable = (TileEntityCable) te;
            int mask = cable.getPipeBlock().getActualConnections(cable.getPipeBlock().getPipeTileEntity(te), te.getWorld());
            connections = GTUtility.fromGTCEBitmask(mask);
        }
        return connections;
    }

    @Override
    public boolean isAcceptable(TileEntity te) {
        return te instanceof TileEntityCable;
    }

    @Override
    public void connect(TileEntity te, EnumFacing direction, EntityPlayer player) {
        if (isAcceptable(te)) {
            TileEntityCable cable = (TileEntityCable) te;
            cable.setConnectionBlocked(AttachmentType.PIPE, direction, false);

            if (te.getWorld() instanceof WorldServer) {
                GTUtility.update(te);
            }
            cable.notifyBlockUpdate();
        }
    }

    @Override
    public void disconnect(TileEntity te, EnumFacing direction, EntityPlayer player) {
        if (isAcceptable(te)) {
            TileEntityCable cable = (TileEntityCable) te;
            cable.setConnectionBlocked(AttachmentType.PIPE, direction, true);

            if (te.getWorld() instanceof WorldServer) {
                GTUtility.update(te);
            }
            cable.notifyBlockUpdate();
        }
    }

    @Override
    public List<Block> getAcceptedBlocks() {
        ArrayList<Block> accepted = new ArrayList<>();
        accepted.add(MetaBlocks.CABLE);
        return accepted;
    }

    @Override
    public float getBreakSpeed() {
        return 80f;
    }
}
