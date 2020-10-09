package gtr.api.items.toolitem;

import gtr.api.capability.GregtechCapabilities;
import gtr.api.items.IToolItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;

import javax.annotation.Nonnull;

public class ToolHooks {
    static PlayerInteractionManager interactionManager;

    public static void preHarvest(PlayerInteractionManager manager) {
        ItemStack tool = manager.player.getHeldItemMainhand();

        if (tool.getItem() instanceof IToolItem) {
            if (tool.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) {
                ItemCatcher.startCatching();
                interactionManager = manager;
            }
        }

    }

    public static void postHarvest() {

        if (ItemCatcher.isCatching() && interactionManager != null) {
            EntityPlayer player = interactionManager.player;
            for (ItemStack is : ItemCatcher.stopCatching()) {
                ItemStack stack = is.copy();
                EntityItem fakeEntity = new EntityItem(player.world, player.posX, player.posY, player.posZ);
                fakeEntity.setItem(stack);

                EntityItemPickupEvent event = new EntityItemPickupEvent(player, fakeEntity);

                if (!MinecraftForge.EVENT_BUS.post(event)) {

                    giveItemToPlayerSilent(player, stack, -1);
                }
            }

            interactionManager = null;
        }
    }




    public static void giveItemToPlayerSilent(EntityPlayer player, @Nonnull ItemStack stack, int preferredSlot) {

        IItemHandler inventory = new PlayerMainInvWrapper(player.inventory);
        World world = player.world;

        ItemStack remainder = stack;
        if(preferredSlot >= 0) {
            remainder = inventory.insertItem(preferredSlot, stack, false);
        }
        if(!remainder.isEmpty()) {
            remainder = ItemHandlerHelper.insertItemStacked(inventory, remainder, false);
        }

        if (!(player instanceof FakePlayer) && (remainder.isEmpty() || remainder.getCount() != stack.getCount())) {
            world.playSound(null, player.posX, player.posY + 0.5, player.posZ,
                SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }

        if (!remainder.isEmpty() && !world.isRemote) {
            EntityItem entityitem = new EntityItem(world, player.posX, player.posY + 0.5, player.posZ, stack);
            entityitem.setPickupDelay(40);
            entityitem.motionX = 0;
            entityitem.motionZ = 0;

            world.spawnEntity(entityitem);
        }
    }
}
