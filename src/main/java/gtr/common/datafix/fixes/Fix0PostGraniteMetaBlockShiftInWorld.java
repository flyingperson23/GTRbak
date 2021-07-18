package gtr.common.datafix.fixes;

import gtr.common.datafix.fixes.metablockid.PreGraniteMetaBlockIdFixer;
import gtr.common.datafix.fixes.metablockid.WorldDataHooks;
import gtr.common.datafix.util.DataFixHelper;
import gtr.common.datafix.util.RemappedBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

public class Fix0PostGraniteMetaBlockShiftInWorld implements IFixableData {

    @Override
    public int getFixVersion() {
        return 0;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if (WorldDataHooks.isFixerUnavailable()) {
            return compound;
        }

        PreGraniteMetaBlockIdFixer fixer = (PreGraniteMetaBlockIdFixer) WorldDataHooks.getMetaBlockIdFixer();
        DataFixHelper.rewriteBlocks(compound, (id, data) -> {
            int index = fixer.getRemapCacheCompressed().getOldIndex(id);
            if (index == -1) {
                return null;
            }
            RemappedBlock remapped = fixer.remapCompressedPreGraniteToPost(index, data);
            if (remapped == null) {
                return null;
            }
            return new RemappedBlock(fixer.getRemapCacheCompressed().getOldId(remapped.id), remapped.data);
        });
        return compound;
    }

}