package com.oroarmor.core.game.gui.animation;

public interface Easing {

    Easing LINEAR = (x) -> x;

    Easing EASE_IN_SIN = (x) -> (float) (1 - Math.cos(x * Math.PI / 2));
    Easing EASE_OUT_SIN = (x) -> (float) Math.sin(x * Math.PI / 2);
    Easing EASE_IN_OUT_SIN = (x) -> (float) (-(Math.cos(x * Math.PI) - 1) / 2);

    float calculate(float percent);
}
