package com.oroarmor.core.glfw.event;

public interface EventListener {

	public boolean isActive();

	public void processEvent(Event event);

	public void setActive(boolean active);
}
