package gtr.integration.jei;

import gtr.api.GTValues;
import gtr.api.GregTechAPI;
import gtr.api.capability.GregtechTileCapabilities;
import gtr.api.capability.IControllable;
import gtr.api.capability.impl.AbstractRecipeLogic;
import gtr.api.capability.impl.FuelRecipeLogic;
import gtr.api.gui.impl.ModularUIGuiHandler;
import gtr.api.items.metaitem.MetaItem;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.RecipeMaps;
import gtr.api.recipes.ingredients.IntCircuitIngredient;
import gtr.api.recipes.machines.FuelRecipeMap;
import gtr.api.recipes.machines.RecipeMapFurnace;
import gtr.api.unification.OreDictUnifier;
import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.DustMaterial;
import gtr.api.unification.material.type.Material;
import gtr.api.unification.ore.OrePrefix;
import gtr.api.worldgen.config.OreDepositDefinition;
import gtr.api.worldgen.config.WorldGenRegistry;
import gtr.common.blocks.BlockOre;
import gtr.common.blocks.MetaBlocks;
import gtr.common.items.MetaItems;
import gtr.common.metatileentities.MetaTileEntities;
import gtr.integration.jei.multiblock.MultiblockInfoCategory;
import gtr.integration.jei.recipe.*;
import gtr.integration.jei.recipe.fuel.FuelRecipeMapCategory;
import gtr.integration.jei.recipe.fuel.GTFuelRecipeWrapper;
import gtr.integration.jei.recipe.primitive.*;
import gtr.integration.jei.utils.*;
import gtr.loaders.recipe.CustomItemReturnShapedOreRecipeRecipe;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@JEIPlugin
public class GTJeiPlugin implements IModPlugin {

    public static IIngredientRegistry ingredientRegistry;

    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistry subtypeRegistry) {
        MetaItemSubtypeHandler subtype = new MetaItemSubtypeHandler();
        for (MetaItem<?> metaItem : MetaItems.ITEMS) {
            subtypeRegistry.registerSubtypeInterpreter(metaItem, subtype);
        }
        subtypeRegistry.registerSubtypeInterpreter(Item.getItemFromBlock(MetaBlocks.MACHINE), new MachineSubtypeHandler());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new IntCircuitCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MultiblockInfoCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new OreGenCategory(registry.getJeiHelpers().getGuiHelper()));
        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new RecipeMapCategory(recipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
        for (FuelRecipeMap fuelRecipeMap : FuelRecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new FuelRecipeMapCategory(fuelRecipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
        registry.addRecipeCategories(new PrimitiveBlastRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CokeOvenRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new OreByProductCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();



        List<OreGenWrapper> l = new ArrayList<>();

        for (OreDepositDefinition d : WorldGenRegistry.getOreDeposits()) {
            if (d.getBlockFiller().getAllPossibleStates().size() > 0) {
                d.getBlockFiller().getAllPossibleStates().forEach((i) -> {
                    if (i.getPossibleResults().size() > 0) {
                        if (((IBlockState) i.getPossibleResults().toArray()[0]).getBlock() instanceof BlockOre)
                            l.add(new OreGenWrapper(i.getPossibleResults(), d));
                    }
                });
            }
        }


        registry.addRecipes(l, "gtr:ores");



        registry.addRecipes(IntCircuitRecipeWrapper.create(), IntCircuitCategory.UID);
        MultiblockInfoCategory.registerRecipes(registry);
        registry.handleRecipes(CustomItemReturnShapedOreRecipeRecipe.class, recipe -> new CustomItemReturnRecipeWrapper(jeiHelpers, recipe), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeRegistryPlugin(new FacadeRegistryPlugin());

        ModularUIGuiHandler modularUIGuiHandler = new ModularUIGuiHandler(jeiHelpers.recipeTransferHandlerHelper());
        registry.addAdvancedGuiHandlers(modularUIGuiHandler);
        registry.addGhostIngredientHandler(modularUIGuiHandler.getGuiContainerClass(), modularUIGuiHandler);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(modularUIGuiHandler, VanillaRecipeCategoryUid.CRAFTING);

        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            List<GTRecipeWrapper> recipesList = recipeMap.getRecipeList()
                .stream().filter(recipe -> !recipe.isHidden() && recipe.hasValidInputsForDisplay())
                .map(r -> new GTRecipeWrapper(recipeMap, r))
                .collect(Collectors.toList());
            registry.addRecipes(recipesList, GTValues.MODID + ":" + recipeMap.unlocalizedName);
        }

        for (FuelRecipeMap fuelRecipeMap : FuelRecipeMap.getRecipeMaps()) {
            List<GTFuelRecipeWrapper> recipeList = fuelRecipeMap.getRecipeList().stream()
                .map(GTFuelRecipeWrapper::new)
                .collect(Collectors.toList());
            registry.addRecipes(recipeList, GTValues.MODID + ":" + fuelRecipeMap.unlocalizedName);
        }

        for (ResourceLocation metaTileEntityId : GregTechAPI.META_TILE_ENTITY_REGISTRY.getKeys()) {
            MetaTileEntity metaTileEntity = GregTechAPI.META_TILE_ENTITY_REGISTRY.getObject(metaTileEntityId);
            assert metaTileEntity != null;
            if (metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null) != null) {
                IControllable workableCapability = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);

                if (workableCapability instanceof AbstractRecipeLogic) {
                    RecipeMap<?> recipeMap = ((AbstractRecipeLogic) workableCapability).recipeMap;
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), GTValues.MODID + ":" + recipeMap.unlocalizedName);
                    if (recipeMap instanceof RecipeMapFurnace) {
                        registry.addRecipeCatalyst(metaTileEntity.getStackForm(), VanillaRecipeCategoryUid.SMELTING);
                    }
                } else if (workableCapability instanceof FuelRecipeLogic) {
                    FuelRecipeMap recipeMap = ((FuelRecipeLogic) workableCapability).recipeMap;
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), GTValues.MODID + ":" + recipeMap.unlocalizedName);
                }
            }
        }

        for (MetaTileEntity breweryTile : MetaTileEntities.BREWERY) {
            registry.addRecipeCatalyst(breweryTile.getStackForm(), VanillaRecipeCategoryUid.BREWING);
        }

        String semiFluidMapId = GTValues.MODID + ":" + RecipeMaps.SEMI_FLUID_GENERATOR_FUELS.getUnlocalizedName();
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(), semiFluidMapId);
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(), semiFluidMapId);
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(), semiFluidMapId);
        registry.addRecipeCatalyst(MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(), semiFluidMapId);

        registry.addIngredientInfo(Objects.requireNonNull(Materials.Air.getFluid(1000)), VanillaTypes.FLUID, I18n.format("gtr.machine.air_collector.jei_description"));

        String primitiveBlastId = GTValues.MODID + ":" + "primitive_blast_furnace";
        registry.addRecipes(RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES.stream()
            .map(PrimitiveBlastRecipeWrapper::new)
            .collect(Collectors.toList()), primitiveBlastId);
        registry.addRecipeCatalyst(MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(), primitiveBlastId);

        String cokeOvenId = GTValues.MODID + ":" + "coke_oven";
        registry.addRecipes(RecipeMaps.COKE_OVEN_RECIPES.stream()
            .map(CokeOvenRecipeWrapper::new)
            .collect(Collectors.toList()), cokeOvenId);
        registry.addRecipeCatalyst(MetaTileEntities.COKE_OVEN.getStackForm(), cokeOvenId);

        List<OreByProduct> oreByproductList = new CopyOnWriteArrayList<>();
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material instanceof DustMaterial && OreDictUnifier.get(OrePrefix.ore, material) != ItemStack.EMPTY) {
                oreByproductList.add(new OreByProduct((DustMaterial) material));
            }
        }
        String oreByProductId = GTValues.MODID + ":" + "ore_by_product";
        registry.addRecipes(oreByproductList, oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.MACERATOR)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.ORE_WASHER)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.CENTRIFUGE)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);
        for (MetaTileEntity machine : MetaTileEntities.THERMAL_CENTRIFUGE)
            registry.addRecipeCatalyst(machine.getStackForm(), oreByProductId);

        ingredientRegistry = registry.getIngredientRegistry();
        for (int i = 0; i <= IntCircuitIngredient.CIRCUIT_MAX; i++) {
            registry.addIngredientInfo(IntCircuitIngredient.getIntegratedCircuit(i), VanillaTypes.ITEM,
                "metaitem.circuit.integrated.jei_description");
        }

        registry.addRecipeCatalyst(MetaTileEntities.WORKBENCH.getStackForm(), VanillaRecipeCategoryUid.CRAFTING);
    }
}
