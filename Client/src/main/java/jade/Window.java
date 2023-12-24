package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import Game.GrassLanders;
import org.lwjgl.system.windows.DISPLAY_DEVICE;

import java.awt.*;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    public int width;
    public int height;
    String title;
    private static Window window = null;
    private long glfwWindow;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "GrassLands";
    }

    public void changeScene(Scene scene) {
        scene.init();
        scene.start();
    }

    public static Window getWindow() {
        if (Window.window == null)
            Window.window = new Window();
        return Window.window;
    }

    public void run(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        loop();

//        Free the window callbacks and destroy the window
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

//        Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init(Scene scene) {
//        Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

//        Initialise GLFW
        if (!glfwInit()){
            throw new IllegalStateException("Unable to initialise GLFW");
        }

//        Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);


//        Create the window
//        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, glfwGetPrimaryMonitor(), NULL);
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW window");
        }

//        Forwards position callback to mousePosCallback function (lambda expression)
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

//        Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
//        Enable v-sync
        glfwSwapInterval(1);

//        Make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

        this.changeScene(scene);
    }


    public void loop(){
        float beginTime = (float)glfwGetTime();
        float endTime;
        float dt = -1.0f;


        while(!glfwWindowShouldClose(glfwWindow)){
//            Poll events
            glfwPollEvents();

            glClearColor(1, 1, 1,1);
            glClear(GL_COLOR_BUFFER_BIT);


            GrassLanders.instance.tick(dt);

            if (dt >= 0)
                GrassLanders.instance.tick(dt);

            glfwSwapBuffers(glfwWindow);

            endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
}
