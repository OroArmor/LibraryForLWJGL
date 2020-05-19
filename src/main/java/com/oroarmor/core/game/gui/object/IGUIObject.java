package com.oroarmor.core.game.gui.object;

import org.joml.Matrix4f;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.game.gui.IGUICallback;
import com.oroarmor.core.glfw.event.mouse.button.MouseButtonEventListener;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEventListener;

public interface IGUIObject<T extends IGUIObject<T>>
		extends MouseButtonEventListener, MousePositionEventListener, IGUI<T> {

	public IGUICallback getCallback();

	public void setCallback(IGUICallback callback);

	public Matrix4f getAnimationMatrix();

	public void setAnimationMatrix(Matrix4f animationMatrix);

	public void setScale(float newScale);

	public float getScale();

}
