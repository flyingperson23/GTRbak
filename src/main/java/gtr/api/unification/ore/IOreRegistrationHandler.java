package gtr.api.unification.ore;

import gtr.api.unification.material.type.Material;

@FunctionalInterface
public interface IOreRegistrationHandler {

    void processMaterial(OrePrefix orePrefix, Material material);

}