package com.oroarmor.core.game.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class Entity {

	protected Matrix4f modelMatrix;
	protected Vector3f positionVector;
	protected Vector3f rotationVector;
	protected Vector3f scaleVector;

	public Entity(Vector3f position, Vector3f rotation, Vector3f scale) {
		positionVector = position;
		rotationVector = rotation;
		scaleVector = scale;

		setModelMatrix();
	}

	public Matrix4f getModelMatrix() {
		return modelMatrix;
	}

	public Vector3f getPosition() {
		return positionVector;
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
