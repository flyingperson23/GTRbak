package gtr.common.items.behaviors;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import gtr.api.GTValues;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.items.metaitem.MetaItem.MetaValueItem;
import gtr.api.items.metaitem.stats.IEnchantabilityHelper;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.common.ConfigHolder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class NanoSaberBehavior extends ToggleEnergyConsumerBehavior implements IEnchantabilityHelper {

    private static final ResourceLocation OVERRIDE_KEY_LOCATION = new ResourceLocation(GTValues.MODID, "nano_saber_active");
    protected static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A288-9C13A33DB5CF");
    protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4288-B01B-BCCE9785ACA3");

    public NanoSaberBehavior() {
        super(ConfigHolder.nanoSaberConfiguration.energyConsumption);
    }

    @Override
    public void onAddedToItem(MetaValueItem metaValueItem) {
        metaValueItem.getMetaItem().addPropertyOverride(OVERRIDE_KEY_LOCATION, (stack, worldIn, entityIn) -> isItemActive(stack) ? 1.0f : 0.0f);
        metaValueItem.getStackForm().addEnchantment(Enchantments.FIRE_ASPECT, 2);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        HashMultimap<String, AttributeModifier> modifiers = HashMultimap.create();
        if(slot == EntityEquipmentSlot.MAINHAND) {
            double attackDamage = ConfigHolder.nanoSaberConfiguration.nanoSaberBaseDamage;
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BossKills") && stack.getTagCompound().getInteger("BossKills") > 0) {
                attackDamage += stack.getTagCompound().getInteger("BossKills") * 2;
            }
            if (isItemActive(stack)) attackDamage *= ConfigHolder.nanoSaberConfiguration.nanoSaberDamageMultiplier;
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 1.5, 0));
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon Modifier", attackDamage, 0));
        }
        return modifiers;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 30;
    }

    public void addInformation(ItemStack itemStack, List<String> lines) {
        lines.add("Gains 1 heart damage for every boss mob killed");
        int bosskills;
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        if (itemStack.getTagCompound().hasKey("BossKills")) bosskills = itemStack.getTagCompound().getInteger("BossKills");
        else bosskills = 0;
        lines.add("Boss kills: "+bosskills);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.UNBREAKING &&
            enchantment != Enchantments.MENDING &&
            enchantment.type.canEnchantItem(Items.IRON_SWORD);
    }
}