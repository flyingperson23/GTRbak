package gtr.common.metatileentities.steam.boiler;

import gtr.api.capability.impl.FilteredFluidHandler;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.TankWidget;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.recipes.ModHandler;
import gtr.api.render.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

public class SteamLavaBoiler extends SteamBoiler {

    private FluidTank lavaFluidTank;

    public SteamLavaBoiler(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, isHighPressure, Textures.LAVA_BOILER_OVERLAY, 100);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamLavaBoiler(metaTileEntityId, isHighPressure);
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        FluidTankList superHandler = super.createImportFluidHandler();
        this.lavaFluidTank = new FilteredFluidHandler(16000)
            .setFillPredicate(ModHandler::isLava);
        return new FluidTankList(false, superHandler, lavaFluidTank);

    }

    public static final int LAVA_PER_OPERATION = 100;

    @Override
    protected void tryConsumeNewFuel() {
        if (lavaFluidTank.getFluidAmount() >= LAVA_PER_OPERATION) {
            lavaFluidTank.drain(LAVA_PER_OPERATION, true);
            setFuelMaxBurnTime(LAVA_PER_OPERATION);
        }
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return createUITemplate(entityPlayer)
            .widget(new TankWidget(lavaFluidTank, 108, 17, 11, 55)
                .setBackgroundTexture(getGuiTexture("bar_%s_empty")))
            .build(getHolder(), entityPlayer);
    }
}
