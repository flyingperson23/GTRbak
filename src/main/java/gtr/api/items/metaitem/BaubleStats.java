package gtr.api.items.metaitem;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.items.metaitem.stats.IItemCapabilityProvider;
import gtr.api.items.metaitem.stats.IItemComponent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BaubleStats implements IItemComponent, IItemCapabilityProvider {
    @Override
    public ICapabilityProvider createProvider(ItemStack itemStack) {
        return new ICapabilityProvider() {
            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
            }

            @Nullable
            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                if (capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE) {
                    return (T) new IBauble() {

                        @Override
                        public BaubleType getBaubleType(ItemStack itemStack) {
                            return BaubleType.TRINKET;
                        }


                        @Override
                        public void onWornTick(ItemStack itemStack, EntityLivingBase player) {
                            if (itemStack.getItem() instanceof MetaItem<?>) {
                                MetaItem<?>.MetaValueItem m = ((MetaItem<?>) itemStack.getItem()).getItem(itemStack);
                                m.getBehaviours().stream().forEach(i -> i.onUpdate(itemStack, player));
                            }
                        }

                        public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
                            return true;
                        }

                    };
                }
                return null;
            }
        };
    }

    private static boolean isInDishargeMode(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return tagCompound != null && tagCompound.getBoolean("DischargeMode");
    }

    private static long chargeElectricItem(long maxDischargeAmount, IElectricItem source, IElectricItem target) {
        long maxDischarged = source.discharge(maxDischargeAmount, source.getTier(), false, false, true);
        long maxReceived = target.charge(maxDischarged, source.getTier(), false, true);
        if(maxReceived > 0L) {
            long resultDischarged = source.discharge(maxReceived, source.getTier(), false, true, false);
            target.charge(resultDischarged, source.getTier(), false, false);
            return resultDischarged;
        }
        return 0L;
    }
}
