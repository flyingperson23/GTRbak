package gtr.common.metatileentities.electric;

import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.SimpleMachineMetaTileEntity;
import gtr.api.recipes.RecipeMap;
import gtr.api.render.OrientedOverlayRenderer;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityMassFab extends SimpleMachineMetaTileEntity {
    public MetaTileEntityMassFab(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, OrientedOverlayRenderer renderer, int tier) {
        super(metaTileEntityId, recipeMap, renderer, tier);
    }

    @Override
    public void update() {
        super.update();
        this.workable.forceRecipeRecheck = true;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityMassFab(metaTileEntityId, workable.recipeMap, renderer, getTier());
    }


}