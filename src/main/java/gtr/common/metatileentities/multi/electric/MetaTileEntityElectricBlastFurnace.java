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
import gtr.api.net.displayrecipes.MessageRequestFuelDieselEngine;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.BlockWireCoil.CoilType;
import gtr.common.blocks.MetaBlocks;
import gtr.common.blocks.VariantBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityElectricBlastFurnace extends RecipeMapMultiblockController implements IUIHolder {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
        MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
        MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
        MultiblockAbility.INPUT_ENERGY
    };

    private int blastFurnaceTemperature;
    private CoilType coil = CoilType.CUPRONICKEL;

    public MetaTileEntityElectricBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BLAST_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityElectricBlastFurnace(metaTileEntityId);
    }


    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        JeiOpenWidget recipes = new JeiOpenWidget(130, 59, 18, 18, this.recipeMap);

        StructureFormedWidget structureFormed = new StructureFormedWidget(155, 68, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(131, 42, 16, 16, () -> isStructureFormed() && recipeMapWorkable.isActive() && recipeMapWorkable.isWorkingEnabled());

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        SlotWidget coilWidget = null;
        if (getWorld().getBlockState(getPos().offset(EnumFacing.UP)).getBlock() instanceof BlockWireCoil) {
            this.coil = getWorld().getBlockState(getPos().offset(EnumFacing.UP)).getValue(((BlockWireCoil) getWorld().getBlockState(getPos().offset(EnumFacing.UP)).getBlock()).VARIANT);

            coilWidget = new SlotWidget(new IItemHandlerModifiable() {
                @Override
                public void setStackInSlot(int slot, @Nonnull ItemStack stack) {

                }

                @Override
                public int getSlots() {
                    return 1;
                }

                @Override
                public ItemStack getStackInSlot(int slot) {
                    return MetaBlocks.WIRE_COIL.getItemVariant(coil);
                }

                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    return stack;
                }

                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    return ItemStack.EMPTY;
                }

                @Override
                public int getSlotLimit(int slot) {
                    return 1;
                }
            }, 0, 151, 4, false, false);
        }




        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 166, TextureArea.fullImage("textures/gui/multiblock/ebf.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).widget(coilWidget).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);

        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gtr.multiblock.blast_furnace.max_temperature", blastFurnaceTemperature));
        }
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.blastFurnaceTemperature = context.getOrDefault("CoilType", CoilType.CUPRONICKEL).getCoilTemperature();
        this.coil = context.getOrDefault("CoilType", CoilType.CUPRONICKEL);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int recipeRequiredTemp = recipe.getIntegerProperty("blast_furnace_temperature");
        return this.blastFurnaceTemperature >= recipeRequiredTemp;
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof BlockWireCoil))
                return false;
            BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
            CoilType coilType = blockWireCoil.getState(blockState);
            CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
            return currentCoilType.getName().equals(coilType.getName());
        };
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
            .aisle("XXX", "CCC", "CCC", "XXX")
            .aisle("XXX", "C#C", "C#C", "XXX")
            .aisle("XSX", "CCC", "CCC", "XXX")
            .setAmountAtLeast('L', 10)
            .where('S', selfPredicate())
            .where('L', statePredicate(getCasingState()))
            .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
            .where('C', heatingCoilPredicate())
            .where('#', isAirPredicate())
            .build();
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.INVAR_HEATPROOF);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HEAT_PROOF_CASING;
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
