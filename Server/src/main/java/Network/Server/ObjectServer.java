package Network.Server;

import Network.Shared.Packet.PacketEncoder;
import Network.Shared.Packet.PacketDecoder;
import Server.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ObjectServer {

    private int port;
    private Server server;
    private static ServerConnectionHandler sch;

//    Sets the server port and server object
    public ObjectServer(int port, Server server) {
        this.port = port;
        this.server = server;
    }

//    Creates the connection
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
//                                    Calls ConnectionServerHandler, then Encodes the packet and sends it off
                                    new PacketEncoder(),
                                    new PacketDecoder(),
                                    new ServerConnectionHandler(server));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

//           Bind and start to accept incoming connections.
            b.bind(port).sync().channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}