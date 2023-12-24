package jade;

import org.joml.Vector2f;

public class DebugObject extends RawObject {

    public DebugObject(String name, Vector2f position) {
        super(name, new Transform(position, null), 10);
    }
}
