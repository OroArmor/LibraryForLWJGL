package com.oroarmor.core.glfw.event.mouse.button.release;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseButtonReleaseEventListener extends Active {
	public static ArrayList<MouseButtonReleaseEventListener> mouseReleaseListeners = new ArrayList<>();

	public static void addMouseReleaseListener(final MouseButtonReleaseEventListener listener) {
		mouseReleaseListeners.add(listener);
	}

	public static void processAllMouseReleaseEvent(final MouseReleaseEvent event) {
		for (final MouseButtonReleaseEventListener listener : mouseReleaseListeners) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processMouseReleasedEvent(event);
		}
	}

	public default void addToReleaseListeners() {
		addMouseReleaseListener(this);
	}

	public void processMouseReleasedEvent(MouseReleaseEvent event);
}
