package com.oroarmor.core.glfw.event;

import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.mouse.MouseEventListener;

/**
 * Listener that listens to all events
 * <p>
 * Must call {@code addToListeners()} in order to register the listener
 *
 * @author OroArmor
 *
 */
public interface GLFWEventListener extends KeyEventListener, MouseEventListener {

	/**
	 * Registers the listener with all events
	 */
	public default void addToListeners() {
		addToKeyListeners();
		addToMouseListeners();
	}

	/**
	 * Processes any {@link GLFWEvent}
	 *
	 * @param event {@link GLFWEvent} to process
	 */
	public void processGLFWEvent(GLFWEvent event);

}
