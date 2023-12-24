package Network;

import Network.Shared.Packet.Packet;
import Network.Shared.Packet.PacketListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.CountDownLatch;

// This is Connection.java from minecraft
public class ClientConnectionHandler extends SimpleChannelInboundHandler<Packet<?>> {

    private Channel channel;
    private PacketListener packetListener;
    private CountDownLatch latch = new CountDownLatch(1);

    //    Constructor takes in the game, to apply packet actions
    public ClientConnectionHandler() {}

    public void setPacketListener(PacketListener packetListener) {
        this.packetListener = packetListener;
    }

//    Runs when a connection is established
    public void channelActive(ChannelHandlerContext ctx) {
        this.channel = ctx.channel();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    //    Called when a packet is received
    public void channelRead0(ChannelHandlerContext ctx, Packet<?> packet) {
        genericsFtw(packet, this.packetListener);
//        ctx.close();
    }

//    Called by ChannelRead0
    private static <T extends PacketListener> void genericsFtw(Packet<T> packet, PacketListener packetListener) {
        packet.handle((T)packetListener);
    }

//    Called by _____ to send packet
    public void sendPacket(Packet<?> packet){
        this.channel.writeAndFlush(packet);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}