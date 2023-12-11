package components;

import Game.GrassLanders;
import Util.AssetPool;
import Util.Spritesheet;
import Util.TileTempData;
import Util.Xml;
import jade.*;
import org.joml.Vector2f;
import org.mapeditor.core.Map;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import java.util.ArrayList;
import java.util.List;

public class Prefabs {

    public static GameObject PLAYER_PREFAB(Camera camera, Vector2f startingCoords) {
        int scale = GrassLanders.instance.getScale();

        Spritesheet playerSprites = AssetPool.getSpritesheet("assets/player_assets/Human-Soldier-Red.png");

        GameObject player = new GameObject("Player", new Transform(
                new Vector2f(startingCoords.x-(8 * scale), startingCoords.y-(8 * scale)),
                new Vector2f(((16 * scale)*2),((16 * scale)*2))), 9);



        StateMachine stateMachine = new StateMachine();

//        Movement and idle animation setup
        float defaultFrameTime = 0.5f;
        String[] directions = {"D", "RD", "R", "UR", "U", "LU", "L", "DL"};
        int spriteSheetRowFramesOffset = 24;
        int numDirections = 8;
        int runOffset = 1;
        int runFrameCount = 4;
        for (int dirIndex =0; dirIndex < numDirections; dirIndex++){
            int rowIndex = spriteSheetRowFramesOffset * dirIndex;
            AnimationState idle = new AnimationState();
            idle.title = directions[dirIndex] + "Idle";
            idle.addFrame(playerSprites.getSprite(rowIndex), 2);
            idle.addFrame(playerSprites.getSprite(rowIndex + 1), 2);
            idle.setLoop(true);

            AnimationState run = new AnimationState();
            run.title = directions[dirIndex] + "Run";
            run.setLoop(true);
            for (int i=0; i < runFrameCount; i++) {
                run.addFrame(playerSprites.getSprite(rowIndex + runOffset + i), defaultFrameTime);
            }
            stateMachine.addState(idle);
            stateMachine.addState(run);
            stateMachine.setDefaultState("RRun");
        }
        player.addComponent(stateMachine);
        player.addComponent(new GameCamera(camera));
        player.addComponent(new SpriteRenderer(playerSprites.getSprite(0)));
        player.addComponent(new CollisionBox("p", true));
        player.addComponent(new PlayerController());
        return player;
    }

    public static GameMap MAP_PREFAB(String mapName) {
        int scale = GrassLanders.instance.getScale();

        RawMapData rawMapData = Xml.readMap("assets/maps/"+mapName+".xml");


        Spritesheet mapSprites = AssetPool.getSpritesheet("assets/maps/map_assets/punyworld-overworld-tileset.png");
        int mapWidth = rawMapData.getWidth();
        int mapHeight = rawMapData.getHeight();
        float tileWidth = 16 * scale;
        float tileHeight = 16 * scale;

        GameMap gameMap = new GameMap(scale, mapWidth, mapHeight);
        List<Layer> layers = new ArrayList<>();

        for (int layerIndex = 0; layerIndex < rawMapData.getNumLayers(); layerIndex++) {
            List<LevelObject> layerObjects = new ArrayList<>();
            List<LevelObject> debugObjects = new ArrayList<>();
            int[] layerData = rawMapData.getLayer(layerIndex);
            for (int y=0; y < mapHeight; y++) {
                for (int x=0; x < mapWidth; x++) {
                    float tileXPos = 16 * x * scale;
                    float tileYPos = 16 * y * scale;
                    int tileIndex = ((mapHeight - 1 - y) * mapWidth) + x;
                    int tileSpriteIndex = layerData[tileIndex] - 1;
                    LevelObject levelObject;
                    LevelObject debugObject;

                    if (tileSpriteIndex != -1) {

                        int zIndex = layerIndex;
                        // Creates a new layer object
                        if (layerIndex == rawMapData.getNumLayers() - 1) {
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
            layerObjects.addAll(debugObjects);
            layers.add(new Layer(layerObjects, layerIndex, mapWidth, mapHeight));
        }

        gameMap.setLayers(layers);
        return gameMap;
    }
}
