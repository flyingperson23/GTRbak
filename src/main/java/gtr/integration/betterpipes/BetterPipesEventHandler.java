package gtr.integration.betterpipes;

import gtr.GregTechMod;
import gtr.api.capability.GregtechCapabilities;
import gtr.common.pipelike.cable.BlockCable;
import gtr.common.pipelike.cable.tile.TileEntityCable;
import gtr.common.render.WrenchOverlayRenderer;
import gtr.integration.betterpipes.compat.ICompatBase;
import gtr.integration.betterpipes.compat.wrench.IWrenchProvider;
import gtr.integration.betterpipes.network.ConnectionGrid;
import gtr.integration.betterpipes.network.MessageBlockUpdate;
import gtr.integration.betterpipes.network.MessageGetConnections;
import gtr.integration.betterpipes.util.BlockWrapper;
import gtr.integration.betterpipes.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class BetterPipesEventHandler {
    @SubscribeEvent
    public static void onEvent(BlockEvent.PlaceEvent event) {
        if (!event.getWorld().isRemote) {
            for (int j = 0; j < GregTechMod.instance.COMPAT_LIST.size(); j++) {
                ICompatBase i = GregTechMod.instance.COMPAT_LIST.get(j);

                BlockPos pos = event.getPos();
                BlockWrapper block = new BlockWrapper(event);
                if (i.isAcceptable(block)) {
                    for (EnumFacing facing : EnumFacing.VALUES) {

                        boolean lookingAt = false;

                        RayTraceResult rt1 = Utils.getBlockLookingat1(event.getPlayer());
                        RayTraceResult rt2 = Utils.getBlockLookingat2(event.getPlayer(), pos);


                        if (rt1 != null) {
                            if (Utils.arePosEqual(rt1.getBlockPos(), pos.offset(facing, 1))) lookingAt = true;
                        }
                        if (rt2 != null) {
                            if (Utils.arePosEqual(rt2.getBlockPos(), pos.offset(facing, 1))) lookingAt = true;
                        }

                        if (i.canConnect(block, facing) && (lookingAt)) {
                            i.connect(block, facing, event.getPlayer());
                            i.connect(block.offset(facing), facing.getOpposite(), event.getPlayer());
                        } else {
                            i.disconnect(block, facing, event.getPlayer());
                            i.disconnect(block.offset(facing), facing.getOpposite(), event.getPlayer());
                        }
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos, j));
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos.offset(facing, 1), j));
                        Utils.update(block, facing);
                    }
                } else {
                    for (EnumFacing facing : EnumFacing.VALUES) {
                        BlockWrapper side = block.offset(facing);
                        if (side != null) {
                            if (i.isAcceptable(side)) {

                                boolean lookingAt = false;

                                RayTraceResult rt1 = Utils.getBlockLookingat1(event.getPlayer());
                                RayTraceResult rt2 = Utils.getBlockLookingat2(event.getPlayer(), pos);


                                if (rt1 != null) {
                                    if (Utils.arePosEqual(rt1.getBlockPos(), pos.offset(facing, 1)))
                                        lookingAt = true;
                                }
                                if (rt2 != null) {
                                    if (Utils.arePosEqual(rt2.getBlockPos(), pos.offset(facing, 1)))
                                        lookingAt = true;
                                }

                                if (i.canConnect(side, facing.getOpposite()) && lookingAt) {
                                    i.connect(side, facing.getOpposite(), event.getPlayer());
                                } else {
                                    i.disconnect(side, facing.getOpposite(), event.getPlayer());
                                }
                                GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(side.pos, j));
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEvent(TickEvent event) {
        GregTechMod.instance.counter++;
        if (GregTechMod.instance.counter % 3 == 0) {
            GregTechMod.instance.wrenchMap.clear();
        }
    }

    @SubscribeEvent
    public static void onEvent(PlayerInteractEvent event) {
        if (!(event instanceof PlayerInteractEvent.LeftClickEmpty | event instanceof  PlayerInteractEvent.LeftClickBlock)) {
            RayTraceResult lookingAt = Utils.getBlockLookingAtIgnoreBB(event.getEntityPlayer());
            for (int i = 0; i < GregTechMod.instance.COMPAT_LIST.size(); i++) {
                ICompatBase compat = GregTechMod.instance.COMPAT_LIST.get(i);
                if (lookingAt != null) {
                    if (compat.isAcceptable(new BlockWrapper(lookingAt.getBlockPos(), event.getEntityPlayer()))) {
                        if (!event.getEntityPlayer().isSneaking()) {
                            for (IWrenchProvider c : GregTechMod.instance.WRENCH_PROVIDERS) {
                                if (c.canBeUsed(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer()) && c.isAcceptable(event.getEntityPlayer().getHeldItemMainhand(), event.getWorld().getTileEntity(lookingAt.getBlockPos()))) {
                                    if (event.isCancelable()) event.setCanceled(true);
                                    if (!GregTechMod.instance.wrenchMap.contains(lookingAt.getBlockPos())) {
                                        GregTechMod.instance.wrenchMap.add(lookingAt.getBlockPos());
                                        if (Utils.wrenchUse(event, i)) {
                                            c.use(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer());

                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        if (event.getWorld().getTileEntity(lookingAt.getBlockPos()) instanceof TileEntityCable && event.getEntityPlayer().getHeldItemMainhand().hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null) && event.isCancelable()) {
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            RayTraceResult lookingAt = Utils.getBlockLookingAtIgnoreBB(player);
            if (lookingAt != null && player.getEntityWorld().getTileEntity(lookingAt.getBlockPos()) != null) {
                if (Utils.isValidWrench(player.getHeldItemMainhand(), player.getEntityWorld().getTileEntity(lookingAt.getBlockPos()))) {
                    BlockPos pos = lookingAt.getBlockPos();
                    for (int i = 0; i < GregTechMod.instance.COMPAT_LIST.size(); i++) {
                        ICompatBase compat = GregTechMod.instance.COMPAT_LIST.get(i);
                        if (compat.isAcceptable(new BlockWrapper(pos, player))) {
                            if (!Minecraft.getMinecraft().player.isSneaking()) {
                                GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos, i));
                                if (ConnectionGrid.instance() != null) {
                                    if (ConnectionGrid.instance().get(pos) != null) {
                                        WrenchOverlayRenderer.renderOverlay(player, pos, lookingAt.sideHit, event.getPartialTicks(), ConnectionGrid.instance().get(pos).connections);
                                        EnumFacing directionHovered = Utils.getDirection(lookingAt.sideHit, lookingAt.hitVec);
                                        WrenchOverlayRenderer.drawOutline(player, pos.offset(directionHovered, 1), event.getPartialTicks());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (GregTechMod.instance.counter % 100 == 0) ConnectionGrid.instance().clear();
    }
}