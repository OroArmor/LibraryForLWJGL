package com.oroarmor.core.glfw.event.key.press;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyPressEventListener extends Active {
	public static ArrayList<KeyPressEventListener> keyPressListeners = new ArrayList<KeyPressEventListener>();

	public static void addKeyPressListener(KeyPressEventListener listener) {
		keyPressListeners.add(listener);
	}

	public static void processAllKeyPressEvent(KeyPressEvent event) {
		for (KeyPressEventListener listener : keyPressListeners) {
			listener.processKeyPressedEvent(event);
		}
	}

	public default void addToKeyPressListeners() {
		addKeyPressListener(this);
	}

	public void processKeyPressedEvent(KeyPressEvent event);
}
