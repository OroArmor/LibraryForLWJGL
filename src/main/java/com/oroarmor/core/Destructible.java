package com.oroarmor.core;

/**
 * This interface is for all classes that should have a cleanup (destroy) method
 * before the program ends
 *
 * @author OroArmor
 */
public interface Destructible {
    /**
     * A cleanup method that is run to clear memory/data of an object
     */
    void destroy();

    /**
     * This checks if the {@link Destructor} or the user destroyed the object
     *
     * @param fromDestructor If true, the {@link Destructor} destroyed the object,
     *                       if false, the user destroyed the object
     */
    default void destroy(final boolean fromDestructor) {
        if (!fromDestructor) {
            Destructor.removeDestructable(this);
        }
        destroy();
    }
}
