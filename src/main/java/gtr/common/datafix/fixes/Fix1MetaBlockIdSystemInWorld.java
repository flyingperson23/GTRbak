package gtr.common.datafix.fixes;


import gtr.common.datafix.fixes.metablockid.MetaBlockIdRemapCache;
import gtr.common.datafix.fixes.metablockid.PostGraniteMetaBlockIdFixer;
import gtr.common.datafix.fixes.metablockid.WorldDataHooks;
import gtr.common.datafix.util.DataFixHelper;
import gtr.common.datafix.util.RemappedBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

public class Fix1MetaBlockIdSystemInWorld implements IFixableData {

    @Override
    public int getFixVersion() {
        return 1;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if (WorldDataHooks.isFixerUnavailable()) {
            return compound;
        }

        PostGraniteMetaBlockIdFixer fixer = (PostGraniteMetaBlockIdFixer) WorldDataHooks.getMetaBlockIdFixer();
        MetaBlockIdRemapCache remapCompressed = fixer.getRemapCacheCompressed();
        MetaBlockIdRemapCache remapSurfRock = fixer.getRemapCacheSurfRock();
        DataFixHelper.rewriteBlocks(compound, (id, data) -> {
            int index = remapCompressed.getOldIndex(id);
            if (index != -1) {
                RemappedBlock remapped = fixer.remapCompressedPostGraniteToNew(index, data);
                return new RemappedBlock(remapCompressed.getNewId(remapped.id), remapped.data);
            }
            index = remapSurfRock.getOldIndex(id);
            if (index != -1) {
                RemappedBlock remapped = fixer.remapSurfRockToNew(index, data);
                return new RemappedBlock(remapSurfRock.getNewId(remapped.id), remapped.data);
            }
            return null;
        });
        return compound;
    }

}