package renderer;

import jade.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer extends RawRenderer{

     private List<GameObject> gameObjects = new ArrayList<>();

     public GameRenderer(Renderer renderer) {
         super(renderer);
     }

    @Override
    public void update(float dt) {

    }

    public void addGameObject(GameObject go) {
         this.gameObjects.add(go);
     }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addToGame(){
         for (GameObject go : gameObjects) {
             renderer.add(go);
         }
    }


}
