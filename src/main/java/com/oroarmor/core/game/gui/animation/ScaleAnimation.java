package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.object.GUIObject;

public class ScaleAnimation<T extends GUIObject<T>> implements IAnimation<T> {

	private float scaleFactor;
	private long duration;

	public ScaleAnimation(long duration, float scaleFactor) {
		this.scaleFactor = scaleFactor;
		this.duration = duration;
	}

	@Override
	public long getDurationInMillis() {
		return duration;
	}

	@Override
	public void animate(T object, float percent) {
		object.setScale(((1 + scaleFactor) - object.getScale()) * percent + object.getScale());
	}

}
