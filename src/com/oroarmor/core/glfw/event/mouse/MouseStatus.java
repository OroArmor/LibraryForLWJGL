package com.oroarmor.core.glfw.event.mouse;

import static org.lwjgl.glfw.GLFW.*;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

public class MouseStatus {
	private static boolean[] mouseButtonStatus = new boolean[GLFW_MOUSE_BUTTON_LAST];

	private static float mouseX, mouseY, pastMouseX, pastMouseY, deltaMouseX, deltaMouseY;

	public static float getDeltaMouseX() {
		return deltaMouseX;
	}

	public static float getDeltaMouseY() {
		return deltaMouseY;
	}

	public static float getMouseX() {
		return mouseX;
	}

	public static float getMouseY() {
		return mouseY;
	}

	public static float getPastMouseX() {
		return pastMouseX;
	}

	public static float getPastMouseY() {
		return pastMouseY;
	}

	public static boolean isMouseButtonDown(MouseButton button) {
		return mouseButtonStatus[button.getMouseButtonID()];
	}

	public static void setDeltaMouseX(float _deltaMouseX) {
		deltaMouseX = _deltaMouseX;
	}

	public static void setDeltaMouseY(float _deltaMouseY) {
		deltaMouseY = _deltaMouseY;
	}

	public static void setMouseButtonDown(MouseButton button) {
		mouseButtonStatus[button.getMouseButtonID()] = true;
	}

	public static void setMouseButtonUp(MouseButton button) {
		mouseButtonStatus[button.getMouseButtonID()] = false;
	}

	public static void setMouseX(float _mouseX) {
		mouseX = _mouseX;
	}

	public static void setMouseY(float _mouseY) {
		mouseY = _mouseY;
	}

	public static void setPastMouseX(float _pastMouseX) {
		pastMouseX = _pastMouseX;
	}

	public static void setPastMouseY(float _pastMouseY) {
		pastMouseY = _pastMouseY;
	}

	public static void updateMousePositon(float xpos, float ypos) {
		pastMouseX = mouseX;
		pastMouseY = mouseY;

		mouseX = xpos;
		mouseY = ypos;

		deltaMouseX = mouseX - pastMouseX;
		deltaMouseY = mouseY - pastMouseY;
	}

	private MouseStatus() {
	}
}
