package gtr.common.metatileentities.multi.electric;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.IMultipleTankHandler;
import gtr.api.capability.impl.*;
import gtr.api.gui.GuiTextures;
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
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.net.displayrecipes.MessageRequestRecipeMultiblock;
import gtr.api.net.displayrecipes.MessageSetRecipeMultiblock;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.common.blocks.BlockMultiblockCasing;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

import static gtr.api.multiblock.BlockPattern.RelativeDirection.*;

public class MetaTileEntityFusionReactor extends RecipeMapMultiblockController implements IUIHolder {

    private final int tier;
    private EnergyContainerList inputEnergyContainers;
    private int heat = 0;

    public MetaTileEntityFusionReactor(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, RecipeMaps.FUSION_RECIPES);
        this.recipeMapWorkable = new FusionRecipeLogic(this);
        this.tier = tier;
        this.reinitializeStructurePattern();
        this.energyContainer = new EnergyContainerHandler(this, Integer.MAX_VALUE, 0, 0, 0, 0) {
            @Override
            public String getName() {
                return "EnergyContainerInternal";
            }
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityFusionReactor(metaTileEntityId, tier);
    }


    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return this.recipeMapWorkable.isActive() ? Textures.ACTIVE_FUSION_BASE : Textures.FUSION_BASE;
    }


    @Override
    protected void formStructure(PatternMatchContext context) {
        long energyStored = this.energyContainer.getEnergyStored();
        super.formStructure(context);
        this.initializeAbilities();
        ((EnergyContainerHandler) this.energyContainer).setEnergyStored(energyStored);
    }


    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            if (this.inputEnergyContainers.getEnergyStored() > 0) {
                long energyAdded = this.energyContainer.addEnergy(this.inputEnergyContainers.getEnergyStored());
                if (energyAdded > 0) this.inputEnergyContainers.removeEnergy(energyAdded);
            }
            super.updateFormedValid();
        }
    }


    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        this.getBaseTexture(null).render(renderState, translation, pipeline);
        Textures.FUSION_REACTOR_OVERLAY.render(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive());
    }

    @Override
    public boolean isRemote() {
        return getWorld().isRemote;
    }

    @Override
    public void markAsDirty() {
        this.markDirty();
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


        ProgressWidget progress = new ProgressWidget(() -> (double) energyContainer.getEnergyStored() / (1.0 * energyContainer.getEnergyCapacity()), 5, 156, 147, 5)
            .setProgressBar(GuiTextures.ENERGY_EMPTY, GuiTextures.ENERGY_FULL, ProgressWidget.MoveType.HORIZONTAL);

        JeiOpenWidget recipes = new JeiOpenWidget(154, 4, 18, 18, this.recipeMap);

        StructureFormedWidget structureFormed = new StructureFormedWidget(46, 46, 13, 13, this::isStructureFormed);

        ActiveWidget active = new ActiveWidget(155, 23, 16, 16, () -> this.recipeMapWorkable.isActive());

        FluidDisplayWidget input1 = null;
        FluidDisplayWidget input2 = null;
        FluidDisplayWidget output = null;
        ProgressWidget progressWidget = new ProgressWidget(() -> this.recipeMapWorkable.getProgressPercent(), 155, 65, 16, 22)
            .setProgressBar(gtr.api.gui.resources.TextureArea.fullImage("textures/gui/multiblock/fusionbar_empty.png"),
                gtr.api.gui.resources.TextureArea.fullImage("textures/gui/multiblock/fusionbar_full.png"), ProgressWidget.MoveType.VERTICAL);

        if (displayRecipe != null) {
            input1 = new FluidDisplayWidget(154, 112, 18, 18, () -> displayRecipe.getFluidInputs().get(0));
            input2 = new FluidDisplayWidget(154, 94, 18, 18, () -> displayRecipe.getFluidInputs().get(1));
            output = new FluidDisplayWidget(154, 40, 18, 18, () -> displayRecipe.getFluidOutputs().get(0));
        }


        return ModularUI.builder(TextureArea.fullImage("textures/gui/multiblock/fusion_computer.png"), 176, 166).widget(progress).widget(recipes).widget(structureFormed)
            .widget(active).widget(progressWidget).widget(input1).widget(input2).widget(output).build(this, entityPlayer);
    }


    @Override
    protected BlockPattern createStructurePattern() {
        FactoryBlockPattern.start();
        return FactoryBlockPattern.start(LEFT, DOWN, FRONT)
            .aisle("###############", "######OCO######", "###############")
            .aisle("######ICI######", "####CCcccCC####", "######ICI######")
            .aisle("####CC###CC####", "###EccOCOccE###", "####CC###CC####")
            .aisle("###C#######C###", "##EcEC###CEcE##", "###C#######C###")
            .aisle("##C#########C##", "#CcE#######EcC#", "##C#########C##")
            .aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
            .aisle("#I###########I#", "OcO#########OcO", "#I###########I#")
            .aisle("#C###########C#", "CcC#########CcC", "#C###########C#")
            .aisle("#I###########I#", "OcO#########OcO", "#I###########I#")
            .aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
            .aisle("##C#########C##", "#CcE#######EcC#", "##C#########C##")
            .aisle("###C#######C###", "##EcEC###CEcE##", "###C#######C###")
            .aisle("####CC###CC####", "###EccOSOccE###", "####CC###CC####")
            .aisle("######ICI######", "####CCcccCC####", "######ICI######")
            .aisle("###############", "######OCO######", "###############")
            .where('S', selfPredicate())
            .where('C', statePredicate(getCasingState()))
            .where('c', statePredicate(getCoilState()))
            .where('O', statePredicate(getCasingState())
                .or((abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS))))
            .where('E', statePredicate(getCasingState())
                .or(abilityPartPredicate(MultiblockAbility.INPUT_ENERGY)))
            .where('I', statePredicate(getCasingState())
                .or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
            .where('#', (tile) -> true).build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING);
    }

    private IBlockState getCoilState() {
        return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL);
    }

    private long getMaxEU() {
        return 480000000;
    }



    private void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.inputEnergyContainers = new EnergyContainerList(this.getAbilities(MultiblockAbility.INPUT_ENERGY));
        this.energyContainer = new EnergyContainerHandler(this, getMaxEU(), GTValues.V[tier], 0, 0, 0) {
            @Override
            public String getName() {
                return "EnergyContainerInternal";
            }
        };
    }


    private class FusionRecipeLogic extends MultiblockRecipeLogic {
        public FusionRecipeLogic(MetaTileEntityFusionReactor tileEntity) {
            super(tileEntity);
            this.allowOverclocking = false;
        }

        @Override
        public void updateWorkable() {
            super.updateWorkable();
            if (!isActive && heat > 0) {
                heat = heat <= 10000 ? 0 : (heat - 10000);
            }
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
            return (recipe != null && recipe.getIntegerProperty("eu_to_start") <= energyContainer.getEnergyCapacity()) ? recipe : null;
        }

        @Override
        protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
            int heatDiff = recipe.getIntegerProperty("eu_to_start") - heat;
            if (heatDiff <= 0) {
                return super.setupAndConsumeRecipeInputs(recipe);
            }
            if (energyContainer.getEnergyStored() < heatDiff || !super.setupAndConsumeRecipeInputs(recipe)) {
                return false;
            }
            energyContainer.removeEnergy(heatDiff);
            heat += heatDiff;
            return true;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound tag = super.serializeNBT();
            tag.setInteger("Heat", heat);
            return tag;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            super.deserializeNBT(compound);
            heat = compound.getInteger("Heat");
        }
    }
}