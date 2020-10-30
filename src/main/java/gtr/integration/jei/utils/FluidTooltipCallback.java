package gtr.integration.jei.utils;

import java.util.List;
import net.minecraftforge.fluids.FluidStack;
import mezz.jei.api.gui.ITooltipCallback;

public class FluidTooltipCallback implements ITooltipCallback<FluidStack> {
    public void onTooltip(final int slotIndex, final boolean input, final FluidStack ingredient, final List<String> tooltip) {
        if (slotIndex == 0 && !input) {
            tooltip.addAll(new FluidWrapper(ingredient).getTooltip());
        }
    }
}
