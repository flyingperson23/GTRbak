package gtr.loaders.recipe.magnetic;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class MagneticRecipeMaker {

    public static List<MagneticRecipeWrapper> getMagneticRecipes() {
        List<MagneticRecipeWrapper> recipes = new ArrayList<>();
        for (Item item : GameRegistry.findRegistry(Item.class).getValues()) {
            if (item instanceof ItemTool) {
                MagneticRecipeWrapper recipe = new MagneticRecipeWrapper((ItemTool) item);
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    private MagneticRecipeMaker() {

    }

}
