package com.oroarmor.core.glfw.event.key.hold;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent;

public class KeyHoldEvent extends KeyEvent {

	private GLFWEventMods mods;

	public KeyHoldEvent(Key key, long window, GLFWEventMods mods) {
		this.key = key;
		this.mods = mods;
		this.window = window;
	}

	@Override
	public KeyEventType getKeyEventType() {
		return KeyEventType.HOLD;
	}

	@Override
	public GLFWEventMods getEventMods() {
		return mods;
	}

	@Override
	public void setEventMods(GLFWEventMods newMods) {
		this.mods = newMods;
	}

}
