package Shared.Packet;

import Server.Server;
import Server.ServerConnectionHandler;
import Server.ServerPlayer;
import Shared.Packet.PacketDefinition.ClientboundChatPacket;
import Shared.Packet.PacketDefinition.ServerboundChatPacket;
import Shared.Packet.PacketDefinition.ServerboundDisconnectPacket;
import Shared.Packet.PacketDefinition.ServerboundLoginPacket;

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
        System.err.println("Login packet: " + packet.getAuthCode() + packet.getName());

        ServerPlayer newPlayer = new ServerPlayer(packet.getName(), packet.getAuthCode(), sch);
        this.serverPlayer = newPlayer;
        server.addPlayer(newPlayer);

        ClientboundChatPacket cbsp = new ClientboundChatPacket("Server", packet.getName() + ", has joined the server");
        sch.sendPacketsAll(cbsp);
    }

    //    Handler function for ChatPacket
    public void handlePlayerChat(ServerboundChatPacket packet) {
        if (packet.getMessage().equals("/cringe")){
            ClientboundChatPacket cbcp = new ClientboundChatPacket("Server", "You Smell");
            sch.sendPacket(cbcp);
        } else if (packet.getMessage().equals("/getPlayers")) {
            ClientboundChatPacket cbcp = new ClientboundChatPacket("Server", server.getPlayers().stream().toList().toString());
            sch.sendPacket(cbcp);
        } else{
            System.err.println(packet.getGroup() + ":" + packet.getMessage());
            ClientboundChatPacket cbcp = new ClientboundChatPacket(packet.getGroup(), packet.getMessage());
            sch.sendPacketsAll(cbcp);
        }

    }

    public void handleDisconnect(ServerboundDisconnectPacket packet) {
        server.removePlayer(serverPlayer);
        ClientboundChatPacket cbsp = new ClientboundChatPacket("Server", serverPlayer.getName() + ", has left the server");
        sch.sendPacketsAll(cbsp);
    }
}
