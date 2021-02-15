package com.oroarmor.core.glfw.event.mouse.over;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEventListener;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEvent;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEventListener;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;

public class MouseOverEvent extends MouseEvent {

    MouseOverEventType type;

    public MouseOverEvent(final long window, final MouseOverEventType type, final GLFWEventMods mods) {
        super(window, MouseEventType.OVER, mods);

        this.type = type;
    }

    public static void create(final long window, final int action, final GLFWEventMods mods) {
        if (action == GLFW_TRUE) {
            MouseEnterEventListener.processAllMouseEnterEvent(new MouseEnterEvent(window, mods));
        } else if (action == GLFW_FALSE) {
            MouseLeaveEventListener.processAllMouseLeaveEvent(new MouseLeaveEvent(window, mods));
        }
    }

    public MouseOverEventType getMouseOverEventType() {
        return type;
    }

    @Override
    public String toString() {
        return "Over event type: " + type;
    }
}
