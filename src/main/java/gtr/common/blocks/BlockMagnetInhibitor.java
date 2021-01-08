package gtr.common.blocks;

import gtr.api.GregTechAPI;
import gtr.common.ConfigHolder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockMagnetInhibitor extends Block {
    public BlockMagnetInhibitor() {
        super(Material.IRON);
        setUnlocalizedName("gt.magnetinhibitor");
        setCreativeTab(GregTechAPI.TAB_GREGTECH);
        setHarvestLevel("pickaxe", 1);
        setHardness(7);
        setSoundType(SoundType.METAL);
        setResistance(9);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add("Radius: "+ ConfigHolder.magnetInhibitorRange);
    }
}
