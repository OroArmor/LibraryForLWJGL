package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.Event;
import com.oroarmor.core.glfw.event.EventType;

public abstract class MouseEvent implements Event {

	public static enum MouseEventType {
		BUTTON, OVER, POSITION, SCROLL;
	}
	protected MouseEventType type;

	protected long window;

	public MouseEvent(long window, MouseEventType type) {
		this.window = window;
		this.type = type;
	}

	@Override
	public EventType getEventType() {
		return EventType.MOUSE;
	}

	public MouseEventType getMouseEventType() {
		return type;
	}

	@Override
	public long getWindow() {
		return window;
	}

	@Override
	public void setWindow(long window) {
		this.window = window;
	}
}
