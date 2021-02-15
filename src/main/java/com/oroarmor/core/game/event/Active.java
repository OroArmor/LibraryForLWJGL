package com.oroarmor.core.game.event;

/**
 * A class to see if something is active
 *
 * @author OroArmor
 */
public interface Active {
    /**
     * @return The active state of the class. true is active, false is not active
     */
    boolean isActive();

    /**
     * Sets the active state of the class
     *
     * @param active True if active, false if not active
     */
    void setActive(boolean active);
}
