package com.oroarmor.core.glfw.event.mouse;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton {
	LEFT_BUTTON(GLFW_MOUSE_BUTTON_LEFT), RIGHT_BUTTON(GLFW_MOUSE_BUTTON_RIGHT), MIDDLE_BUTTON(GLFW_MOUSE_BUTTON_MIDDLE),
	NOT_FOUND(-1);

	private int mouseButtonID;

	private MouseButton(int mouseButtonID) {
		this.mouseButtonID = mouseButtonID;
	}

	public int getMouseButtonID() {
		return mouseButtonID;
	}

	public static MouseButton getButtonFromCode(int code) {
		for (MouseButton button : values()) {
			if (button.getMouseButtonID() == code) {
				return button;
			}
		}

		return NOT_FOUND;
	}

}
