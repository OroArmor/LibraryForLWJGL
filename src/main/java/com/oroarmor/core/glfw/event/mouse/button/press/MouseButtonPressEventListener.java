package com.oroarmor.core.glfw.event.mouse.button.press;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MouseButtonPressEventListener extends Active {
    ArrayList<MouseButtonPressEventListener> mousePressListeners = new ArrayList<>();

    static void addMousePressListener(final MouseButtonPressEventListener listener) {
        mousePressListeners.add(listener);
    }

    static void processAllMousePressEvent(final MousePressEvent event) {
        for (final MouseButtonPressEventListener listener : mousePressListeners) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processMousePressEvent(event);
        }
    }

    default void addToPressListeners() {
        addMousePressListener(this);
    }

    void processMousePressEvent(MousePressEvent event);
}
