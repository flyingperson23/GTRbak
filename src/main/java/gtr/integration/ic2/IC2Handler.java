package gtr.integration.ic2;

import gtr.api.GTValues;
import gtr.api.capability.IEnergyContainer;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.Ic2Fluid;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import ic2.core.block.comp.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;

// This class is for all the shit that should only work if IC2 is enabled but I can't put it anywhere else
// because the import would crash it and I have no clue how to use @Optional
public class IC2Handler {
    public static boolean isAcceptable(TileEntity te) {
        if (te instanceof TileEntityBlock) {
            if (((TileEntityBlock) te).hasComponent(Energy.class)) {
                return true;
            }
        }
        if (!te.getBlockType().getTranslationKey().contains("ic2")) return false;
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

    public static boolean canItemReceiveEU(ItemStack s) {
        return s.getItem() instanceof IElectricItem;
    }

    public static long insertEnergyToItem(ItemStack s, IEnergyContainer container, int tier) {
        long requestedPower = (long) Math.min(ElectricItem.manager.getMaxCharge(s) - ElectricItem.manager.getCharge(s), GTValues.V[ElectricItem.manager.getTier(s)]);
        long powerToDeliver = Math.min(requestedPower, Math.min(container.getEnergyStored(), GTValues.V[tier]));
        return (long) ElectricItem.manager.charge(s, powerToDeliver, ElectricItem.manager.getTier(s), true, false);
    }

}
