package gtr.integration.multi.crafttweaker.gtwrap.impl;

import gtr.api.gui.resources.TextureArea;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.ITextureArea;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class MCTextureArea implements ITextureArea {

    @Nonnull
    private final TextureArea inner;

    public MCTextureArea(@Nonnull TextureArea inner) {
        this.inner = inner;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Nonnull
    @Override
    public TextureArea getInternal() {
        return inner;
    }

    @Override
    public ITextureArea getSubArea(double offsetX, double offsetY, double width, double height) {
        return new MCTextureArea(inner.getSubArea(offsetX, offsetY, width, height));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onStitch(TextureStitchEvent.Pre evt) {
        evt.getMap().registerSprite(inner.imageLocation);
    }

}
