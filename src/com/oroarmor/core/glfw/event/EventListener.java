package com.oroarmor.core.glfw.event;

import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.mouse.MouseEventListener;

public interface EventListener extends KeyEventListener, MouseEventListener {

	public void processEvent(Event event);

}
