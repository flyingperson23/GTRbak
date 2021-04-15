package gtr.common.metatileentities.multi.electric.generator;

import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.impl.FluidTankList;
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
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.net.displayrecipes.MessageRequestFuelLargeTurbine;
import gtr.api.net.displayrecipes.MessageSetFuelLargeTurbine;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.machines.FuelRecipeMap;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.api.unification.material.Materials;
import gtr.common.MetaFluids;
import gtr.common.blocks.BlockTurbineCasing.TurbineCasingType;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.electric.multiblockpart.MetaTileEntityRotorHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

public class MetaTileEntityLargeTurbine extends RotorHolderMultiblockController implements IUIHolder {

    private static final int MIN_DURABILITY_TO_WARN = 10;

    @Override
    public boolean isRemote() {
        return getWorld().isRemote;
    }

    @Override
    public void markAsDirty() {
        markDirty();
    }

    public enum TurbineType {

        STEAM(RecipeMaps.STEAM_TURBINE_FUELS, MetaBlocks.TURBINE_CASING.getState(TurbineCasingType.STEEL_TURBINE_CASING), Textures.SOLID_STEEL_CASING, true),
        GAS(RecipeMaps.GAS_TURBINE_FUELS, MetaBlocks.TURBINE_CASING.getState(TurbineCasingType.STAINLESS_TURBINE_CASING), Textures.CLEAN_STAINLESS_STEEL_CASING, false),
        PLASMA(RecipeMaps.PLASMA_GENERATOR_FUELS, MetaBlocks.TURBINE_CASING.getState(TurbineCasingType.TUNGSTENSTEEL_TURBINE_CASING), Textures.ROBUST_TUNGSTENSTEEL_CASING, true);

        public final FuelRecipeMap recipeMap;
        public final IBlockState casingState;
        public final ICubeRenderer casingRenderer;
        public final boolean hasOutputHatch;

        TurbineType(FuelRecipeMap recipeMap, IBlockState casingState, ICubeRenderer casingRenderer, boolean hasOutputHatch) {
            this.recipeMap = recipeMap;
            this.casingState = casingState;
            this.casingRenderer = casingRenderer;
            this.hasOutputHatch = hasOutputHatch;
        }
    }

    public final TurbineType turbineType;
    public IFluidHandler exportFluidHandler;
    public FluidStack displayFluid;

    public MetaTileEntityLargeTurbine(ResourceLocation metaTileEntityId, TurbineType turbineType) {
        super(metaTileEntityId, turbineType.recipeMap, GTValues.V[4]);
        this.turbineType = turbineType;
        reinitializeStructurePattern();
    }

    @Override
    public void update() {
        super.update();
        if (isStructureFormed() && !isRemote() && importFluidHandler.getFluidTanks().size() > 0 && GregTechMod.instance.counter % 7 == 3 && getWorld().getTileEntity(getPos()) != null) {
            for (IFluidTank t : importFluidHandler.getFluidTanks()) {
                if (t.getFluid() != null) {
                    if (!(t.getFluid().getFluid() instanceof MetaFluids.MaterialFluid && ((MetaFluids.MaterialFluid) t.getFluid().getFluid()).material == Materials.Lubricant)) {
                        GregTechMod.DISPLAY_INFO_WRAPPER.sendToAll(new MessageSetFuelLargeTurbine(getPos(), t.getFluid().getFluid()));
                    }
                }
            }
        }
    }

    @Override
    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new LargeTurbineWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeTurbine(metaTileEntityId, turbineType);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.exportFluidHandler = null;
    }

    @Override
    public int getRotorSpeedIncrement() {
        return 1;
    }

    @Override
    public int getRotorSpeedDecrement() {
        return -3;
    }

    @Deprecated
    public boolean isTurbineFaceFree() {
        return isRotorFaceFree();
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        GregTechMod.DISPLAY_INFO_WRAPPER.sendToServer(new MessageRequestFuelLargeTurbine(getPos()));

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
        builder.image(0, 0, 176, 178, TextureArea.fullImage("textures/gui/multiblock/large_turbine.png"));
        builder.widget(text);

        return builder.widget(recipes).widget(structureFormed).widget(active).widget(input1w).widget(power).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);

        if (isStructureFormed()) {
            MetaTileEntityRotorHolder rotorHolder = getRotorHolder();
            FluidStack fuelStack = ((LargeTurbineWorkableHandler) workableHandler).getFuelStack();
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            textList.add(new TextComponentTranslation("gtr.multiblock.turbine.fuel_amount", fuelAmount));

            if (rotorHolder.getRotorEfficiency() > 0.0) {
                textList.add(new TextComponentTranslation("gtr.multiblock.turbine.rotor_speed", rotorHolder.getCurrentRotorSpeed(), rotorHolder.getMaxRotorSpeed()));
                textList.add(new TextComponentTranslation("gtr.multiblock.turbine.rotor_efficiency", (int) (rotorHolder.getRotorEfficiency() * 100)));
                int rotorDurability = (int) (rotorHolder.getRotorDurability() * 100);
                if (rotorDurability > MIN_DURABILITY_TO_WARN) {
                    textList.add(new TextComponentTranslation("gtr.multiblock.turbine.rotor_durability", rotorDurability));
                } else {
                    textList.add(new TextComponentTranslation("gtr.multiblock.turbine.low_rotor_durability")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
                }
            }
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

    @Override
    protected BlockPattern createStructurePattern() {
        return turbineType == null ? null :
            FactoryBlockPattern.start()
                .aisle("CCCC", "CHHC", "CCCC")
                .aisle("CHHC", "R##D", "CHHC")
                .aisle("CCCC", "CSHC", "CCCC")
                .where('S', selfPredicate())
                .where('#', isAirPredicate())
                .where('C', statePredicate(getCasingState()))
                .where('H', statePredicate(getCasingState()).or(abilityPartPredicate(getAllowedAbilities())))
                .where('R', abilityPartPredicate(ABILITY_ROTOR_HOLDER))
                .where('D', abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY))
                .build();
    }

    public MultiblockAbility[] getAllowedAbilities() {
        return turbineType.hasOutputHatch ?
            new MultiblockAbility[]{MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS} :
            new MultiblockAbility[]{MultiblockAbility.IMPORT_FLUIDS};
    }

    public IBlockState getCasingState() {
        return turbineType.casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return turbineType.casingRenderer;
    }

}
