package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.ClientPacketListener;
import Network.Shared.Packet.Packet;
import Network.Shared.Packet.ServerPacketListener;
import Network.Shared.Util.FriendlyByteR;

public class ServerboundDisconnectPacket implements Packet<ServerPacketListener> {


    // Constructors
    public ServerboundDisconnectPacket() {}
    public ServerboundDisconnectPacket(FriendlyByteR friendlyByteBuf) {}


    // Handle and write
    public void handle(ServerPacketListener packetListener){
        packetListener.handleDisconnect(this);
    }
    public void write(FriendlyByteR friendlyByteBuf) {}
}
