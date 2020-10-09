package gtr.common.blocks.modelfactories;

import gtr.api.model.AbstractBlockModelFactory;
import gtr.api.model.ResourcePackHook;
import gtr.api.unification.material.MaterialIconType;
import gtr.api.unification.material.type.Material;
import gtr.common.blocks.BlockFrame;
import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockFrameFactory extends AbstractBlockModelFactory {

    public static void init() {
        BlockFrameFactory factory = new BlockFrameFactory();
        ResourcePackHook.addResourcePackFileHook(factory);
    }

    private BlockFrameFactory() {
        super("frame_block", "frame_");
    }

    @Override
    protected String fillSample(Block block, String blockStateSample) {
        Material material = ((BlockFrame) block).frameMaterial;
        return blockStateSample.replace("$MATERIAL$", material.toString())
            .replace("$TEXTURE$", MaterialIconType.frameSide.getBlockPath(material.materialIconSet).toString())
            .replace("$TEXTURE_TOP$", MaterialIconType.frameTop.getBlockPath(material.materialIconSet).toString());
    }


}
