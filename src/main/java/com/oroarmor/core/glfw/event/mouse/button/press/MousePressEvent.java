package com.oroarmor.core.glfw.event.mouse.button.press;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.mouse.button.MouseButton;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEvent;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventType;

public class MousePressEvent extends MouseButtonEvent {
    public MousePressEvent(MouseButton button, float x, float y, long window, GLFWEventMods mods) {
        super(button, x, y, MouseButtonEventType.PRESS, window, mods);
    }
}
