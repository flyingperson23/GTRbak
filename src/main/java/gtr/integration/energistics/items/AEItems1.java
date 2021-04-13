package gtr.integration.energistics.items;

import appeng.core.Api;
import gtr.integration.energistics.items.behaviors.FluidEncoderBehaviour;
import gtr.integration.energistics.items.behaviors.StockerTerminalBehavior;
import gtr.api.GTValues;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AEItems1 extends StandardModMetaItem {
	public static final short META_ITEM_OFFSET = 0;

	public AEItems1() {
		super(META_ITEM_OFFSET);
	}

	@Override
	public void registerSubItems() {
		AEItems.AE2_STOCKER_LV = addItem(0, "ae2.stocker.lv");
		AEItems.AE2_STOCKER_MV = addItem(1, "ae2.stocker.mv");
		AEItems.AE2_STOCKER_HV = addItem(2, "ae2.stocker.hv");
		AEItems.AE2_STOCKER_EV = addItem(3, "ae2.stocker.ev");
		AEItems.AE2_STOCKER_IV = addItem(4, "ae2.stocker.iv");
		AEItems.MACHINE_STATUS = addItem(8, "machine.status");
		AEItems.FLUID_ENCODER = addItem(9, "fluid.encoder")
				.addComponents(new FluidEncoderBehaviour()).setMaxStackSize(1);
		AEItems.STOCKER_TERMINAL = addItem(10, "stocker.terminal")
				.addComponents(new StockerTerminalBehavior());
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")	// If these items are missing the mod should probably fail to load
	protected void registerStocker(MetaItem<?>.MetaValueItem roboticArm, MetaItem<?>.MetaValueItem pump,
								   MetaItem<?>.MetaValueItem output, int voltage) {
		RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
				.inputs(
						roboticArm.getStackForm(),
						pump.getStackForm(),
						gtr.common.items.MetaItems.COVER_MACHINE_CONTROLLER.getStackForm(),
						Api.INSTANCE.definitions().blocks().iface().maybeStack(1).get(),
						Api.INSTANCE.definitions().blocks().fluidIface().maybeStack(1).get(),
						Api.INSTANCE.definitions().parts().levelEmitter().maybeStack(1).get(),
						Api.INSTANCE.definitions().parts().fluidLevelEmitter().maybeStack(1).get()
				)
				.input(OrePrefix.plate, Materials.Tin, 4)
				.outputs(output.getStackForm())
				.duration(200)
				.EUt((int) GTValues.V[voltage])
				.buildAndRegister();
	}

	public void registerRecipes() {
		registerStocker(gtr.common.items.MetaItems.ROBOT_ARM_LV, gtr.common.items.MetaItems.ELECTRIC_PUMP_LV,
				AEItems.AE2_STOCKER_LV, GTValues.LV);
		registerStocker(gtr.common.items.MetaItems.ROBOT_ARM_MV, gtr.common.items.MetaItems.ELECTRIC_PUMP_MV,
				AEItems.AE2_STOCKER_MV, GTValues.MV);
		registerStocker(gtr.common.items.MetaItems.ROBOT_ARM_HV, gtr.common.items.MetaItems.ELECTRIC_PUMP_HV,
				AEItems.AE2_STOCKER_HV, GTValues.HV);
		registerStocker(gtr.common.items.MetaItems.ROBOT_ARM_EV, gtr.common.items.MetaItems.ELECTRIC_PUMP_EV,
				AEItems.AE2_STOCKER_EV, GTValues.EV);
		registerStocker(gtr.common.items.MetaItems.ROBOT_ARM_IV, gtr.common.items.MetaItems.ELECTRIC_PUMP_IV,
				AEItems.AE2_STOCKER_IV, GTValues.IV);

		RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
				.input(OrePrefix.plate, Materials.Tin, 4)
				.input(OrePrefix.dust, Materials.Redstone, 2)
				.outputs(AEItems.MACHINE_STATUS.getStackForm())
				.duration(200)
				.EUt((int) GTValues.V[GTValues.MV])
				.buildAndRegister();

		RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
				.inputs(
						gtr.common.items.MetaItems.INTEGRATED_CIRCUIT.getStackForm(),
						new ItemStack(Items.BUCKET)
				)
				.outputs(AEItems.FLUID_ENCODER.getStackForm())
				.duration(200)
				.EUt((int) GTValues.V[GTValues.MV])
				.buildAndRegister();

		RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
				.input("itemIlluminatedPanel", 1)
				.inputs(
						Api.INSTANCE.definitions().materials().engProcessor().maybeStack(1).get(),
						AEItems.AE2_STOCKER_MV.getStackForm()
				)
				.outputs(AEItems.STOCKER_TERMINAL.getStackForm())
				.duration(200)
				.EUt((int) GTValues.V[GTValues.MV])
				.buildAndRegister();
	}
}
