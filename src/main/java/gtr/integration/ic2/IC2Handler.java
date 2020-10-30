package gtr.integration.ic2;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

// This class is for all the shit that should only work if IC2 is enabled but I can't put it anywhere else
// because the import would crash it and I have no clue how to use @Optional
public class IC2Handler {
    public static boolean isAcceptable(TileEntity te) {
        if (te instanceof TileEntityBlock) {
            if (((TileEntityBlock) te).hasComponent(Energy.class)) {
                return true;
            }
        }
        return te instanceof IEnergySink || te instanceof IEnergySource;
    }

    public static int receiveEnergy(TileEntity te, long voltage, long amperes, EnumFacing directionFrom) {
        if (te instanceof IEnergySink) {
            IEnergySink sink = (IEnergySink) te;
            System.out.println(voltage);
            return (int) ((voltage * amperes) - sink.injectEnergy(directionFrom, voltage * amperes, voltage));
        } else if (te instanceof TileEntityBlock && ((TileEntityBlock) te).hasComponent(Energy.class)) {
            return (int) ((TileEntityBlock) te).getComponent(Energy.class).addEnergy(voltage*amperes);
        } else return 0;
    }

}
