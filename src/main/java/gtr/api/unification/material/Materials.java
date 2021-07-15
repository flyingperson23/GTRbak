package gtr.api.unification.material;

import gtr.api.GTValues;
import gtr.api.unification.Element;
import gtr.api.unification.material.type.*;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.MaterialStack;

import static com.google.common.collect.ImmutableList.of;
import static gtr.api.unification.material.MaterialIconSet.FLUID;
import static gtr.api.unification.material.type.DustMaterial.MatFlags.*;
import static gtr.api.unification.material.type.FluidMaterial.MatFlags.*;
import static gtr.api.unification.material.type.GemMaterial.MatFlags.*;
import static gtr.api.unification.material.type.IngotMaterial.MatFlags.*;
import static gtr.api.unification.material.type.Material.MatFlags.*;
import static gtr.api.unification.material.type.SolidMaterial.MatFlags.*;

@SuppressWarnings("WeakerAccess")
public class Materials {

    public static void register() {
        MarkerMaterials.register();
    }

    private static final long STD_SOLID = GENERATE_PLATE | GENERATE_ROD | GENERATE_BOLT_SCREW | GENERATE_LONG_ROD;
    private static final long STD_GEM = GENERATE_ORE | STD_SOLID | GENERATE_LENSE;
    private static final long STD_METAL = GENERATE_PLATE;
    private static final long EXT_METAL = STD_METAL | GENERATE_ROD | GENERATE_BOLT_SCREW | GENERATE_LONG_ROD;
    private static final long EXT2_METAL = EXT_METAL | GENERATE_GEAR | GENERATE_FOIL | GENERATE_FINE_WIRE;

    public static MarkerMaterial _NULL = new MarkerMaterial("_null");


    /**
     * 'Direct Elements'
     */
    public static IngotMaterial Aluminium = new IngotMaterial(1, "aluminium", 0x80C8F0, MaterialIconSet.SHINY, 2, of(), EXT2_METAL | GENERATE_SMALL_GEAR | GENERATE_ORE | GENERATE_RING | GENERATE_FRAME | GENERATE_ROTOR, Element.Al, 9.0F, 2.5F, 966, 1700);
    //public static IngotMaterial Americium = new IngotMaterial(2, "americium", 0xC8C8C8, MaterialIconSet.METALLIC, 3, of(), STD_METAL, Element.Am);
    public static IngotMaterial Antimony = new IngotMaterial(3, "antimony", 0xDCDCC8, MaterialIconSet.SHINY, 2, of(), STD_METAL | MORTAR_GRINDABLE, Element.Sb);
    public static DustMaterial Arsenic = new DustMaterial(5, "arsenic", 0xDDDDDD, MaterialIconSet.SAND, 2, of(), SMELT_INTO_FLUID, Element.As);
    public static IngotMaterial Barium = new IngotMaterial(6, "barium", 0xFFFFFF, MaterialIconSet.SHINY, 2, of(), 0, Element.Ba);
    public static IngotMaterial Beryllium = new IngotMaterial(7, "beryllium", 0x64B464, MaterialIconSet.METALLIC, 2, of(), EXT_METAL | GENERATE_ORE, Element.Be, 11.0F, 3.0F, 450);
    public static IngotMaterial Bismuth = new IngotMaterial(8, "bismuth", 0x64A0A0, MaterialIconSet.METALLIC, 1, of(), GENERATE_ORE, Element.Bi);
    public static IngotMaterial Boron = new IngotMaterial(9, "boron", 0xD2F0D2, MaterialIconSet.METALLIC, 2, of(), 0, Element.B); //IngotMaterial for the sake of NuclearCraft
    public static IngotMaterial Caesium = new IngotMaterial(10, "caesium", 0xFFFFFC, MaterialIconSet.DULL, 2, of(), 0, Element.Cs);
    public static IngotMaterial Calcium = new IngotMaterial(11, "calcium", 0xDDDDAA, MaterialIconSet.METALLIC, 2, of(), 0, Element.Ca);
    public static IngotMaterial Carbon = new IngotMaterial(12, "carbon", 0x333333, MaterialIconSet.DULL, 1, of(), 0, Element.C);
    public static IngotMaterial Cadmium = new IngotMaterial(13, "cadmium", 0x505060, MaterialIconSet.SHINY, 2, of(), STD_METAL, Element.Cd);
    public static IngotMaterial Cerium = new IngotMaterial(14, "cerium", 0xEEEEEE, MaterialIconSet.METALLIC, 2, of(), 0, Element.Ce, 1068);
    public static FluidMaterial Chlorine = new FluidMaterial(15, "chlorine", 0xEEEECC, MaterialIconSet.GAS, of(), STATE_GAS, Element.Cl);
    public static IngotMaterial Chrome = new IngotMaterial(16, "chrome", 0xFFAAAB, MaterialIconSet.SHINY, 4, of(), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR, Element.Cr, 11.0F, 3.5F, 1960, 1700);
    public static IngotMaterial Cobalt = new IngotMaterial(17, "cobalt", 0x2929BC, MaterialIconSet.METALLIC, 2, of(), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_ORE, Element.Co, 8.0F, 4.0F, 1440);
    public static IngotMaterial Copper = new IngotMaterial(18, "copper", 0xFF8000, MaterialIconSet.SHINY, 1, of(), EXT2_METAL | GENERATE_ORE | MORTAR_GRINDABLE | GENERATE_RING, Element.Cu, 5.0F, 1.2F, 120);
    public static FluidMaterial Deuterium = new FluidMaterial(19, "deuterium", 0xEEEE00, FLUID, of(), STATE_GAS, Element.D);
    public static FluidMaterial Fluorine = new FluidMaterial(23, "fluorine", 0xFFFFAA, MaterialIconSet.GAS, of(), STATE_GAS, Element.F);
    public static IngotMaterial Gallium = new IngotMaterial(25, "gallium", 0xEEEEFF, MaterialIconSet.SHINY, 2, of(), GENERATE_PLATE, Element.Ga);
    public static IngotMaterial Gold = new IngotMaterial(26, "gold", 0xFFFF00, MaterialIconSet.SHINY, 2, of(), EXT2_METAL | GENERATE_ORE | MORTAR_GRINDABLE, Element.Au, 14.0F, 3.0F, 64);
    public static FluidMaterial Hydrogen = new FluidMaterial(28, "hydrogen", 0x00FFAA, MaterialIconSet.GAS, of(), STATE_GAS, Element.H);
    public static FluidMaterial Helium = new FluidMaterial(29, "helium", 0xDDDD00, MaterialIconSet.GAS, of(), STATE_GAS | GENERATE_PLASMA | DECOMPOSITION_BY_CENTRIFUGING, Element.He);
    public static FluidMaterial Helium3 = new FluidMaterial(30, "helium3", 0xDDDD00, MaterialIconSet.GAS, of(), STATE_GAS | DECOMPOSITION_BY_CENTRIFUGING, Element.He_3);
    public static IngotMaterial Indium = new IngotMaterial(31, "indium", 0x6600BB, MaterialIconSet.METALLIC, 2, of(), 0, Element.In);
    public static IngotMaterial Iridium = new IngotMaterial(32, "iridium", 0xFFFFFF, MaterialIconSet.METALLIC, 5, of(), GENERATE_ORE | EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_FRAME, Element.Ir, 11.0F, 5.0F, 1096, 2719);
    public static IngotMaterial Iron = new IngotMaterial(33, "iron", 0xAAAAAA, MaterialIconSet.METALLIC, 2, of(), EXT2_METAL | GENERATE_ORE | MORTAR_GRINDABLE | GENERATE_RING | GENERATE_PLASMA | GENERATE_FRAME, Element.Fe, 7.0F, 2.5F, 288);
    public static IngotMaterial Lanthanum = new IngotMaterial(34, "lanthanum", 0xFFFFFF, MaterialIconSet.METALLIC, 2, of(), 0, Element.La, 1193);
    public static IngotMaterial Lead = new IngotMaterial(35, "lead", 0x8C648C, MaterialIconSet.METALLIC, 1, of(), EXT2_METAL | GENERATE_ORE | MORTAR_GRINDABLE, Element.Pb);
    public static IngotMaterial Lithium = new IngotMaterial(36, "lithium", 0xCBCBCB, MaterialIconSet.METALLIC, 2, of(), STD_METAL | GENERATE_ORE, Element.Li);
    public static IngotMaterial Magnesium = new IngotMaterial(38, "magnesium", 0xFFBBBB, MaterialIconSet.METALLIC, 2, of(), 0, Element.Mg);
    public static IngotMaterial Manganese = new IngotMaterial(39, "manganese", 0xEEEEEE, MaterialIconSet.SHINY, 2, of(), GENERATE_ORE, Element.Mn);
    public static FluidMaterial Mercury = new FluidMaterial(40, "mercury", 0xFFDDDD, FLUID, of(), SMELT_INTO_FLUID, Element.Hg);
    public static IngotMaterial Molybdenum = new IngotMaterial(41, "molybdenum", 0xAAAADD, MaterialIconSet.METALLIC, 2, of(), GENERATE_DENSE | GENERATE_ORE, Element.Mo);
    public static IngotMaterial Neodymium = new IngotMaterial(42, "neodymium", 0x777777, MaterialIconSet.METALLIC, 2, of(), STD_METAL | GENERATE_ROD | GENERATE_ORE, Element.Nd, 1297);
    public static IngotMaterial Darmstadtium = new IngotMaterial(43, "darmstadtium", 0xAAAAAA, MaterialIconSet.METALLIC, 6, of(), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR | GENERATE_FRAME, Element.Ds, 24.0F, 8.0F, 155360);
    public static IngotMaterial Nickel = new IngotMaterial(44, "nickel", 0xAAAAFF, MaterialIconSet.METALLIC, 2, of(), STD_METAL | GENERATE_ORE | MORTAR_GRINDABLE | GENERATE_PLASMA, Element.Ni);
    public static IngotMaterial Niobium = new IngotMaterial(45, "niobium", 0x9486AA, MaterialIconSet.METALLIC, 2, of(), STD_METAL | GENERATE_ORE, Element.Nb, 2750);
    public static FluidMaterial Nitrogen = new FluidMaterial(46, "nitrogen", 0x7090AF, FLUID, of(), STATE_GAS | GENERATE_PLASMA, Element.N);
    public static IngotMaterial Osmium = new IngotMaterial(47, "osmium", 0x5050FF, MaterialIconSet.METALLIC, 4, of(), GENERATE_ORE | EXT2_METAL | GENERATE_RING | GENERATE_ROTOR, Element.Os, 16.0F, 7.0F, 2459, 3306);
    public static FluidMaterial Oxygen = new FluidMaterial(48, "oxygen", 0x90AAEE, FLUID, of(), STATE_GAS | GENERATE_PLASMA, Element.O);
    public static IngotMaterial Palladium = new IngotMaterial(49, "palladium", 0xCED0DD, MaterialIconSet.METALLIC, 4, of(), EXT2_METAL | GENERATE_ORE, Element.Pd, 8.0F, 6.0F, 480, 985);
    public static DustMaterial Phosphorus = new DustMaterial(50, "phosphorus", 0xC8C800, MaterialIconSet.SAND, 2, of(), 0, Element.P);
    public static IngotMaterial Platinum = new IngotMaterial(51, "platinum", 0xFFFF99, MaterialIconSet.SHINY, 4, of(), EXT2_METAL | GENERATE_ORE, Element.Pt, 12.0F, 6.0F, 480);
    public static IngotMaterial Plutonium = new IngotMaterial(52, "plutonium", 0xF03232, MaterialIconSet.METALLIC, 3, of(), 0, Element.Pu, 8.0F, 5.0F, 1048);
    public static IngotMaterial Plutonium239 = new IngotMaterial(53, "plutonium239", 0xFA4646, MaterialIconSet.SHINY, 3, of(), EXT_METAL | GENERATE_ORE, Element.Pu_239, 8.0F, 5.0F, 1048);
    public static IngotMaterial Potassium = new IngotMaterial(54, "potassium", 0xCECECE, MaterialIconSet.METALLIC, 1, of(), 0, Element.K);
    //public static IngotMaterial Praseodymium = new IngotMaterial(55, "praseodymium", 0xCECECE, MaterialIconSet.METALLIC, 2, of(), 0, Element.Pr, 1208);
    public static FluidMaterial Radon = new FluidMaterial(57, "radon", 0xFF00FF, FLUID, of(), STATE_GAS | DECOMPOSITION_BY_CENTRIFUGING, Element.Rn);
    public static IngotMaterial Silicon = new IngotMaterial(61, "silicon", 0x3C3C50, MaterialIconSet.METALLIC, 2, of(), STD_METAL | GENERATE_FOIL, Element.Si, 1687);
    public static IngotMaterial Silver = new IngotMaterial(62, "silver", 0xDCDCFF, MaterialIconSet.SHINY, 2, of(), EXT2_METAL | GENERATE_ORE | MORTAR_GRINDABLE, Element.Ag, 10.0F, 3.0F, 64);
    public static IngotMaterial Sodium = new IngotMaterial(63, "sodium", 0x000096, MaterialIconSet.METALLIC, 2, of(), 0, Element.Na);
    public static DustMaterial Sulfur = new DustMaterial(65, "sulfur", 0xC8C800, MaterialIconSet.SAND, 2, of(), NO_SMASHING | NO_SMELTING | FLAMMABLE |  GENERATE_ORE, Element.S);
    public static IngotMaterial Tantalum = new IngotMaterial(66, "tantalum", 0xFFFFFF, MaterialIconSet.METALLIC, 2, of(), 0, Element.Ta);
    public static IngotMaterial Thorium = new IngotMaterial(69, "thorium", 0x001E00, MaterialIconSet.SHINY, 2, of(), STD_METAL | GENERATE_ORE, Element.Th, 6.0F, 4.0F, 788);
    public static IngotMaterial Tin = new IngotMaterial(71, "tin", 0xDCDCDC, MaterialIconSet.METALLIC, 1, of(), EXT2_METAL | MORTAR_GRINDABLE | GENERATE_RING | GENERATE_ROTOR | GENERATE_ORE, Element.Sn);
    public static IngotMaterial Titanium = new IngotMaterial(72, "titanium", 0xDCA0F0, MaterialIconSet.METALLIC, 3, of(), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR | GENERATE_FRAME, Element.Ti, 10.0F, 4.0F, 1600, 1941);
    public static FluidMaterial Tritium = new FluidMaterial(73, "tritium", 0xFF0000, MaterialIconSet.METALLIC, of(), STATE_GAS, Element.T);
    public static IngotMaterial Tungsten = new IngotMaterial(74, "tungsten", 0x323232, MaterialIconSet.METALLIC, 3, of(), EXT2_METAL | GENERATE_ORE, Element.W, 7.0F, 4.0F, 1300);
    public static IngotMaterial Uranium = new IngotMaterial(75, "uranium", 0x32F032, MaterialIconSet.METALLIC, 3, of(), GENERATE_ORE, Element.U, 8.0F, 4.0F, 1024);
    public static IngotMaterial Uranium235 = new IngotMaterial(76, "uranium235", 0x46FA46, MaterialIconSet.SHINY, 3, of(), 0, Element.U_235, 8.0F, 4.0F, 1024);
    public static IngotMaterial Vanadium = new IngotMaterial(77, "vanadium", 0x323232, MaterialIconSet.METALLIC, 2, of(), STD_METAL, Element.V, 2183);
    public static IngotMaterial Yttrium = new IngotMaterial(79, "yttrium", 0xDCFADC, MaterialIconSet.METALLIC, 2, of(), STD_METAL, Element.Y, 1799);
    public static IngotMaterial Zinc = new IngotMaterial(80, "zinc", 0xFAF0F0, MaterialIconSet.METALLIC, 1, of(), STD_METAL | GENERATE_ORE | MORTAR_GRINDABLE | GENERATE_FOIL, Element.Zn);
    public static IngotMaterial Zirconium = new IngotMaterial(81, "zirconium", 0xB76A17, MaterialIconSet.SHINY, 3, of(), GENERATE_PLATE, Element.Zr, 10.0F, 4.5F, 680, 1855);

    public static IngotMaterial Technetium = new IngotMaterial(82, "technetium", 0x8a8a8a, MaterialIconSet.DULL, 3, of(), EXT2_METAL, Element.Tc, 12.0F, 6.0F, 1536);
    public static FluidMaterial Oganesson = new FluidMaterial(83, "oganesson", 0xa80aad, FLUID, of(), STATE_GAS, Element.Og);

    /**
     * First Degree Compounds : Fits up to 100-150ID
     */
    public static DustMaterial YellowLimonite = new DustMaterial(199, "yellow_limonite", 0xC8C800, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2)), GENERATE_ORE | INDUCTION_SMELTING_LOW_OUTPUT);

    public static DustMaterial BrownLimonite = new DustMaterial(96, "brown_limonite", 0xC86400, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2)), GENERATE_ORE);
    public static FluidMaterial Methane = new FluidMaterial(100, "methane", 0xFFFFFF, FLUID, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4)), 0);
    public static FluidMaterial CarbonDioxide = new FluidMaterial(101, "carbon_dioxide", 0xA9D0F5, FLUID, of(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 2)), 0);
    public static FluidMaterial CarbonMonoxide = new FluidMaterial(102, "carbon_monoxide", 0xA9D0F5, FLUID, of(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 1)), 0);
    public static FluidMaterial Air = new FluidMaterial(104, "air", 0xA9D0F5, FLUID, of(new MaterialStack(Nitrogen, 40), new MaterialStack(Oxygen, 11)), STATE_GAS | DISABLE_DECOMPOSITION);
    public static FluidMaterial LiquidAir = new FluidMaterial(105, "liquid_air", 0xA9D0F5, FLUID, of(new MaterialStack(Nitrogen, 40), new MaterialStack(Oxygen, 11)), STATE_GAS | DISABLE_DECOMPOSITION);
    public static DustMaterial DarkAsh = new DustMaterial(107, "dark_ash", 0x323232, MaterialIconSet.SAND, 1, of(new MaterialStack(Carbon, 1)), DISABLE_DECOMPOSITION);
    public static FluidMaterial Glyceryl = new FluidMaterial(106, "glyceryl", 0x009696, FLUID, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Nitrogen, 3), new MaterialStack(Oxygen, 9)), FLAMMABLE | EXPLOSIVE | NO_SMELTING | NO_SMASHING);
    public static FluidMaterial DistilledWater = new FluidMaterial(109, "distilled_water", 0x0000FF, FLUID, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), NO_RECYCLING | DISABLE_DECOMPOSITION);
    public static FluidMaterial Pahoehoe = new FluidMaterial(110, "pahoehoe", 0x0000FF, FLUID, of(), NO_RECYCLING | DISABLE_DECOMPOSITION);
    public static FluidMaterial TitaniumTetrachloride = new FluidMaterial(108, "titanium_tetrachloride", 0xD40D5C, FLUID, of(new MaterialStack(Titanium, 1), new MaterialStack(Carbon, 2), new MaterialStack(Chlorine, 2)), DISABLE_DECOMPOSITION);
    public static DustMaterial MagnesiumChloride = new DustMaterial(111, "magnesium_chloride", 0xD40D5C, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Magnesium, 1), new MaterialStack(Chlorine, 2)), 0);
    public static FluidMaterial NitrogenDioxide = new FluidMaterial(113, "nitrogen_dioxide", 0x64AFFF, FLUID, of(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 2)), 0);
    public static DustMaterial Phosphate = new DustMaterial(115, "phosphate", 0xFFFF00, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Phosphorus, 1), new MaterialStack(Oxygen, 4)), GENERATE_ORE | NO_SMASHING | NO_SMELTING | FLAMMABLE | EXPLOSIVE);
    public static IngotMaterial Polyethylene = new IngotMaterial(116, "polyethylene", 0xC8C8C8, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2)), GENERATE_PLATE | FLAMMABLE | NO_SMASHING | SMELT_INTO_FLUID);
    public static IngotMaterial Epoxid = new IngotMaterial(117, "epoxid", 0xC88C14, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 1)), EXT2_METAL);
    public static DustMaterial Silicone = new DustMaterial(118, "silicone", 0xDCDCDC, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 1)), GENERATE_PLATE | FLAMMABLE | NO_SMASHING | SMELT_INTO_FLUID);
    public static IngotMaterial Polycaprolactam = new IngotMaterial(119, "polycaprolactam", 0x323232, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 11), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 1)), GENERATE_PLATE);
    public static IngotMaterial Polytetrafluoroethylene = new IngotMaterial(120, "polytetrafluoroethylene", 0x646464, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 2), new MaterialStack(Fluorine, 4)), GENERATE_PLATE | SMELT_INTO_FLUID | NO_WORKING, 5.0F, 2.0F, 512);
    public static IngotMaterial Rubber = new IngotMaterial(121, "rubber", 0x151515, MaterialIconSet.DULL, 0, of(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)), GENERATE_PLATE | GENERATE_GEAR | GENERATE_RING | FLAMMABLE | NO_SMASHING | GENERATE_RING | NO_WORKING, 4.0F, 2.0F, 256);
    public static DustMaterial RawRubber = new DustMaterial(122, "raw_rubber", 0xCCC789, MaterialIconSet.SAND, 0, of(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)), 0);
    public static FluidMaterial SodiumPersulfate = new FluidMaterial(123, "sodium_persulfate", 0xFFFFFF, FLUID, of(new MaterialStack(Sodium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)), 0);
    public static FluidMaterial HydrogenSulfide = new FluidMaterial(125, "hydrogen_sulfide", 0xFFFFFF, FLUID, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1)), 0);
    public static FluidMaterial Steam = new FluidMaterial(126, "steam", 0xFFFFFF, MaterialIconSet.GAS, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), NO_RECYCLING | GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial Epichlorhydrin = new FluidMaterial(127, "epichlorhydrin", 0xFFFFFF, FLUID, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Chlorine, 1), new MaterialStack(Oxygen, 1)), 0);

    /**
     * Vanilla Materials : Fits 151-199ID
     */
    //Sand = SiliconDioxide
    public static DustMaterial SiliconDioxide = new DustMaterial(151, "silicon_dioxide", 0xC8C8C8, MaterialIconSet.QUARTZ, 1, of(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2)), NO_SMASHING | NO_SMELTING | CRYSTALLISABLE | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static DustMaterial Stone = new DustMaterial(152, "stone", 0xCDCDCD, MaterialIconSet.ROUGH, 1, of(), MORTAR_GRINDABLE | GENERATE_GEAR | GENERATE_PLATE | NO_SMASHING | NO_RECYCLING);
    public static DustMaterial Obsidian = new DustMaterial(153, "obsidian", 0x503264, MaterialIconSet.DULL, 3, of(new MaterialStack(Magnesium, 1), new MaterialStack(Iron, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 8)), NO_SMASHING | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static FluidMaterial Lava = new FluidMaterial(155, "lava", 0xFF4000, FLUID, of(), 0);
    public static DustMaterial Glowstone = new DustMaterial(156, "glowstone", 0xFFFF00, MaterialIconSet.SHINY, 1, of(new MaterialStack(Phosphorus, 2)), NO_SMASHING | SMELT_INTO_FLUID | GENERATE_PLATE | EXCLUDE_PLATE_COMPRESSOR_RECIPE);
    public static DustMaterial Ice = new DustMaterial(157, "ice", 0xC8C8FF, MaterialIconSet.ROUGH, 0, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), NO_SMASHING | NO_RECYCLING | SMELT_INTO_FLUID | EXCLUDE_BLOCK_CRAFTING_RECIPES | DISABLE_DECOMPOSITION);
    public static GemMaterial NetherStar = new GemMaterial(158, "nether_star", 0xFFFFFF, MaterialIconSet.NETHERSTAR, 4, of(), GENERATE_PLATE | GENERATE_ROD | GENERATE_LENSE | NO_SMASHING | NO_SMELTING, null, 7.0F, 5.0F, 5120);
    public static DustMaterial Endstone = new DustMaterial(159, "endstone", 0xFFFFFF, MaterialIconSet.DULL, 1, of(), NO_SMASHING | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static DustMaterial Netherrack = new DustMaterial(160, "netherrack", 0xC80000, MaterialIconSet.ROUGH, 1, of(), NO_SMASHING | FLAMMABLE | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static RoughSolidMaterial Wood = new RoughSolidMaterial(161, "wood", 0x896727, MaterialIconSet.WOOD, 0, of(), GENERATE_PLATE | GENERATE_ROD | FLAMMABLE | NO_SMELTING | GENERATE_GEAR, () -> OrePrefix.plank);
    public static DustMaterial Redstone = new DustMaterial(162, "redstone", 0xC80000, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Boron, 8), new MaterialStack(Phosphorus, 11)), GENERATE_PLATE | GENERATE_ORE | NO_SMASHING | SMELT_INTO_FLUID);
    public static DustMaterial Blaze = new DustMaterial(163, "blaze", 0xFFC800, MaterialIconSet.SAND, 1, of(new MaterialStack(DarkAsh, 1), new MaterialStack(Sulfur, 1)), NO_SMELTING | MORTAR_GRINDABLE | SMELT_INTO_FLUID | BURNING);
    public static GemMaterial EnderPearl = new GemMaterial(164, "ender_pearl", 0x6CDCC8, MaterialIconSet.GEM_VERTICAL, 1, of(new MaterialStack(Beryllium, 1), new MaterialStack(Potassium, 4), new MaterialStack(Nitrogen, 5)), GENERATE_PLATE | GENERATE_LENSE | NO_SMASHING | NO_SMELTING | EXCLUDE_BLOCK_CRAFTING_RECIPES, 6.0F, 3.0F, 120);
    public static GemMaterial EnderEye = new GemMaterial(165, "ender_eye", 0x66FF66, MaterialIconSet.GEM_VERTICAL, 1, of(new MaterialStack(EnderPearl, 1), new MaterialStack(Blaze, 1)), GENERATE_PLATE | GENERATE_LENSE | NO_SMASHING | NO_SMELTING | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static RoughSolidMaterial Flint = new RoughSolidMaterial(166, "flint", 0x002040, MaterialIconSet.FLINT, 1, of(new MaterialStack(SiliconDioxide, 1)), NO_SMASHING | MORTAR_GRINDABLE, () -> OrePrefix.gem);
    public static DustMaterial Glass = new DustMaterial(167, "glass", 0xFFFFFF, MaterialIconSet.GLASS, 0, of(new MaterialStack(SiliconDioxide, 1)), GENERATE_PLATE | GENERATE_LENSE | NO_SMASHING | NO_RECYCLING | SMELT_INTO_FLUID | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static DustMaterial Gunpowder = new DustMaterial(168, "gunpowder", 0x808080, MaterialIconSet.SAND, 0, of(), FLAMMABLE | EXPLOSIVE | NO_SMELTING | NO_SMASHING);
    public static RoughSolidMaterial Paper = new RoughSolidMaterial(169, "paper", 0xFFFFFF, MaterialIconSet.PAPER, 0, of(), GENERATE_PLATE | FLAMMABLE | NO_SMELTING | NO_SMASHING | MORTAR_GRINDABLE | GENERATE_RING | EXCLUDE_PLATE_COMPRESSOR_RECIPE, () -> OrePrefix.plate);
    public static FluidMaterial Water = new FluidMaterial(170, "water", 0x0000FF, FLUID, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), NO_RECYCLING | DISABLE_DECOMPOSITION);
    public static DustMaterial Bone = new DustMaterial(172, "bone", 0xFFFFFF, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Calcium, 1)), EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static DustMaterial Clay = new DustMaterial(173, "clay", 0xC8C8DC, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Sodium, 2), new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 2), new MaterialStack(Water, 6)), MORTAR_GRINDABLE | EXCLUDE_BLOCK_CRAFTING_RECIPES);
    public static DustMaterial Brick = new DustMaterial(174, "brick", 0xB75A40, MaterialIconSet.FINE, 1, of(new MaterialStack(Clay, 1)), EXCLUDE_BLOCK_CRAFTING_RECIPES | DECOMPOSITION_BY_CENTRIFUGING);

    /**
     * Ore Materials 200ID - 300ID
     */
    //Titanium
    public static DustMaterial Rutile = new DustMaterial(200, "rutile", 0xD40D5C, MaterialIconSet.DULL, 2, of(new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 2)), GENERATE_ORE | DISABLE_DECOMPOSITION);
    public static DustMaterial Ilmenite = new DustMaterial(201, "ilmenite", 0x463732, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Iron, 1), new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE | DISABLE_DECOMPOSITION);

    //Aluminium
    public static DustMaterial Bauxite = new DustMaterial(202, "bauxite", 0xC86400, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Rutile, 2), new MaterialStack(Aluminium, 16), new MaterialStack(Hydrogen, 10), new MaterialStack(Oxygen, 11)), GENERATE_ORE);
    public static DustMaterial Pyrope = new DustMaterial(236, "pyrope", 0x783264, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Aluminium, 2), new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)), GENERATE_ORE);

    //Iron
    public static DustMaterial BandedIron = new DustMaterial(204, "banded_iron", 0x915A5A, MaterialIconSet.DULL, 1, of(new MaterialStack(Iron, 2), new MaterialStack(Oxygen, 3)), GENERATE_ORE);
    public static DustMaterial Siderite = new DustMaterial(205, "siderite", 15657130, MaterialIconSet.SAND, 1, of(new MaterialStack(Iron, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE);
    public static DustMaterial Geothite = new DustMaterial(206, "geothite", 15657130, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Silicon, 1), new MaterialStack(Iron, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE);
    public static DustMaterial Hematite = new DustMaterial(207, "hematite", 9127187, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Iron, 2), new MaterialStack(Oxygen, 3)), GENERATE_ORE);
    public static DustMaterial Pyrite = new DustMaterial(208, "pyrite", 0x967828, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2)), GENERATE_ORE | INDUCTION_SMELTING_LOW_OUTPUT);
    public static DustMaterial Magnetite = new DustMaterial(209, "magnetite", 0x1E1E1E, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 3), new MaterialStack(Oxygen, 4)), GENERATE_ORE);
    public static DustMaterial VanadiumMagnetite = new DustMaterial(210, "vanadium_magnetite", 0x23233C, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Magnetite, 1), new MaterialStack(Vanadium, 1)), GENERATE_ORE);

    //Tin
    public static DustMaterial Cassiterite = new DustMaterial(215, "cassiterite", 0xDCDCDC, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Tin, 1), new MaterialStack(Oxygen, 2)), GENERATE_ORE);

    //Others
    public static DustMaterial Bentonite = new DustMaterial(203, "bentonite", 0xF5D7D2, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Sodium, 1), new MaterialStack(Magnesium, 6), new MaterialStack(Silicon, 12), new MaterialStack(Hydrogen, 4), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 36)), GENERATE_ORE);
    public static DustMaterial Calcite = new DustMaterial(214, "calcite", 0xFAE6DC, MaterialIconSet.DULL, 1, of(new MaterialStack(Calcium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE);
    public static DustMaterial Sphalerite = new DustMaterial(217, "sphalerite", 0xFFFFFF, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Zinc, 1), new MaterialStack(Sulfur, 1)), GENERATE_ORE | INDUCTION_SMELTING_LOW_OUTPUT);
    public static DustMaterial Stibnite = new DustMaterial(218, "stibnite", 0x464646, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Antimony, 2), new MaterialStack(Sulfur, 3)), GENERATE_ORE);
    public static DustMaterial Uraninite = new DustMaterial(219, "uraninite", 0x232323, MaterialIconSet.ROUGH, 3, of(new MaterialStack(Uranium, 1), new MaterialStack(Oxygen, 2)), GENERATE_ORE | DISABLE_DECOMPOSITION);
    public static DustMaterial Chromite = new DustMaterial(220, "chromite", 0x23140F, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Iron, 1), new MaterialStack(Chrome, 2), new MaterialStack(Oxygen, 4)), GENERATE_ORE);
    public static GemMaterial Cinnabar = new GemMaterial(103, "cinnabar", 0x960000, MaterialIconSet.EMERALD, 1, of(new MaterialStack(Mercury, 1), new MaterialStack(Sulfur, 1)), GENERATE_ORE | CRYSTALLISABLE);
    public static DustMaterial Biotite = new DustMaterial(221, "biotite", 0x141E14, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 3), new MaterialStack(Aluminium, 3), new MaterialStack(Fluorine, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 10)), GENERATE_ORE);
    public static DustMaterial Realgar = new DustMaterial(222, "realgar", 0x8C6464, MaterialIconSet.DULL, 2, of(new MaterialStack(Arsenic, 4), new MaterialStack(Sulfur, 4)), GENERATE_ORE);
    public static DustMaterial Pentlandite = new DustMaterial(223, "pentlandite", 0xA59605, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Nickel, 9), new MaterialStack(Sulfur, 8)), GENERATE_ORE | INDUCTION_SMELTING_LOW_OUTPUT);
    public static DustMaterial Garnierite = new DustMaterial(224, "garnierite", 0x32C846, MaterialIconSet.ROUGH, 3, of(new MaterialStack(Nickel, 1), new MaterialStack(Oxygen, 1)), GENERATE_ORE);
    public static DustMaterial Spodumene = new DustMaterial(225, "spodumene", 0xBEAAAA, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 6)), GENERATE_ORE);
    public static DustMaterial Lepidolite = new DustMaterial(226, "lepidolite", 0xF0328C, MaterialIconSet.FINE, 2, of(new MaterialStack(Potassium, 1), new MaterialStack(Lithium, 3), new MaterialStack(Aluminium, 4), new MaterialStack(Fluorine, 2), new MaterialStack(Oxygen, 10)), GENERATE_ORE);
    public static DustMaterial Pitchblende = new DustMaterial(227, "pitchblende", 0xC8D200, MaterialIconSet.ROUGH, 3, of(new MaterialStack(Uraninite, 3), new MaterialStack(Thorium, 1), new MaterialStack(Lead, 1)), GENERATE_ORE);
    public static DustMaterial Tungstate = new DustMaterial(228, "tungstate", 0x373223, MaterialIconSet.DULL, 3, of(new MaterialStack(Tungsten, 1), new MaterialStack(Lithium, 2), new MaterialStack(Oxygen, 4)), GENERATE_ORE | DECOMPOSITION_REQUIRES_HYDROGEN, null);
    public static DustMaterial Salt = new DustMaterial(229, "salt", 0xFFFFFF, MaterialIconSet.SAND, 1, of(new MaterialStack(Sodium, 1), new MaterialStack(Chlorine, 1)), GENERATE_ORE | NO_SMASHING);
    public static DustMaterial Saltpeter = new DustMaterial(230, "saltpeter", 0xE6E6E6, MaterialIconSet.FINE, 1, of(new MaterialStack(Potassium, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE | NO_SMASHING |NO_SMELTING |FLAMMABLE);
    public static DustMaterial Scheelite = new DustMaterial(231, "scheelite", 0xC88C14, MaterialIconSet.DULL, 3, of(new MaterialStack(Tungsten, 1), new MaterialStack(Calcium, 2), new MaterialStack(Oxygen, 4)), GENERATE_ORE | DECOMPOSITION_REQUIRES_HYDROGEN);
    public static DustMaterial Galena = new DustMaterial(232, "galena", 0x643C64, MaterialIconSet.ROUGH, 3, of(new MaterialStack(Lead, 3), new MaterialStack(Silver, 3), new MaterialStack(Sulfur, 2)), GENERATE_ORE | NO_SMELTING);
    public static DustMaterial Talc = new DustMaterial(233, "talc", 0x5AB45A, MaterialIconSet.FINE, 2, of(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12)), GENERATE_ORE);
    public static DustMaterial Soapstone = new DustMaterial(234, "soapstone", 0x5F915F, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12)), GENERATE_ORE);
    public static DustMaterial Pyrolusite = new DustMaterial(235, "pyrolusite", 0x9696AA, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Manganese, 1), new MaterialStack(Oxygen, 2)), GENERATE_ORE);
    public static DustMaterial Wulfenite = new DustMaterial(239, "wulfenite", 0xFF8000, MaterialIconSet.DULL, 3, of(new MaterialStack(Lead, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4)), GENERATE_ORE);
    public static DustMaterial Magnesite = new DustMaterial(237, "magnesite", 0xFAFAB4, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Magnesium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE);
    public static DustMaterial Molybdenite = new DustMaterial(238, "molybdenite", 0x191919, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Molybdenum, 1), new MaterialStack(Sulfur, 2)), GENERATE_ORE);
    public static DustMaterial Cobaltite = new DustMaterial(240, "cobaltite", 0x5050FA, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Cobalt, 1), new MaterialStack(Arsenic, 1), new MaterialStack(Sulfur, 1)), GENERATE_ORE);
    public static DustMaterial Cooperite = new DustMaterial(241, "cooperite", 0xFFFFC8, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Platinum, 3), new MaterialStack(Nickel, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Palladium, 1)), GENERATE_ORE);
    public static DustMaterial Powellite = new DustMaterial(242, "powellite", 0xFFFF00, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Calcium, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4)), GENERATE_ORE);
    public static DustMaterial RockSalt = new DustMaterial(243, "rock_salt", 0xF0C8C8, MaterialIconSet.FINE, 1, of(new MaterialStack(Potassium, 1), new MaterialStack(Chlorine, 1)), GENERATE_ORE | NO_SMASHING);
    public static DustMaterial Grossular = new DustMaterial(244, "grossular", 0xC86400, MaterialIconSet.GEM_VERTICAL, 1, of(new MaterialStack(Calcium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)), GENERATE_ORE);
    public static DustMaterial Graphite = new DustMaterial(245, "graphite", 0x808080, MaterialIconSet.DULL, 2, of(), GENERATE_ORE | NO_SMELTING | FLAMMABLE);
    public static DustMaterial Borax = new DustMaterial(252, "borax", 0xE6E6E6, MaterialIconSet.SAND, 1, of(new MaterialStack(Sodium, 2), new MaterialStack(Boron, 4), new MaterialStack(Water, 10), new MaterialStack(Oxygen, 7)), GENERATE_ORE);
    public static DustMaterial Witherite = new DustMaterial(253, "witherite", 0xF5F5F5, MaterialIconSet.SAND, 1, of(new MaterialStack(Barium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE);

    //Copper
    public static DustMaterial Cuprite = new DustMaterial(247, "cuprite", 0x770000, MaterialIconSet.RUBY, 2, of(new MaterialStack(Copper, 2), new MaterialStack(Oxygen, 1)), GENERATE_ORE);
    public static DustMaterial Bornite = new DustMaterial(248, "bornite", 0xC11800, MaterialIconSet.DULL, 1, of(new MaterialStack(Copper, 5), new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 4)), GENERATE_ORE);
    public static DustMaterial Chalcocite = new DustMaterial(249, "chalcocite", 0x353535, MaterialIconSet.GEM_VERTICAL, 2, of(new MaterialStack(Copper, 2), new MaterialStack(Sulfur, 1)), GENERATE_ORE);
    public static DustMaterial Tennantite = new DustMaterial(213, "tennantite", 0x909090, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Copper, 12), new MaterialStack(Arsenic, 4), new MaterialStack(Sulfur, 13)), GENERATE_ORE);
    public static DustMaterial Chalcopyrite = new DustMaterial(211, "chalcopyrite", 0xA07828, MaterialIconSet.DULL, 1, of(new MaterialStack(Copper, 1), new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2)), GENERATE_ORE | INDUCTION_SMELTING_LOW_OUTPUT);
    public static DustMaterial Tetrahedrite = new DustMaterial(251, "tetrahedrite", 0xC82000, MaterialIconSet.ROUGH, 2, of(new MaterialStack(Copper, 3), new MaterialStack(Antimony, 1), new MaterialStack(Sulfur, 3), new MaterialStack(Iron, 1)), GENERATE_ORE | INDUCTION_SMELTING_LOW_OUTPUT);

    /**
     * Fuels/Oils : 301-350ID
     */
    public static FluidMaterial Oil = new FluidMaterial(301, "oil", 0x666666, FLUID, of(), 0);
    public static DustMaterial Oilsands = new DustMaterial(302, "oilsands", 0x0A0A0A, MaterialIconSet.SAND, 1, of(new MaterialStack(Oil, 1L)), GENERATE_ORE);
    public static FluidMaterial OilHeavy = new FluidMaterial(303, "oil_heavy", 0x666666, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial OilMedium = new FluidMaterial(305, "oil_medium", 0x666666, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial OilLight = new FluidMaterial(304, "oil_light", 0x666666, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial NaturalGas = new FluidMaterial(306, "natural_gas", 0xFFFFFF, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SulfuricGas = new FluidMaterial(307, "sulfuric_gas", 0xFFFFFF, FLUID, of(), STATE_GAS | GENERATE_FLUID_BLOCK);
    public static FluidMaterial Gas = new FluidMaterial(308, "gas", 0xFFFFFF, FLUID, of(), STATE_GAS | GENERATE_FLUID_BLOCK);
    public static FluidMaterial SulfuricNaphtha = new FluidMaterial(309, "sulfuric_naphtha", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial BioFuel = new FluidMaterial(310, "bio_fuel", 0xFF8000, FLUID, of(), 0);
    public static FluidMaterial Biomass = new FluidMaterial(312, "biomass", 0x00FF00, FLUID, of(), 0);
    public static FluidMaterial Creosote = new FluidMaterial(313, "creosote", 0x804000, FLUID, of(), 0);
    public static FluidMaterial Ethanol = new FluidMaterial(314, "ethanol", 0xFF8000, FLUID, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1)), DISABLE_DECOMPOSITION);
    public static FluidMaterial Fuel = new FluidMaterial(315, "fuel", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial SulfuricLightFuel = new FluidMaterial(311, "sulfuric_light_fuel", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial SulfuricHeavyFuel = new FluidMaterial(316, "sulfuric_heavy_fuel", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial Naphtha = new FluidMaterial(317, "naphtha", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial LightFuel = new FluidMaterial(318, "light_fuel", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial HeavyFuel = new FluidMaterial(319, "heavy_fuel", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial LPG = new FluidMaterial(320, "lpg", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial Toluene = new FluidMaterial(350, "toluene", 0xFFFFFF, FLUID, of(new MaterialStack(Carbon, 7), new MaterialStack(Hydrogen, 8)), DISABLE_DECOMPOSITION);
    public static FluidMaterial NitroFuel = new FluidMaterial(322, "nitro_fuel", 0xC8FF00, FLUID, of(new MaterialStack(Glyceryl, 1), new MaterialStack(Fuel, 4)), FLAMMABLE | EXPLOSIVE | NO_SMELTING | NO_SMASHING);
    public static FluidMaterial SeedOil = new FluidMaterial(323, "seed_oil", 0xC4FF00, FLUID, of(), 0);
    public static FluidMaterial Propane = new FluidMaterial(326, "propane", 12890952, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 8)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Propene = new FluidMaterial(327, "propene", 12954956, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Ethane = new FluidMaterial(328, "ethane", 10329540, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Butene = new FluidMaterial(329, "butene", 10700561, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 8)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Butane = new FluidMaterial(330, "butane", 9385508, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 10)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);

    /**
     * Random Dusts, good for separation : 351-400ID
     */
    public static DustMaterial RareEarth = new DustMaterial(351, "rare_earth", 0x808064, MaterialIconSet.ROUGH, 0, of(), 0);
    public static DustMaterial Phosphor = new DustMaterial(352, "phosphor", 0xFFFF00, MaterialIconSet.FLINT, 2, of(new MaterialStack(Calcium, 3), new MaterialStack(Phosphate, 2)), GENERATE_ORE | NO_SMASHING | NO_SMELTING | FLAMMABLE | EXPLOSIVE);
    public static DustMaterial Barite = new DustMaterial(354, "barite", 0xE6EBFF, MaterialIconSet.DULL, 2, of(new MaterialStack(Barium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)), GENERATE_ORE);
    public static DustMaterial Ash = new DustMaterial(355, "ash", 0x969696, MaterialIconSet.SAND, 1, of(new MaterialStack(Carbon, 1)), DISABLE_DECOMPOSITION | GENERATE_ORE);
    public static DustMaterial Massicot = new DustMaterial(356, "massicot", 8943149, MaterialIconSet.SAND, 1, of(new MaterialStack(Lead, 1), new MaterialStack(Oxygen, 1)), DECOMPOSITION_BY_ELECTROLYZING);
    public static DustMaterial Niter = new DustMaterial(363, "niter", 0xFFC8C8, MaterialIconSet.FLINT, 0, of(new MaterialStack(Saltpeter, 1)), NO_SMASHING | NO_SMELTING);
    public static DustMaterial Potash = new DustMaterial(361, "potash", 5057059, MaterialIconSet.SAND, 1, of(new MaterialStack(Potassium, 2), new MaterialStack(Oxygen, 1)), DECOMPOSITION_BY_ELECTROLYZING);
    public static DustMaterial SodaAsh = new DustMaterial(362, "soda_ash", 7697800, MaterialIconSet.SAND, 1, of(new MaterialStack(Sodium, 2), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)), DECOMPOSITION_BY_ELECTROLYZING);
    public static DustMaterial PlatinumGroupSludge = new DustMaterial(364, "platinum_group_sludge", 4864, MaterialIconSet.SAND, 1, of(), DISABLE_DECOMPOSITION);
    // public static DustMaterial FerriteMixture = new DustMaterial(368, "ferrite_mixture", 9803157, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Nickel, 1), new MaterialStack(Zinc, 1), new MaterialStack(Iron, 4)), DISABLE_DECOMPOSITION);
    public static DustMaterial Quicklime = new DustMaterial(369, "quicklime", 8421504, MaterialIconSet.SAND, 1, of(new MaterialStack(Calcium, 1), new MaterialStack(Oxygen, 1)), 0);
    public static DustMaterial Urea = new DustMaterial(370, "urea", 15657130, MaterialIconSet.SAND, 1, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4), new MaterialStack(Nitrogen, 2), new MaterialStack(Oxygen, 1)), SMELT_INTO_FLUID | DISABLE_DECOMPOSITION);
    public static DustMaterial SodiumCyanate = new DustMaterial(371, "sodium_cyanate", 14800746, MaterialIconSet.SAND, 1, of(new MaterialStack(Sodium, 1), new MaterialStack(Oxygen, 1), new MaterialStack(Carbon, 1), new MaterialStack(Nitrogen, 1)), DISABLE_DECOMPOSITION);
    public static DustMaterial SodiumHydroxide = new DustMaterial(372, "sodium_hydroxide", 6466, MaterialIconSet.SAND, 1, of(new MaterialStack(Sodium, 1), new MaterialStack(Oxygen, 1), new MaterialStack(Hydrogen, 1)), DISABLE_DECOMPOSITION);
    public static DustMaterial SodiumBisulfate = new DustMaterial(375, "sodium_bisulfate", 10291, MaterialIconSet.DULL, 1, of(new MaterialStack(Sodium, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)), DISABLE_DECOMPOSITION);

    /**
     * Gem Materials 401-450ID
     */
    public static GemMaterial Apatite = new GemMaterial(401, "apatite", 0xC8C8FF, MaterialIconSet.EMERALD, 1, of(new MaterialStack(Calcium, 5), new MaterialStack(Phosphate, 3), new MaterialStack(Chlorine, 1)), STD_GEM | NO_SMASHING | NO_SMELTING | CRYSTALLISABLE, 6.0F, 1.5F, 240);
    public static GemMaterial Almandine = new GemMaterial(402, "almandine", 0xFF0000, MaterialIconSet.GEM_VERTICAL, 2, of(new MaterialStack(Aluminium, 2), new MaterialStack(Iron, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)), STD_GEM, 8.0F, 3.4F, 844);
    public static GemMaterial Aquamarine = new GemMaterial(403, "aquamarine", 0x7FFFD4, MaterialIconSet.GEM_HORIZONTAL, 1, of(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 6), new MaterialStack(Beryllium, 2), new MaterialStack(Oxygen, 18)), STD_GEM | NO_SMASHING | NO_SMELTING, 7.0F, 2.0F, 256);
    public static GemMaterial Diamond = new GemMaterial(406, "diamond", 0xC8FFFF, MaterialIconSet.DIAMOND, 3, of(new MaterialStack(Carbon, 1)), STD_GEM | GENERATE_GEAR | NO_SMASHING | NO_SMELTING | FLAMMABLE | GENERATE_ORE | DISABLE_DECOMPOSITION, 8.0F, 4.0F, 1280);
    public static GemMaterial Emerald = new GemMaterial(407, "emerald", 0x50FF50, MaterialIconSet.EMERALD, 3, of(new MaterialStack(Beryllium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 6), new MaterialStack(Oxygen, 18)), STD_GEM | NO_SMASHING | NO_SMELTING, 9.0F, 4.5F, 2048);
    public static GemMaterial Peridot = new GemMaterial(408, "peridot", 0x64C882, MaterialIconSet.GEM_HORIZONTAL, 2, of(new MaterialStack(Magnesium, 2), new MaterialStack(Iron, 2), new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 4)), STD_GEM | NO_SMASHING | NO_SMELTING, 8.0F, 3.0F, 876); //Old form: Green Sapphire
    public static GemMaterial Lazurite = new GemMaterial(409, "lazurite", 0x6478FF, MaterialIconSet.LAPIS, 1, of(new MaterialStack(Aluminium, 6), new MaterialStack(Silicon, 6), new MaterialStack(Calcium, 8), new MaterialStack(Sodium, 8)), STD_GEM | NO_SMELTING | CRYSTALLISABLE | DECOMPOSITION_BY_ELECTROLYZING);
    public static GemMaterial Ruby = new GemMaterial(410, "ruby", 0xBD4949, MaterialIconSet.RUBY, 2, of(new MaterialStack(Chrome, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), STD_GEM | NO_SMASHING | NO_SMELTING, 8.0F, 3.0F, 876);
    public static GemMaterial Sapphire = new GemMaterial(411, "sapphire", 0x6464C8, MaterialIconSet.GEM_VERTICAL, 2, of(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), STD_GEM | NO_SMASHING | NO_SMELTING, 8.0F, 3.0F, 876);
    public static GemMaterial Sodalite = new GemMaterial(412, "sodalite", 0x1414FF, MaterialIconSet.LAPIS, 1, of(new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Sodium, 4), new MaterialStack(Chlorine, 1)), STD_GEM | NO_SMASHING | NO_SMELTING | CRYSTALLISABLE | DECOMPOSITION_BY_ELECTROLYZING);
    public static GemMaterial Topaz = new GemMaterial(414, "topaz", 0xFF8000, MaterialIconSet.GEM_HORIZONTAL, 1, of(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6)), STD_GEM | NO_SMASHING | NO_SMELTING, 7.0F, 2.5F, 1024);
    public static GemMaterial NetherQuartz = new GemMaterial(415, "nether_quartz", 0xE6D2D2, MaterialIconSet.QUARTZ, 1, of(), NO_SMASHING | NO_SMELTING | CRYSTALLISABLE | STD_GEM);
    public static GemMaterial CertusQuartz = new GemMaterial(416, "certus_quartz", 0xD2D2E6, MaterialIconSet.QUARTZ, 1, of(), NO_SMASHING | NO_SMELTING | CRYSTALLISABLE | STD_GEM, 8.0F, 2.0F, 340);
    public static GemMaterial Quartzite = new GemMaterial(418, "quartzite", 0xD2E6D2, MaterialIconSet.QUARTZ, 2, of(), NO_SMASHING | NO_SMELTING | CRYSTALLISABLE | STD_GEM);
    public static GemMaterial Olivine = new GemMaterial(420, "olivine", 0x66FF66, MaterialIconSet.RUBY, 2, of(new MaterialStack(Magnesium, 2), new MaterialStack(Iron, 1), new MaterialStack(SiliconDioxide, 2)), STD_GEM | NO_SMASHING | NO_SMELTING, 8.5F, 3.0F, 1024);
    public static GemMaterial Opal = new GemMaterial(421, "opal", 0x0000FF, MaterialIconSet.OPAL, 2, of(new MaterialStack(SiliconDioxide, 1)), STD_GEM | NO_SMASHING | NO_SMELTING, 8.5F, 3.0F, 1024);
    public static GemMaterial Lapis = new GemMaterial(423, "lapis", 0x4646DC, MaterialIconSet.LAPIS, 1, of(new MaterialStack(Lazurite, 12), new MaterialStack(Sodalite, 2), new MaterialStack(Pyrite, 1), new MaterialStack(Calcite, 1)), STD_GEM | NO_SMASHING | NO_SMELTING | CRYSTALLISABLE | NO_WORKING | DECOMPOSITION_BY_ELECTROLYZING);
    public static GemMaterial Xenotime = new GemMaterial(424, "xenotime", 0xC62B0B, MaterialIconSet.GEM_HORIZONTAL, 3, of(new MaterialStack(Yttrium, 1), new MaterialStack(Arsenic, 1), new MaterialStack(Oxygen, 4)), GENERATE_ORE);
    public static GemMaterial Citrine = new GemMaterial(426, "citrine", 0xFFEE02, MaterialIconSet.OPAL, 2, of(new MaterialStack(SiliconDioxide, 1)), STD_GEM | NO_SMASHING | NO_SMELTING | CRYSTALLISABLE, 7.5F, 3.0F, 480);
    public static GemMaterial Alexandrite = new GemMaterial(428, "alexandrite", 0x0A0A0A, MaterialIconSet.EMERALD, 3, of(new MaterialStack(Beryllium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 4)), STD_GEM | NO_SMASHING | NO_SMELTING, 9.5F, 5.0F, 512);
    public static GemMaterial Onyx = new GemMaterial(429, "onyx", 0x333333, MaterialIconSet.DIAMOND, 3, of(new MaterialStack(SiliconDioxide, 1)), STD_GEM | NO_SMASHING | NO_SMELTING, 8.5F, 4.0F, 2019);
    public static GemMaterial Spessartine = new GemMaterial(430, "spessartine", 0xFF9400, MaterialIconSet.GEM_VERTICAL, 2, of(new MaterialStack(Aluminium, 2), new MaterialStack(Manganese, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)), STD_GEM | CRYSTALLISABLE);
    public static GemMaterial Malachite = new GemMaterial(431, "malachite", 0x055F05, MaterialIconSet.EMERALD, 2, of(new MaterialStack(Copper, 2), new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 5)), STD_GEM | CRYSTALLISABLE);
    public static GemMaterial Spinel = new GemMaterial(432, "spinel", 8991416, MaterialIconSet.EMERALD, 2, of(new MaterialStack(Magnesium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 4), new MaterialStack(Oxygen, 5)), STD_GEM);
    public static GemMaterial Zircon = new GemMaterial(433, "zircon", 0x42F4F4, MaterialIconSet.GEM_VERTICAL, 3, of(new MaterialStack(Zirconium, 1), new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 4), new MaterialStack(Oxygen, 5)), STD_GEM);

    /**
     * Second Degree Compounds : 451-500ID
     */
    public static FluidMaterial Styrene = new FluidMaterial(466, "styrene", 10722453, FLUID, of(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Butadiene = new FluidMaterial(467, "butadiene", 11885072, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static DustMaterial Concrete = new DustMaterial(456, "concrete", 0x646464, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Stone, 1)), NO_SMASHING | SMELT_INTO_FLUID);
    public static IngotMaterial Graphene = new IngotMaterial(453, "graphene", 0x808080, MaterialIconSet.SHINY, 2, of(), GENERATE_PLATE | GENERATE_FOIL);
    public static DustMaterial Fireclay = new DustMaterial(452, "fireclay", 0x928073, MaterialIconSet.FINE, 2, of(new MaterialStack(Clay, 1), new MaterialStack(Brick, 1)), DECOMPOSITION_BY_CENTRIFUGING);
    public static IngotMaterial Acrylic = new IngotMaterial(457, "acrylic", 15657130, MaterialIconSet.DULL, 1, of(new MaterialStack(Oxygen, 2), new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)), GENERATE_PLATE | SMELT_INTO_FLUID | DISABLE_DECOMPOSITION);
    public static DustMaterial Polydimethylsiloxane = new DustMaterial(458, "polydimethylsiloxane", 9211020, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1), new MaterialStack(Silicon, 1)), DECOMPOSITION_BY_ELECTROLYZING);
    public static DustMaterial StyreneButadieneRubber = new IngotMaterial(459, "styrene_butadiene_rubber", 1906453, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8), new MaterialStack(Butadiene, 3)), GENERATE_PLATE | GENERATE_GEAR | GENERATE_RING | FLAMMABLE | NO_SMASHING);
    public static DustMaterial SiliconeRubber = new IngotMaterial(460, "silicon_rubber", 11316396, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1), new MaterialStack(Silicon, 1)), GENERATE_PLATE | GENERATE_GEAR | GENERATE_RING | FLAMMABLE | NO_SMASHING | GENERATE_FOIL);
    //public static DustMaterial Polystyrene = new IngotMaterial(461, "polystyrene", 8945785, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8)), DISABLE_DECOMPOSITION | GENERATE_FOIL);
    public static DustMaterial RawStyreneButadieneRubber = new DustMaterial(462, "raw_styrene_butadiene_rubber", 5192762, MaterialIconSet.SAND, 1, of(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8), new MaterialStack(Butadiene, 3)), DISABLE_DECOMPOSITION);
    public static IngotMaterial AcrylonitrileButadieneStyrene = new IngotMaterial(468, "acrylonitrile_butadiene_styrene", 0xCDCDCD, MaterialIconSet.DULL, 1, of(), GENERATE_PLATE);
    public static FluidMaterial Acrylonitrile = new FluidMaterial(469, "acrylonitrile", 0xC8C8DC, FLUID, of(), 0);
    public static DustMaterial AmmoniumSulfate = new DustMaterial(470, "ammonium_sulfate", 0xC8C8DC, MaterialIconSet.SAND, 1, of(new MaterialStack(Nitrogen, 2), new MaterialStack(Hydrogen, 8), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)), SMELT_INTO_FLUID);
    public static DustMaterial AmmoniumPersulfate = new DustMaterial(471, "ammonium_persulfate", 0xC8C8DC, MaterialIconSet.SAND, 1, of(new MaterialStack(Nitrogen, 2), new MaterialStack(Hydrogen, 8), new MaterialStack(Sulfur, 2), new MaterialStack(Oxygen, 8)), SMELT_INTO_FLUID);
    public static FluidMaterial SOHIOMixture = new FluidMaterial(472, "sohio_mixture", 0x928073, FLUID, of(), 0);
    public static FluidMaterial Acetonitrile = new FluidMaterial(473, "acetonitrile", 0xFADCE1, FLUID, of(), 0);
    //public static FluidMaterial Chlorodifluoromethane = new FluidMaterial(474, "chlorodifluoromethane", 0x647D7D, MaterialIconSet.GAS, of(), 0);
    public static FluidMaterial DichloroTrifluoroethane = new FluidMaterial(475, "2_2-dichloro-1_1_1-trifluoroethane", 0xFADCE1, MaterialIconSet.GAS, of(), 0);
    public static FluidMaterial Tetrachloroethylene = new FluidMaterial(476, "tetrachloroethylene", 0xFADCE1, FLUID, of(), 0);
    public static FluidMaterial EthyleneDichloride = new FluidMaterial(477, "ethylene_dichloride", 0xFADCE1, FLUID, of(), 0);
    public static DustMaterial FerrousChloride = new DustMaterial(478, "ferrous_chloride", 0x68F442, MaterialIconSet.SAND, 1, of(new MaterialStack(Iron, 1), new MaterialStack(Chlorine, 2)), SMELT_INTO_FLUID);
    public static DustMaterial FerricChloride = new DustMaterial(479, "ferric_chloride", 0xFCE700, MaterialIconSet.SAND, 1, of(new MaterialStack(Iron, 1), new MaterialStack(Chlorine, 3)), SMELT_INTO_FLUID);
    public static FluidMaterial Trichlorotrifluoroethane = new FluidMaterial(480, "trichlorotrifluoroethane", 0x505050, FLUID, of(), 0);
    public static FluidMaterial Chlorotrifluoroethylene = new FluidMaterial(481, "chlorotrifluoroethylene", 0xCDCEF6, FLUID, of(), 0);
    public static IngotMaterial Polychlorotrifluoroethylene = new IngotMaterial(482, "polychlorotrifluoroethylene", 0xCDCEF6, MaterialIconSet.SHINY, 1, of(new MaterialStack(Carbon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Carbon, 1), new MaterialStack(Chlorine, 1), new MaterialStack(Fluorine, 1)), GENERATE_PLATE | SMELT_INTO_FLUID);
    public static DustMaterial BariumSulfide = new DustMaterial(483, "barium_sulfide", 11885072, MaterialIconSet.SAND, 1, of(new MaterialStack(Barium, 1), new MaterialStack(Sulfur, 3)), SMELT_INTO_FLUID);
    public static DustMaterial BariumOxide = new DustMaterial(485, "barium_oxide", 0x647E7D, MaterialIconSet.SAND, 1, of(new MaterialStack(Barium, 1), new MaterialStack(Oxygen, 1)), SMELT_INTO_FLUID);
    public static DustMaterial BariumPeroxide = new DustMaterial(486, "barium_peroxide", 0x647E7D, MaterialIconSet.SAND, 1, of(new MaterialStack(Barium, 1), new MaterialStack(Oxygen, 2)), SMELT_INTO_FLUID);
    public static IngotMaterial CarbonFiberReinforcedPolymer = new IngotMaterial(487, "carbon_fiber_reinforced_polymer", 0x222222, MaterialIconSet.METALLIC, 1, of(), GENERATE_PLATE | SMELT_INTO_FLUID);
    public static IngotMaterial Polyacrylonitrile = new IngotMaterial(488, "polyacrylonitrile", 0xC8C8DC, MaterialIconSet.DULL, 1, of(), GENERATE_PLATE | SMELT_INTO_FLUID);
    public static IngotMaterial PolyvinylAcetate = new IngotMaterial(766, "polyvinyl_acetate", 13139532, MaterialIconSet.DULL, 1, of(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2)), GENERATE_PLATE);

    /**
     * Alloys 501:600ID
     */
    public static IngotMaterial StainlessSteel = new IngotMaterial(501, "stainless_steel", 0xC8C8DC, MaterialIconSet.SHINY, 2, of(new MaterialStack(Iron, 6), new MaterialStack(Chrome, 1), new MaterialStack(Manganese, 1), new MaterialStack(Nickel, 1)), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR | GENERATE_FRAME, null, 6.0F, 3.5F, 1248, 1700);
    public static IngotMaterial Steel = new IngotMaterial(502, "steel", 0x505050, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 1)), EXT2_METAL | MORTAR_GRINDABLE | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR | GENERATE_DENSE | DISABLE_DECOMPOSITION | GENERATE_FRAME, null, 6.0F, 3.0F, 748, 1000);
    public static IngotMaterial RedAlloy = new IngotMaterial(504, "red_alloy", 0xC80000, MaterialIconSet.METALLIC, 0, of(new MaterialStack(Copper, 1), new MaterialStack(Redstone, 4)), STD_METAL | GENERATE_FINE_WIRE | GENERATE_FOIL);
    public static IngotMaterial SterlingSilver = new IngotMaterial(505, "sterling_silver", 0xFADCE1, MaterialIconSet.SHINY, 2, of(new MaterialStack(Copper, 1), new MaterialStack(Silver, 4)), EXT2_METAL, null, 13.0F, 3.0F, 256, 1700);
    public static IngotMaterial RoseGold = new IngotMaterial(507, "rose_gold", 0xFFE61E, MaterialIconSet.SHINY, 2, of(new MaterialStack(Copper, 1), new MaterialStack(Gold, 4)), EXT_METAL | GENERATE_RING, null, 13.0F, 3.0F, 256, 1600);
    public static IngotMaterial BismuthBronze = new IngotMaterial(506, "bismuth_bronze", 0x647D7D, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Bismuth, 1), new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3)), EXT_METAL, 8.0F, 3.0F, 512);
    public static IngotMaterial DamascusSteel = new IngotMaterial(509, "damascus_steel", 0x6E6E6E, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Steel, 1)), 0, null, 9.0F, 3.5F, 1280, 1500);
    public static IngotMaterial TungstenSteel = new IngotMaterial(508, "tungsten_steel", 0x6464A0, MaterialIconSet.METALLIC, 4, of(new MaterialStack(Steel, 1), new MaterialStack(Tungsten, 1)), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR | GENERATE_DENSE | GENERATE_FRAME, null, 9.0F, 3.0F, 2560, 3000);
    public static IngotMaterial TungstenCarbide = new IngotMaterial(510, "tungsten_carbide", 0x330066, MaterialIconSet.METALLIC, 4, of(new MaterialStack(Tungsten, 1), new MaterialStack(Carbon, 1)), 0, null, 13.0F, 3.5F, 1280, 2460);
    public static IngotMaterial Invar = new IngotMaterial(511, "invar", 0xB4B478, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 2), new MaterialStack(Nickel, 1)), EXT2_METAL | MORTAR_GRINDABLE | GENERATE_RING | GENERATE_FRAME, 6.0F, 2.5F, 256);
    public static IngotMaterial VanadiumSteel = new IngotMaterial(512, "vanadium_steel", 0xC0C0C0, MaterialIconSet.METALLIC, 3, of(new MaterialStack(Vanadium, 1), new MaterialStack(Chrome, 1), new MaterialStack(Steel, 7)), EXT_METAL, null, 6.0F, 5.0F, 400, 1453);
    public static IngotMaterial HSSG = new IngotMaterial(513, "hssg", 0x999900, MaterialIconSet.METALLIC, 3, of(new MaterialStack(TungstenSteel, 5), new MaterialStack(Chrome, 1), new MaterialStack(Molybdenum, 2), new MaterialStack(Vanadium, 1)), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR | GENERATE_FRAME, null, 10.0F, 5.0F, 3584, 4500);
    public static IngotMaterial HSSE = new IngotMaterial(515, "hsse", 0x336600, MaterialIconSet.METALLIC, 4, of(new MaterialStack(HSSG, 6), new MaterialStack(Cobalt, 1), new MaterialStack(Manganese, 1), new MaterialStack(Silicon, 1)), EXT2_METAL | GENERATE_RING | GENERATE_ROTOR | GENERATE_SMALL_GEAR, null, 10.0F, 5.0F, 5120, 5400);
    public static IngotMaterial HSSS = new IngotMaterial(514, "hsss", 0x660033, MaterialIconSet.METALLIC, 5, of(new MaterialStack(HSSG, 6), new MaterialStack(Iridium, 2), new MaterialStack(Osmium, 1)), EXT2_METAL | GENERATE_GEAR, null, 14.0F, 6.0F, 3200, 5400);
    public static IngotMaterial IronMagnetic = new IngotMaterial(516, "iron_magnetic", 0xC8C8C8, MaterialIconSet.MAGNETIC, 2, of(new MaterialStack(Iron, 1)), STD_METAL | GENERATE_ROD | MORTAR_GRINDABLE);
    public static IngotMaterial SteelMagnetic = new IngotMaterial(517, "steel_magnetic", 0x808080, MaterialIconSet.MAGNETIC, 2, of(new MaterialStack(Steel, 1)), STD_METAL | GENERATE_ROD | MORTAR_GRINDABLE);
    public static IngotMaterial NeodymiumMagnetic = new IngotMaterial(518, "neodymium_magnetic", 0x646464, MaterialIconSet.MAGNETIC, 2, of(new MaterialStack(Neodymium, 1)), STD_METAL | GENERATE_ROD);
    public static IngotMaterial Osmiridium = new IngotMaterial(519, "osmiridium", 0x6464FF, MaterialIconSet.METALLIC, 5, of(new MaterialStack(Iridium, 3), new MaterialStack(Osmium, 1)), EXT2_METAL, null, 15.0F, 6.0F, 4800, 2500);
    public static IngotMaterial SolderingAlloy = new IngotMaterial(520, "soldering_alloy", 0xDCDCE6, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Tin, 9), new MaterialStack(Antimony, 1)), EXT_METAL | GENERATE_FINE_WIRE);
    public static IngotMaterial Nichrome = new IngotMaterial(521, "nichrome", 0xCDCEF6, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Nickel, 4), new MaterialStack(Chrome, 1)), EXT_METAL, null, 6.0F, 2.5F, 128, 2700);
    public static IngotMaterial NiobiumTitanium = new IngotMaterial(522, "niobium_titanium", 0x1D1D29, MaterialIconSet.SHINY, 2, of(new MaterialStack(Niobium, 1), new MaterialStack(Titanium, 1)), EXT2_METAL, null, 4500);
    public static IngotMaterial VanadiumGallium = new IngotMaterial(523, "vanadium_gallium", 0x80808C, MaterialIconSet.SHINY, 2, of(new MaterialStack(Vanadium, 3), new MaterialStack(Gallium, 1)), STD_METAL | GENERATE_FOIL | GENERATE_ROD, null, 4500);
    public static IngotMaterial WroughtIron = new IngotMaterial(525, "wrought_iron", 0xC8B4B4, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 1)), EXT2_METAL | MORTAR_GRINDABLE | GENERATE_RING, Iron.toolSpeed + 0.5F, Iron.toolAttackDamage, Iron.toolDurability + 50);
    public static IngotMaterial Electrum = new IngotMaterial(526, "electrum", 0xFFFF64, MaterialIconSet.SHINY, 2, of(new MaterialStack(Silver, 1), new MaterialStack(Gold, 1)), EXT2_METAL | MORTAR_GRINDABLE | GENERATE_FOIL, 12.0F, 2.0F, 120);
    public static IngotMaterial PigIron = new IngotMaterial(524, "pig_iron", 0xC8B4B4, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 1)), EXT_METAL, Iron.toolSpeed + 1.5F, Iron.toolAttackDamage, Iron.toolDurability + 100);
    public static IngotMaterial AnnealedCopper = new IngotMaterial(527, "annealed_copper", 0xFF7814, MaterialIconSet.SHINY, 2, of(new MaterialStack(Copper, 1)), EXT2_METAL | MORTAR_GRINDABLE);
    public static IngotMaterial BatteryAlloy = new IngotMaterial(528, "battery_alloy", 0x9C7CA0, MaterialIconSet.DULL, 1, of(new MaterialStack(Lead, 4), new MaterialStack(Antimony, 1)), STD_METAL);
    public static IngotMaterial Kanthal = new IngotMaterial(529, "kanthal", 0xC2D2DF, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Iron, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Chrome, 1)), EXT_METAL, null, 6.0F, 3.0F, 240, 1800);
    public static IngotMaterial Magnalium = new IngotMaterial(530, "magnalium", 0xC8BEFF, MaterialIconSet.DULL, 2, of(new MaterialStack(Magnesium, 1), new MaterialStack(Aluminium, 2)), EXT_METAL, 6.0F, 2.5F, 256);
    public static IngotMaterial Cupronickel = new IngotMaterial(534, "cupronickel", 0xE39680, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Copper, 1), new MaterialStack(Nickel, 1)), EXT_METAL | GENERATE_RING, 6.0F, 2.5F, 64);
    public static IngotMaterial YttriumBariumCuprate = new IngotMaterial(531, "yttrium_barium_cuprate", 0x504046, MaterialIconSet.METALLIC, 4, of(new MaterialStack(Yttrium, 1), new MaterialStack(Barium, 2), new MaterialStack(Copper, 3), new MaterialStack(Oxygen, 7)), EXT_METAL | GENERATE_FOIL, null, 4500);
    public static IngotMaterial Brass = new IngotMaterial(532, "brass", 0xFFB400, MaterialIconSet.METALLIC, 1, of(new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3)), EXT_METAL | GENERATE_RING | MORTAR_GRINDABLE, 7.0F, 2.0F, 96);
    public static IngotMaterial Bronze = new IngotMaterial(533, "bronze", 0xFF8000, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Tin, 1), new MaterialStack(Copper, 3)), EXT2_METAL | MORTAR_GRINDABLE | GENERATE_ROTOR | GENERATE_FRAME, 6.5F, 2.5F, 256);
    public static IngotMaterial Ultimet = new IngotMaterial(535, "ultimet", 0xB4B4E6, MaterialIconSet.SHINY, 4, of(new MaterialStack(Cobalt, 5), new MaterialStack(Chrome, 2), new MaterialStack(Nickel, 1), new MaterialStack(Molybdenum, 1)), EXT2_METAL, null, 9.0F, 4.0F, 2048, 2700);
    public static IngotMaterial BerylliumCopper = new IngotMaterial(536, "beryllium_copper", 0x64B464, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Copper, 5), new MaterialStack(Beryllium, 2), new MaterialStack(Nickel, 1)), STD_METAL | GENERATE_ROD | GENERATE_RING | GENERATE_FOIL, null, 7.0F, 3.5F, 593, 1100);
    //public static IngotMaterial AluminiumCopper = new IngotMaterial(537, "aluminium_copper", 0xFADCE1, MaterialIconSet.SHINY, 2, of(new MaterialStack(Copper, 3), new MaterialStack(Aluminium, 1)), STD_METAL | GENERATE_ROD | GENERATE_RING | GENERATE_FOIL, null, 8.0F, 3.8F, 710, 2500);

    /**
     * Magic + Fantasy Materials 601-650ID
     */
    //GT inheritent fantasy materials
    public static IngotMaterial Naquadah = new IngotMaterial(601, "naquadah", 0x323232, MaterialIconSet.METALLIC, 4, of(), EXT2_METAL | GENERATE_ORE, Element.Nq, 6.0F, 4.0F, 1280, 5400);
    public static IngotMaterial NaquadahEnriched = new IngotMaterial(602, "naquadah_enriched", 0x282828, MaterialIconSet.METALLIC, 5, of(), EXT2_METAL | GENERATE_ORE, null, 6.0F, 4.0F, 1280, 4500);
    public static IngotMaterial NaquadahAlloy = new IngotMaterial(603, "naquadah_alloy", 0x282828, MaterialIconSet.METALLIC, 5, of(new MaterialStack(Naquadah, 1), new MaterialStack(Osmiridium, 1)), EXT2_METAL, null, 10.0F, 5.0F, 5120, 7200);
    //public static IngotMaterial Adamantine = new IngotMaterial(604, "adamantine", 0xF0E68C, MaterialIconSet.METALLIC, 5, of(), EXT_METAL, 9.0F, 4.5F, 4096);

    /**
     * Coal and Related Items 671-680ID
     */
    public static GemMaterial Charcoal = new GemMaterial(671, "charcoal", 0x644646, MaterialIconSet.LIGNITE, 0, of(new MaterialStack(Carbon, 1)), FLAMMABLE | NO_SMELTING | NO_SMASHING | MORTAR_GRINDABLE);
    public static GemMaterial Coal = new GemMaterial(672, "coal", 0x464646, MaterialIconSet.LIGNITE, 1, of(new MaterialStack(Carbon, 1)), GENERATE_ORE | FLAMMABLE | NO_SMELTING | NO_SMASHING | MORTAR_GRINDABLE);
    public static GemMaterial Lignite = new GemMaterial(673, "lignite", 0x644646, MaterialIconSet.LIGNITE, 0, of(new MaterialStack(Carbon, 2), new MaterialStack(Water, 4), new MaterialStack(DarkAsh, 1)), GENERATE_ORE | FLAMMABLE | NO_SMELTING | NO_SMASHING | MORTAR_GRINDABLE);
    public static GemMaterial Coke = new GemMaterial(674, "coke", 0x666666, MaterialIconSet.LIGNITE, 0, of(new MaterialStack(Carbon, 1)), FLAMMABLE | NO_SMELTING | NO_SMASHING | MORTAR_GRINDABLE);

    /**
     * Foods 681-700ID
     */
    public static FluidMaterial Milk = new FluidMaterial(681, "milk", 0xFEFEFE, MaterialIconSet.FINE, of(), 0);
    public static DustMaterial Wheat = new DustMaterial(682, "wheat", 0xFFFFC4, MaterialIconSet.FINE, 0, of(), 0);
    public static FluidMaterial Honey = new FluidMaterial(683, "honey", 0xD2C800, FLUID, of(), 0);
    public static DustMaterial Cocoa = new DustMaterial(685, "cocoa", 0xBE5F00, MaterialIconSet.ROUGH, 0, of(), 0);
    public static DustMaterial Sugar = new DustMaterial(686, "sugar", 0xFAFAFA, MaterialIconSet.SAND, 0, of(new MaterialStack(Carbon, 2), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 25)), EXCLUDE_BLOCK_CRAFTING_RECIPES);

    /**
     * Acids 701-750ID
     */
    public static FluidMaterial SulfuricAcid = new FluidMaterial(701, "sulfuric_acid", 0xFF8000, FLUID, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HypochlorousAcid = new FluidMaterial(704, "hypochlorous_acid", 6123637, FLUID, of(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1), new MaterialStack(Oxygen, 1)), 0);
    public static FluidMaterial NitricAcid = new FluidMaterial(702, "nitric_acid", 0xCCCC00, FLUID, of(new MaterialStack(Hydrogen, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3)), 0);
    public static FluidMaterial PhosphoricAcid = new FluidMaterial(703, "phosphoric_acid", 11447824, FLUID, of(new MaterialStack(Hydrogen, 3), new MaterialStack(Phosphorus, 4)), 0);
    public static FluidMaterial HydrochloricAcid = new FluidMaterial(707, "hydrochloric_acid", 9477273, FLUID, of(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1)), 0);
    public static FluidMaterial AceticAcid = new FluidMaterial(708, "acetic_acid", 10260096, FLUID, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 2)), 0);
    public static FluidMaterial DilutedSulfuricAcid = new FluidMaterial(705, "diluted_sulfuric_acid", 9987366, FLUID, of(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)), 0);
    public static FluidMaterial DilutedHydrochloricAcid = new FluidMaterial(706, "diluted_hydrochloric_acid", 8160900, FLUID, of(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1)), 0);
    public static FluidMaterial HydrofluoricAcid = new FluidMaterial(709, "hydrofluoric_acid", 946055, FLUID, of(new MaterialStack(Hydrogen, 1), new MaterialStack(Fluorine, 1)), 0);
    public static FluidMaterial HydrocyanicAcid = new FluidMaterial(710, "hydrocyanic_acid", 946055, FLUID, of(new MaterialStack(Hydrogen, 1), new MaterialStack(Carbon, 1), new MaterialStack(Nitrogen, 1)), 0);

    /**
     * General Fluids 751-900ID
     */
    public static FluidMaterial Lubricant = new FluidMaterial(751, "lubricant", 0xFFC400, FLUID, of(), 0);
    public static FluidMaterial Glue = new FluidMaterial(753, "glue", 0xC8C400, FLUID, of(), 0);
    public static FluidMaterial ConstructionFoam = new FluidMaterial(754, "construction_foam", 0x333333, FLUID, of(), 0);
    public static FluidMaterial IndiumConcentrate = new FluidMaterial(757, "indium_concentrate", 205130, FLUID, of(), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial LeadZincSolution = new FluidMaterial(756, "lead_zinc_solution", 3213570, FLUID, of(new MaterialStack(Lead, 1), new MaterialStack(Silver, 1), new MaterialStack(Zinc, 1), new MaterialStack(Sulfur, 3), new MaterialStack(Water, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Tetrafluoroethylene = new FluidMaterial(758, "tetrafluoroethylene", 6776679, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 2), new MaterialStack(Fluorine, 4)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial SaltWater = new FluidMaterial(755, "salt_water", 255, FLUID, of(new MaterialStack(Salt, 1), new MaterialStack(Water, 1)), DISABLE_DECOMPOSITION);
    public static FluidMaterial Benzene = new FluidMaterial(760, "benzene", 2039583, FLUID, of(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 6)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Chloroform = new FluidMaterial(759, "chloroform", 7351936, FLUID, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 3)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Dimethylhydrazine = new FluidMaterial(761, "dimethylhidrazine", 1052748, FLUID, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 8), new MaterialStack(Nitrogen, 2)), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial Tetranitromethane = new FluidMaterial(763, "tetranitromethane", 1715244, FLUID, of(new MaterialStack(Carbon, 1), new MaterialStack(Nitrogen, 4), new MaterialStack(Oxygen, 8)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial VinylAcetate = new FluidMaterial(764, "vinyl_acetate", 13144428, FLUID, of(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Chloromethane = new FluidMaterial(765, "chloromethane", 10301057, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Phenol = new FluidMaterial(767, "phenol", 6635559, FLUID, of(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    //public static FluidMaterial VinylChloride = new FluidMaterial(768, "vinyl_chloride", 11582395, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Cumene = new FluidMaterial(769, "cumene", 4924684, FLUID, of(new MaterialStack(Carbon, 9), new MaterialStack(Hydrogen, 12)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Ammonia = new FluidMaterial(770, "ammonia", 4011371, MaterialIconSet.GAS, of(new MaterialStack(Nitrogen, 1), new MaterialStack(Hydrogen, 3)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial NitricOxide = new FluidMaterial(711, "nitric_oxide", 6790328, MaterialIconSet.GAS, of(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 1)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Dimethylamine = new FluidMaterial(712, "dimethylamine", 4931417, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 7), new MaterialStack(Nitrogen, 1)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Methanol = new FluidMaterial(713, "methanol", 8941584, FLUID, of(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Chloramine = new FluidMaterial(715, "chloramine", 4031340, MaterialIconSet.GAS, of(new MaterialStack(Nitrogen, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Chlorine, 1)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial FermentedBiomass = new FluidMaterial(719, "fermented_biomass", 4147981, FLUID, of(), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial WoodVinegar = new FluidMaterial(718, "wood_vinegar", 10832655, FLUID, of(), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial WoodTar = new FluidMaterial(717, "wood_tar", 2957592, FLUID, of(), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial BioDiesel = new FluidMaterial(720, "bio_diesel", 12806415, FLUID, of(), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial Acetone = new FluidMaterial(721, "acetone", 9342606, FLUID, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial SulfurTrioxide = new FluidMaterial(714, "sulfur_trioxide", 8618781, MaterialIconSet.GAS, of(new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 3)), GENERATE_FLUID_BLOCK | STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial SulfurDioxide = new FluidMaterial(725, "sulfur_dioxide", 10263584, MaterialIconSet.GAS, of(new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 2)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Glycerol = new FluidMaterial(726, "glycerol", 7384944, FLUID, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 8), new MaterialStack(Oxygen, 3)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Ethylene = new FluidMaterial(729, "ethylene", 11382189, MaterialIconSet.GAS, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4)), STATE_GAS | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial MethylAcetate = new FluidMaterial(723, "methyl_acetate", 12427150, FLUID, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial CalciumAcetate = new FluidMaterial(728, "calcium_acetate", 11444113, FLUID, of(new MaterialStack(Calcium, 1), new MaterialStack(Carbon, 2), new MaterialStack(Oxygen, 4), new MaterialStack(Hydrogen, 6)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Ethenone = new FluidMaterial(724, "ethenone", 1776449, FLUID, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial BisphenolA = new FluidMaterial(731, "bisphenol_a", 10848014, FLUID, of(new MaterialStack(Carbon, 15), new MaterialStack(Hydrogen, 16), new MaterialStack(Oxygen, 2)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial AllylChloride = new FluidMaterial(732, "allyl_chloride", 7450250, FLUID, of(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Chlorine, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial SodiumCyanide = new FluidMaterial(733, "sodium_cyanide", 16316671, FLUID, of(new MaterialStack(Sodium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Nitrogen, 1)), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial AcetoneCyanohydrin = new FluidMaterial(734, "acetone_cyanohydrin", 12092939, FLUID, of(new MaterialStack(Oxygen, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 7)), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial MethylMethacrylate = new FluidMaterial(735, "methyl_methacrylate", 16777215, FLUID, of(new MaterialStack(Oxygen, 2), new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)), GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial Dimethyldichlorosilane = new FluidMaterial(736, "dimethyldichlorosilane", 4070471, FLUID, of(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Chlorine, 2), new MaterialStack(Silicon, 1)), GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Dichlorobenzene = new FluidMaterial(737, "dichlorobenzene", 868171, FLUID, of(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 4), new MaterialStack(Chlorine, 2)),GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial Isoprene = new FluidMaterial(748, "isoprene", 1907997, FLUID, of(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)),GENERATE_FLUID_BLOCK | DECOMPOSITION_BY_ELECTROLYZING);
    public static FluidMaterial NickelSulfateSolution = new FluidMaterial(749, "nickel_sulfate_water_solution", 4109888, FLUID, of(),GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);
    public static FluidMaterial BlueVitriolSolution = new FluidMaterial(730, "blue_vitriol_water_solution", 4761024, FLUID, of(),GENERATE_FLUID_BLOCK | DISABLE_DECOMPOSITION);

    /**
     * Cracked Fluids
     */
    public static FluidMaterial HydroCrackedEthane = new FluidMaterial(916, "hydrocracked_ethane", 9868988, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedEthylene = new FluidMaterial(915, "hydrocracked_ethylene", 10724256, MaterialIconSet.GAS, of(), STATE_GAS);
    public static FluidMaterial HydroCrackedPropene = new FluidMaterial(914, "hydrocracked_propene", 12494144, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedPropane = new FluidMaterial(913, "hydrocracked_propane", 12494144, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedLightFuel = new FluidMaterial(912, "hydrocracked_light_fuel", 12037896, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedButane = new FluidMaterial(911, "hydrocracked_butane", 8727576, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedNaphtha = new FluidMaterial(910, "hydrocracked_naphtha", 12563976, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedHeavyFuel = new FluidMaterial(909, "hydrocracked_heavy_fuel", 16776960, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedGas = new FluidMaterial(908, "hydrocracked_gas", 11842740, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedButene = new FluidMaterial(907, "hydrocracked_butene", 10042885, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial HydroCrackedButadiene = new FluidMaterial(906, "hydrocracked_butadiene", 11358723, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedEthane = new FluidMaterial(905, "steamcracked_ethane", 9868988, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedEthylene = new FluidMaterial(904, "steamcracked_ethylene", 10724256, MaterialIconSet.GAS, of(), STATE_GAS);
    public static FluidMaterial SteamCrackedPropene = new FluidMaterial(903, "steamcracked_propene", 12494144, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedPropane = new FluidMaterial(902, "steamcracked_propane", 12494144, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedButane = new FluidMaterial(901, "steamcracked_butane", 8727576, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedNaphtha = new FluidMaterial(917, "steamcracked_naphtha", 12563976, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedGas = new FluidMaterial(918, "steamcracked_gas", 11842740, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedButene = new FluidMaterial(919, "steamcracked_butene", 10042885, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedButadiene = new FluidMaterial(920, "steamcracked_butadiene", 11358723, FLUID, of(), GENERATE_FLUID_BLOCK);
    public static FluidMaterial SteamCrackedLightFuel = new FluidMaterial(921, "steamcracked_light_fuel", 0xFFFF00, FLUID, of(), 0);
    public static FluidMaterial SteamCrackedHeavyFuel = new FluidMaterial(922, "steamcracked_heavy_fuel", 0xFFFF00, FLUID, of(), 0);

    public static Material Superconductor = new Material(959, "superconductor",0xFFFFFF, MaterialIconSet.NONE, of(), 0L, null) {};



    public static DustMaterial Tantalite = new DustMaterial(960, "tantalite", 0x915028, MaterialIconSet.METALLIC, 3, of(new MaterialStack(Manganese, 1), new MaterialStack(Tantalum, 2), new MaterialStack(Oxygen, 6)), GENERATE_ORE);
    public static DustMaterial Glauconite = new DustMaterial(961, "glauconite", 0x82B43C, MaterialIconSet.DULL, 2, of(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 2), new MaterialStack(Aluminium, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12)), GENERATE_ORE);
    public static DustMaterial Enargite = new DustMaterial(962, "enargite", 0xBBBBBB, MaterialIconSet.METALLIC, 2, of(new MaterialStack(Copper, 3), new MaterialStack(Arsenic, 1), new MaterialStack(Sulfur, 4)), GENERATE_ORE);
    public static GemMaterial Monazite = new GemMaterial(963, "monazite", 0x324632, MaterialIconSet.GEM_VERTICAL, 1, of(new MaterialStack(RareEarth, 1), new MaterialStack(Phosphate, 1)), GENERATE_ORE | NO_SMASHING | NO_SMELTING | CRYSTALLISABLE);
    public static GemMaterial GreenSapphire = new GemMaterial(964, "green_sapphire", 0x64C882, MaterialIconSet.GEM_HORIZONTAL, 2, of(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), GENERATE_ORE | NO_SMASHING | NO_SMELTING | HIGH_SIFTER_OUTPUT | GENERATE_LENSE, 8.0F, 3.0f, 368);
    public static DustMaterial Tenorite = new DustMaterial(965, "tenorite", 0x606060, MaterialIconSet.FINE, 1, of(new MaterialStack(Copper, 1), new MaterialStack(Oxygen, 1)), GENERATE_ORE);
    public static DustMaterial Bastnasite = new DustMaterial(966, "bastnasite", 0xC86E2D, MaterialIconSet.FINE, 2, of(new MaterialStack(Cerium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Fluorine, 1), new MaterialStack(Oxygen, 3)), GENERATE_ORE);


    public static DustMaterial PotassiumFeldspar = new DustMaterial(973, "potassium_feldspar", 0x782828, MaterialIconSet.FINE, 1, of(new MaterialStack(Potassium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 8)), 0);
    public static DustMaterial Basalt = new DustMaterial(967, "basalt", 0x1E1414, MaterialIconSet.ROUGH, 1, of(new MaterialStack(Olivine, 1), new MaterialStack(Calcite, 3), new MaterialStack(Flint, 8), new MaterialStack(DarkAsh, 4)), NO_SMASHING);
    public static DustMaterial Andesite = new DustMaterial(968, "andesite", 0xBEBEBE, MaterialIconSet.ROUGH, 2, of(), NO_SMASHING);
    public static DustMaterial Diorite = new DustMaterial(969, "diorite", 0xFFFFFF, MaterialIconSet.ROUGH, 2, of(), NO_SMASHING);
    public static DustMaterial GraniteBlack = new DustMaterial(970, "granite_black", 0x0A0A0A, MaterialIconSet.ROUGH, 3, of(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Biotite, 1)), NO_SMASHING);
    public static DustMaterial GraniteRed = new DustMaterial(971, "granite_red", 0xFF0080, MaterialIconSet.ROUGH, 3, of(new MaterialStack(Aluminium, 2), new MaterialStack(PotassiumFeldspar, 1), new MaterialStack(Oxygen, 3)), NO_SMASHING);
    public static DustMaterial Marble = new DustMaterial(972, "marble", 0xC8C8C8, MaterialIconSet.FINE, 1, of(new MaterialStack(Magnesium, 1), new MaterialStack(Calcite, 7)), NO_SMASHING);



    static {
        for (DustMaterial dustMaterial : new DustMaterial[]{Magnetite, VanadiumMagnetite}) {
            dustMaterial.separatedOnto = Gold;
        }
        for (DustMaterial dustMaterial : new DustMaterial[]{YellowLimonite, BrownLimonite, Pyrite, BandedIron, Siderite, Geothite, Hematite, Nickel, Pentlandite, Tin, Antimony, Ilmenite, Manganese, Chrome, Chromite}) {
            dustMaterial.separatedOnto = Iron;
        }
        for (DustMaterial dustMaterial : new DustMaterial[]{Pyrite, YellowLimonite}) {
            dustMaterial.addFlag(BLAST_FURNACE_CALCITE_DOUBLE);
        }
        for (DustMaterial dustMaterial : new DustMaterial[]{Iron, PigIron, BrownLimonite, WroughtIron, Siderite, Hematite}) {
            dustMaterial.addFlag(BLAST_FURNACE_CALCITE_TRIPLE);
        }
        for (DustMaterial dustMaterial : new DustMaterial[]{Bastnasite, Monazite}) {
            dustMaterial.separatedOnto = Neodymium;
        }



        for (DustMaterial dustMaterial : new DustMaterial[]{Gold, Silver, Osmium, Platinum, Cooperite, Chalcopyrite, Bornite}) {
            dustMaterial.washedIn = Mercury;
        }
        for (DustMaterial dustMaterial : new DustMaterial[]{Zinc, Nickel, Copper, Cobalt, Cobaltite, Tetrahedrite, Sphalerite}) {
            dustMaterial.washedIn = SodiumPersulfate;
        }

        Neodymium.magneticMaterial = NeodymiumMagnetic;
        Steel.magneticMaterial = SteelMagnetic;
        Iron.magneticMaterial = IronMagnetic;

        NeodymiumMagnetic.setSmeltingInto(Neodymium);
        NeodymiumMagnetic.setArcSmeltingInto(Neodymium);
        NeodymiumMagnetic.setMaceratingInto(Neodymium);

        SteelMagnetic.setSmeltingInto(Steel);
        SteelMagnetic.setArcSmeltingInto(Steel);
        SteelMagnetic.setMaceratingInto(Steel);

        IronMagnetic.setSmeltingInto(Iron);
        IronMagnetic.setArcSmeltingInto(WroughtIron);
        IronMagnetic.setMaceratingInto(Iron);

        Iron.setArcSmeltingInto(WroughtIron);
        Copper.setArcSmeltingInto(AnnealedCopper);

        Tetrahedrite.setDirectSmelting(Copper);
        Chalcopyrite.setDirectSmelting(Copper);
        Tenorite.setDirectSmelting(Copper);
        Malachite.setDirectSmelting(Copper);
        Pentlandite.setDirectSmelting(Nickel);
        Sphalerite.setDirectSmelting(Zinc);
        Pyrite.setDirectSmelting(Iron);
        Cassiterite.setDirectSmelting(Tin);
        Garnierite.setDirectSmelting(Nickel);
        Cobaltite.setDirectSmelting(Cobalt);
        Stibnite.setDirectSmelting(Antimony);
        Cooperite.setDirectSmelting(Platinum);
        Pyrolusite.setDirectSmelting(Manganese);
        Magnesite.setDirectSmelting(Magnesium);
        Molybdenite.setDirectSmelting(Molybdenum);

        YellowLimonite.setDirectSmelting(Iron);
        BrownLimonite.setDirectSmelting(Iron);

        //TODO: Other gems abide with this rule
        Salt.setOreMultiplier(3);
        RockSalt.setOreMultiplier(3);
        Lepidolite.setOreMultiplier(5);

        Spodumene.setOreMultiplier(2);
        Spessartine.setOreMultiplier(2);
        Soapstone.setOreMultiplier(3);

        Almandine.setOreMultiplier(6);
        Grossular.setOreMultiplier(6);
        Bentonite.setOreMultiplier(7);
        Pyrope.setOreMultiplier(4);
        Enargite.setOreMultiplier(2);

        Bauxite.setOreMultiplier(3);

        Olivine.setOreMultiplier(2);
        Topaz.setOreMultiplier(2);

        Tennantite.setOreMultiplier(2);
        Pitchblende.setOreMultiplier(2);

        Scheelite.setOreMultiplier(2);
        Tungstate.setOreMultiplier(2);

        Cassiterite.setOreMultiplier(2);
        NetherQuartz.setOreMultiplier(2);
        CertusQuartz.setOreMultiplier(2);
        Quartzite.setOreMultiplier(2);

        Phosphor.setOreMultiplier(3);
        Saltpeter.setOreMultiplier(4);
        Apatite.setOreMultiplier(5);
        Apatite.setByProductMultiplier(2);

        Redstone.setOreMultiplier(6);
        Glowstone.setOreMultiplier(5);

        Lapis.setOreMultiplier(6);
        Lapis.setByProductMultiplier(4);
        Sodalite.setOreMultiplier(6);
        Sodalite.setByProductMultiplier(4);
        Lazurite.setOreMultiplier(6);
        Lazurite.setByProductMultiplier(4);

        Coal.setBurnTime(1600); //default coal burn time in vanilla
        Charcoal.setBurnTime(1600); //default coal burn time in vanilla
        Lignite.setBurnTime(1200); //2/3 of burn time of coal
        Coke.setBurnTime(3200); //2x burn time of coal

        Bornite.addOreByProducts(Pyrite, Cobalt, Cadmium, Gold);
        Chalcocite.addOreByProducts(Sulfur, Lead, Silver);
        Cuprite.addOreByProducts(Iron, Antimony, Malachite);
        Tennantite.addOreByProducts(Iron, Antimony, Zinc);
        Lepidolite.addOreByProducts(Boron);
        GraniteRed.addOreByProducts(PotassiumFeldspar);
        Chalcopyrite.addOreByProducts(Pyrite, Cobalt, Cadmium, Gold);
        Sphalerite.addOreByProducts(Cadmium, Gallium, Zinc);
        Uraninite.addOreByProducts(Uranium, Thorium, Lead);
        Pitchblende.addOreByProducts(Thorium, Uranium, Lead);
        Galena.addOreByProducts(Sulfur, Silver, Lead);
        Lapis.addOreByProducts(Lazurite, Sodalite, Pyrite);
        Pyrite.addOreByProducts(Sulfur, Phosphor, Iron);
        Copper.addOreByProducts(Cobalt, Gold, Nickel);
        Nickel.addOreByProducts(Cobalt, Platinum, Iron);
        Cooperite.addOreByProducts(Palladium, Nickel, Iridium);
        Pentlandite.addOreByProducts(Iron, Sulfur, Cobalt);
        Uranium.addOreByProducts(Lead, Thorium);
        Scheelite.addOreByProducts(Manganese, Molybdenum, Calcium);
        Tungstate.addOreByProducts(Manganese, Silver, Lithium);
        Bauxite.addOreByProducts(Grossular, Rutile, Gallium);
        Malachite.addOreByProducts(BrownLimonite);
        YellowLimonite.addOreByProducts(Nickel, BrownLimonite, Cobalt);
        BrownLimonite.addOreByProducts(Malachite, YellowLimonite);
        Quartzite.addOreByProducts(CertusQuartz, Barite);
        CertusQuartz.addOreByProducts(Quartzite, Barite);
        Redstone.addOreByProducts(RareEarth, Glowstone);
        Malachite.addOreByProducts(Copper, Calcite);
        Neodymium.addOreByProducts(RareEarth);
        Glowstone.addOreByProducts(Redstone, Gold);
        Zinc.addOreByProducts(Tin, Gallium);
        Tungsten.addOreByProducts(Manganese, Molybdenum);
        Iron.addOreByProducts(Nickel, Tin);
        Lepidolite.addOreByProducts(Lithium, Caesium);
        Gold.addOreByProducts(Copper, Nickel);
        Tin.addOreByProducts(Iron, Zinc);
        Antimony.addOreByProducts(Zinc, Iron);
        Silver.addOreByProducts(Lead, Sulfur);
        Lead.addOreByProducts(Silver, Sulfur);
        Thorium.addOreByProducts(Uranium, Lead);
        Electrum.addOreByProducts(Gold, Silver);
        Bronze.addOreByProducts(Copper, Tin);
        Brass.addOreByProducts(Copper, Zinc);
        Coal.addOreByProducts(Lignite, Thorium);
        Ilmenite.addOreByProducts(Iron, Rutile);
        Manganese.addOreByProducts(Chrome, Iron);
        Sapphire.addOreByProducts(Aluminium);
        Peridot.addOreByProducts(Aluminium, Magnesium);
        Platinum.addOreByProducts(Nickel, Iridium);
        Emerald.addOreByProducts(Beryllium, Aluminium);
        Olivine.addOreByProducts(Pyrope, Magnesium, Manganese);
        Chrome.addOreByProducts(Iron, Magnesium);
        Chromite.addOreByProducts(Iron, Magnesium);
        Tetrahedrite.addOreByProducts(Antimony, Zinc);
        Magnetite.addOreByProducts(Iron, Gold);
        VanadiumMagnetite.addOreByProducts(Magnetite, Vanadium);
        Lazurite.addOreByProducts(Sodalite, Lapis);
        Sodalite.addOreByProducts(Lazurite, Lapis);
        Spodumene.addOreByProducts(Aluminium, Lithium);
        Ruby.addOreByProducts(Chrome, Aluminium);
        Phosphor.addOreByProducts(Apatite, Phosphate);
        Iridium.addOreByProducts(Platinum, Osmium);
        Pyrope.addOreByProducts(Aluminium, Magnesium, Silicon);
        Almandine.addOreByProducts(Aluminium);
        Spessartine.addOreByProducts(Aluminium, Manganese);
        Grossular.addOreByProducts(Aluminium, Calcium);
        Calcite.addOreByProducts(Malachite);
        NaquadahEnriched.addOreByProducts(Naquadah);
        Naquadah.addOreByProducts(NaquadahEnriched);
        Pyrolusite.addOreByProducts(Manganese);
        Molybdenite.addOreByProducts(Molybdenum);
        Stibnite.addOreByProducts(Antimony);
        Garnierite.addOreByProducts(Nickel);
        Lignite.addOreByProducts(Coal);
        Diamond.addOreByProducts(Graphite);
        Beryllium.addOreByProducts(Emerald);
        Apatite.addOreByProducts(Phosphor);
        Magnesite.addOreByProducts(Magnesium);
        NetherQuartz.addOreByProducts(Netherrack);
        PigIron.addOreByProducts(Iron);
        Steel.addOreByProducts(Iron);
        Graphite.addOreByProducts(Carbon);
        Netherrack.addOreByProducts(Sulfur);
        Flint.addOreByProducts(Obsidian);
        Cobaltite.addOreByProducts(Cobalt);
        Cobalt.addOreByProducts(Cobaltite);
        Sulfur.addOreByProducts(Sulfur);
        Saltpeter.addOreByProducts(Saltpeter);
        Endstone.addOreByProducts(Helium3);
        Osmium.addOreByProducts(Iridium);
        Magnesium.addOreByProducts(Olivine);
        Aluminium.addOreByProducts(Bauxite);
        Titanium.addOreByProducts(Almandine);
        Obsidian.addOreByProducts(Olivine);
        Ash.addOreByProducts(Carbon);
        DarkAsh.addOreByProducts(Carbon);
        Clay.addOreByProducts(Clay);
        Cassiterite.addOreByProducts(Tin);
        Phosphate.addOreByProducts(Phosphorus);
        Phosphorus.addOreByProducts(Phosphate);
        Niter.addOreByProducts(Saltpeter);
        Lithium.addOreByProducts(Lithium);
        Silicon.addOreByProducts(SiliconDioxide);
        Salt.addOreByProducts(RockSalt, Borax);
        RockSalt.addOreByProducts(Salt, Borax);
        Lepidolite.addOreByProducts(Boron);
        Marble.addOreByProducts(Calcite);

        //LV
        RedAlloy.setCableProperties(GTValues.V[0], 1, 0);
        Tin.setCableProperties(GTValues.V[0], 1, 1);
        Cobalt.setCableProperties(GTValues.V[0], 2, 2);
        Lead.setCableProperties(GTValues.V[0], 2, 2);
        Zinc.setCableProperties(GTValues.V[0], 1, 1);
        SolderingAlloy.setCableProperties(GTValues.V[0], 1, 1);

        //MV
        Copper.setCableProperties(GTValues.V[1], 1, 2);
        Iron.setCableProperties(GTValues.V[1], 2, 3);
        Nickel.setCableProperties(GTValues.V[1], 3, 3);
        Cupronickel.setCableProperties(GTValues.V[1], 2, 3);
        AnnealedCopper.setCableProperties(GTValues.V[1], 1, 1);

        //HV
        Kanthal.setCableProperties(GTValues.V[2], 4, 2);
        Gold.setCableProperties(GTValues.V[2], 2, 2);
        Electrum.setCableProperties(GTValues.V[2], 3, 2);
        Silver.setCableProperties(GTValues.V[2], 1, 1);

        //EV
        Nichrome.setCableProperties(GTValues.V[3], 3, 3);
        Steel.setCableProperties(GTValues.V[3], 2, 2);
        Titanium.setCableProperties(GTValues.V[3], 4, 2);
        Aluminium.setCableProperties(GTValues.V[3], 1, 1);

        //IV
        Graphene.setCableProperties(GTValues.V[4], 1, 1);
        Osmium.setCableProperties(GTValues.V[4], 4, 2);
        Platinum.setCableProperties(GTValues.V[4], 2, 1);
        TungstenSteel.setCableProperties(GTValues.V[4], 3, 2);
        Tungsten.setCableProperties(GTValues.V[4], 2, 2);
        Naquadah.setCableProperties(GTValues.V[4], 2, 3);

        //LuV
        HSSG.setCableProperties(GTValues.V[5], 4, 2);
        NiobiumTitanium.setCableProperties(GTValues.V[5], 4, 3);
        VanadiumGallium.setCableProperties(GTValues.V[5], 4, 3);
        YttriumBariumCuprate.setCableProperties(GTValues.V[5], 4, 4);
        NaquadahAlloy.setCableProperties(GTValues.V[5], 2, 2);

        //UV (Superconductor material initialized in MetaBlocks.class
        NaquadahEnriched.setCableProperties(GTValues.V[6], 1, 2);
        Darmstadtium.setCableProperties(GTValues.V[6], 2, 2);

        //Fluid Pipes
        Polyethylene.setFluidPipeProperties(2000, 400, true);
        Polytetrafluoroethylene.setFluidPipeProperties(3000, 600, true);
        Bronze.setFluidPipeProperties(300, 2000, true);
        Steel.setFluidPipeProperties(500, 2500, true);
        StainlessSteel.setFluidPipeProperties(1500, 3500, true);
        Titanium.setFluidPipeProperties(2000, 5000, true);
        Ultimet.setFluidPipeProperties(3000, 4500, true);
        TungstenSteel.setFluidPipeProperties(4000, 7500, true);
        Acrylic.setFluidPipeProperties(8000, 9000, true);
        Polychlorotrifluoroethylene.setFluidPipeProperties(12000, 16000, true);
    }

}
