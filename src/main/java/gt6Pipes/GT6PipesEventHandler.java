package gt6Pipes;

import gt6Pipes.client.Renderer;
import gt6Pipes.compat.ICompatBase;
import gt6Pipes.compat.IWrenchProvider;
import gt6Pipes.network.ConnectionGrid;
import gt6Pipes.network.MessageGetConnections;
import gt6Pipes.util.BlockWrapper;
import gt6Pipes.util.Utils;
import gtr.api.capability.tool.IWrenchItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GT6PipesEventHandler {
    @SubscribeEvent
    public void onEvent(BlockEvent.PlaceEvent event) {
        if (!event.getWorld().isRemote) {
            for (int j = 0; j < GT6Pipes.instance.COMPAT_LIST.size(); j++) {
            ICompatBase i = GT6Pipes.instance.COMPAT_LIST.get(j);
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

                        if (i.canConnect(block, facing) && lookingAt) {
                            i.connect(block, facing, event.getPlayer());
                            i.connect(block.offset(facing), facing.getOpposite(), event.getPlayer());
                        } else {
                            i.disconnect(block, facing, event.getPlayer());
                            i.disconnect(block.offset(facing), facing.getOpposite(), event.getPlayer());
                        }
                        GT6Pipes.BETTER_PIPES_NETWORK_WRAPPER.sendToServer(new MessageGetConnections(pos, j));
                        GT6Pipes.BETTER_PIPES_NETWORK_WRAPPER.sendToServer(new MessageGetConnections(pos.offset(facing, 1), j));
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
                                GT6Pipes.BETTER_PIPES_NETWORK_WRAPPER.sendToServer(new MessageGetConnections(side.pos, j));
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEvent(TickEvent event) {
        GT6Pipes.instance.counter++;
        if (GT6Pipes.instance.counter % 3 == 0) {
            GT6Pipes.instance.wrenchMap.clear();
        }
    }

    @SubscribeEvent
    public void onEvent(PlayerInteractEvent event) {
        if (!(event instanceof PlayerInteractEvent.LeftClickEmpty | event instanceof  PlayerInteractEvent.LeftClickBlock)) {
            RayTraceResult lookingAt = Utils.getBlockLookingAtIgnoreBB(event.getEntityPlayer());
            for (int i = 0; i < GT6Pipes.instance.COMPAT_LIST.size(); i++) {
                ICompatBase compat = GT6Pipes.instance.COMPAT_LIST.get(i);
                if (lookingAt != null) {
                    BlockWrapper b = new BlockWrapper(lookingAt.getBlockPos(), event.getEntityPlayer());
                    if (compat.isAcceptable(b)) {
                        if (!event.getEntityPlayer().isSneaking()) {
                            for (IWrenchProvider c : GT6Pipes.instance.WRENCH_PROVIDERS) {
                                if (c.enable() && c.canBeUsed(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer()) && c.isAcceptable(event.getEntityPlayer().getHeldItemMainhand(), b)) {
                                    if (event.isCancelable()) event.setCanceled(true);
                                    if (!GT6Pipes.instance.wrenchMap.contains(lookingAt.getBlockPos())) {
                                        GT6Pipes.instance.wrenchMap.add(lookingAt.getBlockPos());
                                        if (Utils.wrenchUse(event, i)) {
                                            c.use(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer());
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            RayTraceResult lookingAt = Utils.getBlockLookingAtIgnoreBB(player);
            if (lookingAt != null) {
                BlockPos pos = lookingAt.getBlockPos();
                for (int i = 0; i < GT6Pipes.instance.COMPAT_LIST.size(); i++) {
                    ICompatBase compat = GT6Pipes.instance.COMPAT_LIST.get(i);
                    BlockWrapper b = new BlockWrapper(pos, player);
                    if (compat.isAcceptable(b) && Utils.isValidWrench(player.getHeldItemMainhand(), b)) {
                        GT6Pipes.BETTER_PIPES_NETWORK_WRAPPER.sendToServer(new MessageGetConnections(pos, i));
                        if (ConnectionGrid.instance() != null) {
                            if (ConnectionGrid.instance().get(pos) != null) {
                                Renderer.renderOverlay(player, pos, lookingAt.sideHit, event.getPartialTicks(), ConnectionGrid.instance().get(pos).connections);
                                EnumFacing directionHovered = Utils.getDirection(lookingAt.sideHit, lookingAt.hitVec);
                                if (directionHovered != null)
                                    Renderer.drawOutline(player, pos.offset(directionHovered, 1), event.getPartialTicks());
                            }
                        }
                    }
                }
            }
        }
        if (GT6Pipes.instance.counter % 100 == 0) ConnectionGrid.instance().clear();
    }
}