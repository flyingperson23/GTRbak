package gtr.common.metatileentities.electric;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.GTValues;
import gtr.api.gui.ModularUI;
import gtr.api.items.toolitem.ToolMetaItem;
import gtr.api.metatileentity.*;
import gtr.api.render.Textures;
import gtr.api.util.GTUtility;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityDieselEngine;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gtr.common.tools.DamageValues;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MetaTileEntityWorldAccelerator extends TieredMetaTileEntity {

    public static final long energyPerTEUpdateTick = 10L;
    public ArrayList<BlockPos> teList = new ArrayList<>();
    private int counter = 0;
    public boolean enabled = true;
    public int range;
    public int speed;

    public MetaTileEntityWorldAccelerator(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.range = tier;
        this.speed = getIterationsMax();

    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityWorldAccelerator(metaTileEntityId, getTier());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.WORLD_ACCELERATOR.render(renderState, translation, pipeline, getFrontFacing(), enabled);
    }

    @Override
    public void update() {
        super.update();
        counter++;
        if (!getWorld().isRemote && enabled) {
            if (counter % 4 == 0) updateList();
            for (BlockPos pos : teList) {
                TileEntity te = getWorld().getTileEntity(pos);
                if (te instanceof ITickable) {
                    for (int i = 0; i < speed; i++) {
                        if (energyContainer.getEnergyStored() >= energyPerTEUpdateTick) {
                            energyContainer.removeEnergy(energyPerTEUpdateTick);
                            ((ITickable) te).update();
                        }
                    }
                }
            }
        }
    }

    public int getIterationsMax() {
        double exp = Math.pow(4, getTier()+1);
        double temp = 0.5 * exp;
        return (int) temp;
    }

    private void updateList() {
        teList.clear();
        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = this.getPos().add(x, y, z);
                    if (getWorld().getBlockState(pos).getBlock().hasTileEntity(getWorld().getBlockState(pos))) {
                        TileEntity te = getWorld().getTileEntity(pos);
                        if (te instanceof ITickable) {
                            if (te instanceof MetaTileEntityHolder) {
                                MetaTileEntity holder = ((MetaTileEntityHolder) te).getMetaTileEntity();
                                if (!(holder instanceof MetaTileEntityWorldAccelerator
                                    || holder instanceof MetaTileEntityBatteryBuffer
                                    || holder instanceof MetaTileEntityCharger
                                    || holder instanceof MetaTileEntityMagicEnergyAbsorber
                                    || holder instanceof MetaTileEntityTransformer
                                    || holder instanceof MetaTileEntityDieselEngine
                                    || holder instanceof MetaTileEntityLargeTurbine)) teList.add(pos);
                            } else {
                                teList.add(pos);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean hasFrontFacing() {
        return false;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.speed = data.getInteger("accelerator.speed");
        this.range = data.getInteger("accelerator.range");
        this.enabled = data.getBoolean("accelerator.enabled");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("accelerator.speed", this.speed);
        data.setInteger("accelerator.range", this.range);
        data.setBoolean("accelerator.enabled", this.enabled);
        return data;
    }

    @Override
    public final boolean onCoverScrewdriverClick(EntityPlayer playerIn, EnumHand hand, CuboidRayTraceResult result) {
        if (playerIn.isSneaking()) {
            if (speed >= getIterationsMax()) {
                speed = 1;
            } else {
                speed *= 2;
            }
            if (!getWorld().isRemote) playerIn.sendMessage(new TextComponentString("Speed: "+speed));
        } else {
            if (range >= getTier()) {
                range = 1;
            } else {
                range++;
            }
            if (!getWorld().isRemote) playerIn.sendMessage(new TextComponentString("Range: "+range));
        }
        updateList();
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {

        if (!(playerIn.world.isRemote || playerIn.world.isAirBlock(getPos()))) {
            if (playerIn.getHeldItem(hand).getItem() instanceof ToolMetaItem) {
                ToolMetaItem<?> t = (ToolMetaItem<?>) playerIn.getHeldItem(hand).getItem();
                if (t.getItem(playerIn.getHeldItem(hand)) == MetaItems.SOFT_HAMMER) {
                    this.enabled = !enabled;
                    GTUtility.doDamageItem(playerIn.getHeldItem(hand), DamageValues.DAMAGE_FOR_SOFT_HAMMER, false);
                    return true;
                }
            }
        }
        if (playerIn.world.isRemote) playerIn.swingArm(hand);
        return false;
    }

    @Override
    protected long getMaxInputOutputAmperage() {
        return 16L;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        String tierName = GTValues.VN[getTier()];


        tooltip.add(I18n.format("gtr.universal.tooltip.voltage_in", energyContainer.getInputVoltage(), tierName));
        tooltip.add(I18n.format("gtr.universal.tooltip.amperage_in_till", getMaxInputOutputAmperage()));
        tooltip.add(I18n.format("gtr.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
    }

}
