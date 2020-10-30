package gtr.common.metatileentities.electric.multiblockpart;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.impl.EnergyContainerHandler;
import gtr.api.gui.ModularUI;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.render.SimpleCubeRenderer;
import gtr.api.render.Textures;
import gtr.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityFusionEnergyInjector extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<IEnergyContainer> {

    private final IEnergyContainer energyContainer;
    public boolean isActive;

    public MetaTileEntityFusionEnergyInjector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTValues.IV);
        this.energyContainer = EnergyContainerHandler.receiverContainer(this, 10000000, GTValues.IV_VOLTAGE, 2);
        this.frontFacing = EnumFacing.NORTH;

    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing wrenchSide, CuboidRayTraceResult hitResult) {
        return false;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityFusionEnergyInjector(metaTileEntityId);
    }

    @Override
    public void update() {
        if (GregTechMod.instance.counter % 3 == 1) updateActive();
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);

        SimpleCubeRenderer renderer = isActive ? Textures.ACTIVE_FUSION_BASE : Textures.FUSION_BASE;
        renderer.render(renderState, translation, pipeline, Cuboid6.full);
    }

    @Override
    public MultiblockAbility<IEnergyContainer> getAbility() {
        return MultiblockAbility.INPUT_ENERGY;
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
        tooltip.add(I18n.format("gtr.universal.tooltip.voltage_in", energyContainer.getInputVoltage(), tierName));
        tooltip.add(I18n.format("gtr.universal.tooltip.amperage_in_till", energyContainer.getInputAmperage()));
        tooltip.add(I18n.format("gtr.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
    }

    private void updateActive() {
        if (getController() instanceof MetaTileEntityFusionReactor) {
            this.isActive = ((MetaTileEntityFusionReactor) getController()).recipeMapWorkable.isActive();
        } else {
            this.isActive = false;
        }
    }
}
