package com.oroarmor.core.glfw.event.mouse.position;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MousePositionEventListener extends Active {
	public static ArrayList<MousePositionEventListener> mousePositionListener = new ArrayList<>();

	public static void addMousePositionListener(final MousePositionEventListener listener) {
		mousePositionListener.add(listener);
	}

	public static void processAllMousePositionEvent(final MousePositionEvent event) {
		for (final MousePositionEventListener listener : mousePositionListener) {
			if (!listener.isActive()) {
				continue;
			}
			listener.processMousePositionEvent(event);
		}
	}

	public default void addToPositionListeners() {
		addMousePositionListener(this);
	}

	public void processMousePositionEvent(MousePositionEvent event);

}
