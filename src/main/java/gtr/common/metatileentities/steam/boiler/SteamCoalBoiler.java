package gtr.common.metatileentities.steam.boiler;

import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.ProgressWidget;
import gtr.api.gui.widgets.ProgressWidget.MoveType;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.recipes.ModHandler;
import gtr.api.render.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class SteamCoalBoiler extends SteamBoiler {

    public SteamCoalBoiler(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, isHighPressure, Textures.COAL_BOILER_OVERLAY, 150);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamCoalBoiler(metaTileEntityId, isHighPressure);
    }

    @Override
    protected void tryConsumeNewFuel() {
        ItemStack fuelInSlot = importItems.extractItem(0, 1, true);
        if (fuelInSlot.isEmpty()) return;
        int burnTime = TileEntityFurnace.getItemBurnTime(fuelInSlot);
        if (burnTime <= 0) return;
        importItems.extractItem(0, 1, false);
        ItemStack remainderAsh = ModHandler.getBurningFuelRemainder(getWorld().rand, fuelInSlot);
        if (!remainderAsh.isEmpty()) { //we don't care if we can't insert ash - it's chanced anyway
            exportItems.insertItem(0, remainderAsh, false);
        }
        setFuelMaxBurnTime(burnTime);
    }

    @Override
    public IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    public IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(1) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (TileEntityFurnace.getItemBurnTime(stack) <= 0)
                    return stack;
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
            .widget(new SlotWidget(this.importItems, 0, 115, 54)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE, SLOT_FURNACE_BACKGROUND))
            .widget(new SlotWidget(this.exportItems, 0, 115, 18, true, false)
                .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE))
            .widget(new ProgressWidget(this::getFuelLeftPercent, 114, 35, 18, 18)
                .setProgressBar(getGuiTexture("boiler_%s_fuel"),
                    getGuiTexture("boiler_%s_fuel_full"),
                    MoveType.VERTICAL))
            .build(getHolder(), player);
    }
}
