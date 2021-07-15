package gtr.integration.energistics.covers;

import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.cover.CoverBehavior;
import gtr.api.cover.CoverDefinition;
import gtr.api.cover.ICoverable;
import gtr.common.items.behaviors.CoverPlaceBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerCoverPlaceBehavior extends CoverPlaceBehavior {
    public PlayerCoverPlaceBehavior(CoverDefinition coverDefinition) {
        super(coverDefinition);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        TileEntity tileEntity = world.getTileEntity(pos);
        ICoverable coverable = tileEntity == null ? null : tileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_COVERABLE, null);
        if (coverable == null)
            return EnumActionResult.PASS;

        EnumFacing coverSide = ICoverable.rayTraceCoverableSide(coverable, player);
        if (coverable.getCoverAtSide(coverSide) != null || !coverable.canPlaceCoverOnSide(coverSide))
            return EnumActionResult.PASS;

        if (!world.isRemote) {
            ItemStack itemStack = player.getHeldItem(hand);
            boolean successfullyPlaced = coverable.placeCoverOnSide(coverSide, itemStack, coverDefinition);
            if (successfullyPlaced) {
                if(!player.capabilities.isCreativeMode)
                    itemStack.shrink(1);

                CoverBehavior placedCoverBehavior = coverable.getCoverAtSide(coverSide);
                if(placedCoverBehavior instanceof PlayerPlacedCoverBehavior)
                    ((PlayerPlacedCoverBehavior) placedCoverBehavior).setPlacingPlayer(player);
            }

            return successfullyPlaced ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
        }

        return EnumActionResult.SUCCESS;
    }
}