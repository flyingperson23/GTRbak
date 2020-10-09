package gtr.common.pipelike.cable;

import gtr.api.GTValues;
import gtr.api.pipenet.block.material.ItemBlockMaterialPipe;
import gtr.api.util.GTUtility;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockCable extends ItemBlockMaterialPipe<Insulation, WireProperties> {

    public ItemBlockCable(BlockCable block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        WireProperties wireProperties = blockPipe.createItemProperties(stack);
        String voltageName = GTValues.VN[GTUtility.getTierByVoltage(wireProperties.voltage)];
        tooltip.add(I18n.format("gtr.cable.voltage", wireProperties.voltage, voltageName));
        tooltip.add(I18n.format("gtr.cable.amperage", wireProperties.amperage));
        tooltip.add(I18n.format("gtr.cable.loss_per_block", wireProperties.lossPerBlock));
    }
}
