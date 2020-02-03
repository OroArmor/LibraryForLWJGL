package com.oroarmor.core.glfw.event.key;

import org.lwjgl.glfw.GLFW;

import com.oroarmor.core.glfw.event.Event;
import com.oroarmor.core.glfw.event.EventListenerManager;
import com.oroarmor.core.glfw.event.EventType;

public abstract class KeyEvent implements Event {
	public static enum KeyEventType {
		PRESS, HOLD, RELEASE;
	}

	public Key key;

	public long window;

	@Override
	public void setWindow(long window) {
		this.window = window;
	}

	@Override
	public long getWindow() {
		return window;
	}

	public Key getKey() {
		return key;
	}

	public abstract KeyEventType getKeyEventType();

	@Override
	public EventType getEventType() {
		return EventType.KEY;
	}

	public static void create(int keyCode, int action, long window) {
		KeyEvent newEvent;
		Key key = Key.getKey(keyCode);
		if (action == GLFW.GLFW_PRESS) {
			newEvent = new KeyPressEvent(key, window);
		} else if (action == GLFW.GLFW_RELEASE) {
			newEvent = new KeyReleaseEvent(key, window);
		} else {
			newEvent = new KeyHoldEvent(key, window);
		}

		EventListenerManager.addKeyEvent(newEvent);
	}

	@Override
	public String toString() {
		return "keycode: " + key.getKeyCode() + ", key: " + key + ", type: " + getKeyEventType();
	}
}
