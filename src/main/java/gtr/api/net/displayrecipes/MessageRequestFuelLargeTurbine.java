package gtr.api.net.displayrecipes;

import gtr.GregTechMod;
import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRequestFuelLargeTurbine implements IMessage {
    public MessageRequestFuelLargeTurbine(){}

    private BlockPos pos;

    public MessageRequestFuelLargeTurbine(BlockPos pos) {
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

    public static class MessageHandler implements IMessageHandler<MessageRequestFuelLargeTurbine, IMessage> {
        @Override
        public IMessage onMessage(MessageRequestFuelLargeTurbine message, MessageContext ctx) {
            World w = ctx.getServerHandler().player.getServerWorld();
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                TileEntity te = w.getTileEntity(message.pos);
                if (te instanceof MetaTileEntityHolder) {
                    MetaTileEntity te2 = ((MetaTileEntityHolder) te).getMetaTileEntity();
                    if (te2 instanceof MetaTileEntityLargeTurbine && ((MetaTileEntityLargeTurbine) te2).importFluidHandler != null) {
                        for (IFluidTank t : ((MetaTileEntityLargeTurbine) te2).importFluidHandler) {
                            if (t.getFluid() != null && !t.getFluid().getUnlocalizedName().contains("lubricant")) {
                                if (!t.getFluid().getUnlocalizedName().contains("oxygen")) {
                                    GregTechMod.DISPLAY_INFO_WRAPPER.sendToAll(new MessageSetFuelDieselEngine(message.pos, t.getFluid().getFluid()));
                                }
                            }
                        }
                    }
                }
            });
            return null;
        }
    }
}