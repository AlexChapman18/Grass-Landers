package jade;

import org.joml.Vector2i;

public class LevelObject extends RawObject {

    private Vector2i position;

    public LevelObject(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
    }
}
