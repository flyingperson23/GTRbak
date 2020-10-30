package gtr.api.net.displayrecipes;

import gtr.api.metatileentity.MetaTileEntity;
import gtr.api.metatileentity.MetaTileEntityHolder;
import gtr.common.metatileentities.multi.electric.generator.MetaTileEntityDieselEngine;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetFuelDieselEngine implements IMessage {
    public MessageSetFuelDieselEngine(){}

    private BlockPos pos;
    private String fuel;

    public MessageSetFuelDieselEngine(BlockPos pos, Fluid fuel) {
        this.pos = pos;
        this.fuel = FluidRegistry.getFluidName(fuel);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer buf2 = new PacketBuffer(buf);
        buf2.writeLong(pos.toLong());
        buf2.writeInt(fuel.toCharArray().length);
        buf2.writeString(fuel);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer buf2 = new PacketBuffer(buf);
        pos = BlockPos.fromLong(buf2.readLong());
        int len = buf2.readInt();
        fuel = buf2.readString(len);
    }

    public static class MessageHandler implements IMessageHandler<MessageSetFuelDieselEngine, IMessage> {
        @Override
        public IMessage onMessage(MessageSetFuelDieselEngine message, MessageContext ctx) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (player.world.isBlockLoaded(message.pos)) {
                    TileEntity te = player.world.getTileEntity(message.pos);
                    if (te instanceof MetaTileEntityHolder) {
                        MetaTileEntity te2 = ((MetaTileEntityHolder) te).getMetaTileEntity();
                        if (te2 instanceof MetaTileEntityDieselEngine) {
                            ((MetaTileEntityDieselEngine) te2).displayFluid = FluidRegistry.getFluidStack(message.fuel, 1000);
                        }
                    }
                }
            });
            return null;
        }
    }
}