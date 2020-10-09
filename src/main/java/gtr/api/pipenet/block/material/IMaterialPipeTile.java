package gtr.api.pipenet.block.material;

import gtr.api.pipenet.block.IPipeType;
import gtr.api.pipenet.tile.IPipeTile;
import gtr.api.unification.material.type.Material;

public interface IMaterialPipeTile<PipeType extends Enum<PipeType> & IPipeType<NodeDataType>, NodeDataType> extends IPipeTile<PipeType, NodeDataType> {

    Material getPipeMaterial();
}
