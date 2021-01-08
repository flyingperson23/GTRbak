package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMultiblockCasing;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class FusionInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.FUSION_REACTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("###############", "######NCN######", "###############")
            .aisle("######DCD######", "####CCcccCC####", "######UCU######")
            .aisle("####CC###CC####", "###sccNCNccs###", "####CC###CC####")
            .aisle("###C#######C###", "##wcnC###Cnce##", "###C#######C###")
            .aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##")
            .aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
            .aisle("#D###########D#", "WcE#########WcE", "#U###########U#")
            .aisle("#C###########C#", "CcC#########CcC", "#C###########C#")
            .aisle("#D###########D#", "WcE#########WcE", "#U###########U#")
            .aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
            .aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##")
            .aisle("###C#######C###", "##wcsC###Csce##", "###C#######C###")
            .aisle("####CC###CC####", "###nccSMSccn###", "####CC###CC####")
            .aisle("######DCD######", "####CCcccCC####", "######UCU######")
            .aisle("###############", "######NCN######", "###############")
            .where('M', MetaTileEntities.FUSION_REACTOR, EnumFacing.NORTH)
            .where('C', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING))
            .where('c', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL))
            .where('W', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.WEST)
            .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.EAST)
            .where('S', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.SOUTH)
            .where('N', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.NORTH)
            .where('w', MetaTileEntities.ENERGY_INPUT_HATCH[3], EnumFacing.WEST)
            .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[3], EnumFacing.EAST)
            .where('s', MetaTileEntities.ENERGY_INPUT_HATCH[3], EnumFacing.SOUTH)
            .where('n', MetaTileEntities.ENERGY_INPUT_HATCH[3], EnumFacing.NORTH)
            .where('U', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.UP)
            .where('D', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.DOWN)
            .where('#', Blocks.AIR.getDefaultState())
            .build();

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.fusion_reactor.description")};
    }
}