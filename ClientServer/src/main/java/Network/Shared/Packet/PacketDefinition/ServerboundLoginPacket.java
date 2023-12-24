package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.ClientPacketListener;
import Network.Shared.Packet.Packet;
import Network.Shared.Packet.ServerPacketListener;
import Network.Shared.Util.FriendlyByteR;

public class ServerboundLoginPacket implements Packet<ServerPacketListener> {
    private int authCode;
    private String name;


    // Constructors
    public ServerboundLoginPacket(int authCode, String name) {
        this.authCode = authCode;
        this.name = name;
    }
    public ServerboundLoginPacket(FriendlyByteR friendlyByteBuf) {
        this.authCode = friendlyByteBuf.readInt();
        this.name = friendlyByteBuf.readString();
    }


    // Handle and write
    public int getAuthCode() {
        return authCode;
    }
    public String getName() {
        return name;
    }


    // Getters
    public void handle(ServerPacketListener packetListener){
        packetListener.handleLogin(this);
    }
    public void write(FriendlyByteR friendlyByteBuf) {
        friendlyByteBuf.writeInt(this.authCode);
        friendlyByteBuf.writeString(this.name);
    }
}
