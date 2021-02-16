package com.oroarmor.core.glfw.event.mouse.position;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface MousePositionEventListener extends Active {
    ArrayList<MousePositionEventListener> mousePositionListener = new ArrayList<>();

    static void addMousePositionListener(MousePositionEventListener listener) {
        mousePositionListener.add(listener);
    }

    static void processAllMousePositionEvent(MousePositionEvent event) {
        for (MousePositionEventListener listener : mousePositionListener) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processMousePositionEvent(event);
        }
    }

    default void addToPositionListeners() {
        addMousePositionListener(this);
    }

    void processMousePositionEvent(MousePositionEvent event);
}
