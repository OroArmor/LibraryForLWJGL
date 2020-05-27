package com.oroarmor.core.game.event;

/**
 * A class to see if something is active
 *
 * @author OroArmor
 *
 */
public interface Active {
	/**
	 *
	 * @return The active state of the class. true is active, false is not active
	 */
	public boolean isActive();

	/**
	 * Sets the active state of the class
	 *
	 * @param active True if active, false if not active
	 */
	public void setActive(boolean active);
}
