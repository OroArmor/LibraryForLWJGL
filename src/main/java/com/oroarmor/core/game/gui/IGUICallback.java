package com.oroarmor.core.game.gui;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

public interface IGUICallback {
	public void onClick(MouseButton button);

	public void onHover();

	public void onHoverStop();

	public void onRelease(MouseButton button, boolean inbounds);

}
