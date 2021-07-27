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
import gtr.api.unification.material.Materials;
import gtr.common.items.MetaItems;
import gtr.common.items.behaviors.DataBehavior;
import gtr.integration.ic2.IC2Handler;
import ic2.api.crops.CropCard;
import ic2.core.item.ItemCropSeed;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMapCropSynthesizer extends RecipeMap<SimpleRecipeBuilder> {

    public static RecipeMapCropSynthesizer RECIPE_MAP = new RecipeMapCropSynthesizer();

    public static final RecipeMap<SimpleRecipeBuilder> JEI_MAP = new RecipeMap<>("synthesiser_jei", 4, 4, 1, 1, 1, 1, 0, 0, new SimpleRecipeBuilder());


    public RecipeMapCropSynthesizer() {
        super("synthesiser", 4, 4, 1, 1, 1, 1, 0, 0, new SimpleRecipeBuilder(), true);
    }

    @Override
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, int outputFluidTankCapacity, MatchingMode m) {
        Recipe normalRecipe = super.findRecipe(voltage, inputs, fluidInputs, outputFluidTankCapacity, m);
        if (normalRecipe != null || inputs.size() == 0 || inputs.get(0).isEmpty())
            return normalRecipe;

        short species = -1;
        short growth = -1;
        short gain = -1;
        short resistance = -1;
        ItemStack[] inputSticks = new ItemStack[4];
        for(ItemStack input : inputs) { if (input.getItem() instanceof MetaItem) {             MetaItem<?> i = (MetaItem<?>) input.getItem();
                if (i.getItem(input) == MetaItems.DATA_STICK) { if (input.hasTagCompound() && input.getTagCompound().hasKey("data")) {                     short data = input.getTagCompound().getShort("data");
                        DataBehavior.DataType type = DataBehavior.getTypeFromBits(data);
                        short dataBits = DataBehavior.getDataBits(data);
                        switch(type) {
                            case ERROR:
                            case ELEMENT:
                                break;
                            case CROP_SPECIES:
                                inputSticks[0] = input;
                                species = dataBits;
                                break;
                            case CROP_GROWTH:
                                inputSticks[1] = input;
                                growth = dataBits;
                                break;
                            case CROP_GAIN:
                                inputSticks[2] = input;
                                gain = dataBits;
                                break;
                            case CROP_RESISTANCE:
                                inputSticks[3] = input;
                                resistance = dataBits;
                                break;
                        }
                    }
                }
            }
        }
        for (ItemStack s : inputSticks) {
            if (s != null) {
                s.setCount(1);
            }
        }

        if (species != -1 && growth != -1 && gain != -1 && resistance != -1) {
            CropCard crop = IC2Handler.crops_intToCard.get((int) species);
            ItemStack output = ItemCropSeed.generateItemStackFromValues(crop, growth, gain, resistance, 4);

            return this.recipeBuilder().notConsumable(inputSticks[0]).notConsumable(inputSticks[1]).notConsumable(inputSticks[2]).notConsumable(inputSticks[3]).fluidInputs(Materials.UUM.getFluid(500*crop.getProperties().getTier()))
                .outputs(output).EUt(16).duration(160*crop.getProperties().getTier()).build().getResult();
        }

        return null;
    }

    @Override
    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (!isFluid) {
            if (!isOutputs) {
                builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, true)
                    .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.DATA_OVERLAY));
            } else {
                builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, false)
                    .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CROP_OVERLAY));
            }
        } else super.addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, true, isOutputs);
    }

    @Override
    public boolean canInputFluidForce(Fluid fluid) {
        return fluid == Materials.UUM.getMaterialFluid();
    }
}