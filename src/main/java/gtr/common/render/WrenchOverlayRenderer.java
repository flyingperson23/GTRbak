package gtr.common.render;

import gtr.GregTechMod;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.util.GTUtility;
import gtr.common.items.behaviors.CoverPlaceBehavior;
import gtr.common.items.behaviors.CrowbarBehaviour;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class WrenchOverlayRenderer {


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        World world = player.world;
        RayTraceResult target = GTUtility.getBlockLookingAtIgnoreBB(player);
        if (target != null) {
            BlockPos pos = target.getBlockPos();
            TileEntity tileEntity = world.getTileEntity(pos);
            ItemStack heldItem = player.getHeldItemMainhand();
            if (tileEntity != null && shouldDrawOverlayForItem(heldItem, tileEntity)) {
                renderOverlay(player, pos, target.sideHit, event.getPartialTicks(), new ArrayList<>());
                drawOutline(player, new BlockPos(0, -1, 0), event.getPartialTicks());
            }
        }
    }



    public static boolean shouldDrawOverlayForItem(ItemStack itemStack, TileEntity tileEntity) {
        if(tileEntity instanceof MetaTileEntityHolder &&
            itemStack.hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null)) {
            return true;
        }
        if(tileEntity.hasCapability(GregtechTileCapabilities.CAPABILITY_COVERABLE, null)) {
            if(itemStack.hasCapability(GregtechCapabilities.CAPABILITY_SCREWDRIVER, null)) {
                return true;
            }
            if (itemStack.getItem() instanceof MetaItem) {
                MetaItem<?> metaItem = (MetaItem<?>) itemStack.getItem();
                MetaItem<?>.MetaValueItem valueItem = metaItem.getItem(itemStack);
                if (valueItem != null) {
                    List<IItemBehaviour> behaviourList = valueItem.getBehaviours();
                    return behaviourList.stream().anyMatch(it ->
                        it instanceof CoverPlaceBehavior || it instanceof CrowbarBehaviour);
                }
            }
        }
        return false;
    }


    public static void renderOverlay(EntityPlayer aPlayer, BlockPos pos, EnumFacing aSide, float aPartialTicks, ArrayList<EnumFacing> connections) {
        int aX = pos.getX();
        int aY = pos.getY();
        int aZ = pos.getZ();

        GL11.glPushMatrix();

        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glTranslated(-(aPlayer.lastTickPosX + (aPlayer.posX - aPlayer.lastTickPosX) * aPartialTicks), -(aPlayer.lastTickPosY + (aPlayer.posY - aPlayer.lastTickPosY) * aPartialTicks), -(aPlayer.lastTickPosZ + (aPlayer.posZ - aPlayer.lastTickPosZ) * aPartialTicks));
        GL11.glTranslated(aX + 0.5, aY + 0.5, aZ + 0.5);
        GTUtility.sideRotations[aSide.getIndex()].glApply();
        GL11.glTranslated(0, -0.5025, 0);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBegin(GL11.GL_LINES);
        double animation = (double) GregTechMod.instance.counter / 10;
        double tColor = (animation % 42 < 21 ? 0.25 + ((animation % 21)/40.0) : 0.75 - ((animation % 21)/40.0));
        GL11.glColor4d(tColor, tColor, tColor, 1);


        GL11.glVertex3d(0.50, 0, -0.25);
        GL11.glVertex3d(-0.50, 0, -0.25);
        GL11.glVertex3d(0.50, 0, 0.25);
        GL11.glVertex3d(-0.50, 0, 0.25);
        GL11.glVertex3d(0.25, 0, -0.50);
        GL11.glVertex3d(0.25, 0, 0.50);
        GL11.glVertex3d(-0.25, 0, -0.50);
        GL11.glVertex3d(-0.25, 0, 0.50);
        switch (aSide) {
            case DOWN:
                if (connections.contains(EnumFacing.DOWN)) {
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.UP)) {
                    GL11.glVertex3d(-0.50, 0, -0.50);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.50);

                    GL11.glVertex3d(+0.50, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);

                    GL11.glVertex3d(+0.50, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);

                    GL11.glVertex3d(-0.50, 0, +0.50);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.NORTH)) {
                    GL11.glVertex3d(-0.25, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);
                }
                if (connections.contains(EnumFacing.SOUTH)) {
                    GL11.glVertex3d(-0.25, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.WEST)) {
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.EAST)) {
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                break;
            case UP:
                if (connections.contains(EnumFacing.DOWN)) {
                    GL11.glVertex3d(-0.50, 0, -0.50);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.50);

                    GL11.glVertex3d(+0.50, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);

                    GL11.glVertex3d(+0.50, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);

                    GL11.glVertex3d(-0.50, 0, +0.50);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.UP)) {
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.NORTH)) {
                    GL11.glVertex3d(-0.25, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.SOUTH)) {
                    GL11.glVertex3d(-0.25, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);
                }
                if (connections.contains(EnumFacing.WEST)) {
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.EAST)) {
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                break;
            case NORTH:
                if (connections.contains(EnumFacing.DOWN)) {
                    GL11.glVertex3d(-0.25, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.UP)) {
                    GL11.glVertex3d(-0.25, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);
                }
                if (connections.contains(EnumFacing.NORTH)) {
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.SOUTH)) {
                    GL11.glVertex3d(-0.50, 0, -0.50);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.50);

                    GL11.glVertex3d(+0.50, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);

                    GL11.glVertex3d(+0.50, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);

                    GL11.glVertex3d(-0.50, 0, +0.50);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.WEST)) {
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.EAST)) {
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                break;
            case SOUTH:
                if (connections.contains(EnumFacing.DOWN)) {
                    GL11.glVertex3d(-0.25, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);
                }
                if (connections.contains(EnumFacing.UP)) {
                    GL11.glVertex3d(-0.25, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.NORTH)) {
                    GL11.glVertex3d(-0.50, 0, -0.50);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.50);

                    GL11.glVertex3d(+0.50, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);

                    GL11.glVertex3d(+0.50, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);

                    GL11.glVertex3d(-0.50, 0, +0.50);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.SOUTH)) {
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.WEST)) {
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.EAST)) {
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                break;
            case WEST:
                if (connections.contains(EnumFacing.DOWN)) {
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.UP)) {
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.NORTH)) {
                    GL11.glVertex3d(-0.25, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);
                }
                if (connections.contains(EnumFacing.SOUTH)) {
                    GL11.glVertex3d(-0.25, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.WEST)) {
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.EAST)) {
                    GL11.glVertex3d(-0.50, 0, -0.50);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.50);

                    GL11.glVertex3d(+0.50, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);

                    GL11.glVertex3d(+0.50, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);

                    GL11.glVertex3d(-0.50, 0, +0.50);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.50);
                }
                break;
            case EAST:
                if (connections.contains(EnumFacing.DOWN)) {
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.UP)) {
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                if (connections.contains(EnumFacing.NORTH)) {
                    GL11.glVertex3d(-0.25, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);
                }
                if (connections.contains(EnumFacing.SOUTH)) {
                    GL11.glVertex3d(-0.25, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.WEST)) {
                    GL11.glVertex3d(-0.50, 0, -0.50);
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(-0.50, 0, -0.25);
                    GL11.glVertex3d(-0.25, 0, -0.50);

                    GL11.glVertex3d(+0.50, 0, +0.50);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(+0.50, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, +0.50);

                    GL11.glVertex3d(+0.50, 0, -0.50);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                    GL11.glVertex3d(+0.50, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, -0.50);

                    GL11.glVertex3d(-0.50, 0, +0.50);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(-0.50, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.50);
                }
                if (connections.contains(EnumFacing.EAST)) {
                    GL11.glVertex3d(-0.25, 0, -0.25);
                    GL11.glVertex3d(+0.25, 0, +0.25);
                    GL11.glVertex3d(-0.25, 0, +0.25);
                    GL11.glVertex3d(+0.25, 0, -0.25);
                }
                break;
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPopMatrix();

    }





    public static void renderSide(EntityPlayer aPlayer, BlockPos pos, EnumFacing aSide, float aPartialTicks, float d) {

        GL11.glPushMatrix();



        int aX = pos.getX();
        int aY = pos.getY();
        int aZ = pos.getZ();
        try {
            Class.forName("codechicken.lib.vec.Rotation");
        } catch (ClassNotFoundException e) {
            return;
        }

        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glTranslated(-(aPlayer.lastTickPosX + (aPlayer.posX - aPlayer.lastTickPosX) * aPartialTicks), -(aPlayer.lastTickPosY + (aPlayer.posY - aPlayer.lastTickPosY) * aPartialTicks), -(aPlayer.lastTickPosZ + (aPlayer.posZ - aPlayer.lastTickPosZ) * aPartialTicks));
        GL11.glTranslated(aX + 0.5, aY + 0.5, aZ + 0.5);
        GTUtility.sideRotations[aSide.getIndex()].glApply();
        GL11.glTranslated(0, -0.5025, 0);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBegin(GL11.GL_QUADS);


        double animation = (double) GregTechMod.instance.counter / 10;
        double tColor = (animation % 42 < 21 ? 0.25 + ((animation % 21)/40.0) : 0.75 - ((animation % 21)/40.0));
        GL11.glColor4d(tColor, tColor, tColor, 0.3);

        GL11.glVertex3d(d, 0, d);
        GL11.glVertex3d(-1*d, 0, d);
        GL11.glVertex3d(-1*d, 0, -1*d);
        GL11.glVertex3d(d, 0, -1*d);

        GL11.glEnd();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPopMatrix();

    }

    public static void drawOutline(EntityPlayer player, BlockPos pos, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.glLineWidth(2.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);

        double animation = (double) GregTechMod.instance.counter / 10;
        float color = (float) (animation % 42 < 21 ? 0.25 + ((animation % 21)/40.0) : 0.75 - ((animation % 21)/40.0))/255;

        GlStateManager.color(color, color, color, 0.3F);

        Vec3d pos2 = new Vec3d(player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTicks, player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTicks, player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTicks).scale(-1);
        RenderGlobal.renderFilledBox(Block.FULL_BLOCK_AABB.offset(pos).expand(0.002, 0.002, 0.002).offset(pos2), color, color, color, 1);
        RenderGlobal.drawSelectionBoundingBox(Block.FULL_BLOCK_AABB.offset(pos).expand(0.002, 0.002, 0.002).offset(pos2), color, color, color, 1);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }


}
