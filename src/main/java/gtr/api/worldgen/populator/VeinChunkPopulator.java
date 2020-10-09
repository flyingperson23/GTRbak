package gtr.api.worldgen.populator;

import gtr.api.worldgen.config.OreDepositDefinition;
import gtr.api.worldgen.generator.GridEntryInfo;
import net.minecraft.world.World;

import java.util.Random;

public interface VeinChunkPopulator extends IVeinPopulator {

    void populateChunk(World world, int chunkX, int chunkZ, Random random, OreDepositDefinition definition, GridEntryInfo gridEntryInfo);
}
