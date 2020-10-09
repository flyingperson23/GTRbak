package gtr.common.metatileentities.steam;

import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.ProgressWidget;
import gtr.api.gui.widgets.ProgressWidget.MoveType;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.SteamMetaTileEntity;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class SteamCompressor extends SteamMetaTileEntity {

    public SteamCompressor(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, RecipeMaps.COMPRESSOR_RECIPES, Textures.COMPRESSOR_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamCompressor(metaTileEntityId, isHighPressure);
    }

    @Override
    public IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    public IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
            .widget(new SlotWidget(this.importItems, 0, 53, 25)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE, getFullGuiTexture("slot_%s_compressor_background")))
            .widget(new ProgressWidget(workableHandler::getProgressPercent, 78, 23, 20, 18)
                .setProgressBar(getFullGuiTexture("progress_bar_%s_compressor"),
                    getFullGuiTexture("progress_bar_%s_compressor_filled"),
                    MoveType.HORIZONTAL))
            .widget(new SlotWidget(this.exportItems, 0, 107, 25, true, false)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE))
            .build(getHolder(), player);
    }
}
