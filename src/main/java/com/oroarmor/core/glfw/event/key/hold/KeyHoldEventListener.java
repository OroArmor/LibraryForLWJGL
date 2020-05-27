package com.oroarmor.core.glfw.event.key.hold;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyHoldEventListener extends Active {
	public static ArrayList<KeyHoldEventListener> keyPressListeners = new ArrayList<>();

	public static void addKeyHoldListener(final KeyHoldEventListener listener) {
		keyPressListeners.add(listener);
	}

	public static void processAllKeyPressEvent(final KeyHoldEvent event) {
		for (final KeyHoldEventListener listener : keyPressListeners) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processKeyHeldEvent(event);
		}
	}

	public default void addToKeyHoldListeners() {
		addKeyHoldListener(this);
	}

	public void processKeyHeldEvent(KeyHoldEvent event);
}
