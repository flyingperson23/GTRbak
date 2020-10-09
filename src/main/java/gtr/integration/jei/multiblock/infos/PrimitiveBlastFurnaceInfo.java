package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class PrimitiveBlastFurnaceInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.PRIMITIVE_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("XXX", "XXX", "XXX", "XXX")
            .aisle("XXX", "C#X", "X#X", "X#X")
            .aisle("XXX", "XXX", "XXX", "XXX")
            .where('X', MetaBlocks.METAL_CASING.getState(MetalCasingType.PRIMITIVE_BRICKS))
            .where('C', MetaTileEntities.PRIMITIVE_BLAST_FURNACE, EnumFacing.WEST)
            .where('#', Blocks.AIR.getDefaultState())
            .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.primitive_blast_furnace.description")};
    }

}
