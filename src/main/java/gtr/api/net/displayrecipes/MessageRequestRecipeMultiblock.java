package gtr.api.net.displayrecipes;

import gtr.GregTechMod;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.api.metatileentity.multiblock.RecipeMapMultiblockController;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRequestRecipeMultiblock implements IMessage {
    public MessageRequestRecipeMultiblock(){}

    private BlockPos pos;

    public MessageRequestRecipeMultiblock(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
    }

    public static class MessageHandler implements IMessageHandler<MessageRequestRecipeMultiblock, IMessage> {
        @Override
        public IMessage onMessage(MessageRequestRecipeMultiblock message, MessageContext ctx) {
            World w = ctx.getServerHandler().player.getServerWorld();
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                TileEntity te = w.getTileEntity(message.pos);
                if (te instanceof MetaTileEntityHolder) {
                    MetaTileEntity te2 = ((MetaTileEntityHolder) te).getMetaTileEntity();
                    if (te2 instanceof RecipeMapMultiblockController) {
                        RecipeMapMultiblockController f = (RecipeMapMultiblockController) te2;
                        if (f.recipeMapWorkable.previousRecipe != null) {
                            GregTechMod.DISPLAY_INFO_WRAPPER.sendToAll(new MessageSetRecipeMultiblock(f.getPos(), f.recipeMapWorkable.previousRecipe, f.recipeMap));
                        }
                    }
                }
            });
            return null;
        }
    }
}