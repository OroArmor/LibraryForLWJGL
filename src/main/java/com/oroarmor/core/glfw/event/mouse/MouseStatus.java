package com.oroarmor.core.glfw.event.mouse;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

/**
 * This class keeps track of all the down buttons and position of the mouse
 *
 * @author OroArmor
 *
 */
public class MouseStatus {
	/**
	 * An array that keeps track of whether or not a mouse button is pushed
	 */
	private static boolean[] mouseButtonStatus = new boolean[GLFW_MOUSE_BUTTON_LAST];

	/**
	 * Mouse position values
	 */
	private static float mouseX, mouseY, pastMouseX, pastMouseY, deltaMouseX, deltaMouseY;

	/**
	 *
	 * @return The current delta x for the mouse
	 */
	public static float getDeltaMouseX() {
		return deltaMouseX;
	}

	/**
	 *
	 * @return The current delta y for the mouse
	 */
	public static float getDeltaMouseY() {
		return deltaMouseY;
	}

	/**
	 *
	 * @return The current x pos of the mouse
	 */
	public static float getMouseX() {
		return mouseX;
	}

	/**
	 *
	 * @return The current y pos of the mouse
	 */
	public static float getMouseY() {
		return mouseY;
	}

	/**
	 *
	 * @return The past mouse x position
	 */
	public static float getPastMouseX() {
		return pastMouseX;
	}

	/**
	 *
	 * @return The past mouse y position
	 */
	public static float getPastMouseY() {
		return pastMouseY;
	}

	/**
	 * Returns true if the button is down
	 *
	 * @param button The button to check
	 * @return True if down
	 */
	public static boolean isMouseButtonDown(final MouseButton button) {
		return mouseButtonStatus[button.getMouseButtonID()];
	}

	public static void setDeltaMouseX(final float _deltaMouseX) {
		deltaMouseX = _deltaMouseX;
	}

	public static void setDeltaMouseY(final float _deltaMouseY) {
		deltaMouseY = _deltaMouseY;
	}

	public static void setMouseButtonDown(final MouseButton button) {
		mouseButtonStatus[button.getMouseButtonID()] = true;
	}

	public static void setMouseButtonUp(final MouseButton button) {
		mouseButtonStatus[button.getMouseButtonID()] = false;
	}

	public static void setMouseX(final float _mouseX) {
		mouseX = _mouseX;
	}

	public static void setMouseY(final float _mouseY) {
		mouseY = _mouseY;
	}

	public static void setPastMouseX(final float _pastMouseX) {
		pastMouseX = _pastMouseX;
	}

	public static void setPastMouseY(final float _pastMouseY) {
		pastMouseY = _pastMouseY;
	}

	/**
	 * Updates the mouse values
	 *
	 * @param xpos New xpos
	 * @param ypos New y pos
	 */
	public static void updateMousePositon(final float xpos, final float ypos) {
		pastMouseX = mouseX;
		pastMouseY = mouseY;

		mouseX = xpos;
		mouseY = ypos;

		deltaMouseX = mouseX - pastMouseX;
		deltaMouseY = mouseY - pastMouseY;
	}

	/**
	 * No instances for you
	 */
	private MouseStatus() {
	}
}
