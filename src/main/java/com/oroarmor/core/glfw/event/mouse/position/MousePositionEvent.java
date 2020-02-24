package com.oroarmor.core.glfw.event.mouse.position;

import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;

/**
 * An {@link Event} that is created when the mouse is moved
 * 
 * @author OroArmor
 *
 */
public class MousePositionEvent extends MouseEvent {
	/**
	 * Creates a new {@link MousePositionEvent} using {@link MouseStatus} to set the
	 * values. Processes the {@link MousePositionEvent}.
	 * 
	 * @param window The window of the {@link MousePositionEvent}
	 */
	public static void create(long window) {
		MousePositionEventListener.processAllMousePositionEvent(new MousePositionEvent(window, MouseStatus.getMouseX(),
				MouseStatus.getMouseY(), MouseStatus.getDeltaMouseX(), MouseStatus.getDeltaMouseY()));
	}

	/**
	 * The delta position of the mouse
	 */
	private float deltaX, deltaY;

	/**
	 * The current position of the mouse
	 */
	private float mouseX, mouseY;

	/**
	 * Creates a new {@link MousePositionEvent}, but does not process it
	 * 
	 * @param window The window
	 * @param mouseX The current mouse x
	 * @param mouseY The current mouse y
	 * @param deltaX The delta x
	 * @param deltaY The delta y
	 */
	public MousePositionEvent(long window, float mouseX, float mouseY, float deltaX, float deltaY) {
		super(window, MouseEventType.POSITION);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public float getDeltaX() {
		return deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public float getMouseX() {
		return mouseX;
	}

	public float getMouseY() {
		return mouseY;
	}

	@Override
	public String toString() {
		return "Mouse x, y: " + mouseX + " " + mouseY + ", delta x, y:" + deltaX + " " + deltaY;
	}
}
