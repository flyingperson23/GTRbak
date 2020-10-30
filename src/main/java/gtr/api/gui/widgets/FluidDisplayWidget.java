package gtr.api.gui.widgets;

import com.google.common.collect.Lists;
import gtr.api.gui.IRenderContext;
import gtr.api.gui.Widget;
import gtr.api.gui.igredient.IIngredientSlot;
import gtr.api.gui.resources.RenderUtil;
import gtr.api.util.Position;
import gtr.api.util.Size;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

public class FluidDisplayWidget extends Widget implements IIngredientSlot {

    private final Supplier<FluidStack> fluidStackSupplier;


    public FluidDisplayWidget(int xPosition, int yPosition, int width, int height, Supplier<FluidStack> fluidStackSupplier) {
        super(new Position(xPosition, yPosition), new Size(width, height));
        this.fluidStackSupplier = fluidStackSupplier;
    }

    @Override
    public Object getIngredientOverMouse(int mouseX, int mouseY) {
        if (isMouseOverElement(mouseX, mouseY)) {
            return fluidStackSupplier == null ? null : fluidStackSupplier.get();
        }
        return null;
    }

    @Override
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        super.drawInBackground(mouseX, mouseY, context);
        if (fluidStackSupplier != null) {
            FluidStack lastFluidStack = fluidStackSupplier.get();
            Position pos = getPosition();
            Size size = getSize();
            if (lastFluidStack != null) {
                GlStateManager.disableBlend();
                RenderUtil.drawFluidForGui(lastFluidStack, lastFluidStack.amount, pos.x + 1, pos.y + 1, size.width - 1, size.height - 1);
                GlStateManager.enableBlend();
                GlStateManager.color(1.0f, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        super.drawInForeground(mouseX, mouseY);
        if (fluidStackSupplier != null) {
            FluidStack lastFluidStack = fluidStackSupplier.get();
            if (isMouseOverElement(mouseX, mouseY)) {
                if (lastFluidStack != null) {
                    String fluidName = lastFluidStack.getLocalizedName();
                    drawHoveringText(ItemStack.EMPTY, Lists.newArrayList(fluidName), -1, mouseX, mouseY);
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

                }
            }
        }
    }
}
