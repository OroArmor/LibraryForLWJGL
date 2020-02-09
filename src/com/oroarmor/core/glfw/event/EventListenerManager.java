package com.oroarmor.core.glfw.event;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.mouse.MouseEventListener;
import com.oroarmor.core.glfw.event.mouse.MouseScrollEvent;

public class EventListenerManager {
	public static ArrayList<KeyEventListener> keyListeners = new ArrayList<KeyEventListener>();
	public static ArrayList<EventListener> eventListeners = new ArrayList<EventListener>();
	public static ArrayList<MouseEventListener> mouseListeners = new ArrayList<MouseEventListener>();

	public static void addEvent(Event event) {
		for (EventListener listener : eventListeners) {
			listener.processEvent(event);
		}
	}

	public static void addKeyEvent(KeyEvent event) {
		for (KeyEventListener listener : keyListeners) {
			listener.processKeyEvent(event);
		}
	}

	public static void addListener(EventListener listener) {
		boolean found = false;

		if (listener instanceof KeyEventListener) {
			keyListeners.add(listener);
			found = true;
		}

		if (!found) {
			eventListeners.add(listener);
		}
	}

	public static void removeListener(EventListener listener) {
		eventListeners.remove(listener);
		keyListeners.remove(listener);
	}

	public static void addMouseEvent(MouseScrollEvent event) {
		for (MouseEventListener listener : mouseListeners) {
			listener.processMouseEvent(event);
		}
	}

	public static void addKeyListener(KeyEventListener listener) {
		keyListeners.add(listener);
	}
}
