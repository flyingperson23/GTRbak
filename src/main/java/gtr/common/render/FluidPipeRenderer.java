package gtr.common.render;

import codechicken.lib.render.BlockRenderer;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.block.BlockRenderingRegistry;
import codechicken.lib.render.block.ICCBlockRenderer;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Translation;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.uv.IconTransformation;
import gtr.api.GTValues;
import gtr.api.cover.ICoverable;
import gtr.api.pipenet.tile.IPipeTile;
import gtr.api.unification.material.type.Material;
import gtr.api.util.GTLog;
import gtr.api.util.GTUtility;
import gtr.api.util.ModCompatibility;
import gtr.common.pipelike.fluidpipe.BlockFluidPipe;
import gtr.common.pipelike.fluidpipe.FluidPipeProperties;
import gtr.common.pipelike.fluidpipe.FluidPipeType;
import gtr.common.pipelike.fluidpipe.ItemBlockFluidPipe;
import gtr.common.pipelike.fluidpipe.tile.TileEntityFluidPipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

public class FluidPipeRenderer implements ICCBlockRenderer, IItemRenderer {


    public static ModelResourceLocation MODEL_LOCATION = new ModelResourceLocation(new ResourceLocation(GTValues.MODID, "fluid_pipe"), "normal");
    public static FluidPipeRenderer INSTANCE = new FluidPipeRenderer();
    public static EnumBlockRenderType BLOCK_RENDER_TYPE;
    private static ThreadLocal<BlockRenderer.BlockFace> blockFaces = ThreadLocal.withInitial(BlockRenderer.BlockFace::new);

    //private TextureAtlasSprite[] insulationTextures = new TextureAtlasSprite[6];
    //private TextureAtlasSprite wireTexture;


    private Map<FluidPipeType, TextureAtlasSprite> pipeTextures = new HashMap<>();
    private Map<FluidPipeType, TextureAtlasSprite> connectionTextures = new HashMap<>();

    public static void preInit() {
        BLOCK_RENDER_TYPE = BlockRenderingRegistry.createRenderType("gt_fluid_pipe");
        BlockRenderingRegistry.registerRenderer(BLOCK_RENDER_TYPE, INSTANCE);
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        TextureUtils.addIconRegister(INSTANCE::registerIcons);
    }

    public void registerIcons(TextureMap map) {
        GTLog.logger.info("Registering fluid pipe textures.");

        pipeTextures.put(FluidPipeType.TINY_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_tiny_side")));
        pipeTextures.put(FluidPipeType.SMALL_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_small_side")));
        pipeTextures.put(FluidPipeType.MEDIUM_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_medium_side")));
        pipeTextures.put(FluidPipeType.LARGE_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_large_side")));

        connectionTextures.put(FluidPipeType.TINY_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_tiny_in")));
        connectionTextures.put(FluidPipeType.SMALL_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_small_in")));
        connectionTextures.put(FluidPipeType.MEDIUM_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_medium_in")));
        connectionTextures.put(FluidPipeType.LARGE_OPAQUE, map.registerSprite(new ResourceLocation(GTValues.MODID, "blocks/pipe/pipe_large_in")));
    }

    @SubscribeEvent
    public void onModelsBake(ModelBakeEvent event) {
        GTLog.logger.info("Injected fluid pipe render model");
        event.getModelRegistry().putObject(MODEL_LOCATION, this);
    }

    private int getPipeColor(Material material, int insulationColor) {
        if(insulationColor == IPipeTile.DEFAULT_INSULATION_COLOR) {
            return material.materialRGB;
        } else return insulationColor;
    }


    @Override
    public void renderItem(ItemStack rawItemStack, TransformType transformType) {
        ItemStack stack = ModCompatibility.getRealItemStack(rawItemStack);
        if (!(stack.getItem() instanceof ItemBlockFluidPipe)) {
            return;
        }
        GlStateManager.enableBlend();
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.startDrawing(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        BlockFluidPipe blockFluidPipe = (BlockFluidPipe) ((ItemBlockFluidPipe) stack.getItem()).getBlock();
        FluidPipeType type = blockFluidPipe.getItemPipeType(stack);
        Material material = blockFluidPipe.getItemMaterial(stack);
        if (type != null && material != null) {

            renderFluidPipeBlock(type, material, IPipeTile.DEFAULT_INSULATION_COLOR, renderState, new IVertexOperation[0],
                1 << EnumFacing.SOUTH.getIndex() | 1 << EnumFacing.NORTH.getIndex() |
                    1 << (6 + EnumFacing.SOUTH.getIndex()) | 1 << (6 + EnumFacing.NORTH.getIndex()));
        }
        renderState.draw();
        GlStateManager.disableBlend();
    }

    @Override
    public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        renderState.setBrightness(world, pos);
        IVertexOperation[] pipeline = {new Translation(pos)};

        BlockFluidPipe blockFluidPipe = (BlockFluidPipe) state.getBlock();
        TileEntityFluidPipe tileEntityFluidPipe = (TileEntityFluidPipe) blockFluidPipe.getPipeTileEntity(world, pos);
        if (tileEntityFluidPipe == null) return false;
        int paintingColor = tileEntityFluidPipe.getInsulationColor();
        int connectedSidesMask = blockFluidPipe.getActualConnections(tileEntityFluidPipe, world);
        Material material = tileEntityFluidPipe.getPipeMaterial();
        BlockRenderLayer renderLayer = MinecraftForgeClient.getRenderLayer();
        if (renderLayer == BlockRenderLayer.CUTOUT) {
            renderFluidPipeBlock(tileEntityFluidPipe.getPipeType(), material, paintingColor, renderState, pipeline, connectedSidesMask);
        }
        ICoverable coverable = tileEntityFluidPipe.getCoverableImplementation();
        coverable.renderCovers(renderState, new Matrix4().translate(pos.getX(), pos.getY(), pos.getZ()), renderLayer);
        return true;
    }

    public void renderFluidPipeBlock(FluidPipeType type, Material material, int insulationColor1, CCRenderState state, IVertexOperation[] pipeline, int connectMask) {
        int wireColor = GTUtility.convertRGBtoOpaqueRGBA_CL(material.materialRGB);
        float thickness = type.getThickness();

        IVertexOperation[] wire = ArrayUtils.addAll(pipeline, new IconTransformation(connectionTextures.get(type)), new ColourMultiplier(wireColor));
        IVertexOperation[] overlays;
        IVertexOperation[] insulation;

        int pipeColor = GTUtility.convertRGBtoOpaqueRGBA_CL(getPipeColor(material, insulationColor1));
        ColourMultiplier multiplier = new ColourMultiplier(pipeColor);
        insulation = ArrayUtils.addAll(pipeline, new IconTransformation(pipeTextures.get(type)), multiplier);
        overlays = ArrayUtils.addAll(pipeline, new IconTransformation(connectionTextures.get(type)), multiplier);

        Cuboid6 cuboid6 = BlockFluidPipe.getSideBox(null, thickness);
        for (EnumFacing renderedSide : EnumFacing.VALUES) {
            if ((connectMask & 1 << renderedSide.getIndex()) == 0) {
                int oppositeIndex = renderedSide.getOpposite().getIndex();
                if ((connectMask & 1 << oppositeIndex) > 0 && (connectMask & ~(1 << oppositeIndex)) == 0) {
                    //if there is something on opposite side, render overlay + wire
                    renderFluidPipeSide(state, wire, renderedSide, cuboid6);
                    renderFluidPipeSide(state, overlays, renderedSide, cuboid6);
                } else {
                    renderFluidPipeSide(state, insulation, renderedSide, cuboid6);
                }
            }
        }

        renderFluidPipeSide(connectMask, state, insulation, wire, overlays, EnumFacing.DOWN, thickness);
        renderFluidPipeSide(connectMask, state, insulation, wire, overlays, EnumFacing.UP, thickness);
        renderFluidPipeSide(connectMask, state, insulation, wire, overlays, EnumFacing.WEST, thickness);
        renderFluidPipeSide(connectMask, state, insulation, wire, overlays, EnumFacing.EAST, thickness);
        renderFluidPipeSide(connectMask, state, insulation, wire, overlays, EnumFacing.NORTH, thickness);
        renderFluidPipeSide(connectMask, state, insulation, wire, overlays, EnumFacing.SOUTH, thickness);
    }

    private static void renderFluidPipeSide(int connections, CCRenderState renderState, IVertexOperation[] pipeline, IVertexOperation[] wire, IVertexOperation[] overlays, EnumFacing side, float thickness) {
        if ((connections & 1 << side.getIndex()) > 0) {
            boolean renderFrontSide = (connections & 1 << (6 + side.getIndex())) > 0;
            Cuboid6 cuboid6 = BlockFluidPipe.getSideBox(side, thickness);
            for (EnumFacing renderedSide : EnumFacing.VALUES) {
                if (renderedSide == side) {
                    if (renderFrontSide) {
                        renderFluidPipeSide(renderState, wire, renderedSide, cuboid6);
                        renderFluidPipeSide(renderState, overlays, renderedSide, cuboid6);
                    }
                } else if (renderedSide != side.getOpposite()) {
                    renderFluidPipeSide(renderState, pipeline, renderedSide, cuboid6);
                }
            }
        }
    }

    private static void renderFluidPipeSide(CCRenderState renderState, IVertexOperation[] pipeline, EnumFacing side, Cuboid6 cuboid6) {
        BlockRenderer.BlockFace blockFace = blockFaces.get();
        blockFace.loadCuboidFace(cuboid6, side.getIndex());
        renderState.setPipeline(blockFace, 0, blockFace.verts.length, pipeline);
        renderState.render();
    }

    @Override
    public void renderBrightness(IBlockState state, float brightness) {
    }

    @Override
    public void handleRenderBlockDamage(IBlockAccess world, BlockPos pos, IBlockState state, TextureAtlasSprite sprite, BufferBuilder buffer) {
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        renderState.setPipeline(new Vector3(new Vec3d(pos)).translation(), new IconTransformation(sprite));
        BlockFluidPipe blockFluidPipe = (BlockFluidPipe) state.getBlock();
        IPipeTile<FluidPipeType, FluidPipeProperties> tileEntityFluidPipe = blockFluidPipe.getPipeTileEntity(world, pos);
        if (tileEntityFluidPipe == null) {
            return;
        }
        FluidPipeType type = tileEntityFluidPipe.getPipeType();
        if (type == null) {
            return;
        }
        float thickness = type.getThickness();
        int connectedSidesMask = blockFluidPipe.getActualConnections(tileEntityFluidPipe, world);
        Cuboid6 baseBox = BlockFluidPipe.getSideBox(null, thickness);
        BlockRenderer.renderCuboid(renderState, baseBox, 0);
        for (EnumFacing renderSide : EnumFacing.VALUES) {
            if ((connectedSidesMask & (1 << renderSide.getIndex())) > 0) {
                Cuboid6 sideBox = BlockFluidPipe.getSideBox(renderSide, thickness);
                BlockRenderer.renderCuboid(renderState, sideBox, 0);
            }
        }
    }

    @Override
    public void registerTextures(TextureMap map) {
    }

    @Override
    public IModelState getTransforms() {
        return TransformUtils.DEFAULT_BLOCK;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return TextureUtils.getMissingSprite();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    public Pair<TextureAtlasSprite, Integer> getParticleTexture(IPipeTile<FluidPipeType, FluidPipeProperties> tileEntity) {
        if (tileEntity == null) {
            return Pair.of(TextureUtils.getMissingSprite(), 0xFFFFFF);
        }
        Material material = ((TileEntityFluidPipe) tileEntity).getPipeMaterial();
        FluidPipeType type = tileEntity.getPipeType();
        if (material == null || type == null) {
            return Pair.of(TextureUtils.getMissingSprite(), 0xFFFFFF);
        }
        TextureAtlasSprite atlasSprite;
        int particleColor;
        atlasSprite = pipeTextures.get(type);
        particleColor = tileEntity.getInsulationColor();
        return Pair.of(atlasSprite, particleColor);
    }


}
