package gtr.api.gui.widgets;

import gtr.api.gui.IRenderContext;
import gtr.api.gui.Widget;
import gtr.api.util.Position;
import gtr.api.util.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.PacketBuffer;

import java.util.function.Supplier;

public class TextWidget extends Widget {

    protected int color;
    protected Supplier<String> textSupplier;
    protected String lastText = "";

    public TextWidget(int xPosition, int yPosition, int color, Supplier<String> textSupplier) {
        super(new Position(xPosition, yPosition), Size.ZERO);
        this.color = color;
        this.textSupplier = textSupplier;
    }

    public TextWidget(int xPosition, int yPosition, Supplier<String> textSupplier) {
        this(xPosition, yPosition, 0xFFFFFF, textSupplier);
    }

    private void updateSize() {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        int stringWidth = fontRenderer.getStringWidth(lastText);
        setSize(new Size(stringWidth, fontRenderer.FONT_HEIGHT));
        if (uiAccess != null) {
            uiAccess.notifySizeChange();
        }
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        Position position = getPosition();
        fontRenderer.drawString(textSupplier.get(),
            position.x - fontRenderer.getStringWidth(textSupplier.get()) / 2,
            position.y - fontRenderer.FONT_HEIGHT / 2, color);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }

    @Override
    public void detectAndSendChanges() {
        if (!textSupplier.get().equals(lastText)) {
            this.lastText = textSupplier.get();
            writeUpdateInfo(1, buffer -> buffer.writeString(lastText));
        }
    }

    @Override
    public void readUpdateInfo(int id, PacketBuffer buffer) {
        if (id == 1) {
            this.lastText = buffer.readString(Short.MAX_VALUE);
            updateSize();
        }
    }
}
