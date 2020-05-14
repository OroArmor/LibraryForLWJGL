package com.oroarmor.core.glfw.event;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyStatus;

public class GLFWEventMods {

	public static enum GLFWModIDs {
		SHIFT(GLFW_MOD_SHIFT), CONTROL(GLFW_MOD_CONTROL), ALT(GLFW_MOD_ALT), SUPER(GLFW_MOD_SUPER),
		CAPS_LOCK(GLFW_MOD_CAPS_LOCK), NUM_LOCK(GLFW_MOD_NUM_LOCK);

		public int modID;

		private GLFWModIDs(int modID) {
			this.modID = modID;
		}

		public static GLFWModIDs[] getIDsFromInt(int mods) {
			ArrayList<GLFWModIDs> idsArr = new ArrayList<GLFWModIDs>();

			for (GLFWModIDs id : values()) {
				if ((id.modID & mods) == id.modID) {
					idsArr.add(id);

				}
			}

			GLFWModIDs[] ids = new GLFWModIDs[idsArr.size()];
			return idsArr.toArray(ids);
		}
	}

	private int mod;

	private GLFWModIDs[] mods;

	public GLFWEventMods(int mod) {
		this.mod = mod;
		this.mods = GLFWModIDs.getIDsFromInt(mod);
	}

	public int getMod() {
		return mod;
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

	public boolean isShift() {
		for (GLFWModIDs id : this.mods) {
			if (id == GLFWModIDs.SHIFT) {
				return true;
			}
		}
		return false;
	}

}
