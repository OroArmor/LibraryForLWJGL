package com.oroarmor.core.game.gui;

import java.util.List;

import com.oroarmor.core.opengl.Renderer;

public interface IGUIGroup extends IGUI {
	public int numObjects();

	public List<IGUI> getChildren();
	
	public void addChildren(IGUI... children);

	public void renderChildren(Renderer renderer);

	public void hideAll();

	public void showAll();

	public boolean isVisable();
}
