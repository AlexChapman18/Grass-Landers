package jade;

import Game.GrassLanders;
import Util.Util;
import components.CollisionBox;
import components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.mapeditor.core.Map;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private int rawNumLayers;
    private int[][] rawMap;


    private List<Layer> layers;
    private float widthPx;
    private float heightPx;
    private int width;
    private int height;


    public GameMap(int width, int height, int numLayers, int[][] rawMap) {
        this.width = width;
        this.height = height;
        this.rawNumLayers = numLayers;
        this.rawMap = rawMap;
    }

    public void build() {
        // int scale = GrassLanders.instance.getScale();
        //
        // Map map = null;
        //
        // try {
        //     TMXMapReader mapReader = new TMXMapReader();
        //     map = mapReader.readMap("assets/images/maps/samplemap1.tmx");
        // } catch (Exception e) {
        //     System.out.println("Error while reading the map:\n" + e.getMessage());
        // }
        //
        // Spritesheet mapSprites = AssetPool.getSpritesheet("assets/images/punyworld-overworld-tileset.png");
        // List<LevelObject> mapObjects = new ArrayList<>();
        // int mapWidthTiles = map.getWidth();
        // int mapHeightTiles = map.getHeight();
        // // float initXCoords = -((mapWidthTiles / 2f) * 16) * scale;
        // // float initYCoords = -((mapHeightTiles / 2f) * 16) * scale;
        // float initXCoords = 0;
        // float initYCoords = 0;
        // float tileWidth = 16 * scale;
        // float tileHeight = 16 * scale;
        //
        // GameMap gameMap = new GameMap(scale, mapWidthTiles, mapHeightTiles);
        // List<Layer> layers = new ArrayList<>();
        //
        // for (int l = 0; l < map.getLayerCount(); l++) {
        //     List<LevelObject> layerObjects = new ArrayList<>();
        //     List<LevelObject> debugObjects = new ArrayList<>();
        //     for (int y=0; y < map.getHeight(); y++) {
        //         for (int x=0; x < map.getWidth(); x++) {
        //             float tileXPos = initXCoords + (16 * x * scale);
        //             float tileYPos = initYCoords + (16 * y * scale);
        //
        //
        //             // Check if the tile on that layer is null or not (Some layers only have trees)
        //             if (((TileLayer) map.getLayer(l)).getTileAt(x, map.getHeight() - 1 - y) != null) {
        //                 int zIndex = l;
        //                 // Creates a new layer object
        //                 if (l == map.getLayerCount()-1){
        //                     zIndex = 10;
        //                 }
        //
        //                 int tileID = ((TileLayer) map.getLayer(l)).getTileAt(x, map.getHeight() - 1 - y).getId();
        //
        //                 // TEMP----
        //                 CollisionBox cb = new CollisionBox(TileTempData.COLLISION_DATA[tileID], true);
        //                 // TEMP----
        //
        //                 LevelObject levelObject = new LevelObject("punnyworld: " + tileID, new Transform(
        //                         new Vector2f(tileXPos, tileYPos),
        //                         new Vector2f(tileWidth, tileHeight)),
        //                         zIndex);
        //
        //                 LevelObject levelObjectDebug = new LevelObject("punnyworld debug: " + tileID, new Transform(
        //                         new Vector2f(tileXPos + cb.collisionBoxRaw.x * scale, tileYPos + cb.collisionBoxRaw.y * scale),
        //                         new Vector2f(cb.collisionBoxRaw.width * scale, cb.collisionBoxRaw.height * scale)),
        //                         zIndex);
        //                 levelObjectDebug.addComponent(new SpriteRenderer(mapSprites.getSprite(129)));
        //
        //                 //  Add the tiles texture to the levelObject
        //
        //                 levelObject.addComponent(new SpriteRenderer(mapSprites.getSprite(tileID)));
        //
        //                 // If it is animated, add the relevant frames (i.e. water)
        //                 if (TileTempData.shouldAnimate(tileID))
        //                     TileTempData.addAnimations(levelObject, tileID, mapSprites);
        //
        //                 if (tileID != 113)
        //                     levelObject.addComponent(cb);
        //
        //                 // Add object to map objects
        //                 layerObjects.add(levelObject);
        //                 debugObjects.add(levelObjectDebug);
        //             } else {
        //                 layerObjects.add(new LevelObject("BLANK", new Transform(new Vector2f(), new Vector2f()), l));
        //             }
        //         }
        //     }
        //     // layerObjects.addAll(debugObjects);
        //     layers.add(new Layer(layerObjects, l, mapWidthTiles, mapHeightTiles));
        // }
        //
        // gameMap.setLayers(layers);
        // return gameMap;
    }


    public GameMap(float scale, int width, int height) {
        this.width = width;
        this.height = height;
        this.widthPx = width * 16 * scale;
        this.heightPx = height * 16 * scale;
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


    // COULD BE MORE EFFICIENT
    // Returns 9 tiles in a 3x3 around the x and y given
    public List<CollisionBox> getCollisionsInAround(int curX, int curY) {
        List<CollisionBox> collisionBoxes = new ArrayList<>();
        Vector2i tileCoords = worldToTile(curX, curY);

        for (int x = (tileCoords.x-1); x < (tileCoords.x+2); x++) {
            for (int y = (tileCoords.y-1); y < (tileCoords.y+2); y++) {
                for (Layer layer : layers) {
                    // System.out.println(x + "  " + y);
                    x = Util.clamp(x, 0, width-1);
                    y = Util.clamp(y, 0, height-1);
                    CollisionBox cb = layer.getTile(x, y).getComponent(CollisionBox.class);
                    if (cb != null && cb.isCollidable()) {
                        collisionBoxes.add(cb);
                    }
                }
            }
        }
        return collisionBoxes;
    }

    private Vector2i worldToTile(int x, int y) {
        int mapScale = GrassLanders.instance.getScale();
        // DEBUG STEP
        x = x / (16 * mapScale);
        y = y / (16 * mapScale);
        x = Util.clamp(x, 0, width-1);
        y = Util.clamp(y, 0, height-1);
        return new Vector2i(x, y);
    }

    // Get tiles at index x, y for all layers
    public List<LevelObject> getTilesAt(int x, int y){
        List<LevelObject> tiles = new ArrayList<>();
        for (Layer layer : layers) {
            tiles.add(layer.getTile(x, y));
        }
        return tiles;
    }

    // Get a specific tile on a layer
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
}
