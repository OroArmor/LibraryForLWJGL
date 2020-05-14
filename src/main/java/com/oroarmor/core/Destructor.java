package com.oroarmor.core;

import java.util.ArrayList;

/**
 * This class is in charge of keeping track of all {@link Destructable} classes
 * and destroying them
 * 
 * @author OroArmor
 *
 */
public class Destructor {

	/**
	 * This is the list of all the {@link Destructable} classes
	 */
	private static ArrayList<Destructable> destructables = new ArrayList<Destructable>();

	/**
	 * Add a {@link Destructable} to the list
	 * 
	 * @param destructable {@link Destructable} to add
	 */
	public static void addDestructable(Destructable destructable) {
		destructables.add(destructable);
	}

	/**
	 * Destroys all {@link Destructable} classes
	 */
	public static void destroyAll() {
		for (Destructable destructable : destructables) {
			destructable.destroy(true);
		}
	}

	/**
	 * Removes a {@link Destructable} from the list
	 * 
	 * @param destructable {@link Destructable} to remove
	 */
	public static void removeDestructable(Destructable destructable) {
		destructables.remove(destructable);
	}
}
