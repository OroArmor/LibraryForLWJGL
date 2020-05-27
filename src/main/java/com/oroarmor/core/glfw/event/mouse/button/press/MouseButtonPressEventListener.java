package com.oroarmor.core.glfw.event.mouse.button.press;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseButtonPressEventListener extends Active {
	public static ArrayList<MouseButtonPressEventListener> mousePressListeners = new ArrayList<>();

	public static void addMousePressListener(final MouseButtonPressEventListener listener) {
		mousePressListeners.add(listener);
	}

	public static void processAllMousePressEvent(final MousePressEvent event) {
		for (final MouseButtonPressEventListener listener : mousePressListeners) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processMousePressEvent(event);
		}
	}

	public default void addToPressListeners() {
		addMousePressListener(this);
	}

	public void processMousePressEvent(MousePressEvent event);
}
