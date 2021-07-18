package gtr.common.datafix.fixes;


import gtr.common.datafix.GregTechDataFixers;
import gtr.common.datafix.fixes.metablockid.MetaBlockIdFixHelper;
import gtr.common.datafix.fixes.metablockid.PreGraniteMetaBlockIdFixer;
import gtr.common.datafix.fixes.metablockid.WorldDataHooks;
import gtr.common.datafix.util.RemappedBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

public class Fix0PostGraniteMetaBlockShift implements IFixableData {

    @Override
    public int getFixVersion() {
        return 0;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if (WorldDataHooks.isFixerUnavailable()) {
            return compound;
        }

        int index = MetaBlockIdFixHelper.getCompressedIndexFromResLoc(compound.getString(GregTechDataFixers.COMPOUND_ID));
        if (index != -1) {
            RemappedBlock remapped = ((PreGraniteMetaBlockIdFixer) WorldDataHooks.getMetaBlockIdFixer())
                .remapCompressedPreGraniteToPost(index, compound.getShort(GregTechDataFixers.COMPOUND_META));
            if (remapped != null) {
                compound.setString(GregTechDataFixers.COMPOUND_ID, MetaBlockIdFixHelper.COMP_RESLOC_PREF + remapped.id);
                compound.setShort(GregTechDataFixers.COMPOUND_META, remapped.data);
            }
        }
        return compound;
    }

}
