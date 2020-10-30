package gtr.common.metatileentities.electric.multiblockpart;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.FluidContainerSlotWidget;
import gtr.api.gui.widgets.ImageWidget;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.gui.widgets.TankWidget;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.render.SimpleCubeRenderer;
import gtr.api.render.Textures;
import gtr.api.util.GTUtility;
import gtr.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityFusionMaterialInjector extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IFluidTank> {

    private static final int INITIAL_INVENTORY_SIZE = 8000;
    private final ItemStackHandler containerInventory;
    public boolean isActive;

    public MetaTileEntityFusionMaterialInjector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTValues.IV);
        this.containerInventory = new ItemStackHandler(2);
        initializeInventory();
        this.frontFacing = EnumFacing.NORTH;
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing wrenchSide, CuboidRayTraceResult hitResult) {
        return false;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityFusionMaterialInjector(metaTileEntityId);
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
        if (!getWorld().isRemote) {
            fillInternalTankFromFluidContainer(containerInventory, containerInventory, 0, 1);
            pullFluidsFromNearbyHandlers(getFrontFacing());
        }
        if (GregTechMod.instance.counter % 3 == 1) updateActive();
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        SimpleCubeRenderer sides = isActive ? Textures.ACTIVE_FUSION_BASE : Textures.FUSION_BASE;
        SimpleCubeRenderer top = Textures.FUSION_SIDE_2;

        for (EnumFacing e : EnumFacing.VALUES) {
            if (GTUtility.isHorizontal(e)) {
                sides.renderSided(e, renderState, translation, pipeline);
            } else {
                top.renderSided(e, renderState, translation, pipeline);
            }
        }
    }

    private int getInventorySize() {
        return INITIAL_INVENTORY_SIZE * (1 << getTier());
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        return new FluidTankList(false, new FluidTank(getInventorySize()));
    }

    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(false);
    }

    @Override
    public MultiblockAbility<IFluidTank> getAbility() {
        return MultiblockAbility.IMPORT_FLUIDS;
    }

    @Override
    public void registerAbilities(List<IFluidTank> abilityList) {
        abilityList.addAll(this.importFluids.getFluidTanks());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return createTankUI((importFluids).getTankAt(0), containerInventory, getMetaFullName(), entityPlayer)
            .build(getHolder(), entityPlayer);
    }

    public ModularUI.Builder createTankUI(IFluidTank fluidTank, IItemHandlerModifiable containerInventory, String title, EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(this);
        builder.image(7, 16, 81, 55, GuiTextures.DISPLAY);
        TankWidget tankWidget = new TankWidget(fluidTank, 69, 52, 18, 18)
            .setHideTooltip(true).setAlwaysShowFull(true);
        builder.widget(tankWidget);
        builder.label(11, 20, "gtr.gui.fluid_amount", 0xFFFFFF);
        builder.dynamicLabel(11, 30, tankWidget::getFormattedFluidAmount, 0xFFFFFF);
        builder.dynamicLabel(11, 40, tankWidget::getFluidLocalizedName, 0xFFFFFF);
        return builder.label(6, 6, title)
            .widget(new FluidContainerSlotWidget(containerInventory, 0, 90, 17, true)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.IN_SLOT_OVERLAY))
            .widget(new ImageWidget(91, 36, 14, 15, GuiTextures.TANK_ICON))
            .widget(new SlotWidget(containerInventory, 1, 90, 54, true, false)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.OUT_SLOT_OVERLAY))
            .bindPlayerInventory(entityPlayer.inventory);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtr.universal.tooltip.fluid_storage_capacity", getInventorySize()));
    }

    private void updateActive() {
        if (getController() instanceof MetaTileEntityFusionReactor) {
            this.isActive = ((MetaTileEntityFusionReactor) getController()).recipeMapWorkable.isActive();
        } else {
            this.isActive = false;
        }
    }
}
