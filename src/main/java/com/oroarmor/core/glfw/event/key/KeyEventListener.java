package com.oroarmor.core.glfw.event.key;

import com.oroarmor.core.glfw.event.key.KeyEvent.KeyEventType;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEventListener;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEventListener;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEventListener;

public interface KeyEventListener extends KeyPressEventListener, KeyHoldEventListener, KeyReleaseEventListener {
	public default void processKeyEvent(KeyEvent event) {
		if (event.getKeyEventType() == KeyEventType.PRESS) {
			processKeyPressedEvent((KeyPressEvent) event);
		}
		if (event.getKeyEventType() == KeyEventType.HOLD) {
			processKeyHeldEvent((KeyHoldEvent) event);
		}
		if (event.getKeyEventType() == KeyEventType.RELEASE) {
			processKeyReleasedEvent((KeyReleaseEvent) event);
		}
	}

	public default void addToKeyListeners() {
		this.addToKeyHoldListeners();
		this.addToKeyReleaseListeners();
		this.addToKeyPressListeners();
	}
}
