package com.oroarmor.core;

import java.util.ArrayList;

/**
 * This class is in charge of keeping track of all {@link Destructible} classes
 * and destroying them
 *
 * @author OroArmor
 */
public class Destructor {
    /**
     * This is the list of all the {@link Destructible} classes
     */
    private static final ArrayList<Destructible> destructibles = new ArrayList<>();

    /**
     * Add a {@link Destructible} to the list
     *
     * @param destructible {@link Destructible} to add
     */
    public static void addDestructable(Destructible destructible) {
        destructibles.add(destructible);
    }

    /**
     * Destroys all {@link Destructible} classes
     */
    public static void destroyAll() {
        for (Destructible destructible : destructibles) {
            destructible.destroy(true);
        }
    }

    /**
     * Removes a {@link Destructible} from the list
     *
     * @param destructible {@link Destructible} to remove
     */
    public static void removeDestructible(Destructible destructible) {
        destructibles.remove(destructible);
    }
}
