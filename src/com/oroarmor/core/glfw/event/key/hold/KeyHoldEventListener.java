package com.oroarmor.core.glfw.event.key.hold;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface KeyHoldEventListener extends ActiveListener {
	public void processKeyHeldEvent(KeyHoldEvent event);
}
