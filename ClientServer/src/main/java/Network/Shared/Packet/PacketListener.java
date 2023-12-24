package Network.Shared.Packet;

import Network.Shared.Packet.PacketDefinition.*;

//    Functions/variable all Packet Listeners should have
public abstract class PacketListener {

    public void handlePlayerChat(ClientboundChatPacket packet) {}
    public void handlePlayerChat(ServerboundChatPacket packet) {}

    public void handleLogin(ServerboundLoginPacket packet) {}

    public void handleMap(ClientboundMapPacket packet)  {}

    public void handleDisconnect(ServerboundDisconnectPacket packet) {}
}