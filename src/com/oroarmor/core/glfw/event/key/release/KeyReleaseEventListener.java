package com.oroarmor.core.glfw.event.key.release;

import com.oroarmor.core.glfw.event.ActiveListener;

public interface KeyReleaseEventListener extends ActiveListener {
	public void processKeyReleasedEvent(KeyReleaseEvent event);
}
