package com.oroarmor.core.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.glfw.event.key.KeyEventListener;

public abstract class Entity implements KeyEventListener {

	private boolean active = true;
	protected Matrix4f modelMatrix;
	protected Vector3f positionVector;
	protected Vector3f rotationVector;
	protected Vector3f scaleVector;

	public Entity(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.positionVector = position;
		this.rotationVector = rotation;
		this.scaleVector = scale;

		setModelMatrix();
		addToKeyListeners();
	}

	public Matrix4f getModelMatrix() {
		return modelMatrix;
	}

	public Vector3f getPosition() {
		return positionVector;
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

	public void tick(float delta) {
		update(delta);
		setModelMatrix();
	}

	public abstract void update(float delta);
}
