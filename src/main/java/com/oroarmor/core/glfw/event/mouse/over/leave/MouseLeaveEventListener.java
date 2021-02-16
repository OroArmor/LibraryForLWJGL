package com.oroarmor.core.glfw.event.mouse.over.leave;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseLeaveEventListener extends Active {
    ArrayList<MouseLeaveEventListener> mouseLeaveListener = new ArrayList<>();

    static void addMouseLeaveListener(MouseLeaveEventListener listener) {
        mouseLeaveListener.add(listener);
    }

    static void processAllMouseLeaveEvent(MouseLeaveEvent event) {
        for (MouseLeaveEventListener listener : mouseLeaveListener) {
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
