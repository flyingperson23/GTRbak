package gtr.common.metatileentities.multi.electric.generator;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.IMultipleTankHandler;
import gtr.api.capability.impl.EnergyContainerList;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.metatileentity.MTETrait;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.recipes.machines.FuelRecipeMap;
import gtr.api.render.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.Map;

public abstract class FueledMultiblockController extends MultiblockWithDisplayBase {

    protected final FuelRecipeMap recipeMap;
    protected FuelRecipeLogic workableHandler;
    protected IEnergyContainer energyContainer;
    public IMultipleTankHandler importFluidHandler;

    public FueledMultiblockController(ResourceLocation metaTileEntityId, FuelRecipeMap recipeMap, long maxVoltage) {
        super(metaTileEntityId);
        this.recipeMap = recipeMap;
        this.workableHandler = createWorkable(maxVoltage);
    }

    protected FuelRecipeLogic createWorkable(long maxVoltage) {
        return new FuelRecipeLogic(this, recipeMap,
            () -> energyContainer,
            () -> importFluidHandler, maxVoltage);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
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
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    private void initializeAbilities() {
        this.importFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.importFluidHandler = null;
        this.energyContainer = null;
    }

    @Override
    protected void updateFormedValid() {
        this.workableHandler.update();
    }

    @Override
    protected boolean shouldUpdate(MTETrait trait) {
        return !(trait instanceof FuelRecipeLogic);
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        return abilities.containsKey(MultiblockAbility.IMPORT_FLUIDS) &&
            abilities.containsKey(MultiblockAbility.OUTPUT_ENERGY);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(),
            isStructureFormed() && workableHandler.isActive());
    }
}
