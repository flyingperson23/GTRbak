package gtr.common.tools;

import gtr.api.items.metaitem.MetaItem;
import gtr.common.items.behaviors.WireCutterBehavior;
import gtr.common.items.behaviors.WrenchBehaviour;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class ToolWireCutter extends ToolBase {

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return 1;
    }

    @Override
    public int getToolDamagePerContainerCraft(ItemStack stack) {
        return 4;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return 1.25F;
    }

    @Override
    public boolean canMineBlock(IBlockState block, ItemStack stack) {
        String tool = block.getBlock().getHarvestTool(block);
        return tool != null && tool.equals("cutter");
    }

    @Override
    public void onStatsAddedToTool(MetaItem.MetaValueItem item) {
        item.addComponents(new WireCutterBehavior());
    }

}
