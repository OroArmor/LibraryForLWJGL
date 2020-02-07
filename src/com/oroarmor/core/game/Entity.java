package com.oroarmor.core.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.glfw.event.EventListenerManager;
import com.oroarmor.core.glfw.event.key.KeyEventListener;

public abstract class Entity implements KeyEventListener {

	protected Matrix4f modelMatrix;
	protected Vector3f positionVector;
	protected Vector3f rotationVector;
	protected Vector3f scaleVector;

	private boolean active = true;

	public Entity(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.positionVector = position;
		this.rotationVector = rotation;
		this.scaleVector = scale;

		setModelMatrix();
		EventListenerManager.addListener(this);
	}

	public Matrix4f getModelMatrix() {
		return modelMatrix;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	protected void setModelMatrix() {
		modelMatrix = new Matrix4f().identity().translateLocal(positionVector).rotateLocalX(rotationVector.x)
				.rotateLocalY(rotationVector.y).rotateLocalZ(rotationVector.z).scale(scaleVector);
	}

	public void tick() {
		update();
		setModelMatrix();
	}

	public abstract void update();
}
