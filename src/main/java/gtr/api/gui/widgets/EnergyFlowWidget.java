package gtr.api.gui.widgets;

import gtr.GregTechMod;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.gui.IRenderContext;
import gtr.api.gui.Widget;
import gtr.api.gui.resources.TextureArea;
import gtr.api.util.Position;
import gtr.api.util.Size;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Supplier;

public class EnergyFlowWidget extends Widget {

    private final Supplier<FuelRecipeLogic> workableSupplier;
    private final Supplier<Boolean> isFormed;

    public EnergyFlowWidget(int xPosition, int yPosition, int width, int height, Supplier<FuelRecipeLogic> workableSupplier, Supplier<Boolean> isFormed) {
        super(new Position(xPosition, yPosition), new Size(width, height));
        this.workableSupplier = workableSupplier;
        this.isFormed = isFormed;
    }

    public EnergyFlowWidget(int xPosition, int yPosition, Supplier<FuelRecipeLogic> workable, Supplier<Boolean> isFormed) {
        this(xPosition, yPosition, 96, 16, workable, isFormed);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawInBackground(int mouseX, int mouseY, IRenderContext context) {
        Position position = getPosition();
        Size size = getSize();
        int counter = (GregTechMod.instance.counter/4)%6;
        counter++;
        TextureArea t;
        if (isFormed.get()) {
            if (!workableSupplier.get().isWorkingEnabled()) {
                t = TextureArea.fullImage("textures/gui/icon/energyflow_0.png");
            } else if (workableSupplier.get().isActive()) {
                t = TextureArea.fullImage("textures/gui/icon/energyflow_" + counter + ".png");
            } else {
                t = TextureArea.fullImage("textures/gui/icon/energyflow_off.png");
            }
        } else {
            t = TextureArea.fullImage("textures/gui/icon/energyflow_off.png");
        }
        t.draw(position.x, position.y, size.width, size.height);
    }


}

