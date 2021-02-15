package com.oroarmor.core.game.gui.object;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.game.gui.IGUICallback;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventListener;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEventListener;
import org.joml.Matrix4f;

public interface IGUIObject<T extends IGUIObject<T>>
        extends MouseButtonEventListener, MousePositionEventListener, IGUI<T> {

    Matrix4f getAnimationMatrix();

    void setAnimationMatrix(Matrix4f animationMatrix);

    IGUICallback getCallback();

    void setCallback(IGUICallback callback);

    float getScale();

    void setScale(float newScale);

}
