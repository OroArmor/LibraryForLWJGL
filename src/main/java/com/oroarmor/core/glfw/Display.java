package com.oroarmor.core.glfw;

import com.oroarmor.core.glfw.event.GLFWEvent;
import com.oroarmor.core.glfw.event.GLFWEventCreator;
import com.oroarmor.core.glfw.event.GLFWEventListener;
import com.oroarmor.core.glfw.event.key.Key;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class Display implements GLFWEventListener {
    /**
     * The original dimensions
     */
    private final int owidth, oheight;
    /**
     * The GLFW window handle
     */
    private final long window;
    /**
     * The key that when released closes the window
     */
    public Key closeKey = Key.ESCAPE;
    /**
     * When true, the display is listening to events
     */
    boolean active = true;
    /**
     * Set to true is the window is maximized
     */
    private boolean maximized = false;
    /**
     * The current dimensions
     */
    private int width, height;

    /**
     * Creates a new display
     *
     * @param width  The width of the display
     * @param height The height of the display
     * @param name   The name of the display
     */
    public Display(final int width, final int height, final String name) {
        this.width = width;
        this.height = height;
        owidth = width;
        oheight = height;
        window = GLFWUtil.glfwCreateWindowHelper(width, height, name, NULL, NULL);

        glfwSetWindowSizeCallback(window, (window, _width, _height) -> {
            Display.this.setWidth(_width);
            Display.this.setHeight(_height);

            glViewport(0, 0, _width, _height);
        });

        glClearDepth(1.0f);
        glDepthFunc(GL_LESS);
        glEnable(GL_DEPTH_TEST);

        GLFWEventCreator.initalizeWindow(window);
        addToListeners();
    }

    /**
     * Clear the color and depth buffers
     */
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Tells the window to close
     */
    public void close() {
        if (!glfwWindowShouldClose(window)) {
            glfwSetWindowShouldClose(window, true);
        }
    }

    /**
     * Enables transparency with the function {@code 1 - alpha}
     */
    public void enableTransparency() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * End glfw and the window
     */
    public void end() {
        glfwTerminate();
    }

    /**
     * Toggles fullscreen for the window
     */
    public void fullscreen() {
        if (!maximized) {
            glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, 1920, 1080, 60);
        } else {
            glfwSetWindowMonitor(window, NULL, 100, 100, owidth, oheight, 60);
        }
        maximized = !maximized;
    }

    /**
     * @return The height of the windw
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the window
     *
     * @param height New height
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @return An orthographic view model for the display
     */
    public Matrix4f getOrthoViewModel() {
        return new Matrix4f().setOrtho(0, width, height, 0, -10000, 10000);
    }

    /**
     * @param fov The field of view for the camera
     * @return A perspective view model for the display
     */
    public Matrix4f getPerspectiveViewModel(final float fov) {
        final float aspect = (float) width / (float) height;
        final float tanfov = (float) Math.tan(Math.toRadians(fov / 2));

        final Matrix4f mat = new Matrix4f().zero();

        final float near = -1f;
        final float far = 400f;

        mat.m00(1f / (aspect * tanfov));
        mat.m11(1f / tanfov);
        mat.m22(-(far + near) / (far - near));
        mat.m32(-1f);
        mat.m23(-2f * far * near / (far - near));
        return mat;
    }

    /**
     * @return The width of the window
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the window
     *
     * @param width New width
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public void processGLFWEvent(final GLFWEvent event) {
    }

    /**
     * Swaps the render buffer and display buffer Polls events
     */
    public void render() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    /**
     * Sets the clear color of the display
     *
     * @param red   Red channel (0 - 1)
     * @param green Green channel (0 - 1)
     * @param blue  Blue channel (0 - 1)
     * @param alpha Alpha channel (0 - 1)
     */
    public void setClearColor(final float red, final float green, final float blue, final float alpha) {
        glClearColor(red, green, blue, alpha);
    }

    public void setCullFace(final CullFace face) {
        glEnable(GL_CULL_FACE);
        glCullFace(face.id);
    }

    /**
     * Sets the key that closes the window
     *
     * @param closeKey New key to close the window with
     */
    public void setKeyClose(final Key closeKey) {
        this.closeKey = closeKey;
    }

    /**
     * @return True if glfw thinks the window should close
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public enum CullFace {
        FRONT(GL_FRONT), BACK(GL_BACK);

        public int id;

        CullFace(final int id) {
            this.id = id;
        }
    }

}
