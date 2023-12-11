package Util;

import Game.GrassLanders;
import org.joml.Vector2i;

public class Coords {

    public static Vector2i screenToWorld(float x, float y) {
        int mapScale = GrassLanders.instance.getScale();
        return new Vector2i((int) (x / (16 * mapScale)), (int) (y / (16 * mapScale)));
    }

}
