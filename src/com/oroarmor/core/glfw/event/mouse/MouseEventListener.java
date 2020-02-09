package com.oroarmor.core.glfw.event.mouse;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.ActiveListener;
import com.oroarmor.core.glfw.event.mouse.MouseEvent.MouseEventType;

public interface MouseEventListener extends ActiveListener {
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

	public static int asdf = 10;
	public static ArrayList<MouseEventListener> mouseListeners = new ArrayList<MouseEventListener>();

	public static void addMouseListener(MouseEventListener listener) {
		mouseListeners.add(listener);
	}

	public static void processAllMouseEvent(MouseEvent event) {
		for (MouseEventListener listener : mouseListeners) {
			listener.processMouseEvent(event);
		}
	}
}
