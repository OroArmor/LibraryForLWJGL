package com.oroarmor.core.game.gui;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

public interface IGUICallback {
	public void onClick(MouseButton button);

	public void onRelease(MouseButton button, boolean inbounds);

	public void onHover();

	public void onHoverStop();

}
