package com.oroarmor.core.glfw.event.mouse.over.enter;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseEnterEventListener extends Active {
    ArrayList<MouseEnterEventListener> mouseEnterListener = new ArrayList<>();

    static void addMouseEnterListener(MouseEnterEventListener listener) {
        mouseEnterListener.add(listener);
    }

    static void processAllMouseEnterEvent(MouseEnterEvent event) {
        for (MouseEnterEventListener listener : mouseEnterListener) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processMouseEnterEvent(event);
        }
    }

    default void addToEnterListeners() {
        addMouseEnterListener(this);
    }

    void processMouseEnterEvent(MouseEnterEvent event);
}
