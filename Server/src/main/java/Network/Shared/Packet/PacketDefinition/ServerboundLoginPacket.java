package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.Packet;
import Network.Shared.Packet.ServerPacketListener;
import Network.Shared.Util.FriendlyByteR;

public class ServerboundLoginPacket implements Packet<ServerPacketListener> {
    private int authCode;
    private String name;


    public ServerboundLoginPacket(int authCode, String name) {
        this.authCode = authCode;
        this.name = name;
    }

    public void handle(ServerPacketListener packetListener){
        packetListener.handleLogin(this);
    }

    public int getAuthCode() {
        return authCode;
    }
    public String getName() {
        return name;
    }

    public void write(FriendlyByteR friendlyByteBuf) {
        friendlyByteBuf.writeInt(this.authCode);
        friendlyByteBuf.writeString(this.name);
    }

    public ServerboundLoginPacket(FriendlyByteR friendlyByteBuf) {
        this.authCode = friendlyByteBuf.readInt();
        this.name = friendlyByteBuf.readString();
    }
}
