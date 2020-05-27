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
		this.velocityVector = new Vector3f();
		this.accelerationVector = new Vector3f();
		this.weight = weight;
	}

	public void accelerateLocalXZ(final Vector2f xzAcceleration) {
		this.addAcceleration(new Vector3f(
				xzAcceleration.x * (float) Math.cos(this.rotationVector.y)
						+ xzAcceleration.y * (float) Math.cos(Math.PI / 2 + this.rotationVector.y), //
				0, //
				xzAcceleration.x * (float) Math.sin(this.rotationVector.y)
						+ xzAcceleration.y * (float) Math.sin(Math.PI / 2 + this.rotationVector.y)//
		));
	}

	public void addAcceleration(final Vector3f acceleration) {
		this.accelerationVector.add(acceleration);
	}

	public void addForce(final Vector3f force) {
		this.accelerationVector.add(force.div(this.weight));
	}

	public void drag(final float xDrag, final float yDrag, final float zDrag) {
		this.addAcceleration(this.velocityVector.mul(xDrag, yDrag, zDrag, new Vector3f()).mul(-1));
	}

	public Vector3f getAccelerationVector() {
		return this.accelerationVector;
	}

	public float getMaxAcceleration() {
		return this.maxAcceleration;
	}

	public float getMaxSpeed() {
		return this.maxSpeed;
	}

	public Vector3f getVelocityVector() {
		return this.velocityVector;
	}

	public float getWeight() {
		return this.weight;
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

		if (this.maxAcceleration != 0 && this.accelerationVector.lengthSquared() > this.maxAcceleration * this.maxAcceleration) {
			this.accelerationVector.normalize(this.maxAcceleration);
		}

		this.velocityVector.add(this.accelerationVector.mul(delta, new Vector3f()));

		if (this.maxSpeed != 0 && this.velocityVector.lengthSquared() > this.maxSpeed * this.maxSpeed) {
			this.velocityVector.normalize(this.maxSpeed);
		}

		this.positionVector.add(this.velocityVector);

		this.accelerationVector.zero();
	}

}
