package Shared.Packet;

import Shared.Util.FriendlyByteR;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet<?>> {
    
//    Encodes the packet object into bytes
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet<?> packet, ByteBuf out) {
        int packetID = PacketClassConverter.CLASS_TO_ID.get(packet.getClass());
        FriendlyByteR friendlyByteBuf = new FriendlyByteR(out);
        friendlyByteBuf.writeVarInt(packetID);
        packet.write(friendlyByteBuf);
    }

}
