package gtr.integration.multi.gregtech.tile;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.data.IData;
import crafttweaker.api.formatting.IFormattedText;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtr.api.GTValues;
import gtr.api.capability.impl.EnergyContainerList;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.recipes.Recipe;
import gtr.api.render.ICubeRenderer;
import gtr.integration.multi.crafttweaker.CustomMultiblock;
import gtr.integration.multi.crafttweaker.functions.IDisplayTextFunction;
import gtr.integration.multi.crafttweaker.functions.IFormStructureFunction;
import gtr.integration.multi.crafttweaker.functions.IRecipePredicate;
import gtr.integration.multi.crafttweaker.functions.IRemovalFunction;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCControllerTile;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCPatternMatchContext;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCRecipe;
import gtr.integration.multi.gregtech.MultiblockRegistry;
import gtr.integration.multi.gregtech.recipes.CustomMultiblockRecipeLogic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TileControllerCustom extends RecipeMapMultiblockController {

    public static final String TAG_PERSISTENT = GTValues.MODID + ":persistent";
    public final CustomMultiblock multiblock;
    // remove on error
    private IFormStructureFunction formStructureFunction;
    private IDisplayTextFunction displayTextFunction;
    private IRemovalFunction removalFunction;
    private IRecipePredicate recipePredicate;

    @Nullable
    public IData persistentData;

    public TileControllerCustom(@Nonnull CustomMultiblock multiblock) {
        super(multiblock.loc, multiblock.recipeMap);
        this.multiblock = multiblock;
        this.recipeMapWorkable = new CustomMultiblockRecipeLogic(this,
                multiblock.update,
                multiblock.updateWorktable,
                multiblock.setupRecipe,
                multiblock.completeRecipe
        );
        displayTextFunction = multiblock.displayTextFunction;
        removalFunction = multiblock.removalFunction;
        recipePredicate = multiblock.recipePredicate;
        formStructureFunction = multiblock.formStructureFunction;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if(isStructureFormed() &&
                recipeMapWorkable.isWorkingEnabled() &&
                recipeMapWorkable.isActive()) {
            int eut = recipeMapWorkable.getRecipeEUt();
            if(eut < 0) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.generation_eu", Math.min(-eut, energyContainer.getOutputVoltage())));
            }
        }

        if(displayTextFunction == null) return;

        try {
            List<IFormattedText> added = displayTextFunction.addDisplayText(new MCControllerTile(this));
            if(added != null) {
                for(IFormattedText component : added) {
                    textList.add(new TextComponentString(component.getText()));
                }
            }
        } catch(RuntimeException e) {
            logFailure("displayTextFunction", e);
            displayTextFunction = null;
        }

    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.energyContainer = new EnergyContainerList(
                Stream.of(MultiblockAbility.INPUT_ENERGY,
                        MultiblockAbility.OUTPUT_ENERGY)
                        .map(this::getAbilities)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );

        if(formStructureFunction == null) return;

        try {
            formStructureFunction.formStructure(new MCControllerTile(this), new MCPatternMatchContext(context));
        } catch(RuntimeException e) {
            logFailure("formStructureFunction", e);
            formStructureFunction = null;
        }
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        if(recipePredicate == null) return true;

        try {
            return recipePredicate.test(
                    new MCControllerTile(this),
                    new MCRecipe(recipe),
                    consumeIfSuccess
            );
        } catch(RuntimeException e) {
            logFailure("recipePredicate", e);
            recipePredicate = null;
        }
        return true;
    }

    @Override
    public void onRemoval() {
        super.onRemoval();

        if(removalFunction == null) return;

        try {
            removalFunction.onRemoval(new MCControllerTile(this));
        } catch(RuntimeException e) {
            logFailure("removalFunction", e);
            removalFunction = null;
        }
    }

    private void logFailure(String func, Throwable t) {
        CraftTweakerAPI.logError(String.format("Couldn't run %s function of %s.", func, multiblock), t);
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        int itemInputsCount = (abilities.getOrDefault(MultiblockAbility.IMPORT_ITEMS, Collections.emptyList())).stream()
                .map(IItemHandler.class::cast).mapToInt(IItemHandler::getSlots).sum();
        int fluidInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_FLUIDS, Collections.emptyList()).size();
        return itemInputsCount >= this.recipeMap.getMinInputs()
                && fluidInputsCount >= this.recipeMap.getMinFluidInputs()
                && (multiblock.noEnergy ||
                abilities.containsKey(MultiblockAbility.INPUT_ENERGY) ||
                abilities.containsKey(MultiblockAbility.OUTPUT_ENERGY));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return Objects.requireNonNull(MultiblockRegistry.get(metaTileEntityId)).pattern;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart part) {
        return multiblock.texture;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileControllerCustom(multiblock);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data = super.writeToNBT(data);

        if(persistentData != null)
            data.setTag(TAG_PERSISTENT, CraftTweakerMC.getNBT(persistentData));

        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);

        persistentData = CraftTweakerMC.getIData(data.getTag(TAG_PERSISTENT));
    }

}
