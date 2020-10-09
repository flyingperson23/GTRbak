package gtr.api.util;

import gtr.api.unification.ore.StoneType;
import net.minecraft.block.state.IBlockState;

public interface IBlockOre {

    IBlockState getOreBlock(StoneType stoneType);

}
