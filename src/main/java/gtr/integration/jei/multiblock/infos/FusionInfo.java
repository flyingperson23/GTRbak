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
            .where('W', MetaTileEntities.FUSION_MATERIAL_EXTRACTOR, EnumFacing.NORTH)
            .where('E', MetaTileEntities.FUSION_MATERIAL_EXTRACTOR, EnumFacing.NORTH)
            .where('S', MetaTileEntities.FUSION_MATERIAL_EXTRACTOR, EnumFacing.NORTH)
            .where('N', MetaTileEntities.FUSION_MATERIAL_EXTRACTOR, EnumFacing.NORTH)
            .where('w', MetaTileEntities.FUSION_ENERGY_INJECTOR, EnumFacing.NORTH)
            .where('e', MetaTileEntities.FUSION_ENERGY_INJECTOR, EnumFacing.NORTH)
            .where('s', MetaTileEntities.FUSION_ENERGY_INJECTOR, EnumFacing.NORTH)
            .where('n', MetaTileEntities.FUSION_ENERGY_INJECTOR, EnumFacing.NORTH)
            .where('U', MetaTileEntities.FUSION_MATERIAL_INJECTOR, EnumFacing.NORTH)
            .where('D', MetaTileEntities.FUSION_MATERIAL_INJECTOR, EnumFacing.NORTH)
            .where('#', Blocks.AIR.getDefaultState())
            .build();

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.fusion_reactor.description")};
    }
}