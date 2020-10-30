package gtr.common.metatileentities.multi.electric.generator;

import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.gui.IUIHolder;
import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
import gtr.api.gui.widgets.*;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.BlockWorldState;
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.net.displayrecipes.MessageRequestFuelDieselEngine;
import gtr.api.net.displayrecipes.MessageSetFuelDieselEngine;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.api.unification.material.Materials;
import gtr.common.MetaFluids;
import gtr.common.blocks.BlockMetalCasing.MetalCasingType;
import gtr.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gtr.common.blocks.BlockTurbineCasing.TurbineCasingType;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityDieselEngine extends FueledMultiblockController implements IUIHolder {

    public FluidStack displayFluid;

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
    public void update() {
        super.update();
        if (isStructureFormed() && !isRemote() && importFluidHandler.getFluidTanks().size() > 0 && GregTechMod.instance.counter % 7 == 3 && getWorld().getTileEntity(getPos()) != null) {
            for (IFluidTank t : importFluidHandler.getFluidTanks()) {
                if (t.getFluid() != null) {
                    if (!(t.getFluid().getFluid() instanceof MetaFluids.MaterialFluid && ((MetaFluids.MaterialFluid) t.getFluid().getFluid()).material == Materials.Lubricant)) {
                        if (((MetaFluids.MaterialFluid) t.getFluid().getFluid()).material != Materials.Oxygen) {
                            GregTechMod.DISPLAY_INFO_WRAPPER.sendToAll(new MessageSetFuelDieselEngine(getPos(), t.getFluid().getFluid()));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        GregTechMod.DISPLAY_INFO_WRAPPER.sendToServer(new MessageRequestFuelDieselEngine(getPos()));

        JeiOpenWidget recipes = new JeiOpenWidget(130, 59, 18, 18, this.recipeMap);

        StructureFormedWidget structureFormed = new StructureFormedWidget(155, 68, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(131, 42, 16, 16, () -> isStructureFormed() && workableHandler.isActive() && workableHandler.isWorkingEnabled());

        FluidDisplayWidget input1w = null;


        if (displayFluid != null) {
            input1w = new FluidDisplayWidget(151, 4, 18, 18, () -> this.displayFluid);
        }

        EnergyFlowWidget power = new EnergyFlowWidget(40, 162, 96, 16, () -> workableHandler, this::isStructureFormed);

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 178, TextureArea.fullImage("textures/gui/multiblock/diesel_engine.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).widget(input1w).widget(power).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            FluidStack fuelStack = null;
            for (IFluidTank t : importFluidHandler) {
                if (t != null && t.getFluid() != null) {
                    if (!t.getFluid().getUnlocalizedName().contains("lubricant")) {
                        if (!t.getFluid().getUnlocalizedName().contains("oxygen")) {
                            fuelStack = t.getFluid();
                        }
                    }
                }
            }

            FluidStack lubricantStack = importFluidHandler.drain(Materials.Lubricant.getFluid(Integer.MAX_VALUE), false);
            FluidStack oxygenStack = importFluidHandler.drain(Materials.Oxygen.getFluid(Integer.MAX_VALUE), false);
            int lubricantAmount = lubricantStack == null ? 0 : lubricantStack.amount;
            int oxygenAmount = oxygenStack == null ? 0 : oxygenStack.amount;
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            textList.add(new TextComponentTranslation("gtr.multiblock.diesel_engine.lubricant_amount", lubricantAmount));
            textList.add(new TextComponentTranslation("gtr.multiblock.diesel_engine.fuel_amount", fuelAmount));
            textList.add(new TextComponentTranslation("gtr.multiblock.diesel_engine.oxygen_amount", oxygenAmount));
            textList.add(new TextComponentTranslation(oxygenAmount >= 2 ? "gtr.multiblock.diesel_engine.oxygen_boosted" : "gtr.multiblock.diesel_engine.supply_oxygen_to_boost"));
            if (!workableHandler.isWorkingEnabled()) {
                textList.add(new TextComponentTranslation("gtr.multiblock.work_paused"));
            } else if (workableHandler.isActive()) {
                textList.add(new TextComponentTranslation("gtr.multiblock.running"));
                textList.add(new TextComponentTranslation("gtr.multiblock.generation_eu", workableHandler.getRecipeOutputVoltage()));
            } else {
                textList.add(new TextComponentTranslation("gtr.multiblock.idling"));
            }
        }
    }

    protected Predicate<BlockWorldState> intakeCasingPredicate() {
        IBlockState blockState = MetaBlocks.MULTIBLOCK_CASING.getState(MultiblockCasingType.ENGINE_INTAKE_CASING);
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

    @Override
    public boolean isRemote() {
        return getWorld().isRemote;
    }

    @Override
    public void markAsDirty() {
        markDirty();
    }
}
