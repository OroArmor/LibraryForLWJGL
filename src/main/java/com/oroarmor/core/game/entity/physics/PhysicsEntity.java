package com.oroarmor.core.game.entity.physics;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.oroarmor.core.game.entity.Entity;

public abstract class PhysicsEntity extends Entity {

	protected float weight = 0;

	protected Vector3f velocityVector;
	protected Vector3f accelerationVector;
	float maxSpeed = 0;
	float maxAcceleration = 0;

	public PhysicsEntity(final Vector3f position, final Vector3f rotation, final Vector3f scale, final float weight) {
		super(position, rotation, scale);
		velocityVector = new Vector3f();
		accelerationVector = new Vector3f();
		this.weight = weight;
	}

	public void accelerateLocalXZ(final Vector2f xzAcceleration) {
		addAcceleration(new Vector3f(
				xzAcceleration.x * (float) Math.cos(rotationVector.y)
						+ xzAcceleration.y * (float) Math.cos(Math.PI / 2 + rotationVector.y), //
				0, //
				xzAcceleration.x * (float) Math.sin(rotationVector.y)
						+ xzAcceleration.y * (float) Math.sin(Math.PI / 2 + rotationVector.y)//
		));
	}

	public void addAcceleration(final Vector3f acceleration) {
		accelerationVector.add(acceleration);
	}

	public void addForce(final Vector3f force) {
		accelerationVector.add(force.div(weight));
	}

	public void drag(final float xDrag, final float yDrag, final float zDrag) {
		addAcceleration(velocityVector.mul(xDrag, yDrag, zDrag, new Vector3f()).mul(-1));
	}

	public Vector3f getAccelerationVector() {
		return accelerationVector;
	}

	public float getMaxAcceleration() {
		return maxAcceleration;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public Vector3f getVelocityVector() {
		return velocityVector;
	}

	public float getWeight() {
		return weight;
	}

	public void setAccelerationVector(final Vector3f accelerationVector) {
		this.accelerationVector = accelerationVector;
	}

	public void setMaxAcceleration(final float maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public void setMaxSpeed(final float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setVelocityVector(final Vector3f velocityVector) {
		this.velocityVector = velocityVector;
	}

	public void setWeight(final float weight) {
		this.weight = weight;
	}

	@Override
	public void tick(final float delta) {
		super.tick(delta);

		if (maxAcceleration != 0
				&& accelerationVector.lengthSquared() > maxAcceleration * maxAcceleration) {
			accelerationVector.normalize(maxAcceleration);
		}

		velocityVector.add(accelerationVector.mul(delta, new Vector3f()));

		if (maxSpeed != 0 && velocityVector.lengthSquared() > maxSpeed * maxSpeed) {
			velocityVector.normalize(maxSpeed);
		}

		positionVector.add(velocityVector);

		accelerationVector.zero();
	}

}
