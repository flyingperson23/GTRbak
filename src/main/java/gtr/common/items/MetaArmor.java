package gtr.common.items;

import gtr.api.GTValues;
import gtr.api.items.armor.ArmorMetaItem;
import gtr.common.armor.*;
import gtr.common.items.armor.ArmorLogicRebreather;
import net.minecraft.inventory.EntityEquipmentSlot;

public class MetaArmor extends ArmorMetaItem<ArmorMetaItem<?>.ArmorMetaValueItem> {

    @Override
    public void registerSubItems() {
        MetaItems.NANO_CHESTPLATE = addItem(0, "nano.chestplate").setArmorLogic(new NanoSuit(EntityEquipmentSlot.CHEST, 500, 1638400));
        MetaItems.NANO_LEGGINGS = addItem(1, "nano.leggings").setArmorLogic(new NanoSuit(EntityEquipmentSlot.LEGS, 500, 1638400));
        MetaItems.NANO_HELMET = addItem(2, "nano.helmet").setArmorLogic(new NanoSuit(EntityEquipmentSlot.HEAD, 500, 1638400));
        MetaItems.NANO_BOOTS = addItem(3, "nano.boots").setArmorLogic(new NanoSuit(EntityEquipmentSlot.FEET, 500, 1638400));

        MetaItems.QUANTUM_CHESTPLATE = addItem(4, "quantum.chestplate").setArmorLogic(new QuantumSuit(EntityEquipmentSlot.CHEST, 1000, 8192000, GTValues.EV));
        MetaItems.QUANTUM_LEGGINGS = addItem(5, "quantum.leggings").setArmorLogic(new QuantumSuit(EntityEquipmentSlot.LEGS, 1000, 8192000, GTValues.EV));
        MetaItems.QUANTUM_HELMET = addItem(6, "quantum.helmet").setArmorLogic(new QuantumSuit(EntityEquipmentSlot.HEAD, 1000, 8192000, GTValues.EV));
        MetaItems.QUANTUM_BOOTS = addItem(7, "quantum.boots").setArmorLogic(new QuantumSuit(EntityEquipmentSlot.FEET, 1000, 8192000, GTValues.EV));

        MetaItems.ELECTRIC_JETPACK = addItem(9, "electric_jetpack").setArmorLogic(new AdvancedJetpack(96, 512000, GTValues.MV));


        MetaItems.QUANTUM_SUIT_JETPACK = addItem(13, "quantum.jetpack").setArmorLogic(new QuantumSuitJetpack());
        MetaItems.NANO_SUIT_JETPACK = addItem(14, "nano.jetpack").setArmorLogic(new NanoSuitJetpack());

        MetaItems.ELECTRIC_JETPACK.setModelAmount(8);

        MetaItems.REBREATHER = addItem(15, "rebreather").setArmorLogic(new ArmorLogicRebreather(16));

    }
}
