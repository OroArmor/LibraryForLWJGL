package com.oroarmor.core.glfw.event.mouse.scroll;

import com.oroarmor.core.glfw.event.mouse.MouseEvent;

public class MouseScrollEvent extends MouseEvent {
	public static void create(long window, float xoffset, float yoffset) {
		MouseScrollEvent event = new MouseScrollEvent(window, xoffset, yoffset);

		MouseScrollEventListener.processAllMouseScrollEvent(event);
	}

	private float scrollX, scrollY;

	public MouseScrollEvent(long window, float scrollX, float scrollY) {
		super(window, MouseEventType.SCROLL);
		this.scrollX = scrollX;
		this.scrollY = scrollY;
	}

	public float getScrollX() {
		return scrollX;
	}

	public float getScrollY() {
		return scrollY;
	}

	@Override
	public String toString() {
		return "scroll x: " + scrollX + ", scroll y: " + scrollY;
	}
}
