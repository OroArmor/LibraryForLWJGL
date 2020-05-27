package com.oroarmor.core.glfw.event.mouse.position;

import com.oroarmor.core.glfw.event.GLFWEventMods;
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
	public static void create(final long window, final GLFWEventMods mods) {
		MousePositionEventListener.processAllMousePositionEvent(new MousePositionEvent(window, MouseStatus.getMouseX(),
				MouseStatus.getMouseY(), MouseStatus.getDeltaMouseX(), MouseStatus.getDeltaMouseY(), mods));
	}

	/**
	 * The delta position of the mouse
	 */
	private final float deltaX, deltaY;

	/**
	 * The current position of the mouse
	 */
	private final float mouseX, mouseY;

	/**
	 * Creates a new {@link MousePositionEvent}, but does not process it
	 *
	 * @param window The window
	 * @param mouseX The current mouse x
	 * @param mouseY The current mouse y
	 * @param deltaX The delta x
	 * @param deltaY The delta y
	 */
	public MousePositionEvent(final long window, final float mouseX, final float mouseY, final float deltaX, final float deltaY, final GLFWEventMods mods) {
		super(window, MouseEventType.POSITION, mods);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public float getDeltaX() {
		return this.deltaX;
	}

	public float getDeltaY() {
		return this.deltaY;
	}

	public float getMouseX() {
		return this.mouseX;
	}

	public float getMouseY() {
		return this.mouseY;
	}

	@Override
	public String toString() {
		return "Mouse x, y: " + this.mouseX + " " + this.mouseY + ", delta x, y:" + this.deltaX + " " + this.deltaY;
	}
}
