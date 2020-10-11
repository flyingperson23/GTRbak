package gtr.common.items.behaviors;

import gtr.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class WireCutterBehavior implements IItemBehaviour {

    public WireCutterBehavior() {
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return EnumActionResult.PASS;
    }
}
