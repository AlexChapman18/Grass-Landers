package Network.Server;

import io.netty.channel.Channel;

public class ServerPlayer {

    private Channel channel;
    private ServerConnectionHandler sch;
    private int authCode;
    private String name;

    public ServerPlayer(Channel channel) {
        this.channel = channel;
    }

    public ServerPlayer(String name, int authCode, ServerConnectionHandler sch) {
        this.name = name;
        this.authCode = authCode;
        this.sch = sch;
    }

    public ServerConnectionHandler getSch() {
        return sch;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }
}
