package com.oroarmor.core.glfw.event.key.press;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyPressEventListener extends Active {
	public static ArrayList<KeyPressEventListener> keyPressListeners = new ArrayList<>();

	public static void addKeyPressListener(final KeyPressEventListener listener) {
		keyPressListeners.add(listener);
	}

	public static void processAllKeyPressEvent(final KeyPressEvent event) {
		for (final KeyPressEventListener listener : keyPressListeners) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processKeyPressedEvent(event);
		}
	}

	public default void addToKeyPressListeners() {
		addKeyPressListener(this);
	}

	public void processKeyPressedEvent(KeyPressEvent event);
}
