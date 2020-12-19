package gtr.common.render;

import gtr.api.util.GTUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.util.Stack;

public class InvOverlayRenderer {
    private static String TEXTURE_MAP = "textures/atlas/blocks.png";

    private static final Stack<String> texturestack = new Stack<>();
    public static final String TEXTURE_QUILT = "textures/atlas/blocks.png";

    static {
        new InvOverlayRenderer();
    }

    private InvOverlayRenderer() {
    }

    public static void pushTexture(final String filename) {
        texturestack.push(TEXTURE_MAP);
        TEXTURE_MAP = filename;
        bindTexture(TEXTURE_MAP);
    }

    public static void popTexture() {
        TEXTURE_MAP = texturestack.pop();
        bindTexture(TEXTURE_MAP);
    }

    public static void bindTexture(final String tex) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(tex));
    }

    protected static EnergyMeter energy = null;

    public static void draw(TickEvent.RenderTickEvent event, double current, double max) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().player != null && Minecraft.isGuiEnabled() && Minecraft.getMinecraft().currentScreen == null) {
            ScaledResolution screen = new ScaledResolution(Minecraft.getMinecraft());

            double top = (double) screen.getScaledHeight() / 2.0 - (double) 16;
            double left = screen.getScaledWidth() - 34;

            String currEnergyStr = GTUtility.formatNumberShort(current) + "EU";


            if (max > 0) {
                if (energy == null) {
                    energy = new EnergyMeter();
                }
            } else energy = null;


            double stringX = left - 2;

            if (energy != null) {
                energy.draw(left, top, current / max);


                double val;
                GL11.glPushAttrib(GL11.GL_TEXTURE_BIT);
                val = Minecraft.getMinecraft().fontRenderer.getStringWidth(currEnergyStr);
                GL11.glPopAttrib();


                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(currEnergyStr, (int) (stringX - val), (int) top, -1);
            }
        }
    }
}
