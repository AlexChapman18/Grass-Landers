package Game;

import Network.ClientConnectionHandler;
import Network.ObjectClient;
import Network.Shared.Packet.PacketDefinition.ServerboundLoginPacket;
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
    private RawMapData rmd;
    private GameMap map;
    private int scale = 2;
    // Networking
    private ClientConnectionHandler connection;
    private final int AUTHCODE = 2153;
    private String username;
    private boolean isWaiting;


    public GrassLanders() {
        GrassLanders.instance = this;
        this.gameRenderer = new GameRenderer(this.renderer);
        this.levelRenderer = new LevelRenderer(this.renderer);
        this.debugRenderer = new DebugRenderer(this.renderer);
        this.window = Window.getWindow();
    }

    public void connect(String host, int port, String name) {
        this.username = name;
        ObjectClient oc = new ObjectClient(host, port, this);
        this.connection = oc.getCch();
        oc.run();
    }

    // As soon as connection is established, send login packet
    public void connected() {
        sendLoginPacket();
        // Waiting for map packet
        waitFor();
    }

    public void startGame(String host, int port, String name) {
        connect(host, port, name);
    }

    public void startScene() {
        this.currentScene = new GameScene(this.levelRenderer, this.gameRenderer, this.debugRenderer, this);
        this.window.init(currentScene);
        this.window.run();
        System.out.println("Hello world!");
    }


    public void tick(float dt) {
        this.currentScene.update(dt);
        this.renderer.render();
    }

    private void waitFor() {
        this.isWaiting = true;
        while (this.isWaiting) {
            System.out.println("Loop");
        }
    }


    // ----------------------------------------
    // Packets
    private void sendLoginPacket() {
        ServerboundLoginPacket sblp = new ServerboundLoginPacket(AUTHCODE, username);
        this.connection.sendPacket(sblp);
    }

    // ----------------------------------------
    // Getters and setters
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
    public RawMapData getRawMapData() {
        return rmd;
    }
    public void setRawMap(RawMapData map) {
        this.rmd = map;
    }
    public Scene getCurrentScene() {
        return currentScene;
    }

    public GameMap getMap() {
        return map;
    }
    public void setMap(GameMap map) {
        this.map = map;
    }

    public void setWaiting(boolean b) {
        this.isWaiting = b;
    }
}
