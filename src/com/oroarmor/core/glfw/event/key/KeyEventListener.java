package com.oroarmor.core.glfw.event.key;

import com.oroarmor.core.glfw.event.EventListener;
import com.oroarmor.core.glfw.event.key.KeyEvent.KeyEventType;

public interface KeyEventListener extends EventListener {
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

	public void processKeyHeldEvent(KeyHoldEvent event);

	public void processKeyPressedEvent(KeyPressEvent event);

	public void processKeyReleasedEvent(KeyReleaseEvent event);
}
