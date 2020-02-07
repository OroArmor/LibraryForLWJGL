package com.oroarmor.core.glfw.event;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.mouse.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.MouseScrollEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;

public class EventCreator {

	public static void initalizeWindow(long window) {
		glfwSetKeyCallback(window, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				KeyEvent.create(key, action, window);
			}
		});

		glfwSetScrollCallback(window, new GLFWScrollCallback() {

			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				MouseScrollEvent.create(window, (float) xoffset, (float) yoffset);
			}

		});

		glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xpos, double ypos) {
				MouseStatus.updateMousePositon((float) xpos, (float) ypos);
				MousePositionEvent.create(window, (float) xpos, (float) ypos);
			}
		});

	}
}
