package com.oroarmor.core.glfw.event.mouse.over;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;

public enum MouseOverEventType {

	ENTER(GLFW_TRUE), LEAVE(GLFW_FALSE);

	public static MouseOverEventType getTypeFromInt(int type) {
		if (type == 1) {
			return ENTER;
		}
		return LEAVE;
	}

	private int action;

	private MouseOverEventType(int action) {
		this.action = action;
	}

	public int getAction() {
		return action;
	}
}
