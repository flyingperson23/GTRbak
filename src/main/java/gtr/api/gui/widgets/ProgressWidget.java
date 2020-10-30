package gtr.api.gui.widgets;

import com.google.common.collect.Lists;
import gtr.api.gui.IRenderContext;
import gtr.api.gui.Widget;
import gtr.api.gui.resources.TextureArea;
import gtr.api.util.Position;
import gtr.api.util.Size;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class ProgressWidget extends Widget {

    public enum MoveType {
        VERTICAL,
        HORIZONTAL,
        VERTICAL_INVERTED
    }

    public final DoubleSupplier progressSupplier;
    private MoveType moveType;
    private TextureArea emptyBarArea;
    private TextureArea filledBarArea;

    private double lastProgressValue;
    private Supplier<List<String>> textSupplier = null;

    public ProgressWidget(DoubleSupplier progressSupplier, int x, int y, int width, int height) {
        super(new Position(x, y), new Size(width, height));
        this.progressSupplier = progressSupplier;
    }

    public ProgressWidget(DoubleSupplier progressSupplier, int x, int y, int width, int height, Supplier<List<String>> textSupplier) {
        this(progressSupplier, x, y, width, height);
        this.textSupplier = textSupplier;
    }

    public ProgressWidget(DoubleSupplier progressSupplier, int x, int y, int width, int height, TextureArea fullImage, MoveType moveType) {
        super(new Position(x, y), new Size(width, height));
        this.progressSupplier = progressSupplier;
        this.emptyBarArea = fullImage.getSubArea(0.0, 0.0, 1.0, 0.5);
        this.filledBarArea = fullImage.getSubArea(0.0, 0.5, 1.0, 0.5);
        this.moveType = moveType;
    }

    public ProgressWidget(DoubleSupplier progressSupplier, int x, int y, int width, int height, TextureArea fullImage, MoveType moveType, Supplier<List<String>> textSupplier) {
        this(progressSupplier, x, y, width, height, fullImage, moveType);
        this.textSupplier = textSupplier;
    }

    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        super.drawInForeground(mouseX, mouseY);
        if (isMouseOverElement(mouseX, mouseY) && textSupplier != null) {
            drawHoveringText(ItemStack.EMPTY, textSupplier.get(), -1, mouseX, mouseY);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public ProgressWidget setProgressBar(TextureArea emptyBarArea, TextureArea filledBarArea, MoveType moveType) {
        this.emptyBarArea = emptyBarArea;
        this.filledBarArea = filledBarArea;
        this.moveType = moveType;
        return this;
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        Position pos = getPosition();
        Size size = getSize();
        if (emptyBarArea != null) {
            emptyBarArea.draw(pos.x, pos.y, size.width, size.height);
        }
        if (filledBarArea != null) {
            //fuck this precision-dependent things, they are so fucking annoying
            if (moveType == MoveType.HORIZONTAL) {
                filledBarArea.drawSubArea(pos.x, pos.y, (int) (size.width * lastProgressValue), size.height,
                    0.0, 0.0, ((int) (size.width * lastProgressValue)) / (size.width * 1.0), 1.0);
            } else if (moveType == MoveType.VERTICAL) {
                int progressValueScaled = (int) (size.height * lastProgressValue);
                filledBarArea.drawSubArea(pos.x, pos.y + size.height - progressValueScaled, size.width, progressValueScaled,
                    0.0, 1.0 - (progressValueScaled / (size.height * 1.0)),
                    1.0, (progressValueScaled / (size.height * 1.0)));
            } else if (moveType == MoveType.VERTICAL_INVERTED) {
                int progressValueScaled = (int) (size.height * lastProgressValue);
                filledBarArea.drawSubArea(pos.x, pos.y, size.width, progressValueScaled,
                    0.0, 0.0,
                    1.0, (progressValueScaled / (size.height * 1.0)));
            }
        }
    }

    @Override
    public void detectAndSendChanges() {
        double actualValue = progressSupplier.getAsDouble();
        if (Math.abs(actualValue - lastProgressValue) > 0.005) {
            this.lastProgressValue = actualValue;
            writeUpdateInfo(0, buffer -> buffer.writeDouble(actualValue));
        }
    }

    @Override
    public void readUpdateInfo(int id, PacketBuffer buffer) {
        if (id == 0) {
            this.lastProgressValue = buffer.readDouble();
        }
    }
}
