package com.oroarmor.core.game.gui.object;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.game.gui.IGUICallback;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;

public abstract class GUIObject<T extends GUIObject<T>> implements IGUIObject<T> {

	protected boolean active = true;
	protected boolean hovered = false;
	protected boolean clicked = false;

	protected float x;
	protected float y;

	protected IGUICallback callback = new GUICallback();

	protected Matrix4f animationMatrix;

	protected float scale = 1;

	public GUIObject(float x, float y) {
		this.x = x;
		this.y = y;

		this.addToButtonListeners();
		this.addToPositionListeners();

		animationMatrix = new Matrix4f().identity();
	}

	@Override
	public void processMousePressEvent(MousePressEvent event) {
		if (inBounds(event.getX(), event.getY())) {
			this.callback.onClick(event.getButton());
			this.clicked = true;
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void processMouseReleasedEvent(MouseReleaseEvent event) {
		if (this.clicked) {
			boolean inbounds = inBounds(event.getX(), event.getY());
			this.callback.onRelease(event.getButton(), inbounds);
			if (inbounds) {
				this.callback.onHover();
			} else {
				this.callback.onHoverStop();
			}
			this.clicked = false;
		}
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public IGUICallback getCallback() {
		return callback;
	}

	@Override
	public void setCallback(IGUICallback callback) {
		this.callback = callback;
	}

	public abstract boolean inBounds(float x, float y);

	@Override
	public void processMousePositionEvent(MousePositionEvent event) {
		if (!clicked) {
			if (this.inBounds(event.getMouseX(), event.getMouseY())) {
				if (!this.hovered)
					this.callback.onHover();
				this.hovered = true;
			} else if (this.hovered) {
				this.hovered = false;
				this.callback.onHoverStop();
			}
		}
	}

	private boolean hasParent = false;

	@Override
	public void setHasParent(boolean hasParent) {
		this.hasParent = true;
	}

	@Override
	public boolean hasParent() {
		return hasParent;
	}

	protected List<IAnimation<T>> animations = new ArrayList<IAnimation<T>>();
	protected List<Long> animationDurations = new ArrayList<Long>();

	@Override
	public void triggerAnimation(IAnimation<T> animation) {
		animations.add(animation);
		animationDurations.add(System.currentTimeMillis());
	}

	public Matrix4f getAnimationMatrix() {
		return animationMatrix;
	}

	public void setAnimationMatrix(Matrix4f animationMatrix) {
		this.animationMatrix = animationMatrix;
	}

	public void setScale(float newScale) {
		animationMatrix.scale(newScale / scale);
		scale = newScale;
	}

	public float getScale() {
		return scale;
	}

}
