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
import com.oroarmor.core.glfw.event.EventListenerManager;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.key.KeyPressEvent;

public abstract class Display implements KeyEventListener {
	long window;
	public Key closeKey = Key.ESCAPE;

	private int width, height;
	private int owidth, oheight;

	boolean active = true;

	boolean maximized = false;

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
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);

		EventCreator.initalizeWindow(window);
		EventListenerManager.addListener(this);
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void close() {
		if (!glfwWindowShouldClose(window))
			glfwSetWindowShouldClose(window, true);
	}

	public void enableTransparentcy() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void end() {
		glfwTerminate();
	}

	public void fullscreen() {
		if (!maximized) {
			glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, 1920, 1080, 60);
		} else {
			glfwSetWindowMonitor(window, NULL, 100, 100, owidth, oheight, 60);
		}
		maximized = !maximized;
	}

	public int getHeight() {
		return height;
	}

	public Matrix4f getOrthoViewModel() {
		return new Matrix4f().ortho(0, width, 0, height, -10000, 10000);
	}

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

	@Override
	public abstract void processKeyPressedEvent(KeyPressEvent event);

	public void render() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	public void setClearColor(float red, float green, float blue, float alpha) {
		glClearColor(red, green, blue, alpha);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setKeyClose(Key closeKey) {
		this.closeKey = closeKey;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

}
