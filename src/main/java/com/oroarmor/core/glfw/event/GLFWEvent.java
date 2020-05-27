package com.oroarmor.core.glfw.event;

import com.oroarmor.core.game.event.Event;

public interface GLFWEvent extends Event {
	/**
	 *
	 * @return The GLFW Event Mods from the GLFW Event
	 */
	public GLFWEventMods getEventMods();

	/**
	 * Reset the Event Mods
	 *
	 * @param newMods New Event Mods
	 */
	public void setEventMods(GLFWEventMods newMods);
}
