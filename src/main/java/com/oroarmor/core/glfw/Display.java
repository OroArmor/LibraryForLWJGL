package com.oroarmor.core.glfw;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowMonitor;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LESS;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;

import com.oroarmor.core.glfw.event.Event;
import com.oroarmor.core.glfw.event.EventCreator;
import com.oroarmor.core.glfw.event.EventListener;
import com.oroarmor.core.glfw.event.key.Key;

public abstract class Display implements EventListener {
	/**
	 * When true, the display is listening to events
	 */
	boolean active = true;

	/**
	 * The key that when released closes the window
	 */
	public Key closeKey = Key.ESCAPE;

	/**
	 * Set to true is the window is maximized
	 */
	private boolean maximized = false;

	/**
	 * The original dimensions
	 */
	private int owidth, oheight;

	/**
	 * The current dimensions
	 */
	private int width, height;

	/**
	 * The GLFW window handle
	 */
	private long window;

	public enum CullFace {
		FRONT(GL_FRONT), BACK(GL_BACK);

		public int id;

		private CullFace(int id) {
			this.id = id;
		}
	}

	/**
	 * Creates a new display
	 * 
	 * @param width  The width of the display
	 * @param height The height of the display
	 * @param name   The name of the display
	 */
	public Display(int width, int height, String name) {
		this.width = width;
		this.height = height;
		this.owidth = width;
		this.oheight = height;
		window = GLFWUtil.glfwCreateWindowHelper(width, height, name, NULL, NULL);

		glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallbackI() {

			@Override
			public void invoke(long window, int _width, int _height) {
				setWidth(_width);
				setHeight(_height);

				glViewport(0, 0, _width, _height);
			}
		});

		glClearDepth(1.0f);
		glDepthFunc(GL_LESS);
		glEnable(GL_DEPTH_TEST);

		EventCreator.initalizeWindow(window);
		addToListeners();
	}

	public void setCullFace(CullFace face) {
		glEnable(GL_CULL_FACE);
		glCullFace(face.id);
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
		if (!glfwWindowShouldClose(window))
			glfwSetWindowShouldClose(window, true);
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
	 * 
	 * @return The height of the windw
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return An orthographic view model for the display
	 */
	public Matrix4f getOrthoViewModel() {
		return new Matrix4f().ortho(0, width, 0, height, -10000, 10000);
	}

	/**
	 * 
	 * @param fov The field of view for the camera
	 * @return A perspective view model for the display
	 */
	public Matrix4f getPerspectiveViewModel(float fov) {
		float aspect = (float) width / (float) height;
		float tanfov = (float) Math.tan(Math.toRadians(fov / 2));

		Matrix4f mat = new Matrix4f().zero();

		float near = -1;
		float far = 400;

		mat.m00(1f / (aspect * tanfov));
		mat.m11(1 / tanfov);
		mat.m22(-(far + near) / (far - near));
		mat.m32(-1);
		mat.m23(-2 * far * near / (far - near));
		return mat;
	}

	/**
	 * 
	 * @return The width of the window
	 */
	public int getWidth() {
		return width;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void processEvent(Event event) {
	}

	/**
	 * Swaps the render buffer and display buffer Polls events
	 */
	public void render() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Sets the clear color of the display
	 * 
	 * @param red   Red channel (0 - 1)
	 * @param green Green channel (0 - 1)
	 * @param blue  Blue channel (0 - 1)
	 * @param alpha Alpha channel (0 - 1)
	 */
	public void setClearColor(float red, float green, float blue, float alpha) {
		glClearColor(red, green, blue, alpha);
	}

	/**
	 * Sets the height of the window
	 * 
	 * @param height New height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the key that closes the window
	 * 
	 * @param closeKey New key to close the window with
	 */
	public void setKeyClose(Key closeKey) {
		this.closeKey = closeKey;
	}

	/**
	 * Sets the width of the window
	 * 
	 * @param width New width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return True if glfw thinks the window should close
	 */
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

}
