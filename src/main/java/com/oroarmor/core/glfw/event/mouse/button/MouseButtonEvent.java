package com.oroarmor.core.glfw.event.mouse.button;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;
import com.oroarmor.core.glfw.event.mouse.button.press.MouseButtonPressEventListener;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseButtonReleaseEventListener;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;

public class MouseButtonEvent extends MouseEvent {
    private final MouseButton button;
    private final MouseButtonEventType type;
    private final float x, y;

    public MouseButtonEvent(MouseButton button, float x, float y, MouseButtonEventType type, long window, final GLFWEventMods mods) {
        super(window, MouseEventType.BUTTON, mods);
        this.button = button;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static void create(long window, int button, int action, GLFWEventMods mods) {
        if (action == MouseButtonEventType.PRESS.getAction()) {
            MousePressEvent event = new MousePressEvent(MouseButton.getButtonFromCode(button),
                    MouseStatus.getMouseX(), MouseStatus.getMouseY(), window, mods);
            MouseButtonPressEventListener.processAllMousePressEvent(event);
        }
        if (action == MouseButtonEventType.RELEASE.getAction()) {
            MouseReleaseEvent event = new MouseReleaseEvent(MouseButton.getButtonFromCode(button),
                    MouseStatus.getMouseX(), MouseStatus.getMouseY(), window, mods);
            MouseButtonReleaseEventListener.processAllMouseReleaseEvent(event);
        }
    }

    public MouseButton getButton() {
        return button;
    }

    public MouseButtonEventType getMouseButtonEventType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "button id: " + button.getMouseButtonID() + ", button: " + button + ", type: " + getMouseButtonEventType();
    }

}
