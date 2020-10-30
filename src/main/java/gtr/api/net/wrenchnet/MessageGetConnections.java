package gtr.api.net.wrenchnet;

import gtr.api.items.toolitem.wrenchcompat.ICompatBase;
import gtr.GregTechMod;
import gtr.api.util.GTUtility;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static gtr.common.render.WrenchOverlayHandler.BlockWrapper;

public class MessageGetConnections implements IMessage {
    public MessageGetConnections(){}

    private BlockPos pos;
    private int id;
    public MessageGetConnections(BlockPos pos, int id) {
        this.pos = pos;
        this.id = id;
    }

    @Override public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(id);
    }

    @Override public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        id = buf.readInt();
    }

    public static class MessageHandler implements IMessageHandler<MessageGetConnections, IMessage> {

        @Override public IMessage onMessage(MessageGetConnections message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                ICompatBase compat = GregTechMod.instance.wrenchHandler.COMPAT_LIST.get(message.id);
                BlockWrapper block = new BlockWrapper(message.pos, serverPlayer);
                if (compat.isAcceptable(GTUtility.getTE(block))) {
                    ConnectionBlock connections = new ConnectionBlock(block.pos, compat.getConnections(GTUtility.getTE(block)));
                    GregTechMod.WRENCH_NET_WRAPPER.sendTo(new MessageReturnConnections(connections), serverPlayer);
                }
            });
            return null;
        }
    }
}