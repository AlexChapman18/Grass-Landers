package Network.Shared.Packet;

import Server.Server;
import Network.Server.ServerConnectionHandler;
import Network.Shared.Packet.PacketDefinition.*;
import Network.Server.ServerPlayer;
import Server.RawMapData;

// Contains Handlers for every function
public class ServerPacketListener implements PacketListener {

    private Server server;
    private ServerConnectionHandler sch;
    private ServerPlayer serverPlayer;

    public ServerPacketListener(Server server, ServerConnectionHandler sch) {
        this.server = server;
        this.sch = sch;
    }

    public void handleLogin(ServerboundLoginPacket packet) {
        System.err.println("Login packet: " + packet.getAuthCode() + "-" + packet.getName());

        ServerPlayer newPlayer = new ServerPlayer(packet.getName(), packet.getAuthCode(), sch);
        this.serverPlayer = newPlayer;
        server.addPlayer(newPlayer);
        ClientboundChatPacket cbcp = new ClientboundChatPacket("Network/Server", packet.getName() + ", has joined the server");

        RawMapData rmd = this.server.getRawMapData();
        ClientboundMapPacket cbmp = new ClientboundMapPacket(rmd.getMapName(), rmd.getWidth(), rmd.getHeight(), rmd.getNumLayers(), rmd.getLayerIDs(), rmd.getLayersData());
        sch.sendPacketsAll(cbcp);
        sch.sendPacket(cbmp);
    }

    public void handlePlayerChat(ClientboundChatPacket packet) {
    }
    public void handlePlayerChat(ServerboundChatPacket packet) {
        if (packet.getMessage().equals("/cringe")){
            ClientboundChatPacket cbcp = new ClientboundChatPacket("Network/Server", "You Smell");
            sch.sendPacket(cbcp);
        } else if (packet.getMessage().equals("/getPlayers")) {
            ClientboundChatPacket cbcp = new ClientboundChatPacket("Network/Server", server.getPlayers().stream().toList().toString());
            sch.sendPacket(cbcp);
        } else{
            System.err.println(packet.getGroup() + ":" + packet.getMessage());
            ClientboundChatPacket cbcp = new ClientboundChatPacket(packet.getGroup(), packet.getMessage());
            sch.sendPacketsAll(cbcp);
        }
    }

    public void handleDisconnect(ServerboundDisconnectPacket packet) {
        ClientboundChatPacket cbsp = new ClientboundChatPacket("Network/Server", serverPlayer.getName() + ", has left the server");
        server.removePlayer(serverPlayer);
        sch.sendPacketsAll(cbsp);
    }

    public void handleMap(ClientboundMapPacket packet) {}
}
