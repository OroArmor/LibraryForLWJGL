package com.oroarmor.core.game.gui.animation;

public interface Easing {

	public static Easing LINEAR = (x) -> x;

	public static Easing EaseInSin = (x) -> (float) (1 - Math.cos(x * Math.PI / 2));
	public static Easing EaseOutSin = (x) -> (float) Math.sin(x * Math.PI / 2);
	public static Easing EaseInOutSin = (x) -> (float) (-(Math.cos(x * Math.PI) - 1) / 2);

	public float calculate(float percent);

}
