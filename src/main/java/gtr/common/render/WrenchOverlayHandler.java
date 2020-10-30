package gtr.common.render;

import gtr.GregTechMod;
import gtr.api.items.toolitem.wrenchcompat.*;
import gtr.api.net.wrenchnet.ConnectionGrid;
import gtr.api.net.wrenchnet.MessageGetConnections;
import gtr.api.util.GTUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Vector3d;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class WrenchOverlayHandler {

    @SubscribeEvent
    public void onEvent(BlockEvent.PlaceEvent event) {
        if (!event.getWorld().isRemote) {
            for (int j = 0; j < GregTechMod.instance.wrenchHandler.COMPAT_LIST.size(); j++) {
                ICompatBase i = GregTechMod.instance.wrenchHandler.COMPAT_LIST.get(j);
                BlockPos pos = event.getPos();
                BlockWrapper wrapper = new BlockWrapper(event);
                TileEntity block = GTUtility.getTE(wrapper);
                if (block != null && i.isAcceptable(block)) {
                    List<EnumFacing> sidesWithPipeConnection = new ArrayList<>();
                    for (EnumFacing facing : EnumFacing.VALUES) {

                        boolean lookingAt = false;

                        RayTraceResult rt1 = GTUtility.getBlockLookingat1(event.getPlayer());
                        RayTraceResult rt2 = GTUtility.getBlockLookingat2(event.getPlayer(), pos);

                        if (rt1 != null) {
                            if (GTUtility.arePosEqual(rt1.getBlockPos(), pos.offset(facing, 1))) lookingAt = true;
                        }
                        if (rt2 != null) {
                            if (GTUtility.arePosEqual(rt2.getBlockPos(), pos.offset(facing, 1))) lookingAt = true;
                        }

                        if (i.getConnections(GTUtility.getTE(GTUtility.fromTE(block).offset(facing))).contains(facing.getOpposite())) {
                            sidesWithPipeConnection.add(facing);
                        }

                        if (i.canConnect(block, facing) && lookingAt) {
                            i.connect(block, facing, event.getPlayer());
                            if (i.isAcceptable(GTUtility.getTE(wrapper.offset(facing))))
                                i.connect(GTUtility.getTE(wrapper.offset(facing)), facing.getOpposite(), event.getPlayer());
                        } else {
                            i.disconnect(block, facing, event.getPlayer());
                            if (i.isAcceptable(GTUtility.getTE(wrapper.offset(facing))))
                                i.disconnect(GTUtility.getTE(wrapper.offset(facing)), facing.getOpposite(), event.getPlayer());
                        }
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos, j));
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos.offset(facing, 1), j));
                        GTUtility.update(block, facing);
                    }
                    for (EnumFacing e : sidesWithPipeConnection) {
                        i.connect(block, e, event.getPlayer());
                        i.connect(GTUtility.getTE(GTUtility.fromTE(block).offset(e)), e.getOpposite(), event.getPlayer());
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos, j));
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos.offset(e, 1), j));
                        GTUtility.update(block, e);
                    }
                } else {
                    for (EnumFacing facing : EnumFacing.VALUES) {
                        BlockWrapper sideWrapper = wrapper.offset(facing);
                        TileEntity side = GTUtility.getTE(sideWrapper);
                        if (side != null) {
                            if (i.isAcceptable(side)) {


                                boolean lookingAt = false;

                                RayTraceResult rt1 = GTUtility.getBlockLookingat1(event.getPlayer());
                                RayTraceResult rt2 = GTUtility.getBlockLookingat2(event.getPlayer(), pos);


                                if (rt1 != null) {
                                    if (GTUtility.arePosEqual(rt1.getBlockPos(), pos.offset(facing, 1)))
                                        lookingAt = true;
                                }
                                if (rt2 != null) {
                                    if (GTUtility.arePosEqual(rt2.getBlockPos(), pos.offset(facing, 1)))
                                        lookingAt = true;
                                }

                                if (i.canConnect(side, facing.getOpposite()) && lookingAt) {
                                    i.connect(side, facing.getOpposite(), event.getPlayer());
                                }
                                GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(side.getPos(), j));
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEvent(TickEvent event) {
        GregTechMod.instance.counter++;
        if (GregTechMod.instance.counter % 3 == 0) {
            GregTechMod.instance.wrenchHandler.wrenchMap.clear();
        }
    }

    @SubscribeEvent
    public void onEvent(PlayerInteractEvent event) {
        if (!(event instanceof PlayerInteractEvent.LeftClickEmpty | event instanceof  PlayerInteractEvent.LeftClickBlock)) {
            RayTraceResult lookingAt = GTUtility.getBlockLookingAtIgnoreBB(event.getEntityPlayer());
            for (int i = 0; i < GregTechMod.instance.wrenchHandler.COMPAT_LIST.size(); i++) {
                ICompatBase compat = GregTechMod.instance.wrenchHandler.COMPAT_LIST.get(i);
                if (lookingAt != null) {
                    BlockWrapper b = new BlockWrapper(lookingAt.getBlockPos(), event.getEntityPlayer());
                    if (compat.isAcceptable(GTUtility.getTE(b))) {
                        if (!event.getEntityPlayer().isSneaking()) {
                            if (WrenchHelper.enable() && WrenchHelper.canBeUsed(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer()) && WrenchHelper.isAcceptable(event.getEntityPlayer().getHeldItemMainhand(), b)) {
                                if (event.isCancelable()) event.setCanceled(true);
                                if (!GregTechMod.instance.wrenchHandler.wrenchMap.contains(lookingAt.getBlockPos())) {
                                    GregTechMod.instance.wrenchHandler.wrenchMap.add(lookingAt.getBlockPos());
                                    if (GTUtility.wrenchUse(event, i)) {
                                        WrenchHelper.use(event.getEntityPlayer().getHeldItemMainhand(), event.getEntityPlayer());
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

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            RayTraceResult lookingAt = GTUtility.getBlockLookingAtIgnoreBB(player);
            if (lookingAt != null) {
                BlockPos pos = lookingAt.getBlockPos();
                for (int i = 0; i < GregTechMod.instance.wrenchHandler.COMPAT_LIST.size(); i++) {
                    ICompatBase compat = GregTechMod.instance.wrenchHandler.COMPAT_LIST.get(i);
                    BlockWrapper b = new BlockWrapper(pos, player);
                    if (compat.isAcceptable(GTUtility.getTE(b)) && GTUtility.isValidWrench(player.getHeldItemMainhand(), b)) {
                        GregTechMod.WRENCH_NET_WRAPPER.sendToServer(new MessageGetConnections(pos, i));
                        if (ConnectionGrid.instance() != null) {
                            if (ConnectionGrid.instance().get(pos) != null) {
                                WrenchOverlayRenderer.renderOverlay(player, pos, lookingAt.sideHit, event.getPartialTicks(), ConnectionGrid.instance().get(pos).connections);
                                EnumFacing directionHovered = GTUtility.getDirection(lookingAt.sideHit, lookingAt.hitVec);
                                if (directionHovered != null)
                                    WrenchOverlayRenderer.drawOutline(player, pos.offset(directionHovered, 1), event.getPartialTicks());
                            }
                        }
                    }
                }
            }
        }
        if (GregTechMod.instance.counter % 100 == 0) ConnectionGrid.instance().clear();
    }


    public ArrayList<BlockPos> wrenchMap = new ArrayList<>();

    public void postInit() {
        COMPAT_LIST.add(new Item());
        COMPAT_LIST.add(new Fluid());
        COMPAT_LIST.add(new Energy());
    }

    public ArrayList<ICompatBase> COMPAT_LIST = new ArrayList<>();



    public static class BlockWrapper {
        public BlockPos pos;
        public IBlockState state;
        public World world;
        public BlockWrapper(BlockPos pos, IBlockState state, World world) {
            this.pos = pos;
            this.state = state;
            this.world = world;
        }
        public BlockWrapper(BlockPos pos, EntityPlayer player) {
            this(pos, player.world.getBlockState(pos), player.world);
        }
        public BlockWrapper(BlockEvent e) {
            this(e.getPos(), e.getState(), e.getWorld());
        }
        public BlockWrapper offset(EnumFacing direction) {
            BlockPos offsetBlock = pos.offset(direction, 1);
            return new BlockWrapper(offsetBlock, world.getBlockState(offsetBlock), world);
        }
    }

    public static class Matrix4 {

        private static final FloatBuffer glBuf = ByteBuffer.allocateDirect(16 * 8).order(ByteOrder.nativeOrder()).asFloatBuffer();

        public double m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33;

        public Matrix4(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13, double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33) {
            m00 = d00;
            m01 = d01;
            m02 = d02;
            m03 = d03;
            m10 = d10;
            m11 = d11;
            m12 = d12;
            m13 = d13;
            m20 = d20;
            m21 = d21;
            m22 = d22;
            m23 = d23;
            m30 = d30;
            m31 = d31;
            m32 = d32;
            m33 = d33;
        }




        @SideOnly (Side.CLIENT)
        public void glApply() {
            glBuf.put((float) m00).put((float) m10).put((float) m20).put((float) m30).put((float) m01).put((float) m11).put((float) m21).put((float) m31).put((float) m02).put((float) m12).put((float) m22).put((float) m32).put((float) m03).put((float) m13).put((float) m23).put((float) m33);
            glBuf.flip();
            GlStateManager.multMatrix(glBuf);
        }



        @Override
        public int hashCode() {
            long bits = 1L;
            bits = 31L * bits + Double.doubleToLongBits(m00);
            bits = 31L * bits + Double.doubleToLongBits(m01);
            bits = 31L * bits + Double.doubleToLongBits(m02);
            bits = 31L * bits + Double.doubleToLongBits(m03);
            bits = 31L * bits + Double.doubleToLongBits(m10);
            bits = 31L * bits + Double.doubleToLongBits(m11);
            bits = 31L * bits + Double.doubleToLongBits(m12);
            bits = 31L * bits + Double.doubleToLongBits(m13);
            bits = 31L * bits + Double.doubleToLongBits(m20);
            bits = 31L * bits + Double.doubleToLongBits(m21);
            bits = 31L * bits + Double.doubleToLongBits(m22);
            bits = 31L * bits + Double.doubleToLongBits(m23);
            bits = 31L * bits + Double.doubleToLongBits(m30);
            bits = 31L * bits + Double.doubleToLongBits(m31);
            bits = 31L * bits + Double.doubleToLongBits(m32);
            bits = 31L * bits + Double.doubleToLongBits(m33);
            return (int) (bits ^ (bits >> 32));
        }
    }

    public static abstract class Transformation {
        public Matrix4 mat;

        public Transformation(Matrix4 mat) {
            this.mat = mat;
        }

        @SideOnly(Side.CLIENT)
        public void glApply() {
            mat.glApply();
        }

        public abstract void apply(Vector3d v);

    }



}
