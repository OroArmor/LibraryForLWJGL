package com.oroarmor.core.glfw.event.mouse.button;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton {
    NOT_FOUND(-1),
    LEFT_BUTTON(GLFW_MOUSE_BUTTON_LEFT),
    MIDDLE_BUTTON(GLFW_MOUSE_BUTTON_MIDDLE),
    RIGHT_BUTTON(GLFW_MOUSE_BUTTON_RIGHT);

    private final int mouseButtonID;

    MouseButton(int mouseButtonID) {
        this.mouseButtonID = mouseButtonID;
    }

    public static MouseButton getButtonFromCode(int code) {
        for (MouseButton button : values()) {
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
