package jade;

import Game.GrassLanders;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    public MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener getInstance() {
        if (MouseListener.instance == null){
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos){
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
        getInstance().xPos = xpos;
        getInstance().yPos = ypos;
        getInstance().isDragging = getInstance().mouseButtonPressed[0] ||
                                    getInstance().mouseButtonPressed[1] ||
                                    getInstance().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods){
        if (action == GLFW_PRESS){
//            Ignores buttons that are outside of the usual 3
            if (button < getInstance().mouseButtonPressed.length) {
                getInstance().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            //            Ignores buttons that are outside of the usual 3
            if (button < getInstance().mouseButtonPressed.length) {
                getInstance().mouseButtonPressed[button] = false;
                getInstance().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        getInstance().scrollX = xOffset;
        getInstance().scrollY = yOffset;
    }

    public static void endFrame() {
        getInstance().scrollX = 0;
        getInstance().scrollY = 0;
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
    }

    public static double getX() {
        return getInstance().xPos;
    }

    public static double getY() {
        return getInstance().yPos;
    }

    public static float getOrthoX() {
        float currentX = (float) getX();
        float currentY = (float) getY();
        currentX = currentX / (float)Window.getWindow().width;
        currentY = 1 - (currentY / 1016);
        Camera camera = GrassLanders.instance.getCurrentScene().camera;
                // System.out.println(((currentX * camera.cameraOrthoWidth) + camera.position.x)
                //         + ", "
                //         + ((currentY * camera.cameraOrthoHeight) + camera.position.y));
                // System.out.println(currentY);
        return currentX;
    }

    public static float getOrthoY() {


        return -1;
    }

    public static double getDx() {
        return getInstance().lastX - getInstance().xPos;
    }

    public static double getDy() {
        return getInstance().lastY - getInstance().yPos;
    }

    public static double getScrollX() {
        return getInstance().scrollX;
    }

    public static double getScrollY() {
        return getInstance().scrollY;
    }

    public static boolean isDragging() {
        return getInstance().isDragging;
    }

    public static boolean getMouseButtonPressed(int button) {
        if (button < getInstance().mouseButtonPressed.length){
            return getInstance().mouseButtonPressed[button];
        }
        return false;
    }

}
