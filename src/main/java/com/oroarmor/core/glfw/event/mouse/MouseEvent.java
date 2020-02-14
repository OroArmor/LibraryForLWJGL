package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.Event;
import com.oroarmor.core.glfw.event.EventType;

/**
 * An {@link Event} that all other mouse events use
 * 
 * @author OroArmor
 *
 */
public abstract class MouseEvent implements Event {

	/**
	 * The type of mouse event
	 * 
	 * @author OroArmor
	 *
	 */
	public static enum MouseEventType {
		/**
		 * A mouse button event
		 */
		BUTTON,
		/**
		 * A mouse over event
		 */
		OVER,
		/**
		 * A mouse position event
		 */
		POSITION,
		/**
		 * A mouse scroll event
		 */
		SCROLL;
	}

	/**
	 * The type of mouse event
	 */
	protected MouseEventType type;

	/**
	 * The window of the event
	 */
	protected long window;

	/**
	 * Creates a new mouse event
	 * 
	 * @param window The window the event occurred in
	 * @param type   The type of event {@link MouseEventType}
	 */
	public MouseEvent(long window, MouseEventType type) {
		this.window = window;
		this.type = type;
	}

	@Override
	public EventType getEventType() {
		return EventType.MOUSE;
	}

	/**
	 * 
	 * @return The type of mouse event
	 */
	public MouseEventType getMouseEventType() {
		return type;
	}

	@Override
	public long getWindow() {
		return window;
	}

	@Override
	public void setWindow(long window) {
		this.window = window;
	}
}
