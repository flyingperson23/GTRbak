package gtr.common.asm;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import ic2.api.crops.CropProperties;
import ic2.api.crops.Crops;
import ic2.api.item.ElectricItem;
import ic2.core.block.comp.Energy;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.crop.cropcard.GenericCropCard;
import ic2.core.item.ItemBatteryChargeHotbar;
import ic2.core.item.type.CropResItemType;
import ic2.core.item.type.DustResourceType;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

import static ic2.core.crop.IC2Crops.*;

public class IC2Hooks {
    public static void emitGTEU(TileEntityBaseGenerator generator) {
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));

            if (te != null) {
                IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                if (container != null) {
                    int voltage = (int) Math.pow(2, 3+2*e.getSourceTier());
                    int packets = Math.min(e.getPacketOutput(), (int) e.getEnergy() / voltage);
                    if (packets > 0) {
                        long ampsUsed = container.acceptEnergyFromNetwork(facing.getOpposite(), voltage, packets);
                        for (int i = 0; i < ampsUsed; i++) {
                            e.useEnergy(voltage);
                        }
                    }
                }
            }
        }
    }

    public static void emitGTEU2(TileEntityElectricBlock generator) {
        Energy e = generator.getComponent(Energy.class);
        for (EnumFacing facing : EnumFacing.VALUES) {
            TileEntity te = generator.getWorld().getTileEntity(generator.getPos().offset(facing));

            if (te != null) {
                IEnergyContainer container = te.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                if (container != null) {
                    int voltage = (int) Math.pow(2, 3+2*e.getSourceTier());
                    int packets = Math.min(e.getPacketOutput(), (int) e.getEnergy() / voltage);
                    if (packets > 0) {
                        long ampsUsed = container.acceptEnergyFromNetwork(facing.getOpposite(), voltage, packets);
                        for (int i = 0; i < ampsUsed; i++) {
                            e.useEnergy(voltage);
                        }
                    }
                }
            }
        }
    }

    public static void chargeHotbar(ItemBatteryChargeHotbar battery, ItemStack stack, World world, Entity entity) {
        if (entity instanceof EntityPlayerMP && world.getTotalWorldTime() % 10L < (long) battery.getTier(stack) && isEnabled(stack)) {
            double limit = battery.getTransferLimit(stack);
            int tier = battery.getTier(stack);
            EntityPlayer thePlayer = (EntityPlayer)entity;
            List<ItemStack> inventory = thePlayer.inventory.mainInventory;
            for(int i = 0; i < 9 && limit > 0.0D; ++i) {
                ItemStack toCharge = inventory.get(i);
                if (!StackUtil.isEmpty(toCharge) && (i != thePlayer.inventory.currentItem) && (toCharge.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) || toCharge.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                    IElectricItem e = toCharge.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                    if (e != null) {
                        double charge = e.getMaxCharge() - e.getCharge();
                        charge = ElectricItem.manager.discharge(stack, charge, tier, true, false, false);
                        e.charge((long) charge, tier, true, false);
                        limit -= charge;
                    } else if (toCharge.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                        IEnergyContainer e2 = toCharge.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
                        if (e2 != null) {
                            double charge = e2.getEnergyCapacity() - e2.getEnergyStored();
                            charge = ElectricItem.manager.discharge(stack, charge, tier, true, false, false);
                            e2.addEnergy((long) charge);
                            limit -= charge;
                        }
                    }
                }
            }
        }
    }

    private static boolean isEnabled(ItemStack stack) {
        if (stack != null && stack.hasTagCompound()) {
            if (!stack.getTagCompound().hasKey("mode")) return true;
            return stack.getTagCompound().getByte("mode") == 0;
        } else return false;
    }

    public static void registerCrops() {
        Crops.instance.registerCrop(weed);
        Crops.instance.registerCrop(cropWheat);
        Crops.instance.registerCrop(cropPumpkin);
        Crops.instance.registerCrop(cropMelon);
        Crops.instance.registerCrop(cropYellowFlower);
        Crops.instance.registerCrop(cropRedFlower);
        Crops.instance.registerCrop(cropBlackFlower);
        Crops.instance.registerCrop(cropPurpleFlower);
        Crops.instance.registerCrop(cropBlueFlower);
        Crops.instance.registerCrop(cropVenomilia);
        Crops.instance.registerCrop(cropReed);
        Crops.instance.registerCrop(cropStickReed);
        Crops.instance.registerCrop(cropCocoa);
        Crops.instance.registerCrop(cropRedwheat);
        Crops.instance.registerCrop(cropNetherWart);
        Crops.instance.registerCrop(cropTerraWart);
        Crops.instance.registerCrop(cropCoffee);
        Crops.instance.registerCrop(cropHops);
        Crops.instance.registerCrop(cropCarrots);
        Crops.instance.registerCrop(cropPotato);
        Crops.instance.registerCrop(cropEatingPlant);
        Crops.instance.registerCrop(cropBeetroot);
        Crops.instance.registerCrop(cropOakSapling);
        Crops.instance.registerCrop(cropSpruceSapling);
        Crops.instance.registerCrop(cropBirchSapling);
        Crops.instance.registerCrop(cropJungleSapling);
        Crops.instance.registerCrop(cropAcaciaSapling);
        Crops.instance.registerCrop(cropDarkOakSapling);
    }

    public static void registerBaseSeeds() {
        Crops.instance.registerBaseSeed(new ItemStack(Items.WHEAT_SEEDS, 1, 32767), cropWheat, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.PUMPKIN_SEEDS, 1, 32767), cropPumpkin, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.MELON_SEEDS, 1, 32767), cropMelon, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.NETHER_WART, 1, 32767), cropNetherWart, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(ItemName.terra_wart.getItemStack(), cropTerraWart, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(ItemName.crop_res.getItemStack(CropResItemType.coffee_beans), cropCoffee, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.REEDS, 1, 32767), cropReed, 1, 3, 0, 2);
        Crops.instance.registerBaseSeed(new ItemStack(Items.DYE, 1, 3), cropCocoa, 1, 0, 0, 0);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.RED_FLOWER, 4, 32767), cropRedFlower, 4, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.YELLOW_FLOWER, 4, 32767), cropYellowFlower, 4, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.CARROT, 1, 32767), cropCarrots, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.POTATO, 1, 32767), cropPotato, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.BROWN_MUSHROOM, 4, 32767), cropBrownMushroom, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.RED_MUSHROOM, 4, 32767), cropRedMushroom, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.CACTUS, 1, 32767), cropEatingPlant, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Items.BEETROOT_SEEDS, 1, 32767), cropBeetroot, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.SAPLING, 1, 0), cropOakSapling, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.SAPLING, 1, 1), cropSpruceSapling, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.SAPLING, 1, 2), cropBirchSapling, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.SAPLING, 1, 3), cropJungleSapling, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.SAPLING, 1, 4), cropAcaciaSapling, 1, 1, 1, 1);
        Crops.instance.registerBaseSeed(new ItemStack(Blocks.SAPLING, 1, 5), cropDarkOakSapling, 1, 1, 1, 1);
    }
}
