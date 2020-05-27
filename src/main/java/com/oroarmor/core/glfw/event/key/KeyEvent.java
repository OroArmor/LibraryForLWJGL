package com.oroarmor.core.glfw.event.key;

import org.lwjgl.glfw.GLFW;

import com.oroarmor.core.game.event.EventType;
import com.oroarmor.core.glfw.event.GLFWEvent;
import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEventListener;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEventListener;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEventListener;

public abstract class KeyEvent implements GLFWEvent {
	public static enum KeyEventType {
		HOLD, PRESS, RELEASE;
	}

	public static void create(final int keyCode, final int action, final long window, final GLFWEventMods mods) {
		final Key key = Key.getKey(keyCode);
		if (action == GLFW.GLFW_PRESS) {
			final KeyPressEvent newEvent = new KeyPressEvent(key, window, mods);
			KeyPressEventListener.processAllKeyPressEvent(newEvent);
		} else if (action == GLFW.GLFW_RELEASE) {
			final KeyReleaseEvent newEvent = new KeyReleaseEvent(key, window, mods);
			KeyReleaseEventListener.processAllKeyReleaseEvent(newEvent);
		} else {
			final KeyHoldEvent newEvent = new KeyHoldEvent(key, window, mods);
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
		return this.key;
	}

	public abstract KeyEventType getKeyEventType();

	@Override
	public long getWindow() {
		return this.window;
	}

	@Override
	public void setWindow(final long window) {
		this.window = window;
	}

	@Override
	public String toString() {
		return "keycode: " + this.key.getKeyCode() + ", key: " + this.key + ", type: " + this.getKeyEventType();
	}
}
