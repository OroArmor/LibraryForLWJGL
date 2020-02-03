package com.oroarmor.core.glfw.event;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import org.lwjgl.glfw.GLFWKeyCallback;

import com.oroarmor.core.glfw.event.key.KeyEvent;

public class EventCreator {

	public static void initalizeWindow(long window) {
		glfwSetKeyCallback(window, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				KeyEvent.create(key, action, window);
			}
		});
	}
}
