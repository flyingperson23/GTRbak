package gtr.integration.energistics.covers;

import java.util.function.BiFunction;

import gtr.integration.energistics.items.AEItems;
import gtr.api.GTValues;
import gtr.api.cover.CoverBehavior;
import gtr.api.cover.CoverDefinition;
import gtr.api.cover.ICoverable;
import gtr.api.items.metaitem.MetaItem;
import gtr.common.items.behaviors.CoverPlaceBehavior;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class AECoverBehaviors {
	public static final int startingCoverId = 40;
	public static void init() {
		registerStockerCover(0, "ae2.interface.lv", AEItems.AE2_STOCKER_LV, GTValues.LV, 1280);
		registerStockerCover(1, "ae2.interface.mv", AEItems.AE2_STOCKER_MV, GTValues.MV, 20480);
		registerStockerCover(2, "ae2.interface.hv", AEItems.AE2_STOCKER_HV, GTValues.HV, 327680);
		registerStockerCover(3, "ae2.interface.ev", AEItems.AE2_STOCKER_EV, GTValues.EV, 5242880);
		registerStockerCover(4, "ae2.interface.iv", AEItems.AE2_STOCKER_IV, GTValues.IV, 83886080);

		registerBehavior(8, new ResourceLocation(GTValues.MODID, "machine.status"), AEItems.MACHINE_STATUS,
				CoverMachineStatus::new);
	}

	protected static void registerStockerCover(int idOffset, String resourcePath, MetaItem<?>.MetaValueItem placerItem,
											   int tier, int maxStockCount) {
		registerBehavior(idOffset, new ResourceLocation(GTValues.MODID, resourcePath), placerItem,
				(tile, side) -> new CoverAE2Stocker(tile, side, tier, maxStockCount));
	}

	public static void registerBehavior(int idOffset, ResourceLocation coverId,
			MetaItem<?>.MetaValueItem placerItem, BiFunction<ICoverable, EnumFacing, CoverBehavior> behaviorCreator) {
		CoverDefinition coverDefinition = new CoverDefinition(coverId, behaviorCreator, placerItem.getStackForm());
		CoverDefinition.registerCover(startingCoverId + idOffset, coverDefinition);
		//noinspection deprecation // Using deprecation fields allows for compatibility with older GTCE versions
		placerItem.addStats(new PlayerCoverPlaceBehavior(coverDefinition));
	}
}
