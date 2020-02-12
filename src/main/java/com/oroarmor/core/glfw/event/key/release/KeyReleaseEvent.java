package com.oroarmor.core.glfw.event.key.release;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.key.KeyStatus;

public class KeyReleaseEvent extends KeyEvent {

	public KeyReleaseEvent(Key key, long window) {
		this.key = key;
		this.window = window;
		KeyStatus.setKeyUp(key);
	}

	@Override
	public KeyEventType getKeyEventType() {
		return KeyEventType.RELEASE;
	}

}
