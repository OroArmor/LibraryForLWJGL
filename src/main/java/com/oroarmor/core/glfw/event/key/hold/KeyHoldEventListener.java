package com.oroarmor.core.glfw.event.key.hold;

import java.util.ArrayList;

import com.oroarmor.core.game.event.Active;

public interface KeyHoldEventListener extends Active {
    ArrayList<KeyHoldEventListener> keyPressListeners = new ArrayList<>();

    static void addKeyHoldListener(final KeyHoldEventListener listener) {
        keyPressListeners.add(listener);
    }

    static void processAllKeyPressEvent(final KeyHoldEvent event) {
        for (final KeyHoldEventListener listener : keyPressListeners) {
            if (!listener.isActive()) {
                continue;
            }
            listener.processKeyHeldEvent(event);
        }
    }

    default void addToKeyHoldListeners() {
        addKeyHoldListener(this);
    }

    void processKeyHeldEvent(KeyHoldEvent event);
}
