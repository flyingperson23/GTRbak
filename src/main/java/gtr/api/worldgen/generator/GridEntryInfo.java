package gtr.api.worldgen.generator;

import gtr.api.worldgen.config.OreDepositDefinition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Collection;
import java.util.Set;

public interface GridEntryInfo {

    int getBottomHeight();

    int getTerrainHeight();

    int getSeaLevel();

    Set<OreDepositDefinition> getGeneratedVeins();

    BlockPos getCenterPos(OreDepositDefinition definition);

    Collection<IBlockState> getGeneratedBlocks(OreDepositDefinition definition, int chunkX, int chunkZ);

}
