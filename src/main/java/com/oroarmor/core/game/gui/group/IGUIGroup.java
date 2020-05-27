package com.oroarmor.core.game.gui.group;

import java.util.List;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.opengl.Renderer;

public interface IGUIGroup extends IGUI<IGUIGroup> {
	public void addChildren(IGUI<?>... children);

	public List<IGUI<?>> getChildren();

	public void hideAll();

	public boolean isVisable();

	public void makeVisable(boolean visable);

	public int numObjects();

	public void renderChildren(Renderer renderer);

	public void showAll();
}
