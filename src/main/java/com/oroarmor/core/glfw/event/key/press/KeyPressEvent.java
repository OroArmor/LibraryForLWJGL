package com.oroarmor.core.glfw.event.key.press;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.key.KeyStatus;

public class KeyPressEvent extends KeyEvent {

	public KeyPressEvent(Key key, long window) {
		this.key = key;
		this.window = window;
		KeyStatus.setKeyDown(key);
	}

	@Override
	public KeyEventType getKeyEventType() {
		return KeyEventType.PRESS;
	}

}
