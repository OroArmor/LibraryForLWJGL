package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.Event;
import com.oroarmor.core.glfw.event.EventType;

public abstract class MouseEvent implements Event {

	protected long window;
	protected MouseEventType type;

	@Override
	public EventType getEventType() {
		return EventType.MOUSE;
	}

	@Override
	public long getWindow() {
		return window;
	}

	@Override
	public void setWindow(long window) {
		this.window = window;
	}

	public MouseEventType getMouseEventType() {
		return type;
	}

	public static enum MouseEventType {
		BUTTON, POSITION, SCROLL, ENTER;
	}
}
