package gtr.common.blocks;

import gtr.api.capability.GregtechCapabilities;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockMachineCasing extends VariantBlock<BlockMachineCasing.MachineCasingType> {

    public BlockMachineCasing() {
        super(Material.IRON);
        setUnlocalizedName("machine_casing");
        setHardness(4.0f);
        setResistance(8.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(MachineCasingType.LV));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
        return false;
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return player.getHeldItemMainhand().hasCapability(GregtechCapabilities.CAPABILITY_WRENCH, null);
    }

    @Nullable
    @Override
    public String getHarvestTool(IBlockState state) {
        return "wrench";
    }

    public enum MachineCasingType implements IStringSerializable {

        //Voltage-tiered casings
        LV("low_voltage"),
        MV("medium_voltage"),
        HV("high_voltage"),
        EV("extreme_voltage"),
        IV("insane_voltage"),
        LuV("ludicrous_voltage"),
        UV("ultra_voltage"),
        BRONZE_HULL("bronze_hull"),
        BRONZE_BRICKS_HULL("bronze_bricks_hull"),
        STEEL_HULL("steel_hull"),
        STEEL_BRICKS_HULL("steel_bricks_hull"),
        PYROLYSE("pyrolyse");

        private final String name;

        MachineCasingType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }

    public int getColorMultiplier() {
        return 13819135;
    }

}
