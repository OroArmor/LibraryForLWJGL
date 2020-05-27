package com.oroarmor.core.glfw.event.mouse.over.leave;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseLeaveEventListener extends Active {
	public static ArrayList<MouseLeaveEventListener> mouseLeaveListener = new ArrayList<>();

	public static void addMouseLeaveListener(final MouseLeaveEventListener listener) {
		mouseLeaveListener.add(listener);
	}

	public static void processAllMouseLeaveEvent(final MouseLeaveEvent event) {
		for (final MouseLeaveEventListener listener : mouseLeaveListener) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processMouseLeaveEvent(event);
		}
	}

	public default void addToLeaveListeners() {
		addMouseLeaveListener(this);
	}

	public void processMouseLeaveEvent(MouseLeaveEvent event);
}
