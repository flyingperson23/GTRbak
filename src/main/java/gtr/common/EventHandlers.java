package gtr.common;

import akka.japi.Pair;
import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import gtr.api.items.armor.ArmorMetaItem;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.items.toolitem.ItemCatcher;
import gtr.api.net.KeysPacket;
import gtr.api.pipenet.Node;
import gtr.api.util.BaublesHelper;
import gtr.common.armor.ArmorLogicSuite;
import gtr.common.armor.ArmorUtils;
import gtr.common.input.Key;
import gtr.common.input.Keybinds;
import gtr.common.items.MetaItems;
import gtr.common.pipelike.cable.WireProperties;
import gtr.common.pipelike.cable.net.WorldENet;
import gtr.common.render.InvOverlayRenderer;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@Mod.EventBusSubscriber
public class EventHandlers {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ArmorUtils.SIDE.isClient()) {
            boolean needNewPacket = false;
            for (Key key : Keybinds.REGISTERY) {
                boolean keyState = key.getBind().isKeyDown();
                if (key.state != keyState) {
                    key.state = keyState;
                    needNewPacket = true;
                }
            }
            if (needNewPacket) GregTechMod.DISPLAY_INFO_WRAPPER.sendToServer(new KeysPacket(Keybinds.REGISTERY));
        }
    }


    @SubscribeEvent
    public static void onPlaced(BlockEvent.PlaceEvent e) {
        TileEntity te = e.getWorld().getTileEntity(e.getPos());
        if (te != null) {
            boolean flag = false;
            for (EnumFacing facing : EnumFacing.VALUES) {
                if (te.hasCapability(CapabilityEnergy.ENERGY, facing)) flag = true;
            }
            if (flag) {
                WorldENet.getWorldENet(e.getWorld()).addNode(e.getPos(), new WireProperties(1, 1, 0), Node.DEFAULT_MARK, 0, true);
            } else if (Loader.isModLoaded("ic2") && IC2Handler.isAcceptable(te)) {
                WorldENet.getWorldENet(e.getWorld()).addNode(e.getPos(), new WireProperties(1, 1, 0), Node.DEFAULT_MARK, 0, true);
            }
        }
    }

    private static int c1;
    private static int c2;
    private static boolean c3;
    private static final Random rand = new Random();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onToolTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty() && stack.getItem() instanceof ItemTool && stack.hasTagCompound()
            && stack.getTagCompound().hasKey("magnetic")) {
            event.getToolTip().add(
                TextFormatting.YELLOW + "" + TextFormatting.ITALIC + I18n.format("tooltip.magnetic"));
        }

        if (event.getEntityPlayer() != null && stack.getItem() instanceof MetaItem<?>) {
            MetaItem<?>.MetaValueItem i = ((MetaItem<?>) stack.getItem()).getItem(stack);
            if (i == MetaItems.CREATIVE_BATTERY) {
                event.getToolTip().add(TextFormatting.RED + "Do you really want to use this?");
                event.getToolTip().add((rand.nextDouble() < 0.001D ? TextFormatting.DARK_PURPLE : TextFormatting.DARK_GRAY)+"This demonic object harnesses infinite energy through an unstable rift in spacetime");
                if (rand.nextDouble() < 0.001 && c1 == 0) c1 = 1;
                if (c1 > 0) {
                    c1++;
                    event.getToolTip().add((rand.nextDouble() < 0.3D ? TextFormatting.YELLOW : TextFormatting.RED)+"May summon demonic eldritch horrors");
                }
                if (c1 > 15) c1 = 0;

                if (rand.nextDouble() < 0.00001 && c2 == 0) {
                    c2 = 1;
                    c3 = false;
                }
                if (c2 > 0) {
                    c2++;
                    event.getToolTip().add(TextFormatting.RED+"THEY'RE WATCHING YOU");
                    if (rand.nextDouble() < 0.3D) c3 = true;
                    if (c3) event.getToolTip().add(TextFormatting.DARK_RED+"Our bLOod is ON YOur haNDS");
                }
                if (c2 > 10) c2 = 0;
            }
        }
        if (event.getEntityPlayer() != null && stack.getItem() instanceof ArmorMetaItem<?>) {
            ArmorMetaItem<?>.ArmorMetaValueItem armorMetaValue = ((ArmorMetaItem<?>) stack.getItem()).getItem(stack);
            
            if (armorMetaValue == MetaItems.QUANTUM_HELMET) {
                if (GuiScreen.isShiftKeyDown()) {
                    event.getToolTip().add(TextFormatting.GREEN + "Night Vision: ALT Menu + Mode Switch");
                } else {
                    event.getToolTip().add(TextFormatting.GREEN + "Shift to see keybind combinations");
                }
                event.getToolTip().add(TextFormatting.GOLD + "Feeds player from food in inventory automatically");
                event.getToolTip().add(TextFormatting.GOLD + "Lets player breathe in water indefinitely");
            }

            if (armorMetaValue == MetaItems.QUANTUM_SUIT_JETPACK) {
                if (GuiScreen.isShiftKeyDown()) {
                    event.getToolTip().add(TextFormatting.GREEN + "Supply Mode: Shift Right-Click With Item");
                    event.getToolTip().add(TextFormatting.GREEN + "Fly Mode: Fly Key");
                    event.getToolTip().add(TextFormatting.GREEN + "Hover Mode: Space + Mode Switch");
                } else {
                    event.getToolTip().add(TextFormatting.GREEN + "Shift to see keybind combinations");
                }
            }

            if (armorMetaValue == MetaItems.QUANTUM_LEGGINGS) {
                if (GuiScreen.isShiftKeyDown()) {
                    event.getToolTip().add(TextFormatting.GREEN + "Ctrl + Jump: High Jump");
                } else {
                    event.getToolTip().add(TextFormatting.GREEN + "Shift to see keybind combinations");
                }
                event.getToolTip().add(TextFormatting.GOLD + "Has inertia cancellation");
            }

            if (armorMetaValue == MetaItems.NANO_HELMET) {
                if (GuiScreen.isShiftKeyDown()) {
                    event.getToolTip().add(TextFormatting.GREEN + "Night Vision: ALT Menu + Mode Switch");
                } else {
                    event.getToolTip().add(TextFormatting.GREEN + "Shift to see keybind combinations");
                }
            }

            if (armorMetaValue == MetaItems.NANO_SUIT_JETPACK) {
                if (GuiScreen.isShiftKeyDown()) {
                    event.getToolTip().add(TextFormatting.GREEN + "Supply Mode: Shift Right-Click With Item");
                    event.getToolTip().add(TextFormatting.GREEN + "Fly Mode: Fly Key");
                    event.getToolTip().add(TextFormatting.GREEN + "Hover Mode: Space + Mode Switch");
                } else {
                    event.getToolTip().add(TextFormatting.GREEN + "Shift to see keybind combinations");
                }
            }

            if (armorMetaValue == MetaItems.ELECTRIC_JETPACK) {
                if (GuiScreen.isShiftKeyDown()) {
                    event.getToolTip().add(TextFormatting.GREEN + "Fly Mode: Fly Key");
                    event.getToolTip().add(TextFormatting.GREEN + "Hover Mode: Space + Mode Switch");
                } else {
                    event.getToolTip().add(TextFormatting.GREEN + "Shift to see keybind combinations");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEvent(EntityJoinWorldEvent event) {
        ItemCatcher.entityJoinWorld(event);
    }

    @SubscribeEvent
    public static void onEndermanTeleportEvent(EnderTeleportEvent event) {
        if (event.getEntity() instanceof EntityEnderman && event.getEntityLiving()
            .getActivePotionEffect(MobEffects.WEAKNESS) != null) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GTValues.MODID)) {
            ConfigManager.sync(GTValues.MODID, Config.Type.INSTANCE);
        }
    }


    @SubscribeEvent
    public void onEvent(PlayerInteractEvent event) {
        if (event instanceof PlayerInteractEvent.RightClickBlock) {
            ItemStack stack = event.getItemStack();
            if (!stack.isEmpty() && stack.getItem() == Items.FLINT_AND_STEEL) {
                if (!event.getWorld().isRemote
                    && !event.getEntityPlayer().capabilities.isCreativeMode
                    && event.getWorld().rand.nextInt(100) >= ConfigHolder.flintChanceToCreateFire) {
                    stack.damageItem(1, event.getEntityPlayer());
                    if (stack.getItemDamage() >= stack.getMaxDamage()) {
                        stack.shrink(1);
                    }
                    event.setCanceled(true);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRender(final TickEvent.RenderTickEvent event) {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.inGameHasFocus && mc.world != null && !mc.gameSettings.showDebugInfo && Minecraft.isGuiEnabled()) {
            final ItemStack item = mc.player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex());
            if (item.getItem() instanceof ArmorMetaItem) {
                ArmorMetaItem<?>.ArmorMetaValueItem armorMetaValue = ((ArmorMetaItem<?>) item.getItem()).getItem(item);
                if (armorMetaValue.getArmorLogic() instanceof ArmorLogicSuite) {
                    ArmorLogicSuite armorLogic = (ArmorLogicSuite) armorMetaValue.getArmorLogic();
                    if (armorLogic.isNeedDrawHUD()) {
                        armorLogic.drawHUD(item);
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onEntityLivingFallEvent(LivingFallEvent event) {
        if (!event.getEntity().getEntityWorld().isRemote && event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            ItemStack armor = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            final ItemStack NANO = MetaItems.NANO_BOOTS.getStackForm();
            final ItemStack QUANTUM = MetaItems.QUANTUM_BOOTS.getStackForm();
            int fallDamage;
            if (armor.isItemEqual(NANO)) {
                fallDamage = MathHelper.floor(event.getDistance() - 3.0);
                if (fallDamage >= 8) return;
            } else if (armor.isItemEqual(QUANTUM)) {
                fallDamage = Math.max((int) event.getDistance() - 10, 0);
            } else {
                return;
            }
            ArmorMetaItem<?>.ArmorMetaValueItem armorMetaValue = ((ArmorMetaItem<?>) armor.getItem()).getItem(armor);
            ArmorLogicSuite armorLogic = (ArmorLogicSuite) armorMetaValue.getArmorLogic();
            IElectricItem item = armor.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            if (item == null) return;
            int energyCost = armorLogic.getEnergyPerUse() * fallDamage;
            if (item.getCharge() >= energyCost) {
                item.discharge(energyCost, item.getTier(), true, false, false);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDamagedEvent(LivingDamageEvent event) {
        if (!event.getEntity().getEntityWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();
            ItemStack h = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack c = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack l = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack b = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (h == MetaItems.QUANTUM_HELMET.getStackForm() &&
                (c == MetaItems.QUANTUM_CHESTPLATE.getStackForm() || c == MetaItems.QUANTUM_SUIT_JETPACK.getStackForm())
            && l == MetaItems.QUANTUM_LEGGINGS.getStackForm() && b == MetaItems.QUANTUM_BOOTS.getStackForm()) {
                float damagePerArmorPiece = event.getAmount() / 4;

                ArmorMetaItem<?>.ArmorMetaValueItem helmet = ((ArmorMetaItem<?>) h.getItem()).getItem(h);
                ArmorMetaItem<?>.ArmorMetaValueItem chestplate = ((ArmorMetaItem<?>) c.getItem()).getItem(c);
                ArmorMetaItem<?>.ArmorMetaValueItem leggings = ((ArmorMetaItem<?>) l.getItem()).getItem(l);
                ArmorMetaItem<?>.ArmorMetaValueItem boots = ((ArmorMetaItem<?>) b.getItem()).getItem(b);

                IElectricItem helmetElectric = h.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                IElectricItem chestplateElectric = c.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                IElectricItem leggingsElectric = l.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                IElectricItem bootsElectric = b.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);

                if (helmetElectric != null && chestplateElectric != null && leggingsElectric != null && bootsElectric != null) {
                    if (helmetElectric.getCharge() >= damagePerArmorPiece * ((ArmorLogicSuite) helmet.getArmorLogic()).getEnergyPerUse()
                    && chestplateElectric.getCharge() >= damagePerArmorPiece * ((ArmorLogicSuite) chestplate.getArmorLogic()).getEnergyPerUse()
                    && leggingsElectric.getCharge() >= damagePerArmorPiece * ((ArmorLogicSuite) leggings.getArmorLogic()).getEnergyPerUse()
                    && bootsElectric.getCharge() >= damagePerArmorPiece * ((ArmorLogicSuite) boots.getArmorLogic()).getEnergyPerUse()) {
                        helmetElectric.discharge((long) damagePerArmorPiece * ((ArmorLogicSuite) helmet.getArmorLogic()).getEnergyPerUse(), helmetElectric.getTier(), true, false, false);
                        chestplateElectric.discharge((long) damagePerArmorPiece * ((ArmorLogicSuite) chestplate.getArmorLogic()).getEnergyPerUse(), chestplateElectric.getTier(), true, false, false);
                        leggingsElectric.discharge((long) damagePerArmorPiece * ((ArmorLogicSuite) leggings.getArmorLogic()).getEnergyPerUse(), leggingsElectric.getTier(), true, false, false);
                        bootsElectric.discharge((long) damagePerArmorPiece * ((ArmorLogicSuite) boots.getArmorLogic()).getEnergyPerUse(), bootsElectric.getTier(), true, false, false);
                        ((WorldServer) entity.world).spawnParticle(EnumParticleTypes.SPELL_WITCH, false,
                            entity.posX, entity.posY, entity.posZ, 4, 0.0, 0.0, 0.0, 0.1);
                        event.setCanceled(true);

                    }
                }

            }
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onGUIEvent(GuiOpenEvent event) {
        boolean resync = false;
        for (Key current : Keybinds.REGISTERY) {
            if (current.state = true) {
                current.state = false;
                resync = true;
            }
        }
        if (resync) GregTechMod.DISPLAY_INFO_WRAPPER.sendToServer(new KeysPacket(Keybinds.REGISTERY));
    }

    @SideOnly(Side.CLIENT)
    private static long eu = 0;
    @SideOnly(Side.CLIENT)
    private static long maxeu = 0;

    @SubscribeEvent
    public static void render(TickEvent.RenderTickEvent event) {
        if (Minecraft.getMinecraft().player != null) {
            if (GregTechMod.instance.counter % 3 == 0) {
                eu = 0;
                maxeu = 0;
                countItems(Minecraft.getMinecraft().player.inventory.armorInventory);
                countItems(Minecraft.getMinecraft().player.inventory.mainInventory);
                countItems(Minecraft.getMinecraft().player.inventory.offHandInventory);
                if (Loader.isModLoaded("baubles")) {
                    Pair<Long, Long> i = BaublesHelper.getStuff(Minecraft.getMinecraft().player);
                    eu += i.first();
                    maxeu += i.second();
                }
            }
            if (maxeu != 0) {
                InvOverlayRenderer.draw(event, eu, maxeu);
            }
        }


    }

    private static void countItems(NonNullList<ItemStack> items) {
        if (items != null) {
            for (ItemStack s : items) {
                if (s.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                    IEnergyContainer c = s.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
                    eu += c.getEnergyStored();
                    maxeu += c.getEnergyCapacity();
                } else if (s.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) {
                    IElectricItem c = s.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                    eu += c.getCharge();
                    maxeu += c.getMaxCharge();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBossKilled(LivingDeathEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            if (player.getHeldItemMainhand().getItem() instanceof MetaItem<?>) {
                MetaItem.MetaValueItem item = ((MetaItem) player.getHeldItemMainhand().getItem()).getItem(player.getHeldItemMainhand());
                if ((item == MetaItems.NANO_SABER || item == MetaItems.NANO_BOW) && !event.getEntity().isNonBoss()) {
                    ItemStack heldItem = player.getHeldItemMainhand();
                    if (!heldItem.hasTagCompound()) heldItem.setTagCompound(new NBTTagCompound());
                    if (!heldItem.getTagCompound().hasKey("BossKills")) heldItem.getTagCompound().setInteger("BossKills", 0);
                    heldItem.getTagCompound().setInteger("BossKills", 1 + heldItem.getTagCompound().getInteger("BossKills"));
                }
            }
        }
    }
}
