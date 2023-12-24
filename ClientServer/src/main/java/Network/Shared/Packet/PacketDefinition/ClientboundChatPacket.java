package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.ClientPacketListener;
import Network.Shared.Packet.Packet;
import Network.Shared.Util.FriendlyByteR;

public class ClientboundChatPacket implements Packet<ClientPacketListener> {
    private String message;
    private String group;


    // Constructors
    public ClientboundChatPacket(String group, String message) {
        this.group = group;
        this.message = message;
    }
    public ClientboundChatPacket(FriendlyByteR friendlyByteBuf) {
        this.message = friendlyByteBuf.readString();
        this.group = friendlyByteBuf.readString();
    }


    // Handle and write
    public void handle(ClientPacketListener packetListener){
        packetListener.handlePlayerChat(this);
    }
    public void write(FriendlyByteR friendlyByteBuf) {
        friendlyByteBuf.writeString(this.message);
        friendlyByteBuf.writeString(this.group);
    }


    // Getters
    public String getMessage() {
        return message;
    }
    public String getGroup(){
        return group;
    }
}
