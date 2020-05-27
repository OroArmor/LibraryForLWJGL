package com.oroarmor.core.glfw.event.mouse.scroll;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

/**
 * A Listener for {@link MouseScrollEvents}
 * <p>
 * Must call {@code addToScrollListeners} to initialize
 *
 * @author OroArmor
 *
 */
public interface MouseScrollEventListener extends Active {
	/**
	 * The list of {@link MouseScrollEventListener}
	 */
	public static ArrayList<MouseScrollEventListener> mouseScrollListeners = new ArrayList<>();

	/**
	 * Adds a new {@link MouseScrollEventListener} to the list
	 *
	 * @param listener
	 */
	public static void addMouseScrollListener(final MouseScrollEventListener listener) {
		mouseScrollListeners.add(listener);
	}

	/**
	 * Sends a {@link MouseScrollEvent} to all listeners
	 *
	 * @param event {@link MouseScrollEvent} to process
	 */
	public static void processAllMouseScrollEvent(final MouseScrollEvent event) {
		for (final MouseScrollEventListener listener : mouseScrollListeners) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processMouseScrolledEvent(event);
		}
	}

	/**
	 * Initializes the {@link MouseScrollEventListener}
	 */
	public default void addToScrollListeners() {
		addMouseScrollListener(this);
	}

	/**
	 * Process a {@link MouseScrollEvent}
	 *
	 * @param event {@link MouseScrollEvent} to process
	 */
	public void processMouseScrolledEvent(MouseScrollEvent event);
}
