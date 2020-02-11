package com.oroarmor.core.glfw.event.mouse.button;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_MIDDLE;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;

public enum MouseButton {
	LEFT_BUTTON(GLFW_MOUSE_BUTTON_LEFT), MIDDLE_BUTTON(GLFW_MOUSE_BUTTON_MIDDLE), NOT_FOUND(-1),
	RIGHT_BUTTON(GLFW_MOUSE_BUTTON_RIGHT);

	public static MouseButton getButtonFromCode(int code) {
		for (MouseButton button : values()) {
			if (button.getMouseButtonID() == code) {
				return button;
			}
		}

		return NOT_FOUND;
	}

	private int mouseButtonID;

	private MouseButton(int mouseButtonID) {
		this.mouseButtonID = mouseButtonID;
	}

	public int getMouseButtonID() {
		return mouseButtonID;
	}

}
