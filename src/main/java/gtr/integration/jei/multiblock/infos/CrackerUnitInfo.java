package gtr.integration.jei.multiblock.infos;

import com.google.common.collect.Lists;
import gtr.api.GTValues;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMetalCasing;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class CrackerUnitInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.CRACKER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
            .aisle("XCXCX", "XCSCF", "XCXCX")
            .aisle("XCXCX", "H###X", "XCXCX")
            .aisle("XCXCX", "XCECF", "XCXCX")
            .where('S', MetaTileEntities.CRACKER, EnumFacing.NORTH)
            .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
            .where('C', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.CUPRONICKEL))
            .where('#', Blocks.AIR.getDefaultState())
            .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.EAST)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
            .where('H', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
            .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();
        ITextComponent tooltip = new TextComponentTranslation("gtr.multiblock.preview.limit", 20).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN), tooltip);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.cracker.description")};
    }

}
