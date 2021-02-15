package com.oroarmor.core.glfw.event.key.press;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyPressEventListener extends Active {
    ArrayList<KeyPressEventListener> keyPressListeners = new ArrayList<>();

    static void addKeyPressListener(final KeyPressEventListener listener) {
        keyPressListeners.add(listener);
    }

    static void processAllKeyPressEvent(final KeyPressEvent event) {
        for (final KeyPressEventListener listener : keyPressListeners) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processKeyPressedEvent(event);
        }
    }

    default void addToKeyPressListeners() {
        addKeyPressListener(this);
    }

    void processKeyPressedEvent(KeyPressEvent event);
}
