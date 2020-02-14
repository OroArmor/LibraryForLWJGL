package com.oroarmor.core.glfw.event.key.release;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface KeyReleaseEventListener extends ActiveListener {
	public void processKeyReleasedEvent(KeyReleaseEvent event);

	public static ArrayList<KeyReleaseEventListener> keyReleaseListeners = new ArrayList<KeyReleaseEventListener>();

	public static void addKeyReleaseListener(KeyReleaseEventListener listener) {
		keyReleaseListeners.add(listener);
	}

	public static void processAllKeyReleaseEvent(KeyReleaseEvent event) {
		for (KeyReleaseEventListener listener : keyReleaseListeners) {
			listener.processKeyReleasedEvent(event);
		}
	}

	public default void addToKeyReleaseListeners() {
		addKeyReleaseListener(this);
	}
}
