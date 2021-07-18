package gtr.integration.jei.multiblock.infos;

import gtr.api.GTValues;
import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.common.blocks.BlockMachineCasing;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class PyrolyseOvenInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return MetaTileEntities.PYROLYSE_OVEN;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            shapeInfo.add(MultiblockShapeInfo.builder()
                .aisle("CCECC", "CCCCC", "CCCCC", "CCCCC")
                .aisle("CHHHC", "C###C", "C###C", "CCCCC")
                .aisle("CHHHC", "C###C", "C###C", "CCCCC")
                .aisle("CHHHC", "C###C", "C###C", "CCCCC")
                .aisle("OISFG", "CCCCC", "CCCCC", "CCCCC")
                .where('C', MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.PYROLYSE))
                .where('S', MetaTileEntities.PYROLYSE_OVEN, EnumFacing.SOUTH)
                .where('H', MetaBlocks.WIRE_COIL.getState(coilType))
                .where('#', Blocks.AIR.getDefaultState())
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV], EnumFacing.NORTH)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .where('G', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .build());
        }
        return shapeInfo;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtr.multiblock.pyrolyze_oven.description")};
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();
        ItemStack coils = MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL);
        ITextComponent tooltip = new TextComponentTranslation("gtr.multiblock.preview.only", coils.getDisplayName()).setStyle(new Style().setColor(TextFormatting.RED));
        addBlockTooltip(coils, tooltip);
    }

}
