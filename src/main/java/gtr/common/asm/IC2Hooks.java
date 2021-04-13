package gtr.common.asm;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IEnergyContainer;
import ic2.core.block.comp.Energy;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class IC2Hooks {
    public static void emitGTEU(TileEntityBaseGenerator generator) {
        System.out.println("EMITTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));
            if (te == null) break;
            IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
            if (container != null && container.getEnergyCanBeInserted() >= container.getInputVoltage()) {
                 if (e.getEnergy() >= container.getInputVoltage()) {
                     container.acceptEnergyFromNetwork(facing.getOpposite(), container.getInputVoltage(), 1);
                     e.useEnergy(container.getInputVoltage());
                 }
            }
        }
    }
}
