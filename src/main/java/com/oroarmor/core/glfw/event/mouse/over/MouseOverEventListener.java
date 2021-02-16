package com.oroarmor.core.glfw.event.mouse.over;

import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEventListener;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEvent;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEventListener;

public interface MouseOverEventListener extends MouseLeaveEventListener, MouseEnterEventListener {
    default void addToOverListeners() {
        addToLeaveListeners();
        addToEnterListeners();
    }

    default void processMouseOverEvent(MouseOverEvent event) {
        if (event.getMouseOverEventType() == MouseOverEventType.ENTER) {
            processMouseEnterEvent((MouseEnterEvent) event);
        } else if (event.getMouseOverEventType() == MouseOverEventType.LEAVE) {
            processMouseLeaveEvent((MouseLeaveEvent) event);
        }
    }
}
