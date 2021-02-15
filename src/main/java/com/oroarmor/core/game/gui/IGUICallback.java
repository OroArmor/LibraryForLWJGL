package com.oroarmor.core.game.gui;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

public interface IGUICallback {
    void onClick(MouseButton button);

    void onHover();

    void onHoverStop();

    void onRelease(MouseButton button, boolean inbounds);

}
