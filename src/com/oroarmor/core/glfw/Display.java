package com.oroarmor.core.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;

public abstract class Display {
	long window;
	int closeKey = GLFW_KEY_ESCAPE;

	private int width, height;

	public Display(int width, int height, String name) {
		this.width = width;
		this.height = height;
		window = GLFWUtil.glfwCreateWindowHelper(width, height, name, NULL, NULL);
		setKeyFunction();

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
	}

	public void enableTransparentcy() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void setKeyClose(int closeKey) {
		this.closeKey = closeKey;
		setKeyFunction();
	}

	public abstract void onKey(int key, int action);

	public void setKeyFunction() {
		glfwSetKeyCallback(window, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				onKey(key, action);
				if (key == closeKey && action == GLFW_RELEASE)
					glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
			}

		});
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
}
