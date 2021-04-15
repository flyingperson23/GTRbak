package gtr.loaders.recipe;

import gtr.api.GTValues;
import gtr.api.items.metaitem.MetaItem.MetaValueItem;
import gtr.api.recipes.ModHandler;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.MarkerMaterials.Tier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.UnificationEntry;
import gtr.api.util.GTLog;
import gtr.common.ConfigHolder;
import gtr.common.blocks.BlockMultiblockCasing;
import gtr.common.blocks.BlockWireCoil;
import gtr.common.blocks.MetaBlocks;
import gtr.common.blocks.wood.BlockGregLog.LogVariant;
import gtr.common.crafting.FacadeRecipe;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.loaders.oreprocessing.ToolRecipeHandler;
import gtr.loaders.recipe.magnetic.RecipeMagnetic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.RecipeSorter;

import static gtr.api.util.DyeUtil.*;

public class CraftingRecipeLoader {

    public static void init() {
        loadCraftingRecipes();
    }

    private static void loadCraftingRecipes() {
        RecipeSorter.register("gtr:magnetic", RecipeMagnetic.class, RecipeSorter.Category.SHAPELESS,
            "after:minecraft:shapeless");
        GameRegistry.findRegistry(IRecipe.class).register(new RecipeMagnetic().setRegistryName("gtr:magnetic"));

        ModHandler.addShapedRecipe("nano_helm", MetaItems.NANO_HELMET.getChargedStack(0), "CCC", "CEC", "   ", 'C', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'E', MetaItems.ENERGY_CRYSTAL);
        ModHandler.addShapedRecipe("nano_chest", MetaItems.NANO_CHESTPLATE.getChargedStack(0), "CEC", "CCC", "CCC", 'C', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'E', MetaItems.ENERGY_CRYSTAL);
        ModHandler.addShapedRecipe("nano_legs", MetaItems.NANO_LEGGINGS.getChargedStack(0), "CCC", "CEC", "C C", 'C', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'E', MetaItems.ENERGY_CRYSTAL);
        ModHandler.addShapedRecipe("nano_boots", MetaItems.NANO_BOOTS.getChargedStack(0), "   ", "CEC", "C C", 'C', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'E', MetaItems.ENERGY_CRYSTAL);

        ModHandler.addShapedRecipe("quantum_helm", MetaItems.QUANTUM_HELMET.getChargedStack(0), "INI", "IEI", "   ", 'I', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'N', MetaItems.NANO_HELMET.getStackForm(), 'E', MetaItems.LAPOTRON_CRYSTAL);
        ModHandler.addShapedRecipe("quantum_chest", MetaItems.QUANTUM_CHESTPLATE.getChargedStack(0), "IEI", "INI", "III", 'I', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'N', MetaItems.NANO_CHESTPLATE.getStackForm(), 'E', MetaItems.LAPOTRON_CRYSTAL);
        ModHandler.addShapedRecipe("quantum_legs", MetaItems.QUANTUM_LEGGINGS.getChargedStack(0), "INI", "IEI", "I I", 'I', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'N', MetaItems.NANO_LEGGINGS.getStackForm(), 'E', MetaItems.LAPOTRON_CRYSTAL);
        ModHandler.addShapedRecipe("quantum_boots", MetaItems.QUANTUM_BOOTS.getChargedStack(0), "   ", "IEI", "INI", 'I', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'N', MetaItems.NANO_BOOTS.getStackForm(), 'E', MetaItems.LAPOTRON_CRYSTAL);

        ModHandler.addShapedRecipe("nano_jetpack", MetaItems.NANO_SUIT_JETPACK.getChargedStack(0), "IJI", "MCM", "R R", 'I', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'J', MetaItems.ELECTRIC_JETPACK.getStackForm(), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), 'C', MetaItems.NANO_CHESTPLATE, 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.Chrome));
        ModHandler.addShapedRecipe("quantum_jetpack", MetaItems.QUANTUM_SUIT_JETPACK.getChargedStack(0), "IJI", "MCM", "R R", 'I', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'J', MetaItems.ELECTRIC_JETPACK, 'M', MetaItems.ELECTRIC_MOTOR_EV, 'C', MetaItems.QUANTUM_CHESTPLATE.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.TungstenSteel));
        ModHandler.addShapedRecipe("electric_jetpck", MetaItems.ELECTRIC_JETPACK.getChargedStack(0), "SES", "MSM", "R R", 'S', OreDictUnifier.get(OrePrefix.plate, Materials.Steel), 'E', MetaItems.BATTERY_RE_MV_CADMIUM.getStackForm(), 'M', MetaItems.ELECTRIC_MOTOR_MV, 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.Bronze));

        ModHandler.addShapedRecipe("nano_sword", MetaItems.NANO_SABER.getChargedStack(0), " P ", " C ", "CEC", 'P', OreDictUnifier.get(OrePrefix.toolHeadSword, Materials.Platinum), 'C', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'E', MetaItems.ENERGY_CRYSTAL);
        ModHandler.addShapedRecipe("nano_bow", MetaItems.NANO_BOW.getChargedStack(0), " CS", "E S", " CS", 'C', OreDictUnifier.get(OrePrefix.plate, Materials.CarbonFiberReinforcedPolymer), 'E', MetaItems.ENERGY_CRYSTAL, 'S', Items.STRING);
        ModHandler.addShapedRecipe("magnet", MetaItems.ELECTROMAGNET.getChargedStack(0), "M M", "SBS", "SSS", 'M', OreDictUnifier.get(OrePrefix.plate, Materials.SteelMagnetic), 'S', OreDictUnifier.get(OrePrefix.plate, Materials.Steel), 'B', MetaItems.BATTERY_RE_LV_CADMIUM);

        ModHandler.addShapedRecipe("battery_holder", MetaTileEntities.BATTERY_HOLDER.getStackForm(), "BCB", "IEO", "BCB", 'B', MetaTileEntities.BATTERY_BUFFER[GTValues.EV][2].getStackForm(), 'C', MetaBlocks.MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.HIGH_POWER), 'I', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV].getStackForm(), 'E', MetaItems.CIRCUIT_EV, 'O', MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.EV].getStackForm());
        ModHandler.addShapedRecipe("large_batbuf", MetaTileEntities.LARGE_BATTERY_BUFFER.getStackForm(), "CMC", "MSM", "CMC", 'C', MetaItems.CIRCUIT_EV, 'M', MetaBlocks.MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.HIGH_POWER), 'S', OreDictUnifier.get(OrePrefix.cableGtOctal, Materials.Superconductor));

        ModHandler.addShapedRecipe("fusion_coil", MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), "POP", "OFO", "POP", 'P', OreDictUnifier.get(OrePrefix.plate, Materials.Polyacrylonitrile), 'O', OreDictUnifier.get(OrePrefix.plate, Materials.AcrylonitrileButadieneStyrene), 'F', MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR));

        ModHandler.addShapedRecipe("jachammer_base", MetaItems.JACKHAMMER_BASE.getChargedStack(0), " P ", "PCP", "SBS", 'P', MetaItems.ELECTRIC_PISTON_HV, 'C', MetaItems.CIRCUIT_LV, 'S', OreDictUnifier.get(OrePrefix.plate, Materials.Steel), 'B', MetaItems.BATTERY_RE_HV_LITHIUM);
        ModHandler.addShapedRecipe("rebreather", MetaItems.REBREATHER.getChargedStack(0), "GGG", "GEG", "BBB", 'G', Blocks.GLASS_PANE, 'E', MetaTileEntities.ELECTROLYZER[GTValues.LV].getStackForm(), 'B', MetaItems.BATTERY_RE_LV_CADMIUM);

        ModHandler.addShapedRecipe("fusion_core", MetaTileEntities.FUSION_REACTOR.getStackForm(), "COC", "OAO", "COC", 'C', MetaItems.CIRCUIT_EV, 'O', MetaItems.ENERGY_LAPOTRONIC_ORB, 'A', MetaBlocks.MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING));

        registerFacadeRecipe(Materials.Aluminium, 5);
        registerFacadeRecipe(Materials.WroughtIron, 3);
        registerFacadeRecipe(Materials.Iron, 2);

        ToolRecipeHandler.registerPowerUnitRecipes();
        ModHandler.addShapedRecipe("small_wooden_pipe", OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Wood, 4), "WWW", "h f", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood));
        ModHandler.addShapedRecipe("medium_wooden_pipe", OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Wood, 2), "WWW", "f h", "WWW", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood));

        ModHandler.addShapelessRecipe("clay_block_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Clay, 4), 'm', Blocks.CLAY);
        ModHandler.addShapelessRecipe("clay_ball_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Clay), 'm', Items.CLAY_BALL);
        ModHandler.addShapelessRecipe("brick_block_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Brick, 4), 'm', Blocks.BRICK_BLOCK);
        ModHandler.addShapelessRecipe("brick_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Brick), 'm', Items.BRICK);
        ModHandler.addShapelessRecipe("wheat_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Wheat, 1), 'm', Items.WHEAT);
        ModHandler.addShapelessRecipe("gravel_to_flint", new ItemStack(Items.FLINT, 1), 'm', Blocks.GRAVEL);
        ModHandler.addShapelessRecipe("bone_to_bone_meal", new ItemStack(Items.DYE, 4, 15), 'm', Items.BONE);
        ModHandler.addShapelessRecipe("blaze_rod_to_powder", new ItemStack(Items.BLAZE_POWDER, 3), 'm', Items.BLAZE_ROD);

        ModHandler.addShapedRecipe("item_filter", MetaItems.ITEM_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe("fluid_filter", MetaItems.FLUID_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Lapis));

        ModHandler.addShapedRecipe("ore_dictionary_filter_olivine", MetaItems.ORE_DICTIONARY_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Olivine));
        ModHandler.addShapedRecipe("ore_dictionary_filter_emerald", MetaItems.ORE_DICTIONARY_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Emerald));

        ModHandler.addShapedRecipe("smart_item_filter_olivine", MetaItems.SMART_FILTER.getStackForm(), "XEX", "XCX", "XEX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic), 'E', new UnificationEntry(OrePrefix.plate, Materials.Olivine));
        ModHandler.addShapedRecipe("smart_item_filter_emerald", MetaItems.SMART_FILTER.getStackForm(), "XEX", "XCX", "XEX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic), 'E', new UnificationEntry(OrePrefix.plate, Materials.Emerald));

        ModHandler.addShapedRecipe("plank_to_wooden_shape", MetaItems.WOODEN_FORM_EMPTY.getStackForm(), "   ", " X ", "s  ", 'X', new UnificationEntry(OrePrefix.plank, Materials.Wood));
        ModHandler.addShapedRecipe("wooden_shape_brick", MetaItems.WOODEN_FORM_BRICK.getStackForm(), "k ", " X", 'X', MetaItems.WOODEN_FORM_EMPTY.getStackForm());
        ModHandler.addShapedRecipe("compressed_clay", MetaItems.COMPRESSED_CLAY.getStackForm(8), "XXX", "XYX", "XXX", 'Y', MetaItems.WOODEN_FORM_BRICK.getStackForm(), 'X', Items.CLAY_BALL);
        ModHandler.addShapelessRecipe("fireclay_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Fireclay, 2), new UnificationEntry(OrePrefix.dust, Materials.Brick), new UnificationEntry(OrePrefix.dust, Materials.Clay));
        ModHandler.addSmeltingRecipe(MetaItems.COMPRESSED_CLAY.getStackForm(), MetaItems.COKE_OVEN_BRICK.getStackForm());
        ModHandler.addSmeltingRecipe(MetaItems.COMPRESSED_FIRECLAY.getStackForm(), MetaItems.FIRECLAY_BRICK.getStackForm());

        ModHandler.addSmeltingRecipe(new UnificationEntry(OrePrefix.nugget, Materials.Iron), OreDictUnifier.get(OrePrefix.nugget, Materials.WroughtIron));

        for (MetaValueItem batteryItem : ToolRecipeHandler.batteryItems[0]) {
            ModHandler.addShapedEnergyTransferRecipe("scanner_" + batteryItem.unlocalizedName, MetaItems.SCANNER.getStackForm(),
                batteryItem::isItemEqual, true,
                "DGD", "CGC", "SBS",
                'D', new UnificationEntry(OrePrefix.plate, Materials.Diamond),
                'G', new UnificationEntry(OrePrefix.paneGlass),
                'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic),
                'S', new UnificationEntry(OrePrefix.plate, Materials.Steel),
                'B', batteryItem.getStackForm());
        }

        for(Material material : new Material[] {Materials.Lapis, Materials.Lazurite, Materials.Sodalite}) {
            String recipeName = "lapotron_crystal_" + material.toString();
            ModHandler.addShapedEnergyTransferRecipe(recipeName, MetaItems.LAPOTRON_CRYSTAL.getStackForm(),
                Ingredient.fromStacks(MetaItems.ENERGY_CRYSTAL.getStackForm()), false,
                "XCX", "XEX", "XCX",
                'X', new UnificationEntry(OrePrefix.plate, material),
                'C', new UnificationEntry(OrePrefix.circuit, Tier.Advanced),
                'E', MetaItems.ENERGY_CRYSTAL.getStackForm());
        }

        for (MetaValueItem batteryItem : ToolRecipeHandler.batteryItems[1]) {
            ItemStack batteryStack = batteryItem.getStackForm();
            ModHandler.addShapedEnergyTransferRecipe("rebreather_" + batteryItem.unlocalizedName,
                MetaItems.REBREATHER.getStackForm(),
                Ingredient.fromStacks(batteryStack), true,
                "CEC", "PGP", "BUB",
                'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic),
                'E', MetaTileEntities.ELECTROLYZER[0].getStackForm(),
                'G', new UnificationEntry(OrePrefix.glass, null),
                'P', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel),
                'B', batteryStack,
                'U', MetaItems.ELECTRIC_PUMP_LV.getStackForm());
        }

        ModHandler.addShapelessRecipe("rubber_wood_planks", new ItemStack(Blocks.PLANKS, 4, EnumType.JUNGLE.getMetadata()), new ItemStack(MetaBlocks.LOG, 1, LogVariant.RUBBER_WOOD.ordinal()));

        ModHandler.addShapedRecipe("paper_ring", OreDictUnifier.get(OrePrefix.ring, Materials.Paper), "k", "X", 'X', new UnificationEntry(OrePrefix.plate, Materials.Paper));
        ModHandler.addShapedRecipe("rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.Rubber), "k", "X", 'X', new UnificationEntry(OrePrefix.plate, Materials.Rubber));
        ModHandler.addShapedRecipe("silicone_rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.SiliconeRubber), "k", "P", 'P', OreDictUnifier.get(OrePrefix.plate, Materials.SiliconeRubber));
        ModHandler.addShapedRecipe("styrene_rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.StyreneButadieneRubber), "k", "P", 'P', OreDictUnifier.get(OrePrefix.plate, Materials.StyreneButadieneRubber));

        ModHandler.addShapedRecipe("rubber_drop_torch", new ItemStack(Blocks.TORCH, 3), "X", "Y", 'X', MetaItems.RUBBER_DROP, 'Y', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        ModHandler.addShapedRecipe("lignite_coal_torch", new ItemStack(Blocks.TORCH, 4), "X", "Y", 'X', new UnificationEntry(OrePrefix.gem, Materials.Lignite), 'Y', new UnificationEntry(OrePrefix.stick, Materials.Wood));

        ModHandler.addShapelessRecipe("iron_magnetic_stick", OreDictUnifier.get(OrePrefix.stick, Materials.IronMagnetic), new UnificationEntry(OrePrefix.stick, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone));

        ModHandler.addShapedRecipe("torch_sulfur", new ItemStack(Blocks.TORCH, 2), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Sulfur), 'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        ModHandler.addShapedRecipe("torch_phosphor", new ItemStack(Blocks.TORCH, 6), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Phosphorus), 'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));

        ModHandler.addShapedRecipe("piston_bronze", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Bronze));
        ModHandler.addShapedRecipe("piston_aluminium", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Aluminium));
        ModHandler.addShapedRecipe("piston_steel", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Steel));
        ModHandler.addShapedRecipe("piston_titanium", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Titanium));

        ModHandler.addShapelessRecipe("dynamite", MetaItems.DYNAMITE.getStackForm(), Items.STRING, Items.PAPER, Items.GUNPOWDER);
        GTLog.logger.info("Modifying vanilla recipes according to config. DON'T BE SCARED OF FML's WARNING ABOUT DANGEROUS ALTERNATIVE PREFIX.");

        if (ConfigHolder.vanillaRecipes.bucketRequirePlatesAndHammer) {
            ModHandler.addShapedRecipe("iron_bucket", new ItemStack(Items.BUCKET), "XhX", " X ", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bucket"));
        }
        if (ConfigHolder.vanillaRecipes.ironConsumingCraftingRecipesRequirePlates) {
            ModHandler.addShapedRecipe("iron_pressure_plate", new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:heavy_weighted_pressure_plate"));

            ModHandler.addShapedRecipe("gold_pressure_plate", new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE), "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Gold));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:light_weighted_pressure_plate"));

            ModHandler.addShapedRecipe("iron_door", new ItemStack(Items.IRON_DOOR, 3), "XX ", "XXh", "XX ", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_door"));

            ModHandler.addShapedRecipe("iron_trapdoor", new ItemStack(Blocks.IRON_TRAPDOOR), "XX ", "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_trapdoor"));

            ModHandler.addShapedRecipe("cauldron", new ItemStack(Items.CAULDRON), "X X", "XhX", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:cauldron"));

            ModHandler.addShapedRecipe("hopper", new ItemStack(Blocks.HOPPER), "XwX", "XCX", " X ", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron), 'C', "chestWood");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:hopper"));

            ModHandler.addShapedRecipe("iron_bars", new ItemStack(Blocks.IRON_BARS, 8), " w ", "XXX", "XXX", 'X', new UnificationEntry(OrePrefix.stick, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_bars"));
        }

        if (ConfigHolder.vanillaRecipes.bowlRequireKnife) {
            ModHandler.addShapedRecipe("bowl", new ItemStack(Items.BOWL), "k", "X", 'X', new UnificationEntry(OrePrefix.plank, Materials.Wood));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bowl"));
        }

        if (ConfigHolder.vanillaRecipes.nerfStickCrafting) {
            ModHandler.addShapedRecipe("stick_saw", new ItemStack(Items.STICK, 4), "s", "P", "P", 'P', new UnificationEntry(OrePrefix.plank, Materials.Wood));
            ModHandler.addShapedRecipe("stick_normal", new ItemStack(Items.STICK, 2), "P", "P", 'P', new UnificationEntry(OrePrefix.plank, Materials.Wood));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:stick"));
        }

        ModHandler.addShapelessRecipe("dust_ferrite_mixture", OreDictUnifier.get(OrePrefix.dust, Materials.FerriteMixture, 6), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Zinc), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron));
        ModHandler.addShapelessRecipe("dust_electrum", OreDictUnifier.get(OrePrefix.dust, Materials.Electrum, 2), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Gold));
        ModHandler.addShapelessRecipe("dust_brass", OreDictUnifier.get(OrePrefix.dust, Materials.Brass, 4), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Zinc));
        ModHandler.addShapelessRecipe("dust_bronze", OreDictUnifier.get(OrePrefix.dust, Materials.Bronze, 4), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Tin));
        ModHandler.addShapelessRecipe("dust_invar", OreDictUnifier.get(OrePrefix.dust, Materials.Invar, 3), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Nickel));
        ModHandler.addShapelessRecipe("dust_cupronickel", OreDictUnifier.get(OrePrefix.dust, Materials.Cupronickel, 2), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Copper));

        ModHandler.addShapelessRecipe("dust_rose_gold", OreDictUnifier.get(OrePrefix.dust, Materials.RoseGold, 5), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_sterling_silver", OreDictUnifier.get(OrePrefix.dust, Materials.SterlingSilver, 5), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_bismuth_bronze", OreDictUnifier.get(OrePrefix.dust, Materials.BismuthBronze, 5), new UnificationEntry(OrePrefix.dust, Materials.Bismuth), new UnificationEntry(OrePrefix.dust, Materials.Zinc), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper));

        ModHandler.addShapelessRecipe("dust_ultimet", OreDictUnifier.get(OrePrefix.dust, Materials.Ultimet, 9), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum));
        ModHandler.addShapelessRecipe("dust_stainless_steel", OreDictUnifier.get(OrePrefix.dust, Materials.StainlessSteel, 9), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Manganese), new UnificationEntry(OrePrefix.dust, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_yttrium_barium_cuprate", OreDictUnifier.get(OrePrefix.dust, Materials.YttriumBariumCuprate, 6), new UnificationEntry(OrePrefix.dust, Materials.Yttrium), new UnificationEntry(OrePrefix.dust, Materials.Barium), new UnificationEntry(OrePrefix.dust, Materials.Barium), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_kanthal", OreDictUnifier.get(OrePrefix.dust, Materials.Kanthal, 3), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Chrome));

        ModHandler.addShapelessRecipe("dust_tiny_ultimet", OreDictUnifier.get(OrePrefix.dust, Materials.Ultimet, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome), new UnificationEntry(OrePrefix.dustTiny, Materials.Nickel), new UnificationEntry(OrePrefix.dustTiny, Materials.Molybdenum));
        ModHandler.addShapelessRecipe("dust_tiny_stainless_steel", OreDictUnifier.get(OrePrefix.dust, Materials.StainlessSteel, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Nickel), new UnificationEntry(OrePrefix.dustTiny, Materials.Manganese), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_tiny_yttrium_barium_cuprate", OreDictUnifier.get(OrePrefix.dustTiny, Materials.YttriumBariumCuprate, 6), new UnificationEntry(OrePrefix.dustTiny, Materials.Yttrium), new UnificationEntry(OrePrefix.dustTiny, Materials.Barium), new UnificationEntry(OrePrefix.dustTiny, Materials.Barium), new UnificationEntry(OrePrefix.dustTiny, Materials.Copper), new UnificationEntry(OrePrefix.dustTiny, Materials.Copper), new UnificationEntry(OrePrefix.dustTiny, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_tiny_kanthal", OreDictUnifier.get(OrePrefix.dustTiny, Materials.Kanthal, 3), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Aluminium), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome));

        ModHandler.addShapelessRecipe("dust_vanadium_steel", OreDictUnifier.get(OrePrefix.dust, Materials.VanadiumSteel, 9), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Vanadium), new UnificationEntry(OrePrefix.dust, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_hssg", OreDictUnifier.get(OrePrefix.dust, Materials.HSSG, 9), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum), new UnificationEntry(OrePrefix.dust, Materials.Vanadium));
        ModHandler.addShapelessRecipe("dust_hsse", OreDictUnifier.get(OrePrefix.dust, Materials.HSSE, 9), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Manganese), new UnificationEntry(OrePrefix.dust, Materials.Silicon));
        ModHandler.addShapelessRecipe("dust_hsss", OreDictUnifier.get(OrePrefix.dust, Materials.HSSS, 9), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.Iridium), new UnificationEntry(OrePrefix.dust, Materials.Iridium), new UnificationEntry(OrePrefix.dust, Materials.Osmium));

        ModHandler.addShapelessRecipe("powder_coal", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));
        ModHandler.addShapelessRecipe("powder_charcoal", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));
        ModHandler.addShapelessRecipe("powder_carbon", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));



        //Crafting components
        MetaValueItem[] motors = new MetaValueItem[] {MetaItems.ELECTRIC_MOTOR_LV, MetaItems.ELECTRIC_MOTOR_MV, MetaItems.ELECTRIC_MOTOR_HV, MetaItems.ELECTRIC_MOTOR_EV, MetaItems.ELECTRIC_MOTOR_IV};
        MetaValueItem[] conveyors = new MetaValueItem[] {MetaItems.CONVEYOR_MODULE_LV, MetaItems.CONVEYOR_MODULE_MV, MetaItems.CONVEYOR_MODULE_HV, MetaItems.CONVEYOR_MODULE_EV, MetaItems.CONVEYOR_MODULE_IV};
        MetaValueItem[] pistons = new MetaValueItem[] {MetaItems.ELECTRIC_PISTON_LV, MetaItems.ELECTRIC_PISTON_MV, MetaItems.ELECTRIC_PISTON_HV, MetaItems.ELECTRIC_PISTON_EV, MetaItems.ELECTRIC_PISTON_IV};
        MetaValueItem[] pumps = new MetaValueItem[] {MetaItems.ELECTRIC_PUMP_LV, MetaItems.ELECTRIC_PUMP_MV, MetaItems.ELECTRIC_PUMP_HV, MetaItems.ELECTRIC_PUMP_EV, MetaItems.ELECTRIC_PUMP_IV};
        MetaValueItem[] emitters = new MetaValueItem[] {MetaItems.EMITTER_LV, MetaItems.EMITTER_MV, MetaItems.EMITTER_HV, MetaItems.EMITTER_EV, MetaItems.EMITTER_IV};
        MetaValueItem[] field_generators = new MetaValueItem[] {MetaItems.FIELD_GENERATOR_LV, MetaItems.FIELD_GENERATOR_MV, MetaItems.FIELD_GENERATOR_HV, MetaItems.FIELD_GENERATOR_EV, MetaItems.FIELD_GENERATOR_IV};
        MetaValueItem[] robot_arms = new MetaValueItem[] {MetaItems.ROBOT_ARM_LV, MetaItems.ROBOT_ARM_MV, MetaItems.ROBOT_ARM_HV, MetaItems.ROBOT_ARM_EV, MetaItems.ROBOT_ARM_IV};
        MetaValueItem[] sensors = new MetaValueItem[] {MetaItems.SENSOR_LV, MetaItems.SENSOR_MV, MetaItems.SENSOR_HV, MetaItems.SENSOR_EV, MetaItems.SENSOR_IV};




        for (int i = 0; i < motors.length; i++) {
            ModHandler.addShapedRecipe(motors[i].unlocalizedName, motors[i].getStackForm(1), "CWR", "WIW", "RWC",
                'C', CraftingComponent.CABLE.getIngredient(i), 'W', CraftingComponent.WIRE.getIngredient(i),
                'R', CraftingComponent.STICK.getIngredient(i), 'I', CraftingComponent.STICK_MAGNETIC.getIngredient(i));
        }

        for (int i = 0; i < conveyors.length; i++) {
            ModHandler.addShapedRecipe(conveyors[i].unlocalizedName, conveyors[i].getStackForm(1), "RRR", "MCM", "RRR",
                'R', new UnificationEntry(OrePrefix.plate, CraftingComponent.RUBBER.get(i)),
                'C', CraftingComponent.CABLE.getIngredient(i), 'M', CraftingComponent.MOTOR.getIngredient(i));
        }

        for (int i = 0; i < pistons.length; i++) {
            ModHandler.addShapedRecipe(pistons[i].unlocalizedName, pistons[i].getStackForm(1), "PPP", "CSS", "CMG",
                'P', CraftingComponent.PLATE.getIngredient(i), 'S', CraftingComponent.STICK.getIngredient(i),
                'G', new UnificationEntry(OrePrefix.gearSmall, CraftingComponent.MAIN_MATERIAL.get(i)),
                'M', CraftingComponent.MOTOR.getIngredient(i), 'C', CraftingComponent.CABLE.getIngredient(i));
        }



        for (int i = 0; i < pumps.length; i++) {
            ModHandler.addShapedRecipe(pumps[i].unlocalizedName, pumps[i].getStackForm(1), "SXO", "dPw", "OMW",
                'S', new UnificationEntry(OrePrefix.screw, CraftingComponent.MAIN_MATERIAL.get(i)),
                'X', CraftingComponent.ROTOR.getIngredient(i), 'W', CraftingComponent.CABLE_QUAD.getIngredient(i),
                'O', new UnificationEntry(OrePrefix.ring, CraftingComponent.RUBBER.get(i)),
                'P', CraftingComponent.PIPE.getIngredient(i), 'M', CraftingComponent.MOTOR.getIngredient(i));
        }

        for (int i = 0; i < robot_arms.length; i++) {
            ModHandler.addShapedRecipe(robot_arms[i].unlocalizedName, robot_arms[i].getStackForm(1), "CCC", "MSM", "PES",
                'C', CraftingComponent.CABLE.getIngredient(i), 'E', CraftingComponent.CIRCUIT.getIngredient(i),
                'P', CraftingComponent.PISTON.getIngredient(i), 'M', CraftingComponent.MOTOR.getIngredient(i),
                'S', CraftingComponent.STICK.getIngredient(i));
        }



        MetaBlocks.FRAMES.values().forEach(CraftingRecipeLoader::registerColoringRecipes);

        if (ConfigHolder.vanillaRecipes.nerfPaperCrafting) {
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:paper"));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:sugar"));
            ModHandler.addShapedRecipe("paper_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Paper, 2), "SSS", " m ", 'S', new ItemStack(Items.REEDS));
            ModHandler.addShapedRecipe("sugar", OreDictUnifier.get(OrePrefix.dust, Materials.Sugar, 1), "Sm ", 'S', new ItemStack(Items.REEDS));
            ItemStack paperStack = OreDictUnifier.get(OrePrefix.plate, Materials.Paper, 2);
            Object[] paperRecipeIngredients = ModHandler.finalizeShapedRecipeInput(" C ", "SSS", " C ", 'S', OreDictUnifier.get(OrePrefix.dust, Materials.Paper, 1), 'C', new ItemStack(Blocks.STONE_SLAB));
            ForgeRegistries.RECIPES.register(new CustomItemReturnShapedOreRecipeRecipe(null, paperStack,
                stack -> Block.getBlockFromItem(stack.getItem()) == Blocks.STONE_SLAB, paperRecipeIngredients)
                .setMirrored(false).setRegistryName("paper"));
        }

        if (ConfigHolder.vanillaRecipes.flintAndSteelRequireSteel) {
            ModHandler.addShapedRecipe("flint_and_steel", new ItemStack(Items.FLINT_AND_STEEL), "S ", " F", 'F', new ItemStack(Items.FLINT, 1), 'S', new UnificationEntry(OrePrefix.nugget, Materials.Steel));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:flint_and_steel"));
        }

    }

    private static void registerFacadeRecipe(Material material, int facadeAmount) {
        OreIngredient ingredient = new OreIngredient(new UnificationEntry(OrePrefix.plate, material).toString());
        ForgeRegistries.RECIPES.register(new FacadeRecipe(null, ingredient, facadeAmount).setRegistryName("facade_" + material));
    }

    private static void registerColoringRecipes(BlockColored block) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            String recipeName = String.format("%s_color_%s", block.getRegistryName().getPath(), getColorName(dyeColor));
            ModHandler.addShapedRecipe(recipeName, new ItemStack(block, 8, dyeColor.getMetadata()), "XXX", "XDX", "XXX",
                'X', new ItemStack(block, 1, GTValues.W), 'D', getOrdictColorName(dyeColor));
        }
    }
}
