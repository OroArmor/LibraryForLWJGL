package com.oroarmor.core.game.gui;

import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventListener;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEventListener;

public interface IGUIObject extends MouseButtonEventListener, MousePositionEventListener, IGUI {

	public IGUICallback getCallback();

	public void setCallback(IGUICallback callback);

}
