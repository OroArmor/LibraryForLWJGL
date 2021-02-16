package com.oroarmor.core.game.gui.group;

import java.util.List;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.opengl.Renderer;

public interface IGUIGroup extends IGUI<IGUIGroup> {
    void addChildren(IGUI<?>... children);

    List<IGUI<?>> getChildren();

    void hideAll();

    boolean isVisible();

    void makeVisible(boolean visable);

    int numObjects();

    void renderChildren(Renderer renderer);

    void showAll();
}
