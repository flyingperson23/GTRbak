package gtr.integration.ic2;

import gtr.api.GTValues;
import gtr.api.unification.material.type.Material;
import ic2.api.crops.CropCard;
import ic2.api.crops.CropProperties;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import speiger.src.crops.api.ICropCardInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GTBaseCrop extends CropCard implements ICropCardInfo {

    public static ArrayList<GTBaseCrop> cropList = new ArrayList<>();

    private int id;
    private String cropName;
    private String discoveredBy;
    private ItemStack baseSeed;
    private int tier, maxSize, growthSpeed, afterHarvestSize, harvestSize, statChemical, statFood, statDefensive, statColor, statWeed;
    private String[] attributes;
    private ItemStack drop;
    private ItemStack[] specialDrops;
    private CropProperties properties;
    public Material requiredMatBelow;
    public Runnable registerBaseSeed;

    public GTBaseCrop(int aID, String aCropName, String aDiscoveredBy, ItemStack aBaseSeed, int aTier, int aMaxSize, int aGrowthSpeed, int aAfterHarvestSize, int aHarvestSize, int aStatChemical, int aStatFood, int aStatDefensive, int aStatColor, int aStatWeed, String[] aAttributes, Material aBlock, ItemStack aDrop, ItemStack[] aSpecialDrops) {
        this(aID, aCropName, aDiscoveredBy, aBaseSeed, aTier, aMaxSize, aGrowthSpeed, aAfterHarvestSize, aHarvestSize, aStatChemical, aStatFood, aStatDefensive, aStatColor, aStatWeed, aAttributes, aDrop, aSpecialDrops);
        requiredMatBelow = aBlock;
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
        cropName = aCropName.toLowerCase();
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
        if (baseSeed != null) {
            registerBaseSeed = () -> Crops.instance.registerBaseSeed(baseSeed, this, 1, 1, 1, 1);
        }
        cropList.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public List<ResourceLocation> getTexturesLocation() {

        List<ResourceLocation> ret = new ArrayList<>();

        for(int size = 1; size <= getMaxSize(); ++size) {
            ResourceLocation r = new ResourceLocation("gtr", "blocks/crop/blockcrop." + cropName + "." + size);
            ret.add(r);
        }

        return ret;
    }


    @Override
    public String getId() {
        return cropName;
    }

    @Override
    public int getSizeAfterHarvest(ICropTile cropTile) {
        return afterHarvestSize;
    }

    @Override
    public int getRootsLength(ICropTile crop) {
        return 5;
    }

    @Override
    public int getOptimalHarvestSize(ICropTile cropTile) {
        return harvestSize;
    }

    @Override
    public ItemStack[] getGains(ICropTile aCrop) {
        int tDrop;
        if (specialDrops != null && (tDrop = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, (specialDrops.length*2) + 2)) < specialDrops.length && specialDrops[tDrop] != null) {
            if (Math.random() < 0.25) return new ItemStack[] {drop.copy(), specialDrops[tDrop].copy()};
            return new ItemStack[] {specialDrops[tDrop].copy()};
        }
        return new ItemStack[] {drop.copy()};
    }

    @Override
    public boolean canGrow(ICropTile iCropTile) {
        if (iCropTile.getCurrentSize() < (getOptimalHarvestSize(iCropTile) - 1)) return super.canGrow(iCropTile);
        else if (iCropTile.getCurrentSize() == (getOptimalHarvestSize(iCropTile) - 1) && requiredMatBelow != null) return super.canGrow(iCropTile) && isBlockBelow(iCropTile);
        return super.canGrow(iCropTile);
    }

    public boolean isBlockBelow(ICropTile aCrop) {
        if (aCrop != null) {
            for (int i = 1; i <= this.getRootsLength(aCrop); ++i) {
                BlockPos pos = aCrop.getPosition();
                IBlockState state = aCrop.getWorldObj().getBlockState(pos.add(0, -1-i, 0));
                ItemStack stack = new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
                List<String> oreDictNamesInLowercase = IntStream.of(OreDictionary.getOreIDs(stack)).mapToObj(OreDictionary::getOreName).map(String::toLowerCase).collect(Collectors.toList());
                if (oreDictNamesInLowercase.contains("block"+requiredMatBelow.toString())) return true;
            }
        }
        return false;
    }

    @Override
    public int getGrowthDuration(ICropTile aCrop) {
        if (growthSpeed < 200) return super.getGrowthDuration(aCrop);
        return tier * growthSpeed;
    }

    @Override
    public String getDiscoveredBy() {
        return discoveredBy;
    }

    @Override
    public String getOwner() {
        return GTValues.MODID;
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
    public List<String> getCropInformation() {
        return Arrays.asList(attributes);
    }

    @Override
    public ItemStack getDisplayItem() {
        return drop;
    }

    @Override
    public String[] getAttributes() {
        return attributes;
    }


}