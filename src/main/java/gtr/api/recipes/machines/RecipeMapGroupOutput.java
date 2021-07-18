package gtr.api.recipes.machines;

import gtr.api.capability.IMultipleTankHandler;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.ModularUI.Builder;
import gtr.api.gui.widgets.*;
import gtr.api.metatileentity.SimpleMachineMetaTileEntity.RecipeMapWithConfigButton;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.function.DoubleSupplier;

public class RecipeMapGroupOutput extends RecipeMap<SimpleRecipeBuilder> implements RecipeMapWithConfigButton {

    public RecipeMapGroupOutput(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, int amperage, SimpleRecipeBuilder defaultRecipe) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe);
    }

    @Override
    public Builder createJeiUITemplate(IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids) {
        return super.createUITemplate(() -> 0.0, importItems, exportItems, importFluids, exportFluids);
    }

    @Override
    public Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids) {
        return createUITemplate(progressSupplier, importItems, exportItems, importFluids, exportFluids, 0);
    }

    @Override
    public Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new ProgressWidget(progressSupplier, 77, 22 + yOffset, 21, 20, progressBarTexture, moveType));
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        BooleanWrapper booleanWrapper = new BooleanWrapper();
        ServerWidgetGroup itemOutputGroup = createItemOutputWidgetGroup(exportItems, new ServerWidgetGroup(() -> !booleanWrapper.getCurrentMode()), yOffset);
        ServerWidgetGroup fluidOutputGroup = createFluidOutputWidgetGroup(exportFluids, new ServerWidgetGroup(booleanWrapper::getCurrentMode), yOffset);
        builder.widget(itemOutputGroup).widget(fluidOutputGroup);
        ToggleButtonWidget buttonWidget = new ToggleButtonWidget(176 - 7 - 54, 62 + yOffset, 18, 18,
            GuiTextures.BUTTON_SWITCH_VIEW, booleanWrapper::getCurrentMode, booleanWrapper::setCurrentMode)
            .setTooltipText("gtr.gui.toggle_view");
        builder.widget(buttonWidget);
        return builder;
    }

    @Override
    public int getLeftButtonOffset() {
        return 0;
    }

    @Override
    public int getRightButtonOffset() {
        return 18;
    }

    private static class BooleanWrapper {

        private boolean currentMode;

        public boolean getCurrentMode() {
            return currentMode;
        }

        public void setCurrentMode(boolean newMode) {
            this.currentMode = newMode;
        }
    }

    protected ServerWidgetGroup createItemOutputWidgetGroup(IItemHandlerModifiable itemHandler, ServerWidgetGroup widgetGroup) {
        return createItemOutputWidgetGroup(itemHandler, widgetGroup, 0);
    }

    protected ServerWidgetGroup createItemOutputWidgetGroup(IItemHandlerModifiable itemHandler, ServerWidgetGroup widgetGroup, int yOffset) {
        int[] inputSlotGrid = determineSlotsGrid(itemHandler.getSlots());
        int itemSlotsToLeft = inputSlotGrid[0];
        int itemSlotsToDown = inputSlotGrid[1];
        int startInputsX = 106;
        int startInputsY = 32 - (int) (itemSlotsToDown / 2.0 * 18) + yOffset;
        for (int i = 0; i < itemSlotsToDown; i++) {
            for (int j = 0; j < itemSlotsToLeft; j++) {
                int slotIndex = i * itemSlotsToLeft + j;
                int x = startInputsX + 18 * j;
                int y = startInputsY + 18 * i;
                widgetGroup.addWidget(new SlotWidget(itemHandler, slotIndex, x, y, true, false)
                    .setBackgroundTexture(getOverlaysForSlot(true, false, false)));
            }
        }
        return widgetGroup;
    }

    protected ServerWidgetGroup createFluidOutputWidgetGroup(IMultipleTankHandler fluidHandler, ServerWidgetGroup widgetGroup) {
        return createFluidOutputWidgetGroup(fluidHandler, widgetGroup, 0);
    }

    protected ServerWidgetGroup createFluidOutputWidgetGroup(IMultipleTankHandler fluidHandler, ServerWidgetGroup widgetGroup, int yOffset) {
        int[] inputSlotGrid = determineSlotsGrid(fluidHandler.getTanks());
        int itemSlotsToLeft = inputSlotGrid[0];
        int itemSlotsToDown = inputSlotGrid[1];
        int startInputsX = 106;
        int startInputsY = 32 - (int) (itemSlotsToDown / 2.0 * 18) + yOffset;
        for (int i = 0; i < itemSlotsToDown; i++) {
            for (int j = 0; j < itemSlotsToLeft; j++) {
                int slotIndex = i * itemSlotsToLeft + j;
                int x = startInputsX + 18 * j;
                int y = startInputsY + 18 * i;
                widgetGroup.addWidget(new TankWidget(fluidHandler.getTankAt(slotIndex), x, y, 18, 18)
                    .setAlwaysShowFull(true)
                    .setBackgroundTexture(getOverlaysForSlot(true, true, false))
                    .setContainerClicking(true, false));
            }
        }
        return widgetGroup;
    }
}
