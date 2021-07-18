package gtr.common.items;

import gtr.api.GTValues;
import gtr.api.items.OreDictNames;
import gtr.api.items.materialitem.MaterialMetaItem;
import gtr.api.items.metaitem.BaubleStats;
import gtr.api.items.metaitem.ElectricStats;
import gtr.api.items.metaitem.FluidStats;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.material.type.SolidMaterial;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.ItemMaterialInfo;
import gtr.api.unification.stack.MaterialStack;
import gtr.common.items.behaviors.*;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gtr.common.items.MetaItems.*;

public class MetaItem1 extends MaterialMetaItem {

    public final Map<OrePrefix, OrePrefix> purifyMap = new HashMap<>();

    public MetaItem1() {
        super(OrePrefix.dustTiny, OrePrefix.dustSmall, OrePrefix.dust, OrePrefix.dustImpure, OrePrefix.dustPure,
            OrePrefix.crushed, OrePrefix.crushedPurified, OrePrefix.crushedCentrifuged, OrePrefix.gem, OrePrefix.nugget,
            OrePrefix.ingot, OrePrefix.ingotHot, OrePrefix.plate, OrePrefix.plateDense, OrePrefix.stick, OrePrefix.lens,
            OrePrefix.bolt, OrePrefix.screw, OrePrefix.ring, OrePrefix.foil,
            null, null, null, null, null, null, null, null, null, null, null, null);
        registerPurifyRecipes();
    }

    private void registerPurifyRecipes() {
        purifyMap.put(OrePrefix.crushed, OrePrefix.crushedPurified);
        purifyMap.put(OrePrefix.dustImpure, OrePrefix.dust);
        purifyMap.put(OrePrefix.dustPure, OrePrefix.dust);
    }

    @Override
    public void registerSubItems() {
        SHAPE_EMPTY = addItem(300, "shape.empty");

        SHAPE_MOLDS[0] = SHAPE_MOLD_PLATE = addItem(301, "shape.mold.plate");
        SHAPE_MOLDS[1] = SHAPE_MOLD_GEAR = addItem(303, "shape.mold.gear");
        SHAPE_MOLDS[2] = SHAPE_MOLD_BOTTLE = addItem(305, "shape.mold.bottle");
        SHAPE_MOLDS[3] = SHAPE_MOLD_INGOT = addItem(306, "shape.mold.ingot");
        SHAPE_MOLDS[4] = SHAPE_MOLD_BALL = addItem(307, "shape.mold.ball");
        SHAPE_MOLDS[5] = SHAPE_MOLD_BLOCK = addItem(308, "shape.mold.block");
        SHAPE_MOLDS[6] = SHAPE_MOLD_NUGGET = addItem(309, "shape.mold.nugget");
        SHAPE_MOLDS[7] = SHAPE_MOLD_CYLINDER = addItem(313, "shape.mold.cylinder");
        SHAPE_MOLDS[8] = SHAPE_MOLD_ANVIL = addItem(314, "shape.mold.anvil");
        SHAPE_MOLDS[9] = SHAPE_MOLD_NAME = addItem(315, "shape.mold.name");
        SHAPE_MOLDS[10] = SHAPE_MOLD_GEAR_SMALL = addItem(317, "shape.mold.gear.small");
        SHAPE_MOLDS[11] = SHAPE_MOLD_ROTOR = addItem(318, "shape.mold.rotor");

        SHAPE_EXTRUDERS[0] = SHAPE_EXTRUDER_PLATE = addItem(350, "shape.extruder.plate");
        SHAPE_EXTRUDERS[1] = SHAPE_EXTRUDER_ROD = addItem(351, "shape.extruder.rod");
        SHAPE_EXTRUDERS[2] = SHAPE_EXTRUDER_BOLT = addItem(352, "shape.extruder.bolt");
        SHAPE_EXTRUDERS[3] = SHAPE_EXTRUDER_RING = addItem(353, "shape.extruder.ring");
        SHAPE_EXTRUDERS[4] = SHAPE_EXTRUDER_CELL = addItem(354, "shape.extruder.cell");
        SHAPE_EXTRUDERS[5] = SHAPE_EXTRUDER_INGOT = addItem(355, "shape.extruder.ingot");
        SHAPE_EXTRUDERS[6] = SHAPE_EXTRUDER_WIRE = addItem(356, "shape.extruder.wire");
        SHAPE_EXTRUDERS[7] = SHAPE_EXTRUDER_PIPE_TINY = addItem(358, "shape.extruder.pipe.tiny");
        SHAPE_EXTRUDERS[8] = SHAPE_EXTRUDER_PIPE_SMALL = addItem(359, "shape.extruder.pipe.small");
        SHAPE_EXTRUDERS[9] = SHAPE_EXTRUDER_PIPE_MEDIUM = addItem(360, "shape.extruder.pipe.medium");
        SHAPE_EXTRUDERS[10] = SHAPE_EXTRUDER_PIPE_LARGE = addItem(361, "shape.extruder.pipe.large");
        SHAPE_EXTRUDERS[11] = SHAPE_EXTRUDER_BLOCK = addItem(363, "shape.extruder.block");
        SHAPE_EXTRUDERS[12] = SHAPE_EXTRUDER_SWORD = addItem(364, "shape.extruder.sword");
        SHAPE_EXTRUDERS[13] = SHAPE_EXTRUDER_PICKAXE = addItem(365, "shape.extruder.pickaxe");
        SHAPE_EXTRUDERS[14] = SHAPE_EXTRUDER_SHOVEL = addItem(366, "shape.extruder.shovel");
        SHAPE_EXTRUDERS[15] = SHAPE_EXTRUDER_AXE = addItem(367, "shape.extruder.axe");
        SHAPE_EXTRUDERS[16] = SHAPE_EXTRUDER_HOE = addItem(368, "shape.extruder.hoe");
        SHAPE_EXTRUDERS[17] = SHAPE_EXTRUDER_HAMMER = addItem(369, "shape.extruder.hammer");
        SHAPE_EXTRUDERS[18] = SHAPE_EXTRUDER_FILE = addItem(370, "shape.extruder.file");
        SHAPE_EXTRUDERS[19] = SHAPE_EXTRUDER_SAW = addItem(371, "shape.extruder.saw");
        SHAPE_EXTRUDERS[20] = SHAPE_EXTRUDER_GEAR = addItem(372, "shape.extruder.gear");
        SHAPE_EXTRUDERS[21] = SHAPE_EXTRUDER_BOTTLE = addItem(373, "shape.extruder.bottle");

        SPRAY_EMPTY = addItem(402, "spray.empty")
            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Tin, OrePrefix.plate.materialAmount * 2L), new MaterialStack(Materials.Redstone, OrePrefix.dust.materialAmount)));

        LARGE_FLUID_CELL_STEEL = addItem(405, "large_fluid_cell.steel")
            .addComponents(new FluidStats(64000, Integer.MIN_VALUE, Integer.MAX_VALUE, true))
            .setMaxStackSize(16)
            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, OrePrefix.plate.materialAmount * 2L + 2L * OrePrefix.ring.materialAmount)));

        LARGE_FLUID_CELL_TUNGSTEN_STEEL = addItem(406, "large_fluid_cell.tungstensteel")
            .addComponents(new FluidStats(256000, Integer.MIN_VALUE, Integer.MAX_VALUE, true))
            .setMaxStackSize(16)
            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.TungstenSteel, OrePrefix.plate.materialAmount * 2L + 2L * OrePrefix.ring.materialAmount)));

        for (int i = 0; i < EnumDyeColor.values().length; i++) {
            EnumDyeColor dyeColor = EnumDyeColor.values()[i];
            SPRAY_CAN_DYES[i] = addItem(430 + 2 * i, "spray.can.dyes." + dyeColor.getName()).setMaxStackSize(1);
            ColorSprayBehaviour behaviour = new ColorSprayBehaviour(SPRAY_EMPTY.getStackForm(), 512, i);
            SPRAY_CAN_DYES[i].addComponents(behaviour);
        }

        TOOL_MATCHES = addItem(471, "tool.matches")
            .addComponents(new LighterBehaviour(1));
        TOOL_MATCHBOX = addItem(473, "tool.matchbox")
            .addComponents(new LighterBehaviour(16)).setMaxStackSize(1);
        TOOL_LIGHTER_INVAR = addItem(476, "tool.lighter.invar")
            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Invar, GTValues.L * 2)))
            .addComponents(new LighterBehaviour(100)).setMaxStackSize(1);
        TOOL_LIGHTER_PLATINUM = addItem(477, "tool.lighter.platinum")
            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Platinum, GTValues.L * 2)))
            .addComponents(new LighterBehaviour(1000)).setMaxStackSize(1);

        BATTERY_HULL_LV = addItem(500, "battery.hull.lv").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.BatteryAlloy, OrePrefix.plate.materialAmount)));
        BATTERY_HULL_MV = addItem(501, "battery.hull.hv").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.BatteryAlloy, OrePrefix.plate.materialAmount * 3L)));
        BATTERY_HULL_HV = addItem(502, "battery.hull.mv").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.BatteryAlloy, OrePrefix.plate.materialAmount * 9L)));

        BATTERY_SU_LV_SULFURIC_ACID = addItem(510, "battery.su.lv.sulfuricacid").addComponents(new BaubleStats(), ElectricStats.createBattery(18000, GTValues.LV, false)).setModelAmount(8);
        BATTERY_SU_LV_MERCURY = addItem(511, "battery.su.lv.mercury").addComponents(new BaubleStats(), ElectricStats.createBattery(32000, GTValues.LV, false)).setModelAmount(8);


        BATTERY_RE_LV_CADMIUM = addItem(517, "battery.re.lv.cadmium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(120000, GTValues.LV)).setModelAmount(8);
        BATTERY_RE_LV_LITHIUM = addItem(518, "battery.re.lv.lithium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(100000, GTValues.LV)).setModelAmount(8);
        BATTERY_RE_LV_SODIUM = addItem(519, "battery.re.lv.sodium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(80000, GTValues.LV)).setModelAmount(8);

        BATTERY_RE_LV_CADMIUM.addOreDict("reBattery");
        BATTERY_RE_LV_LITHIUM.addOreDict("reBattery");
        BATTERY_RE_LV_SODIUM.addOreDict("reBattery");

        BATTERY_SU_MV_SULFURIC_ACID = addItem(520, "battery.su.mv.sulfuricacid").addComponents(new BaubleStats(), ElectricStats.createBattery(72000, GTValues.MV, false)).setModelAmount(8);
        BATTERY_SU_MV_MERCURY = addItem(521, "battery.su.mv.mercury").addComponents(new BaubleStats(), ElectricStats.createBattery(128000, GTValues.MV, false)).setModelAmount(8);

        CREATIVE_BATTERY = addItem(512, "battery.creative").addComponents(new BaubleStats(), ElectricStats.createCreativeBattery());

        BATTERY_RE_MV_CADMIUM = addItem(527, "battery.re.mv.cadmium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(420000, GTValues.MV)).setModelAmount(8);
        BATTERY_RE_MV_LITHIUM = addItem(528, "battery.re.mv.lithium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(400000, GTValues.MV)).setModelAmount(8);
        BATTERY_RE_MV_SODIUM = addItem(529, "battery.re.mv.sodium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(360000, GTValues.MV)).setModelAmount(8);

        BATTERY_SU_HV_SULFURIC_ACID = addItem(530, "battery.su.hv.sulfuricacid").addComponents(new BaubleStats(), ElectricStats.createBattery(288000, GTValues.HV, false)).setModelAmount(8);
        BATTERY_SU_HV_MERCURY = addItem(531, "battery.su.hv.mercury").addComponents(new BaubleStats(), ElectricStats.createBattery(512000, GTValues.HV, false)).setModelAmount(8);

        BATTERY_RE_HV_CADMIUM = addItem(537, "battery.re.hv.cadmium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(1800000, GTValues.HV)).setModelAmount(8);
        BATTERY_RE_HV_LITHIUM = addItem(538, "battery.re.hv.lithium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(1600000, GTValues.HV)).setModelAmount(8);
        BATTERY_RE_HV_SODIUM = addItem(539, "battery.re.hv.sodium").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(1200000, GTValues.HV)).setModelAmount(8);

        ENERGY_LAPOTRONIC_ORB = addItem(597, "energy.lapotronicorb").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(100000000, GTValues.IV)).setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.Elite).setModelAmount(8);
        ENERGY_LAPOTRONIC_ORB2 = addItem(598, "energy.lapotronicorb2").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(1000000000, GTValues.LuV)).setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.Ultimate).setModelAmount(8);

        ENERGY_INFUSED_LAPOTRONIC_ORB = addItem(605, "energy.infused_lapotronicorb").addComponents(new BaubleStats(), ElectricStats.createRechargeableBattery(Long.MAX_VALUE, GTValues.UV)).setModelAmount(8);

        ELECTRIC_MOTOR_LV = addItem(600, "electric.motor.lv");
        ELECTRIC_MOTOR_MV = addItem(601, "electric.motor.mv");
        ELECTRIC_MOTOR_HV = addItem(602, "electric.motor.hv");
        ELECTRIC_MOTOR_EV = addItem(603, "electric.motor.ev");
        ELECTRIC_MOTOR_IV = addItem(604, "electric.motor.iv");

        PUMPS[0] = ELECTRIC_PUMP_LV = addItem(610, "electric.pump.lv");
        PUMPS[1] = ELECTRIC_PUMP_MV = addItem(611, "electric.pump.mv");
        PUMPS[2] = ELECTRIC_PUMP_HV = addItem(612, "electric.pump.hv");
        PUMPS[3] = ELECTRIC_PUMP_EV = addItem(613, "electric.pump.ev");
        PUMPS[4] = ELECTRIC_PUMP_IV = addItem(614, "electric.pump.iv");

        RUBBER_DROP = addItem(627, "rubber_drop").setBurnValue(200);

        FLUID_FILTER = addItem(628, "fluid_filter");

        DYNAMITE = addItem(629, "dynamite").addComponents(new DynamiteBehaviour()).setMaxStackSize(16);

        CONVEYOR_MODULE_LV = addItem(630, "conveyor.module.lv");
        CONVEYOR_MODULE_MV = addItem(631, "conveyor.module.mv");
        CONVEYOR_MODULE_HV = addItem(632, "conveyor.module.hv");
        CONVEYOR_MODULE_EV = addItem(633, "conveyor.module.ev");
        CONVEYOR_MODULE_IV = addItem(634, "conveyor.module.iv");

        ELECTRIC_PISTON_LV = addItem(640, "electric.piston.lv");
        ELECTRIC_PISTON_MV = addItem(641, "electric.piston.mv");
        ELECTRIC_PISTON_HV = addItem(642, "electric.piston.hv");
        ELECTRIC_PISTON_EV = addItem(643, "electric.piston.ev");
        ELECTRIC_PISTON_IV = addItem(644, "electric.piston.iv");

        ROBOT_ARM_LV = addItem(650, "robot.arm.lv");
        ROBOT_ARM_MV = addItem(651, "robot.arm.mv");
        ROBOT_ARM_HV = addItem(652, "robot.arm.hv");
        ROBOT_ARM_EV = addItem(653, "robot.arm.ev");
        ROBOT_ARM_IV = addItem(654, "robot.arm.iv");

        FIELD_GENERATOR_LV = addItem(670, "field.generator.lv");
        FIELD_GENERATOR_MV = addItem(671, "field.generator.mv");
        FIELD_GENERATOR_HV = addItem(672, "field.generator.hv");
        FIELD_GENERATOR_EV = addItem(673, "field.generator.ev");
        FIELD_GENERATOR_IV = addItem(674, "field.generator.iv");

        EMITTER_LV = addItem(680, "emitter.lv");
        EMITTER_MV = addItem(681, "emitter.mv");
        EMITTER_HV = addItem(682, "emitter.hv");
        EMITTER_EV = addItem(683, "emitter.ev");
        EMITTER_IV = addItem(684, "emitter.iv");

        SENSOR_LV = addItem(690, "sensor.lv");
        SENSOR_MV = addItem(691, "sensor.mv");
        SENSOR_HV = addItem(692, "sensor.hv");
        SENSOR_EV = addItem(693, "sensor.ev");
        SENSOR_IV = addItem(694, "sensor.iv");


        COMPONENT_GRINDER_DIAMOND = addItem(722, "component.grinder.diamond").addOreDict(OreDictNames.craftingGrinder);
        COMPONENT_GRINDER_TUNGSTEN = addItem(723, "component.grinder.tungsten").addOreDict(OreDictNames.craftingGrinder);

        QUANTUM_EYE = addItem(724, "quantumeye");
        QUANTUM_STAR = addItem(725, "quantumstar");

        ITEM_FILTER = addItem(729, "item_filter");
        ORE_DICTIONARY_FILTER = addItem(102, "ore_dictionary_filter");
        SMART_FILTER = addItem(103, "smart_item_filter");

        COVER_MACHINE_CONTROLLER = addItem(730, "cover.controller");

        COVER_ACTIVITY_DETECTOR = addItem(731, "cover.activity.detector").setInvisible();
        COVER_FLUID_DETECTOR = addItem(732, "cover.fluid.detector").setInvisible();
        COVER_ITEM_DETECTOR = addItem(733, "cover.item.detector").setInvisible();
        COVER_ENERGY_DETECTOR = addItem(734, "cover.energy.detector").setInvisible();

        COVER_SCREEN = addItem(740, "cover.screen").setInvisible();
        COVER_CRAFTING = addItem(744, "cover.crafting").setInvisible();
        COVER_DRAIN = addItem(745, "cover.drain").setInvisible();

        COVER_SHUTTER = addItem(749, "cover.shutter");

        COVER_SOLAR_PANEL = addItem(750, "cover.solar.panel");
        COVER_SOLAR_PANEL_ULV = addItem(751, "cover.solar.panel.ulv");
        COVER_SOLAR_PANEL_LV = addItem(752, "cover.solar.panel.lv");

        FLUID_CELL = addItem(762, "fluid_cell").addComponents(new FluidStats(1000, Integer.MIN_VALUE, Integer.MAX_VALUE, false));
        INTEGRATED_CIRCUIT = addItem(766, "circuit.integrated").addComponents(new IntCircuitBehaviour());
        FOAM_SPRAYER = addItem(746, "foam_sprayer").addComponents(new FoamSprayerBehavior());
    }

    public void registerRecipes() {
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.dust, Materials.Redstone).input(OrePrefix.plate, Materials.Tin, 2)
            .outputs(SPRAY_EMPTY.getStackForm())
            .duration(200).EUt(8)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plateDense, Materials.Steel, 2)
            .input(OrePrefix.ring, Materials.Steel, 8)
            .outputs(LARGE_FLUID_CELL_STEEL.getStackForm())
            .circuitMeta(1).duration(100).EUt(64)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plateDense, Materials.TungstenSteel, 2)
            .input(OrePrefix.ring, Materials.TungstenSteel, 8)
            .outputs(LARGE_FLUID_CELL_TUNGSTEN_STEEL.getStackForm())
            .circuitMeta(1).duration(200).EUt(256)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plate, Materials.Tin, 6)
            .inputs(SPRAY_EMPTY.getStackForm())
            .input(OrePrefix.paneGlass.name(), 1)
            .outputs(FOAM_SPRAYER.getStackForm())
            .duration(200).EUt(8)
            .buildAndRegister();

        // Matches/lighters recipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.stick, Materials.Wood).input(OrePrefix.dustSmall, Materials.Phosphorus)
            .outputs(TOOL_MATCHES.getStackForm())
            .duration(16).EUt(16)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.stick, Materials.Wood).input(OrePrefix.dustSmall, Materials.Phosphor)
            .outputs(TOOL_MATCHES.getStackForm())
            .duration(16).EUt(16)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.stick, Materials.Wood, 4).input(OrePrefix.dust, Materials.Phosphorus)
            .outputs(TOOL_MATCHES.getStackForm(4))
            .duration(64).EUt(16)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.stick, Materials.Wood, 4).input(OrePrefix.dust, Materials.Phosphor)
            .outputs(TOOL_MATCHES.getStackForm(4))
            .duration(64)
            .EUt(16)
            .buildAndRegister();

        RecipeMaps.PACKER_RECIPES.recipeBuilder()
            .inputs(TOOL_MATCHES.getStackForm(16)).input(OrePrefix.plate, Materials.Paper)
            .outputs(TOOL_MATCHBOX.getStackForm())
            .duration(64)
            .EUt(16)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plate, Materials.Invar, 2).inputs(new ItemStack(Items.FLINT, 1))
            .outputs(TOOL_LIGHTER_INVAR.getStackForm())
            .duration(256)
            .EUt(16)
            .buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
            .input(OrePrefix.plate, Materials.Platinum, 2).inputs(new ItemStack(Items.FLINT, 1))
            .outputs(TOOL_LIGHTER_PLATINUM.getStackForm())
            .duration(256)
            .EUt(256)
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_SU_LV_SULFURIC_ACID.getStackForm())
            .outputs(BATTERY_HULL_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_SU_LV_MERCURY.getStackForm())
            .outputs(BATTERY_HULL_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_SU_MV_SULFURIC_ACID.getStackForm())
            .outputs(BATTERY_HULL_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_SU_MV_MERCURY.getStackForm())
            .outputs(BATTERY_HULL_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_SU_HV_SULFURIC_ACID.getStackForm())
            .outputs(BATTERY_HULL_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_SU_HV_MERCURY.getStackForm())
            .outputs(BATTERY_HULL_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_LV_CADMIUM.getStackForm())
            .outputs(BATTERY_HULL_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_LV_LITHIUM.getStackForm())
            .outputs(BATTERY_HULL_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_LV_SODIUM.getStackForm())
            .outputs(BATTERY_HULL_LV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_MV_CADMIUM.getStackForm())
            .outputs(BATTERY_HULL_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_MV_LITHIUM.getStackForm())
            .outputs(BATTERY_HULL_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_MV_SODIUM.getStackForm())
            .outputs(BATTERY_HULL_MV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_HV_CADMIUM.getStackForm())
            .outputs(BATTERY_HULL_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_HV_LITHIUM.getStackForm())
            .outputs(BATTERY_HULL_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(BATTERY_RE_HV_SODIUM.getStackForm())
            .outputs(BATTERY_HULL_HV.getStackForm())
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_LV.getStackForm())
            .input(OrePrefix.dust, Materials.Cadmium, 2)
            .outputs(BATTERY_RE_LV_CADMIUM.getStackForm())
            .duration(100).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_LV.getStackForm())
            .input(OrePrefix.dust, Materials.Lithium, 2)
            .outputs(BATTERY_RE_LV_LITHIUM.getStackForm())
            .duration(100).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_LV.getStackForm())
            .input(OrePrefix.dust, Materials.Sodium, 2)
            .outputs(BATTERY_RE_LV_SODIUM.getStackForm())
            .duration(100).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_MV.getStackForm())
            .input(OrePrefix.dust, Materials.Cadmium, 8)
            .outputs(BATTERY_RE_MV_CADMIUM.getStackForm())
            .duration(400).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_MV.getStackForm())
            .input(OrePrefix.dust, Materials.Lithium, 8)
            .outputs(BATTERY_RE_MV_LITHIUM.getStackForm())
            .duration(400).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_MV.getStackForm())
            .input(OrePrefix.dust, Materials.Sodium, 8)
            .outputs(BATTERY_RE_MV_SODIUM.getStackForm())
            .duration(400).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_HV.getStackForm())
            .input(OrePrefix.dust, Materials.Cadmium, 16)
            .outputs(BATTERY_RE_HV_CADMIUM.getStackForm())
            .duration(1600).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_HV.getStackForm())
            .input(OrePrefix.dust, Materials.Lithium, 16)
            .outputs(BATTERY_RE_HV_LITHIUM.getStackForm())
            .duration(1600).EUt(2)
            .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder()
            .inputs(BATTERY_HULL_HV.getStackForm())
            .input(OrePrefix.dust, Materials.Sodium, 16)
            .outputs(BATTERY_RE_HV_SODIUM.getStackForm())
            .duration(1600).EUt(2)
            .buildAndRegister();

    }

    @Override
    public boolean isBeaconPayment(ItemStack stack) {
        int damage = stack.getMetadata();
        if (damage >= this.metaItemOffset) {
            return false;
        }
        Material material = Material.MATERIAL_REGISTRY.getObjectById(damage % 1000);
        OrePrefix prefix = this.orePrefixes[(damage / 1000)];
        if(prefix != null && material != null) {
            boolean isSolidState = prefix == OrePrefix.ingot || prefix == OrePrefix.gem;
            boolean isMaterialTiered = material instanceof SolidMaterial && ((SolidMaterial) material).harvestLevel >= 2;
            return isSolidState && isMaterialTiered;
        }
        return false;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem itemEntity) {
        int damage = itemEntity.getItem().getMetadata();
        if (damage >= this.metaItemOffset || itemEntity.getEntityWorld().isRemote)
            return false;
        Material material = Material.MATERIAL_REGISTRY.getObjectById(damage % 1000);
        OrePrefix prefix = this.orePrefixes[(damage / 1000)];
        if (!purifyMap.containsKey(prefix))
            return false;
        BlockPos blockPos = new BlockPos(itemEntity);
        IBlockState blockState = itemEntity.getEntityWorld().getBlockState(blockPos);
        int waterLevel = blockState.getBlock() instanceof BlockCauldron ?
            blockState.getValue(BlockCauldron.LEVEL) : 0;
        if (waterLevel == 0)
            return false;
        itemEntity.getEntityWorld().setBlockState(blockPos,
            blockState.withProperty(BlockCauldron.LEVEL, waterLevel - 1));
        ItemStack replacementStack = OreDictUnifier.get(purifyMap.get(prefix), material,
            itemEntity.getItem().getCount());
        itemEntity.setItem(replacementStack);
        return false;
    }

    @Override
    protected void addMaterialTooltip(ItemStack itemStack, OrePrefix prefix, Material material, List<String> lines, ITooltipFlag tooltipFlag) {
        if (prefix == OrePrefix.dustImpure || prefix == OrePrefix.dustPure) {
            lines.add(I18n.format("metaitem.dust.tooltip.purify"));
        }
    }

}