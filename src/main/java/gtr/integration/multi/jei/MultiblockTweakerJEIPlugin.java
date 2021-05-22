package gtr.integration.multi.jei;

import gtr.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import gtr.integration.multi.crafttweaker.CustomMultiblock;
import gtr.integration.multi.gregtech.MultiblockRegistry;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class MultiblockTweakerJEIPlugin implements IModPlugin {

    public static IJeiRuntime runtime;

    @Override
    public void register(@Nonnull IModRegistry registry) {
        List<MultiblockInfoRecipeWrapper> recipeList = new ArrayList<>();
        for(CustomMultiblock customMultiblock : MultiblockRegistry.getAll()) {
            if(!customMultiblock.designs.isEmpty()) {
                recipeList.add(new MultiblockInfoRecipeWrapper(new CustomInfoPage(customMultiblock)));
            }
        }

        registry.addRecipes(recipeList, "gtr:multiblock_info");
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
    }

}
