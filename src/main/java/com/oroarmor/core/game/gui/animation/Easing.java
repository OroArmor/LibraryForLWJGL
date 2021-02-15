package com.oroarmor.core.game.gui.animation;

public interface Easing {

    Easing LINEAR = (x) -> x;

    Easing EaseInSin = (x) -> (float) (1 - Math.cos(x * Math.PI / 2));
    Easing EaseOutSin = (x) -> (float) Math.sin(x * Math.PI / 2);
    Easing EaseInOutSin = (x) -> (float) (-(Math.cos(x * Math.PI) - 1) / 2);

    float calculate(float percent);

}
