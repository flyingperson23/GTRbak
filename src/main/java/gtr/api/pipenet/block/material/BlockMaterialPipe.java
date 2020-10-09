package gtr.api.pipenet.block.material;

import gtr.api.pipenet.PipeNet;
import gtr.api.pipenet.WorldPipeNet;
import gtr.api.pipenet.block.BlockPipe;
import gtr.api.pipenet.block.IPipeType;
import gtr.api.pipenet.tile.IPipeTile;
import gtr.api.pipenet.tile.TileEntityPipeBase;
import gtr.api.unification.material.type.Material;
import net.minecraft.item.ItemStack;

public abstract class BlockMaterialPipe<PipeType extends Enum<PipeType> & IPipeType<NodeDataType>, NodeDataType, WorldPipeNetType extends WorldPipeNet<NodeDataType, ? extends PipeNet<NodeDataType>>> extends BlockPipe<PipeType, NodeDataType, WorldPipeNetType> {

    @Override
    public NodeDataType createProperties(IPipeTile<PipeType, NodeDataType> pipeTile) {
        PipeType pipeType = pipeTile.getPipeType();
        Material material = ((IMaterialPipeTile<PipeType, NodeDataType>) pipeTile).getPipeMaterial();
        if (pipeType == null || material == null) {
            return getFallbackType();
        }
        return createProperties(pipeType, material);
    }

    @Override
    public NodeDataType createItemProperties(ItemStack itemStack) {
        PipeType pipeType = getItemPipeType(itemStack);
        Material material = getItemMaterial(itemStack);
        if (pipeType == null || material == null) {
            return getFallbackType();
        }
        return createProperties(pipeType, material);
    }

    public ItemStack getItem(PipeType pipeType, Material material) {
        if (pipeType == null || material == null) {
            return ItemStack.EMPTY;
        }
        int materialId = Material.MATERIAL_REGISTRY.getIDForObject(material);
        return new ItemStack(this, 1, pipeType.ordinal() * 1000 + materialId);
    }

    @Override
    public PipeType getItemPipeType(ItemStack itemStack) {
        return getPipeTypeClass().getEnumConstants()[itemStack.getMetadata() / 1000];
    }

    public Material getItemMaterial(ItemStack itemStack) {
        return Material.MATERIAL_REGISTRY.getObjectById(itemStack.getMetadata() % 1000);
    }

    @Override
    public void setTileEntityData(TileEntityPipeBase<PipeType, NodeDataType> pipeTile, ItemStack itemStack) {
        ((TileEntityMaterialPipeBase<PipeType, NodeDataType>) pipeTile).setPipeData(this, getItemPipeType(itemStack), getItemMaterial(itemStack));
    }

    @Override
    public ItemStack getDropItem(IPipeTile<PipeType, NodeDataType> pipeTile) {
        Material material = ((IMaterialPipeTile<PipeType, NodeDataType>) pipeTile).getPipeMaterial();
        return getItem(pipeTile.getPipeType(), material);
    }

    protected abstract NodeDataType createProperties(PipeType pipeType, Material material);

}
