package Shared.Packet.PacketDefinition;

import Shared.Packet.Packet;
import Shared.Packet.ServerPacketListener;
import Shared.Util.FriendlyByteR;

public class ClientboundMapPacket implements Packet<ServerPacketListener> {

    private String mapName;
    private int width;
    private int height;
    private int numLayers;
    private int[] layerIDs;
    private int[][] layersData;


    public ClientboundMapPacket(String mapName, int width, int height, int numLayers, int[] layerIDs, int[][] layersData) {
        this.mapName = mapName;
        this.width = width;
        this.height = height;
        this.numLayers = numLayers;
        this.layerIDs = layerIDs;
        this.layersData = layersData;
    }

    public String getMapName(){
        return this.mapName;
    }

    public void handle(ServerPacketListener packetListener){
        packetListener.handleMap(this);
    }

    public void write(FriendlyByteR friendlyByteBuf) {
        friendlyByteBuf.writeString(this.mapName);
        friendlyByteBuf.writeInt(width);
        friendlyByteBuf.writeInt(height);
        friendlyByteBuf.writeInt(numLayers);
        for (int i=0; i < this.numLayers; i++) {
            friendlyByteBuf.writeInt(this.layerIDs[i]);
            friendlyByteBuf.writeIntArray(this.layersData[i]);
        }
    }

    public ClientboundMapPacket(FriendlyByteR friendlyByteBuf) {
        this.mapName = friendlyByteBuf.readString();
        this.width = friendlyByteBuf.readInt();
        this.height = friendlyByteBuf.readInt();
        this.numLayers = friendlyByteBuf.readInt();
        this.layerIDs = new int[this.numLayers];
        this.layersData = new int[this.width * this.height][this.numLayers];
        for (int i=0; i < this.numLayers; i++) {
            this.layerIDs[i] = friendlyByteBuf.readInt();
            this.layersData[i] = friendlyByteBuf.readIntArray();
        }
    }
}
