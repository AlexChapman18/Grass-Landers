package jade;

import java.util.List;

public class GameObject extends RawObject{

    public GameObject() {
    }

    public GameObject(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
    }
}
