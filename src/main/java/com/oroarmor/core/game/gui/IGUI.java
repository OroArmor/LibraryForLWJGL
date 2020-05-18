package com.oroarmor.core.game.gui;

import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.opengl.Renderer;

public interface IGUI<T extends IGUI<T>> {
	public float getX();

	public float getY();

	public void render(Renderer renderer);

	public boolean hasParent();

	public void setHasParent(boolean hasParent);

	public void triggerAnimation(IAnimation<T> animation);
}
