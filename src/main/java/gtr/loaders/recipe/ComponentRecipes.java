package gtr.loaders.recipe;

import gtr.api.GTValues;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;

import static gtr.common.items.MetaItems.FLUID_REGULATORS;
import static gtr.common.items.MetaItems.PUMPS;

public class ComponentRecipes {
    public static void init() {
        Material[] circuitTiers = new Material[] {MarkerMaterials.Tier.Basic, MarkerMaterials.Tier.Good, MarkerMaterials.Tier.Advanced, MarkerMaterials.Tier.Elite, MarkerMaterials.Tier.Ultimate};

        for (int i = 0; i < circuitTiers.length; i++) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(PUMPS[i].getStackForm())
                .input(OrePrefix.circuit, circuitTiers[i], 2)
                .outputs(FLUID_REGULATORS[i].getStackForm())
                .EUt((int) (GTValues.V[i] * 30 / 32))
                .duration(100)
                .buildAndRegister();
        }
    }
}
