package gtr.common.metatileentities.electric;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.GregTechMod;
import gtr.api.GTValues;
import gtr.api.capability.impl.FilteredFluidHandler;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.gui.ModularUI;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.TieredMetaTileEntity;
import gtr.api.recipes.RecipeMap;
import gtr.api.recipes.builders.SimpleRecipeBuilder;
import gtr.api.render.Textures;
import gtr.api.unification.material.Materials;
import gtr.common.CommonProxy;
import ic2.core.crop.IC2CropCard;
import ic2.core.crop.IC2Crops;
import ic2.core.crop.TileEntityCrop;
import ic2.core.crop.cropcard.CropWeed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MetaTileEntityCropWeedPicker extends TieredMetaTileEntity {

    public static final RecipeMap<SimpleRecipeBuilder> map;

    static {
        map = new RecipeMap<SimpleRecipeBuilder>("dummy_crop_weed_picker", 0, 0, 0, 0, 1, 1, 0, 0, new SimpleRecipeBuilder(), true) {
            public boolean canInputFluidForce(Fluid fluid) {
                return fluid == Materials.Lubricant.getMaterialFluid();
            }
        };
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        FilteredFluidHandler[] fluidImports = new FilteredFluidHandler[1];
        for (int i = 0; i < fluidImports.length; i++) {
            FilteredFluidHandler filteredFluidHandler = new FilteredFluidHandler(8000);
            filteredFluidHandler.setFillPredicate(f -> f.getFluid() == Materials.Lubricant.getMaterialFluid());
            fluidImports[i] = filteredFluidHandler;
        }
        return new FluidTankList(false, fluidImports);
    }

    private int radius;
    private ArrayList<BlockPos> toUpdate = new ArrayList<>();

    public MetaTileEntityCropWeedPicker(ResourceLocation id, int tier) {
        super(id, tier);
        this.radius = 2+tier*2;
        initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder arg0) {
        return new MetaTileEntityCropWeedPicker(metaTileEntityId, getTier());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = map.createUITemplate(() -> 0, importItems, exportItems, importFluids, exportFluids);
        return builder.build(getHolder(), entityPlayer);
    }

    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add("Needs Lubricant (10mB/weed) and power ("+ (int) (GTValues.V[getTier()] * 0.5)+" eu/t)");
        tooltip.add("Radius: "+radius);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        for (EnumFacing side : new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST}) {
            Textures.WEED_PICKER.renderSided(side, renderState, translation, pipeline);
        }
    }

    @Override
    public void update() {
        super.update();
        double energyToDraw = GTValues.V[getTier()] * 0.25;
        if (energyContainer.getEnergyStored() >= energyToDraw && importFluids.getTankAt(0).getFluidAmount() > 10) {
            energyContainer.removeEnergy((long) energyToDraw);
            for (BlockPos pos : toUpdate) {
                TileEntity te = getWorld().getTileEntity(pos);
                if (te instanceof TileEntityCrop) {
                    TileEntityCrop crop = (TileEntityCrop) te;
                    if (crop.getCrop() instanceof CropWeed) {
                        importFluids.getTankAt(0).drain(10, true);
                        crop.performHarvest();
                    }
                }
            }
        }
        if (GregTechMod.instance.counter % 10 == 0) {
            toUpdate.clear();
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = -radius; k <= radius; k++) {
                        if (i != 0 && j != 0 && k != 0) {
                            BlockPos current = getPos().add(i, j, k);
                            if (getWorld().getTileEntity(current) instanceof TileEntityCrop) {
                                toUpdate.add(current);
                            }
                        }
                    }
                }
            }
        }
    }

}