package gtr.api.metatileentity;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.GTValues;
import gtr.api.capability.impl.EnergyContainerHandler;
import gtr.api.capability.impl.FilteredFluidHandler;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.ModularUI.Builder;
import gtr.api.gui.widgets.FluidContainerSlotWidget;
import gtr.api.gui.widgets.ImageWidget;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.gui.widgets.TankWidget;
import gtr.api.recipes.machines.FuelRecipeMap;
import gtr.api.render.OrientedOverlayRenderer;
import gtr.api.render.Textures;
import gtr.api.util.PipelineUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public class SimpleGeneratorMetaTileEntity extends TieredMetaTileEntity {

    private FuelRecipeLogic workableHandler;
    private ItemStackHandler containerInventory;
    private OrientedOverlayRenderer overlayRenderer;
    private FuelRecipeMap recipeMap;

    public SimpleGeneratorMetaTileEntity(ResourceLocation metaTileEntityId, FuelRecipeMap recipeMap, OrientedOverlayRenderer renderer, int tier) {
        super(metaTileEntityId, tier);
        this.containerInventory = new ItemStackHandler(2);
        this.overlayRenderer = renderer;
        this.recipeMap = recipeMap;
        this.workableHandler = createWorkableHandler();
    }

    protected FuelRecipeLogic createWorkableHandler() {
        return new FuelRecipeLogic(this, recipeMap,
            () -> energyContainer, () -> importFluids, GTValues.V[getTier()]);
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        return new FluidTankList(false, new FilteredFluidHandler(16000)
            .setFillPredicate(this::canInputFluid));
    }

    private boolean canInputFluid(FluidStack fluid) {
        return workableHandler.recipeMap.findRecipe(GTValues.V[getTier()], fluid) != null;
    }

    @Override
    protected boolean isEnergyEmitter() {
        return true;
    }

    @Override
    protected void reinitializeEnergyContainer() {
        super.reinitializeEnergyContainer();
        ((EnergyContainerHandler) this.energyContainer).setSideOutputCondition(side -> side == getFrontFacing());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.overlayRenderer.render(renderState, translation, pipeline, getFrontFacing(), workableHandler.isActive());
        Textures.ENERGY_OUT.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GTValues.VC[getTier()]));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("ContainerInventory", containerInventory.serializeNBT());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.containerInventory.deserializeNBT(data.getCompoundTag("ContainerInventory"));
    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        super.clearMachineInventory(itemBuffer);
        clearInventory(itemBuffer, containerInventory);
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote && getTimer() % 5 == 0) {
            fillInternalTankFromFluidContainer(containerInventory, containerInventory, 0, 1);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SimpleGeneratorMetaTileEntity(metaTileEntityId, workableHandler.recipeMap, overlayRenderer, getTier());
    }

    protected ModularUI.Builder createGuiTemplate(EntityPlayer player) {
        Builder builder = ModularUI.defaultBuilder();
        builder.image(7, 16, 81, 55, GuiTextures.DISPLAY);
        TankWidget tankWidget = new TankWidget(importFluids.getTankAt(0), 69, 52, 18, 18)
            .setHideTooltip(true).setAlwaysShowFull(true);
        builder.widget(tankWidget);
        builder.label(11, 20, "gtr.gui.fluid_amount", 0xFFFFFF);
        builder.dynamicLabel(11, 30, tankWidget::getFormattedFluidAmount, 0xFFFFFF);
        builder.dynamicLabel(11, 40, tankWidget::getFluidLocalizedName, 0xFFFFFF);
        return builder.label(6, 6, getMetaFullName())
            .widget(new FluidContainerSlotWidget(containerInventory, 0, 90, 17, true)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.IN_SLOT_OVERLAY))
            .widget(new ImageWidget(91, 36, 14, 15, GuiTextures.TANK_ICON))
            .widget(new SlotWidget(containerInventory, 1, 90, 54, true, false)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
            .bindPlayerInventory(player.inventory);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return createGuiTemplate(entityPlayer).build(getHolder(), entityPlayer);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtr.universal.tooltip.voltage_out", energyContainer.getOutputVoltage(), GTValues.VN[getTier()]));
        tooltip.add(I18n.format("gtr.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
    }
}
