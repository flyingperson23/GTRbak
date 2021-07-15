package gtr.integration.betterpipes.network;

import crafttweaker.api.block.IBlock;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBlockUpdate implements IMessage {
    public MessageBlockUpdate(){}

    private BlockPos pos;
    public MessageBlockUpdate(BlockPos pos) {
        this.pos = pos;
    }

    @Override public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
    }

    @Override public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
    }

    public static class MessageHandler implements IMessageHandler<MessageBlockUpdate, IMessage> {

        @Override public IMessage onMessage(MessageBlockUpdate message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                World world = serverPlayer.getServerWorld();
                BlockPos currentPos;
                IBlockState state;
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        for (int z = -1; z < 2; z++) {
                            currentPos = message.pos.add(x, y, z);
                            state = world.getBlockState(currentPos);
                            world.notifyBlockUpdate(currentPos, state, state, 3);
                            TileEntity te = world.getTileEntity(currentPos);
                            if (te != null) {
                                te.markDirty();
                            }
                        }
                    }
                }
            });
            return null;
        }
    }
}