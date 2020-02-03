package com.oroarmor.core.glfw.event;

public interface Event {
	public EventType getEventType();

	public long getWindow();

	public void setWindow(long window);
}
