package gtr.api.gui.widgets;

import com.google.common.collect.Lists;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.IRenderContext;
import gtr.api.gui.Widget;
import gtr.api.util.Position;
import gtr.api.util.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class StructureFormedWidget extends Widget {

    private final Supplier<Boolean> isFormed;

    public StructureFormedWidget(int xPosition, int yPosition, int width, int height, Supplier<Boolean> isFormed) {
        super(new Position(xPosition, yPosition), new Size(width, height));
        this.isFormed = isFormed;
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        super.drawInBackground(mouseX, mouseY, context);
        Position position = getPosition();
        Size size = getSize();

        if (isFormed.get()) {
            GuiTextures.CHECK.drawSubArea(position.x, position.y, size.width, size.height, 0.0, 0.0, 1.0, 1.0);
        } else {
            GuiTextures.X.drawSubArea(position.x, position.y, size.width, size.height, 0.0, 0.0, 1.0, 1.0);
        }
    }

    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        super.drawInForeground(mouseX, mouseY);
        if (isMouseOverElement(mouseX, mouseY)) {
            String text = I18n.format(isFormed.get() ? "gtr.tooltip.formed" : "gtr.tooltip.notformed");
            drawHoveringText(ItemStack.EMPTY, Lists.newArrayList(text), -1, mouseX, mouseY);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
