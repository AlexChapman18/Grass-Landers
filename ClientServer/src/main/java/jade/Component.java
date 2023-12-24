package jade;

public abstract class Component<T extends RawObject> {

    public T parentObject = null;

//    Abstract method you do have to overide
    public abstract void update(float dt);

//    Abstract method you do not have to overide
    public void start() {}
}