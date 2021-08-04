package gtr.common.blocks;

import gtr.api.capability.GregtechCapabilities;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockGTMiningPipe extends VariantBlock<BlockGTMiningPipe.MiningPipeType> {

    public BlockGTMiningPipe() {
        super(Material.IRON);
        setTranslationKey("mining_pipe");
        setHardness(4.0f);
        setResistance(8.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(MiningPipeType.PIPE));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
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

    public enum MiningPipeType implements IStringSerializable {

        //Voltage-tiered casings
        PIPE("pipe"),
        TIP("tip");

        private final String name;

        MiningPipeType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }


    private static final AxisAlignedBB pipeAabb = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return false;
    }

    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        MiningPipeType type = state.getValue(VARIANT);
        return type != MiningPipeType.PIPE;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return this.getAabb(state.getValue(VARIANT));
    }

    private AxisAlignedBB getAabb(MiningPipeType type) {
        switch(type) {
            case PIPE:
                return pipeAabb;
            case TIP:
            default:
                return FULL_BLOCK_AABB;
        }
    }

    public int getLightOpacity(IBlockState state) {
        return state.isFullCube() ? 255 : 0;
    }

    public boolean isFullCube(IBlockState state) {
        return state.getValue(VARIANT) == MiningPipeType.TIP;
    }

    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.getValue(VARIANT) == MiningPipeType.TIP;
    }

}
