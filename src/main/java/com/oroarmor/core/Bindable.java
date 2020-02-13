package com.oroarmor.core;

/**
 * This class is for objects that can be bound/unbound
 * 
 * @author OroArmor
 *
 */
public interface Bindable {
	/**
	 * Bind the object
	 */
	public void bind();

	/**
	 * Unbind the object
	 */
	public void unbind();
}
