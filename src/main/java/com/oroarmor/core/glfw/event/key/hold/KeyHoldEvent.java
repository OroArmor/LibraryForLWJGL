package com.oroarmor.core.glfw.event.key.hold;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent;

public class KeyHoldEvent extends KeyEvent {

	public KeyHoldEvent(Key key, long window) {
		this.key = key;
		this.window = window;
	}

	@Override
	public KeyEventType getKeyEventType() {
		return KeyEventType.HOLD;
	}

}
