package gtr.integration.multi.gregtech.cuberenderer;

import gtr.api.GTValues;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = GTValues.MODID, value = Side.CLIENT)
public class MapHolder {

    public static TextureMap map = null;

    @SubscribeEvent
    public static void postStitch(TextureStitchEvent.Post evt) {
        map = evt.getMap();
    }

}
