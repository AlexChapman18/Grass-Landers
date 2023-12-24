package Network;

import Game.GrassLanders;
import Network.Shared.Packet.ClientPacketListener;
import Network.Shared.Packet.PacketDecoder;
import Network.Shared.Packet.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ObjectClient {

    private int port;
    private String host;
    private GrassLanders client;
    private static ClientConnectionHandler cch;

    public ObjectClient(String host, int port, GrassLanders client) {
        this.port = port;
        this.host = host;
        this.client = client;
        cch = new ClientConnectionHandler();
        cch.setPacketListener(new ClientPacketListener(client));
    }


    public ClientConnectionHandler getCch(){
        return cch;
    }

    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
//                                    Decodes the packet, then calls ConnectionClientHandler to see what to do
                            new ProtobufVarint32FrameDecoder(),
                            new PacketDecoder(),
                            new ProtobufVarint32LengthFieldPrepender(),
                            new PacketEncoder(),
                            cch);
                }
            });

            // Start the connection attempt.
            b.connect(this.host, this.port).sync().channel();
            client.connected();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}