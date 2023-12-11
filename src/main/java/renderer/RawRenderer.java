package renderer;

public abstract class RawRenderer {

    protected Renderer renderer;

    public RawRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void update(float dt);

    public abstract void addToGame();

}
