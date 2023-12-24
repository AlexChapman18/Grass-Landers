package Network.Shared.Packet.PacketDefinition;

import Network.Shared.Packet.ClientPacketListener;
import Network.Shared.Packet.Packet;
import Network.Shared.Util.FriendlyByteR;

public class ClientboundMapPacket implements Packet<ClientPacketListener> {

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

    public void handle(ClientPacketListener packetListener){
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

    // Getters
    public String getMapName(){
        return this.mapName;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getNumLayers() {
        return numLayers;
    }
    public int[] getLayerIDs() {
        return layerIDs;
    }
    public int[][] getLayersData() {
        return layersData;
    }
}
