package gtr.integration.ic2;

import gtr.api.unification.material.Materials;
import gtr.api.unification.material.type.Material;
import ic2.api.crops.CropCard;
import ic2.api.crops.CropProperties;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import net.minecraft.block.Block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import speiger.src.crops.api.ICropCardInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GTBaseCrop extends CropCard implements ICropCardInfo {

    private int id;
    private String cropName;
    private String discoveredBy;
    private ItemStack baseSeed;
    private int tier, maxSize, growthSpeed, afterHarvestSize, harvestSize, statChemical, statFood, statDefensive, statColor, statWeed;
    private String[] attributes;
    private ItemStack drop;
    private ItemStack[] specialDrops;
    private CropProperties properties;

    public GTBaseCrop(int aID, String aCropName, String aDiscoveredBy, ItemStack aBaseSeed, int aTier, int aMaxSize, int aGrowthSpeed, int aAfterHarvestSize, int aHarvestSize, int aStatChemical, int aStatFood, int aStatDefensive, int aStatColor, int aStatWeed, String[] aAttributes, Material aBlock, ItemStack aDrop, ItemStack[] aSpecialDrops) {
        this(aID, aCropName, aDiscoveredBy, aBaseSeed, aTier, aMaxSize, aGrowthSpeed, aAfterHarvestSize, aHarvestSize, aStatChemical, aStatFood, aStatDefensive, aStatColor, aStatWeed, aAttributes, aDrop, aSpecialDrops);
    }
        /**
         * To create new Crops
         *
         * @param aID           Default ID
         * @param aCropName     Name of the Crop
         * @param aDiscoveredBy The one who discovered the Crop
         * @param aDrop         The Item which is dropped by the Crop. must be != null
         * @param aBaseSeed     Baseseed to plant this Crop. null == crossbreed only
         * @param aTier         tier of the Crop. forced to be >= 1
         * @param aMaxSize      maximum Size of the Crop. forced to be >= 3
         * @param aGrowthSpeed  how fast the Crop grows. if < 0 then its set to Tier*300
         * @param aHarvestSize  the size the Crop needs to be harvested. forced to be between 2 and max size
         */
    public GTBaseCrop(int aID, String aCropName, String aDiscoveredBy, ItemStack aBaseSeed, int aTier, int aMaxSize, int aGrowthSpeed, int aAfterHarvestSize, int aHarvestSize, int aStatChemical, int aStatFood, int aStatDefensive, int aStatColor, int aStatWeed, String[] aAttributes, ItemStack aDrop, ItemStack[] aSpecialDrops) {
        id = aID;
        cropName = aCropName;
        discoveredBy = aDiscoveredBy;
        baseSeed = aBaseSeed;
        tier = aTier;
        maxSize = aMaxSize;
        growthSpeed = aGrowthSpeed;
        afterHarvestSize = aAfterHarvestSize;
        harvestSize = aHarvestSize;
        statChemical = aStatChemical;
        statFood = aStatFood;
        statDefensive = aStatDefensive;
        statColor = aStatColor;
        statWeed = aStatWeed;
        attributes = aAttributes;
        drop = aDrop;
        specialDrops = aSpecialDrops;
        properties = new CropProperties(tier, statChemical, statFood, statDefensive, statColor, statWeed);
        Crops.instance.registerCrop(this);
        if (baseSeed != null) {
            Crops.instance.registerBaseSeed(baseSeed, this, 1, 1, 1, 1);
        }
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public int getSizeAfterHarvest(ICropTile cropTile) {
        return afterHarvestSize;
    }

    /*
    @Override
    public int getOptimalHarvestSize(ICropTile cropTile) {
        return harvestSize;
    }
     */

    @Override
    public String getOwner() {
        return discoveredBy;
    }

    @Override
    public CropProperties getProperties() {
        return properties;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public List<ResourceLocation> getTexturesLocation() {
        return Collections.singletonList(drop.getItem().getRegistryName());
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList(attributes);
    }

    @Override
    public ItemStack getDisplayItem() {
        return drop;
    }
}