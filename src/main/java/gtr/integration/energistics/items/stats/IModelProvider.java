package gtr.integration.energistics.items.stats;

import gtr.api.items.metaitem.stats.IItemComponent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface IModelProvider extends IItemComponent {
    ModelResourceLocation getModel();
}