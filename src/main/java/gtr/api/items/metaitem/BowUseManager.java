package gtr.api.items.metaitem;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.items.metaitem.stats.IEnchantabilityHelper;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.api.items.metaitem.stats.IItemUseManager;
import gtr.common.ConfigHolder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BowUseManager implements IItemUseManager, IEnchantabilityHelper, IItemBehaviour {

    @Override
    public boolean canStartUsing(ItemStack stack, EntityPlayer player) {
        return true;
    }

    public void addInformation(ItemStack itemStack, List<String> lines) {
        lines.add("Gains 1 heart damage for every boss mob killed");
        int bosskills;
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        if (itemStack.getTagCompound().hasKey("BossKills")) bosskills = itemStack.getTagCompound().getInteger("BossKills");
        else bosskills = 0;
        lines.add("Boss kills: "+bosskills);
    }



    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (itemstack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).getCharge() >= ConfigHolder.nanoBowConfiguration.energyConsumption) {
            if (!worldIn.isRemote)
            {
                itemstack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).discharge(ConfigHolder.nanoBowConfiguration.energyConsumption, 3, true, false, false);
                ItemArrow itemarrow = (ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
                EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, playerIn);
                entityarrow = this.customizeArrow(entityarrow);
                entityarrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 6, 1.0F);
                entityarrow.setIsCritical(true);
                double d = ConfigHolder.nanoBowConfiguration.nanoBowBaseDamage;
                if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("BossKills")) d += itemstack.getTagCompound().getInteger("BossKills");
                entityarrow.setDamage(d);
                entityarrow.setKnockbackStrength(2);
                entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                worldIn.spawnEntity(entityarrow);
            }

            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (new Random().nextFloat() * 0.4F + 1.2F) + 1);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemstack);
    }

    public EntityArrow customizeArrow(EntityArrow arrow)
    {
        return arrow;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 15;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.UNBREAKING &&
            enchantment != Enchantments.MENDING &&
            enchantment.type.canEnchantItem(Items.BOW);
    }
}
