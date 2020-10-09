package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.GTValues;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockMultiblockCasing;
import gtr.common.blocks.BlockTurbineCasing;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class DieselEngineInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.DIESEL_ENGINE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("AAA", "ACA", "AAA")
            .aisle("HHH", "HGH", "HHH")
            .aisle("HHH", "FGH", "HHH")
            .aisle("HHH", "HEH", "HHH")
            .where('H', MetaBlocks.METAL_CASING.getState(MetalCasingType.TITANIUM_STABLE))
            .where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX))
            .where('A', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING))
            .where('C', MetaTileEntities.DIESEL_ENGINE, EnumFacing.NORTH)
            .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.EV], EnumFacing.WEST)
            .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV], EnumFacing.SOUTH)
            .where('#', Blocks.AIR.getDefaultState())
            .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.diesel_engine.description")};
    }

}
