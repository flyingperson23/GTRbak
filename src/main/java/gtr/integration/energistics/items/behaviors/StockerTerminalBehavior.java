package gtr.integration.energistics.items.behaviors;

import appeng.api.AEApi;
import appeng.api.parts.IPart;
import gtr.api.GTValues;
import gtr.integration.energistics.items.stats.IModelProvider;
import gtr.integration.energistics.items.stats.IPartProvider;
import gtr.integration.energistics.parts.StockerTerminalPart;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StockerTerminalBehavior implements IItemBehaviour, IPartProvider, IModelProvider {
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemUse(@Nonnull EntityPlayer player, @Nonnull World world, @Nonnull BlockPos pos,
                                             @Nonnull EnumHand hand, @Nonnull EnumFacing side,
                                             float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);
        EnumActionResult placeResult = AEApi.instance().partHelper().placeBus(heldItem, pos, side, player, hand, world);
        return new ActionResult<>(placeResult, heldItem);
    }

    @Override
    public IPart getPart(ItemStack stack) {
        return new StockerTerminalPart(stack);
    }

    @Override
    public ModelResourceLocation getModel() {
        AEApi.instance().registries().partModels().registerModels(StockerTerminalPart.MODELS);
        return new ModelResourceLocation(GTValues.MODID + ":part/stocker.terminal");
    }
}
