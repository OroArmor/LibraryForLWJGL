package com.oroarmor.core.glfw.event.key.release;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyReleaseEventListener extends Active {
	public static ArrayList<KeyReleaseEventListener> keyReleaseListeners = new ArrayList<>();

	public static void addKeyReleaseListener(final KeyReleaseEventListener listener) {
		keyReleaseListeners.add(listener);
	}

	public static void processAllKeyReleaseEvent(final KeyReleaseEvent event) {
		for (final KeyReleaseEventListener listener : keyReleaseListeners) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processKeyReleasedEvent(event);
		}
	}

	public default void addToKeyReleaseListeners() {
		addKeyReleaseListener(this);
	}

	public void processKeyReleasedEvent(KeyReleaseEvent event);
}
