package gtr.api.worldgen.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.type.DustMaterial;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.StoneType;
import gtr.common.blocks.BlockOre;
import gtr.common.blocks.MetaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OreConfigUtils {

    @SuppressWarnings("deprecation")
    public static List<IBlockState> getOreDictBlocks(String oreDictName) {
        List<ItemStack> allOres = OreDictUnifier.getAllWithOreDictionaryName(oreDictName);
        ArrayList<IBlockState> allBlocks = new ArrayList<>();
        for (ItemStack oreStack : allOres) {
            Block itemStackBlock = Block.getBlockFromItem(oreStack.getItem());
            if (itemStackBlock == Blocks.AIR)
                continue;
            int placementMetadata = oreStack.getItem().getMetadata(oreStack.getMetadata());
            IBlockState placementState = itemStackBlock.getStateFromMeta(placementMetadata);
            allBlocks.add(placementState);
        }
        if (allBlocks.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find any blocks matching " + oreDictName + " oredict tag");
        }
        return allBlocks;
    }

    public static Map<StoneType, IBlockState> getOreStateMap(String stringDeclaration) {
        String materialName;
        if (stringDeclaration.startsWith("ore:")) {
            materialName = stringDeclaration.substring(4);
        } else {
            throw new IllegalArgumentException("Invalid string ore declaration: " + stringDeclaration);
        }
        DustMaterial material = getMaterialByName(materialName);
        return getOreForMaterial(material);
    }

    public static Map<StoneType, IBlockState> getOreForMaterial(DustMaterial material) {
        List<BlockOre> oreBlocks = MetaBlocks.ORES.stream()
            .filter(ore -> ore.material == material)
            .collect(Collectors.toList());
        HashMap<StoneType, IBlockState> stoneTypeMap = new HashMap<>();
        for (BlockOre blockOre : oreBlocks) {
            for (StoneType stoneType : blockOre.STONE_TYPE.getAllowedValues()) {
                IBlockState blockState = blockOre.getOreBlock(stoneType);
                stoneTypeMap.put(stoneType, blockState);
            }
        }
        if (stoneTypeMap.isEmpty()) {
            throw new IllegalArgumentException("There is no ore generated for material " + material);
        }
        return stoneTypeMap;
    }

    public static DustMaterial getMaterialByName(String name) {
        Material material = Material.MATERIAL_REGISTRY.getObject(name);
        if (!(material instanceof DustMaterial))
            throw new IllegalArgumentException("Material with name " + name + " not found!");
        return (DustMaterial) material;
    }

    public static Block getBlockByName(String name) {
        ResourceLocation blockName = new ResourceLocation(name);
        Block block = GameRegistry.findRegistry(Block.class).getValue(blockName);


        if (block == null)
            throw new IllegalArgumentException("Block with identifier " + blockName + " not found!");
        return block;
    }

    public static int[] getIntRange(JsonElement element) {
        if (element.isJsonArray()) {
            JsonArray dataArray = element.getAsJsonArray();
            int max = dataArray.get(1).getAsInt();
            int min = Math.min(max, dataArray.get(0).getAsInt());
            return new int[]{min, max};
        } else if (element.isJsonObject()) {
            JsonObject dataObject = element.getAsJsonObject();
            int max = dataObject.get("max").getAsInt();
            int min = Math.min(max, dataObject.get("min").getAsInt());
            return new int[]{min, max};
        } else if (element.isJsonPrimitive()) {
            int size = element.getAsInt();
            return new int[]{size, size};
        } else {
            throw new IllegalArgumentException("size range not defined");
        }
    }
}
