package com.oroarmor.core.glfw.event.key;

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
