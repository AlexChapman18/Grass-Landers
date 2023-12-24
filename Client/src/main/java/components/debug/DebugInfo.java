package components.debug;

import Game.GrassLanders;
import jade.Component;
import jade.GameObject;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class DebugInfo extends Component {

    private GameObject player;
    private List<String> information = new ArrayList<>();

    @Override
    public void start() {
        this.player = GrassLanders.instance.getPlayer();
    }




    @Override
    public void update(float dt) {
        if (player != null) {
            Vector2f playerPos = this.player.transform.position;
            // information.add("Player X: " + playerPos.x + ", Y: " + playerPos.y);
            System.out.println("Player X: " + playerPos.x + ", Y: " + playerPos.y);
        } else {
            this.player = GrassLanders.instance.getPlayer();
        }
    }
}
