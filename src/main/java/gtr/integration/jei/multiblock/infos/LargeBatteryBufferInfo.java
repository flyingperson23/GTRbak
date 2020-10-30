package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.GTValues;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMultiblockCasing;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class LargeBatteryBufferInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.LARGE_BATTERY_BUFFER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {

        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("CCC", "CHC", "CCC")
            .aisle("CCC", "CAC", "CCC")
            .aisle("CCC", "ISO", "CCC")
            .where('C', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.HIGH_POWER))
            .where('H', MetaTileEntities.BATTERY_HOLDER, EnumFacing.WEST)
            .where('A', Blocks.AIR.getDefaultState())
            .where('I', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.IV][0], EnumFacing.SOUTH)
            .where('O', MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.IV][0], EnumFacing.SOUTH)
            .where('S', MetaTileEntities.LARGE_BATTERY_BUFFER, EnumFacing.SOUTH)
            .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.large_battery_buffer.description")};
    }
}