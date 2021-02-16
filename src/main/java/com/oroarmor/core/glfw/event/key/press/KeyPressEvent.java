package com.oroarmor.core.glfw.event.key.press;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent;
import com.oroarmor.core.glfw.event.key.KeyStatus;

public class KeyPressEvent extends KeyEvent {

    private GLFWEventMods mods;

    public KeyPressEvent(Key key, long window, GLFWEventMods mods) {
        this.key = key;
        this.window = window;
        this.mods = mods;
        KeyStatus.setKeyDown(key);
    }

    @Override
    public GLFWEventMods getEventMods() {
        return mods;
    }

    @Override
    public void setEventMods(GLFWEventMods newMods) {
        mods = newMods;
    }

    @Override
    public KeyEventType getKeyEventType() {
        return KeyEventType.PRESS;
    }

}
