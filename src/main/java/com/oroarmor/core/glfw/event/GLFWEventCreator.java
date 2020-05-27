package com.oroarmor.core.glfw.event;

import static org.lwjgl.glfw.GLFW.glfwSetCursorEnterCallback;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEvent;
import com.oroarmor.core.glfw.event.mouse.over.MouseOverEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;

/**
 * Class that is in charge of creating new events based on GLFW actions
 *
 * @author OroArmor
 *
 */
public class GLFWEventCreator {

	public static void initalizeWindow(final long window) {
		glfwSetKeyCallback(window, new GLFWKeyCallback() {
			@Override
			public void invoke(final long window, final int key, final int scancode, final int action, final int mods) {
				KeyEvent.create(key, action, window, new GLFWEventMods(mods));
			}
		});

		glfwSetScrollCallback(window, new GLFWScrollCallback() {

			@Override
			public void invoke(final long window, final double xoffset, final double yoffset) {
				MouseScrollEvent.create(window, (float) xoffset, (float) yoffset, new GLFWEventMods(0));
			}

		});

		glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
			@Override
			public void invoke(final long window, final double xpos, final double ypos) {
				MouseStatus.updateMousePositon((float) xpos, (float) ypos);
				MousePositionEvent.create(window, GLFWEventMods.createFromCurrentStatus());
			}
		});

		glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
			@Override
			public void invoke(final long window, final int button, final int action, final int mods) {
				MouseButtonEvent.create(window, button, action, new GLFWEventMods(mods));
			}
		});

		glfwSetCursorEnterCallback(window, new GLFWCursorEnterCallback() {
			@Override
			public void invoke(final long window, final boolean entered) {
				MouseOverEvent.create(window, entered ? 1 : 0, GLFWEventMods.createFromCurrentStatus());
			}
		});

	}
}
