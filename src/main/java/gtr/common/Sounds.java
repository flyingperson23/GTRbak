package gtr.common;

import gtr.api.GTValues;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Sounds {
    public static final SoundEvent wrench_sound = new SoundEvent(new ResourceLocation(GTValues.MODID, "wrench")).setRegistryName(new ResourceLocation(GTValues.MODID,"wrench"));;

    public static void init(IForgeRegistry<SoundEvent> registry) {
        registry.register(wrench_sound);
    }
}
