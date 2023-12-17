package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.Packet;
import Network.Shared.Packet.ServerPacketListener;
import Network.Shared.Util.FriendlyByteR;

public class ServerboundDisconnectPacket implements Packet<ServerPacketListener> {

    public ServerboundDisconnectPacket() {}

    public void handle(ServerPacketListener packetListener){
        packetListener.handleDisconnect(this);
    }

    public void write(FriendlyByteR friendlyByteBuf) {}

    public ServerboundDisconnectPacket(FriendlyByteR friendlyByteBuf) {}
}
