package com.oroarmor.core.glfw.event.mouse.button;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public enum MouseButtonEventType {
    PRESS(GLFW_PRESS),
    RELEASE(GLFW_RELEASE);

    private final int action;

    MouseButtonEventType(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
