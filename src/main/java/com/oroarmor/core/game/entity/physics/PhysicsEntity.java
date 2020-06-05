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

	public PhysicsEntity(Vector3f position, Vector3f rotation, Vector3f scale, float weight) {
		super(position, rotation, scale);
		velocityVector = new Vector3f();
		accelerationVector = new Vector3f();
		this.weight = weight;
	}

	public void accelerateLocalXZ(Vector2f xzAcceleration) {
		Vector3f xz3D = new Vector3f(xzAcceleration.x, 0, xzAcceleration.y);
		Vector3f yUnit = new Vector3f(0, 1, 0);

		Vector3f finalStep = yUnit.mul(yUnit.dot(xz3D), new Vector3f()).mul(1 - (float) Math.cos(rotationVector.y));
		addAcceleration(xz3D.mul((float) Math.cos(rotationVector.y), new Vector3f())
				.add(yUnit.cross(xz3D, new Vector3f()).mul((float) Math.sin(rotationVector.y), new Vector3f()),
						new Vector3f())
				.add(finalStep, new Vector3f()));

	}

	public void addAcceleration(Vector3f acceleration) {
		accelerationVector.add(acceleration);
	}

	public void addForce(Vector3f force) {
		accelerationVector.add(force.div(weight));
	}

	public void drag(float xDrag, float yDrag, float zDrag) {
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

	public void setAccelerationVector(Vector3f accelerationVector) {
		this.accelerationVector = accelerationVector;
	}

	public void setMaxAcceleration(float maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setVelocityVector(Vector3f velocityVector) {
		this.velocityVector = velocityVector;
	}

	public void setWeight(float weight) {
		this.weight = weight;
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

}
