package gtr.integration.tinkers;

import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.IngotMaterial;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.common.ConfigHolder;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;

@Mod.EventBusSubscriber
public class TinkersEvents {
    @Optional.Method(modid = "tconstruct")
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (ConfigHolder.GregsConstruct.EnableGregsConstruct && Loader.isModLoaded("tconstruct")) {
            TinkersGtRecipes.init();
            TinkersGtRecipes.init2();
        }
    }

    @Optional.Method(modid = "tconstruct")
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void smeltingRemoval(TinkerRegisterEvent.MeltingRegisterEvent event) {
        if (ConfigHolder.GregsConstruct.EnableGregsConstruct) for (Material mat : Material.MATERIAL_REGISTRY)
            if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0 && (matches(event, OrePrefix.ore, mat) || matches(event, OrePrefix.dust, mat) || matches(event, OrePrefix.dustSmall, mat) || matches(event, OrePrefix.dustTiny, mat))) event.setCanceled(true);
    }

    @Optional.Method(modid = "tconstruct")
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void alloyRemoval(TinkerRegisterEvent.AlloyRegisterEvent event) {
        if (event.getRecipe().getResult() == Materials.Brass.getFluid(3) && ConfigHolder.GregsConstruct.TinkersMaterialAlloying) event.setCanceled(true);
    }

    private static boolean matches(TinkerRegisterEvent.MeltingRegisterEvent e, OrePrefix prefix, Material mat) {
        return e.getRecipe().input.matches(NonNullList.withSize(1, OreDictUnifier.get(prefix, mat))).isPresent();
    }
}