package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.mouse.MouseEvent.MouseEventType;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEvent;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventListener;
import com.oroarmor.core.glfw.event.mouse.over.MouseOverEvent;
import com.oroarmor.core.glfw.event.mouse.over.MouseOverEventListener;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEventListener;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEventListener;

/**
 * A Listener that listens to all mouse events
 * <p>
 * Must call {@code addToMouseListeners} to initialize properly
 *
 * @author OroArmor
 */
public interface MouseEventListener
        extends MouseScrollEventListener, MouseButtonEventListener, MousePositionEventListener, MouseOverEventListener {
    /**
     * Called to initialize the listener
     */
    default void addToMouseListeners() {
        addToPositionListeners();
        addToButtonListeners();
        addToScrollListeners();
        addToOverListeners();
    }

    /**
     * Processes a mouse event
     *
     * @param event Event to process
     */
    default void processMouseEvent(MouseEvent event) {
        if (!isActive()) {
            return;
        }
        if (event.getMouseEventType() == MouseEventType.BUTTON) {
            processMouseButtonEvent((MouseButtonEvent) event);
        }
        if (event.getMouseEventType() == MouseEventType.POSITION) {
            assert event instanceof MousePositionEvent;
            processMousePositionEvent((MousePositionEvent) event);
        }
        if (event.getMouseEventType() == MouseEventType.OVER) {
            assert event instanceof MouseOverEvent;
            processMouseOverEvent((MouseOverEvent) event);
        }
        if (event.getMouseEventType() == MouseEventType.SCROLL) {
            assert event instanceof MouseScrollEvent;
            processMouseScrolledEvent((MouseScrollEvent) event);
        }
    }
}
