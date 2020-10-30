package gtr.common.metatileentities.multi.electric;

import gtr.api.GTValues;
import gtr.api.capability.impl.MultiblockRecipeLogic;
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
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.common.blocks.BlockMachineCasing.MachineCasingType;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.BlockWireCoil.CoilType;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MetaTileEntityPyrolyseOven extends RecipeMapMultiblockController implements IUIHolder {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
        MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
        MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
        MultiblockAbility.INPUT_ENERGY
    };

    private CoilType coil = CoilType.CUPRONICKEL;

    public MetaTileEntityPyrolyseOven(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap);
        this.recipeMapWorkable = new PyrolyseRecipeLogic(this, () -> coil);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.coil = context.getOrDefault("CoilType", CoilType.CUPRONICKEL);
    }

    public MetaTileEntityPyrolyseOven(ResourceLocation metaTileEntityId) {
        this(metaTileEntityId, RecipeMaps.PYROLYSE_RECIPES);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        JeiOpenWidget recipes = new JeiOpenWidget(130, 59, 18, 18, this.recipeMap);

        StructureFormedWidget structureFormed = new StructureFormedWidget(133, 8, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(131, 42, 16, 16, () -> isStructureFormed() && recipeMapWorkable.isActive() && recipeMapWorkable.isWorkingEnabled());

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        SlotWidget coilWidget = null;
        if (getWorld().getBlockState(getPos().offset(getFrontFacing().getOpposite())).getBlock() instanceof BlockWireCoil) {
            this.coil = getWorld().getBlockState(getPos().offset(getFrontFacing().getOpposite())).getValue(((BlockWireCoil) getWorld().getBlockState(getPos().offset(getFrontFacing().getOpposite())).getBlock()).VARIANT);

            coilWidget = new SlotWidget(new IItemHandlerModifiable() {
                @Override
                public void setStackInSlot(int slot, @Nonnull ItemStack stack) {

                }

                @Override
                public int getSlots() {
                    return 1;
                }

                @Nonnull
                @Override
                public ItemStack getStackInSlot(int slot) {
                    return MetaBlocks.WIRE_COIL.getItemVariant(coil);
                }

                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    return stack;
                }

                @Nonnull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    return ItemStack.EMPTY;
                }

                @Override
                public int getSlotLimit(int slot) {
                    return 1;
                }
            }, 0, 130, 23, false, false);


        }




        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 166, TextureArea.fullImage("textures/gui/multiblock/pyrolyse_oven.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).widget(coilWidget).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }



    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityPyrolyseOven(metaTileEntityId);
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
            .aisle("XXXXX", "CCCCC", "CCCCC", "CCCCC")
            .aisle("XHHHX", "C###C", "C###C", "CCCCC")
            .aisle("XHHHX", "C###C", "C###C", "CCCCC")
            .aisle("XHHHX", "C###C", "C###C", "CCCCC")
            .aisle("XXSXX", "CCCCC", "CCCCC", "CCCCC")
            .where('S', selfPredicate())
            .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
            .where('C', statePredicate(getCasingState()))
            .where('H', heatingCoilPredicate())
            .where('#', isAirPredicate())
            .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.PYROLYSE;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.MACHINE_CASING.getState(MachineCasingType.PYROLYSE);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtr.pyrolyse_tooltip"));
        tooltip.add("I.E. CuNi-50%, FeAlCr-100%, NiCr-150%, etc.");
    }

    @Override
    public boolean isRemote() {
        return getWorld().isRemote;
    }

    @Override
    public void markAsDirty() {
        markDirty();
    }

    static class PyrolyseRecipeLogic extends MultiblockRecipeLogic {

        private Supplier<CoilType> coilTypeSupplier = () -> CoilType.CUPRONICKEL;

        public PyrolyseRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        public PyrolyseRecipeLogic(RecipeMapMultiblockController tileEntity, Supplier<CoilType> coilTypeSupplier) {
            this(tileEntity);
            this.coilTypeSupplier = coilTypeSupplier;
        }

        @Override
        protected int[] calculateOverclock(int EUt, long voltage, int d) {
            double duration = d;
            if (coilTypeSupplier != null && coilTypeSupplier.get() != null) {
                duration *= ((double) coilTypeSupplier.get().getTier() /2.0);
            }

            if(!allowOverclocking) {
                return new int[] {EUt, (int) duration};
            }
            boolean negativeEU = EUt < 0;
            int tier = getOverclockingTier(voltage);
            if (GTValues.V[tier] <= EUt || tier == 0)
                return new int[]{EUt, (int) duration};
            if (negativeEU)
                EUt = -EUt;
            if (EUt <= 16) {
                int multiplier = EUt <= 8 ? tier : tier - 1;
                int resultEUt = EUt * (1 << multiplier) * (1 << multiplier);
                double resultDuration = duration / (1 << multiplier);
                return new int[]{negativeEU ? -resultEUt : resultEUt, (int) resultDuration};
            } else {
                int resultEUt = EUt;
                double resultDuration = duration;
                //do not overclock further if duration is already too small
                while (resultDuration >= 3 && resultEUt <= GTValues.V[tier - 1]) {
                    resultEUt *= 4;
                    resultDuration /= 2.8;
                }
                return new int[]{negativeEU ? -resultEUt : resultEUt, (int) Math.ceil(resultDuration)};
            }
        }
    }

}
