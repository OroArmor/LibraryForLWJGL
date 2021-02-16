package com.oroarmor.core.glfw.event.mouse.scroll;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

/**
 * A Listener for {@link MouseScrollEvent}s
 * <p>
 * Must call {@code addToScrollListeners} to initialize
 *
 * @author OroArmor
 */
public interface MouseScrollEventListener extends Active {
    /**
     * The list of {@link MouseScrollEventListener}
     */
    ArrayList<MouseScrollEventListener> mouseScrollListeners = new ArrayList<>();

    /**
     * Adds a new {@link MouseScrollEventListener} to the list
     *
     * @param listener The scroll event listener
     */
    static void addMouseScrollListener(MouseScrollEventListener listener) {
        mouseScrollListeners.add(listener);
    }

    /**
     * Sends a {@link MouseScrollEvent} to all listeners
     *
     * @param event {@link MouseScrollEvent} to process
     */
    static void processAllMouseScrollEvent(MouseScrollEvent event) {
        for (MouseScrollEventListener listener : mouseScrollListeners) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processMouseScrolledEvent(event);
        }
    }

    /**
     * Initializes the {@link MouseScrollEventListener}
     */
    default void addToScrollListeners() {
        addMouseScrollListener(this);
    }

    /**
     * Process a {@link MouseScrollEvent}
     *
     * @param event {@link MouseScrollEvent} to process
     */
    void processMouseScrolledEvent(MouseScrollEvent event);
}
