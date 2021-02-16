package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.object.IGUIObject;

public class ScaleAnimation<T extends IGUIObject<T>> extends Animation<T> {

    private final float scaleFactor;

    public ScaleAnimation(long duration, Easing easing, float scaleFactor) {
        super(duration, easing);
        this.scaleFactor = scaleFactor;
    }

    public ScaleAnimation(long duration, float scaleFactor) {
        super(duration);
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void animate(T object, float percent) {
        object.setScale((1 + this.scaleFactor - object.getScale()) * easing.calculate(percent) + object.getScale());
    }
}
