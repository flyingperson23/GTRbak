package gtr.common.metatileentities.electric.multiblockpart;


import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.impl.EnergyContainerBatteryHolder;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.metatileentity.MTETrait;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.render.Textures;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.List;

public class MetaTileEntityBatteryHolder extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IEnergyContainer> {

    public static final int inventorySize = 9;
    public final IEnergyContainer energyContainer;

    public MetaTileEntityBatteryHolder(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 0);
        initializeInventory();
        this.energyContainer = new EnergyContainerBatteryHolder(this);
    }


    @Override
    public MultiblockAbility<IEnergyContainer> getAbility() {
        return MultiblockAbility.HOLD_ENERGY;
    }

    @Override
    public void registerAbilities(List<IEnergyContainer> abilityList) {
        abilityList.add(energyContainer);
    }


    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityBatteryHolder(metaTileEntityId);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        Textures.BATTERY_HOLDER.render(renderState, translation, pipeline);
    }

    @Override
    protected boolean shouldUpdate(MTETrait trait) {
        return !(trait instanceof EnergyContainerBatteryHolder);
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing) {
        return true;
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(inventorySize) {
            @Override
            protected void onContentsChanged(int slot) {
                ((EnergyContainerBatteryHolder) energyContainer).notifyEnergyListener(false);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (((EnergyContainerBatteryHolder) energyContainer).getBatteryContainer(stack) == null)
                    return stack; //do not allow to insert non-battery items
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(0);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        this.itemInventory = importItems;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        int rowSize = (int) Math.sqrt(inventorySize);
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.getBackground(this, GuiTextures.BACKGROUND_LOCATION), 176,
            18 + 18 * rowSize + 94)
            .label(10, 5, getMetaFullName());

        for (int y = 0; y < rowSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                int index = y * rowSize + x;
                builder.widget(new SlotWidget(importItems, index, 89 - rowSize * 9 + x * 18, 18 + y * 18, true, true)
                    .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.BATTERY_OVERLAY));
            }
        }
        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 8, 18 + 18 * rowSize + 12);

        return builder.build(getHolder(), entityPlayer);
    }

}
