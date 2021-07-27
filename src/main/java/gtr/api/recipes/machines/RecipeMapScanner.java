package gtr.api.recipes.machines;

import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.recipes.MatchingMode;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import gtr.common.items.behaviors.DataBehavior;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMapScanner extends RecipeMap<SimpleRecipeBuilder> {

    public static RecipeMapScanner RECIPE_MAP = new RecipeMapScanner();

    public static final RecipeMap<SimpleRecipeBuilder> JEI_MAP = new RecipeMap<>("scanner_jei", 1, 2, 1, 1, 0, 1, 0, 0, new SimpleRecipeBuilder());

    public RecipeMapScanner() {
        super("scanner", 1, 2, 1, 1, 0, 1, 0, 0, new SimpleRecipeBuilder(), true);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        Recipe normalRecipe = super.findRecipe(voltage, inputs, fluidInputs, outputFluidTankCapacity, m);
        if (normalRecipe != null && !normalRecipe.getOutputs().isEmpty()) return normalRecipe;
        if (inputs.size() >= 2) {
            if (isDataOrb(inputs.get(0)) && isDataOrb(inputs.get(1))) {
                ItemStack i = inputs.get(1);
                return this.recipeBuilder().duration(80).EUt(24).inputs(inputs.get(0)).notConsumable(i).outputs(i).build().getResult();
            }
        }
        if (Loader.isModLoaded("ic2")) {
            return IC2Handler.getRecipe(this, voltage, inputs, fluidInputs, outputFluidTankCapacity, m);
        }
        return null;
    }

    private boolean isDataOrb(ItemStack stack) {
        if (stack == null) return false;
        if (stack.getItem() instanceof MetaItem<?>) {
            return ((MetaItem<?>) stack.getItem()).getBehaviours(stack).stream().anyMatch(i -> i instanceof DataBehavior);
        }
        return false;
    }

    @Override
    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (!isFluid && !isOutputs) {
            switch (slotIndex) {
                case 0:
                    builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY));
                    break;
                case 1:
                    builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.SCANNER_OVERLAY));
                    break;
            }
        } else super.addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, isFluid, isOutputs);
    }
}