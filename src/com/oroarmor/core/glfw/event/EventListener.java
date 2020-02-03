package com.oroarmor.core.glfw.event;

public interface EventListener {

	public void processEvent(Event event);

	public boolean isActive();

	public void setActive(boolean active);
}
