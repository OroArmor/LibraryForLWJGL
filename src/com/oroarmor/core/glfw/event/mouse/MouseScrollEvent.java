package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.EventListenerManager;

public class MouseScrollEvent extends MouseEvent {
	private float scrollX, scrollY;

	public MouseScrollEvent(long window, float scrollX, float scrollY) {
		setWindow(window);
		this.scrollX = scrollX;
		this.scrollY = scrollY;
	}

	public float getScrollX() {
		return scrollX;
	}

	public float getScrollY() {
		return scrollY;
	}

	public static void create(long window, float xoffset, float yoffset) {
		MouseScrollEvent event = new MouseScrollEvent(window, xoffset, yoffset);

		EventListenerManager.addMouseEvent(event);

	}
}
