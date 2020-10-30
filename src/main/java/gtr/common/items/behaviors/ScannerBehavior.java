package gtr.common.items.behaviors;

import codechicken.lib.raytracer.RayTracer;
import com.mojang.realmsclient.gui.ChatFormatting;
import gtr.api.GTValues;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IElectricItem;
import gtr.api.capability.IEnergyContainer;
import gtr.api.capability.IWorkable;
import gtr.api.items.metaitem.stats.IItemBehaviour;
import gtr.api.items.metaitem.stats.IItemUseManager;
import gtr.api.items.toolitem.IScannableBlock;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.util.GTUtility;
import gtr.common.pipelike.cable.net.EnergyNet;
import gtr.common.pipelike.cable.net.WorldENet;
import gtr.common.pipelike.cable.tile.CableEnergyContainer;
import gtr.common.pipelike.cable.tile.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ScannerBehavior implements IItemBehaviour, IItemUseManager {


    private final int costPerUseTick;

    public ScannerBehavior(int costPerUseTick) {
        this.costPerUseTick = costPerUseTick;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (player.world != null && !player.world.isRemote) {
            boolean discharged = dischargeItem(player.getHeldItem(hand), 100, false);
            if (discharged) {
                BlockPos pos = RayTracer.retrace(player).getBlockPos();
                ArrayList<String> results = getCoordinateScan(player, pos);
                results.forEach(i -> player.sendMessage(new TextComponentString(i)));
                player.stopActiveHand();
                player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 10);
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }


    private boolean dischargeItem(ItemStack itemStack, long amount, boolean simulate) {
        IElectricItem electricItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem == null) {
            return false;
        }
        long dischargeAmount = amount * costPerUseTick;
        return electricItem.discharge(dischargeAmount, Integer.MAX_VALUE, true, false, simulate) >= dischargeAmount;
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 200_000;
    }

    public static ArrayList<String> getCoordinateScan(EntityPlayer player, BlockPos pos) {
        World world = player.world;

        ArrayList<String> list = new ArrayList<>();

        TileEntity tileEntity = world.getTileEntity(pos);
        Block block = world.getBlockState(pos).getBlock();

        list.add("----- X: " + ChatFormatting.AQUA + pos.getX() + ChatFormatting.RESET + " Y: " + ChatFormatting.AQUA + pos.getY() + ChatFormatting.RESET + " Z: " + ChatFormatting.AQUA + pos.getZ() + ChatFormatting.RESET + " D: " + ChatFormatting.AQUA + world.provider.getDimension() + ChatFormatting.RESET + " -----");

        list.add("Name: " + ChatFormatting.BLUE + block.getLocalizedName() + ChatFormatting.RESET);
        list.add("Hardness: " + ChatFormatting.YELLOW + block.getBlockHardness(world.getBlockState(pos), world, pos) + ChatFormatting.RESET + " Blast Resistance: " + ChatFormatting.YELLOW + block.getExplosionResistance(world, pos, player, new Explosion(world, player, pos.getX(), pos.getY(), pos.getZ(), 5.0f, false, false)) + ChatFormatting.RESET);
        if (block.isBeaconBase(world, pos, pos.offset(EnumFacing.UP)))
            list.add(ChatFormatting.GOLD + "Is valid Beacon Pyramid Material" + ChatFormatting.RESET);


        if (tileEntity != null) {

            if (tileEntity instanceof IFluidHandler) {
                IFluidTankProperties[] tanks = ((IFluidHandler) tileEntity).getTankProperties();
                for (int i = 0; i < tanks.length; i++) {
                    list.add("Tank " + i + ": " + ChatFormatting.GREEN + formatNumbers((tanks[i].getContents() == null ? 0 : Objects.requireNonNull(tanks[i].getContents()).amount)) + ChatFormatting.RESET + " L / " + ChatFormatting.YELLOW + formatNumbers(tanks[i].getCapacity()) + ChatFormatting.RESET + " L " + ChatFormatting.GOLD + Objects.requireNonNull(tanks[i].getContents()).getLocalizedName() + ChatFormatting.RESET);
                }
            }


            if (tileEntity instanceof ic2.api.reactor.IReactorChamber) {
                tileEntity = (TileEntity) (((ic2.api.reactor.IReactorChamber) tileEntity).getReactorInstance());
            }


            if (tileEntity instanceof ic2.api.reactor.IReactor) {
                list.add("Heat: " + ChatFormatting.GREEN + ((ic2.api.reactor.IReactor) tileEntity).getHeat() + ChatFormatting.RESET + " / " + ChatFormatting.YELLOW + ((ic2.api.reactor.IReactor) tileEntity).getMaxHeat() + ChatFormatting.RESET);
                list.add("HEM: " + ChatFormatting.YELLOW + ((ic2.api.reactor.IReactor) tileEntity).getHeatEffectModifier());
            }


            if (tileEntity instanceof ic2.api.tile.IWrenchable) {
                list.add("Facing: " + ChatFormatting.GREEN + ((ic2.api.tile.IWrenchable) tileEntity).getFacing(world, pos) + ChatFormatting.RESET + " / Chance: ");
                list.add(((ic2.api.tile.IWrenchable) tileEntity).wrenchCanRemove(world, pos, player) ? ChatFormatting.GREEN + "You can remove this with a Wrench" + ChatFormatting.RESET : ChatFormatting.RED + "You can NOT remove this with a Wrench" + ChatFormatting.RESET);
            }


            if (tileEntity instanceof ic2.api.energy.tile.IEnergySink) {
                list.add("Demanded Energy: " + ((ic2.api.energy.tile.IEnergySink) tileEntity).getDemandedEnergy());
                list.add("Max Safe Input: " + (((ic2.api.energy.tile.IEnergySink) tileEntity).getSinkTier()));
            }


            if (tileEntity instanceof ic2.api.energy.tile.IEnergySource) {
                list.add("Max Energy Output: " + ((ic2.api.energy.tile.IEnergySource) tileEntity).getOfferedEnergy());
                list.add("Tier: " + ((ic2.api.energy.tile.IEnergySource) tileEntity).getSourceTier());
            }


            if (tileEntity instanceof ic2.api.energy.tile.IEnergyConductor) {
                list.add("Conduction Loss: " + ChatFormatting.YELLOW + ((ic2.api.energy.tile.IEnergyConductor) tileEntity).getConductionLoss() + ChatFormatting.RESET);
            }


            if (tileEntity instanceof ic2.api.tile.IEnergyStorage) {
                list.add("Contained Energy: " + ChatFormatting.YELLOW + ((ic2.api.tile.IEnergyStorage) tileEntity).getStored() + ChatFormatting.RESET + " EU / " + ChatFormatting.YELLOW + ((ic2.api.tile.IEnergyStorage) tileEntity).getCapacity() + ChatFormatting.RESET + " EU");
            }


            if (tileEntity instanceof MetaTileEntityHolder) {
                MetaTileEntityHolder h = (MetaTileEntityHolder) tileEntity;
                MetaTileEntity te = h.getMetaTileEntity();
                if (te instanceof IWorkable) {
                    IWorkable workable = (IWorkable) te;
                    if (workable.isActive()) list.add(ChatFormatting.RED + "Inactive");
                    else list.add(ChatFormatting.GREEN + "Active");
                    list.add(ChatFormatting.BLUE + "Progress: " + ChatFormatting.RESET + workable.getProgress() + "/" + workable.getMaxProgress());
                }
            }


            IEnergyContainer container = null;
            for (EnumFacing f : EnumFacing.VALUES) {
                if (container == null)
                    container = tileEntity.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, f);
            }
            if (container != null) {
                list.add("Max IN: " + ChatFormatting.RED + container.getInputVoltage() + " (" + GTValues.VN[GTUtility.getTierByVoltage(container.getInputVoltage())] + ") " + ChatFormatting.RESET + " EU at " + ChatFormatting.RED + container.getInputAmperage() + ChatFormatting.RESET + " A");
                list.add("Max OUT: " + ChatFormatting.RED + container.getOutputVoltage() + " (" + GTValues.VN[GTUtility.getTierByVoltage(container.getOutputVoltage())] + ") " + ChatFormatting.RESET + " EU at " + ChatFormatting.RED + container.getOutputAmperage() + ChatFormatting.RESET + " A");
                list.add("Energy: " + ChatFormatting.GREEN + formatNumbers(container.getEnergyStored()) + ChatFormatting.RESET + " EU / " + ChatFormatting.YELLOW + formatNumbers(container.getEnergyCapacity()) + ChatFormatting.RESET + " EU");

            }


            if (tileEntity instanceof TileEntityCable) {
                long amps = WorldENet.getWorldENet(world).getNetFromPos(pos).currentAmperageCounter.get(world);
                long volts = WorldENet.getWorldENet(world).getNetFromPos(pos).currentMaxVoltageCounter.get(world);

                System.out.println(amps+" "+WorldENet.getWorldENet(world).getNetFromPos(pos).getLastAmperage());

                list.add(ChatFormatting.RED + "Last Amperage: " + ChatFormatting.RESET + amps + "A");
                list.add(ChatFormatting.RED + "Last Voltage: " + ChatFormatting.RESET + volts + "V");
            }
        }


        return list;
    }

    public static String formatNumbers(long aNumber) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        return formatter.format(aNumber);
    }


}
