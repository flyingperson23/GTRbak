package gtr.common.items.behaviors;

import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.api.unification.Element;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class DataBehavior implements IItemBehaviour {
    //  8 bits -
    public enum DataType {
        ERROR,
        ELEMENT,
        CROP_SPECIES,
        CROP_GROWTH,
        CROP_GAIN,
        CROP_RESISTANCE
    }

    public static DataType getTypeFromBits(short bitmap) {
        int i = bitmap & 0b111;
        if (i < DataType.values().length) return DataType.values()[i];
        return DataType.ERROR;
    }

    public static short getDataBits(short bitmap) {
        return (short) ((bitmap & 0b111111111111000) >> 3);
    }

    public static short combine(short data, DataType type) {
        short typeBits = (short) (type.ordinal() & 0b111);
        return (short) (((data << 3) | typeBits) & 0b111111111111111);
    }

    public static short getData(Element element) {
        return (short) element.ordinal();
    }

    public static Element getElement(short data) {
        return Element.values()[data];
    }

    public static short getData(ItemStack crop, DataType type) {
        switch(type) {
            case CROP_SPECIES:
                return IC2Handler.getSpecies(crop);
            case CROP_GROWTH:
                return IC2Handler.getGrowth(crop);
            case CROP_GAIN:
                return IC2Handler.getGain(crop);
            case CROP_RESISTANCE:
                return IC2Handler.getResistance(crop);
        }
        return -1;
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> lines) {
        lines.add("Craft to erase");
        lines.add("Use a scanner to duplicate");
        if (itemStack.hasTagCompound()) {
            NBTTagCompound compound = itemStack.getTagCompound();
            if (compound != null) {
                if (compound.hasKey("data")) {
                    short data = compound.getShort("data");
                    switch(getTypeFromBits(data)) {
                        case ELEMENT:
                            lines.add("Element scan: "+getElement(getDataBits(data)).name());
                            break;
                        case CROP_SPECIES:
                            lines.add("Crop species: "+IC2Handler.getSpeciesName(getDataBits(data)));
                            break;
                        case CROP_GROWTH:
                            lines.add("Crop growth: "+ (int) getDataBits(data));
                            break;
                        case CROP_GAIN:
                            lines.add("Crop gain: "+ (int) getDataBits(data));
                            break;
                        case CROP_RESISTANCE:
                            lines.add("Crop resistance: "+ (int) getDataBits(data));
                            break;
                        case ERROR:
                            lines.add("Empty");
                            break;
                    }
                } else {
                    compound.setShort("data", (short) 0);
                }
            }
        } else {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }
}
