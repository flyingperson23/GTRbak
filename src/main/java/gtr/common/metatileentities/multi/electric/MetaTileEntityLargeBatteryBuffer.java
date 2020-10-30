package gtr.common.metatileentities.multi.electric;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gtr.GregTechMod;
import gtr.GregTechVersion;
import gtr.api.GTValues;
import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.impl.EnergyContainerListWithAmps;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.IUIHolder;
import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
import gtr.api.gui.widgets.*;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.net.displayrecipes.MessageRequestFuelDieselEngine;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.api.util.GTUtility;
import gtr.common.blocks.BlockMultiblockCasing;
import gtr.common.blocks.MetaBlocks;
import gtr.common.metatileentities.electric.multiblockpart.MetaTileEntityBatteryHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MetaTileEntityLargeBatteryBuffer extends MultiblockWithDisplayBase implements IUIHolder {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.OUTPUT_ENERGY, MultiblockAbility.INPUT_ENERGY, MultiblockAbility.HOLD_ENERGY};
    private static final Point[] guiPoints = {new Point(154, 11), new Point(174, 11), new Point(154, 29), new Point(174, 29),
    new Point(154, 47), new Point(174, 47), new Point(154, 65), new Point(174, 65),
    new Point(154, 83), new Point(174, 83), new Point(154, 101), new Point(174, 101),
    new Point(154, 119), new Point(174, 119), new Point(154, 137), new Point(174, 137)};
    private IEnergyContainer input;
    private IEnergyContainer hold;
    private IEnergyContainer output;
    private boolean isActive = false;
    private int currentDrain = 0;
    private int drain = 0;
    DecimalFormat formatter = new DecimalFormat("#0.0");

    public MetaTileEntityLargeBatteryBuffer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }


    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        if (isActive)
            setActive(false);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    private void initializeAbilities() {
        this.input = new EnergyContainerListWithAmps(getAbilities(MultiblockAbility.INPUT_ENERGY));
        this.output = new EnergyContainerListWithAmps(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
        this.hold = new EnergyContainerListWithAmps(getAbilities(MultiblockAbility.HOLD_ENERGY));
    }

    private void resetTileAbilities() {
        this.input = new EnergyContainerListWithAmps(Lists.newArrayList());
        this.output = new EnergyContainerListWithAmps(Lists.newArrayList());
        this.hold = new EnergyContainerListWithAmps(Lists.newArrayList());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        int counter = 0;
        ModularUI.Builder builder = ModularUI.defaultBuilder();
        for (IMultiblockPart m : getMultiblockParts()) {
            if (m instanceof MetaTileEntityBatteryHolder) {
                MetaTileEntityBatteryHolder bat = (MetaTileEntityBatteryHolder) m;
                if (counter < 4) {

                    int rowSize = (int) Math.sqrt(MetaTileEntityBatteryHolder.inventorySize);
                    for (int y = 0; y < rowSize; y++) {
                        for (int x = 0; x < rowSize; x++) {
                            int index = y * rowSize + x;
                            //builder.widget(new SlotWidget(bat.importItems, index, 89 - rowSize * 9 + x * 18, 18 + y * 18, true, true)
                            //    .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.BATTERY_OVERLAY));
                        }
                    }
                }
                counter++;

            }
        }
        if (input != null) System.out.println(input.getEnergyStored() / input.getEnergyCapacity());
        if (output != null) System.out.println(output.getEnergyStored() / output.getEnergyCapacity());

        ProgressWidget i = new ProgressWidget(this::getProgressI, 5, 148, 147, 5, () -> Lists.newArrayList(I18n.format("gtr.tooltip.energyi")))
            .setProgressBar(GuiTextures.ENERGY_EMPTY, TextureArea.fullImage("textures/gui/multiblock/energybar_i.png"), ProgressWidget.MoveType.HORIZONTAL);

        ProgressWidget o = new ProgressWidget(this::getProgressO, 5, 156, 147, 5, () -> Lists.newArrayList(I18n.format("gtr.tooltip.energyo")))
            .setProgressBar(GuiTextures.ENERGY_EMPTY, TextureArea.fullImage("textures/gui/multiblock/energybar_o.png"), ProgressWidget.MoveType.HORIZONTAL);


        ProgressWidget energy = new ProgressWidget(this::getProgressE, 158, 22, 12, 135, () -> Lists.newArrayList(I18n.format("gtr.tooltip.energye")))
            .setProgressBar(GuiTextures.ENERGY_EMPTY, TextureArea.fullImage("textures/gui/multiblock/batterybuffer_bar.png"),
                ProgressWidget.MoveType.VERTICAL);

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);
        builder.image(0, 0, 178, 166, TextureArea.fullImage("textures/gui/multiblock/large_battery_buffer.png"));
        return builder.widget(text).widget(energy).widget(i).widget(o).build(this, entityPlayer);
    }

    public double getProgressE() {
        return (double) hold.getEnergyStored() / (1.0 * hold.getEnergyCapacity());
    }

    public double getProgressI() {
        if (input == null) return 0;
        return (double) input.getEnergyStored() / input.getEnergyCapacity();
    }

    public double getProgressO() {
        if (output == null) return 0;
        return (double) output.getEnergyStored() / output.getEnergyCapacity();
    }


    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            if (!isActive) {
                setActive(true);
            }

            if (hold.getEnergyStored() < hold.getEnergyCapacity()) {
                if (input.getEnergyStored() < hold.getEnergyCapacity() - hold.getEnergyStored()) {
                    hold.addEnergy(input.getEnergyStored());
                    input.removeEnergy(input.getEnergyStored());
                    currentDrain += input.getEnergyStored();
                } else {
                    long left = hold.getEnergyCapacity() - hold.getEnergyStored();
                    hold.addEnergy(left);
                    input.removeEnergy(left);
                    currentDrain += left;
                }
            }

            if (output.getEnergyStored() < output.getEnergyCapacity()) {
                if (hold.getEnergyStored() < output.getEnergyCapacity() - output.getEnergyStored()) {
                    output.addEnergy(hold.getEnergyStored());
                    hold.removeEnergy(hold.getEnergyStored());
                    currentDrain += hold.getEnergyStored();
                } else {
                    long left = output.getEnergyCapacity() - output.getEnergyStored();
                    output.addEnergy(left);
                    hold.removeEnergy(left);
                    currentDrain += left;
                }
            }

            if (getTimer() % 20 == 0) {
                drain = currentDrain / 20;
                currentDrain = 0;
            }
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
            .aisle("AAA", "AAA", "AAA")
            .aisle("AAA", "A A", "AAA")
            .aisle("AAA", "ASA", "AAA")
            .where('S', selfPredicate())
            .where('A', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
            .build();
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        return abilities.containsKey(MultiblockAbility.INPUT_ENERGY) && abilities.containsKey(MultiblockAbility.OUTPUT_ENERGY) && abilities.containsKey(MultiblockAbility.HOLD_ENERGY);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.HIGH_POWER);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HIGH_POWER;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeBatteryBuffer(metaTileEntityId);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
    }

    protected void setActive(boolean active) {
        this.isActive = active;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);

        input.getEnergyCapacity();
        input.getEnergyStored();

        output.getEnergyCapacity();
        output.getEnergyStored();

        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gtr.multiblock.large_battery_buffer.input", input.getInputAmperage(), GTValues.VN[GTUtility.getTierByVoltage(input.getInputVoltage()/input.getInputAmperage())]));
            textList.add(new TextComponentTranslation("gtr.multiblock.large_battery_buffer.output", output.getOutputAmperage(), GTValues.VN[GTUtility.getTierByVoltage(output.getOutputVoltage()/output.getOutputAmperage())]));
            textList.add(new TextComponentTranslation("gtr.multiblock.large_battery_buffer.stored", (hold.getEnergyStored()+input.getEnergyStored()+output.getEnergyStored()), (hold.getEnergyCapacity()+input.getEnergyCapacity()+output.getEnergyCapacity())));
        }
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