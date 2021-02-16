package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.IGUI;

public abstract class Animation<T extends IGUI<T>> implements IAnimation<T> {
    private final long duration;
    protected Easing easing = Easing.LINEAR;

    protected Animation(final long duration) {
        this.duration = duration;
    }

    protected Animation(final long duration, final Easing easing) {
        this.duration = duration;
        this.easing = easing;
    }

    @Override
    public long getDurationInMillis() {
        return this.duration;
    }

    public Easing getEasing() {
        return this.easing;
    }

    public void setEasing(final Easing easing) {
        this.easing = easing;
    }
}
