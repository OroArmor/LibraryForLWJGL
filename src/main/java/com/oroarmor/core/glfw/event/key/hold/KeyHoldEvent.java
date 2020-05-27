package com.oroarmor.core.glfw.event.key.hold;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent;

public class KeyHoldEvent extends KeyEvent {

	private GLFWEventMods mods;

	public KeyHoldEvent(final Key key, final long window, final GLFWEventMods mods) {
		this.key = key;
		this.mods = mods;
		this.window = window;
	}

	@Override
	public GLFWEventMods getEventMods() {
		return this.mods;
	}

	@Override
	public KeyEventType getKeyEventType() {
		return KeyEventType.HOLD;
	}

	@Override
	public void setEventMods(final GLFWEventMods newMods) {
		this.mods = newMods;
	}

}
