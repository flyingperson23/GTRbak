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

public class BlockMultiblockCasing extends VariantBlock<BlockMultiblockCasing.MultiblockCasingType> {

    public BlockMultiblockCasing() {
        super(Material.IRON);
        setUnlocalizedName("multiblock_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(MultiblockCasingType.ENGINE_INTAKE_CASING));
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

    public enum MultiblockCasingType implements IStringSerializable {

        ENGINE_INTAKE_CASING("engine_intake"),
        GRATE_CASING("grate"),
        FUSION_CASING("fusion"),
        HIGH_POWER("high_power");

        private final String name;

        MultiblockCasingType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }

}
