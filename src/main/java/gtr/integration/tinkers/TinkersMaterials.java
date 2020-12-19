package gtr.integration.tinkers;

import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.*;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.UnificationEntry;
import gtr.common.ConfigHolder;
import net.minecraft.init.Items;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

import java.util.ArrayList;

public class TinkersMaterials {
    private static ArrayList<slimeknights.tconstruct.library.materials.Material> ingotMaterials = new ArrayList<>();
    private static ArrayList<IngotMaterial> GtIngotmaterials = new ArrayList<>();

    private static ArrayList<slimeknights.tconstruct.library.materials.Material> gemMaterials = new ArrayList<>();
    private static ArrayList<GemMaterial> GtGemmaterials = new ArrayList<>();

    public static void preInit() {
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof IngotMaterial) {
                if (mat != Materials.Iron && mat != Materials.Cobalt && mat != Materials.Copper && mat != Materials.Bronze && mat != Materials.Lead && mat != Materials.Electrum && mat != Materials.Silver && mat != Materials.Steel && mat != Materials.PigIron) {
                    if (((SolidMaterial) mat).toolDurability > 0) {
                        ingotMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(true).setCraftable(false));
                        GtIngotmaterials.add((IngotMaterial) mat);
                    } else TinkerRegistry.integrate(((IngotMaterial) mat).getMaterialFluid(), upperCase(mat));
                }
                if (((IngotMaterial) mat).blastFurnaceTemperature <= 0 && ConfigHolder.GregsConstruct.TinkersMaterialsSmelting  && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                }
            }
            if (mat instanceof GemMaterial && ((GemMaterial) mat).toolDurability > 0) {
                gemMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(false).setCraftable(true));
                GtGemmaterials.add((GemMaterial) mat);
            }
            if (mat instanceof DustMaterial && !(mat instanceof IngotMaterial) && ConfigHolder.GregsConstruct.TinkersMaterialsSmelting && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)) {
                DustMaterial dust = (DustMaterial) mat;
                if (dust.directSmelting != null) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                } else if (dust.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID) && mat != Materials.Glass && mat != Materials.Ice) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, mat).toString(), dust.getMaterialFluid(), 144);
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustSmall, mat).toString(), dust.getMaterialFluid(), 36);
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustTiny, mat).toString(), dust.getMaterialFluid(), 16);
                }
            }
        }

        if (ConfigHolder.GregsConstruct.TinkersMetalTools)
            for (int i = 0; i < ingotMaterials.size(); i++) {
                slimeknights.tconstruct.library.materials.Material mat = ingotMaterials.get(i);
                IngotMaterial GtMat = GtIngotmaterials.get(i);
                mat.addCommonItems(upperCase(GtMat));
                mat.setFluid(GtMat.getMaterialFluid());
                mat.addItemIngot(new UnificationEntry(OrePrefix.ingot, GtMat).toString());
                mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.ingot, GtMat));
                if (TinkerRegistry.getMaterial(mat.identifier) == slimeknights.tconstruct.library.materials.Material.UNKNOWN) {
                    TinkerRegistry.addMaterial(mat);
                    TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats((int) (GtMat.toolDurability * 0.8), GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel), new HandleMaterialStats((GtMat.harvestLevel - 0.5f) / 2, GtMat.toolDurability / 3), new ExtraMaterialStats(GtMat.toolDurability / 4));
                    TinkerRegistry.integrate(mat, mat.getFluid(), upperCase(GtMat));
                }
            }

        if (ConfigHolder.GregsConstruct.TinkersGemTools)
            for (int i = 0; i < gemMaterials.size(); i++) {
                slimeknights.tconstruct.library.materials.Material mat = gemMaterials.get(i);
                GemMaterial GtMat = GtGemmaterials.get(i);
                mat.addCommonItems(upperCase(GtMat));
                mat.addItemIngot(new UnificationEntry(OrePrefix.gem, GtMat).toString());
                mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.gem, GtMat));
                if (TinkerRegistry.getMaterial(mat.identifier) == slimeknights.tconstruct.library.materials.Material.UNKNOWN) {
                    TinkerRegistry.addMaterial(mat);
                    TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats(GtMat.toolDurability, GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel), new HandleMaterialStats(GtMat.harvestLevel - 0.5f, GtMat.toolDurability / 4), new ExtraMaterialStats(GtMat.toolDurability / 100));
                    TinkerRegistry.integrate(mat, upperCase(GtMat));
                }
            }

        if (ConfigHolder.GregsConstruct.TinkersMaterialAlloying) {
            TinkerRegistry.registerAlloy(Materials.Brass.getFluid(4), Materials.Copper.getFluid(3), Materials.Zinc.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.Cupronickel.getFluid(2), Materials.Copper.getFluid(1), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.RedAlloy.getFluid(1), Materials.Copper.getFluid(1), Materials.Redstone.getFluid(4));
            TinkerRegistry.registerAlloy(Materials.Brass.getFluid(4), Materials.AnnealedCopper.getFluid(3), Materials.Zinc.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.Cupronickel.getFluid(2), Materials.AnnealedCopper.getFluid(1), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.RedAlloy.getFluid(1), Materials.AnnealedCopper.getFluid(1), Materials.Redstone.getFluid(4));
            TinkerRegistry.registerAlloy(Materials.Invar.getFluid(3), Materials.Iron.getFluid(2), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.Invar.getFluid(3), Materials.WroughtIron.getFluid(2), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.BatteryAlloy.getFluid(5), Materials.Lead.getFluid(4), Materials.Antimony.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.SolderingAlloy.getFluid(10), Materials.Tin.getFluid(9), Materials.Antimony.getFluid(1));
            TinkerRegistry.registerAlloy(Materials.Magnalium.getFluid(3), Materials.Aluminium.getFluid(2), Materials.Magnesium.getFluid(1));
        }

        TinkerRegistry.registerMelting("dustGlass", Materials.Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting("dustQuartzite", Materials.Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting("plateGlass", Materials.Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, Materials.Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting("gemGlass", Materials.Glass.getMaterialFluid(), 1000);
    }

    private static String upperCase(Material mat) {
        return mat.toCamelCaseString().substring(0, 1).toUpperCase() + mat.toCamelCaseString().substring(1);
    }
}