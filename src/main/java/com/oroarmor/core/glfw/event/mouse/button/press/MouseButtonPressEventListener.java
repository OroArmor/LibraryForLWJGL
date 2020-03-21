package com.oroarmor.core.glfw.event.mouse.button.press;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseButtonPressEventListener extends Active {
	public static ArrayList<MouseButtonPressEventListener> mousePressListeners = new ArrayList<MouseButtonPressEventListener>();

	public static void addMousePressListener(MouseButtonPressEventListener listener) {
		mousePressListeners.add(listener);
	}

	public static void processAllMousePressEvent(MousePressEvent event) {
		for (MouseButtonPressEventListener listener : mousePressListeners) {
			listener.processMousePressEvent(event);
		}
	}

	public default void addToPressListeners() {
		addMousePressListener(this);
	}

	public void processMousePressEvent(MousePressEvent event);
}
