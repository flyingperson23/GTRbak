package gtr.common.asm;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IEnergyContainer;
import ic2.core.block.comp.Energy;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import ic2.core.block.wiring.TileEntityElectricBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class IC2Hooks {
    public static void emitGTEU(TileEntityBaseGenerator generator) {
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));

            if (te != null) {
                IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                if (container != null) {
                    int voltage = (int) Math.pow(2, 3+2*e.getSourceTier());
                    int packets = Math.min(e.getPacketOutput(), (int) e.getEnergy() / voltage);
                    if (packets > 0) {
                        long ampsUsed = container.acceptEnergyFromNetwork(facing.getOpposite(), voltage, packets);
                        for (int i = 0; i < ampsUsed; i++) {
                            e.useEnergy(voltage);
                        }
                    }
                }
            }
        }
    }

    public static void emitGTEU2(TileEntityElectricBlock generator) {
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));

            if (te != null) {
                IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                if (container != null) {
                    int voltage = (int) Math.pow(2, 3+2*e.getSourceTier());
                    int packets = Math.min(e.getPacketOutput(), (int) e.getEnergy() / voltage);
                    if (packets > 0) {
                        long ampsUsed = container.acceptEnergyFromNetwork(facing.getOpposite(), voltage, packets);
                        for (int i = 0; i < ampsUsed; i++) {
                            e.useEnergy(voltage);
                        }
                    }
                }
            }
        }
    }
}
