package Server;

import Shared.Packet.Packet;
import Shared.Packet.PacketDefinition.ClientboundChatPacket;
import Shared.Packet.PacketDefinition.ServerboundDisconnectPacket;
import Shared.Packet.PacketListener;
import Shared.Packet.ServerPacketListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

// BRAND NEW INSTANCE FOR EVERY CLIENT REMEMBER THISSSS
// BRAND NEW INSTANCE FOR EVERY CLIENT REMEMBER THISSSS
// BRAND NEW INSTANCE FOR EVERY CLIENT REMEMBER THISSSS
// Is the server, and defines what to do when, i.e. if a connection is made?
public class ServerConnectionHandler extends SimpleChannelInboundHandler<Packet<?>> {
//    Shared accross all handlers
    static final ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private Channel channel;
    private SocketAddress address;
    private PacketListener packetListener;

    public ServerConnectionHandler(Server server) {
        this.packetListener = new ServerPacketListener(server, this);
    }

//    Will be invoked when connection has been established and ready to generate traffic
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        Set address and channel
        this.channel = ctx.channel();
        this.address = this.channel.remoteAddress();

//        Send welcome packet and add to the list of channels
        Packet packet = new ClientboundChatPacket("Server", "Welcome to the server");
        sendPacket(packet);
        allChannels.add(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        genericsFtw(new ServerboundDisconnectPacket(), this.packetListener);
        ctx.close();
    }

    //    Called when a packet is received
    public void channelRead0(ChannelHandlerContext ctx, Packet<?> packet) {
        genericsFtw(packet, this.packetListener);
    }

//    Called by ChannelRead0
    private static <T extends PacketListener> void genericsFtw(Packet<T> packet, PacketListener packetListener) {
        packet.handle((T)packetListener);
    }

//    send packet to this connection
    public void sendPacket(Packet<?> packet){
        this.channel.writeAndFlush(packet);
    }

//    Sends packets to all clients
    public void sendPacketsAll(Packet<?> packet){
        for (Channel c : allChannels) {
            c.writeAndFlush(packet);
        }
    }

//    Sends packets to all clients but this connection
    public void sendPacketsAllBut(Packet<?> packet){
        for (Channel c : allChannels) {
            if (c != this.channel){
                c.writeAndFlush(packet);
            }
        }
    }





    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}