package com.oroarmor.core.glfw.event.mouse;

public class MousePositionEvent extends MouseEvent {
	private float mouseX, mouseY;
	private float deltaX, deltaY;

	public MousePositionEvent(long window, float mouseX, float mouseY, float deltaX, float deltaY) {
		setWindow(window);
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

	public static void create(long window, float xpos, float ypos) {

	}

}
