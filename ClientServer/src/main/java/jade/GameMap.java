package jade;

import Game.GrassLanders;
import components.CollisionBox;
import components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;
import Util.*;

public class GameMap {




    private List<Layer> layers;
    private float widthPx;
    private float heightPx;
    private int width;
    private int height;
    private float scale;

    public GameMap(RawMapData rmd) {
        this.scale = GrassLanders.instance.getScale();
        Spritesheet mapSprites = AssetPool.getSpritesheet("assets/maps/map_assets/punyworld-overworld-tileset.png");
        this.width = rmd.getWidth();
        this.height = rmd.getHeight();
        float tileWidth = 16 * scale;
        float tileHeight = 16 * scale;
        this.widthPx = this.width * tileWidth;
        this.heightPx = this.height * tileHeight;

        List<Layer> layers = new ArrayList<>();

        for (int layerIndex = 0; layerIndex < rmd.getNumLayers(); layerIndex++) {
            List<LevelObject> layerObjects = new ArrayList<>();
            List<LevelObject> debugObjects = new ArrayList<>();
            int[] layerData = rmd.getLayer(layerIndex);
            for (int y=0; y < this.height; y++) {
                for (int x=0; x < this.width; x++) {
                    float tileXPos = 16 * x * scale;
                    float tileYPos = 16 * y * scale;
                    int tileIndex = ((this.height - 1 - y) * this.width) + x;
                    int tileSpriteIndex = layerData[tileIndex] - 1;
                    LevelObject levelObject;
                    LevelObject debugObject;

                    if (tileSpriteIndex != -1) {

                        int zIndex = layerIndex;
                        // Creates a new layer object
                        if (layerIndex == rmd.getNumLayers() - 1) {
                            zIndex = 10;
                        }

                        // TEMP----
                        CollisionBox collisionBox = new CollisionBox(TileTempData.COLLISION_DATA[tileSpriteIndex], true);
                        // TEMP----

                        levelObject = new LevelObject("punnyworld: " + tileSpriteIndex, new Transform(
                                new Vector2f(tileXPos, tileYPos),
                                new Vector2f(tileWidth, tileHeight)),
                                zIndex);

                        debugObject = new LevelObject("punnyworld debug: " + tileSpriteIndex, new Transform(
                                new Vector2f(tileXPos + collisionBox.collisionBoxRaw.x * scale, tileYPos + collisionBox.collisionBoxRaw.y * scale),
                                new Vector2f(collisionBox.collisionBoxRaw.width * scale, collisionBox.collisionBoxRaw.height * scale)),
                                zIndex);
                        debugObject.addComponent(new SpriteRenderer(mapSprites.getSprite(129)));

                        //  Add the tiles texture to the levelObject

                        levelObject.addComponent(new SpriteRenderer(mapSprites.getSprite(tileSpriteIndex)));

                        // If it is animated, add the relevant frames (i.e. water)
                        if (TileTempData.shouldAnimate(tileSpriteIndex))
                            TileTempData.addAnimations(levelObject, tileSpriteIndex, mapSprites);
                        levelObject.addComponent(collisionBox);

                    } else {
                        levelObject = new LevelObject("null", new Transform(new Vector2f(), new Vector2f()), layerIndex);
                        debugObject = new LevelObject("null", new Transform(new Vector2f(), new Vector2f()), layerIndex);
                    }

                    // Add object to map objects
                    layerObjects.add(levelObject);
                    debugObjects.add(debugObject);
                }
            }
//            layerObjects.addAll(debugObjects);
            layers.add(new Layer(layerObjects, layerIndex, this.width, this.height));
        }

        this.setLayers(layers);
    }


    // COULD BE MORE EFFICIENT
    // Returns 9 tiles in a 3x3 around the x and y given and all z axis
    public List<CollisionBox> getCollisionsInAround(int curX, int curY) {
        List<CollisionBox> collisionBoxes = new ArrayList<>();
        Vector2i tileCoords = worldToTile(curX, curY);

        for (int x = (tileCoords.x-1); x < (tileCoords.x+2); x++) {
            for (int y = (tileCoords.y-1); y < (tileCoords.y+2); y++) {
                for (Layer layer : this.layers) {
                    // System.out.println(x + "  " + y);
                    int clap_x = Util.clamp(x, 0, this.width-1);
                    int clap_y = Util.clamp(y, 0, this.height-1);
                    CollisionBox cb = layer.getTile(clap_x, clap_y).getComponent(CollisionBox.class);
                    if (cb != null && cb.isCollidable()) {
                        collisionBoxes.add(cb);
                    }
                }
            }
        }
        return collisionBoxes;
    }

    // world coords to tile coords
    private Vector2i worldToTile(int x, int y) {
        int mapScale = GrassLanders.instance.getScale();
        // DEBUG STEP
        x = x / (16 * mapScale);
        y = y / (16 * mapScale);
        x = Util.clamp(x, 0, width-1);
        y = Util.clamp(y, 0, height-1);
        return new Vector2i(x, y);
    }

    public List<LevelObject> getTilesAt(int x, int y){
        List<LevelObject> tiles = new ArrayList<>();
        for (Layer layer : layers) {
            tiles.add(layer.getTile(x, y));
        }
        return tiles;
    }
    public LevelObject getTileAt(int zIndex,  int x, int y){
        return getLayer(zIndex).getTile(x, y);
    }

    // COULD BE MORE EFFICIENT
    public Layer getLayer(int zIndex) {
        for (Layer layer : layers) {
            if (layer.getzIndex() == zIndex) {
                return layer;
            }
        }
        return null;
    }

    // Getters and setters
    public float getWidthPx() {
        return widthPx;
    }
    public float getHeightPx() {
        return heightPx;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
    public List<LevelObject> getAllLayerObjects() {
        List<LevelObject> allObjects = new ArrayList<>();
        for (Layer layer : layers) {
            allObjects.addAll(layer.getLayerObjects());
        }
        return allObjects;
    }
}
