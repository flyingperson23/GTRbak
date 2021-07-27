package gtr.api.recipes.machines;

import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.recipes.MatchingMode;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import gtr.api.unification.material.Materials;
import ic2.api.crops.ICropSeed;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMapCropReplicator extends RecipeMap<SimpleRecipeBuilder> {

    public static RecipeMapCropReplicator RECIPE_MAP = new RecipeMapCropReplicator();

    public static final RecipeMap<SimpleRecipeBuilder> JEI_MAP = new RecipeMap<>("crop_replicator_jei", 1, 1, 1, 1, 1, 1, 0, 0, new SimpleRecipeBuilder());


    public RecipeMapCropReplicator() {
        super("crop_replicator", 1, 1, 1, 1, 1, 1, 0, 0, new SimpleRecipeBuilder(), true);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        ItemStack stack = inputs.get(0);
        ItemStack input = stack.copy();
        input.setCount(1);
        if (stack.getItem() instanceof ICropSeed) {
            int tier = ((ICropSeed) stack.getItem()).getCropFromStack(stack).getProperties().getTier();
            return this.recipeBuilder().notConsumable(input).outputs(input).fluidInputs(Materials.UUM.getFluid(1000*tier)).duration(80*tier).EUt(16).build().getResult();
        }
        return null;
    }

    @Override
    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (isFluid) {
            super.addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, true, isOutputs);
        } else {
            builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CROP_OVERLAY));
        }
    }

    @Override
    public boolean canInputFluidForce(Fluid fluid) {
        return fluid == Materials.UUM.getMaterialFluid();
    }
}