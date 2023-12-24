package jade;

import Game.GrassLanders;
import Util.AssetPool;
import Util.Spritesheet;
import components.debug.DebugInfo;
import org.joml.Vector2f;
import renderer.DebugRenderer;
import renderer.GameRenderer;
import renderer.LevelRenderer;
import components.Prefabs;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene{

    private GrassLanders grassLanders;
    private GameMap map;
    private Spritesheet sprites;
    private LevelRenderer levelRenderer;
    private GameRenderer gameRenderer;
    private DebugRenderer debugRenderer;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected List<LevelObject> levelObjects = new ArrayList<>();
    protected List<DebugObject> debugObjects = new ArrayList<>();

    public GameScene(LevelRenderer levelRenderer, GameRenderer gameRenderer, DebugRenderer debugRenderer, GrassLanders grassLanders) {
        this.levelRenderer = levelRenderer;
        this.gameRenderer = gameRenderer;
        this.debugRenderer = debugRenderer;
        this.grassLanders = grassLanders;
    }

    @Override
    public void init() {
        loadResources();
        this.map = new GameMap(grassLanders.getRawMapData());
        grassLanders.setRawMap(null);
        this.grassLanders.setMap(this.map);
        this.addObjectsToScene(map.getAllLayerObjects());

        this.camera = new Camera(new Vector2f());
        Vector2f startingCoords = new Vector2f(700, 660);
        GameObject player = Prefabs.PLAYER_PREFAB(this.getCamera(), startingCoords);
        this.addObjectToScene(player);
        GrassLanders.instance.setPlayer(player);

        DebugObject dbgObject = new DebugObject("Player Coords", new Vector2f(0,0));
        dbgObject.addComponent(new DebugInfo());
        this.addObjectToScene(dbgObject);

    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpritesheet("assets/maps/map_assets/punyworld-overworld-tileset.png",
                new Spritesheet(AssetPool.getTexture("assets/maps/map_assets/punyworld-overworld-tileset.png"),
                        16, 16, 900, 0));

        AssetPool.addSpritesheet("assets/player_assets/Human-Soldier-Red.png",
                new Spritesheet(AssetPool.getTexture("assets/player_assets/Human-Soldier-Red.png"),
                        32, 32, 192, 0));
    }



    @Override
    public <T extends RawObject> void update(float dt) {
        MouseListener.getOrthoX();

        for (GameObject go : gameObjects){
            go.update(dt);
        }
        for (LevelObject lo : levelObjects){
            lo.update(dt);
        }
        // for (DebugObject dbgo : debugObjects){
        //     dbgo.update(dt);
        // }
    }

    @Override
    public void start() {
        gameRenderer.addToGame();
        levelRenderer.addToGame();
        debugRenderer.addToGame();
    }

    @Override
    public <T extends RawObject> void addObjectsToScene(List<T> parentObjects) {
        for (T obj : parentObjects) {
            this.addObjectToScene(obj);
        }
    }

    @Override
    public <T extends RawObject> void addObjectToScene(T parentObject) {
        if (parentObject.getClass().equals(GameObject.class)) {
            GameObject go = (GameObject) parentObject;
            gameObjects.add(go);
            go.start();
            gameRenderer.addGameObject(go);

        } else if (parentObject.getClass().equals(LevelObject.class)) {
            LevelObject lo = (LevelObject) parentObject;
            levelObjects.add(lo);
            lo.start();
            levelRenderer.addLevelObject(lo);

        } else if (parentObject.getClass().equals(DebugObject.class)) {
            DebugObject dbgo = (DebugObject) parentObject;
            debugObjects.add(dbgo);
            dbgo.start();
            debugRenderer.addDebugObject(dbgo);

        } else {
            System.out.println("Invalid object for scene");
        }
    }
}
