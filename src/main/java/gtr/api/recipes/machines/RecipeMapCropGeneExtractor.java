package gtr.api.recipes.machines;

import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.widgets.SlotWidget;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.MatchingMode;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import gtr.api.recipes.ingredients.IntCircuitIngredient;
import gtr.api.util.GTUtility;
import gtr.common.items.behaviors.DataBehavior;
import gtr.common.items.behaviors.IntCircuitBehaviour;
import ic2.api.crops.ICropSeed;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeMapCropGeneExtractor extends RecipeMap<SimpleRecipeBuilder> {

    public static RecipeMapCropGeneExtractor RECIPE_MAP = new RecipeMapCropGeneExtractor();

    public static final RecipeMap<SimpleRecipeBuilder> JEI_MAP = new RecipeMap<>("gene_extractor_jei", 2, 3, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder());


    public RecipeMapCropGeneExtractor() {
        super("gene_extractor", 3, 3, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder(), true);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        Recipe normalRecipe = super.findRecipe(voltage, inputs, fluidInputs, outputFluidTankCapacity, m);
        if (normalRecipe != null || inputs.size() == 0 || inputs.get(0).isEmpty())
            return normalRecipe;

        ItemStack circuit = null;
        ItemStack crop = null;
        ItemStack orb = null;

        for(ItemStack input : inputs) {

            if (input.getItem() instanceof MetaItem) {
                List<IItemBehaviour> behaviours = ((MetaItem<?>) input.getItem()).getBehaviours(input);
                List<DataBehavior> data = behaviours.stream().filter(i -> i instanceof DataBehavior).map(i -> (DataBehavior) i).collect(Collectors.toList());
                List<IntCircuitBehaviour> circuits = behaviours.stream().filter(i -> i instanceof IntCircuitBehaviour).map(i -> (IntCircuitBehaviour) i).collect(Collectors.toList());
                if (!data.isEmpty()) {
                    orb = input;
                }
                if (!circuits.isEmpty()) {
                    if (IntCircuitIngredient.getCircuitConfiguration(input) >= 0 && IntCircuitIngredient.getCircuitConfiguration(input) < 4) circuit = input;
                }
            }
            if (input.getItem() instanceof ICropSeed) {
                crop = input;
            }
        }
        if (orb != null) orb.setCount(1);

        if (crop != null && orb != null && circuit != null) {
            ItemStack output = GTUtility.copy(orb);
            int circuitNum = IntCircuitIngredient.getCircuitConfiguration(circuit);
            DataBehavior.DataType type;
            if (circuitNum >= 0 && circuitNum < 4) type = DataBehavior.DataType.values()[circuitNum + 2];
            else type = DataBehavior.DataType.ERROR;
            short full_data = DataBehavior.combine(DataBehavior.getData(crop, type), type);
            int tier = ((ICropSeed) crop.getItem()).getCropFromStack(crop).getProperties().getTier();
            crop.setCount(1);
            NBTTagCompound compound = new NBTTagCompound();
            compound.setShort("data", full_data);
            output.setTagCompound(compound);
            return this.recipeBuilder().inputs(orb).inputs(crop)
                .notConsumable(circuit).outputs(output).duration(80*tier).EUt(16).build().getResult();
        }

        return null;
    }

    @Override
    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (!isOutputs) {
            switch (slotIndex) {
                case 0:
                    builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CROP_OVERLAY));
                    break;
                case 1:
                    builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.DATA_OVERLAY));
                    break;
                case 2:
                    builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY));
                    break;
            }
        } else {
            builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, false)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CROP_OVERLAY));
        }
    }
}
