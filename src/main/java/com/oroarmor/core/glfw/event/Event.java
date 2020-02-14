package com.oroarmor.core.glfw.event;

/**
 * A interface all events extend
 * 
 * @author OroArmor
 *
 */
public interface Event {
	/**
	 * 
	 * @return The type of event, one of {@link EventType}
	 */
	public EventType getEventType();

	/**
	 * Gets the window the event occured on
	 * 
	 * @return The window handle
	 */
	public long getWindow();

	/**
	 * Sets the window
	 * 
	 * @param window New window handle
	 */
	public void setWindow(long window);
}
