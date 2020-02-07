package com.oroarmor.core;

public interface Destructable {
	public void destroy();

	default void destroy(boolean fromDestructor) {
		if (!fromDestructor) {
			Destructor.removeDestructable(this);
		}
		destroy();
	}
}
