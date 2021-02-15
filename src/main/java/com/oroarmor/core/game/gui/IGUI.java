package com.oroarmor.core.game.gui;

import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.opengl.Renderer;

public interface IGUI<T extends IGUI<T>> {
    float getX();

    float getY();

    boolean hasParent();

    void render(Renderer renderer);

    void setHasParent(boolean hasParent);

    void triggerAnimation(IAnimation<T> animation);
}
