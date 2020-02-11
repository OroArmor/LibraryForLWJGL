package com.oroarmor.core.glfw.event.mouse.scroll;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface MouseScrollEventListener extends ActiveListener {
	public void processMouseScrolledEvent(MouseScrollEvent event);

	public static ArrayList<MouseScrollEventListener> mouseScrollListeners = new ArrayList<MouseScrollEventListener>();

	public static void addMouseScrollListener(MouseScrollEventListener listener) {
		mouseScrollListeners.add(listener);
	}

	public static void processAllMouseScrollEvent(MouseScrollEvent event) {
		for (MouseScrollEventListener listener : mouseScrollListeners) {
			listener.processMouseScrolledEvent(event);
		}
	}

	public default void addToScrollListeners() {
		addMouseScrollListener(this);
	}
}
