package gtr.common.metatileentities.multi.electric;

import gtr.GregTechMod;
import gtr.api.gui.IUIHolder;
import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
import gtr.api.gui.widgets.ActiveWidget;
import gtr.api.gui.widgets.AdvancedTextWidget;
import gtr.api.gui.widgets.JeiOpenWidget;
import gtr.api.gui.widgets.StructureFormedWidget;
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
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityVacuumFreezer extends RecipeMapMultiblockController implements IUIHolder {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
        MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
        MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
        MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityVacuumFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.VACUUM_RECIPES);
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

        StructureFormedWidget structureFormed = new StructureFormedWidget(155, 51, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(131, 42, 16, 16, () -> isStructureFormed() && recipeMapWorkable.isActive() && recipeMapWorkable.isWorkingEnabled());

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 166, TextureArea.fullImage("textures/gui/multiblock/vacuum_freezer.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityVacuumFreezer(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
            .aisle("XXX", "XXX", "XXX")
            .aisle("XXX", "X#X", "XXX")
            .aisle("XXX", "XSX", "XXX")
            .setAmountAtLeast('L', 14)
            .where('S', selfPredicate())
            .where('L', statePredicate(getCasingState()))
            .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
            .where('#', isAirPredicate())
            .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.FROST_PROOF_CASING;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.ALUMINIUM_FROSTPROOF);
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
