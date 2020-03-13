package com.oroarmor.core.glfw.event;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

public class EventMods {

	public static enum ModIDs {
		SHIFT(GLFW_MOD_SHIFT), CONTROL(GLFW_MOD_CONTROL), ALT(GLFW_MOD_ALT), SUPER(GLFW_MOD_SUPER),
		CAPS_LOCK(GLFW_MOD_CAPS_LOCK), NUM_LOCK(GLFW_MOD_NUM_LOCK);

		public int modID;

		private ModIDs(int modID) {
			this.modID = modID;
		}
		
		public static ModIDs[] getIDsFromInt(int mods) {
			ArrayList<ModIDs> idsArr = new ArrayList<ModIDs>();
			
			for(ModIDs id : values()) {
				if((id.modID & mods) == id.modID) {
					idsArr.add(id);
					
				}
			}
			
			ModIDs[] ids = new ModIDs[idsArr.size()];
			return idsArr.toArray(ids);
		}
	}

	private int mod;

	public EventMods(int mod) {
		this.mod = mod;
	}

	public int getMod() {
		return mod;
	}

}
