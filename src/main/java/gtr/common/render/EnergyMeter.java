package gtr.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class EnergyMeter {

    final int xsize = 32;
    final int ysize = 8;
    double meterStart, meterLevel;


    public TextureAtlasSprite getTexture() {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/snow");
    }

    public void draw(double xpos, double ypos, double value) {
        InvOverlayRenderer.pushTexture(InvOverlayRenderer.TEXTURE_QUILT);
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LIGHTING_BIT);
        if (Minecraft.isFancyGraphicsEnabled()) {
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        drawFluid(xpos, ypos, value, getTexture());
        drawBar(xpos, ypos, value);
        drawBarEmpty(xpos, ypos);
        GL11.glPopAttrib();
        GL11.glPopAttrib();
        InvOverlayRenderer.popTexture();

        GL11.glLineWidth(0.5f);
        if (value < 0.0001) {
            GL11.glColor4f(1.0f, 0.2f, 0.2f, 1.0f);
        }


    }

    public void drawFluid(double xpos, double ypos, double value, TextureAtlasSprite icon) {
        GL11.glPushMatrix();
        GL11.glScaled(0.5, 0.5, 0.5);

        meterStart = xpos;
        meterLevel = (xpos + xsize * value);

        while (meterStart + 8 < meterLevel) {
            drawIcon(meterStart * 2, ypos * 2, icon, 0, 0, 16, 16);
            meterStart += 8;
        }
        drawIcon(meterStart * 2, ypos * 2, icon, 0, 0, (meterLevel - meterStart) * 2, 16);
        GL11.glPopMatrix();
    }

    public static void drawIcon(double x, double y, TextureAtlasSprite icon, double left, double top, double right, double bottom) {
        TextureAtlasSprite icon1 = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        if (icon != null)
            icon1 = icon;

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LIGHTING_BIT);
        if (Minecraft.isFancyGraphicsEnabled()) {
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }
        GL11.glColor4f(0.6f, 0.1f, 0.9f, 0.7f);

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tess.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        float u1 = icon1.getMinU();
        float v1 = icon1.getMinV();
        float u2 = icon1.getMaxU();
        float v2 = icon1.getMaxV();

        double xoffset1 = left * (u2 - u1) / 16.0f;
        double yoffset1 = top * (v2 - v1) / 16.0f;
        double xoffset2 = right * (u2 - u1) / 16.0f;
        double yoffset2 = bottom * (v2 - v1) / 16.0f;

        bufferBuilder.pos(x + left, y + top, 0);
        bufferBuilder.tex(u1 + xoffset1, v1 + yoffset1);
        bufferBuilder.endVertex();

        bufferBuilder.pos(x + left, y + bottom, 0);
        bufferBuilder.tex(u1 + xoffset1, v1 + yoffset2);
        bufferBuilder.endVertex();

        bufferBuilder.pos(x + right, y + bottom, 0);
        bufferBuilder.tex(u1 + xoffset2, v1 + yoffset2);
        bufferBuilder.endVertex();

        bufferBuilder.pos(x + right, y + top, 0);
        bufferBuilder.tex(u1 + xoffset2, v1 + yoffset1);
        bufferBuilder.endVertex();

        tess.draw();
        GL11.glPopAttrib();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void drawBar(double xpos, double ypos, double value) {
        InvOverlayRenderer.pushTexture("gtr:textures/gui/icon/armor_energy.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(0, 0);
        GL11.glVertex2d(xpos, ypos);
        GL11.glTexCoord2d(0, 1);
        GL11.glVertex2d(xpos + xsize * value, ypos);
        GL11.glTexCoord2d(1, 1);
        GL11.glVertex2d(xpos + xsize * value, ypos + ysize);
        GL11.glTexCoord2d(1, 0);
        GL11.glVertex2d(xpos, ypos + ysize);
        GL11.glEnd();
        InvOverlayRenderer.popTexture();
    }

    public void drawBarEmpty(double xpos, double ypos) {
        InvOverlayRenderer.pushTexture("gtr:textures/gui/icon/armor_energy_empty.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(0, 0);
        GL11.glVertex2d(xpos, ypos);
        GL11.glTexCoord2d(0, 1);
        GL11.glVertex2d(xpos + xsize, ypos);
        GL11.glTexCoord2d(1, 1);
        GL11.glVertex2d(xpos + xsize, ypos + ysize);
        GL11.glTexCoord2d(1, 0);
        GL11.glVertex2d(xpos, ypos + ysize);
        GL11.glEnd();
        InvOverlayRenderer.popTexture();
    }
}