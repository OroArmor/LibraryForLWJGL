package com.oroarmor.core.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
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

	boolean active = true;

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public Display(int width, int height, String name) {
		this.width = width;
		this.height = height;
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

	public void enableTransparentcy() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void setKeyClose(Key closeKey) {
		this.closeKey = closeKey;
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public void render() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void setClearColor(float red, float green, float blue, float alpha) {
		glClearColor(red, green, blue, alpha);
	}

	public void close() {
		if (!glfwWindowShouldClose(window))
			glfwSetWindowShouldClose(window, true);
	}

	public void end() {
		glfwTerminate();
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
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
		float far = 1000;

		mat.m00(1f / (aspect * tanfov));
		mat.m11(1 / tanfov);
		mat.m22(-(far + near) / (far - near));
		mat.m32(-1);
		mat.m23(-2 * far * near / (far - near));
		return mat;
	}

	@Override
	public void processEvent(Event event) {
	}

	@Override
	public abstract void processKeyPressedEvent(KeyPressEvent event);

}
