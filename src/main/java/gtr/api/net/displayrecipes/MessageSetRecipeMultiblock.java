package gtr.api.net.displayrecipes;

import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gtr.api.recipes.Recipe;
import gtr.api.recipes.RecipeMap;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public class MessageSetRecipeMultiblock implements IMessage {
    public MessageSetRecipeMultiblock(){}

    private BlockPos pos;
    private Recipe r;
    private RecipeMap<?> map;
    public MessageSetRecipeMultiblock(BlockPos pos, Recipe r, RecipeMap<?> map) {
        this.pos = pos;
        this.r = r;
        this.map = map;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(map.unlocalizedName.toCharArray().length);
        buf.writeCharSequence(map.unlocalizedName, StandardCharsets.UTF_8);
        buf.writeInt(map.getMap().get(r));
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        int nameLen = buf.readInt();
        String name = String.valueOf(buf.readCharSequence(nameLen, StandardCharsets.UTF_8));
        int value = buf.readInt();
        this.map = RecipeMap.getByName(name);
        Optional<Map.Entry<Recipe, Integer>> recipe = map.getEntries().stream().filter(entry -> entry.getValue()==value).findFirst();
        this.r = recipe.map(Map.Entry::getKey).orElse(null);
    }

    public static class MessageHandler implements IMessageHandler<MessageSetRecipeMultiblock, IMessage> {
        @Override
        public IMessage onMessage(MessageSetRecipeMultiblock message, MessageContext ctx) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (player.world.isBlockLoaded(message.pos)) {
                    TileEntity te = player.world.getTileEntity(message.pos);
                    if (te instanceof MetaTileEntityHolder) {
                        MetaTileEntity te2 = ((MetaTileEntityHolder) te).getMetaTileEntity();
                        if (te2 instanceof RecipeMapMultiblockController) {
                            ((RecipeMapMultiblockController) te2).displayRecipe = message.r;
                        }
                    }
                }
            });
            return null;
        }
    }
}