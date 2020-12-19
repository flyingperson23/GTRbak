package gtr.common.pipelike.cable;

import com.google.common.base.Preconditions;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.IEnergyContainer;
import gtr.api.damagesources.DamageSources;
import gtr.api.pipenet.block.material.BlockMaterialPipe;
import gtr.api.pipenet.tile.IPipeTile;
import gtr.api.pipenet.tile.TileEntityPipeBase;
import gtr.api.unification.material.type.Material;
import gtr.api.util.GTUtility;
import gtr.common.ConfigHolder;
import gtr.common.pipelike.cable.Insulation;
import gtr.common.pipelike.cable.WireProperties;
import gtr.common.pipelike.cable.net.EnergyNet;
import gtr.common.pipelike.cable.net.WorldENet;
import gtr.common.pipelike.cable.tile.TileEntityCable;
import gtr.common.pipelike.cable.tile.TileEntityCableTickable;
import gtr.common.render.CableRenderer;
import gtr.integration.ic2.IC2Handler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BlockCable extends BlockMaterialPipe<Insulation, WireProperties, WorldENet> implements ITileEntityProvider {

    private final Map<Material, WireProperties> enabledMaterials = new TreeMap<>();

    public BlockCable() {
        setHarvestLevel("cutter", 1);
    }

    public void addCableMaterial(Material material, WireProperties wireProperties) {
        Preconditions.checkNotNull(material, "material");
        Preconditions.checkNotNull(wireProperties, "wireProperties");
        Preconditions.checkArgument(Material.MATERIAL_REGISTRY.getNameForObject(material) != null, "material is not registered");
        this.enabledMaterials.put(material, wireProperties);
    }

    public Collection<Material> getEnabledMaterials() {
        return Collections.unmodifiableSet(enabledMaterials.keySet());
    }

    @Override
    public Class<Insulation> getPipeTypeClass() {
        return Insulation.class;
    }

    @Override
    protected WireProperties createProperties(Insulation insulation, Material material) {
        return insulation.modifyProperties(enabledMaterials.getOrDefault(material, getFallbackType()));
    }

    @Override
    protected WireProperties getFallbackType() {
        return enabledMaterials.values().iterator().next();
    }

    @Override
    public WorldENet getWorldPipeNet(World world) {
        return WorldENet.getWorldENet(world);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (Material material : enabledMaterials.keySet()) {
            for (Insulation insulation : Insulation.values()) {
                items.add(getItem(insulation, material));
            }
        }
    }

    @Override
    public int getActiveNodeConnections(IBlockAccess world, BlockPos nodePos, IPipeTile<Insulation, WireProperties> selfTileEntity) {
        int activeNodeConnections = 0;
        for (EnumFacing side : EnumFacing.VALUES) {
            BlockPos offsetPos = nodePos.offset(side);
            TileEntity tileEntity = world.getTileEntity(offsetPos);
            //do not connect to null cables and ignore cables
            if (tileEntity == null || getPipeTileEntity(tileEntity) != null) continue;
            EnumFacing opposite = side.getOpposite();
            if (tileEntity.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, opposite) != null ||
            tileEntity.getCapability(CapabilityEnergy.ENERGY, opposite) != null || IC2Handler.isAcceptable(tileEntity)) {
                activeNodeConnections |= 1 << side.getIndex();
            }
        }
        return activeNodeConnections;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        Insulation insulation = getPipeTileEntity(worldIn, pos).getPipeType();
        boolean damageOnLossless = ConfigHolder.doLosslessWiresDamage;
        if (!worldIn.isRemote && insulation.insulationLevel == -1 && entityIn instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entityIn;
            EnergyNet energyNet = getWorldPipeNet(worldIn).getNetFromPos(pos);
            if (energyNet != null && (damageOnLossless || energyNet.getAllNodes().get(pos).data.lossPerBlock > 0)) {
                long voltage = energyNet.getLastMaxVoltage();
                long amperage = energyNet.getLastAmperage();
                if (voltage > 0L && amperage > 0L) {
                    float damageAmount = (GTUtility.getTierByVoltage(voltage) + 1) * amperage * 4;
                    entityLiving.attackEntityFrom(DamageSources.getElectricDamage(), damageAmount);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return CableRenderer.BLOCK_RENDER_TYPE;
    }

    @Override
    public TileEntityPipeBase<Insulation, WireProperties> createNewTileEntity(boolean supportsTicking) {
        return supportsTicking ? new TileEntityCableTickable() : new TileEntityCable();
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Pair<TextureAtlasSprite, Integer> getParticleTexture(World world, BlockPos blockPos) {
        return CableRenderer.INSTANCE.getParticleTexture((TileEntityCable) world.getTileEntity(blockPos));
    }
}
