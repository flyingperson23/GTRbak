package gtr.common.metatileentities.electric;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.GTValues;
import gtr.api.capability.impl.EnergyContainerHandler;
import gtr.api.gui.ModularUI;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.TieredMetaTileEntity;
import gtr.api.render.Textures;
import gtr.api.util.PipelineUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityCableDiode extends TieredMetaTileEntity {
    public int amperage;
    public MetaTileEntityCableDiode(ResourceLocation metaTileEntityId, int tier, int amperage) {
        super(metaTileEntityId, tier);
        this.amperage = amperage;
        reinitializeEnergyContainer();

        System.out.println(energyContainer.getEnergyCapacity());
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCableDiode(metaTileEntityId, getTier(), amperage);
    }

    @Override
    protected void reinitializeEnergyContainer() {
        long tierVoltage = GTValues.V[getTier()];
        this.energyContainer = new EnergyContainerHandler(this, tierVoltage * amperage, tierVoltage, amperage, tierVoltage, amperage);
        ((EnergyContainerHandler) this.energyContainer).setSideOutputCondition(s -> s == getFrontFacing());


    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.ENERGY_OUT.renderSided(getFrontFacing(), renderState, translation, PipelineUtil.color(pipeline, GTValues.VC[getTier()]));
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing) {
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        return false;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        String tierName = GTValues.VN[getTier()];

        tooltip.add(I18n.format("gtr.universal.tooltip.voltage_in", energyContainer.getInputVoltage(), tierName));
        tooltip.add(I18n.format("gtr.universal.tooltip.voltage_out", energyContainer.getOutputVoltage(), tierName));
        tooltip.add(I18n.format("gtr.universal.tooltip.amperage", amperage));
    }
}
