package components;

import Game.GrassLanders;
import jade.*;
import org.joml.Vector4f;

public class GameCamera extends Component {

    private transient GameObject player;
    private transient Camera gameCamera;

    private Vector4f voidColor = new Vector4f(0,0,0,1);

    public GameCamera(Camera gameCamera) {
        this.gameCamera = gameCamera;
    }

    @Override
    public void start() {
        this.player = GrassLanders.instance.getPlayer();
    }

    @Override
    public void update(float dt) {
        if (player != null) {
            // int scale = GrassLanders.instance.getScale();
            float windowWidth = gameCamera.cameraOrthoWidth;
            float windowHeight = gameCamera.cameraOrthoHeight;
            float maxCameraX = GrassLanders.instance.getMap().getWidthPx() - windowWidth;
            float maxCameraY = GrassLanders.instance.getMap().getHeightPx() - windowHeight;

            float newCameraX = player.transform.position.x - (windowWidth/2) + 32;
            float newCameraY = player.transform.position.y - (windowHeight/2) + 32;

            if (newCameraX > 0 && newCameraX < maxCameraX)
                gameCamera.position.x = newCameraX;
            if ((newCameraY > 0 && newCameraY < maxCameraY))
                gameCamera.position.y =newCameraY;
        } else {
            this.player = GrassLanders.instance.getPlayer();
        }
    }
}
