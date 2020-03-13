package com.oroarmor.core.game.gui;

import org.joml.Matrix4f;

import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;

public abstract class GUIObject implements IGUIObject {

	protected boolean active = true;
	protected boolean hovered = false;
	protected boolean clicked = false;

	protected float x;
	protected float y;

	protected IGUICallback callback = new GUICallback();

	protected Matrix4f animationMatrix;

	public GUIObject(float x, float y) {
		this.x = x;
		this.y = y;

		this.addToButtonListeners();
		this.addToPositionListeners();

		animationMatrix = new Matrix4f();
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
				this.callback.onHover();
				this.hovered = true;
			} else if (this.hovered) {
				this.hovered = false;
				this.callback.onHoverStop();
			}
		}
	}

	private boolean hasParent = false;

	public void setHasParent(boolean hasParent) {
		this.hasParent = true;
	}

	public boolean hasParent() {
		return hasParent;
	}

}
