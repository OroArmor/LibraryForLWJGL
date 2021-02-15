package com.oroarmor.core.glfw.event;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyStatus;

import static org.lwjgl.glfw.GLFW.*;

public class GLFWEventMods {

    private final int mod;
    private final GLFWModIDs[] mods;

    public GLFWEventMods(final int mod) {
        this.mod = mod;
        mods = GLFWModIDs.getIDsFromInt(mod);
    }

    public static GLFWEventMods createFromCurrentStatus() {
        int mod = 0;

        if (KeyStatus.isKeyDown(Key.LEFT_SHIFT) || KeyStatus.isKeyDown(Key.RIGHT_SHIFT)) {
            mod += GLFWModIDs.SHIFT.modID;
        }

        if (KeyStatus.isKeyDown(Key.LEFT_CONTROL)) {
            mod += GLFWModIDs.CONTROL.modID;
        }

        return new GLFWEventMods(mod);
    }

    public int getMod() {
        return mod;
    }

    public boolean isShift() {
        for (final GLFWModIDs id : mods) {
            if (id == GLFWModIDs.SHIFT) {
                return true;
            }
        }
        return false;
    }

    public enum GLFWModIDs {
        SHIFT(GLFW_MOD_SHIFT), CONTROL(GLFW_MOD_CONTROL), ALT(GLFW_MOD_ALT), SUPER(GLFW_MOD_SUPER),
        CAPS_LOCK(GLFW_MOD_CAPS_LOCK), NUM_LOCK(GLFW_MOD_NUM_LOCK);

        public int modID;

        GLFWModIDs(final int modID) {
            this.modID = modID;
        }

        public static GLFWModIDs[] getIDsFromInt(final int mods) {
            final ArrayList<GLFWModIDs> idsArr = new ArrayList<>();

            for (final GLFWModIDs id : values()) {
                if ((id.modID & mods) == id.modID) {
                    idsArr.add(id);

                }
            }

            final GLFWModIDs[] ids = new GLFWModIDs[idsArr.size()];
            return idsArr.toArray(ids);
        }
    }

}
