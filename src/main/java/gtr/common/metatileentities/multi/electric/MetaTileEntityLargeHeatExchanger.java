package gtr.common.metatileentities.multi.electric;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gtr.api.capability.GregtechCapabilities;
import gtr.api.capability.impl.FluidTankList;
import gtr.api.capability.tool.ISoftHammerItem;
import gtr.api.gui.IUIHolder;
import gtr.api.gui.ModularUI;
import gtr.api.gui.resources.TextureArea;
import gtr.api.gui.widgets.AdvancedTextWidget;
import gtr.api.gui.widgets.JeiOpenWidget;
import gtr.api.gui.widgets.StructureFormedWidget;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.IMultiblockPart;
import gtr.api.metatileentity.multiblock.MultiblockAbility;
import gtr.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gtr.api.multiblock.BlockPattern;
import gtr.api.multiblock.FactoryBlockPattern;
import gtr.api.multiblock.PatternMatchContext;
import gtr.api.recipes.RecipeMaps;
import gtr.api.render.ICubeRenderer;
import gtr.api.render.Textures;
import gtr.common.blocks.BlockBoilerCasing;
import gtr.common.blocks.BlockMetalCasing;
import gtr.common.blocks.MetaBlocks;
import gtr.common.tools.DamageValues;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MetaTileEntityLargeHeatExchanger extends RecipeMapMultiblockController implements IUIHolder {

    @Override
    public boolean isRemote() {
        return getWorld().isRemote;
    }

    @Override
    public void markAsDirty() {
        markDirty();
    }

    public MetaTileEntityLargeHeatExchanger(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.LARGE_HEAT_EXCHANGER_FUELS);
        this.recipeMapWorkable.setAllowOverclocking(false);
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityLargeHeatExchanger(metaTileEntityId);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {

        StructureFormedWidget structureFormed = new StructureFormedWidget(155, 64, 13, 13, this::isStructureFormed);

        AdvancedTextWidget text = new AdvancedTextWidget(10, 7, this::addDisplayText, 0xFFFFFF)
            .setMaxWidthLimit(156)
            .setClickHandler(this::handleDisplayClick);

        JeiOpenWidget recipes = new JeiOpenWidget(130, 59, 18, 18, RecipeMaps.LARGE_HEAT_EXCHANGER_FUELS);


        ModularUI.Builder builder = ModularUI.defaultBuilder();
        builder.image(0, 0, 176, 166, TextureArea.fullImage("textures/gui/multiblock/lhe.png"));
        builder.widget(text);

        return builder.widget(structureFormed).widget(recipes).bindPlayerInventory(entityPlayer.inventory, Textures.EMPTY, 7, 83)
            .build(this, entityPlayer);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            if (recipeMapWorkable.isActive()) {
                if (recipeMapWorkable.previousRecipe != null) {
                    System.out.println(recipeMapWorkable.previousRecipe.getDuration());
                }
            }
            //textList.add(new TextComponentTranslation("gtr.multiblock.large_boiler.steam_output", recipeMapWorkable.rec, 16000));
        }
    }

    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null ? 0 : (recipeMapWorkable.isActive() ? 15 : 0);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
            .aisle("CCC", "CCC", "CCC", "CCC")
            .aisle("CCC", "CPC", "CPC", "CCC")
            .aisle("CSC", "CCC", "CCC", "CCC")
            .setAmountAtLeast('C', 20)
            .where('S', selfPredicate())
            .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)))
            .where('C', statePredicate(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE)).or(abilityPartPredicate(
                MultiblockAbility.EXPORT_FLUIDS)).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
            .build();
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), recipeMapWorkable.isActive());
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        int importFluidsSize = abilities.getOrDefault(MultiblockAbility.IMPORT_FLUIDS, Collections.emptyList()).size();
        int exportFluidsSize = abilities.getOrDefault(MultiblockAbility.EXPORT_FLUIDS, Collections.emptyList()).size();
        return importFluidsSize >= 2 && exportFluidsSize >= 2;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.STABLE_TITANIUM_CASING;
    }

    @Override
    public boolean shouldRenderOverlay(IMultiblockPart sourcePart) {
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        ItemStack itemStack = playerIn.getHeldItem(hand);
        if(!itemStack.isEmpty() && itemStack.hasCapability(GregtechCapabilities.CAPABILITY_MALLET, null)) {
            ISoftHammerItem softHammerItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_MALLET, null);

            if (getWorld().isRemote) {
                return true;
            }
            if(!softHammerItem.damageItem(DamageValues.DAMAGE_FOR_SOFT_HAMMER, false)) {
                return false;
            }
        }
        return super.onRightClick(playerIn, hand, facing, hitResult);
    }
}
