package gtr.integration.jei.utils;

import gtr.api.worldgen.config.OreDepositDefinition;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class OreGenWrapper implements IRecipeWrapper {

    List<List<ItemStack>> ores = new ArrayList<>();

    OreDepositDefinition definition;

    public OreGenWrapper(Collection<IBlockState> c, OreDepositDefinition definition) {
        this.definition = definition;
        ores.add(new ArrayList<>());

        if (c.size() > 0) {
            c.stream().map(state -> new ItemStack(Item.getItemFromBlock(state.getBlock()))).filter(i -> !ores.get(0).contains(i)).forEach(ores.get(0)::add);
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, ores);
        ingredients.setOutputLists(VanillaTypes.ITEM, ores);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString("Name: "+definition.getDepositName().substring(definition.getDepositName().indexOf('\\')+1, definition.getDepositName().indexOf('.')), 0, 0, 0x777777);
        minecraft.fontRenderer.drawString("Height: "+(definition.getMinimumHeight() < 0 ? 0 : definition.getMinimumHeight())+" < y < "+(definition.getMaximumHeight() > 255 ? 255 : definition.getMaximumHeight()), 0, 10, 0x777777);
        minecraft.fontRenderer.drawString("Weight: "+definition.getWeight(), 0, 20, 0x777777);
        minecraft.fontRenderer.drawString("Density: "+definition.getDensity(), 0, 30, 0x777777);
        String dimName = definition.getDepositName().substring(0, definition.getDepositName().indexOf('\\'));
        minecraft.fontRenderer.drawString("Dimension: "+dimName.substring(0, 1).toUpperCase()+dimName.substring(1), 0, 40, 0x777777);
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }

}
