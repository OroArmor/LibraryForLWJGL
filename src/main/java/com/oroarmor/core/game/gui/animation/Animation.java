package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.IGUI;

public abstract class Animation<T extends IGUI<T>> implements IAnimation<T> {

	private long duration;
	protected Easing easing = Easing.LINEAR;

	protected Animation(long duration, Easing easing) {
		this.duration = duration;
		this.easing = easing;
	}

	protected Animation(long duration) {
		this.duration = duration;
	}

	@Override
	public long getDurationInMillis() {
		return duration;
	}

	public Easing getEasing() {
		return easing;
	}

	public void setEasing(Easing easing) {
		this.easing = easing;
	}

}
