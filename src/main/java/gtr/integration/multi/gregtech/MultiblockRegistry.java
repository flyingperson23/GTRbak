package gtr.integration.multi.gregtech;

import crafttweaker.annotations.ZenRegister;
import gtr.api.GTValues;
import gtr.api.GregTechAPI;
import gtr.integration.multi.crafttweaker.CustomMultiblock;
import gtr.integration.multi.gregtech.tile.TileControllerCustom;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * An alternative way to get a {@link CustomMultiblock} from its metadata or ID.
 */
@ZenClass("mods.gregtech.multiblock.MultiblockRegistry")
@ZenRegister
public class MultiblockRegistry {

    private static Int2ObjectMap<CustomMultiblock> metaIdMap = new Int2ObjectOpenHashMap<>();
    private static Map<ResourceLocation, CustomMultiblock> resourceLocMap = new HashMap<>();

    public static void registerMultiblock(@Nonnull CustomMultiblock multiblock) {
        resourceLocMap.put(multiblock.loc, multiblock);
        metaIdMap.put(multiblock.metaId, multiblock);
        GregTechAPI.registerMetaTileEntity(multiblock.metaId, new TileControllerCustom(multiblock));
    }

    /**
     * Get a {@link CustomMultiblock} by its metadata.
     *
     * @param metaId The metadata of the multiblock controller.
     * @return The controller, if it was registered, or null.
     */
    @Nullable
    @ZenMethod
    public static CustomMultiblock get(int metaId) {
        return metaIdMap.get(metaId);
    }

    /**
     * Get a {@link CustomMultiblock} by its meta tile entity ID.
     *
     * @param location The ID of the multiblock controller.
     * @return The controller, if it was registered, or null.
     */
    @Nullable
    @ZenMethod
    public static CustomMultiblock get(@Nonnull String location) {
        ResourceLocation loc = new ResourceLocation(location);
        if(loc.getNamespace().equals("minecraft")) {
            loc = new ResourceLocation(GTValues.MODID, loc.getPath());
        }
        return get(loc);
    }

    /**
     * @return All the registered {@link CustomMultiblock}s.
     */
    @ZenMethod
    public static List<CustomMultiblock> all() {
        return new ArrayList<>(getAll());
    }


    @Nullable
    public static CustomMultiblock get(@Nonnull ResourceLocation resourceLocation) {
        return resourceLocMap.get(resourceLocation);
    }

    @Nonnull
    public static Collection<CustomMultiblock> getAll() {
        return metaIdMap.values();
    }

}
