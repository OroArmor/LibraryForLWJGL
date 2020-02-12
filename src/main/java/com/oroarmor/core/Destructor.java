package com.oroarmor.core;

import java.util.ArrayList;

public class Destructor {
	public static ArrayList<Destructable> destructables = new ArrayList<Destructable>();

	public static void addDestructable(Destructable destructable) {
		destructables.add(destructable);
	}

	public static void destroyAll() {
		for (Destructable destructable : destructables) {
			destructable.destroy(true);
		}
	}

	public static void removeDestructable(Destructable destructable) {
		destructables.remove(destructable);
	}
}
