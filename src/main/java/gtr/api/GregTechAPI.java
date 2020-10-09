package gtr.api;

import gtr.api.block.machines.BlockMachine;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.DustMaterial;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.unification.ore.StoneType;
import gtr.api.util.BaseCreativeTab;
import gtr.api.util.GTControlledRegistry;
import gtr.api.util.IBlockOre;
import gtr.common.items.MetaItems;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class GregTechAPI {

    public static BlockMachine MACHINE;
    public static final Map<DustMaterial, Map<StoneType, IBlockOre>> oreBlockTable = new HashMap<>();

    public static final BaseCreativeTab TAB_GREGTECH =
        new BaseCreativeTab(GTValues.MODID + ".main", () -> MetaItems.BATTERY_HULL_HV.getStackForm(), true);
    public static final BaseCreativeTab TAB_GREGTECH_MATERIALS =
        new BaseCreativeTab(GTValues.MODID + ".materials", () -> OreDictUnifier.get(OrePrefix.ingot, Materials.Aluminium), true);
    public static final BaseCreativeTab TAB_GREGTECH_ORES =
        new BaseCreativeTab(GTValues.MODID + ".ores", () -> MetaItems.JACKHAMMER.getStackForm(), true);

    public static final GTControlledRegistry<ResourceLocation, MetaTileEntity> META_TILE_ENTITY_REGISTRY = new GTControlledRegistry<>(Short.MAX_VALUE);

    public static <T extends MetaTileEntity> T registerMetaTileEntity(int id, T sampleMetaTileEntity) {
        META_TILE_ENTITY_REGISTRY.register(id, sampleMetaTileEntity.metaTileEntityId, sampleMetaTileEntity);
        return sampleMetaTileEntity;
    }

}