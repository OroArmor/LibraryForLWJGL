package com.oroarmor.core.game.gui.object;

import java.util.ArrayList;
import java.util.List;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.IGUICallback;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import org.joml.Matrix4f;

public abstract class GUIObject<T extends GUIObject<T>> implements IGUIObject<T> {
    protected boolean active = true;
    protected boolean hovered = false;
    protected boolean clicked = false;

    protected float x;
    protected float y;

    protected IGUICallback callback = new GUICallback();

    protected Matrix4f animationMatrix;

    protected float scale = 1;
    protected List<IAnimation<T>> animations = new ArrayList<>();
    protected List<Long> animationDurations = new ArrayList<>();
    private boolean hasParent = false;

    public GUIObject(float x, float y) {
        this.x = x;
        this.y = y;

        addToButtonListeners();
        addToPositionListeners();

        this.animationMatrix = new Matrix4f().identity();
    }

    @Override
    public Matrix4f getAnimationMatrix() {
        return this.animationMatrix;
    }

    @Override
    public void setAnimationMatrix(final Matrix4f animationMatrix) {
        this.animationMatrix = animationMatrix;
    }

    @Override
    public IGUICallback getCallback() {
        return this.callback;
    }

    @Override
    public void setCallback(final IGUICallback callback) {
        this.callback = callback;
    }

    @Override
    public float getScale() {
        return this.scale;
    }

    @Override
    public void setScale(float newScale) {
        this.animationMatrix.scale(newScale / this.scale);
        this.scale = newScale;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public boolean hasParent() {
        return this.hasParent;
    }

    public abstract boolean inBounds(float x, float y);

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void processMousePositionEvent(MousePositionEvent event) {
        if (!this.clicked) {
            if (this.inBounds(event.getMouseX(), event.getMouseY())) {
                if (!this.hovered) {
                    this.callback.onHover();
                }
                this.hovered = true;
            } else if (this.hovered) {
                this.hovered = false;
                this.callback.onHoverStop();
            }
        }
    }

    @Override
    public void processMousePressEvent(MousePressEvent event) {
        if (this.inBounds(event.getX(), event.getY())) {
            this.callback.onClick(event.getButton());
            this.clicked = true;
        }
    }

    @Override
    public void processMouseReleasedEvent(MouseReleaseEvent event) {
        if (this.clicked) {
            boolean inbounds = this.inBounds(event.getX(), event.getY());
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
    public void setHasParent(boolean hasParent) {
        this.hasParent = true;
    }

    @Override
    public void triggerAnimation(IAnimation<T> animation) {
        this.animations.add(animation);
        this.animationDurations.add(System.currentTimeMillis());
    }
}
