package gtr.loaders.recipe;

import gtr.api.GTValues;
import gtr.api.items.OreDictNames;
import gtr.api.unification.material.MarkerMaterials;
import gtr.api.unification.material.MarkerMaterials.Tier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.stack.UnificationEntry;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gtr.api.GTValues.W;

public enum CraftingComponent {

    CIRCUIT {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.circuit, Tier.Basic);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.circuit, Tier.Good);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.circuit, Tier.Advanced);
                default:
                    return new UnificationEntry(OrePrefix.circuit, Tier.Elite);

            }
        }
    },
    PUMP {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.ELECTRIC_PUMP_LV;
                case GTValues.MV:
                    return MetaItems.ELECTRIC_PUMP_MV;
                case GTValues.HV:
                    return MetaItems.ELECTRIC_PUMP_HV;
                case GTValues.EV:
                    return MetaItems.ELECTRIC_PUMP_EV;
                default:
                    return MetaItems.ELECTRIC_PUMP_IV;

            }
        }
    },
    CABLE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold);
                case GTValues.EV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium);
                case GTValues.IV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.Platinum);
                case GTValues.LuV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.NiobiumTitanium);
                case GTValues.UV:
                    return new UnificationEntry(OrePrefix.cableGtSingle, Materials.Naquadah);
                default:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy);
            }
        }
    },
    WIRE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.wireGtSingle, Materials.Gold);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.wireGtSingle, Materials.Silver);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.wireGtSingle, Materials.Electrum);
                case GTValues.EV:
                    return new UnificationEntry(OrePrefix.wireGtSingle, Materials.Platinum);
                default:
                    return new UnificationEntry(OrePrefix.wireGtSingle, Materials.Osmium);
            }
        }
    },
    CABLE_QUAD {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Tin);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Copper);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Gold);
                case GTValues.EV:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Aluminium);
                case GTValues.IV:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Platinum);
                case GTValues.LuV:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium);
                case GTValues.UV:
                default:
                    return new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Naquadah);
            }
        }
    },
    HULL {
        @Override
        public Object getIngredient(int tier) {
            return MetaTileEntities.HULL[tier].getStackForm();
        }
    },
    WORSE_HULL {
        @Override
        public Object getIngredient(int tier) {
            return MetaTileEntities.HULL[tier - 1].getStackForm();
        }
    },
    PIPE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.pipeMedium, Materials.Bronze);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.pipeMedium, Materials.Steel);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.pipeMedium, Materials.StainlessSteel);
                case GTValues.EV:
                    return new UnificationEntry(OrePrefix.pipeMedium, Materials.Polyethylene);
                case GTValues.IV:
                    return new UnificationEntry(OrePrefix.pipeMedium, Materials.Polytetrafluoroethylene);
                default:
                    return new UnificationEntry(OrePrefix.pipeMedium, Materials.Wood);
            }
        }
    },
    GLASS {
        @Override
        public Object getIngredient(int tier) {
            return new ItemStack(Blocks.GLASS, 1, W);
        }
    },
    PLATE {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(OrePrefix.plate, MAIN_MATERIAL.get(tier));
        }
    },
    MOTOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.ELECTRIC_MOTOR_LV;
                case GTValues.MV:
                    return MetaItems.ELECTRIC_MOTOR_MV;
                case GTValues.HV:
                    return MetaItems.ELECTRIC_MOTOR_HV;
                case GTValues.EV:
                    return MetaItems.ELECTRIC_MOTOR_EV;
                default:
                    return MetaItems.ELECTRIC_MOTOR_IV;
            }
        }
    },
    ROTOR {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(OrePrefix.rotor, MAIN_MATERIAL.get(tier));
        }
    },
    SENSOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.SENSOR_LV;
                case GTValues.MV:
                    return MetaItems.SENSOR_MV;
                case GTValues.HV:
                    return MetaItems.SENSOR_HV;
                case GTValues.EV:
                    return MetaItems.SENSOR_EV;
                default:
                    return MetaItems.SENSOR_IV;

            }
        }
    },
    GRINDER {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.gem, Materials.Diamond);
                default:
                    return OreDictNames.craftingGrinder;
            }
        }
    },
    DIAMOND {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(OrePrefix.gem, Materials.Diamond);
        }
    },
    PISTON {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.ELECTRIC_PISTON_LV;
                case GTValues.MV:
                    return MetaItems.ELECTRIC_PISTON_MV;
                case GTValues.HV:
                    return MetaItems.ELECTRIC_PISTON_HV;
                case GTValues.EV:
                    return MetaItems.ELECTRIC_PISTON_EV;
                default:
                    return MetaItems.ELECTRIC_PISTON_IV;

            }
        }
    },
    EMITTER {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.EMITTER_LV;
                case GTValues.MV:
                    return MetaItems.EMITTER_MV;
                case GTValues.HV:
                    return MetaItems.EMITTER_HV;
                case GTValues.EV:
                    return MetaItems.EMITTER_EV;
                default:
                    return MetaItems.EMITTER_IV;

            }
        }
    },
    CONVEYOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.CONVEYOR_MODULE_LV;
                case GTValues.MV:
                    return MetaItems.CONVEYOR_MODULE_MV;
                case GTValues.HV:
                    return MetaItems.CONVEYOR_MODULE_HV;
                case GTValues.EV:
                    return MetaItems.CONVEYOR_MODULE_EV;
                default:
                    return MetaItems.CONVEYOR_MODULE_IV;

            }
        }
    },
    ROBOT_ARM {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.ROBOT_ARM_LV;
                case GTValues.MV:
                    return MetaItems.ROBOT_ARM_MV;
                case GTValues.HV:
                    return MetaItems.ROBOT_ARM_HV;
                case GTValues.EV:
                    return MetaItems.ROBOT_ARM_EV;
                default:
                    return MetaItems.ROBOT_ARM_IV;

            }
        }
    },
    COIL_HEATING {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Cupronickel);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Kanthal);
                case GTValues.EV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Nichrome);
                case GTValues.IV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.TungstenSteel);
                case GTValues.LuV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.HSSG);
                case GTValues.UV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Naquadah);
                case 7:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.NaquadahAlloy);
                default:
                    return new UnificationEntry(OrePrefix.wireGtOctal, Materials.Nichrome);
            }
        }
    },
    COIL_ELECTRIC {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Tin);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.AnnealedCopper);
                default:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.YttriumBariumCuprate);
            }
        }
    },
    STICK_MAGNETIC {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.stick, Materials.IronMagnetic);
                case GTValues.MV:
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic);
                case GTValues.EV:
                case GTValues.IV:
                    return new UnificationEntry(OrePrefix.stick, Materials.NeodymiumMagnetic);
                case GTValues.LuV:
                case GTValues.UV:
                    return new UnificationEntry(OrePrefix.stickLong, Materials.NeodymiumMagnetic);
                default:
                    return new UnificationEntry(OrePrefix.block, Materials.NeodymiumMagnetic);
            }
        }
    },
    STICK_DISTILLATION {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(OrePrefix.stick, Materials.Blaze);
        }
    },
    FIELD_GENERATOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return MetaItems.FIELD_GENERATOR_LV;
                case GTValues.MV:
                    return MetaItems.FIELD_GENERATOR_MV;
                case GTValues.HV:
                    return MetaItems.FIELD_GENERATOR_HV;
                case GTValues.EV:
                    return MetaItems.FIELD_GENERATOR_EV;
                default:
                    return MetaItems.FIELD_GENERATOR_IV;
            }
        }
    },
    COIL_HEATING_DOUBLE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper);
                case GTValues.MV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Cupronickel);
                case GTValues.HV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Kanthal);
                case GTValues.EV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Nichrome);
                case GTValues.IV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.TungstenSteel);
                case GTValues.LuV:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.HSSG);
                default:
                    return new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy);
            }
        }
    },
    STICK {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(OrePrefix.stick, MAIN_MATERIAL.get(tier));
        }
    },
        MAIN_MATERIAL {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case GTValues.LV:
                    return Materials.Steel;
                case GTValues.MV:
                    return Materials.Aluminium;
                case GTValues.HV:
                    return Materials.StainlessSteel;
                case GTValues.EV:
                    return Materials.Titanium;
                case GTValues.IV:
                    return Materials.TungstenSteel;
                case GTValues.LuV:
                    return Materials.HSSG;
                default:
                    return Materials.Osmiridium;
            }
        }

        @Override
        public Material get(int tier) {
            return (Material) getIngredient(tier);
        }
    },
    RUBBER {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(OrePrefix.dust, get(tier));
        }

        @Override
        public Material get(int tier) {
            switch (tier) {
                case GTValues.LV:
                case GTValues.MV:
                    return Materials.Rubber;
                case GTValues.HV:
                    return Materials.StyreneButadieneRubber;
                case GTValues.EV:
                default:
                    return Materials.SiliconeRubber;
            }
        }
    };

    abstract public Object getIngredient(int tier);

    public Material get(int tier) {
        return Materials.Darmstadtium;
    }
}
