package com.oroarmor.core.glfw.event;

import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.mouse.MouseEventListener;

public interface EventListener extends KeyEventListener, MouseEventListener {

	public void processEvent(Event event);

	public default void addToListeners() {
		this.addToKeyListeners();
		this.addToMouseListeners();
	}

}
