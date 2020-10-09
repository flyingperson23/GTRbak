package gtr.common.metatileentities.multi.electric.generator;

import gtr.api.GTValues;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.BlockWorldState;
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.api.unification.material.Materials;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gtr.common.blocks.BlockTurbineCasing.TurbineCasingType;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityDieselEngine extends FueledMultiblockController {

    public MetaTileEntityDieselEngine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.DIESEL_GENERATOR_FUELS, GTValues.V[GTValues.EV]);
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new DieselEngineWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler, maxVoltage);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityDieselEngine(metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack lubricantStack = importFluidHandler.drain(Materials.Lubricant.getFluid(Integer.MAX_VALUE), false);
            FluidStack oxygenStack = importFluidHandler.drain(Materials.Oxygen.getFluid(Integer.MAX_VALUE), false);
            FluidStack fuelStack = ((DieselEngineWorkableHandler) workableHandler).getFuelStack();
            int lubricantAmount = lubricantStack == null ? 0 : lubricantStack.amount;
            int oxygenAmount = oxygenStack == null ? 0 : oxygenStack.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            ITextComponent fuelName = new TextComponentTranslation(fuelAmount == 0 ? "gtr.fluid.empty" : fuelStack.getUnlocalizedName());
            textList.add(new TextComponentTranslation("gtr.multiblock.diesel_engine.lubricant_amount", lubricantAmount));
            textList.add(new TextComponentTranslation("gtr.multiblock.diesel_engine.fuel_amount", fuelAmount, fuelName));
            textList.add(new TextComponentTranslation("gtr.multiblock.diesel_engine.oxygen_amount", oxygenAmount));
            textList.add(new TextComponentTranslation(oxygenAmount >= 2 ? "gtr.multiblock.diesel_engine.oxygen_boosted" : "gtr.multiblock.diesel_engine.supply_oxygen_to_boost"));
        }
        super.addDisplayText(textList);
    }

    protected Predicate<BlockWorldState> intakeCasingPredicate() {
        IBlockState blockState = MetaBlocks.MUTLIBLOCK_CASING.getState(MultiblockCasingType.ENGINE_INTAKE_CASING);
        return blockWorldState -> {
            if (blockWorldState.getBlockState() != blockState)
                return false;
            IBlockState offsetState = blockWorldState.getOffsetState(getFrontFacing());
            return offsetState.getBlock().isAir(offsetState, blockWorldState.getWorld(), blockWorldState.getPos());
        };
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
            .aisle("XXX", "XDX", "XXX")
            .aisle("XHX", "HGH", "XHX")
            .aisle("XHX", "HGH", "XHX")
            .aisle("AAA", "AYA", "AAA")
            .where('X', statePredicate(getCasingState()))
            .where('G', statePredicate(MetaBlocks.TURBINE_CASING.getState(TurbineCasingType.TITANIUM_GEARBOX)))
            .where('H', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
            .where('D', abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY))
            .where('A', intakeCasingPredicate())
            .where('Y', selfPredicate())
            .build();
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(MetalCasingType.TITANIUM_STABLE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.STABLE_TITANIUM_CASING;
    }

}
