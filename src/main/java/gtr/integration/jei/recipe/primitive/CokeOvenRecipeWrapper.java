package gtr.integration.jei.recipe.primitive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import codechicken.lib.util.ItemNBTUtils;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.recipes.CokeOvenRecipe;
import gtr.api.recipes.recipes.PrimitiveBlastFurnaceRecipe;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CokeOvenRecipeWrapper implements IRecipeWrapper {

	private final CokeOvenRecipe recipe;
	private final List<List<ItemStack>> matchingInputs = new ArrayList<>();
	private final List<ItemStack> outputs = new ArrayList<>();
	private final List<FluidStack> fluidOutputs = new ArrayList<>();

	public CokeOvenRecipeWrapper(CokeOvenRecipe recipe) {
		this.recipe = recipe;
		CountableIngredient ingredient = recipe.getInput();

		List<ItemStack> ingredientValues = Arrays.stream(ingredient.getIngredient().getMatchingStacks())
				.map(ItemStack::copy)
				.sorted(OreDictUnifier.getItemStackComparator())
				.collect(Collectors.toList());
		ingredientValues.forEach(stack -> stack.setCount(ingredient.getCount()));
        
		this.matchingInputs.add(ingredientValues);
		this.outputs.add(recipe.getOutput());
		this.fluidOutputs.add(recipe.getFluidOutput());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, this.matchingInputs);
		ingredients.setOutputs(ItemStack.class, this.outputs);
		ingredients.setOutputs(FluidStack.class, this.fluidOutputs);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRenderer.drawString(I18n.format("gtr.recipe.duration", this.recipe.getDuration() / 20f), 0, 60, 0x111111);
	}

}
