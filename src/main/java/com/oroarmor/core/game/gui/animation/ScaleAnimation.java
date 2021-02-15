package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.object.IGUIObject;

public class ScaleAnimation<T extends IGUIObject<T>> extends Animation<T> {

    private final float scaleFactor;

    public ScaleAnimation(final long duration, final Easing easing, final float scaleFactor) {
        super(duration, easing);
        this.scaleFactor = scaleFactor;
    }

    public ScaleAnimation(final long duration, final float scaleFactor) {
        super(duration);
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void animate(final T object, final float percent) {
        object.setScale((1 + this.scaleFactor - object.getScale()) * easing.calculate(percent) + object.getScale());
    }

}
