package gtr.common;

import gtr.api.GTValues;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Sounds {
    public static final SoundEvent WRENCH_SOUND = new SoundEvent(new ResourceLocation(GTValues.MODID, "wrench")).setRegistryName(new ResourceLocation(GTValues.MODID,"wrench"));
    public static final SoundEvent JET_ENGINE = new SoundEvent(new ResourceLocation(GTValues.MODID, "jet_engine")).setRegistryName(new ResourceLocation(GTValues.MODID, "jet_engine"));

    public static void init(IForgeRegistry<SoundEvent> registry) {
        registry.register(WRENCH_SOUND);
        registry.register(JET_ENGINE);
    }
}
