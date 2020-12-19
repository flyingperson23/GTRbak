package gtr.common.items;

import gtr.api.GTValues;
import gtr.api.items.materialitem.MaterialMetaItem;
import gtr.api.items.metaitem.ElectricStats;
import gtr.api.items.metaitem.FoodStats;
import gtr.api.items.metaitem.stats.IItemContainerItemProvider;
import gtr.api.recipes.CountableIngredient;
import gtr.api.recipes.RecipeMaps;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.MarkerMaterials.Tier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.util.RandomPotionEffect;
import gtr.common.ConfigHolder;
import gtr.common.items.behaviors.FacadeItem;
import gtr.common.items.behaviors.ScannerBehavior;
import gtr.common.items.behaviors.NanoSaberBehavior;
import gtr.common.items.behaviors.TurbineRotorBehavior;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import static gtr.api.util.DyeUtil.getOrdictColorName;
import static gtr.common.items.MetaItems.*;

public class MetaItem2 extends MaterialMetaItem {

    public MetaItem2() {
        super(OrePrefix.toolHeadSword, OrePrefix.toolHeadPickaxe, OrePrefix.toolHeadShovel, OrePrefix.toolHeadAxe,
            OrePrefix.toolHeadHoe, OrePrefix.toolHeadHammer, OrePrefix.toolHeadFile, OrePrefix.toolHeadSaw,
            OrePrefix.toolHeadDrill, OrePrefix.toolHeadChainsaw, OrePrefix.toolHeadWrench, OrePrefix.toolHeadUniversalSpade,
            OrePrefix.toolHeadSense, null, OrePrefix.toolHeadBuzzSaw, OrePrefix.turbineBlade,
            OrePrefix.wireFine, OrePrefix.gearSmall, OrePrefix.rotor, OrePrefix.stickLong, OrePrefix.springSmall, OrePrefix.spring,
            OrePrefix.gemChipped, OrePrefix.gemFlawed, OrePrefix.gemFlawless, OrePrefix.gemExquisite, OrePrefix.gear,
            null, null, null, null, null);
    }

    @Override
    public void registerSubItems() {
        GELLED_TOLUENE = addItem(10, "gelled_toluene");

        IItemContainerItemProvider selfContainerItemProvider = itemStack -> itemStack;
        WOODEN_FORM_EMPTY = addItem(11, "wooden_form.empty");
        WOODEN_FORM_BRICK = addItem(12, "wooden_form.brick").addComponents(selfContainerItemProvider);

        COMPRESSED_CLAY = addItem(13, "compressed.clay");
        COMPRESSED_FIRECLAY = addItem(14, "compressed.fireclay");
        FIRECLAY_BRICK = addItem(15, "brick.fireclay");
        COKE_OVEN_BRICK = addItem(16, "brick.coke");

        BOTTLE_PURPLE_DRINK = addItem(100, "bottle.purple.drink").addComponents(new FoodStats(8, 0.2F, true, true, new ItemStack(Items.GLASS_BOTTLE), new RandomPotionEffect(MobEffects.HASTE, 800, 1, 90)));

        ENERGY_CRYSTAL = addItem(212, "energy_crystal").addComponents(ElectricStats.createRechargeableBattery(4000000L, GTValues.HV)).setModelAmount(8).setMaxStackSize(1);
        LAPOTRON_CRYSTAL = addItem(213, "lapotron_crystal").addComponents(ElectricStats.createRechargeableBattery(10000000L, GTValues.EV)).setModelAmount(8).setMaxStackSize(1);

        DYE_INDIGO = addItem(410, "dye.indigo").addOreDict("dyeBlue").setInvisible();
        for (int i = 0; i < EnumDyeColor.values().length; i++) {
            EnumDyeColor dyeColor = EnumDyeColor.values()[i];
            DYE_ONLY_ITEMS[i] = addItem(414 + i, "dye." + dyeColor.getName()).addOreDict(getOrdictColorName(dyeColor));
        }

        PLANT_BALL = addItem(570, "plant_ball").setBurnValue(75);
        ENERGIUM_DUST = addItem(572, "energium_dust");

        POWER_UNIT_LV = addItem(573, "power_unit.lv").addComponents(ElectricStats.createElectricItem(100000L, GTValues.LV)).setMaxStackSize(8);
        POWER_UNIT_MV = addItem(574, "power_unit.mv").addComponents(ElectricStats.createElectricItem(400000L, GTValues.MV)).setMaxStackSize(8);
        POWER_UNIT_HV = addItem(575, "power_unit.hv") .addComponents(ElectricStats.createElectricItem(1600000L, GTValues.HV)).setMaxStackSize(8);
        JACKHAMMER_BASE = addItem(576, "jackhammer_base").addComponents(ElectricStats.createElectricItem(1600000L, GTValues.HV)).setMaxStackSize(4);

        NANO_SABER = addItem(577, "nano_saber").addComponents(ElectricStats.createElectricItem(4000000L, GTValues.HV)).addComponents(new NanoSaberBehavior()).setMaxStackSize(1);
        ENERGY_FIELD_PROJECTOR = addItem(578, "energy_field_projector").addComponents(ElectricStats.createElectricItem(16000000L, GTValues.EV)).setMaxStackSize(1);
        SCANNER = addItem(579, "scanner").addComponents(ElectricStats.createElectricItem(200_000L, GTValues.LV), new ScannerBehavior(50));

        INGOT_MIXED_METAL = addItem(432, "ingot.mixed_metal");
        ADVANCED_ALLOY_PLATE = addItem(433, "plate.advanced_alloy");
        INGOT_IRIDIUM_ALLOY = addItem(434, "ingot.iridium_alloy");
        PLATE_IRIDIUM_ALLOY = addItem(435, "plate.iridium_alloy");
        NEUTRON_REFLECTOR = addItem(436, "neutron_reflector");

        TURBINE_ROTOR = addItem(508, "turbine_rotor").addComponents(new TurbineRotorBehavior());
        COVER_FACADE = addItem(509, "cover.facade").addComponents(new FacadeItem()).disableModelLoading();





        CIRCUIT_LV = addItem(530, "circuit.basic").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Basic);
        CIRCUIT_MV = addItem(531, "circuit.intermediate").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Good);
        CIRCUIT_HV = addItem(532, "circuit.advanced").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Advanced);
        CIRCUIT_EV = addItem(533, "circuit.elite").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Elite);

        CIRCUIT_ULTIMATE = addItem(534, "circuit.ultimate").setUnificationData(OrePrefix.circuit, Tier.Ultimate);

        BOARD_LV = addItem(542, "board.basic").addOreDict("boardBasic");
        BOARD_MV = addItem(543, "board.intermediate").addOreDict("boardIntermediate");
        BOARD_HV = addItem(544, "board.advanced").addOreDict("boardAdvanced");
        BOARD_EV = addItem(545, "board.elite").addOreDict("boardElite");

        WIRING_LV = addItem(550, "wiring.basic").addOreDict("wiringBasic");
        WIRING_MV = addItem(551, "wiring.intermediate").addOreDict("wiringIntermediate");
        WIRING_HV = addItem(552, "wiring.advanced").addOreDict("wiringAdvanced");
        WIRING_EV = addItem(553, "wiring.elite").addOreDict("wiringElite");

        SOC_LV = addItem(558, "soc.basic").addOreDict("socBasic");
        SOC_MV = addItem(559, "soc.intermediate").addOreDict("socIntermediate");
        SOC_HV = addItem(560, "soc.advanced").addOreDict("socAdvanced");
        SOC_EV = addItem(561, "soc.elite").addOreDict("socElite");

        ETCHING_KIT_LV = addItem(562, "etchingkit.basic").addOreDict("etchingKitBasic");
        ETCHING_KIT_MV = addItem(563, "etchingkit.intermediate").addOreDict("etchingKitIntermediate");
        ETCHING_KIT_HV = addItem(564, "etchingkit.advanced").addOreDict("etchingKitAdvanced");
        ETCHING_KIT_EV = addItem(565, "etchingkit.elite").addOreDict("etchingKitElite");

        GLASS_TUBE = addItem(538, "glass_tube");
        CIRCUIT_VACUUM_TUBE_LV = addItem(539, "vacuum_tube").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.Basic);
    }

    public void registerRecipes() {
        // Dyes recipes
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 0))
            .outputs(new ItemStack(Items.DYE, 2, 1))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 1))
            .outputs(new ItemStack(Items.DYE, 2, 12))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 2))
            .outputs(new ItemStack(Items.DYE, 2, 13))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 3))
            .outputs(new ItemStack(Items.DYE, 2, 7))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 4))
            .outputs(new ItemStack(Items.DYE, 2, 1))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 5))
            .outputs(new ItemStack(Items.DYE, 2, 14))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 6))
            .outputs(new ItemStack(Items.DYE, 2, 7))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 7))
            .outputs(new ItemStack(Items.DYE, 2, 9))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.RED_FLOWER, 1, 8))
            .outputs(new ItemStack(Items.DYE, 2, 7))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.YELLOW_FLOWER, 1, 0))
            .outputs(new ItemStack(Items.DYE, 2, 11))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.DOUBLE_PLANT, 1, 0))
            .outputs(new ItemStack(Items.DYE, 3, 11))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.DOUBLE_PLANT, 1, 1))
            .outputs(new ItemStack(Items.DYE, 3, 13))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.DOUBLE_PLANT, 1, 4))
            .outputs(new ItemStack(Items.DYE, 3, 1))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.DOUBLE_PLANT, 1, 5))
            .outputs(new ItemStack(Items.DYE, 3, 9))
            .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.BEETROOT, 1))
            .outputs(new ItemStack(Items.DYE, 2, 1))
            .buildAndRegister();

        // Misc
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage()))
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Cocoa, 1))
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.REEDS, 1))
            .outputs(new ItemStack(Items.SUGAR, 1))
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.MELON_BLOCK, 1, 0))
            .outputs(new ItemStack(Items.MELON, 8, 0))
            .chancedOutput(new ItemStack(Items.MELON_SEEDS, 1), 8000, 500)
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Blocks.PUMPKIN, 1, 0))
            .outputs(new ItemStack(Items.PUMPKIN_SEEDS, 4, 0))
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.MELON, 1, 0))
            .outputs(new ItemStack(Items.MELON_SEEDS, 1, 0))
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.WHEAT, 1, 0))
            .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Wheat, 1))
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(new ItemStack(Items.STICK, 1))
            .outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Wood, 2))
            .duration(400)
            .EUt(2)
            .buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
            .inputs(CountableIngredient.from("blockWool", 1))
            .outputs(new ItemStack(Items.STRING, 3))
            .chancedOutput(new ItemStack(Items.STRING, 1), 2000, 800)
            .duration(400)
            .EUt(2)
            .buildAndRegister();
    }
}