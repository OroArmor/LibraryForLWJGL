package com.oroarmor.core.glfw.event.mouse.button.release;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseButtonReleaseEventListener extends Active {
    ArrayList<MouseButtonReleaseEventListener> mouseReleaseListeners = new ArrayList<>();

    static void addMouseReleaseListener(final MouseButtonReleaseEventListener listener) {
        mouseReleaseListeners.add(listener);
    }

    static void processAllMouseReleaseEvent(final MouseReleaseEvent event) {
        for (final MouseButtonReleaseEventListener listener : mouseReleaseListeners) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processMouseReleasedEvent(event);
        }
    }

    default void addToReleaseListeners() {
        addMouseReleaseListener(this);
    }

    void processMouseReleasedEvent(MouseReleaseEvent event);
}
