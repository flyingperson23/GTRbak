package gtr.integration.jei.utils;

import java.util.Map;
import net.minecraftforge.fluids.FluidRegistry;
import java.util.ArrayList;
import net.minecraftforge.fluids.FluidStack;

public class FluidWrapper {
    boolean placeable;
    int color;
    int density;
    String name;
    int luminosity;
    int temperature;
    int viscosity;
    boolean isGas;

    public FluidWrapper(final boolean placeable, final int color, final int density, final String name, final int luminosity, final int temperature, final int viscosity, final boolean isGas) {
        this.placeable = placeable;
        this.color = color;
        this.density = density;
        this.name = name;
        this.luminosity = luminosity;
        this.temperature = temperature;
        this.viscosity = viscosity;
        this.isGas = isGas;
    }

    public FluidWrapper(final FluidStack stack) {
        this(stack.getFluid());
    }

    public FluidWrapper(final net.minecraftforge.fluids.Fluid fluid) {
        this.placeable = fluid.canBePlacedInWorld();
        this.color = fluid.getColor();
        this.density = fluid.getDensity();
        this.name = fluid.getName();
        this.luminosity = fluid.getLuminosity();
        this.temperature = fluid.getTemperature();
        this.viscosity = fluid.getViscosity();
        this.isGas = fluid.isGaseous();
    }

    public static ArrayList<FluidWrapper> getAllFluids() {
        final ArrayList<FluidWrapper> allFluids = new ArrayList<>();
        for (final Map.Entry<String, net.minecraftforge.fluids.Fluid> entry : FluidRegistry.getRegisteredFluids().entrySet()) {
            final net.minecraftforge.fluids.Fluid current = entry.getValue();
            allFluids.add(new FluidWrapper(current.canBePlacedInWorld(), current.getColor(), current.getDensity(), current.getName(), current.getLuminosity(), current.getTemperature(), current.getViscosity(), current.isGaseous()));
        }
        FluidRegistry.getRegisteredFluids();
        return allFluids;
    }

    public net.minecraftforge.fluids.Fluid getFluid() {
        return FluidRegistry.getFluid(this.name);
    }

    public boolean getPlaceable() {
        return this.placeable;
    }

    public int getColor() {
        return this.color;
    }

    public int getDensity() {
        return this.density;
    }

    public String getName() {
        return this.name;
    }

    public int getLuminosity() {
        return this.luminosity;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getViscosity() {
        return this.viscosity;
    }

    public boolean isGas() {
        return this.isGas;
    }

    public ArrayList<String> getTooltip() {
        final ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add("Fluid Name: " + this.getName());
        tooltip.add("Fluid Density: " + this.getDensity());
        tooltip.add("Fluid Luminosity: " + this.getLuminosity());
        tooltip.add("Fluid Temperature: " + this.getTemperature());
        tooltip.add("Fluid Viscosity: " + this.getViscosity());
        return tooltip;
    }
}