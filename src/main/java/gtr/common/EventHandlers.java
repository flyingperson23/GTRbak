package gtr.common;

import gtr.api.GTValues;
import gtr.api.items.toolitem.ItemCatcher;
import gtr.api.pipenet.Node;
import gtr.common.pipelike.cable.WireProperties;
import gtr.common.pipelike.cable.net.WorldENet;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandlers {

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
            } else if (IC2Handler.isAcceptor(te)) {
                WorldENet.getWorldENet(e.getWorld()).addNode(e.getPos(), new WireProperties(1, 1, 0), Node.DEFAULT_MARK, 0, true);
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
}
