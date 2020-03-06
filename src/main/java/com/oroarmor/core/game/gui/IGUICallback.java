package com.oroarmor.core.game.gui;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

public interface IGUICallback {
	public void onClick(MouseButton button);

	public void onRelease(MouseButton button);

	public void whileHeld(MouseButton button);

	public void onHover();

	public void whileHovered();

	public void onHoverStop();

}
