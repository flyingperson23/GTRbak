package gtr.integration.multi.jei;

import gtr.api.metatileentity.multiblock.MultiblockControllerBase;
import gtr.integration.jei.multiblock.MultiblockInfoPage;
import gtr.integration.jei.multiblock.MultiblockShapeInfo;
import gtr.integration.multi.crafttweaker.CustomMultiblock;
import gtr.integration.multi.gregtech.tile.TileControllerCustom;
import net.minecraft.client.resources.I18n;

import java.util.List;

public class CustomInfoPage extends MultiblockInfoPage {

    private final CustomMultiblock multiblock;

    public CustomInfoPage(CustomMultiblock multiblock) {
        this.multiblock = multiblock;
    }

    @Override
    public MultiblockControllerBase getController() {
        return new TileControllerCustom(multiblock);
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return multiblock.designs;
    }

    @Override
    public String[] getDescription() {
        String s = multiblock.loc.getNamespace() + ".multiblock." + multiblock.loc.getPath() + ".description";
        return new String[] {I18n.format(s)};
    }

}
