package com.oroarmor.core.game;

import org.joml.Vector2f;
import org.joml.Vector3f;

public abstract class PhysicsEntity extends Entity {

	protected float weight = 0;

	protected Vector3f velocityVector;
	protected Vector3f accelerationVector;
	float maxSpeed = 0;
	float maxAcceleration = 0;

	public PhysicsEntity(Vector3f position, Vector3f rotation, Vector3f scale, float weight) {
		super(position, rotation, scale);
		velocityVector = new Vector3f();
		accelerationVector = new Vector3f();
		this.weight = weight;
	}

	public Vector3f getVelocityVector() {
		return velocityVector;
	}

	public void setVelocityVector(Vector3f velocityVector) {
		this.velocityVector = velocityVector;
	}

	public Vector3f getAccelerationVector() {
		return accelerationVector;
	}

	public void setAccelerationVector(Vector3f accelerationVector) {
		this.accelerationVector = accelerationVector;
	}

	public void addForce(Vector3f force) {
		this.accelerationVector.add(force.div(weight));
	}

	public void addAcceleration(Vector3f acceleration) {
		this.accelerationVector.add(acceleration);
	}

	public void accelerateLocalXZ(Vector2f xzAcceleration) {
		this.addAcceleration(new Vector3f(
				xzAcceleration.x * (float) Math.cos(rotationVector.y)
						+ xzAcceleration.y * (float) Math.cos(Math.PI / 2 + rotationVector.y), //
				0, //
				xzAcceleration.x * (float) Math.sin(rotationVector.y)
						+ xzAcceleration.y * (float) Math.sin(Math.PI / 2 + rotationVector.y)//
		));
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(float maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);

		if (maxAcceleration != 0 && accelerationVector.lengthSquared() > maxAcceleration * maxAcceleration) {
			accelerationVector.normalize(maxAcceleration);
		}

		velocityVector.add(accelerationVector.mul(delta, new Vector3f()));

		if (maxSpeed != 0 && velocityVector.lengthSquared() > maxSpeed * maxSpeed) {
			velocityVector.normalize(maxSpeed);
		}

		positionVector.add(velocityVector);

		accelerationVector.zero();
	}

	public void drag(float xDrag, float yDrag, float zDrag) {
		this.addAcceleration(this.velocityVector.mul(xDrag, yDrag, zDrag, new Vector3f()).mul(-1));
	}

}
