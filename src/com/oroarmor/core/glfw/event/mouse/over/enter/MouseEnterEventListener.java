package com.oroarmor.core.glfw.event.mouse.over.enter;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface MouseEnterEventListener extends ActiveListener {
	public static ArrayList<MouseEnterEventListener> mouseEnterListener = new ArrayList<MouseEnterEventListener>();

	public static void addMouseEnterListener(MouseEnterEventListener listener) {
		mouseEnterListener.add(listener);
	}

	public static void processAllMouseEnterEvent(MouseEnterEvent event) {
		for (MouseEnterEventListener listener : mouseEnterListener) {
			listener.processMouseEnterEvent(event);
		}
	}

	public default void addToEnterListeners() {
		addMouseEnterListener(this);
	}

	public void processMouseEnterEvent(MouseEnterEvent event);
}
