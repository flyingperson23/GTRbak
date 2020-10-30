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
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.net.displayrecipes.MessageRequestRecipeMultiblock;
import gtr.api.net.displayrecipes.MessageSetRecipeMultiblock;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.common.blocks.BlockMetalCasing;
import gtr.common.blocks.BlockWireCoil.CoilType;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityCrackingUnit extends RecipeMapMultiblockController implements IUIHolder {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
        MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
        MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityCrackingUnit(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.CRACKING_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCrackingUnit(metaTileEntityId);
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

        StructureFormedWidget structureFormed = new StructureFormedWidget(155, 68, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(131, 42, 16, 16, () -> isStructureFormed() && recipeMapWorkable.isActive() && recipeMapWorkable.isWorkingEnabled());

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        FluidDisplayWidget fluidDisplay = null;
        if (displayRecipe != null && displayRecipe.getFluidOutputs().size() > 0) {
            fluidDisplay = new FluidDisplayWidget(151, 4, 18, 18, () -> displayRecipe.getFluidOutputs().get(0));
        }

        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 166, TextureArea.fullImage("textures/gui/multiblock/cracking_unit.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).widget(fluidDisplay).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
            .aisle("HCHCH", "HCHCH", "HCHCH")
            .aisle("HCHCH", "H###H", "HCHCH")
            .aisle("HCHCH", "HCOCH", "HCHCH")
            .setAmountAtLeast('L', 20)
            .where('O', selfPredicate())
            .where('L', statePredicate(getCasingState()))
            .where('H', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
            .where('#', isAirPredicate())
            .where('C', statePredicate(getCoilState()))
            .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    protected IBlockState getCoilState() {
        return MetaBlocks.WIRE_COIL.getState(CoilType.CUPRONICKEL);
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
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
