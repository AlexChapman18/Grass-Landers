package Network.Server;

import io.netty.channel.Channel;

public class Player {

    private Channel channel;
    private ServerConnectionHandler sch;
    private int authCode;
    private String name;

    public Player(Channel channel) {
        this.channel = channel;
    }

    public Player(String name, int authCode, ServerConnectionHandler sch) {
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
