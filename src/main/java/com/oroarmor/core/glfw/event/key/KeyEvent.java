package com.oroarmor.core.glfw.event.key;

import org.lwjgl.glfw.GLFW;

import com.oroarmor.core.game.event.Event;
import com.oroarmor.core.game.event.EventType;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEventListener;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEventListener;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEventListener;

public abstract class KeyEvent implements Event {
	public static enum KeyEventType {
		HOLD, PRESS, RELEASE;
	}

	public static void create(int keyCode, int action, long window) {
		Key key = Key.getKey(keyCode);
		if (action == GLFW.GLFW_PRESS) {
			KeyPressEvent newEvent = new KeyPressEvent(key, window);
			KeyPressEventListener.processAllKeyPressEvent(newEvent);
		} else if (action == GLFW.GLFW_RELEASE) {
			KeyReleaseEvent newEvent = new KeyReleaseEvent(key, window);
			KeyReleaseEventListener.processAllKeyReleaseEvent(newEvent);
		} else {
			KeyHoldEvent newEvent = new KeyHoldEvent(key, window);
			KeyHoldEventListener.processAllKeyPressEvent(newEvent);
		}
	}

	public Key key;

	public long window;

	@Override
	public EventType getEventType() {
		return EventType.KEY;
	}

	public Key getKey() {
		return key;
	}

	public abstract KeyEventType getKeyEventType();

	@Override
	public long getWindow() {
		return window;
	}

	@Override
	public void setWindow(long window) {
		this.window = window;
	}

	@Override
	public String toString() {
		return "keycode: " + key.getKeyCode() + ", key: " + key + ", type: " + getKeyEventType();
	}
}
