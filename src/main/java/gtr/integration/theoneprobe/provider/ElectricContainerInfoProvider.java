package gtr.integration.theoneprobe.provider;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.capability.IEnergyContainer;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.common.metatileentities.electric.MetaTileEntityWorldAccelerator;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ElectricContainerInfoProvider extends CapabilityInfoProvider<IEnergyContainer> {

    @Override
    protected Capability<IEnergyContainer> getCapability() {
        return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
    }

    @Override
    public String getID() {
        return "gtr:energy_container_provider";
    }

    @Override
    protected boolean allowDisplaying(IEnergyContainer capability) {
        return !capability.isOneProbeHidden();
    }

    @Override
    protected void addProbeInfo(IEnergyContainer capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        long energyStored = capability.getEnergyStored();
        long maxStorage = capability.getEnergyCapacity();
        if (maxStorage == 0) return; //do not add empty max storage progress bar
        IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        String additionalSpacing = tileEntity.hasCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, sideHit) ? "   " : "";
        horizontalPane.text(TextStyleClass.INFO + "{*gtr.top.energy_stored*} " + additionalSpacing);
        horizontalPane.progress(energyStored, maxStorage, probeInfo.defaultProgressStyle()
            .suffix("/" + maxStorage + " EU")
            .borderColor(0x00000000)
            .backgroundColor(0x00000000)
            .filledColor(0xFFFFE000)
            .alternateFilledColor(0xFFEED000));

        if (tileEntity instanceof MetaTileEntityHolder) {
            if (((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityWorldAccelerator) {
                MetaTileEntityWorldAccelerator accelerator = (MetaTileEntityWorldAccelerator) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity();
                int machines = accelerator.teList.size();

                probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER)).text(TextStyleClass.INFO+"Machines accelerating: "+machines+additionalSpacing);
                probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER)).text(TextStyleClass.INFO+"Range: "+accelerator.range);
                probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER)).text(TextStyleClass.INFO+"Speed: "+accelerator.speed);
                probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER)).text(TextStyleClass.INFO+"Consuming "+(accelerator.teList.size()*accelerator.speed*MetaTileEntityWorldAccelerator.energyPerTEUpdateTick)+" EU/t"+additionalSpacing);
                if (!accelerator.enabled) probeInfo.text(TextStyleClass.INFOIMP + "{*gtr.top.working_disabled*}");
            }
        }
    }

}
