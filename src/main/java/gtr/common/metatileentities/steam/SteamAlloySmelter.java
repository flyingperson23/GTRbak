package gtr.common.metatileentities.steam;

import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
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

public class SteamAlloySmelter extends SteamMetaTileEntity {

    public SteamAlloySmelter(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, RecipeMaps.ALLOY_SMELTER_RECIPES, Textures.ALLOY_SMELTER_OVERLAY, isHighPressure, 0.2);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamAlloySmelter(metaTileEntityId, isHighPressure);
    }

    @Override
    protected boolean isBrickedCasing() {
        return true;
    }

    @Override
    public IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(2);
    }

    @Override
    public IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        TextureArea slotBackground = getFullGuiTexture("slot_%s_furnace_background");
        return createUITemplate(player)
            .widget(new SlotWidget(this.importItems, 0, 60, 25)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE, slotBackground))
            .widget(new SlotWidget(this.importItems, 1, 42, 25)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE, slotBackground))
            .widget(new ProgressWidget(workableHandler::getProgressPercent, 82, 25, 20, 16)
                .setProgressBar(getFullGuiTexture("progress_bar_%s_furnace"),
                    getFullGuiTexture("progress_bar_%s_furnace_filled"),
                    MoveType.HORIZONTAL))
            .widget(new SlotWidget(this.exportItems, 0, 107, 25, true, false)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE))
            .build(getHolder(), player);
    }
}
