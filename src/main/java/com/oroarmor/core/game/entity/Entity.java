package com.oroarmor.core.game.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class Entity {

	protected Matrix4f modelMatrix;
	protected Vector3f positionVector;
	protected Vector3f rotationVector;
	protected Vector3f scaleVector;

	public Entity(final Vector3f position, final Vector3f rotation, final Vector3f scale) {
		this.positionVector = position;
		this.rotationVector = rotation;
		this.scaleVector = scale;

		this.setModelMatrix();
	}

	public Matrix4f getModelMatrix() {
		return this.modelMatrix;
	}

	public Vector3f getPosition() {
		return this.positionVector;
	}

	protected void setModelMatrix() {
		this.modelMatrix = new Matrix4f().identity().translateLocal(this.positionVector).rotateLocalX(this.rotationVector.x)
				.rotateLocalY(this.rotationVector.y).rotateLocalZ(this.rotationVector.z).scale(this.scaleVector);
	}

	public void tick(final float delta) {
		this.update(delta);
		this.setModelMatrix();
	}

	public abstract void update(float delta);
}
