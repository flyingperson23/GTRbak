package gtr.common.metatileentities.electric;

import gtr.api.items.metaitem.MetaItem;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.SimpleMachineMetaTileEntity;
import gtr.api.recipes.RecipeMap;
import gtr.api.render.OrientedOverlayRenderer;
import gtr.common.items.behaviors.DataBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class MetaTileEntityCropSynthesiser extends SimpleMachineMetaTileEntity {
    public MetaTileEntityCropSynthesiser(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, OrientedOverlayRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier);
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCropSynthesiser(metaTileEntityId, workable.recipeMap, renderer, getTier());
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(4) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return (slot + 2 == getTypeStored(stack).ordinal());
            }

            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) return stack;
                return super.insertItem(slot, stack, simulate);
            }
        };

    }

    public DataBehavior.DataType getTypeStored(ItemStack stack) {
        if (stack.getItem() instanceof MetaItem<?>) {
            if (((MetaItem<?>) stack.getItem()).getBehaviours(stack).stream().anyMatch(i -> i instanceof DataBehavior)) {
                if (stack.hasTagCompound() && stack.getTagCompound().hasKey("data")) {
                    return DataBehavior.getTypeFromBits(stack.getTagCompound().getShort("data"));
                }
            }
        }
        return DataBehavior.DataType.ERROR;
    }
}
