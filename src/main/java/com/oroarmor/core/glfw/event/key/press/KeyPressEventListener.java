package com.oroarmor.core.glfw.event.key.press;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface KeyPressEventListener extends ActiveListener {
	public void processKeyPressedEvent(KeyPressEvent event);
}
