package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class LargeTransformerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.LARGE_TRANSFORMER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("ISO")
            .where('S', MetaTileEntities.LARGE_TRANSFORMER, EnumFacing.SOUTH)
            .where('O', MetaTileEntities.ENERGY_OUTPUT_HATCH[0][0], EnumFacing.EAST)
            .where('I', MetaTileEntities.ENERGY_INPUT_HATCH[0][0], EnumFacing.WEST)
            .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.large_transformer.description")};
    }
}