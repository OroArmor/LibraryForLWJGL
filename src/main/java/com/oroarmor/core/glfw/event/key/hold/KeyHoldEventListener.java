package com.oroarmor.core.glfw.event.key.hold;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyHoldEventListener extends Active {
	public static ArrayList<KeyHoldEventListener> keyPressListeners = new ArrayList<KeyHoldEventListener>();

	public static void addKeyHoldListener(KeyHoldEventListener listener) {
		keyPressListeners.add(listener);
	}

	public static void processAllKeyPressEvent(KeyHoldEvent event) {
		for (KeyHoldEventListener listener : keyPressListeners) {
			if (!listener.isActive()) {
				return;
			}
			listener.processKeyHeldEvent(event);
		}
	}

	public default void addToKeyHoldListeners() {
		addKeyHoldListener(this);
	}

	public void processKeyHeldEvent(KeyHoldEvent event);
}
