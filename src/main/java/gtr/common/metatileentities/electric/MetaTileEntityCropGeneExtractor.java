package gtr.common.metatileentities.electric;

import gtr.api.items.metaitem.MetaItem;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.SimpleMachineMetaTileEntity;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.ingredients.IntCircuitIngredient;
import gtr.api.render.OrientedOverlayRenderer;
import gtr.common.items.behaviors.DataBehavior;
import gtr.common.items.behaviors.IntCircuitBehaviour;
import ic2.api.crops.ICropSeed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class MetaTileEntityCropGeneExtractor extends SimpleMachineMetaTileEntity {
    public MetaTileEntityCropGeneExtractor(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, OrientedOverlayRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCropGeneExtractor(metaTileEntityId, workable.recipeMap, renderer, getTier());
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(3) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return (slot == 0 && isCrop(stack)) || (slot == 1 && isDataOrb(stack)) || (slot == 2 && isIntegratedCircuit(stack));
            }

            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) return stack;
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private boolean isIntegratedCircuit(ItemStack stack) {
        if (stack == null) return false;
        if (stack.getItem() instanceof MetaItem<?>) {
            if  (((MetaItem<?>) stack.getItem()).getBehaviours(stack).stream().anyMatch(i -> i instanceof IntCircuitBehaviour)) {
                return IntCircuitIngredient.getCircuitConfiguration(stack) >= 0 && IntCircuitIngredient.getCircuitConfiguration(stack) < 4;
            }
        }
        return false;
    }

    private boolean isDataOrb(ItemStack stack) {
        if (stack == null) return false;
        if (stack.getItem() instanceof MetaItem<?>) {
            return ((MetaItem<?>) stack.getItem()).getBehaviours(stack).stream().anyMatch(i -> i instanceof DataBehavior);
        }
        return false;
    }

    private boolean isCrop(ItemStack stack) {
        return stack.getItem() instanceof ICropSeed;
    }
}
