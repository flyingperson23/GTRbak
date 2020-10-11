package gtr.integration.jei.utils;

import gtr.api.net.NetworkHandler;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.worldgen.config.OreDepositDefinition;
import gtr.common.blocks.BlockOre;
import gtr.common.blocks.StoneBlock;
import gtr.integration.jei.recipe.primitive.OreByProduct;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.*;

public class OreGenWrapper implements IRecipeWrapper {

    List<List<ItemStack>> ores = new ArrayList<>();
    List<List<FluidStack>> fluids = new ArrayList<>();

    OreDepositDefinition definition;

    public OreGenWrapper(OreDepositDefinition d) {
        this.definition = d;

        Collection<IBlockState> results = d.getBlockFiller().getAllPossibleStates().get(0).getPossibleResults();

        // Ore blocks

        List<Material> mats = new ArrayList<>();

        results.stream().filter(state -> state.getBlock() instanceof BlockOre).map(state -> ((BlockOre) state.getBlock()).material)
            .filter(m -> !mats.contains(m)).forEach(mats::add);

        for (Material m : mats) {
            ArrayList<ItemStack> l = new ArrayList<>();
            for (OrePrefix ore : OreByProduct.ORES) {
                l.add(OreDictUnifier.get(ore, m));
            }
            ores.add(l);
        }

        // Fluids
        List<Fluid> f = new ArrayList<>();
        results.stream().filter(state -> state.getBlock() instanceof IFluidBlock).map(state -> ((IFluidBlock) state.getBlock()).getFluid())
            .filter(fluid -> !f.contains(fluid)).forEach(f::add);

        f.forEach(fluid -> fluids.add(Collections.singletonList(new FluidStack(fluid, 1000))));
    }

    public boolean shouldDelete() {
        return fluids.isEmpty() && ores.isEmpty();
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, ores);
        ingredients.setOutputLists(VanillaTypes.ITEM, ores);

        ingredients.setInputLists(VanillaTypes.FLUID, fluids);
        ingredients.setOutputLists(VanillaTypes.FLUID, fluids);
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
