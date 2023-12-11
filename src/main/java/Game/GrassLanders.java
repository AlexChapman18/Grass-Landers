package Game;

import jade.*;
import renderer.DebugRenderer;
import renderer.GameRenderer;
import renderer.LevelRenderer;
import renderer.Renderer;

public class GrassLanders {

    public static GrassLanders instance;
    private GameRenderer gameRenderer;
    private LevelRenderer levelRenderer;
    private DebugRenderer debugRenderer;
    private Window window;
    private Scene currentScene;
    protected Renderer renderer = new Renderer();
    private GameObject player;
    private GameMap map;
    private int scale = 2;

    public GrassLanders() {
        GrassLanders.instance = this;
        this.gameRenderer = new GameRenderer(this.renderer);
        this.levelRenderer = new LevelRenderer(this.renderer);
        this.debugRenderer = new DebugRenderer(this.renderer);
        this.window = Window.getWindow();
    }

    public void start() {
        this.currentScene = new GameScene(this.levelRenderer, this.gameRenderer, this.debugRenderer, this);
        this.window.init(currentScene);
        this.window.run();
        System.out.println("Hello world!");
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void tick(float dt) {
//        this.currentScene.update(dt);

        this.currentScene.update(dt);
        this.renderer.render();


    }

    public void setPlayer(GameObject player) {
        this.player = player;
    }

    public GameObject getPlayer() {
        return player;
    }

    public Window getWindow() {
        return window;
    }

    public int getScale() {
        return scale;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
