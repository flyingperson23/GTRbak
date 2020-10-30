package gtr.integration.jei.multiblock.infos;

import gtr.api.GTValues;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockWireCoil.CoilType;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class ElectricBlastFurnaceInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.ELECTRIC_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (CoilType coilType : CoilType.values()) {
            shapeInfo.add(MultiblockShapeInfo.builder()
                .aisle("IFX", "CCC", "CCC", "XXX")
                .aisle("SXE", "C#C", "C#C", "XXX")
                .aisle("ODX", "CCC", "CCC", "XXX")
                .where('X', MetaBlocks.METAL_CASING.getState(MetalCasingType.INVAR_HEATPROOF))
                .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                .where('S', MetaTileEntities.ELECTRIC_BLAST_FURNACE, EnumFacing.WEST)

                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.MV][0], EnumFacing.EAST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST)

                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.NORTH)
                .where('D', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)


                .where('#', Blocks.AIR.getDefaultState())
                .build());
        }
        return shapeInfo;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.electric_blast_furnace.description")};
    }

}
