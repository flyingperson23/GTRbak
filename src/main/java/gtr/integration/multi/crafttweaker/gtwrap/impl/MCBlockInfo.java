package gtr.integration.multi.crafttweaker.gtwrap.impl;

import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.util.BlockInfo;
import gtr.common.blocks.MetaBlocks;
import gtr.integration.multi.crafttweaker.CustomMultiblock;
import gtr.integration.multi.crafttweaker.gtwrap.interfaces.IBlockInfo;
import gtr.integration.multi.gregtech.MultiblockRegistry;
import gtr.integration.multi.gregtech.tile.TileControllerCustom;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class MCBlockInfo implements IBlockInfo {

    @Nonnull
    private final BlockInfo internal;

    public MCBlockInfo(@Nonnull BlockInfo internal) {
        this.internal = internal;
    }

    @Nonnull
    @Override
    public BlockInfo getInternal() {
        return internal;
    }

    public static class ControllerInfo extends BlockInfo {

        private final EnumFacing facing;
        private final String id;
        private MetaTileEntityHolder te = null;

        public ControllerInfo(EnumFacing facing, String id) {
            super(MetaBlocks.MACHINE.getDefaultState());
            this.facing = facing;
            this.id = id;
        }

        @Override
        public TileEntity getTileEntity() {
            if(te == null) {
                te = new MetaTileEntityHolder();
                CustomMultiblock mb = MultiblockRegistry.get(id);
                if(mb != null) {
                    te.setMetaTileEntity(new TileControllerCustom(mb));
                    te.getMetaTileEntity().setFrontFacing(facing);
                } else {
                    te = null;
                }
            }
            return te;
        }

        @Override
        public void apply(World world, BlockPos pos) {
            world.setBlockState(pos, getBlockState());
            if(getTileEntity() != null) {
                world.setTileEntity(pos, getTileEntity());
            }
        }

    }

}
