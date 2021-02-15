package com.oroarmor.core.glfw.event.mouse.button;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton {
    LEFT_BUTTON(GLFW_MOUSE_BUTTON_LEFT), MIDDLE_BUTTON(GLFW_MOUSE_BUTTON_MIDDLE), NOT_FOUND(-1),
    RIGHT_BUTTON(GLFW_MOUSE_BUTTON_RIGHT);

    private final int mouseButtonID;

    MouseButton(final int mouseButtonID) {
        this.mouseButtonID = mouseButtonID;
    }

    public static MouseButton getButtonFromCode(final int code) {
        for (final MouseButton button : values()) {
            if (button.getMouseButtonID() == code) {
                return button;
            }
        }

        return NOT_FOUND;
    }

    public int getMouseButtonID() {
        return mouseButtonID;
    }

}
