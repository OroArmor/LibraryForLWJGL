package com.oroarmor.core.glfw.event.mouse.button.release;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.mouse.button.MouseButton;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEvent;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventType;

public class MouseReleaseEvent extends MouseButtonEvent {
    public MouseReleaseEvent(MouseButton button, float x, float y, long window, GLFWEventMods mods) {
        super(button, x, y, MouseButtonEventType.RELEASE, window, mods);
    }
}
