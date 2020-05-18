package com.oroarmor.core.game.gui.group;

import java.util.List;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.opengl.Renderer;

public interface IGUIGroup<T extends IGUIGroup<T>> extends IGUI<IGUIGroup<T>> {
	public int numObjects();

	public List<IGUI<?>> getChildren();

	public void addChildren(IGUI<?>... children);

	public void renderChildren(Renderer renderer);

	public void hideAll();

	public void showAll();

	public boolean isVisable();

	public void makeVisable(boolean visable);
}
