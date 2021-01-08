package gtr.common.items.behaviors;

import gtr.api.GTValues;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.items.metaitem.MetaItem;
import gtr.common.ConfigHolder;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

import java.util.List;

public class MagnetBehaviour extends ToggleEnergyConsumerBehavior {

    private static final ResourceLocation OVERRIDE_KEY_LOCATION = new ResourceLocation(GTValues.MODID, "magnet_active");

    public MagnetBehaviour() {
        super(0);
    }

    @Override
    public void onAddedToItem(MetaItem.MetaValueItem metaValueItem) {
        metaValueItem.getMetaItem().addPropertyOverride(OVERRIDE_KEY_LOCATION, (stack, worldIn, entityIn) -> isItemActive(stack) ? 1.0f : 0.0f);
    }

    public void addInformation(ItemStack itemStack, List<String> lines) {
        lines.add("Radius: "+ConfigHolder.magnetRange);
    }

    public void onUpdate(ItemStack stack, Entity entity) {
        if (!entity.isSneaking() && entity.ticksExisted % 10 == 0 && isItemActive(stack) && entity instanceof EntityPlayer) {
            World world = entity.getEntityWorld();
            int range = ConfigHolder.magnetRange;

            List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.posX, entity.posY, entity.posZ).grow(range, range, range));

            for (EntityItem itemEntity : items) {

                if (itemEntity.isDead) {
                    continue;
                }

                NBTTagCompound itemTag = itemEntity.getEntityData();
                if (itemTag.hasKey("PreventRemoteMovement")) {
                    continue;
                }

                String s = itemEntity.getThrower();
                String s2 = entity.getName();
                if (s != null && s2 != null && s.equals(s2)) {
                    int i = itemEntity.pickupDelay;
                    if (i > 0) {
                        continue;
                    }
                }

                EntityPlayer closest = world.getClosestPlayerToEntity(itemEntity, 4);
                if (closest != null && closest != entity) {
                    continue;
                }

                BlockPos pos = new BlockPos(itemEntity);
                boolean blocked = false;
                for (BlockPos checkPos : BlockPos.getAllInBox(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
                    if (world.getBlockState(checkPos).getBlock() == MetaBlocks.MAGNET_INHIBITOR) {
                        blocked = true;
                        break;
                    }
                }

                if (blocked || stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).getCharge() < ConfigHolder.euPerMagnetPickup) {
                    continue;
                }

                if (!world.isRemote) {
                    if (itemEntity.pickupDelay > 0) {
                        itemEntity.pickupDelay = 0;
                    }
                    itemEntity.motionX = itemEntity.motionY = itemEntity.motionZ = 0;
                    ((WorldServer) itemEntity.world).spawnParticle(EnumParticleTypes.SPELL_WITCH, false,
                        itemEntity.posX, itemEntity.posY, itemEntity.posZ, 4, 0.0, 0.0, 0.0, 0.1);
                    itemEntity.setPosition(entity.posX - 0.2 + (world.rand.nextDouble() * 0.4), entity.posY - 0.6, entity.posZ - 0.2 + (world.rand.nextDouble() * 0.4));
                    stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).discharge(ConfigHolder.euPerMagnetPickup, GTValues.LV, true, false, false);
                }
            }

            List<EntityXPOrb> xp = world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.posX, entity.posY, entity.posZ).grow(4, 4, 4));

            EntityPlayer player = (EntityPlayer) entity;

            for (EntityXPOrb orb : xp) {
                if (!world.isRemote && !orb.isDead) {
                    if (orb.delayBeforeCanPickup == 0) {
                        if (MinecraftForge.EVENT_BUS.post(new PlayerPickupXpEvent(player, orb)) || stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).getCharge() < ConfigHolder.euPerMagnetPickup) {
                            continue;
                        }
                        world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, 0.5F * ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.8F));
                        player.onItemPickup(orb, 1);
                        player.addExperience(orb.xpValue);
                        orb.setDead();
                        stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null).discharge(ConfigHolder.euPerMagnetPickup, GTValues.LV, true, false, false);
                    }
                }
            }
        }
    }
}
