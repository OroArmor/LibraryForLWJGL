package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.IGUI;

public interface IAnimation<T extends IGUI<T>> {
	public void animate(T object, float percent);

	public long getDurationInMillis();
}
