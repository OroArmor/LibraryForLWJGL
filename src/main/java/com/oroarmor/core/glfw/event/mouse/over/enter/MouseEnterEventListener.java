package com.oroarmor.core.glfw.event.mouse.over.enter;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseEnterEventListener extends Active {
	public static ArrayList<MouseEnterEventListener> mouseEnterListener = new ArrayList<MouseEnterEventListener>();

	public static void addMouseEnterListener(MouseEnterEventListener listener) {
		mouseEnterListener.add(listener);
	}

	public static void processAllMouseEnterEvent(MouseEnterEvent event) {
		for (MouseEnterEventListener listener : mouseEnterListener) {
			if (!listener.isActive()) {
				return;
			}
			listener.processMouseEnterEvent(event);
		}
	}

	public default void addToEnterListeners() {
		addMouseEnterListener(this);
	}

	public void processMouseEnterEvent(MouseEnterEvent event);
}
