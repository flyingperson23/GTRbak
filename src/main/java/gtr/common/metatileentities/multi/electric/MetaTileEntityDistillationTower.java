package gtr.common.metatileentities.multi.electric;

import gtr.GregTechMod;
import gtr.api.gui.IUIHolder;
import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
import gtr.api.gui.widgets.*;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.BlockWorldState;
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.net.displayrecipes.MessageRequestRecipeMultiblock;
import gtr.api.net.displayrecipes.MessageSetRecipeMultiblock;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

import static gtr.api.multiblock.BlockPattern.RelativeDirection.*;

public class MetaTileEntityDistillationTower extends RecipeMapMultiblockController implements IUIHolder {

    public MetaTileEntityDistillationTower(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.DISTILLATION_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityDistillationTower(metaTileEntityId);
    }

    @Override
    public void update() {
        super.update();
        if (GregTechMod.instance.counter % 7 == 3 && !isRemote() && recipeMapWorkable.previousRecipe != null) {
            GregTechMod.DISPLAY_INFO_WRAPPER.sendToAll(new MessageSetRecipeMultiblock(getPos(), recipeMapWorkable.previousRecipe, recipeMap));
        }
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        GregTechMod.DISPLAY_INFO_WRAPPER.sendToServer(new MessageRequestRecipeMultiblock(getPos()));

        JeiOpenWidget recipes = new JeiOpenWidget(130, 59, 18, 18, this.recipeMap);

        StructureFormedWidget structureFormed = new StructureFormedWidget(132, 8, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(131, 42, 16, 16, () -> isStructureFormed() && recipeMapWorkable.isActive() && recipeMapWorkable.isWorkingEnabled());

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        FluidDisplayWidget fluidDisplay = null;
        if (displayRecipe != null && displayRecipe.getFluidInputs().size() > 0) {
            fluidDisplay = new FluidDisplayWidget(131, 24, 18, 18, () -> displayRecipe.getFluidInputs().get(0));
        }

        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 166, TextureArea.fullImage("textures/gui/multiblock/distillation_tower.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).widget(fluidDisplay).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<BlockWorldState> fluidExportPredicate = countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS));
        Predicate<PatternMatchContext> exactlyOneHatch = context -> context.getInt("HatchesAmount") == 1;
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
            .aisle("YSY", "YZY", "YYY")
            .aisle("XXX", "X#X", "XXX").setRepeatable(0, 11)
            .aisle("XXX", "XXX", "XXX")
            .where('S', selfPredicate())
            .where('Z', abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS))
            .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY)))
            .where('X', fluidExportPredicate.or(statePredicate(getCasingState())))
            .where('#', isAirPredicate())
            .validateLayer(1, exactlyOneHatch)
            .validateLayer(2, exactlyOneHatch)
            .build();
    }

    @Override
    protected boolean allowSameFluidFillForOutputs() {
        return false;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.STAINLESS_CLEAN);
    }

    @Override
    public boolean isRemote() {
        return getWorld().isRemote;
    }

    @Override
    public void markAsDirty() {
        markDirty();
    }
}
