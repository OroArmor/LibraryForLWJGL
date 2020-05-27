package com.oroarmor.core.glfw;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_ANY_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_COMPAT_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;

import org.lwjgl.opengl.GL;

/**
 * A utility class for GLFW that can create windows and do some other
 * configuration
 *
 * @author OroArmor
 *
 */
public class GLFWUtil {
	/**
	 * OpenGL Profiles
	 *
	 * @author OroArmor
	 *
	 */
	public static enum OpenGLProfile {
		/**
		 * Any profile
		 */
		ANY(GLFW_OPENGL_ANY_PROFILE),
		/**
		 * Compatibility Profile
		 */
		COMPAT(GLFW_OPENGL_COMPAT_PROFILE),
		/**
		 * Core Profile
		 */
		CORE(GLFW_OPENGL_CORE_PROFILE);

		private final int profile;

		private OpenGLProfile(final int profile) {
			this.profile = profile;
		}

		/**
		 *
		 * @return The GLFW id for the profile
		 */
		public int getProfile() {
			return this.profile;
		}

	}

	/**
	 * Creates a window from the input parameters
	 *
	 * @param width         The width of the window
	 * @param height        The height of the window
	 * @param name          The name of the window
	 * @param monitorHandle The monitor to display the window on for full screen
	 * @param windowHandle  Another GLFW Window to share with
	 * @return The window handle
	 */
	public static long glfwCreateWindowHelper(final int width, final int height, final CharSequence name, final long monitorHandle,
			final long windowHandle) {
		long window = -1;

		if (!glfwInit()) {
			System.exit(-1);
		}
		glfwWindowHint(GLFW_SAMPLES, 4);
		window = glfwCreateWindow(width, height, name, monitorHandle, windowHandle);
		if (window == -1) {
			glfwTerminate();
			return -1;
		}

		glfwMakeContextCurrent(window);

		glfwSwapInterval(1);
		GL.createCapabilities();
		glEnable(GL_MULTISAMPLE);
		return window;
	}

	/**
	 * Sets the OpenGL Version
	 *
	 * @param glfwMajorVersion  Major version
	 * @param glfwMinorVersion  MinorVersion
	 * @param glfwOpenGLProfile Compatibility Profile {@link OpenGLProfile}
	 */
	public static void setWindowHints(final int glfwMajorVersion, final int glfwMinorVersion, final OpenGLProfile glfwOpenGLProfile) {
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, glfwMajorVersion);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, glfwMinorVersion);
		glfwWindowHint(GLFW_OPENGL_PROFILE, glfwOpenGLProfile.getProfile());

	}
}
