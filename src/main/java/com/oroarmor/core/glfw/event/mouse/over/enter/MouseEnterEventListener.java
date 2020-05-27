package com.oroarmor.core.glfw.event.mouse.over.enter;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseEnterEventListener extends Active {
	public static ArrayList<MouseEnterEventListener> mouseEnterListener = new ArrayList<>();

	public static void addMouseEnterListener(final MouseEnterEventListener listener) {
		mouseEnterListener.add(listener);
	}

	public static void processAllMouseEnterEvent(final MouseEnterEvent event) {
		for (final MouseEnterEventListener listener : mouseEnterListener) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processMouseEnterEvent(event);
		}
	}

	public default void addToEnterListeners() {
		addMouseEnterListener(this);
	}

	public void processMouseEnterEvent(MouseEnterEvent event);
}
