package com.oroarmor.core.glfw.event.key.release;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyReleaseEventListener extends Active {
    ArrayList<KeyReleaseEventListener> keyReleaseListeners = new ArrayList<>();

    static void addKeyReleaseListener(KeyReleaseEventListener listener) {
        keyReleaseListeners.add(listener);
    }

    static void processAllKeyReleaseEvent(KeyReleaseEvent event) {
        for (KeyReleaseEventListener listener : keyReleaseListeners) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processKeyReleasedEvent(event);
        }
    }

    default void addToKeyReleaseListeners() {
        addKeyReleaseListener(this);
    }

    void processKeyReleasedEvent(KeyReleaseEvent event);
}
