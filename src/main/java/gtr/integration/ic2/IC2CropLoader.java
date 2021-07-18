package gtr.integration.ic2;

import appeng.util.item.ItemList;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.util.GTLog;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class IC2CropLoader {
    public static void run() {
        try {
            /*Tags:
             * Vines = Tendrilly
             * Edible = Food
             *
             */


            /*
            ItemStack[] tI = {
                ItemList.Crop_Drop_Indigo.get(4L), ItemList.Crop_Drop_MilkWart.get(4L), new ItemStack(Blocks.BROWN_MUSHROOM, 4), new ItemStack(Blocks.RED_MUSHROOM, 4)
            };
            new GTBaseCrop(124, "Indigo", "Eloraam", tI[0], 2, 4, 0, 1, 4, 1, 1, 0, 4, 0, new String[] {
                "Flower",
                "Blue",
                "Ingredient"
            }, ItemList.Crop_Drop_Indigo.get(1L), null);
            new GTBaseCrop(125, "Flax", "Eloraam", null, 2, 4, 0, 1, 4, 1, 1, 2, 0, 1, new String[] {
                "Silk",
                "Tendrilly",
                "Addictive"
            }, new ItemStack(Items.STRING, 1), null);
            new GTBaseCrop(126, "Oilberries", "Spacetoad", null, 9, 4, 0, 1, 4, 6, 1, 2, 1, 12, new String[] {
                "Fire",
                "Dark",
                "Reed",
                "Rotten",
                "Coal",
                "Oil"
            }, ItemList.Crop_Drop_OilBerry.get(1L), null);
            new GTBaseCrop(127, "Bobsyeruncleranks", "GenerikB", null, 11, 4, 0, 1, 4, 4, 0, 8, 2, 9, new String[] {
                "Shiny",
                "Tendrilly",
                "Emerald",
                "Berylium",
                "Crystal"
            }, Materials.Emerald, ItemList.Crop_Drop_BobsYerUncleRanks.get(1L), new ItemStack[] {
                new ItemStack(Items.EMERALD, 1)
            });*/
            new GTBaseCrop(128, "Diareed", "Direwolf20", null, 12, 4, 0, 1, 4, 5, 0, 10, 2, 10, new String[] {
                "Fire",
                "Shiny",
                "Reed",
                "Coal",
                "Diamond",
                "Crystal"
            }, Materials.Diamond, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Diamond, 1), new ItemStack[] {
                new ItemStack(Items.DIAMOND, 1)
            });

            new GTBaseCrop(129, "TEST", "me lmao", null, 12, 4, 0, 1, 4, 5, 0, 10, 2, 10, new String[] {
                "Test1"
            }, new ItemStack(Blocks.COBBLESTONE), new ItemStack[] {
                new ItemStack(Blocks.STONE)
            });


            /*


            new GTBaseCrop(129, "Withereed", "CovertJaguar", null, 8, 4, 0, 1, 4, 2, 0, 4, 1, 3, new String[] {
                "Fire",
                "Undead",
                "Reed",
                "Coal",
                "Rotten",
                "Wither"
            }, Materials.Coal, OreDictUnifier.get(OrePrefix.dust, Materials.Coal, 1), new ItemStack[] {
                new ItemStack(Items.COAL, 1), new ItemStack(Items.COAL, 1)
            });
            new GTBaseCrop(130, "Blazereed", "Mr. Brain", null, 6, 4, 0, 1, 4, 0, 4, 1, 0, 0, new String[] {
                "Fire",
                "Blaze",
                "Reed",
                "Sulfur"
            }, new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack[] {
                new ItemStack(Items.BLAZE_ROD, 1)
            });
            new GTBaseCrop(131, "Eggplant", "Link", null, 6, 3, 900, 2, 3, 0, 4, 1, 0, 0, new String[] {
                "Chicken",
                "Egg",
                "Food",
                "Feather",
                "Flower",
                "Addictive"
            }, new ItemStack(Items.EGG, 1), new ItemStack[] {
                new ItemStack(Items.CHICKEN, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.FEATHER, 1)
            });
            new GTBaseCrop(132, "Corium", "Gregorius Techneticies", null, 6, 4, 0, 1, 4, 0, 2, 3, 1, 0, new String[] {
                "Cow",
                "Silk",
                "Tendrilly"
            }, new ItemStack(Items.LEATHER, 1), null);
            new GTBaseCrop(133, "Corpseplant", "Mr. Kenny", null, 5, 4, 0, 1, 4, 0, 2, 1, 0, 3, new String[] {
                "Toxic",
                "Undead",
                "Tendrilly",
                "Food",
                "Rotten"
            }, new ItemStack(Items.ROTTEN_FLESH, 1), new ItemStack[] {
                ItemList.Dye_Bonemeal.get(1L), ItemList.Dye_Bonemeal.get(1L), new ItemStack(Items.BONE, 1)
            });
            new GTBaseCrop(134, "Creeperweed", "General Spaz", null, 7, 4, 0, 1, 4, 3, 0, 5, 1, 3, new String[] {
                "Creeper",
                "Tendrilly",
                "Explosive",
                "Fire",
                "Sulfur",
                "Saltpeter",
                "Coal"
            }, OreDictUnifier.get(OrePrefix.dust, Materials.Gunpowder, 1), null);
            new GTBaseCrop(135, "Enderbloom", "RichardG", null, 10, 4, 0, 1, 4, 5, 0, 2, 1, 6, new String[] {
                "Ender",
                "Flower",
                "Shiny"
            }, OreDictUnifier.get(OrePrefix.dust, Materials.EnderPearl, 1), new ItemStack[] {
                new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.ENDER_EYE, 1)
            });
            new GTBaseCrop(136, "Meatrose", "VintageBeef", null, 7, 4, 1500, 1, 4, 0, 4, 1, 3, 0, new String[] {
                "Food",
                "Flower",
                "Cow",
                "Fish",
                "Chicken",
                "Pig"
            }, new ItemStack(Items.DYE, 1, 9), new ItemStack[] {
                new ItemStack(Items.BEEF, 1), new ItemStack(Items.PORKCHOP, 1), new ItemStack(Items.CHICKEN, 1), new ItemStack(Items.FISH, 1)
            });

            new GTBaseCrop(137, "Milkwart", "Mr. Brain", tI[1], 6, 3, 900, 1, 3, 0, 3, 0, 1, 0, new String[] {
                "Food",
                "Milk",
                "Cow"
            }, ItemList.Crop_Drop_MilkWart.get(1L), null);
            new GTBaseCrop(138, "Slimeplant", "Neowulf", null, 6, 4, 0, 3, 4, 3, 0, 0, 0, 2, new String[] {
                "Slime",
                "Bouncy",
                "Sticky",
                "Bush"
            }, new ItemStack(Items.SLIME_BALL, 1), null);
            new GTBaseCrop(139, "Spidernip", "Mr. Kenny", null, 4, 4, 600, 1, 4, 2, 1, 4, 1, 3, new String[] {
                "Toxic",
                "Silk",
                "Spider",
                "Flower",
                "Ingredient",
                "Addictive"
            }, new ItemStack(Items.STRING, 1), new ItemStack[] {
                new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Blocks.WEB, 1)
            });
            new GTBaseCrop(140, "Tearstalks", "Neowulf", null, 8, 4, 0, 1, 4, 1, 2, 0, 0, 0, new String[] {
                "Healing",
                "Nether",
                "Ingredient",
                "Reed",
                "Ghast"
            }, new ItemStack(Items.GHAST_TEAR, 1), null);
            new GTBaseCrop(141, "Tine", "Gregorius Techneticies", null, 5, 3, 0, 2, 3, 2, 0, 3, 0, 0, new String[] {
                "Shiny",
                "Metal",
                "Pine",
                "Tin",
                "Bush"
            }, Materials.Tin, ItemList.Crop_Drop_Tine.get(1L), null);
            new GTBaseCrop(142, "Coppon", "Mr. Brain", null, 6, 3, 0, 2, 3, 2, 0, 1, 1, 1, new String[] {
                "Shiny",
                "Metal",
                "Cotton",
                "Copper",
                "Bush"
            }, Materials.Copper, ItemList.Crop_Drop_Coppon.get(1L), null);
            new GTBaseCrop(143, "Brown Mushrooms", "Mr. Brain", tI[2], 1, 3, 0, 1, 3, 0, 2, 0, 0, 2, new String[] {
                "Food",
                "Mushroom",
                "Ingredient"
            }, new ItemStack(Blocks.BROWN_MUSHROOM, 1), null);
            new GTBaseCrop(144, "Red Mushrooms", "Mr. Kenny", tI[3], 1, 3, 0, 1, 3, 0, 1, 3, 0, 2, new String[] {
                "Toxic",
                "Mushroom",
                "Ingredient"
            }, new ItemStack(Blocks.RED_MUSHROOM, 1), null);
            new GTBaseCrop(145, "Argentia", "Eloraam", null, 7, 4, 0, 3, 4, 2, 0, 1, 0, 0, new String[] {
                "Shiny",
                "Metal",
                "Silver",
                "Reed"
            }, Materials.Silver, ItemList.Crop_Drop_Argentia.get(1L), null);
            new GTBaseCrop(146, "Plumbilia", "KingLemming", null, 6, 4, 0, 3, 4, 2, 0, 3, 1, 1, new String[] {
                "Heavy",
                "Metal",
                "Lead",
                "Reed"
            }, Materials.Lead, ItemList.Crop_Drop_Plumbilia.get(1L), null);
            new GTBaseCrop(147, "Steeleafranks", "Benimatic", null, 10, 4, 0, 1, 4, 3, 0, 7, 2, 8, new String[] {
                "Metal",
                "Tendrilly",
                "Iron"
            }, OreDictUnifier.get(OrePrefix.dust, Materials.Steeleaf, 1L), new ItemStack[] {
                OreDictUnifier.get(OrePrefix.ingot, Materials.Steeleaf, 1L)
            });
            new GTBaseCrop(148, "Liveroots", "Benimatic", null, 8, 4, 0, 1, 4, 2, 0, 5, 2, 6, new String[] {
                "Wood",
                "Tendrilly"
            }, OreDictUnifier.get(OrePrefix.dust, Materials.LiveRoot, 1L), new ItemStack[] {
                ItemList.TF_LiveRoot.get(1L)
            });
            new GTBaseCrop(149, "Trollplant", "unknown", null, 6, 5, 1000, 1, 4, 0, 0, 5, 2, 8, new String[] {
                "Troll",
                "Bad",
                "Scrap"
            }, OreDictUnifier.get(OrePrefix.gem, Materials.FoolsRuby, 1L), new ItemStack[] {
                ItemList.IC2_Plantball.get(1), ItemList.IC2_Scrap.get(1), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Plutonium241, 1L)
            });
            new GTBaseCrop(150, "Lazulia", "unknown", null, 7, 4, 0, 2, 4, 4, 2, 5, 7, 4, new String[] {
                "Shiny",
                "Bad",
                "Crystal",
                "Lapis"
            }, Materials.Lapis, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Lapis, 1), null);
            new GTBaseCrop(151, "Glowheat", "unknown", null, 10, 7, 0, 5, 7, 3, 3, 3, 5, 4, new String[] {
                "Light",
                "Shiny",
                "Crystal"
            }, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Glowstone, 1), null);
            new GTBaseCrop(153, "Fertilia", "unknown", null, 3, 4, 0, 1, 4, 2, 3, 5, 4, 8, new String[] {
                "Growth",
                "Healing",
                "Flower"
            }, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Calcite, 1), new ItemStack[] {
                ItemList.IC2_Fertilizer.get(1), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Apatite, 1), OreDictUnifier.get(OrePrefix.dust, Materials.Phosphate, 1L)
            });
            new GTBaseCrop(154, "Bauxia", "unknown", null, 6, 3, 0, 2, 3, 5, 0, 2, 3, 3, new String[] {
                "Metal",
                "Aluminium",
                "Reed",
                "Aluminium"
            }, Materials.Aluminium, ItemList.Crop_Drop_Bauxite.get(1), null);
            new GTBaseCrop(155, "Titania", "unknown", null, 9, 3, 0, 2, 3, 5, 0, 3, 3, 1, new String[] {
                "Metal",
                "Heavy",
                "Reed",
                "Titanium"
            }, Materials.Titanium, ItemList.Crop_Drop_Ilmenite.get(1), null);
            new GTBaseCrop(156, "Reactoria", "unknown", null, 12, 4, 0, 2, 4, 4, 0, 1, 2, 1, new String[] {
                "Radioactive",
                "Metal",
                "Danger",
                "Uranium"
            }, Materials.Uranium, ItemList.Crop_Drop_Pitchblende.get(1), new ItemStack[] {
                ItemList.Crop_Drop_Uraninite.get(1)
            });
            new GTBaseCrop(157, "God of Thunder", "unknown", null, 9, 4, 0, 2, 4, 3, 0, 5, 1, 2, new String[] {
                "Radioactive",
                "Metal",
                "Coal",
                "Thorium"
            }, Materials.Thorium, ItemList.Crop_Drop_Thorium.get(1), null);
            new GTBaseCrop(158, "Transformium", "unknown", null, 12, 4, 2500, 1, 4, 6, 2, 1, 6, 1, new String[] {
                "Transform",
                "Coal",
                "Reed"
            }, ItemList.Crop_Drop_UUABerry.get(1L), new ItemStack[] {
                ItemList.Crop_Drop_UUABerry.get(1L), ItemList.Crop_Drop_UUABerry.get(1L), ItemList.Crop_Drop_UUABerry.get(1L), ItemList.Crop_Drop_UUABerry.get(1L), ItemList.Crop_Drop_UUMBerry.get(1L)
            });
            new GTBaseCrop(159, "Starwart", "unknown", null, 12, 4, 4000, 1, 4, 2, 0, 0, 1, 0, new String[] {
                "Wither",
                "Nether",
                "Undead",
                "Netherstar"
            }, Materials.NetherStar, OreDictUnifier.get(OrePrefix.dustSmall, Materials.Coal, 1), new ItemStack[] {
                OreDictUnifier.get(OrePrefix.gem, Materials.Coal, 1), new ItemStack(Items.SKULL, 1), new ItemStack(Items.SKULL, 1, 1), new ItemStack(Items.SKULL, 1, 1), OreDictUnifier.get(OrePrefix.dustTiny, Materials.NetherStar, 1)
            });
            new GTBaseCrop(160, "Zomplant", "unknown", null, 3, 4, 0, 1, 4, 1, 3, 4, 2, 6, new String[] {
                "Zombie",
                "Rotten",
                "Undead"
            }, new ItemStack(Items.ROTTEN_FLESH), null);
            new GTBaseCrop(161, "Nickelback", "unknown", null, 5, 3, 0, 2, 3, 3, 0, 1, 2, 2, new String[] {
                "Metal",
                "Fire",
                "Alloy"
            }, Materials.Nickel, ItemList.Crop_Drop_Nickel.get(1), null);
            new GTBaseCrop(162, "Galvania", "unknown", null, 6, 3, 0, 2, 3, 3, 0, 2, 2, 3, new String[] {
                "Metal",
                "Alloy",
                "Bush"
            }, Materials.Zinc, ItemList.Crop_Drop_Zinc.get(1), null);
            new GTBaseCrop(163, "Evil Ore", "unknown", null, 8, 4, 0, 3, 4, 4, 0, 2, 1, 3, new String[] {
                "Crystal",
                "Fire",
                "Nether"
            }, OreDictUnifier.get(OrePrefix.dustTiny, Materials.NetherQuartz, 1), new ItemStack[] {
                OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz, 1), OreDictUnifier.get(OrePrefix.dustTiny, Materials.CertusQuartz, 1),
            });
            new GTBaseCrop(164, "Olivia", "unknown", null, 2, 4, 0, 3, 4, 1, 0, 1, 4, 0, new String[] {
                "Crystal",
                "Shiny",
                "Processing",
                "Olivine"
            }, Materials.Olivine, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Olivine, 1), new ItemStack[] {
                OreDictUnifier.get(OrePrefix.gem, Materials.Olivine, 1),
            });
            new GTBaseCrop(165, "Sapphirum", "unknown", null, 4, 4, 0, 3, 4, 1, 0, 1, 5, 0, new String[] {
                "Crystal",
                "Shiny",
                "Metal",
                "Sapphire"
            }, Materials.Sapphire, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Sapphire, 1), new ItemStack[] {
                OreDictUnifier.get(OrePrefix.gem, Materials.Sapphire, 1),
            });
            new GTBaseCrop(166, "Pyrolusium", "unknown", null, 12, 3, 0, 2, 3, 1, 0, 1, 1, 0, new String[] {
                "Metal",
                "Clean",
                "Bush",
                "Manganese"
            }, Materials.Manganese, ItemList.Crop_Drop_Manganese.get(1), null);
            new GTBaseCrop(167, "Scheelinium", "unknown", null, 12, 3, 0, 2, 3, 3, 0, 1, 1, 0, new String[] {
                "Metal",
                "Hard",
                "Bush",
                "Tungsten"
            }, Materials.Tungsten, ItemList.Crop_Drop_Scheelite.get(1), null);
            new GTBaseCrop(168, "Platina", "unknown", null, 11, 4, 0, 1, 4, 3, 0, 0, 3, 0, new String[] {
                "Metal",
                "Shiny",
                "Reed",
                "Platinum"
            }, Materials.Platinum, ItemList.Crop_Drop_Platinum.get(1), null);
            new GTBaseCrop(169, "Quantaria", "unknown", null, 12, 4, 1000, 1, 4, 4, 0, 0, 1, 0, new String[] {
                "Metal",
                "Iridium",
                "Reed"
            }, Materials.Iridium, ItemList.Crop_Drop_Iridium.get(1), new ItemStack[] {
                ItemList.Crop_Drop_Osmium.get(1)
            });
            new GTBaseCrop(170, "Stargatium", "unknown", null, 12, 4, 1500, 1, 4, 4, 0, 0, 2, 0, new String[] {
                "Metal",
                "Heavy",
                "Alien",
                "Naquadah"
            }, Materials.Naquadah, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Endstone, 1), new ItemStack[] {
                ItemList.Crop_Drop_Naquadah.get(1)
            });
            new GTBaseCrop(171, "Lemon", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Yellow",
                "Sour"
            }, ItemList.Crop_Drop_Lemon.get(1), null);
            new GTBaseCrop(172, "Chilly", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Red",
                "Spicy"
            }, ItemList.Crop_Drop_Chilly.get(1), null);
            new GTBaseCrop(173, "Tomato", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Red"
            }, ItemList.Crop_Drop_Tomato.get(1), new ItemStack[] {
                ItemList.Crop_Drop_MTomato.get(1)
            });
            new GTBaseCrop(174, "Grape", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Purple"
            }, ItemList.Crop_Drop_Grapes.get(1), null);
            new GTBaseCrop(175, "Onion", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Brown"
            }, ItemList.Crop_Drop_Onion.get(1), null);
            new GTBaseCrop(176, "Cucumber", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Green"
            }, ItemList.Crop_Drop_Cucumber.get(1), null);
            new GTBaseCrop(177, "Tea", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Green",
                "Ingredient"
            }, ItemList.Crop_Drop_TeaLeaf.get(1), null);
            new GTBaseCrop(178, "Rape", "unknown", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Yellow",
                "Oil"
            }, ItemList.Crop_Drop_Rape.get(1), null);
            new GTBaseCrop(179, "Micadia", "bartimaeusnek", null, 9, 3, 0, 2, 3, 2, 0, 3, 0, 0, new String[] {
                "Metal",
                "Pine",
                "Mica",
                "Bush"
            }, Materials.Mica, ItemList.Crop_Drop_Mica.get(1L), null);*/
        } catch (Throwable e) {
            GTLog.logger.error("GTR: Failed to register IC2 crops");
        }
    }
}
