package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.EventListener;
import com.oroarmor.core.glfw.event.mouse.MouseEvent.MouseEventType;

public interface MouseEventListener extends EventListener {
	public default void processMouseEvent(MouseEvent event) {
		if (event.getMouseEventType() == MouseEventType.BUTTON) {
		}
		if (event.getMouseEventType() == MouseEventType.POSITION) {
		}
		if (event.getMouseEventType() == MouseEventType.ENTER) {
		}
		if (event.getMouseEventType() == MouseEventType.SCROLL) {
		}
	}

	public void processMouseButtonEvent(MouseButtonEvent event);

	public void processMousePositionEvent(MousePositionEvent event);

	public void processMouseEnterEvent(MouseEnterEvent event);

	public void processMouseScrollEvent(MouseScrollEvent event);
}
