package gtr.api.gui.widgets;

import com.google.common.collect.Lists;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.IRenderContext;
import gtr.api.gui.Widget;
import gtr.api.gui.resources.TextureArea;
import gtr.api.util.Position;
import gtr.api.util.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Supplier;

public class ActiveWidget extends Widget {

    private final Supplier<Boolean> isActive;

    public ActiveWidget(int xPosition, int yPosition, int width, int height, Supplier<Boolean> isActive) {
        super(new Position(xPosition, yPosition), new Size(width, height));
        this.isActive = isActive;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        TextureArea toDraw = isActive.get() ? GuiTextures.ACTIVE : GuiTextures.INACTIVE;
        toDraw.draw(getPosition().x, getPosition().y, getSize().width, getSize().height);
    }

    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        super.drawInForeground(mouseX, mouseY);
        if (isMouseOverElement(mouseX, mouseY)) {
            String text = I18n.format(isActive.get() ? "gtr.tooltip.active" : "gtr.tooltip.inactive");
            drawHoveringText(ItemStack.EMPTY, Lists.newArrayList(text), -1, mouseX, mouseY);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

}

