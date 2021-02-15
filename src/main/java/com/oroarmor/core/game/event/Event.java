package com.oroarmor.core.game.event;

/**
 * A interface all events extend
 *
 * @author OroArmor
 */
public interface Event {
    /**
     * @return The type of event, one of {@link EventType}
     */
    EventType getEventType();

    /**
     * Gets the window the event occured on
     *
     * @return The window handle
     */
    long getWindow();

    /**
     * Sets the window
     *
     * @param window New window handle
     */
    void setWindow(long window);
}
