package gtr.api.gui.widgets;

import com.google.common.collect.Lists;
import gtr.api.gui.Widget;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.machines.FuelRecipeMap;
import gtr.api.util.Position;
import gtr.api.util.Size;
import gtr.integration.jei.GTJeiPlugin;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class JeiOpenWidget extends Widget {

    RecipeMap<?> recipes;
    FuelRecipeMap fuelRecipes;

    public JeiOpenWidget(int xPosition, int yPosition, int width, int height, RecipeMap<?> recipes) {
        super(new Position(xPosition, yPosition), new Size(width, height));
        this.recipes = recipes;
    }

    public JeiOpenWidget(int xPosition, int yPosition, int width, int height, FuelRecipeMap fuelRecipes) {
        super(new Position(xPosition, yPosition), new Size(width, height));
        this.fuelRecipes = fuelRecipes;
    }

    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        super.drawInForeground(mouseX, mouseY);
        if (isMouseOverElement(mouseX, mouseY)) {
            drawHoveringText(ItemStack.EMPTY, Lists.newArrayList(I18n.format("gtr.tooltip.recipes")), -1, mouseX, mouseY);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOverElement(mouseX, mouseY)) {
            triggerButton();
            return true;
        }
        return false;
    }

    protected void triggerButton() {
        if (Loader.isModLoaded("jei")) {
            if (recipes != null) GTJeiPlugin.show(recipes);
            else if (fuelRecipes != null) GTJeiPlugin.show(fuelRecipes);
        }
        playButtonClickSound();
    }

}
