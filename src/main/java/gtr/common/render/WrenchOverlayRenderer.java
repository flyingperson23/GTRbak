package gtr.common.render;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import gt6Pipes.client.Renderer;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.cover.ICoverable.PrimaryBoxData;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.common.items.behaviors.CoverPlaceBehavior;
import gtr.common.items.behaviors.CrowbarBehaviour;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class WrenchOverlayRenderer {

    @SubscribeEvent
    public static void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
        EntityPlayer player = event.getPlayer();
        World world = player.world;
        RayTraceResult target = event.getTarget();

        if (target.typeOfHit != RayTraceResult.Type.BLOCK) {
            return; //magically, draw block highlight is called not only for blocks (see forge issues)
        }

        BlockPos pos = target.getBlockPos();
        TileEntity tileEntity = world.getTileEntity(pos);
        ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);

        if (tileEntity != null && shouldDrawOverlayForItem(heldItem, tileEntity) && useGridForRayTraceResult(target)) {
            Renderer.renderOverlay(event.getPlayer(), pos, target.sideHit, event.getPartialTicks(), new ArrayList<>());

            event.setCanceled(true);
        }
    }

    public static boolean useGridForRayTraceResult(RayTraceResult result) {
        if(result instanceof CuboidRayTraceResult) {
            CuboidRayTraceResult traceResult = (CuboidRayTraceResult) result;
            //use grid only for center hit or for primary box with placement grid enabled
            if(traceResult.cuboid6.data == null) {
                return true; //default is true
            } else if(traceResult.cuboid6.data instanceof PrimaryBoxData) {
                PrimaryBoxData primaryBoxData = (PrimaryBoxData) traceResult.cuboid6.data;
                return primaryBoxData.usePlacementGrid;
            } else return false; //otherwise, do not use grid
        }
        //also use it for default collision blocks
        return true;
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

}
