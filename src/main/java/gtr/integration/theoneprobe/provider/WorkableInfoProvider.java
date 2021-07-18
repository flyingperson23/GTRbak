package gtr.integration.theoneprobe.provider;

import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.capability.IWorkable;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class WorkableInfoProvider extends CapabilityInfoProvider<IWorkable> {

    @Override
    protected Capability<IWorkable> getCapability() {
        return GregtechTileCapabilities.CAPABILITY_WORKABLE;
    }

    @Override
    public String getID() {
        return "gtr:workable_provider";
    }

    @Override
    protected void addProbeInfo(IWorkable capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        int currentProgress = capability.getProgress();
        int maxProgress = capability.getMaxProgress();
        if (maxProgress > 0) {
            int progressScaled = (int) Math.floor(currentProgress / (maxProgress * 1.0) * 100);
            IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            horizontalPane.text(TextStyleClass.INFO + "{*gtr.top.progress*} ");
            horizontalPane.progress(progressScaled, 100, probeInfo.defaultProgressStyle()
                .suffix("%")
                .borderColor(0x00000000)
                .backgroundColor(0x00000000)
                .filledColor(0xFF000099)
                .alternateFilledColor(0xFF000077));
        }
    }
}
