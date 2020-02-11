package com.oroarmor.core.glfw.event.mouse.position;

import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;

public class MousePositionEvent extends MouseEvent {
	private float mouseX, mouseY;
	private float deltaX, deltaY;

	public MousePositionEvent(long window, float mouseX, float mouseY, float deltaX, float deltaY) {
		super(window, MouseEventType.POSITION);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public float getMouseX() {
		return mouseX;
	}

	public float getMouseY() {
		return mouseY;
	}

	public float getDeltaX() {
		return deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public String toString() {
		return "Mouse x, y: " + mouseX + " " + mouseY + ", delta x, y:" + deltaX + " " + deltaY;
	}

	public static void create(long window) {
		MousePositionEventListener.processAllMousePositionEvent(new MousePositionEvent(window, MouseStatus.getMouseX(),
				MouseStatus.getMouseY(), MouseStatus.getDeltaMouseX(), MouseStatus.getDeltaMouseY()));
	}
}
