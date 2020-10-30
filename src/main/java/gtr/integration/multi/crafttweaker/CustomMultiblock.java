package gtr.integration.multi.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import gtr.api.multiblock.BlockPattern;
import gtr.api.recipes.RecipeMap;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import gtr.integration.multi.crafttweaker.construction.MultiblockBuilder;
import gtr.integration.multi.crafttweaker.functions.*;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCBlockPattern;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCCubeRenderer;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IBlockPattern;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IICubeRenderer;
import gtr.integration.multi.crafttweaker.construction.MultiblockBuilder;
import gtr.integration.multi.gregtech.MultiblockRegistry;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * A representation of a custom GregTech Multiblock.
 *
 * This is best used for easy access to the recipe map.
 *
 * Use this to set any custom working functions.
 *
 * @see MultiblockBuilder
 *
 * @see IUpdateFunction
 * @see IUpdateWorktableFunction
 * @see ISetupRecipeFunction
 * @see ICompleteRecipeFunction
 * @see IRecipePredicate
 */
@ZenClass("mods.gregtech.multiblock.Multiblock")
@ZenRegister
public class CustomMultiblock {

    /**
     * The meta value of the multiblock controller. Set in {@link MultiblockBuilder#start(String, int)}.
     */
    @ZenProperty
    public final int metaId;
    /**
     * The recipe map the multiblock uses. Set in {@link MultiblockBuilder#withRecipeMap(RecipeMap)}.
     */
    @ZenProperty
    public final RecipeMap<?> recipeMap;

    /**
     * Set to true if the multiblock should not check for energy inputs/outputs before forming.
     */
    @ZenProperty
    public boolean noEnergy = false;

    public final ResourceLocation loc;
    public final BlockPattern pattern;
    public final gtr.api.render.ICubeRenderer texture;
    public final List<MultiblockShapeInfo> designs;

    /**
     * The {@link IUpdateFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public IUpdateFunction update;
    /**
     * The {@link IUpdateWorktableFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public IUpdateWorktableFunction updateWorktable;
    /**
     * The {@link ISetupRecipeFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public ISetupRecipeFunction setupRecipe;
    /**
     * The {@link ICompleteRecipeFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public ICompleteRecipeFunction completeRecipe;
    /**
     * The {@link IRecipePredicate} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public IRecipePredicate recipePredicate;
    /**
     * The {@link IRemovalFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public IRemovalFunction removalFunction;
    /**
     * The {@link IDisplayTextFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public IDisplayTextFunction displayTextFunction;
    /**
     * The {@link IFormStructureFunction} this multiblock has.
     *
     * Should be set using the ZenSetter.
     */
    @ZenProperty public IFormStructureFunction formStructureFunction;

    public CustomMultiblock(MultiblockBuilder builder) {
        metaId = builder.metaId;
        loc = builder.loc;
        pattern = builder.pattern;
        recipeMap = builder.recipeMap;
        texture = builder.texture;
        designs = builder.designs;
    }

    /**
     * @return The meta tile entity ID of this multiblock. Set in {@link MultiblockBuilder#start(String, int)}.
     */
    @Nonnull
    @ZenGetter("loc")
    public String getLocation() {
        return loc.toString();
    }

    /**
     * @return The pattern of the multiblock. Set in {@link MultiblockBuilder#withPattern(IBlockPattern)}.
     */
    @Nonnull
    @ZenGetter("pattern")
    public IBlockPattern getPattern() {
        return new MCBlockPattern(pattern);
    }

    /**
     * @return The texture of the multiblock. Optionally set in {@link MultiblockBuilder#withTexture(IICubeRenderer)}.
     */
    @Nonnull
    @ZenGetter("texture")
    public IICubeRenderer getTexture() {
        return new MCCubeRenderer(texture);
    }

    /**
     * Register this multiblock. Calling this more than once will error.
     *
     * @return This multiblock, for convenience.
     */
    @ZenMethod
    public CustomMultiblock register() {
        MultiblockRegistry.registerMultiblock(this);
        CraftTweakerAPI.logInfo(String.format("Registered multiblock: %s, with meta %s", loc, metaId));
        return this;
    }

    @Override
    public String toString() {
        return String.format("CustomMultiblock(%s)", loc);
    }

}
