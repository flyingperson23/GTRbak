package gtr.integration.ic2;

import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.util.GTLog;
import gtr.common.items.MetaItems;
import ic2.api.item.IC2Items;
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
            }, IC2Items.getItem("crop_res", "oil_berry"), null);
            new GTBaseCrop(127, "Bobsyeruncleranks", "GenerikB", null, 11, 4, 0, 1, 4, 4, 0, 8, 2, 9, new String[] {
                "Shiny",
                "Tendrilly",
                "Emerald",
                "Berylium",
                "Crystal"
            }, Materials.Emerald, IC2Items.getItem("crop_res", "bobs_yer_uncle_ranks_berry"), new ItemStack[] {
                new ItemStack(Items.EMERALD, 1)
            });
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
                new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.BONE, 1)
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
            new GTBaseCrop(137, "Milkwart", "Mr. Brain", new ItemStack(IC2Items.getItem("crop_res", "milk_wart").getItem(), 4, IC2Items.getItem("crop_res", "milk_wart").getMetadata()), 6, 3, 900, 1, 3, 0, 3, 0, 1, 0, new String[] {
                "Food",
                "Milk",
                "Cow"
            }, IC2Items.getItem("crop_res", "milk_wart"), null);
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
            }, Materials.Tin, MetaItems.TINE_TWIG.getStackForm(), null);
            new GTBaseCrop(142, "Coppon", "Mr. Brain", null, 6, 3, 0, 2, 3, 2, 0, 1, 1, 1, new String[] {
                "Shiny",
                "Metal",
                "Cotton",
                "Copper",
                "Bush"
            }, Materials.Copper, MetaItems.COPPON_FIBER.getStackForm(), null);
            new GTBaseCrop(143, "Brown Mushrooms", "Mr. Brain", new ItemStack(Blocks.BROWN_MUSHROOM, 4), 1, 3, 0, 1, 3, 0, 2, 0, 0, 2, new String[] {
                "Food",
                "Mushroom",
                "Ingredient"
            }, new ItemStack(Blocks.BROWN_MUSHROOM, 1), null);
            new GTBaseCrop(144, "Red Mushrooms", "Mr. Kenny", new ItemStack(Blocks.RED_MUSHROOM, 4), 1, 3, 0, 1, 3, 0, 1, 3, 0, 2, new String[] {
                "Toxic",
                "Mushroom",
                "Ingredient"
            }, new ItemStack(Blocks.RED_MUSHROOM, 1), null);
            new GTBaseCrop(145, "Argentia", "Eloraam", null, 7, 4, 0, 3, 4, 2, 0, 1, 0, 0, new String[] {
                "Shiny",
                "Metal",
                "Silver",
                "Reed"
            }, Materials.Silver, MetaItems.ARGENTIA_LEAF.getStackForm(), null);
            new GTBaseCrop(146, "Plumbilia", "KingLemming", null, 6, 4, 0, 3, 4, 2, 0, 3, 1, 1, new String[] {
                "Heavy",
                "Metal",
                "Lead",
                "Reed"
            }, Materials.Lead, MetaItems.PLUMBILIA_LEAF.getStackForm(), null);
            new GTBaseCrop(149, "Trollplant", "unknown", null, 6, 5, 1000, 1, 4, 0, 0, 5, 2, 8, new String[] {
                "Troll",
                "Bad",
                "Scrap"
            }, OreDictUnifier.get(OrePrefix.gem, Materials.Ruby, 1), new ItemStack[] {
                IC2Items.getItem("crafting", "plant_ball"), IC2Items.getItem("crafting", "scrap"), IC2Items.getItem("nuclear", "small_plutonium")
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
                IC2Items.getItem("crop_res", "fertilizer"), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Apatite, 1), OreDictUnifier.get(OrePrefix.dust, Materials.Phosphate, 1)
            });
            new GTBaseCrop(154, "Bauxia", "unknown", null, 6, 3, 0, 2, 3, 5, 0, 2, 3, 3, new String[] {
                "Metal",
                "Aluminium",
                "Reed",
                "Aluminium"
            }, Materials.Aluminium, MetaItems.BAUXIA_LEAF.getStackForm(), null);
            new GTBaseCrop(155, "Titania", "unknown", null, 9, 3, 0, 2, 3, 5, 0, 3, 3, 1, new String[] {
                "Metal",
                "Heavy",
                "Reed",
                "Titanium"
            }, Materials.Titanium, MetaItems.TITANIA_LEAF.getStackForm(), null);
            new GTBaseCrop(156, "Reactoria", "unknown", null, 12, 4, 0, 2, 4, 4, 0, 1, 2, 1, new String[] {
                "Radioactive",
                "Metal",
                "Danger",
                "Uranium"
            }, Materials.Uranium, MetaItems.REACTORIA_LEAF.getStackForm(), new ItemStack[] {
                MetaItems.URANIUM_LEAF.getStackForm()
            });
            new GTBaseCrop(157, "God of Thunder", "unknown", null, 9, 4, 0, 2, 4, 3, 0, 5, 1, 2, new String[] {
                "Radioactive",
                "Metal",
                "Coal",
                "Thorium"
            }, Materials.Thorium, MetaItems.THUNDER_LEAF.getStackForm(), null);
            new GTBaseCrop(158, "Transformium", "unknown", null, 12, 4, 2500, 1, 4, 6, 2, 1, 6, 1, new String[] {
                "Transform",
                "Coal",
                "Reed"
            }, MetaItems.UUA_BERRY.getStackForm(), new ItemStack[] {
                MetaItems.UUA_BERRY.getStackForm(), MetaItems.UUA_BERRY.getStackForm(), MetaItems.UUA_BERRY.getStackForm(), MetaItems.UUA_BERRY.getStackForm(), MetaItems.UUM_BERRY.getStackForm()
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
            }, Materials.Nickel, MetaItems.NICKELBACK_LEAF.getStackForm(), null);
            new GTBaseCrop(162, "Galvania", "unknown", null, 6, 3, 0, 2, 3, 3, 0, 2, 2, 3, new String[] {
                "Metal",
                "Alloy",
                "Bush"
            }, Materials.Zinc, MetaItems.GALVANIA_LEAF.getStackForm(), null);
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
            }, Materials.Manganese, MetaItems.PYROLUSIUM_LEAF.getStackForm(), null);
            new GTBaseCrop(167, "Scheelinium", "unknown", null, 12, 3, 0, 2, 3, 3, 0, 1, 1, 0, new String[] {
                "Metal",
                "Hard",
                "Bush",
                "Tungsten"
            }, Materials.Tungsten, MetaItems.SCHEELINIUM_LEAF.getStackForm(), null);
            new GTBaseCrop(168, "Platina", "unknown", null, 11, 4, 0, 1, 4, 3, 0, 0, 3, 0, new String[] {
                "Metal",
                "Shiny",
                "Reed",
                "Platinum"
            }, Materials.Platinum, MetaItems.PLATINA_LEAF.getStackForm(), null);
            new GTBaseCrop(169, "Quantaria", "unknown", null, 12, 4, 1000, 1, 4, 4, 0, 0, 1, 0, new String[] {
                "Metal",
                "Iridium",
                "Reed"
            }, Materials.Iridium, MetaItems.QUANTARIA_LEAF_1.getStackForm(), new ItemStack[] {
                MetaItems.QUANTARIA_LEAF_2.getStackForm()
            });
            new GTBaseCrop(170, "Stargatium", "unknown", null, 12, 4, 1500, 1, 4, 4, 0, 0, 2, 0, new String[] {
                "Metal",
                "Heavy",
                "Alien",
                "Naquadah"
            }, Materials.Naquadah, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Endstone, 1), new ItemStack[] {
                MetaItems.STARGATIUM_LEAF.getStackForm()
            });

            new GTBaseCrop(171, "Ferru Leaf", "IC2 Team", null, 6, 4, 1200, 1, 4, 2, 0, 0, 1, 0, new String[] {
                "Gray", "Leaves", "Metal"
            }, Materials.Iron, MetaItems.FERRU_LEAF.getStackForm(), null);

            new GTBaseCrop(175, "Aurelia Leaf", "IC2 Team", null, 6, 5, 1800, 1, 5, 2, 0, 0, 2, 0, new String[] {
                "Gold", "Leaves", "Metal"
            }, Materials.Gold, MetaItems.AURELIA_LEAF.getStackForm(), null);

            new GTBaseCrop(177, "Canola", "cover yourself in oil", null, 4, 4, 0, 1, 4, 1, 1, 0, 2, 0, new String[] {
                "Food",
                "Yellow",
                "Oil"
            }, MetaItems.CANOLA_SEED.getStackForm(), null);
        } catch (Throwable e) {
            GTLog.logger.error("GTR: Failed to register IC2 crops");
            e.printStackTrace();
        }
    }
}
