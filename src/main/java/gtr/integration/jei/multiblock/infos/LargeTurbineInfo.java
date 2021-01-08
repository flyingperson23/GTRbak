package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.GTValues;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.api.unification.material.Materials;
import gtr.api.util.BlockInfo;
import gtr.common.blocks.MetaBlocks;
import gtr.common.items.MetaItems;
import gtr.common.items.behaviors.TurbineRotorBehavior;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.common.metatileentities.electric.multiblockpart.MetaTileEntityRotorHolder;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class LargeTurbineInfo extends MultiblockInfoPage {

    public final MetaTileEntityLargeTurbine turbine;

    public LargeTurbineInfo(MetaTileEntityLargeTurbine turbine) {
        this.turbine = turbine;
    }

    @Override
    public MultiblockControllerBase getController() {
        return turbine;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MetaTileEntityHolder holder = new MetaTileEntityHolder();
        holder.setMetaTileEntity(MetaTileEntities.ROTOR_HOLDER[2]);
        holder.getMetaTileEntity().setFrontFacing(EnumFacing.WEST);
        ItemStack rotorStack = MetaItems.TURBINE_ROTOR.getStackForm();
        //noinspection ConstantConditions
        TurbineRotorBehavior.getInstanceFor(rotorStack).setPartMaterial(rotorStack, Materials.Darmstadtium);
        ((MetaTileEntityRotorHolder) holder.getMetaTileEntity()).getRotorInventory().setStackInSlot(0, rotorStack);
        MultiblockShapeInfo.Builder shapeInfo = MultiblockShapeInfo.builder()
            .aisle("CCCC", "CIOC", "CCCC")
            .aisle("CCCC", "R##D", "CCCC")
            .aisle("CCCC", "CSCC", "CCCC")
            .where('S', turbine, EnumFacing.SOUTH)
            .where('C', turbine.turbineType.casingState)
            .where('R', new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), holder))
            .where('D', MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV], EnumFacing.EAST)
            .where('#', Blocks.AIR.getDefaultState())
            .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.NORTH);
        if (turbine.turbineType.hasOutputHatch) {
            shapeInfo.where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.EV], EnumFacing.NORTH);
        } else {
            shapeInfo.where('O', turbine.turbineType.casingState);
        }
        return Lists.newArrayList(shapeInfo.build());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.large_turbine.description")};
    }

}
