package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.ClientPacketListener;
import Network.Shared.Packet.Packet;
import Network.Shared.Util.FriendlyByteR;

public class ServerboundDisconnectPacket implements Packet<ClientPacketListener> {

    public ServerboundDisconnectPacket() {}

    public void handle(ClientPacketListener packetListener){
        packetListener.handleDisconnect(this);
    }

    public void write(FriendlyByteR friendlyByteBuf) {}

    public ServerboundDisconnectPacket(FriendlyByteR friendlyByteBuf) {}
}
