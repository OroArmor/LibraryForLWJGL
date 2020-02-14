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
public interface EventListener extends KeyEventListener, MouseEventListener {

	/**
	 * Registers the listener with all events
	 */
	public default void addToListeners() {
		this.addToKeyListeners();
		this.addToMouseListeners();
	}

	/**
	 * Processes any {@link Event}
	 * 
	 * @param event {@link Event} to process
	 */
	public void processEvent(Event event);

}
