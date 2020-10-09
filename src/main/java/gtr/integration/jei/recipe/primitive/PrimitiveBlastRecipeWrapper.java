package gtr.integration.jei.recipe.primitive;

import com.google.common.collect.ImmutableList;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.recipes.PrimitiveBlastFurnaceRecipe;
import gtr.api.unification.OreDictUnifier;
import gtr.common.metatileentities.multi.MetaTileEntityPrimitiveBlastFurnace;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrimitiveBlastRecipeWrapper implements IRecipeWrapper {

	private final PrimitiveBlastFurnaceRecipe recipe;
	private final List<List<ItemStack>> matchingInputs = new ArrayList<>();
	private final List<List<ItemStack>> outputs = new ArrayList<>();

	public PrimitiveBlastRecipeWrapper(PrimitiveBlastFurnaceRecipe recipe) {
		this.recipe = recipe;
		CountableIngredient ingredient = recipe.getInput();

		List<ItemStack> ingredientValues = Arrays.stream(ingredient.getIngredient().getMatchingStacks())
				.map(ItemStack::copy)
				.sorted(OreDictUnifier.getItemStackComparator())
				.collect(Collectors.toList());
		ingredientValues.forEach(stack -> stack.setCount(ingredient.getCount()));

		this.matchingInputs.add(ingredientValues);

        List<ItemStack> displayFuelStacks = MetaTileEntityPrimitiveBlastFurnace.getDisplayFuelsForRecipe(recipe.getFuelAmount());
		this.matchingInputs.add(displayFuelStacks);

		ItemStack ashesItemStack = MetaTileEntityPrimitiveBlastFurnace.getAshForRecipeFuelConsumption(recipe.getFuelAmount());
		this.outputs.add(ImmutableList.of(recipe.getOutput()));
		this.outputs.add(ImmutableList.of(ashesItemStack));
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, this.matchingInputs);
		ingredients.setOutputLists(ItemStack.class, this.outputs);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRenderer.drawString(I18n.format("gtr.recipe.duration", this.recipe.getDuration() / 20f), 0, 55, 0x111111);
	}

}
