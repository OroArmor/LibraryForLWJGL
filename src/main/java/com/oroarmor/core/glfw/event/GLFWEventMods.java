package com.oroarmor.core.glfw.event;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

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
			
			for(GLFWModIDs id : values()) {
				if((id.modID & mods) == id.modID) {
					idsArr.add(id);
					
				}
			}
			
			GLFWModIDs[] ids = new GLFWModIDs[idsArr.size()];
			return idsArr.toArray(ids);
		}
	}

	private int mod;

	public GLFWEventMods(int mod) {
		this.mod = mod;
	}

	public int getMod() {
		return mod;
	}

}
