package Shared.Packet.PacketDefinition;

import Shared.Packet.Packet;
import Shared.Packet.ServerPacketListener;
import Shared.Util.FriendlyByteR;

public class ServerboundDisconnectPacket implements Packet<ServerPacketListener> {

    public ServerboundDisconnectPacket() {}

    public void handle(ServerPacketListener packetListener){
        packetListener.handleDisconnect(this);
    }

    public void write(FriendlyByteR friendlyByteBuf) {}

    public ServerboundDisconnectPacket(FriendlyByteR friendlyByteBuf) {}
}
