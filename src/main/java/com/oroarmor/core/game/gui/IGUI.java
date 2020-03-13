package com.oroarmor.core.game.gui;

import com.oroarmor.core.opengl.Renderer;

public interface IGUI {
	public float getX();

	public float getY();

	public void render(Renderer renderer);
	
	public boolean hasParent();
	
	public void setHasParent(boolean hasParent);
}
