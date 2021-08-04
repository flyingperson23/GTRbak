package gtr.common.metatileentities.electric;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.GTValues;
import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.capability.IActiveOutputSide;
import gtr.api.capability.impl.EnergyContainerHandler;
import gtr.api.capability.impl.FluidHandlerProxy;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.capability.impl.ItemHandlerProxy;
import gtr.api.gui.GuiTextures;
import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
import gtr.api.gui.widgets.*;
import gtr.api.items.toolitem.ToolMetaItem;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.TieredMetaTileEntity;
import gtr.api.render.Textures;
import gtr.api.unification.OreDictUnifier;
import gtr.api.util.GTUtility;
import gtr.common.blocks.BlockGTMiningPipe;
import gtr.common.blocks.BlockOre;
import gtr.common.blocks.MetaBlocks;
import gtr.common.items.MetaItems;
import gtr.common.tools.DamageValues;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleSupplier;

public class MetaTileEntityMiner extends TieredMetaTileEntity implements IActiveOutputSide {

    int drillY = 0;
    boolean isPickingPipes = false;
    boolean waitMiningPipe;
    boolean canWork = true;
    public int progressTime;
    public int maxProgressTime;
    static final int[] RADIUS = {8, 8, 16, 24, 32}; //Miner radius per tier
    static final int[] SPEED = {160, 160, 80, 40, 20}; //Miner cycle time per tier
    static final int[] ENERGY = {8, 8, 32, 128, 512}; //Miner energy consumption per tier
    private ItemStackHandler chargerInventory;
    private EnumFacing outputFacing;
    private static final int FONT_HEIGHT = 9; // Minecraft's FontRenderer FONT_HEIGHT value
    private boolean autoOutputItems;
    private boolean allowInputFromOutputSide;
    private int radiusConfig; //Miner configured radius
    private final ArrayList<BlockPos> oreBlockPositions = new ArrayList<>();
    protected IItemHandler outputItemInventory;
    private boolean isWorking = false;

    public MetaTileEntityMiner(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        radiusConfig = RADIUS[tier];
        this.chargerInventory = new ItemStackHandler(1) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (getTier() > 0) {
            tooltip.add("Applies fortune "+getTier());
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
         return new MetaTileEntityMiner(metaTileEntityId, getTier());
    }

    @Override
    public boolean hasFrontFacing() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MINER.render(renderState, translation, pipeline, getFrontFacing(), isWorking);
        if (outputFacing != null) {
            Textures.PIPE_OUT_OVERLAY.renderSided(outputFacing, renderState, translation, pipeline);
            if (isAutoOutputItems()) {
                Textures.ITEM_OUTPUT_OVERLAY.renderSided(outputFacing, renderState, translation, pipeline);
            }
        }
    }

    @Override
    protected void reinitializeEnergyContainer() {
        this.energyContainer = EnergyContainerHandler.receiverContainer(this,
            GTValues.V[getTier()] * 64L, GTValues.V[getTier()], getMaxInputOutputAmperage());
    }

    protected void initializeInventory() {
        this.importItems = new ItemStackHandler(2);
        this.exportItems = new ItemStackHandler(2);
        this.itemInventory = new ItemHandlerProxy(importItems, exportItems);

        this.importFluids = createImportFluidHandler();
        this.exportFluids = createExportFluidHandler();
        this.fluidInventory = new FluidHandlerProxy(importFluids, exportFluids);
        this.outputItemInventory = new ItemHandlerProxy(new ItemStackHandler(0), exportItems);
    }

    public static IBlockState getMiningPipe() {
        if (Loader.isModLoaded("ic2")) {
            return IC2Handler.getMiningPipe();
        }
        return MetaBlocks.MINING_PIPE.getState(BlockGTMiningPipe.MiningPipeType.PIPE);
    }

    public static boolean isMiningPipe(ItemStack stack) {
        if (Loader.isModLoaded("ic2")) {
            return IC2Handler.isMiningPipe(stack);
        }
        ItemStack s = MetaBlocks.MINING_PIPE.getItemVariant(BlockGTMiningPipe.MiningPipeType.PIPE);
        return (stack.getItem() == s.getItem() && s.getMetadata() == stack.getMetadata());
    }

    public static IBlockState getMiningPipeTip() {
        if (Loader.isModLoaded("ic2")) {
            return IC2Handler.getMiningPipeTip();
        }
        return MetaBlocks.MINING_PIPE.getState(BlockGTMiningPipe.MiningPipeType.TIP);
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult result) {
        if (result.getAsTraceResult().sideHit != getFrontFacing()) {
            if (!getWorld().isRemote) {
                if (result.getAsTraceResult().sideHit == getOutputFacing()) {
                    if (isAllowInputFromOutputSide()) {
                        setAllowInputFromOutputSide(false);
                        playerIn.sendMessage(new TextComponentTranslation("gtr.machine.basic.input_from_output_side.disallow"));
                    } else {
                        setAllowInputFromOutputSide(true);
                        playerIn.sendMessage(new TextComponentTranslation("gtr.machine.basic.input_from_output_side.allow"));
                    }
                } else {
                    if (playerIn.isSneaking()) {
                        if (radiusConfig >= 0) {
                            radiusConfig--;
                        }
                        if (radiusConfig < 0)
                            radiusConfig = RADIUS[getTier()];
                    } else {
                        if (radiusConfig <= RADIUS[getTier()]) {
                            radiusConfig++;
                            isPickingPipes = false;
                        }
                        if (radiusConfig > RADIUS[getTier()])
                            radiusConfig = 0;
                    }
                    playerIn.sendMessage(new TextComponentString("Area set to " + (radiusConfig * 2 + 1) + "x" + (radiusConfig * 2 + 1)));
                    oreBlockPositions.clear();
                    fillOreList();
                }
            }
            return true;
        }
        return super.onScrewdriverClick(playerIn, hand, facing, result);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            IItemHandler itemHandler = (side == getOutputFacing() && !allowInputFromOutputSide) ? outputItemInventory : itemInventory;
            if(itemHandler.getSlots() > 0) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);
            }
            return null;
        } else if (capability == GregtechTileCapabilities.CAPABILITY_ACTIVE_OUTPUT_SIDE) {
            if (side == getOutputFacing()) {
                return GregtechTileCapabilities.CAPABILITY_ACTIVE_OUTPUT_SIDE.cast(this);
            }
            return null;
        }

        return super.getCapability(capability, side);
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (!playerIn.isSneaking()) {
            EnumFacing currentOutputSide = getOutputFacing();
            if (currentOutputSide == facing || getFrontFacing() == facing) {
                return false;
            }
            if(!getWorld().isRemote) {
                setOutputFacing(facing);
            }
            return true;
        }
        return super.onWrenchClick(playerIn, hand, facing, hitResult);
    }

    public boolean hasEnergy() {
        return energyContainer.getEnergyStored() >= ENERGY[getTier()] * (SPEED[getTier()] - progressTime);
    }
    
    @Override
    public void update() {
        super.update();

        if (!getWorld().isRemote) {
            ((EnergyContainerHandler) this.energyContainer).dischargeOrRechargeEnergyContainers(chargerInventory, 0);
            if (getTimer() % 5 == 0) {
                EnumFacing outputFacing = getOutputFacing();
                if (autoOutputItems) {
                    pushItemsIntoNearbyHandlers(outputFacing);
                }
            }

            if (!isPickingPipes && oreBlockPositions.isEmpty()) {
                fillOreList();
            }
            if (isActive() && hasEnergy()) {
                if (!isWorking) {
                    isWorking = true;
                    markDirty();
                }
                if (waitMiningPipe) {
                    maxProgressTime = 0;
                    for (int i = 0; i < importItems.getSlots(); i++) {
                        ItemStack s = importItems.getStackInSlot(i);
                        if (isMiningPipe(s) && s.getCount() > 0) {
                            waitMiningPipe = false;
                            return;
                        }
                    }
                } else {
                    energyContainer.removeEnergy(ENERGY[getTier()]);
                    progressTime++;
                    maxProgressTime = SPEED[getTier()];
                }
            } else {
                if (isWorking) {
                    isWorking = false;
                    markDirty();
                }
            }
            if (progressTime >= maxProgressTime && maxProgressTime > 0) {
                progressTime = 0;
                if (isPickingPipes) {
                    IBlockState state = getWorld().getBlockState(getPos().add(0, drillY, 0));
                    if (blockStatesEqual(state, getMiningPipeTip()) || blockStatesEqual(state, getMiningPipe())) {
                        releaseItems(Collections.singletonList(getMiningPipeItemStack()));
                        getWorld().setBlockToAir(getPos().add(0, drillY, 0));
                        drillY++;
                    } else {
                        isPickingPipes = false;
                    }
                    if (drillY == 0) canWork = false;
                } else if (oreBlockPositions.size() > 0) {
                    releaseItems(mineBlock(getPos().add(oreBlockPositions.remove(0))));
                } else {
                    moveOneDown();
                }
            }
        }
    }

    private static boolean blockStatesEqual(IBlockState state1, IBlockState state2) {
        if (!state1.getBlock().equals(state2.getBlock())) return false;
        Collection<IProperty<?>> prop1 = state1.getPropertyKeys();
        Collection<IProperty<?>> prop2 = state2.getPropertyKeys();
        if (prop1.size()!=prop2.size()) return false;
        for (IProperty<?> prop : prop1) {
            if (!prop2.contains(prop)) return false;
            if (state1.getValue(prop) != state2.getValue(prop)) return false;
        }
        return true;
    }

    public ItemStack getMiningPipeItemStack() {
        if (Loader.isModLoaded("ic2")) {
            return IC2Handler.getMiningPipeStack();
        }
        return MetaBlocks.MINING_PIPE.getItemVariant(BlockGTMiningPipe.MiningPipeType.PIPE);
    }


    private void fillOreList() {
        if (drillY == 0)
            return;
        for (int z = -radiusConfig; z <= radiusConfig; ++z) {
            for (int x = -radiusConfig; x <= radiusConfig; ++x) {
                BlockPos offsetPos = getPos().add(x, drillY, z);
                IBlockState state = getWorld().getBlockState(offsetPos);
                if (state.getBlock() instanceof BlockOre || isOre(getWorld(), getPos(), state)) {
                    oreBlockPositions.add(new BlockPos(x, drillY, z));
                }
            }
        }
    }

    private static boolean isOre(World w, BlockPos p, IBlockState state) {
        try {
            for (String s : OreDictUnifier.getOreDictionaryNames(state.getBlock().getPickBlock(state, new RayTraceResult(new Vec3d(0, 0, 0), EnumFacing.UP, p), w, p, null))) {
                if (s.contains("ore")) return true;
            }
        } catch(Exception ignored) {}
        return false;
    }

    public boolean isActive() {
        return canWork;
    }

    @Override
    public boolean onRightClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (playerIn.world.isRemote) playerIn.swingArm(hand);
        if (!(playerIn.world.isRemote || playerIn.world.isAirBlock(getPos()))) {
            if (playerIn.getHeldItem(hand).getItem() instanceof ToolMetaItem) {
                ToolMetaItem<?> t = (ToolMetaItem<?>) playerIn.getHeldItem(hand).getItem();
                ToolMetaItem<?>.MetaValueItem m = t.getItem(playerIn.getHeldItem(hand));
                if (m == MetaItems.SOFT_HAMMER) {
                    this.canWork = !canWork;
                    GTUtility.doDamageItem(playerIn.getHeldItem(hand), DamageValues.DAMAGE_FOR_SOFT_HAMMER, false);
                    return true;
                }
            }
        }
        return super.onRightClick(playerIn, hand, facing, hitResult);
    }

    public void moveOneDown() {
        BlockPos pos = getPos();
        if (pos.getY() + drillY - 1 < 0 || getWorld().getBlockState(pos.add(0, drillY-1, 0)).getBlockHardness(getWorld(), pos.add(0, drillY - 1, 0)) < 0) {
            isPickingPipes = true;
            return;
        }
        if (blockStatesEqual(getWorld().getBlockState(pos.add(0, drillY, 0)), getMiningPipeTip())) {
            getWorld().setBlockState(pos.add(0, drillY, 0), getMiningPipe(), 3);
        }
        miningPipes:
        {
            for (int i = 0; i < importItems.getSlots(); i++) {
                ItemStack s = importItems.getStackInSlot(i);
                if (isMiningPipe(s) && s.getCount() > 0) {
                    s.setCount(s.getCount() - 1);
                    if (s.getCount() == 0) {
                        importItems.setStackInSlot(i, ItemStack.EMPTY);
                    }
                    break miningPipes;
                }
            }
            waitMiningPipe = true;
            return;
        }
        if (!getWorld().isAirBlock(pos.add(0, drillY-1, 0))) {
            releaseItems(mineBlock(pos.add(0, drillY-1, 0)));
        }
        getWorld().setBlockState(pos.add(0, drillY-1, 0), getMiningPipeTip(), 3);
        drillY--;
        fillOreList();
    }

    private void releaseItems(List<ItemStack> items) {
        ArrayList<ItemStack> overflowHandler = new ArrayList<>();
        for (ItemStack item : items) {
            boolean dropped = false;
            for (int i = 0; i < exportItems.getSlots() && items.size() > 0 && !dropped; i++) {
                ItemStack inSlot = exportItems.getStackInSlot(i);
                if (inSlot.isEmpty()) {
                    exportItems.setStackInSlot(i, item);
                    dropped = true;
                } else if (item.getItem() == inSlot.getItem() && item.getMetadata() == inSlot.getMetadata() && inSlot.getCount() < 64 && ItemStack.areItemStackTagsEqual(inSlot, item)) {
                    int inStack = inSlot.getCount() + item.getCount();
                    if (inStack <= 64) {
                        inSlot.setCount(inStack);
                        exportItems.setStackInSlot(i, inSlot);
                    } else {
                        inSlot.setCount(64);
                        exportItems.setStackInSlot(i, inSlot);
                        int excess = inStack - 64;
                        item.setCount(excess);
                        overflowHandler.add(item);
                    }
                    dropped = true;
                }
            }
            if (!dropped) {
                getWorld().spawnEntity(new EntityItem(getWorld(), getPos().getX(), getPos().getY(), getPos().getZ(), item));
            }
        }
        if (overflowHandler.size() > 0) releaseItems(overflowHandler);
    }


    public NonNullList<ItemStack> mineBlock(BlockPos pos) {
        IBlockState state = getWorld().getBlockState(pos);
        NonNullList<ItemStack> drops = NonNullList.create();
        state.getBlock().getDrops(drops, getWorld(), pos, state, getTier());
        getWorld().setBlockToAir(pos);
        getWorld().notifyBlockUpdate(pos, state, getWorld().getBlockState(pos), 3);
        return drops;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isPickingPipe", isPickingPipes);
        data.setInteger("drillY", drillY);
        data.setInteger("radiusConfig", radiusConfig);
        data.setBoolean("active", canWork);
        data.setInteger("progress", progressTime);
        data.setInteger("maxProgress", maxProgressTime);
        data.setTag("ChargerInventory", chargerInventory.serializeNBT());
        data.setInteger("OutputFacing", getOutputFacing().getIndex());
        data.setBoolean("AutoOutputItems", autoOutputItems);
        data.setBoolean("AllowInputFromOutputSide", allowInputFromOutputSide);
        data.setBoolean("working", isWorking);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        isPickingPipes = data.getBoolean("isPickingPipe");
        drillY = data.getInteger("drillY");
        canWork = data.getBoolean("active");
        progressTime = data.getInteger("progress");
        maxProgressTime = data.getInteger("maxProgress");
        if (data.hasKey("radiusConfig"))
            radiusConfig = data.getInteger("radiusConfig");
        this.chargerInventory.deserializeNBT(data.getCompoundTag("ChargerInventory"));
        this.outputFacing = EnumFacing.VALUES[data.getInteger("OutputFacing")];
        this.autoOutputItems = data.getBoolean("AutoOutputItems");
        this.allowInputFromOutputSide = data.getBoolean("AllowInputFromOutputSide");
        this.isWorking = data.getBoolean("working");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeByte(getOutputFacing().getIndex());
        buf.writeBoolean(autoOutputItems);
        buf.writeBoolean(isWorking);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.outputFacing = EnumFacing.VALUES[buf.readByte()];
        this.autoOutputItems = buf.readBoolean();
        this.isWorking = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 100) {
            this.outputFacing = EnumFacing.VALUES[buf.readByte()];
            getHolder().scheduleChunkForRenderUpdate();
        } else if (dataId == 101) {
            this.autoOutputItems = buf.readBoolean();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing) {
        //use direct outputFacing field instead of getter method because otherwise
        //it will just return SOUTH for null output facing
        return super.isValidFrontFacing(facing) && facing != outputFacing;
    }

    public void setOutputFacing(EnumFacing outputFacing) {
        this.outputFacing = outputFacing;
        if (!getWorld().isRemote) {
            getHolder().notifyBlockUpdate();
            writeCustomData(100, buf -> buf.writeByte(outputFacing.getIndex()));
            markDirty();
        }
    }

    public void setAutoOutputItems(boolean autoOutputItems) {
        this.autoOutputItems = autoOutputItems;
        if (!getWorld().isRemote) {
            writeCustomData(101, buf -> buf.writeBoolean(autoOutputItems));
            markDirty();
        }
    }

    public void setAllowInputFromOutputSide(boolean allowInputFromOutputSide) {
        this.allowInputFromOutputSide = allowInputFromOutputSide;
        if(!getWorld().isRemote) {
            markDirty();
        }
    }

    @Override
    public void setFrontFacing(EnumFacing frontFacing) {
        super.setFrontFacing(frontFacing);
        if (this.outputFacing == null) {
            //set initial output facing as opposite to front
            setOutputFacing(frontFacing.getOpposite());
        }
    }

    public EnumFacing getOutputFacing() {
        return outputFacing == null ? EnumFacing.SOUTH : outputFacing;
    }

    public boolean isAutoOutputItems() {
        return autoOutputItems;
    }

    @Override
    public boolean isAutoOutputFluids() {
        return false;
    }

    public boolean isAllowInputFromOutputSide() {
        return allowInputFromOutputSide;
    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        super.clearMachineInventory(itemBuffer);
        clearInventory(itemBuffer, chargerInventory);
    }

    public ArrayList<String> getTOPText() {
        ArrayList<String> text = new ArrayList<>();
        text.add("Radius: "+radiusConfig);
        text.add("Energy consumption: "+ENERGY[getTier()]+" eu/t");
        return text;
    }

    //this DOES NOT include machine control widgets or binds player inventory
    //to be called in order to offset widgets and slots from the top of the window
    public ModularUI.Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new ProgressWidget(progressSupplier, 77, 22 + yOffset, 21, 20, GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL));
        addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
        return builder;
    }

    public ModularUI.Builder createUITemplate(MetaTileEntity te, DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(te);
        builder.widget(new ProgressWidget(progressSupplier, 77, 22, 20, 20, GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL));
        addInventorySlotGroup(builder, importItems, importFluids, false);
        addInventorySlotGroup(builder, exportItems, exportFluids, true);
        return builder;
    }

    protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isOutputs) {
        addInventorySlotGroup(builder, itemHandler, fluidHandler, isOutputs, 0);
    }

    protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
        int itemInputsCount = itemHandler.getSlots();
        int fluidInputsCount = fluidHandler.getTanks();
        boolean invertFluids = false;
        if (itemInputsCount == 0) {
            int tmp = itemInputsCount;
            itemInputsCount = fluidInputsCount;
            fluidInputsCount = tmp;
            invertFluids = true;
        }
        int[] inputSlotGrid = determineSlotsGrid(itemInputsCount);
        int itemSlotsToLeft = inputSlotGrid[0];
        int itemSlotsToDown = inputSlotGrid[1];
        int startInputsX = isOutputs ? 106 : 69 - itemSlotsToLeft * 18;
        int startInputsY = 32 - (int) (itemSlotsToDown / 2.0 * 18) + yOffset;
        for (int i = 0; i < itemSlotsToDown; i++) {
            for (int j = 0; j < itemSlotsToLeft; j++) {
                int slotIndex = i * itemSlotsToLeft + j;
                int x = startInputsX + 18 * j;
                int y = startInputsY + 18 * i;
                addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, invertFluids, isOutputs);
            }
        }
        if (fluidInputsCount > 0 || invertFluids) {
            if (itemSlotsToDown >= fluidInputsCount && itemSlotsToLeft < 3) {
                int startSpecX = isOutputs ? startInputsX + itemSlotsToLeft * 18 : startInputsX - 18;
                for (int i = 0; i < fluidInputsCount; i++) {
                    int y = startInputsY + 18 * i;
                    addSlot(builder, startSpecX, y, i, itemHandler, fluidHandler, !invertFluids, isOutputs);
                }
            } else {
                int startSpecY = startInputsY + itemSlotsToDown * 18;
                for (int i = 0; i < fluidInputsCount; i++) {
                    int x = isOutputs ? startInputsX + 18 * (i % 3) : startInputsX + itemSlotsToLeft * 18 - 18 - 18 * (i % 3);
                    int y = startSpecY + (i / 3) * 18;
                    addSlot(builder, x, y, i, itemHandler, fluidHandler, !invertFluids, isOutputs);
                }
            }
        }
    }

    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
        if (!isFluid) {
            builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                .setBackgroundTexture(getOverlaysForSlot(isOutputs, false, slotIndex == itemHandler.getSlots() - 1)));
        } else {
            builder.widget(new TankWidget(fluidHandler.getTankAt(slotIndex), x, y, 18, 18)
                .setAlwaysShowFull(true)
                .setBackgroundTexture(getOverlaysForSlot(isOutputs, true, slotIndex == fluidHandler.getTanks() - 1))
                .setContainerClicking(true, !isOutputs));
        }
    }

    protected TextureArea[] getOverlaysForSlot(boolean isOutput, boolean isFluid, boolean isLast) {
        if (isOutput) {
            return new TextureArea[]{GuiTextures.SLOT, GuiTextures.ORE_OVERLAY};
        } else {
            return new TextureArea[]{GuiTextures.SLOT, GuiTextures.PIPE_OVERLAY};
        }
    }

    protected static int[] determineSlotsGrid(int itemInputsCount) {
        int itemSlotsToLeft = 0;
        int itemSlotsToDown = 0;
        double sqrt = Math.sqrt(itemInputsCount);
        if (sqrt % 1 == 0) { //check if square root is integer
            //case for 1, 4, 9 slots - it's square inputs (the most common case)
            itemSlotsToLeft = itemSlotsToDown = (int) sqrt;
        } else if (itemInputsCount % 3 == 0) {
            //case for 3 and 6 slots - 3 by horizontal and i / 3 by vertical (common case too)
            itemSlotsToDown = itemInputsCount / 3;
            itemSlotsToLeft = 3;
        } else if (itemInputsCount % 2 == 0) {
            //case for 2 inputs - 2 by horizontal and i / 3 by vertical (for 2 slots)
            itemSlotsToDown = itemInputsCount / 2;
            itemSlotsToLeft = 2;
        }
        return new int[]{itemSlotsToLeft, itemSlotsToDown};
    }

    protected ModularUI.Builder createGuiTemplate(EntityPlayer player) {
        int yOffset = FONT_HEIGHT;

        ModularUI.Builder builder = createUITemplate(() -> (double) this.progressTime / this.maxProgressTime, importItems, exportItems, importFluids, exportFluids, yOffset)
            .widget(new LabelWidget(7, 5, getMetaFullName()))
            .widget(new DischargerSlotWidget(chargerInventory, 0, 79, 62 + yOffset)
                .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CHARGER_OVERLAY))
            .widget(new ImageWidget(79, 42 + yOffset, 18, 18, GuiTextures.INDICATOR_NO_ENERGY)
                .setPredicate(() -> !this.hasEnergy()))
            .bindPlayerInventory(player.inventory, GuiTextures.SLOT, yOffset);

        int rightButtonStartX = 176-40;

        if (exportItems.getSlots() > 0) {
            builder.widget(new ToggleButtonWidget(rightButtonStartX, 62 + yOffset, 18, 18,
                GuiTextures.BUTTON_ITEM_OUTPUT, this::isAutoOutputItems, this::setAutoOutputItems)
                .setTooltipText("gtr.gui.item_auto_output.tooltip"));
        }
        return builder;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return createGuiTemplate(entityPlayer).build(getHolder(), entityPlayer);
    }
}
