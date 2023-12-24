package jade;

import java.util.List;

public class Layer {

    private List<LevelObject> layerObjects;
    private int zIndex;
    private int width;
    private int height;


    public Layer(List<LevelObject> layerObjects, int zIndex, int width, int height) {
        this.layerObjects = layerObjects;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
    }

    public List<LevelObject> getLayerObjects() {
        return layerObjects;
    }

    public void setLayerObjects(List<LevelObject> layerObjects) {
        this.layerObjects = layerObjects;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public LevelObject getTile(int x, int y){
        int index = (y * width) + x;
        return this.layerObjects.get(index);
    }
}
