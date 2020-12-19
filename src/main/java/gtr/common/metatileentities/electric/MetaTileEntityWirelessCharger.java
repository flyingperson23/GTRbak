package gtr.common.metatileentities.electric;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.GTValues;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import gtr.api.gui.ModularUI;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.items.toolitem.ToolMetaItem;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.TieredMetaTileEntity;
import gtr.api.render.Textures;
import gtr.api.util.GTUtility;
import gtr.common.items.MetaItems;
import gtr.common.tools.DamageValues;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaTileEntityWirelessCharger extends TieredMetaTileEntity {

    public boolean enabled = true;
    public int range;
    private int counter = 0;
    private HashMap<EntityPlayer, ArrayList<ItemStack>> cache = new HashMap<>();

    public MetaTileEntityWirelessCharger(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        range = 4 * (int) Math.pow(2, tier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityWirelessCharger(metaTileEntityId, getTier());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.WIRELESS_CHARGER.render(renderState, translation, pipeline, getFrontFacing(), enabled);
    }

    @Override
    public void update() {
        super.update();
        counter++;
        if (!getWorld().isRemote && enabled) {
            if (counter % 4 == 0) updateList();
            for (Map.Entry<EntityPlayer, ArrayList<ItemStack>> entry : cache.entrySet()) {
                if (entry.getKey().isEntityAlive()) {
                    for (ItemStack s : entry.getValue()) {
                        if (s.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) {
                            IElectricItem i = s.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                            if (i != null) {
                                long requestedPower = Math.min(i.getMaxCharge() - i.getCharge(), GTValues.V[i.getTier()]);
                                long powerToDeliver = Math.min(requestedPower, Math.min(energyContainer.getEnergyStored(), GTValues.V[getTier()]));
                                long temp1 = Math.abs(i.charge(powerToDeliver, i.getTier(), false, true));
                                long temp2 = Math.abs(energyContainer.removeEnergy(temp1));
                                i.charge(temp2, i.getTier(), false, false);
                            }
                        } else if (s.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                            IEnergyContainer i = s.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
                            if (i != null) {
                                long requestedPower = Math.min(i.getEnergyCapacity() - i.getEnergyStored(), i.getInputVoltage());
                                long powerToDeliver = Math.min(requestedPower, Math.min(energyContainer.getEnergyStored(), GTValues.V[getTier()]));
                                long temp1 = Math.abs(i.addEnergy(powerToDeliver));
                                energyContainer.removeEnergy(temp1);
                            }
                        } else if (s.hasCapability(CapabilityEnergy.ENERGY, null)) {
                            IEnergyStorage i = s.getCapability(CapabilityEnergy.ENERGY, null);
                            if (i != null) {
                                long requestedPower = ((i.getMaxEnergyStored() - i.getEnergyStored()) / 4); // In EU
                                long powerToDeliver = Math.min(requestedPower, Math.min(energyContainer.getEnergyStored(), GTValues.V[getTier()]));
                                long temp1 = i.receiveEnergy((int) powerToDeliver * 4, false) / 4;
                                energyContainer.removeEnergy(temp1);
                            }
                        } else if (IC2Handler.canItemReceiveEU(s)) {
                            IC2Handler.insertEnergyToItem(s, energyContainer, getTier());
                        }
                    }
                }
            }
        }
    }

    private void updateList() {
        cache.clear();
        for (EntityPlayer p : getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos().add(-range, -range, -range), getPos().add(range+1, range+1, range+1)))) {
            ArrayList<ItemStack> items = new ArrayList<>();
            for (ItemStack s : p.inventoryContainer.getInventory()) {
                if (s.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null) || s.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)
                || s.hasCapability(CapabilityEnergy.ENERGY, null) || IC2Handler.canItemReceiveEU(s)) {
                    items.add(s);
                }
            }
            cache.put(p, items);
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
        this.enabled = data.getBoolean("accelerator.enabled");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("accelerator.enabled", this.enabled);
        return data;
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
