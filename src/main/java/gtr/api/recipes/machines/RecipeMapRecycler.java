package gtr.api.recipes.machines;

import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.recipes.MatchingMode;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import gtr.common.items.MetaItems;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMapRecycler extends RecipeMap<SimpleRecipeBuilder> {

    public static RecipeMapRecycler RECIPE_MAP = new RecipeMapRecycler();

    public static final RecipeMap<SimpleRecipeBuilder> JEI_MAP = new RecipeMap<>("recycler_jei", 1, 1, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder());

    public RecipeMapRecycler() {
        super("recycler", 1, 1, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder(), true);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        if (inputs.size() > 0) {
            ItemStack s = inputs.get(0).copy();
            s.setCount(1);
            return this.recipeBuilder().inputs(s).chancedOutput(getScrap(), 1250, 1000)
                .duration(40).EUt(1).build().getResult();
        }
        return null;
    }

    @Override
    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (!isOutputs) {
            builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.RECYCLER_OVERLAY));
        } else super.addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, isFluid, true);
    }

    public static ItemStack getScrap() {
        if (Loader.isModLoaded("ic2")) return IC2Handler.getScrap();
        return MetaItems.SCRAP.getStackForm();
    }

    public static ItemStack getScrapBox() {
        if (Loader.isModLoaded("ic2")) return IC2Handler.getScrapBox();
        return MetaItems.SCRAP_BOX.getStackForm();
    }
}