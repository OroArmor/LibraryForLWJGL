package com.oroarmor.core;

public interface Destructable {
	default void destroy(boolean fromDestructor) {
		if (!fromDestructor) {
			Destructor.removeDestructable(this);
		}
		destroy();
	}

	public void destroy();
}
