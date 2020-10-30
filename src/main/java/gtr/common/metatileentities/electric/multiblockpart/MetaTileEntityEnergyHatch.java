package gtr.common.metatileentities.electric.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.GTValues;
import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.impl.EnergyContainerHandler;
import gtr.api.gui.ModularUI;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.render.SimpleOverlayRenderer;
import gtr.api.render.Textures;
import gtr.api.util.PipelineUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityEnergyHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IEnergyContainer> {

    private final boolean isExportHatch;
    private final IEnergyContainer energyContainer;
    private final int amps;

    public MetaTileEntityEnergyHatch(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch, int amps) {
        super(metaTileEntityId, tier);
        this.isExportHatch = isExportHatch;
        this.amps = amps;
        if (isExportHatch) {
            this.energyContainer = EnergyContainerHandler.emitterContainer(this, GTValues.V[tier] * amps * 2, GTValues.V[tier], amps);
        } else {
            this.energyContainer = EnergyContainerHandler.receiverContainer(this, GTValues.V[tier] * amps * 2, GTValues.V[tier], amps);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityEnergyHatch(metaTileEntityId, getTier(), isExportHatch, amps);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            SimpleOverlayRenderer renderer = isExportHatch ? Textures.ENERGY_OUT_MULTI : Textures.ENERGY_IN_MULTI;
            renderer.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GTValues.VC[getTier()]));
        }
    }

    @Override
    public MultiblockAbility<IEnergyContainer> getAbility() {
        return isExportHatch ? MultiblockAbility.OUTPUT_ENERGY : MultiblockAbility.INPUT_ENERGY;
    }

    @Override
    public void registerAbilities(List<IEnergyContainer> abilityList) {
        abilityList.add(energyContainer);
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        String tierName = GTValues.VN[getTier()];

        if (isExportHatch) {
            tooltip.add(I18n.format("gtr.universal.tooltip.voltage_out", energyContainer.getOutputVoltage(), tierName));
            tooltip.add(I18n.format("gtr.universal.tooltip.amperage_out_till", energyContainer.getOutputAmperage()));
        } else {
            tooltip.add(I18n.format("gtr.universal.tooltip.voltage_in", energyContainer.getInputVoltage(), tierName));
            tooltip.add(I18n.format("gtr.universal.tooltip.amperage_in_till", energyContainer.getInputAmperage()));
        }
        tooltip.add(I18n.format("gtr.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
    }
}
