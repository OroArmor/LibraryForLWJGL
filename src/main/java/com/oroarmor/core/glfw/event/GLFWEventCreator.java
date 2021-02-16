package com.oroarmor.core.glfw.event;

import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEvent;
import com.oroarmor.core.glfw.event.mouse.over.MouseOverEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Class that is in charge of creating new events based on GLFW actions
 *
 * @author OroArmor
 */
public class GLFWEventCreator {

    public static void initalizeWindow(long window) {
        glfwSetKeyCallback(window, (_window, key, scancode, action, mods) -> KeyEvent.create(key, action, _window, new GLFWEventMods(mods)));

        glfwSetScrollCallback(window, (_window, xoffset, yoffset) -> MouseScrollEvent.create(_window, (float) xoffset, (float) yoffset, new GLFWEventMods(0)));

        glfwSetCursorPosCallback(window, (_window, xpos, ypos) -> {
            MouseStatus.updateMousePositon((float) xpos, (float) ypos);
            MousePositionEvent.create(_window, GLFWEventMods.createFromCurrentStatus());
        });

        glfwSetMouseButtonCallback(window, (_window, button, action, mods) -> MouseButtonEvent.create(_window, button, action, new GLFWEventMods(mods)));

        glfwSetCursorEnterCallback(window, (_window, entered) -> MouseOverEvent.create(_window, entered ? 1 : 0, GLFWEventMods.createFromCurrentStatus()));
    }
}
