package components;

import Game.GrassLanders;
import jade.Component;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.awt.*;

public class CollisionBox extends Component {

    public Rectangle collisionBoxRaw;
    private Rectangle collisionBox;
    private boolean isCollidable;

    public CollisionBox(String collisionString, boolean isCollidable) {
        this.isCollidable = isCollidable;
        this.collisionBoxRaw = getBoxSize(collisionString);
    }

    public void setCollisionBox(Rectangle cb) {
        this.collisionBox = cb;
    }

    private Rectangle getBoxSize(String collisionString) {
        switch (collisionString) {
            case "0":
                this.isCollidable = false;
                return new Rectangle(0, 0, 0, 0);
            case "7":
                return new Rectangle(0, 8, 8, 8);
            case "8":
                return new Rectangle(0, 8, 16, 8);
            case "9":
                return new Rectangle(8, 8, 8, 8);
            case "4":
                return new Rectangle(0, 0, 8, 16);
            case "5":
                return new Rectangle(4, 4, 8, 8);
            case "6":
                return new Rectangle(8, 0, 8, 16);
            case "1":
                return new Rectangle(0, 0, 8, 8);
            case "2":
                return new Rectangle(0, 0, 16, 8);
            case "3":
                return new Rectangle(8, 0, 8, 8);
//            Entire entity
            case "a":
                return new Rectangle(0, 0, 16, 16);
//            Player
            case "p":
                return new Rectangle(6, 4, 8, 8);
            // Other entity with no box
            case "x":
                new Rectangle(0, 0, 0, 0);
            default:
                // ERROR STATE, FIX THIS
                // System.out.println("Error: '" + collisionString + "' is an invalid string");
                this.isCollidable = false;
                return new Rectangle(0, 0, 0, 0);
        }
    }



    private void scaleCollisionTemplate(Rectangle collisionTemplate) {
        int mapScale = GrassLanders.instance.getScale();
        Vector2f parentScale = parentObject.transform.scale;
        Vector2f sensScale = new Vector2f(parentScale.x / 16, parentScale.y / 16);
        Vector2i newPos = new Vector2i((int) (collisionTemplate.x * sensScale.x), (int) (collisionTemplate.y * sensScale.y));
        Vector2i newWH = new Vector2i(collisionTemplate.width * mapScale, collisionTemplate.height * mapScale);
        this.collisionBoxRaw = new Rectangle(newPos.x, newPos.y, newWH.x, newWH.y);
    }




    public void updateCollisionBox() {
        Vector2f parentPos = parentObject.transform.position;
        Vector2i deltaPos = new Vector2i((int) (parentPos.x + collisionBoxRaw.x), (int) (parentPos.y + collisionBoxRaw.y));
        this.collisionBox.setLocation(deltaPos.x, deltaPos.y);
    }


    public boolean isCollidable() {
        return isCollidable;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void start() {
        this.scaleCollisionTemplate(this.collisionBoxRaw);
        this.collisionBox = new Rectangle(0, 0, this.collisionBoxRaw.width, this.collisionBoxRaw.height);
        updateCollisionBox();
    }

    @Override
    public void update(float dt) {
    }
}
