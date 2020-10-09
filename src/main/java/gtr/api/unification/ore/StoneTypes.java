package gtr.api.unification.ore;

import gtr.api.GTValues;
import gtr.api.unification.material.Materials;
import gtr.common.blocks.BlockGranite;
import gtr.common.blocks.BlockGranite.GraniteVariant;
import gtr.common.blocks.BlockMineral;
import gtr.common.blocks.BlockMineral.MineralVariant;
import gtr.common.blocks.MetaBlocks;
import gtr.common.blocks.StoneBlock.ChiselingVariant;
import net.minecraft.block.*;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import static gtr.api.unification.ore.StoneType.AFFECTED_BY_GRAVITY;
import static gtr.api.unification.ore.StoneType.UNBREAKABLE;

public class StoneTypes {

    public static StoneType STONE = new StoneType(0, "stone", new ResourceLocation("blocks/stone"), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", 0,
        () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, EnumType.STONE),
        state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE);


    public static StoneType GRAVEL = new StoneType(4, "gravel", new ResourceLocation("blocks/gravel"), SoundType.SAND, OrePrefix.oreGravel, Materials.Flint, "shovel", AFFECTED_BY_GRAVITY,
        Blocks.GRAVEL::getDefaultState,
        state -> state.getBlock() instanceof BlockGravel);

    public static StoneType BEDROCK = new StoneType(5, "bedrock", new ResourceLocation("blocks/bedrock"), SoundType.STONE, OrePrefix.ore, Materials.Stone, "pickaxe", UNBREAKABLE,
        Blocks.BEDROCK::getDefaultState,
        state -> state.getBlock() == Blocks.BEDROCK);

    public static StoneType NETHERRACK = new StoneType(6, "netherrack", new ResourceLocation("blocks/netherrack"), SoundType.STONE, OrePrefix.oreNetherrack, Materials.Netherrack, "pickaxe", 0,
        Blocks.NETHERRACK::getDefaultState,
        state -> state.getBlock() == Blocks.NETHERRACK);

    public static StoneType ENDSTONE = new StoneType(7, "endstone", new ResourceLocation("blocks/end_stone"), SoundType.STONE, OrePrefix.oreEndstone, Materials.Endstone, "pickaxe", 0,
        Blocks.END_STONE::getDefaultState,
        state -> state.getBlock() == Blocks.END_STONE);

    public static StoneType SANDSTONE = new StoneType(8, "sandstone", new ResourceLocation("blocks/sandstone_normal"), new ResourceLocation("blocks/sandstone_top"), SoundType.STONE, OrePrefix.oreSand, Materials.SiliconDioxide, "pickaxe", 0,
        () -> Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.DEFAULT),
        state -> state.getBlock() instanceof BlockSandStone && state.getValue(BlockSandStone.TYPE) == BlockSandStone.EnumType.DEFAULT);

    public static StoneType RED_SANDSTONE = new StoneType(9, "red_sandstone", new ResourceLocation("blocks/red_sandstone_normal"), new ResourceLocation("blocks/red_sandstone_top"), SoundType.STONE, OrePrefix.oreSand, Materials.SiliconDioxide, "pickaxe", 0,
        () -> Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.DEFAULT),
        state -> state.getBlock() instanceof BlockRedSandstone && state.getValue(BlockRedSandstone.TYPE) == BlockRedSandstone.EnumType.DEFAULT);


}
