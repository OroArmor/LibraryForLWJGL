package com.oroarmor.core.glfw.event.mouse.scroll;

import com.oroarmor.core.game.event.Event;
import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.mouse.MouseEvent;

/**
 * An {@link Event} and {@link MouseEvent} that occurs when the users scrolls
 * the mouse
 * 
 * @author OroArmor
 *
 */
public class MouseScrollEvent extends MouseEvent {
	/**
	 * Creates a new {@link MouseScrollEvent} and sends it to all
	 * {@link MouseScrollEventListeners}
	 * 
	 * @param window  The window
	 * @param xoffset The X offset of the scroll
	 * @param yoffset The Y offset of the scroll
	 */
	public static void create(long window, float xoffset, float yoffset, GLFWEventMods mods) {
		MouseScrollEvent event = new MouseScrollEvent(window, xoffset, yoffset, mods);

		MouseScrollEventListener.processAllMouseScrollEvent(event);
	}

	/**
	 * The scroll values for the event
	 */
	private float scrollX, scrollY;

	/**
	 * Creates a new {@link MouseScrollEvent}
	 * 
	 * @param window  The window
	 * @param scrollX The scroll x value
	 * @param scrollY The scroll y value
	 */
	public MouseScrollEvent(long window, float scrollX, float scrollY, GLFWEventMods mods) {
		super(window, MouseEventType.SCROLL, mods);
		this.scrollX = scrollX;
		this.scrollY = scrollY;
	}

	/**
	 * 
	 * @return The scroll x value
	 */
	public float getScrollX() {
		return scrollX;
	}

	/**
	 * 
	 * @return The scroll y value
	 */
	public float getScrollY() {
		return scrollY;
	}

	@Override
	public String toString() {
		return "scroll x: " + scrollX + ", scroll y: " + scrollY;
	}
}
