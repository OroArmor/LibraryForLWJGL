package com.oroarmor.core.glfw.event.mouse.over.leave;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseLeaveEventListener extends Active {
    ArrayList<MouseLeaveEventListener> mouseLeaveListener = new ArrayList<>();

    static void addMouseLeaveListener(final MouseLeaveEventListener listener) {
        mouseLeaveListener.add(listener);
    }

    static void processAllMouseLeaveEvent(final MouseLeaveEvent event) {
        for (final MouseLeaveEventListener listener : mouseLeaveListener) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processMouseLeaveEvent(event);
        }
    }

    default void addToLeaveListeners() {
        addMouseLeaveListener(this);
    }

    void processMouseLeaveEvent(MouseLeaveEvent event);
}
