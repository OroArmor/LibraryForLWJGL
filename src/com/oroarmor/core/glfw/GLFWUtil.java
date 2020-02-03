package com.oroarmor.core.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import org.lwjgl.opengl.GL;

public class GLFWUtil {
	public static long glfwCreateWindowHelper(int width, int height, CharSequence name, long monitorHandle,
			long windowHandle) {
		long window = -1;

		if (!glfwInit()) {
			System.exit(-1);
		}

		window = glfwCreateWindow(width, height, name, monitorHandle, windowHandle);
		if (window == -1) {
			glfwTerminate();
			return -1;
		}

		glfwMakeContextCurrent(window);

		glfwSwapInterval(1);
		GL.createCapabilities();
		return window;
	}

	public static enum OpenGLProfile {
		CORE(GLFW_OPENGL_CORE_PROFILE), ANY(GLFW_OPENGL_ANY_PROFILE), COMPAT(GLFW_OPENGL_COMPAT_PROFILE);

		private int profile;

		private OpenGLProfile(int profile) {
			this.profile = profile;
		}

		public int getProfile() {
			return profile;
		}

	}

	public static void setWindowHints(int glfwMajorVersion, int glfwMinorVersion, OpenGLProfile glfwOpenGLProfile) {
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, glfwMajorVersion);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, glfwMinorVersion);
		glfwWindowHint(GLFW_OPENGL_PROFILE, glfwOpenGLProfile.getProfile());

	}
}
