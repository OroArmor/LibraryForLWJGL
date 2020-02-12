package com.oroarmor.core.glfw.event.mouse.button;

import com.oroarmor.core.glfw.event.mouse.button.press.MouseButtonPressEventListener;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseButtonReleaseEventListener;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;

public interface MouseButtonEventListener extends MouseButtonPressEventListener, MouseButtonReleaseEventListener {
	public default void addToButtonListeners() {
		addToPressListeners();
		addToReleaseListeners();
	}

	public default void processMouseButtonEvent(MouseButtonEvent event) {
		System.out.println(event);
		if (event.getMouseButtonEventType() == MouseButtonEventType.PRESS) {
			processMousePressEvent((MousePressEvent) event);
		} else if (event.getMouseButtonEventType() == MouseButtonEventType.RELEASE) {
			processMouseReleasedEvent((MouseReleaseEvent) event);
		}
	}
}
