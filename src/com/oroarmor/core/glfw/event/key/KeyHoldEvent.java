package com.oroarmor.core.glfw.event.key;

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
