package Network.Shared.Packet;

import Game.GrassLanders;
import Network.Shared.Packet.PacketDefinition.*;
import jade.RawMapData;

// Contains Handlers for every function
public class ClientPacketListener extends PacketListener {

    private GrassLanders client;

    public ClientPacketListener(GrassLanders client) {
        this.client = client;
    }

    // Clientbound packets handling methods
    public void handlePlayerChat(ClientboundChatPacket packet) {
        System.out.println("[" + packet.getGroup() + "]: " + packet.getMessage());
    }
    public void handleMap(ClientboundMapPacket packet)  {
        System.err.println("Map packet received: " + packet.getMapName());
        RawMapData rawMap = new RawMapData(packet.getMapName(),
                packet.getWidth(),
                packet.getHeight(),
                "punyworld-overworld-tiles.xml",
                packet.getNumLayers());
        for (int i=0; i < packet.getNumLayers(); i++) {
            rawMap.addLayer(packet.getLayerIDs()[i], packet.getLayersData()[i]);
        }
        client.setRawMap(rawMap);
        client.setWaiting(false);
        client.startScene();
    }
}
