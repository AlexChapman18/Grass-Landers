package jade;

import renderer.Renderer;

import java.util.List;

public abstract class Scene {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;

    public void init(){}

    public Scene() {

    }


    public abstract void start();

    public abstract <T extends RawObject> void addObjectToScene(T parentObject);
    public abstract <T extends RawObject> void addObjectsToScene(List<T> parentObjects);

    public abstract <T extends RawObject> void update(float dt);

    public Camera getCamera() {
        return this.camera;
    }

}
