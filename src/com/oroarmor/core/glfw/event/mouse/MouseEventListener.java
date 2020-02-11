package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.mouse.MouseEvent.MouseEventType;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEvent;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventListener;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEventListener;

public interface MouseEventListener extends MouseScrollEventListener, MouseButtonEventListener {
	public default void processMouseEvent(MouseEvent event) {
		if (event.getMouseEventType() == MouseEventType.BUTTON) {
			processMouseButtonEvent((MouseButtonEvent) event);
		}
		if (event.getMouseEventType() == MouseEventType.POSITION) {
		}
		if (event.getMouseEventType() == MouseEventType.ENTER) {
		}
		if (event.getMouseEventType() == MouseEventType.SCROLL) {
			processMouseScrolledEvent((MouseScrollEvent) event);
		}
	}

	public default void addToMouseListeners() {
		addToScrollListeners();
		addToButtonListeners();
	}

	public void processMousePositionEvent(MousePositionEvent event);

	public void processMouseEnterEvent(MouseEnterEvent event);

}
