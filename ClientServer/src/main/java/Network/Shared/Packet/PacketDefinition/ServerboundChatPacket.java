package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.Packet;
import Network.Shared.Packet.ServerPacketListener;
import Network.Shared.Util.FriendlyByteR;

public class ServerboundChatPacket implements Packet<ServerPacketListener> {
    private String message;
    private String group;


    // Constructors
    public ServerboundChatPacket(String group, String message) {
        this.group = group;
        this.message = message;
    }
    public ServerboundChatPacket(FriendlyByteR friendlyByteBuf) {
        this.message = friendlyByteBuf.readString();
        this.group = friendlyByteBuf.readString();
    }


    // Handle and write
    public void handle(ServerPacketListener packetListener){
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
