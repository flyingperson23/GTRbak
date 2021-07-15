package gtr.integration.galacticraft;

import micdoodle8.mods.galacticraft.core.energy.tile.EnergyStorage;
import micdoodle8.mods.galacticraft.core.energy.tile.EnergyStorageTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class GCHandler {
    public static boolean isAcceptable(TileEntity te) {
        return te instanceof EnergyStorageTile;
    }

    public static void receiveEnergy(TileEntity te, int joules, EnumFacing directionFrom) {
        if (isAcceptable(te)) {
            EnergyStorageTile e = (EnergyStorageTile) te;
            EnergyStorage storage = e.storage;
            float toBeStored = Math.min(storage.getEnergyStoredGC() + joules, storage.getCapacityGC());
            storage.setEnergyStored(toBeStored);
        }
    }

    public static float getEnergyRequested(TileEntity te) {
        if (isAcceptable(te)) {
            EnergyStorageTile te2 = (EnergyStorageTile) te;
            return te2.getMaxEnergyStoredGC() - te2.getEnergyStoredGC();
        }
        return 0;
    }
}
