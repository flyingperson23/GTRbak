package gtr.common.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.items.armor.ArmorMetaItem;
import gtr.api.items.armor.ArmorMetaItem.ArmorMetaValueItem;
import gtr.api.items.armor.ISpecialArmorLogic;
import gtr.api.items.metaitem.ElectricStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class ArmorLogicSuite implements ISpecialArmorLogic {

    protected final int energyPerUse;
    protected final int tier;
    protected final int maxCapacity;
    protected final EntityEquipmentSlot SLOT;
    @SideOnly(Side.CLIENT)
    protected ArmorUtils.ModularHUD HUD;

    protected ArmorLogicSuite(int energyPerUse, int maxCapacity, int tier, EntityEquipmentSlot slot) {
        this.energyPerUse = energyPerUse;
        this.maxCapacity = maxCapacity;
        this.tier = tier;
        this.SLOT = slot;
        if (ArmorUtils.SIDE.isClient() && this.isNeedDrawHUD()) {
            HUD = new ArmorUtils.ModularHUD();
        }
    }

    @Override
    public abstract void onArmorTick(World world, EntityPlayer player, ItemStack itemStack);

    @Override
    public int getArmorLayersAmount(ItemStack itemStack) {
        return 1;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, EntityEquipmentSlot equipmentSlot) {
        IElectricItem item = armor.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        int damageLimit = Integer.MAX_VALUE;
        if (source.isUnblockable()) return new ArmorProperties(0, 0.0, 0);
        if (energyPerUse > 0) damageLimit = (int) Math.min(damageLimit, item.getCharge() * 1.0 / energyPerUse * 25.0);
        return new ArmorProperties(0, getAbsorption(armor) * getDamageAbsorption(), damageLimit);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        IElectricItem item = armor.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (item == null) return 0;
        if (item.getCharge() >= energyPerUse) {
            return (int) Math.round(20.0F * this.getAbsorption(armor) * this.getDamageAbsorption());
        } else {
            return (int) Math.round(4.0F * this.getAbsorption(armor) * this.getDamageAbsorption());
        }
    }

    @Override
    public void addToolComponents(ArmorMetaValueItem mvi) {
//		mvi.addStats(ElectricStats.createElectricItem(maxCapacity, tier));
        mvi.addStats(new ElectricStats(maxCapacity, tier, true, false) {
            @Override
            public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
                return onRightClick(world, player, hand);
            }

            @Override
            public void addInformation(ItemStack itemStack, List<String> lines) {
                addInfo(itemStack, lines);
            }
        });
    }

    public void addInfo(ItemStack itemStack, List<String> lines) {
    }

    public ActionResult<ItemStack> onRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (player.getHeldItem(hand).getItem() instanceof ArmorMetaItem) {
            ItemStack armor = player.getHeldItem(hand);
            if (armor.getItem() instanceof ArmorMetaItem && player.inventory.armorInventory.get(SLOT.getIndex()).isEmpty() && !player.isSneaking()) {
                player.inventory.armorInventory.set(SLOT.getIndex(), armor.copy());
                player.setHeldItem(hand, ItemStack.EMPTY);
                player.playSound(new SoundEvent(new ResourceLocation("item.armor.equip_generic")), 1.0F, 1.0F);
                return ActionResult.newResult(EnumActionResult.SUCCESS, armor);
            }
        }

        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack itemStack, DamageSource source, int damage, EntityEquipmentSlot equipmentSlot) {
    }

    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack itemStack) {
        return SLOT;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        return ImmutableMultimap.of();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "";
    }

    public abstract double getDamageAbsorption();

    @SideOnly(Side.CLIENT)
    public boolean isNeedDrawHUD() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void drawHUD(ItemStack stack) {
        this.HUD.draw();
        this.HUD.reset();
    }

    public int getEnergyPerUse() {
        return this.energyPerUse;
    }

    protected float getAbsorption(ItemStack itemStack) {
        switch (this.getEquipmentSlot(itemStack)) {
            case HEAD:
            case FEET:
                return 0.15F;
            case CHEST:
                return 0.4F;
            case LEGS:
                return 0.3F;
            default:
                return 0.0F;
        }
    }
}
