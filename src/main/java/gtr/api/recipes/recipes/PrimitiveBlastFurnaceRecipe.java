package gtr.api.recipes.recipes;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtr.api.GTValues;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.crafttweaker.InputIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Method;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;

@ZenClass("mods.gtr.recipe.PrimitiveBlastFurnaceRecipe")
@ZenRegister
public class PrimitiveBlastFurnaceRecipe {

    private final CountableIngredient input;
    private final ItemStack output;

    private final int duration;
    private final int fuelAmount;

    public PrimitiveBlastFurnaceRecipe(CountableIngredient input, ItemStack output, int duration, int fuelAmount) {
        this.input = input;
        this.output = output;
        this.duration = duration;
        this.fuelAmount = fuelAmount;
    }

    public CountableIngredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    @ZenGetter("duration")
    public int getDuration() {
        return duration;
    }

    @ZenGetter("fuelAmount")
    public int getFuelAmount() {
        return fuelAmount;
    }

    @ZenGetter("input")
    @Method(modid = GTValues.MODID_CT)
    public InputIngredient ctGetInput() {
        return new InputIngredient(getInput());
    }

    @ZenGetter("output")
    @Method(modid = GTValues.MODID_CT)
    public IItemStack ctGetOutput() {
        return CraftTweakerMC.getIItemStack(getOutput());
    }

}
