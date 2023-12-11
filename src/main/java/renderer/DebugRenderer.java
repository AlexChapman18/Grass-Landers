package renderer;

import jade.DebugObject;

import java.util.ArrayList;
import java.util.List;

public class DebugRenderer extends RawRenderer{

    private List<DebugObject> debugObjects = new ArrayList<>();

    public DebugRenderer(Renderer renderer) {
        super(renderer);
    }

    @Override
    public void update(float dt) {

    }

    public void addDebugObject(DebugObject go) {
        this.debugObjects.add(go);
    }

    public List<DebugObject> getDebugObjects() {
        return debugObjects;
    }

    public void addToGame(){
        for (DebugObject lo : debugObjects) {
            renderer.add(lo);
        }
    }
}
