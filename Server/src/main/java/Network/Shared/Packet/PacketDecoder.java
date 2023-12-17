package Network.Shared.Packet;

import Network.Shared.Util.FriendlyByteR;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

// Decodes the packet, based on the packetID
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        FriendlyByteR friendlyIn = new FriendlyByteR(in);
        int packetID = friendlyIn.readInt();
        out.add(PacketClassConverter.ID_TO_CONSTRUCTOR.get(packetID).apply(friendlyIn));
    }
}
