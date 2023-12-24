package Network.Shared.Packet;

import Network.Server.ServerConnectionHandler;
import Network.Server.Player;
import Network.Shared.Packet.PacketDefinition.*;
import jade.RawMapData;
import Game.Server;

// Contains Handlers for every function
public class ServerPacketListener extends PacketListener {

    private Server server;
    private ServerConnectionHandler sch;
    private Player player;

    public ServerPacketListener(Server server, ServerConnectionHandler sch) {
        this.server = server;
        this.sch = sch;
    }

    // Serverbound packets handling methods
    public void handleLogin(ServerboundLoginPacket packet) {
        System.err.println("Login packet: " + packet.getAuthCode() + "-" + packet.getName());

        Player newPlayer = new Player(packet.getName(), packet.getAuthCode(), sch);
        this.player = newPlayer;
        server.addPlayer(newPlayer);
        ClientboundChatPacket cbcp = new ClientboundChatPacket("Network/Server", packet.getName() + ", has joined the server");

        RawMapData rmd = this.server.getRawMapData();
        ClientboundMapPacket cbmp = new ClientboundMapPacket(rmd.getMapName(), rmd.getWidth(), rmd.getHeight(), rmd.getNumLayers(), rmd.getLayerIDs(), rmd.getLayersData());
        sch.sendPacketsAll(cbcp);
        sch.sendPacket(cbmp);
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
        ClientboundChatPacket cbsp = new ClientboundChatPacket("Network/Server", player.getName() + ", has left the server");
        server.removePlayer(player);
        sch.sendPacketsAll(cbsp);
    }
}
