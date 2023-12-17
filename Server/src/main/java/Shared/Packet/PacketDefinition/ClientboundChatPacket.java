package Shared.Packet.PacketDefinition;

import Shared.Packet.Packet;
import Shared.Packet.ServerPacketListener;
import Shared.Util.FriendlyByteR;

public class ClientboundChatPacket implements Packet<ServerPacketListener> {
    private String message;
    private String group;


    public ClientboundChatPacket(String group, String message) {
        this.group = group;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public String getGroup(){
        return group;
    }

    public void handle(ServerPacketListener packetListener){
        packetListener.handlePlayerChat(this);
    }

    public void write(FriendlyByteR friendlyByteBuf) {
        friendlyByteBuf.writeString(this.message);
        friendlyByteBuf.writeString(this.group);
    }

    public ClientboundChatPacket(FriendlyByteR friendlyByteBuf) {
        this.message = friendlyByteBuf.readString();
        this.group = friendlyByteBuf.readString();
    }
}
