package com.oroarmor.core.glfw.event.key;

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
