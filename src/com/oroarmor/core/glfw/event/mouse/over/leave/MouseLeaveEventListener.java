package com.oroarmor.core.glfw.event.mouse.over.leave;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface MouseLeaveEventListener extends ActiveListener {
	public static ArrayList<MouseLeaveEventListener> mouseLeaveListener = new ArrayList<MouseLeaveEventListener>();

	public static void addMouseLeaveListener(MouseLeaveEventListener listener) {
		mouseLeaveListener.add(listener);
	}

	public static void processAllMouseLeaveEvent(MouseLeaveEvent event) {
		for (MouseLeaveEventListener listener : mouseLeaveListener) {
			listener.processMouseLeaveEvent(event);
		}
	}

	public default void addToLeaveListeners() {
		addMouseLeaveListener(this);
	}

	public void processMouseLeaveEvent(MouseLeaveEvent event);
}
