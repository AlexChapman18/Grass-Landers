package components;

import Game.GrassLanders;
import jade.Component;
import jade.KeyListener;
import org.joml.Vector2f;

import java.awt.*;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class PlayerController extends Component {
    private String lastDirection;
    private float walkSpeed = 46f;
    private transient StateMachine stateMachine;
    private float slowDownForce = 10;
    private Vector2f terminalVelocity = new Vector2f(40f, 40f);
    private CollisionBox playerColBox;


    private transient Vector2f acceleration = new Vector2f();
    private transient Vector2f velocity = new Vector2f();



    @Override
    public void start() {
        this.stateMachine = parentObject.getComponent(StateMachine.class);
        this.playerColBox = parentObject.getComponent(CollisionBox.class);
        lastDirection = "D";
    }


    @Override
    public void update(float dt) {
        Vector2f newVelocity = new Vector2f();


//        Right movement
        if (KeyListener.isKeyPressed(GLFW_KEY_D)){
            newVelocity.x = 1;
//        Left movement
        } else if (KeyListener.isKeyPressed(GLFW_KEY_A)){
            newVelocity.x = -1;
        } else {
            newVelocity.x = 0;
        }

//        Up movement
        if (KeyListener.isKeyPressed(GLFW_KEY_W)){
            newVelocity.y = 1;
//        Down movement
        } else if (KeyListener.isKeyPressed(GLFW_KEY_S)) {
            newVelocity.y = -1;
        } else {
            newVelocity.y = 0;
        }

        String[] directions = {"DL", "L", "LU", "D", "", "U", "RD", "R", "UR"};


        if (newVelocity.x == 0 && newVelocity.y == 0) {
            stateMachine.trigger(lastDirection+"Idle");
        } else {
            int directionIndex = (int)((newVelocity.x * 3) + newVelocity.y) + 4;
            String direction = directions[directionIndex];
            this.lastDirection = direction;
            stateMachine.trigger(direction+"Run");


            float magnitude = (float)Math.sqrt((newVelocity.x * newVelocity.x) + (newVelocity.y * newVelocity.y));
            Vector2f unitVelocity = new Vector2f(newVelocity.x / magnitude, newVelocity.y/magnitude);

            parentObject.getComponent(CollisionBox.class).updateCollisionBox();
            Rectangle currentColBox = parentObject.getComponent(CollisionBox.class).getCollisionBox();
            Vector2f deltaPos = new Vector2f(unitVelocity.x * walkSpeed * dt, unitVelocity.y * walkSpeed * dt);
            Rectangle newColBox = new Rectangle(
                                        (int) (currentColBox.x + deltaPos.x),
                                        (int) (currentColBox.y + deltaPos.y),
                                        currentColBox.width,
                                        currentColBox.height
                                );
            if (validMove(newColBox)) {
                parentObject.transform.addPosition(deltaPos.x, deltaPos.y);
            } else {
                System.out.println("INVALID MOVE");
            }
        }
    }

    private boolean validMove(Rectangle newColBox) {

        List<CollisionBox> tileColBoxes = GrassLanders.instance.getMap().getCollisionsInAround(newColBox.x + 16, newColBox.y + 16);

        if (!playerColBox.isCollidable()) {
            return true;
        }

        for (CollisionBox tileColBox : tileColBoxes) {
            if (tileColBox.isCollidable()) {
                if (newColBox.intersects(tileColBox.getCollisionBox())) {
                    return false;
                }
            }
        }
        return true;
    }
}
