package com.oroarmor.core.glfw.event.mouse.button.release;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.Active;

public interface MouseButtonReleaseEventListener extends Active {
	public static ArrayList<MouseButtonReleaseEventListener> mouseReleaseListeners = new ArrayList<MouseButtonReleaseEventListener>();

	public static void addMouseReleaseListener(MouseButtonReleaseEventListener listener) {
		mouseReleaseListeners.add(listener);
	}

	public static void processAllMouseReleaseEvent(MouseReleaseEvent event) {
		for (MouseButtonReleaseEventListener listener : mouseReleaseListeners) {
			listener.processMouseReleasedEvent(event);
		}
	}

	public default void addToReleaseListeners() {
		addMouseReleaseListener(this);
	}

	public void processMouseReleasedEvent(MouseReleaseEvent event);
}
