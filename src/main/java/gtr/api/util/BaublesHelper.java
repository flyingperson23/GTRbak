package gtr.api.util;

import akka.japi.Pair;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class BaublesHelper {
    public static Pair<Long, Long> getStuff(EntityPlayer p) {
        IBaublesItemHandler i = BaublesApi.getBaublesHandler(p);
        AtomicLong eu = new AtomicLong();
        AtomicLong maxeu = new AtomicLong();
        IntStream.range(0, i.getSlots()).mapToObj(i::getStackInSlot).forEach(s -> {
            if (s.hasCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)) {
                IEnergyContainer c = s.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
                eu.addAndGet(c.getEnergyStored());
                maxeu.addAndGet(c.getEnergyCapacity());
            } else if (s.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) {
                IElectricItem c = s.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
                eu.addAndGet(c.getCharge());
                maxeu.addAndGet(c.getMaxCharge());
            }
        });
        return new Pair<>(eu.get(), maxeu.get());
    }

    public static IInventory getInventory(EntityPlayer p) {
        return BaublesApi.getBaubles(p);
    }
}
