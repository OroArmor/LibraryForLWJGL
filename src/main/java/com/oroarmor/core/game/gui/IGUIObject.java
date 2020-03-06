package com.oroarmor.core.game.gui;

import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventListener;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEventListener;
import com.oroarmor.core.opengl.Renderer;

public interface IGUIObject extends MouseButtonEventListener, MousePositionEventListener {

	public float getX();

	public float getY();

	public IGUICallback getCallback();

	public void setCallback(IGUICallback callback);

	public void render(Renderer renderer);

}
