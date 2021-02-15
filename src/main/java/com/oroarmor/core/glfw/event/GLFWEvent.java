package com.oroarmor.core.glfw.event;

import com.oroarmor.core.game.event.Event;

public interface GLFWEvent extends Event {
    /**
     * @return The GLFW Event Mods from the GLFW Event
     */
    GLFWEventMods getEventMods();

    /**
     * Reset the Event Mods
     *
     * @param newMods New Event Mods
     */
    void setEventMods(GLFWEventMods newMods);
}
