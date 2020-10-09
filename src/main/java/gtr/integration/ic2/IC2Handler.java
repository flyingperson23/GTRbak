package gtr.integration.ic2;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

// This class is for all the shit that should only work if IC2 is enabled but I can't put it anywhere else
// because the import would crash it and I have no clue how to use @Optional
public class IC2Handler {
    public static boolean isAcceptor(TileEntity te) {
        return te instanceof IEnergySink;
    }

    public static boolean isEmitter(TileEntity te) {
        return te instanceof IEnergySource;
    }

    public static int receiveEnergy(TileEntity te, long voltage, long amperes, EnumFacing directionFrom) {
        IEnergySink sink = (IEnergySink) te;
        return (int) ((voltage*amperes) - sink.injectEnergy(directionFrom, voltage*amperes, voltage));
    }

}
