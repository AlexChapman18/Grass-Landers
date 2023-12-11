package jade;

public class RawMapData {

    private String mapName;
    private int width;
    private int height;
    private String tilesetSource;
    private int numLayers;
    private int currentLayer = 0;
    private int[] layerIDs;
    private int[][] layersData;

    public RawMapData(String mapName, int width, int height, String tilesetSource, int numLayers) {
        this.mapName = mapName;
        this.width = width;
        this.height = height;
        this.tilesetSource = tilesetSource;
        this.numLayers = numLayers;
        this.layerIDs = new int[numLayers];
        this.layersData = new int[numLayers][width * height];
    }

    public void addLayer(int layerID, int[] layerData){
        this.layerIDs[this.currentLayer] = layerID;
        this.layersData[this.currentLayer] = layerData;
        this.currentLayer++;
    }

    public String getMapName() {
        return mapName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTilesetSource() {
        return tilesetSource;
    }

    public int getNumLayers() {
        return numLayers;
    }

    public int[] getLayerIDs() {
        return layerIDs;
    }

    public int[] getLayer(int index) {
        return layersData[index];
    }
}
