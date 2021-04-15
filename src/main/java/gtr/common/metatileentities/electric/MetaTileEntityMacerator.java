package gtr.common.metatileentities.electric;

import gtr.api.GTValues;
import gtr.api.capability.impl.RecipeLogicEnergy;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.SimpleMachineMetaTileEntity;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import gtr.api.render.OrientedOverlayRenderer;
import gtr.api.util.GTUtility;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class MetaTileEntityMacerator extends SimpleMachineMetaTileEntity {

    private int outputAmount;

    public MetaTileEntityMacerator(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int outputAmount, OrientedOverlayRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier);
        this.outputAmount = outputAmount;
        initializeInventory();
    }

    @Override
    protected RecipeLogicEnergy createWorkable(RecipeMap<?> recipeMap) {
        final RecipeLogicEnergy result = new RecipeLogicEnergy(this, recipeMap, () -> energyContainer) {
            @Override
            protected int getMachineTierForRecipe(Recipe recipe) {
                int tier = GTUtility.getTierByVoltage(getMaxVoltage());
                if (tier > GTValues.MV) {
                    return tier - GTValues.MV;
                }
                return 0;
            }
        };
        result.enableOverclockVoltage();
        return result;
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(outputAmount);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityMacerator(metaTileEntityId, workable.recipeMap, outputAmount, renderer, getTier());
    }
}