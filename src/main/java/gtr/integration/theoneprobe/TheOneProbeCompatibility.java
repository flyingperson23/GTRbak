package gtr.integration.theoneprobe;

import gtr.integration.theoneprobe.provider.ControllableInfoProvider;
import gtr.integration.theoneprobe.provider.DebugPipeNetInfoProvider;
import gtr.integration.theoneprobe.provider.ElectricContainerInfoProvider;
import gtr.integration.theoneprobe.provider.WorkableInfoProvider;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TheOneProbeCompatibility {

    public static void registerCompatibility() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new ElectricContainerInfoProvider());
        oneProbe.registerProvider(new WorkableInfoProvider());
        oneProbe.registerProvider(new ControllableInfoProvider());
        oneProbe.registerProvider(new DebugPipeNetInfoProvider());
    }

}
