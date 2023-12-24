package renderer;

import jade.GameObject;
import jade.LevelObject;

import java.util.ArrayList;
import java.util.List;

public class LevelRenderer extends RawRenderer{

    private List<LevelObject> levelObjects = new ArrayList<>();

    public LevelRenderer(Renderer renderer) {
        super(renderer);
    }

    @Override
    public void update(float dt) {

    }

    public void addLevelObject(LevelObject go) {
        this.levelObjects.add(go);
    }

    public List<LevelObject> getLevelObjects() {
        return levelObjects;
    }

    public void addToGame(){
        for (LevelObject lo : levelObjects) {
            renderer.add(lo);
        }
    }
}
