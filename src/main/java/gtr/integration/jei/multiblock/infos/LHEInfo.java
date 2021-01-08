package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.GTValues;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockBoilerCasing;
import gtr.common.blocks.BlockMetalCasing;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.common.metatileentities.multi.electric.MetaTileEntityLargeHeatExchanger;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class LHEInfo extends MultiblockInfoPage {

    public final MetaTileEntityLargeHeatExchanger lhe;

    public LHEInfo(MetaTileEntityLargeHeatExchanger boiler) {
        this.lhe = boiler;
    }

    @Override
    public MultiblockControllerBase getController() {
        return lhe;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("ICC", "CCC", "CCC", "CCC")
            .aisle("SCC", "CPC", "CPC", "COC")
            .aisle("ICC", "COC", "CCC", "CCC")
            .where('S', lhe, EnumFacing.WEST)
            .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE))
            .where('C', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE))
            .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.MV], EnumFacing.SOUTH)
            .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.MV], EnumFacing.WEST)
            .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.lhe.description")};
    }

}
